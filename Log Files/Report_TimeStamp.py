
import os, sys
import json
import io
import enum
import time
import operator
import datetime, os, shutil 
import os, glob
matchtestcount = 0
alist={}
report_path = "C:\\GRL\\USBPD-C2-Browser-App\\Report\\TempReport"
report_result_path="C:\\GRL\\Comparison_Tool_Report"



class TimeStampReader:

    def __init__(self, filename):
        self.filename = filename
        self.edit_json_value(self.filename)
        

    def edit_json_value(self, file):
        with open(file, "r+") as jsonFile:   
           latest_report = max(glob.glob(os.path.join(report_path, '*')), key=os.path.getmtime)
           print(latest_report) 
           if not os.path.exists(report_result_path):
              os.makedirs(report_result_path)          
           data = json.load(jsonFile)
           data["ReportFilePath"] = report_result_path
           data["FileLocation"] = latest_report
           jsonFile.seek(0)  # rewind
           json.dump(data, jsonFile)
           #jsonFile.truncate()
           jsonFile.close()
           
           
           
             
       
def main():
   
    filename = sys.argv[1]
    reader = TimeStampReader(filename)


if __name__ == "__main__":
    main()
   
   