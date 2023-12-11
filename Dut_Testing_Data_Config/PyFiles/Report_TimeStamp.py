
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



class TimeStampReader:

    def __init__(self, filename,golden_csv):
        self.latest_report = None
        self.filename = filename
        self.golden_csv = golden_csv
        self.edit_config_json_value(self.filename,self.golden_csv)
        
        

    def edit_config_json_value(self, file,golden_csv):
        with open(file, "r+") as jsonFile:   
           self.latest_report = max(glob.glob(os.path.join(report_path, '*')), key=os.path.getmtime)
           print(self.latest_report) 
           updated_run_report = max(glob.glob(os.path.join(self.latest_report, '*')), key=os.path.getmtime)
           print(updated_run_report)
           #To Create Comparison Tool Folder if it is not Exists
           if not os.path.exists(report_result_path):
                os.makedirs(report_result_path)
           #To update input report and goldencsv folder and report  file path (output directory) to config.json                   
           data = json.load(jsonFile)
           data["ReportFilePath"] = report_result_path
           data["FileLocation"] = updated_run_report
           data["GoldenCSVLocation"] = golden_csv
           jsonFile.seek(0)  # rewind
           json.dump(data, jsonFile)
           jsonFile.truncate()
           jsonFile.close()
           
                 
def main():
   
    filename = sys.argv[1]
    golden_csv      = sys.argv[2]
    reader = TimeStampReader(filename,golden_csv)


if __name__ == "__main__":
    main()
   
   