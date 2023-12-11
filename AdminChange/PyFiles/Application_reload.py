import os
import time
from urllib.request import urlopen
import urllib.request
import logging

app_url = "http://localhost:5001/"
installer_glob_path = "C:\\Program Files (x86)\\GRL-C2_Browser_App"
installer_actual_path = "C:\\GRL\\USBPD-C2-Browser-App"
short_delay = 8
long_delay = 60
expected_value = 0
logging.basicConfig(level=logging.INFO)


class Installer_Prerequisites:

    def __init__(self):

        self.app_sts = None
        self.expected_app_sts = 200
        self.installer_path = None

    def check_c2_application_status(self):
        """
        The Function checks whether the application is opened or closed.
        If open, proceed with the if statement. If it's close throw an exception.
        """
        try:
            self.app_sts = urllib.request.urlopen(app_url).getcode()
            logging.info('Way to close and restart the application')
        except Exception as e:
            logging.error('Failed to open the application')

        if self.app_sts == self.expected_app_sts:
            self.close_c2_server_application()
            time.sleep(5)
            self.open_c2_server_application()

    def close_c2_server_application(self):
        """
        Close the c2 server application
        """
        server_close = os.system("taskkill /f /im  C2BrowserApp.exe")
        assert server_close is expected_value, "Value is Miss-Match can't able to close the Application"

    def open_c2_server_application(self):
        """
        Open the c2 server application
        """
        os.chdir("C:\\Program Files (x86)\\GRL-C2_Browser_App")
        server_open = os.system("start cmd /c C2BrowserApp.exe")
        assert server_open is expected_value, "Value is Miss-Match can't able to open the Application"


def main():
    inst_obj = Installer_Prerequisites()
    inst_obj.check_c2_application_status()


if __name__ == "__main__":
    main()
