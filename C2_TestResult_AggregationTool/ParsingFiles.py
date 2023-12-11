import csv
from collections import OrderedDict

from TestNameResultData import TestNameResultData
from TestRunData import TestRunData


class ParsingFiles:
    def __init__(self, pd_test_results, pd_test_list):
        try:
            self.moi_result_data = OrderedDict()
            self.pd_test_results = pd_test_results
            self.pd_test_list = pd_test_list
        except Exception as e:
            print("ParsingFiles _ __init__ :-" + str(e))

    def process_files(self, csv_proj_files_path):
        try:
            for proj_name in csv_proj_files_path.keys():
                for moi_name in csv_proj_files_path[proj_name].csv_files_path.keys():
                    for run_name in csv_proj_files_path[proj_name].csv_files_path[moi_name].keys():
                        rep_name = ''
                        csv_path_run = csv_proj_files_path[proj_name].csv_files_path[moi_name][run_name]
                        for rep_name in csv_path_run.keys():
                            self.read_test_details(proj_name, moi_name, run_name, rep_name, csv_path_run[rep_name])
                        print(proj_name, moi_name, run_name, rep_name,
                              csv_proj_files_path[proj_name].csv_files_path[moi_name][run_name][rep_name])
            return self.moi_result_data
        except Exception as e:
            print("ParsingFiles_ _ process_files :-" + str(e))

    def read_test_details(self, proj_name, moi_name, run_name, rep_name, csv_file_path):
        try:
            proj_moi_data = None
            if proj_name not in self.moi_result_data:
                self.moi_result_data[proj_name] = OrderedDict()
            if moi_name not in self.moi_result_data[proj_name]:
                self.moi_result_data[proj_name][moi_name] = OrderedDict()
                proj_moi_data = self.moi_result_data[proj_name][moi_name]
                for test_id in self.pd_test_list[moi_name].keys():
                    proj_moi_data[test_id] = TestNameResultData()
                    proj_moi_data[test_id].test_id = test_id
                    proj_moi_data[test_id].test_name = self.pd_test_list[moi_name][test_id]
                    proj_moi_data[test_id].run_data[run_name] = TestRunData()
                    proj_moi_data[test_id].run_data[run_name].rep_data[rep_name] = "NS"
            else:
                proj_moi_data = self.moi_result_data[proj_name][moi_name]
            with open(csv_file_path) as csvFile:
                csv_reader = csv.reader(csvFile, delimiter=',')
                for row in csv_reader:
                    if len(row) >= 4:
                        if row[3].strip() in self.pd_test_results:
                            if row[1].strip() in proj_moi_data:
                                if run_name not in proj_moi_data[row[1].strip()].run_data:
                                    for test_id in self.pd_test_list[moi_name].keys():

                                        proj_moi_data[test_id].run_data[run_name] = TestRunData()
                                        proj_moi_data[test_id].run_data[run_name].rep_data[rep_name] = "NS"

                                test_id = row[1].strip()
                                if rep_name not in proj_moi_data[test_id].run_data[run_name].rep_data:
                                    for test_id in self.pd_test_list[moi_name].keys():
                                        proj_moi_data[test_id].run_data[run_name].rep_data[rep_name] = "NS"
                                proj_moi_test_id = proj_moi_data[row[1].strip()]
                                proj_moi_test_id.run_data[run_name].rep_data[rep_name] = row[3].strip()
                                proj_moi_test_id.test_name = row[2].strip()

                                if len(proj_moi_data[test_id].run_data[run_name].rep_data) == 1:
                                    proj_moi_test_id.run_data[run_name].test_last_run_result = row[3].strip()
                                else:
                                    if row[3].strip() != "NOT_EXECUTED":
                                        proj_moi_test_id.run_data[run_name].test_last_run_result = row[3].strip()
                                    else:
                                        if proj_moi_test_id.run_data[run_name].test_last_run_result == "NS":
                                            proj_moi_test_id.run_data[run_name].test_last_run_result = row[3].strip()
            self.moi_result_data[proj_name][moi_name] = proj_moi_data
        except Exception as e:
            print("ParsingFiles_ _ read_test_details :-" + str(e)+proj_name+ moi_name+ run_name+ rep_name)
