import csv
import datetime
import glob
import json
import os
import time
from collections import OrderedDict
from os import path


class JsonDataLoad:
    def __init__(self):
        try:

            base_path = os.path.dirname(os.path.realpath(__file__))
            config_json_path = os.path.join(base_path, "Config" + ".json")
            if not path.exists(config_json_path):
                print("Config.json file is not found")
                self.create_config_json(config_json_path)
            config_data_dict = json.loads(open(config_json_path).read())
            pd_app_mode_json_path = os.path.join(base_path, "Pd_App_Mode" + ".json")
            if not path.exists(pd_app_mode_json_path):
                print("Pd_App_Mode.json file is not found")
                self.create_PDAppMode_json(pd_app_mode_json_path)
            pd_test_cases_c2_json_path = os.path.join(base_path, "Pd_Test_Cases_C2" + ".json")
            if not path.exists(pd_test_cases_c2_json_path):
                print("Pd_Test_Cases_C2.json file is not found")
                exit("Pd_Test_Cases_C2 file is not found")
            pd_appmode_data_dict = json.loads(open(pd_app_mode_json_path).read())
            self.pd_test_cases_dict = json.loads(open(pd_test_cases_c2_json_path).read())
            '''
            if True:
                with open('Pd_Test_Cases_C2.csv', 'w') as csv_write:
                    for moi in pd_test_cases_dict.keys():
                        csv_write.write("%s \n" % moi)
                        for test_id in pd_test_cases_dict[moi].keys():
                            csv_write.write("%s, %s\n" % (test_id, pd_test_cases_dict[moi][test_id]))
            '''
            # self.pd_vif_dict=pd_appmode_data_dict["VIFData"]
            self.vif_dict = pd_appmode_data_dict["VIFHeading"]
            #self.vif_display_heading = pd_appmode_data_dict["VIFReportDisplayField"].keys()
            self.vif_display_heading =list()
            for keys in config_data_dict["VIFReportDisplayField"].keys():
                if config_data_dict["VIFReportDisplayField"][keys] =="True":
                    self.vif_display_heading.append(keys)
            self.row_vif_display_heading = list()
            for keys in config_data_dict["RowVIFReportDisplayField"].keys():
                if config_data_dict["RowVIFReportDisplayField"][keys] == "True":
                    self.row_vif_display_heading.append(keys)
            self.pd_vif_dict = {self.vif_dict[x]: x for x in self.vif_dict.keys()}
            self.pd_appmode_dict = pd_appmode_data_dict["PDAppMode"]
            self.pd_test_results = pd_appmode_data_dict["TestResult"]
            self.pd_appmode_filenames = list(pd_appmode_data_dict["PDAppMode"].keys())
            self.pd_appmode_folder_names = list(pd_appmode_data_dict["PDAppMode"].values())
            self.report_file_name = self.find_file_details(config_data_dict)
            self.test_proj_location = config_data_dict["FileLocation"]
            self.golden_result_location = config_data_dict["GoldenCSVLocation"]
            self.output_file_path = config_data_dict["ReportFilePath"]
            self.moi_specific_vif_data=pd_appmode_data_dict["MoiSpecificVifData"]

            self.vif_dict1 = OrderedDict(
                {'Date': "Date and Time", 'Five Port': "Five Port Enabled", 'C2 Sno': "GRL USB - PD_C2 Serial No",
                 'Test Er': "Test Engineer", 'Port Type': "PD_Port_Type", 'VIF Name': "VIF File name",
                 'VIF Spec': "VIF_Specification", 'PD Spec Rev': "PD_Specification_Revision",
                 'Folder Name': '', 'Folder Abs Path': '', 'Vendor': "Vendor_Name",
                 'SW Ver': "GRL USB-PD Software Version", 'FW Ver': "GRL USB-PD Firmware Version",
                 'Dev Type': "Device Type"})
            self.vif_field_heading = ['Date', 'Five Port', 'C2 Sno', 'Test Er', 'Port Type', 'VIF Name', 'VIF Spec',
                                      'PD Spec Rev',
                                      'Folder Name', 'Folder Abs Path', 'Vendor', 'SW Ver', 'FW Ver', 'Dev Type']
        except Exception as e:
            print("JsonDataLoad _ __init__ :-" + str(e))

    def create_PDAppMode_json(self, pd_app_mode_json_path):
        try:
            pdapp_dict = OrderedDict({"PDAppMode": OrderedDict({'COMPLIANCE_TEST_PD3': "PD3",
                                                                'USB_C_SOURCE_POWER_TEST': "SPT",
                                                                'QC4_TEST': "QC4",
                                                                'DETERMINISTIC_TESTS': "DETER",
                                                                'FUNCTIONAL_TESTS': "FUNC",
                                                                'COMMUNICATION_ENGINE_TESTS': "PD2",
                                                                'DP_ALT_MODE_TESTS': "DP_ALT",
                                                                'QC_LEGACY': "QC3",
                                                                'TBT_POWER_TESTING': "TBT",
                                                                'PD_Merged': "PDMerged",
                                                                'CHROME_BOOK_TEST': "MFG_WWC",
                                                                'MFI_TESTS': "MFI",
                                                                'BC_1_2_TESTS': "BC_1_2"
                                                                }),
                                      'TestResult': ["FAIL", "INCOMPLETE", "SKIPPED", "WARNING", "PASS", "NA",
                                                     "RUNNING",
                                                     "RESERVED", "NOT_EXECUTED", "ABORTED"],
                                      })
            file = open(pd_app_mode_json_path, "w")
            file.write(json.dumps(pdapp_dict, sort_keys=True, indent=4))
            print("Pd_App_Mode.json file is created :-" + pd_app_mode_json_path)
        except Exception as e:
            print("JsonDataLoad _ create_PDAppMode_json :-" + str(e))

    def find_file_details(self, config_dict):
        try:
            file_path = os.path.dirname(os.path.realpath(__file__))
            file_name = "ConsolidatedReport"
            file_type = "xlsx"
            if not config_dict["ReportFileName"] == "":
                file_name = config_dict["ReportFileName"]
            if not config_dict["ReportFilePath"] == "":
                file_path = config_dict["ReportFilePath"]
            if not os.path.exists(file_path):
                os.mkdir(file_path)
            time_str=""
            rowwise_file_name = "Rowwise_" + file_name
            if config_dict["ReportWithTimestamp"].lower() == "true":
                time_stamp = datetime.datetime.fromtimestamp(time.time()).strftime("%Y_%m_%d_%H_%M_%S")
                time_str="_" + time_stamp
            report_folder="Report_"+ time_stamp
            report_folder=os.path.join(file_path, report_folder)
            if not os.path.exists(report_folder):
                os.mkdir(report_folder)
            file_name+= time_str + "." + file_type
            rowwise_file_name+=time_str+"." + file_type
            self.rowwise_file_name =os.path.join(report_folder, rowwise_file_name)
            analysis_file_name ="Analysis"+time_str+"." + file_type
            self.analysis_file_name=os.path.join(report_folder, analysis_file_name)
            return os.path.join(report_folder, file_name)
        except Exception as e:
            print("JsonDataLoad _ find_file_details :-" + str(e))

    def create_config_json(self, config_json_path):
        try:
            config_dict = OrderedDict({'FileLocation': "Type your csv file folder location",
                                       'ReportFilePath': "Type your report file path",
                                       'ReportFileName': "Type your report file name",
                                       'ReportWithTimestamp': "True"
                                       })
            file = open(config_json_path, "w")
            file.write(json.dumps(config_dict, sort_keys=True, indent=4))
            print("Config.json file is created :-" + config_json_path)
        except Exception as e:
            print("JsonDataLoad _ create_config_json :-" + str(e))

    def load_golden_csv_data(self, golden_csv_file_path):
        try:
            pd_test_id = OrderedDict()
            for moi_name in self.pd_test_cases_dict.keys():
                for testid in self.pd_test_cases_dict[moi_name].keys():
                    pd_test_id[testid] = self.pd_test_cases_dict[moi_name][testid]

            obj_test_golden_result_data = OrderedDict()
            all_file_paths = glob.glob(os.path.join(golden_csv_file_path, "**/*.csv"), recursive=True)
            for file_path in all_file_paths:
                file_name = os.path.splitext(os.path.basename(file_path))[0]
                if file_name.endswith("Golden_Report"):
                    project_name = file_name.split('_')[0]
                    if project_name not in obj_test_golden_result_data.keys():
                        obj_test_golden_result_data[project_name] = OrderedDict()
                    with open(file_path, 'r') as csvFile:
                        csv_reader = csv.reader(csvFile, delimiter=',')
                        for row in csv_reader:
                            if len(row) >= 4:
                                if row[3].strip() in self.pd_test_results:
                                    if row[1].strip() in pd_test_id.keys():
                                        obj_test_golden_result_data[project_name][row[1].strip()] = row[3].strip()
                                    else:
                                        print("Test ID is not in Main Test Cases List  - " + row[1].strip())
                                        print(file_path)
            return obj_test_golden_result_data
        except Exception as e:
            print("JsonDataLoad _ load_golden_csv_data :-" + str(e))
