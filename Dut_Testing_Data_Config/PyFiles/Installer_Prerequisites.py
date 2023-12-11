
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

installer_glob_path = "C:\\Program Files (x86)\\GRL-C2_Browser_App"
installer_actual_path = "C:\\GRL\\USBPD-C2-Browser-App"
short_delay = 8
long_delay = 120

class Installer_Prerequisites:

    def __init__(self):
       
        self.app_sts = None
        self.expected_app_sts = True
        self.expected_app_exe_list = None
        self.expected_app_exe_length = None
        self.expected_app_act_list = None
        self.expected_app_act_length = None
        self.installer_path=None
        self.install_cmd = None

        
    def check_c2_application_status(self):
        #Iterates through all the programs running in system and checks for the one in the string  
        self.app_sts = "C2BrowserApp.exe" in (p.name() for p in psutil.process_iter())      
               
        #If status code is True existing c2 server will close after that silent installation will begin
        if self.app_sts == self.expected_app_sts:
            self.close_c2_server_application()
            time.sleep(short_delay)
            self.silent_mode_application_installation()
        #Status code is not equal to 200 ,silent installation will start immediately    
        else:
            self.silent_mode_application_installation()


    def close_c2_server_application(self):
        #To close C2 server application
        os.system("taskkill /f /im  C2BrowserApp.exe")


    def open_c2_server_application(self):
        #To open C2 server application
        os.chdir("C:\\Program Files (x86)\\GRL-C2_Browser_App")
        os.system("start cmd /c C2BrowserApp.exe")
       

    def silent_mode_application_installation(self):
        #To install c2 Application in silent mode
        #os.system('start  cmd /c {} /S'.format(self.installer_path))
        os.system('{} /S'.format(self.installer_path))
        time.sleep(long_delay)

    def verify_application_content(self):
        """To get all the file content in the following file path 
        -> C:\\Program Files (x86)\\GRL-C2_Browser_App
        -> C:\\GRL\\USBPD-C2-Browser-App  """
               
        ins_exe_files = os.listdir(installer_glob_path)
        ins_actual_files = os.listdir(installer_actual_path)
        exe_files_list=[]
        act_files_list =[]
        for exe_content in ins_exe_files:	
            exe_files_list.append(exe_content)
        for act_content in ins_actual_files:	
            act_files_list.append(act_content)   
        self.exe_files_length = len(exe_files_list)  
        self.act_files_length = len(act_files_list) 

        #To compare list of files in installer location with json data
        if  self.exe_files_length ==  self.expected_app_exe_length :
            if set(exe_files_list) == set(self.expected_app_exe_list):
                print("Expected File contents are matching with actual content") 
            else: 
                print("Expected File contents are not matching with actual content")   
        else:
            print("Expected File contents are missing")  

        if  self.act_files_length ==  self.expected_app_act_length :
            if set(act_files_list) == set(self.expected_app_act_list):
                print("Expected File contents are matching with actual content") 
            else:
                print("Expected File contents are not matching with actual content")                  
        else:
            print("Expected File contents are missing")  

    def get_json_data(self,gen_config_json_path):
        print(gen_config_json_path)
        #To read json data which contains existing installer files and counts   
        abs_path = os.path.abspath(os.path.dirname(__file__)) 
        #self.rp_path = os.path.join(abs_path, "general_config.json")
        with open(gen_config_json_path, "r") as rf:
            self.prop = json.load(rf)
            self.installer_path = self.prop["installer_download_path"]
            self.expected_app_exe_list = self.prop["application_executable_content"]  
            self.expected_app_exe_length =len(self.expected_app_exe_list) 
            self.expected_app_act_list = self.prop["application_actual_content"] 
            self.expected_app_act_length =len(self.expected_app_act_list)          

               
def main():  
    general_config_json_path = sys.argv[1] 
    inst_obj = Installer_Prerequisites()
    inst_obj.get_json_data(general_config_json_path)
    inst_obj.check_c2_application_status()
    #inst_obj.verify_application_content()
    inst_obj.open_c2_server_application()

if __name__ == "__main__":
    main()
   
   