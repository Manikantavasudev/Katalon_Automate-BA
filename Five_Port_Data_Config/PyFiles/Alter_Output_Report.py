
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
report_result_path="C:\\GRL\\C2_Comparison_Tool_Report"



class FivePortDataConfig:

    def __init__(self,summary_report_name,port_name):
        self.latest_report = None
        self.latest_executed_report = None
        self.summary_report_name = summary_report_name
        self.summary_report_folder = report_result_path+'\\'+self.summary_report_name
        self.port_name = port_name
        self.alter_output_reports_name(self.summary_report_folder,self.port_name)
        
    def alter_output_reports_name(self,summary_report_folder,port_name):
        try:
            #To split Latest executed report folder path and move the report to comparison Tool (comparison xls folder) 
            self.latest_report = max(glob.glob(os.path.join(report_result_path, '*')), key=os.path.getmtime)
            print(self.latest_report)   

            #To rename comparison tool report 
            self.head_tail = os.path.split(self.latest_report) 
            self.latest_comp_report_name = port_name+'_'+self.head_tail[1]
            print(self.head_tail[1])
            os.chdir(r"C:\\GRL\\C2_Comparison_Tool_Report")
            modified_report_fol = port_name+'_'+self.head_tail[1]
            os.rename(self.head_tail[1],modified_report_fol)

            #To create summary report folder
            if not os.path.exists(summary_report_folder+'\\'+"Executed_Test_Report"):
                os.makedirs(summary_report_folder+'\\'+"Executed_Test_Report")   
            #To create Temp report folder
            if not os.path.exists(summary_report_folder+'\\'+"Temp"):
                os.makedirs(summary_report_folder+'\\'+"Temp")        

            #To move renamed reports to summary report folder
            src =report_result_path+'\\'+modified_report_fol
            dst = summary_report_folder
            #src = report_result_path+'\\'+modified_report_fol
            shutil.move(src, dst, copy_function = shutil.copytree)

            #To move Executed report to Executed Report folder(inside summary report)
            self.latest_executed_report = max(glob.glob(os.path.join(report_path, '*')), key=os.path.getmtime)
            print(self.latest_executed_report)        
            self.head_tail = os.path.split(self.latest_executed_report) 
            source = self.latest_executed_report
            destination = summary_report_folder+'\\'+"Executed_Test_Report"+'\\'+self.head_tail[1]
            #shutil.move(source, destination, copy_function = shutil.copytree)
            shutil.copytree(source, destination)
        except Exception as e:
            print(str(e))

       
        
         
                 
def main():
   
    summary_report  = sys.argv[1]
    port_name       = sys.argv[2]
   
    reader = FivePortDataConfig(summary_report,port_name)


if __name__ == "__main__":
    main()
   
   