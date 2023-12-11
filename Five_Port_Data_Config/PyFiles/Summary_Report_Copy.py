
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



class CopySummaryReport:

    def __init__(self,summary_report_name):
        self.summary_report_name = summary_report_name
        self.summary_report_folder = report_result_path+'\\'+self.summary_report_name
       
        self.copy_final_summary_reports(self.summary_report_folder)
        
    def copy_final_summary_reports(self,summary_report_folder):
        try:
            #Modified Summary Report name as Overall Summary report in Temp folder
            source = summary_report_folder+'\\'+'Temp'+'\\'+'Summary_Report.xlsx'
            modified_source =  summary_report_folder+'\\Temp'+'\\OverAll_Summary_Report.xlsx'
            os.rename(source,modified_source) 
            #destination folder 
            destination = summary_report_folder
            #Copy Overall Summary Report to Fiveport folder    
            shutil.copy2(modified_source,destination)
        except Exception as e:  
            print(str(e)) 

                
def main():
   
    summary_report  =  sys.argv[1] 
    reader = CopySummaryReport(summary_report)


if __name__ == "__main__":
    main()
   
   