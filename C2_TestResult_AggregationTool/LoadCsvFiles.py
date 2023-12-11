import csv
import glob
import os
from collections import OrderedDict

from TestProjectData import TestProjectData


class LoadCsvFiles:
    def __init__(self, obj_json_data):

        try:
            self.moi_run_rep = OrderedDict()
            self.obj_json_data = obj_json_data

        except Exception as e:
            print("LoadCsvFiles _ __init__ :-" + str(e))

    def FindCsvFiles(self, proj_location):
        try:
            obj_test_proj_data = OrderedDict()
            all_file_paths = glob.glob(os.path.join(proj_location, "**/*.csv"), recursive=True)
            for file_path in all_file_paths:
                folder_name_list = (os.path.split(os.path.abspath(file_path))[0]).split('\\')
                folder_name_list_count = len(folder_name_list)
                folder_name = folder_name_list[folder_name_list_count - 1]
                file_name = os.path.splitext(os.path.basename(file_path))[0]
                ext_name = os.path.splitext(os.path.basename(file_path))[1]
                run_no = "run_1"
                rep_no = "rep_0"
                if file_name in self.obj_json_data.pd_appmode_dict.keys():
                    if folder_name == self.obj_json_data.pd_appmode_dict[file_name] and ext_name == ".csv":
                        list_run_folder_name = str(folder_name_list[folder_name_list_count - 2]).split('_')
                        project_name = folder_name_list[folder_name_list_count - 3]
                        if project_name not in obj_test_proj_data:
                            obj_test_proj_data[project_name] = TestProjectData()
                        len_list_run_folder = len(list_run_folder_name)
                        if len_list_run_folder > 7:
                            if list_run_folder_name[len_list_run_folder - 7].lower().__contains__('run'):
                                run_no = "run_" + str((list_run_folder_name[1].lower()).split("run")[1])
                        if len_list_run_folder > 6:
                            if list_run_folder_name[len_list_run_folder - 6].lower().__contains__("rep"):
                                rep_no = "rep_" + str(
                                    str((list_run_folder_name[2].lower()).split("rep")[1]).split(" ")[0])
                        if file_name not in obj_test_proj_data[project_name].csv_files_path:
                            obj_test_proj_data[project_name].csv_files_path[file_name] = OrderedDict()
                            obj_test_proj_data[project_name].moi_run_rep[file_name] = OrderedDict()
                        if run_no not in obj_test_proj_data[project_name].csv_files_path[file_name]:
                            obj_test_proj_data[project_name].csv_files_path[file_name][run_no] = OrderedDict()
                            obj_test_proj_data[project_name].moi_run_rep[file_name][run_no] = []
                        if run_no not in obj_test_proj_data[project_name].obj_vif_data:
                            obj_test_proj_data[project_name].obj_vif_data[run_no] = OrderedDict()

                            vif_csv_file_folder = os.path.dirname(os.path.dirname(file_path))
                            print("Vif_File_Folder : " + vif_csv_file_folder)
                            vif_csv_file = [x for x in os.listdir(vif_csv_file_folder) if
                                            x.endswith(".csv") and x.startswith("GRL_USB_PD_Report")]
                            if len(vif_csv_file):
                                obj_test_proj_data[project_name].obj_vif_data[run_no] = self.load_vif_data(
                                    os.path.join(vif_csv_file_folder, vif_csv_file[0]), self.obj_json_data,
                                    folder_name_list,
                                    project_name)
                            else:
                                obj_test_proj_data[project_name].obj_vif_data[run_no] = self.obj_json_data.vif_dict
                        obj_test_proj_data[project_name].csv_files_path[file_name][run_no][rep_no] = file_path
                        obj_test_proj_data[project_name].moi_run_rep[file_name][run_no].append(rep_no)
                        print(project_name, run_no.upper() + ":-" + file_path)
            return obj_test_proj_data
        except Exception as e:
            print("LoadCsvFiles _ FindCsvFiles :-" + str(e))

    @staticmethod
    def load_vif_data(vif_file_name, obj_json_data, folder_name_list, project_name):
        try:
            obj_vif_values = OrderedDict()
            with open(vif_file_name) as csvFile:
                csv_reader = csv.reader(csvFile, delimiter=',')
                for row in csv_reader:
                    if len(row):
                        if row[0].strip() == "SI No" or row[0].strip() == "Test_Result_Summary":
                            break
                    if len(row) >= 1:
                        row_data = row[0].replace(":", "").replace(".", "").strip()
                        if row_data in obj_json_data.pd_vif_dict.keys():
                            obj_vif_values[row_data] = row[1]
            obj_vif_dict = OrderedDict()
            for field in obj_json_data.pd_vif_dict.keys():
                if field in obj_vif_values.keys():
                    obj_vif_dict[obj_json_data.pd_vif_dict[field]] = obj_vif_values[field]
                else:
                    obj_vif_dict[obj_json_data.pd_vif_dict[field]] = field
            obj_vif_dict["Folder Abs Path"] = ""
            for i in range(len(folder_name_list) - 2):
                obj_vif_dict["Folder Abs Path"] = os.path.join(obj_vif_dict["Folder Abs Path"], folder_name_list[i])
            obj_vif_dict["Folder Name"] = project_name
            obj_vif_dict["VIF Spec"] += "   ."
            return obj_vif_dict
        except Exception as e:
            print("LoadCsvFiles _ load_vif_data :-" + str(e))
