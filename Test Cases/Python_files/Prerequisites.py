
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

app_url = "http://localhost:5001/"
installer_glob_path = "C:\\Program Files (x86)\\GRL-C2_Browser_App"
installer_actual_path = "C:\\GRL\\USBPD-C2-Browser-App"
short_delay = 8
long_delay = 60

class Installer_Prerequisites:

    def __init__(self):
       
        self.app_sts = None
        self.expected_app_sts = 200
        self.installer_path=None
       

        
    def check_c2_application_status(self):
        #To get Application status code if application is not open script will go to except block
        try:
            self.app_sts = urllib.request.urlopen(app_url).getcode()           
        except:
            self.app_sts = 500        
        #If status code is equal to  200 then  existing c2 server will close after that silent installation will begin
        if self.app_sts != self.expected_app_sts:
            self.open_c2_server_application()
            sleep(long_delay)


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
   
   