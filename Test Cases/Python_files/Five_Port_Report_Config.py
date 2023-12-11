
import os, sys
import json
import io
import enum
import time
import operator
import datetime, os, shutil 
import os, glob
import shutil

report_path = "C:\\GRL\\USBPD-C2-Browser-App\\Report\\TempReport"
report_result_path="C:\\GRL\\Comparison_Tool_Report"



class FivePortDataConfig:

    def __init__(self, filename,golden_csv,sum_report):
        self.latest_report = None
        self.filename = filename
        self.golden_csv = golden_csv
        self.summary_report_name = summary_report
        self.summary_report_fol = report_result_path+os.sep+self.summary_report_name
        self.edit_config_json_value(self.filename,self.golden_csv,self.summary_report)
        self.alter_reports_name(self,self.summary_report_fol)
        

    def edit_config_json_value(self, file,goldencsv):
        with open(file, "r+") as jsonFile:  
           #To Find latest Folder and update to input_file in config.json file  
           self.latest_report = max(glob.glob(os.path.join(report_path, '*')), key=os.path.getmtime)
           print(self.latest_report) 
           #To Create Comparison Tool Folder if it is not Exists
           if not os.path.exists(report_result_path):
              os.makedirs(report_result_path)       
           #To update input report and goldencsv folder and report  file path (output directory) to config.json       
           data = json.load(jsonFile)
           data["ReportFilePath"] = report_result_path
           data["FileLocation"] = updated_run_report
           data["GoldenCSVLocation"] = goldencsv
           jsonFile.seek(0)  # rewind
           json.dump(data, jsonFile)
           jsonFile.truncate()
           jsonFile.close()
                

    def alter_reports_name(self,summary_report_path):
        #To create summary report folder
        if not os.path.exists(summary_report_path+os.sep+"Executed_Test_Report"):
            os.makedirs(summary_report_path+os.sep+"Executed_Test_Report") 
        #To split Latest executed report folder path and move the report to comparison Tool    
        self.head_tail = os.path.split(self.latest_report) 
        source =self.latest_report
        destination = self.summary_report_fol+self.head_tail[1]
        shutil.copytree(src, dst) 
         

            



                 
def main():
   
    
    filename        = sys.argv[1]
    golden_csv      = sys.argv[2]
    summary_report  = sys.argv[3]
    
    reader = FivePortDataConfig(filename,golden_csv,summary_report)


if __name__ == "__main__":
    main()
   
   