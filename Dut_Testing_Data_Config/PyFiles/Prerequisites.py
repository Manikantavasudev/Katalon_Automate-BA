
import os, sys
import json
import io
import enum
import time
from time import sleep
import operator
import datetime,shutil 
import glob
from urllib.request import urlopen
from urllib.error import *
import urllib.request
import psutil

installer_glob_path = "C:\\Program Files (x86)\\GRL-C2_Browser_App"
installer_actual_path = "C:\\GRL\\USBPD-C2-Browser-App"
short_delay = 8
long_delay = 40

class Installer_Prerequisites:

    def __init__(self):
       
        self.app_sts = None
        self.expected_app_sts = True
        self.installer_path=None
       
    def check_c2_application_status(self):
        #To get Application status code 
        self.app_sts = "C2BrowserApp.exe" in (p.name() for p in psutil.process_iter())       
        #If status code is equal to False then  c2 server open
        if self.app_sts != self.expected_app_sts:
            self.open_c2_server_application()
            time.sleep(long_delay)
        else :
            self.close_c2_server_application()
            time.sleep(short_delay)
            self.open_c2_server_application()
            time.sleep(long_delay)

 
    def close_c2_server_application(self):
        #To close C2 server application
        os.system("taskkill /f /im  C2BrowserApp.exe")


    def open_c2_server_application(self):
        #To open C2 server application
        os.chdir("C:\\Program Files (x86)\\GRL-C2_Browser_App")
        os.system("start cmd /c C2BrowserApp.exe")
       

    
def main():   
    inst_obj = Installer_Prerequisites()
    inst_obj.check_c2_application_status()
 

if __name__ == "__main__":
    main()
   
   