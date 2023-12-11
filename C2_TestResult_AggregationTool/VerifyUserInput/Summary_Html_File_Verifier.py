import os, sys
import json
import io
import enum
import time
from time import sleep
import operator
import datetime,shutil 
import glob
import psutil  
import pandas as pd
from Update_Summary_Xls_File import Update_Summary_Xls_Report

temp_report_folder_path = "C:\\GRL\\USBPD-C2-Browser-App\\Report\\TempReport"
report_result_path="C:\\GRL\\C2_Comparison_Tool_Report"

class Summary_Html_Verifier(Update_Summary_Xls_Report):

    def __init__(self,proj_name,five_port_folder_with_timestamp,katalon_installation_path):
        
        self.user_config_json_input= None      
        self.project_name = proj_name
        self.five_port_fol = five_port_folder_with_timestamp
        self.katalon_installation_path = katalon_installation_path
        self.html_summary_filepath = None
        #In html file second table contain the Executed Moi's list
        self.second_table_data = dict()
        self.execute_moi_count = None
        #To get total valid table count (including Overall,All MOI Summary,Result Summary)
        self.valid_table_count = None
        #all valid table data list
        self.all_valid_table_data=list()
        #moi and testcase list
        self.all_valid_moi_data= list()
        self.all_valid_testcase_data = list()
        #conclusion summary 
        self.conclusion_summary=None
        self.user_data_pass_count = list()
        self.user_data_fail_count  = list()


    def get_summary_html_report(self):
        #To find latest html summary report in following path ->C:\\GRL\\USBPD-C2-Browser-App\\Report\\TempReport
        self.latest_report = max(glob.glob(os.path.join(temp_report_folder_path, '*')), key=os.path.getmtime)
        self.updated_run_report = max(glob.glob(os.path.join(self.latest_report, '*')), key=os.path.getmtime)
        print(self.updated_run_report)
        #To find html summary report inside latest Report
        list_of_files = list()
        for (dirpath, dirnames, filenames) in os.walk(self.updated_run_report):
            list_of_files += [os.path.join(dirpath, file) for file in filenames]   
        print(list_of_files)    
        #get html output file with name of Summary
        for each_file in list_of_files:
            if '.html' in each_file:                  
                if 'Summary_Run' in each_file:
                    #avoid html summary file with '.~lock.' extension
                    if '.~lock.' not in each_file:
                        self.html_summary_filepath = each_file
                        print("output summary html filename ::  "+self.html_summary_filepath) 
                        
 
 

    def get_executed_moi_count_from_html(self):               
        #Executed Moi's list will present in Second table
        #read table reader as data frame and convert data frame into dictionary and convert dict values as list 
        self.table_reader = pd.read_html(self.html_summary_filepath)
        self.second_table_data = self.table_reader[1].to_dict('list')
        #to find length of total moi ,the reason for finding the moi length is elimating not executed moi table 
        for key,val in self.second_table_data.items() :
            if key == 'MOI Name':
                self.execute_moi_count = len(val)
                #To get total valid table count (including Overall,All MOI Summary,Result Summary)
                self.valid_table_count = self.execute_moi_count+2
                print(self.valid_table_count)
                


    def get_executed_moi_and_testcase_list(self):
        #To get all valid table data as list from html file
        self.table_reader = pd.read_html(self.html_summary_filepath)
        for each_table_id in range(self.valid_table_count):
                each_table_data = self.table_reader[each_table_id].to_dict('list')
                self.all_valid_table_data.append(each_table_data)
        #To get all valid MOI count and Testcase count
        for each_table_dict in self.all_valid_table_data:
            print(each_table_dict)
            for key,val in each_table_dict.items() :
                #if dict key contain moi name then add all dict value to valid moi list
                if key == 'MOI Name':
                    for each_moi in val:
                        self.all_valid_moi_data.append(each_moi)
                #if dict key contain test name then add all dict value to valid testcase list        
                if key == 'Test Name':  
                    for each_testcase in val:  
                        self.all_valid_testcase_data.append(each_testcase)


    def compare_userconfigdata_with_summarydata(self):
        #To create dictionary which contains comparison result
        conclusion_sum_dict = dict()

        """To compare user provided Moi and testcase list with Executed Moi and testcase in summary file"""
        for each_user_ip in self.user_config_json_input:
            #check user input is there or not in valid moi list and testcase list
            if each_user_ip in self.all_valid_moi_data:
                conclusion_sum_dict[each_user_ip]=True
            elif each_user_ip in self.all_valid_testcase_data: 
                conclusion_sum_dict[each_user_ip]=True  
            #user input is not there summary result will set as False in conclusion dictionary    
            else:
                conclusion_sum_dict[each_user_ip]=False
        print(conclusion_sum_dict)     
        #To make True(pass),False(Fail) count based on conclusion summary dictionary result
        for key,val in conclusion_sum_dict.items():
            if val == True :
                self.user_data_pass_count.append(key)
            else:
                self.user_data_fail_count.append(key)
        

    def set_conclusion_summary(self):
        filename = '{}{}'.format(self.project_name,'.txt')
        if len(self.user_data_fail_count) == 0:
            self.conclusion_summary = "Selected Moi's and Related Testcases are Executed Successfully"
            print(self.conclusion_summary)
            port_file_name_abs_path = self.katalon_installation_path+'\\'+'Five_Port_Data_Config\\PyFiles\\temp_port'+'\\'+filename             
            if os.path.exists(port_file_name_abs_path):
                os.remove(port_file_name_abs_path)    
            with open(port_file_name_abs_path, 'w') as f:
                f.write('port Executed successfully')
        else:
            self.conclusion_summary = "Selected Moi's and Related Testcases are not Executed Properly, Here is the Missing list  {}".format(self.user_data_fail_count)   
            print(self.conclusion_summary)       
            port_file_name_abs_path = self.katalon_installation_path+'\\'+'Five_Port_Data_Config\\PyFiles\\temp_port'+'\\'+filename  
            
            if os.path.exists(port_file_name_abs_path):
                os.remove(port_file_name_abs_path)    
            with open(port_file_name_abs_path, 'w') as f:
                f.write('port not Executed successfully')                
        return self.conclusion_summary    
     
    
    def read_moi_config_json(self,user_config_input_json_path):
        with open(user_config_input_json_path, "r") as rf:
            self.prop = json.load(rf)
            self.user_config_json_input = self.prop["MOI_List"]
           

               
def main():  
    
    #user selected Moi and Testcase list &Project name
    user_config_input_json_path = sys.argv[1]    
    user_config_project_name = sys.argv[2] 
    five_port_folder_with_timestamp = sys.argv[3]
    katalon_installation_path = sys.argv[4]
    #To create object for class
    print("1.Start Summary _html. ")
    html_obj = Summary_Html_Verifier(user_config_project_name,five_port_folder_with_timestamp,katalon_installation_path)
    print("2.Start Summary _html. ")
    html_obj.read_moi_config_json(user_config_input_json_path)
    print("3.Start Summary _html. ")
    html_obj.get_summary_html_report()
    print("4.Start Summary _html. ")
    html_obj.get_executed_moi_count_from_html()
    print("5.Start Summary _html. ")
    html_obj.get_executed_moi_and_testcase_list()
    print("6.Start Summary _html. ")
    html_obj.compare_userconfigdata_with_summarydata()
    print("start set_conclusion_summary")
    summary_conclusion = html_obj.set_conclusion_summary()
    print("end set_conclusion_summary")
    #To call child class and update xls file along with conclusion summary
    html_obj.write_conclusion_summary_data(summary_conclusion,user_config_project_name)

    

    
  

if __name__ == "__main__":
    main()






























                 
               
   
        
    
