import os, sys
import json
import io
import time
import datetime,shutil 
from datetime import datetime
from datetime import timedelta
import glob
import email, smtplib, ssl
from email import encoders
from email.mime.base import MIMEBase
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.utils import make_msgid
import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText


report_result_path="C:\\GRL\\C2_Comparison_Tool_Report"

class Auto_Email_Configuration:
    def __init__(self,latest_five_port_folder,json_filename,test_start_time,test_stop_time,td_mins,c2_app_version,katalon_build_version):
        self.five_port_latest_fol = latest_five_port_folder
        self.general_json_path = json_filename       
        #latest output folder variables
        self.latest_report=None
        self.list_of_files = list()
        self.latest_port_xls_file = None

        #Email config variables
        self.testexe_start_time = test_start_time
        self.testexe_stop_time = test_stop_time
        self.tot_timediff_in_min=td_mins
        self.c2_app_ver = c2_app_version
        self.katalon_build_ver = katalon_build_version      
        self.sender_email_add = None
        self.sender_email_pass = None
        self.receiver_email_id = list()
        
             
    def get_latest_summary_xls_file(self):
        #self.latest_report = max(glob.glob(os.path.join(report_result_path, '*')), key=os.path.getmtime)  
        #print(self.latest_report) 
        self.latest_report = report_result_path+'\\'+self.five_port_latest_fol+'\\'+'Temp'
        # Get the list of all files in directory tree at given path
        self.list_of_files = list()
        for (dirpath, dirnames, filenames) in os.walk(self.latest_report):
            self.list_of_files += [os.path.join(dirpath, file) for file in filenames]   
        print(self.list_of_files)    
        #get xls summary output file 
        for xls_file in self.list_of_files:
            if '.xlsx' in xls_file:                  
                if 'OverAll_Summary_Report' in xls_file or 'Summary_Report' in xls_file :
                    #avoid xls file with '.~lock.' extension
                    if '.~lock.' not in xls_file:
                        self.latest_port_xls_file = xls_file
                        print("output xls filename based on user config port::  "+xls_file)
        
  

    def set_email_configuration(self):
        subject = "Automated Mail: C2 Katalon Automation Five Port Report"          
        html = """\
            <html>
            <body>
            <style type="text/css">
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
                padding: 15px;
                text-align: left;
                tr:hover {background-color: #FFFFFF;}
            }
            </style>
             <p>Hello Team,<br>
                <span> This is a system generated mail</span><br>
            </p>
            <table>         
            <tr>
            <td >Test Start Time</td>
            <td >$(test_start_tym)</td>
            </tr>
            <tr>
            <td>Test End Time</td>
            <td >$(test_stop_tym)</td>
            </tr>
            <tr>
            <td>Total Test Duration</td>
            <td >$(test_duration_tym) minutes</td>
            </tr>
            <tr>
            <td >C2 Application Version</td>
            <td >$(c2_app_ver)</td>
            </tr>
            <tr>
            <td>C2 Katalon Build Version</td>
            <td >$(katalon_build_ver)</td>
            </tr>
            <tr>
            <td>Attached Files</td>
            <td >Golden file Compare Tool Summary reports For FivePort</td>
            </tr>
            </table>
            <p>Regards,<br>
                <span> C2 Automation Team </span><br>
            </p>
            </body>
            </html>
            """
        html = html.replace("$(test_start_tym)", str(self.testexe_start_time) )
        html = html.replace("$(test_stop_tym)", str(self.testexe_stop_time))
        html = html.replace("$(test_duration_tym)", str(self.tot_timediff_in_min))
        html = html.replace("$(c2_app_ver)", str(self.c2_app_ver))
        html = html.replace("$(katalon_build_ver)", str(self.katalon_build_ver))
       
        print(html)

        
        # Create a multipart message and set headers
        message = MIMEMultipart()
        message["From"] =self.sender_email_add
        message["To"] = ','.join(self.receiver_email_id)
        message["Subject"] = subject    
        #create unique id for maintaining email chain 
        message["In-Reply-To"] = "unique-id"
        message["References"] = "unique-id"
        # Add body to email
        message.attach(MIMEText(html, 'html'))
        
        # Open xls file in binary mode
        with open(self.latest_port_xls_file, "rb") as attachment:
            # Add file as application/octet-stream
            # Email client can usually download this automatically as attachment
            part = MIMEBase("application", "octet-stream")
            part.set_payload(attachment.read())

        # Encode file in ASCII characters to send by email    
        encoders.encode_base64(part)    
        # Add header as key/value pair to attachment part
        part.add_header("Content-Disposition",f"attachment; filename= {self.latest_port_xls_file}",)
        # Add attachment to message and convert message to string
        message.attach(part)
        text = message.as_string()
        # Log in to server using secure context and send email
        context = ssl.create_default_context()
        with smtplib.SMTP_SSL("smtp.zoho.com", 465, context=context) as server:
            server.login(self.sender_email_add, self.sender_email_pass)
            server.sendmail(self.sender_email_add, self.receiver_email_id, text)


    def get_json_data(self):
        with open(self.general_json_path, "r") as rf:
            self.prop = json.load(rf)
            self.sender_email_add = self.prop["sender_email_id"]
            self.sender_email_pass = self.prop["sender_email_password"]
            self.receiver_email_id = self.prop["receiver_email_id_list"]
           

               
def main():  
    #latest five port folder
    latest_five_port_folder = sys.argv[1]
    #read system arguments
    general_config_json_path = sys.argv[2]
    #read list mail content arguments 
    test_start_time =  sys.argv[3]
    test_stop_time = sys.argv[4]
    c2_app_version = sys.argv[5]
    katalon_build_version = sys.argv[6]
    #Calculate Total Time duration
    time_fmt = '%Y_%m_%d-%H_%M_%S'
    tstamp1 = datetime.strptime(test_start_time, time_fmt)
    tstamp2 = datetime.strptime(test_stop_time, time_fmt)
    td_mins = int(round(abs((tstamp1 - tstamp2).total_seconds()) / 60))

    #initialize object and call methods
    mail_config_obj = Auto_Email_Configuration(latest_five_port_folder,general_config_json_path,test_start_time,test_stop_time,td_mins,c2_app_version,katalon_build_version)
    mail_config_obj.get_json_data()
    mail_config_obj.get_latest_summary_xls_file()
    mail_config_obj.set_email_configuration()

   

if __name__ == "__main__":
    main()
   
   
    
    
    
    
    
    