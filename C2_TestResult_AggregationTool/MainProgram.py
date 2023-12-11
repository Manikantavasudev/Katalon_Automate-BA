import os
from CompareResults import CompareResults
from JsonDataLoad import JsonDataLoad
from LoadCsvFiles import LoadCsvFiles
from ParsingFiles import ParsingFiles
from WriteExcel import WriteExcel
import os, glob
import shutil
import subprocess

if __name__ == '__main__':
    obj_json_data = JsonDataLoad()
    if os.path.exists(obj_json_data.test_proj_location):
        obj_load_test_csv_files = LoadCsvFiles(obj_json_data)
        test_vif_all_data = obj_load_test_csv_files.FindCsvFiles(obj_json_data.test_proj_location)
        obj_load_golden_csv_files = LoadCsvFiles(obj_json_data)
        golden_vif_all_data = obj_load_test_csv_files.FindCsvFiles(obj_json_data.golden_result_location)
        golden_result_data = None
        if bool(golden_vif_all_data):
            obj_golden_parsing_files = ParsingFiles(obj_json_data.pd_test_results, obj_json_data.pd_test_cases_dict)
            golden_result_data = obj_golden_parsing_files.process_files(golden_vif_all_data)
        else:
            print(" This path does not have a valid golden report .csv file")
        if bool(test_vif_all_data):
            obj_test_parsing_files = ParsingFiles(obj_json_data.pd_test_results, obj_json_data.pd_test_cases_dict)
            test_result_data = obj_test_parsing_files.process_files(test_vif_all_data)
            if bool(test_result_data and golden_result_data):
                obj_compare_test_result_data = CompareResults().Compare(obj_json_data, test_result_data,
                                                                        test_vif_all_data, golden_result_data,
                                                                        golden_vif_all_data)
            if bool(test_result_data):
                obj_write_excel = WriteExcel(obj_json_data, obj_compare_test_result_data, test_vif_all_data)
                obj_write_excel.data_write()                
                #To find latest Executed csv file summary report folder path
                output_file_location = obj_json_data.output_file_path
                csv_comparison_report = max(glob.glob(os.path.join(output_file_location, '*')), key=os.path.getmtime)
                if "Report_" in csv_comparison_report :
                    #To call XML comparison Tool and get output xmlComparison report
                    subprocess.call(["C2XMLComparisonTool-main\\C2Testing\\bin\\Debug\\net6.0\\C2Testing.exe"])
                    #To move xml report to csv file summary report folder
                    src =  output_file_location +'\\'+'XMLReport.csv'
                    dst =  csv_comparison_report
                    shutil.move(src, dst, copy_function = shutil.copytree)
            else:
                print(" Empty file:-Unable to find Test run data in .csv files")
        else:
            print(" This path does not have a valid .csv file")
    else:
        print("The path is not found : " + obj_json_data.test_proj_location)
        print(" Please enter valid path in Config.json")

