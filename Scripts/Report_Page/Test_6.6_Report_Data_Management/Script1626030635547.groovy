import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.By as By
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import groovy.time.TimeCategory as TimeCategory
import java.nio.file.*
import java.io.IOException as IOException
import java.io.File as File
import java.util.HashMap as HashMap
import java.util.Map as Map
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions as ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities as DesiredCapabilities
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import groovy.transform.CompileStatic as CompileStatic
import java.awt.Toolkit as Toolkit
import java.awt.datatransfer.StringSelection as StringSelection
import java.awt.event.KeyEvent as KeyEvent
import java.awt.Robot as Robot

CustomKeywords.'custom.Logger.CustomLoggerFactory.customizeLogger4CommentKeyword'()

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

WebUI.delay(GlobalVariable.long_delay)

'To initialize driver object'
WebDriver driver = DriverFactory.getWebDriver()

'To read vif file data '
def all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String pc_xml_file_folder_path = all_data.user_config_data.PC_XML_File_Folder_Path

String downloads_folder_path = all_data.user_config_data.Downloads_Folder_Path

String pc_c2_project_name = all_data.user_config_data.PC_C2_Project_Name

'To read Report data'
def report_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Report_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

List exp_report_data_header_list = report_data.report_page_data.Report_Data_Management_Header

String report_vif_file_path = report_data.report_page_data.Report_Data_Management_Vif_File_Path

List testcases_name = report_data.report_page_data.Report_Data_Testcases_Name

'Expected Test results folder size'
exp_test_results_folder_txt = exp_test_results_folder_size

String temp_report_folder_path = 'C:\\GRL\\USBPD-C2-Browser-App\\Report\\TempReport'

'delete existing grl_report data in downloads folder'
String home_path = System.getProperty('user.home')

def zip_file_path_sts = new File(((home_path + '/Downloads/') + 'GRL_Report') + '.zip')

    boolean zip_file_exists_sts = zip_file_path_sts.exists()

    if (zip_file_exists_sts) {
		
        boolean filedeleted = zip_file_path_sts.delete()

        WebUI.comment('Existing GRL Report file Succesfully deleted without any issues::' + filedeleted)
    }

WebUI.comment('*****************************************************************************************************************************************************' + 
    '\n')

WebUI.comment(('TestcaseName:: ' + 'Test_6.6_Report_Data_Management') + '\n')

'To click Report Panel'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Report'))

'STEP 1:To click Report Data Management button'
WebUI.click(findTestObject('Report_Page_Updated_Objects/Report_Data_Management/button_Report_Data_Management'))

WebUI.delay(GlobalVariable.long_delay)

'STEP 2: To Verify that Report Data Window appears'

//WebUI.verifyElementVisible(findTestObject('Object Repository/Report_Page_Updated_Objects/Report_Data_Management/th_Run No'))
'STEP 3:To Verify table with following headers  (A.Run Number ,B.Folder Name,C.Size,D.Delete,E.Download )'
List report_table_header_list = []

List report_table_header = driver.findElements(By.xpath('//div[@class="resultsmanager-modalcontainer"]/table/tbody/tr/th'))

'To locate table header list size'
int report_table_header_size = report_table_header.size()

for (int j = 1; j <= report_table_header_size; j++) {
    WebUI.delay(GlobalVariable.short_delay)

    WebElement table_header_webele = driver.findElement(By.xpath(('//div[@class="resultsmanager-modalcontainer"]/table/tbody/tr/th[' + 
            j) + ']'))

    def table_header_val = table_header_webele.getText()

    report_table_header_list.add(table_header_val)
}

WebUI.comment(('Report_data_Header_List' + report_table_header_list) + '\n')

assert exp_report_data_header_list == report_table_header_list

'STEP 4&5: Select All data and click Delete Test Report'
WebUI.click(findTestObject('Object Repository/Report_Page_Updated_Objects/Report_Data_Management/button_Delete_Test_Report'))

'STEP 6:Verify that all the data in the table gets cleared'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Report_Page_Updated_Objects/Report_Data_Management/div_second_table_row_data'), 
    10)

WebUI.delay(GlobalVariable.short_delay)

'STEP 7:To Verify that Test Folder Size show’s 0.00MB’s'
def test_result_folder_txt_sts = WebUI.getText(findTestObject('Object Repository/Report_Page_Updated_Objects/Report_Data_Management/small_Test_Results_Folder_Size'))

assert exp_test_results_folder_txt == test_result_folder_txt_sts

WebUI.delay(GlobalVariable.short_delay)

WebUI.click(findTestObject('Object Repository/Report_Page_Updated_Objects/Report_Data_Management/button_close'))

'STEP 8:To Run any one relevant test case'
WebUI.delay(GlobalVariable.short_delay)

'To click Product Capability page'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

'To combine user config vif file path'
Path vif_file_path = Paths.get((pc_xml_file_folder_path + '\\') + report_vif_file_path)

'To click Load xml vif file button & verify windows file open dialogue and select xml file '
WebUI.callTestCase(findTestCase('Load_Xml_File_RobotFramework'), [('key1') : vif_file_path], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(GlobalVariable.short_delay)

'To click Testconfig page'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Test Config'))

WebUI.delay(GlobalVariable.short_delay)

'To click All Supported Certification dropdown'
CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectCertificationDropdown'(findTestObject('Object Repository/Test_Config_Panel/Certification_drop_down'), 
    findTestObject('Object Repository/Test_Config_Panel/Page_GRL - USB PD C2/a_All Supported Certifications'))

WebUI.delay(GlobalVariable.short_delay)

CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectListOfTestCase'(testcases_name)

'Click Start Execution Button'
WebUI.click(findTestObject('Object Repository/Test_Config_Panel/button_Start Execution'))

'Verify that Results Panel gets selected & Wait for Execution Competed'
def run_status

try {
    run_status = true

    while (run_status) {
        'To check Unlive button status'
        def sts = driver.findElement(By.xpath('//button/img[@src=\'../../images/chartIcons/PNG/Live.png\']'))

        if (sts) {
            WebUI.delay(GlobalVariable.long_delay)

            run_status = true
        }
    }
}
catch (Exception e) {
    run_status = false
} 

WebUI.delay(GlobalVariable.short_delay)

'STEP 9&10 verify  data stored in the TempReport Folder'

'The directory contains report flder'
File directory = new File(temp_report_folder_path)

File[] contents = directory.listFiles()

if (contents.length > 0) {
    WebUI.comment(('File content' + contents) + '\n')
}

WebUI.delay(GlobalVariable.short_delay)

'STEP 11:To click Report Panel'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Report'))

'STEP 12:To click Report Data Management'
WebUI.click(findTestObject('Report_Page_Updated_Objects/Report_Data_Management/button_Report_Data_Management'))

'STEP 13:To Verify that there is one entry in the table'
List report_data_table_list = driver.findElements(By.xpath('//div[@class="resultsmanager-modalcontainer"]/table/tbody/tr'))

def expected_report_size = 2

'To find report data size'
int report_data_size = report_data_table_list.size()

assert expected_report_size == report_data_size

WebUI.delay(GlobalVariable.short_delay)

'STEP 14:Select Delete Button report button'
WebUI.click(findTestObject('Object Repository/Report_Page_Updated_Objects/Report_Data_Management/button_Delete_Button'))

WebUI.delay(GlobalVariable.short_delay)

WebUI.click(findTestObject('Object Repository/Report_Page_Updated_Objects/Report_Data_Management/button_close'))

'STEP 15&16: Verify that there is no data in the TempReport Folder '
File[] contents_updated = directory.listFiles()

if ((contents_updated.length == null) || (contents_updated.length == 0)) {
    WebUI.comment(('File content' + contents_updated) + '\n')
} else {
    WebUI.comment(('File content' + contents_updated) + '\n')

    assert false
}

'reun the testcase'
WebUI.delay(GlobalVariable.short_delay)

WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

WebUI.clearText(findTestObject('Object Repository/Product_Capability_Objects/input_Project Name'))

WebUI.setText(findTestObject('Product_Capability_Objects/input_Project Name'), pc_c2_project_name)

WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/button_Save'))

WebUI.delay(GlobalVariable.short_delay)

CustomKeywords.'grl_usb_pd_c2_Testcase.Report_Panel.liveButtonStatusReading'()

WebUI.delay(GlobalVariable.long_delay)

'STEP 17 :To click Product Capability page'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

'STEP 18:Change project name to a different name'
String recent_project_name = 'C2_Katalon_Automation_1'

WebUI.clearText(findTestObject('Object Repository/Product_Capability_Objects/input_Project Name'))

WebUI.setText(findTestObject('Product_Capability_Objects/input_Project Name'), recent_project_name)

WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/button_Save'))

'STEP 19:Rerun the testcases'
CustomKeywords.'grl_usb_pd_c2_Testcase.Report_Panel.liveButtonStatusReading'()

WebUI.delay(GlobalVariable.short_delay)

'STEP 20:To click Report panel'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Report'))

WebUI.delay(GlobalVariable.short_delay)

'STEP 21:To select Report Management Data'
WebUI.click(findTestObject('Report_Page_Updated_Objects/Report_Data_Management/button_Report_Data_Management'))

'STEP 22:To Verify that there is two entry in the table'
List updated_report_data_table_list = driver.findElements(By.xpath('//div[@class="resultsmanager-modalcontainer"]/table/tbody/tr'))

def expected_updated_report_size = 3

'STEP 23&24&25&26 :One with previous project name and Second with Current project name& download report button&To verify downloaded folder in default download path'

'To verify downloaded folder in default download path'
driver.findElement(By.xpath('(//button[@id="reportManagementDownloadReportBtn"])[1]')).click()

WebUI.delay(GlobalVariable.short_delay)

'delete existing grl report data in downloads folder'

boolean downloaded_zip_file_sts = zip_file_path_sts.exists()

	if (downloaded_zip_file_sts) {
		
		boolean downloaded_filedeleted = zip_file_path_sts.delete()

		WebUI.comment('Existing GRL Report file Succesfully deleted without any issues::' + downloaded_filedeleted)
	}


'To verify downloaded folder in default download path'
driver.findElement(By.xpath('(//button[@id="reportManagementDownloadReportBtn"])[2]')).click()

WebUI.delay(GlobalVariable.short_delay)

'To find report data size'
int updated_report_data_size = updated_report_data_table_list.size()

assert expected_updated_report_size == updated_report_data_size

List updated_report_folder_header = driver.findElements(By.xpath('div[@class="resultsmanager-modalcontainer"]/table/tbody/tr/td[2]'))

'To locate folder name size'
int updated_folder_name_size = updated_report_folder_header.size()

for (int i = 1; i <= updated_folder_name_size; i++) {
    WebElement updated_folder_name_webele = driver.findElement(By.xpath(('(//div[@class="resultsmanager-modalcontainer"]/table/tbody/tr[2])[' + 
            i) + ']'))

    def updated_folder_name_val = updated_folder_name_webele.getText()

    if (updated_folder_name_val.contains(pc_c2_project_name)) {
        WebUI.comment(('Report folder name' + updated_folder_name_val) + '\n')

        WebUI.delay(GlobalVariable.short_delay)
    } else if (updated_folder_name_val.contains(recent_project_name)) {
        WebUI.comment(('Report folder name' + updated_folder_name_val) + '\n')

        WebUI.delay(GlobalVariable.short_delay)
    } else {
        WebUI.comment(('Report folder name' + updated_folder_name_val) + '\n')

        assert false
    }
}

'STEP 27:Select Delete Test Report'

WebUI.delay(GlobalVariable.short_delay)

WebUI.click(findTestObject('Object Repository/Report_Page_Updated_Objects/Report_Data_Management/button_Delete_Test_Report'))

'STEP 28:Verify that all the data in the table gets cleared'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Report_Page_Updated_Objects/Report_Data_Management/div_second_table_row_data'), 
    10)

WebUI.delay(GlobalVariable.short_delay)

'To close report data table'
WebUI.click(findTestObject('Object Repository/Report_Page_Updated_Objects/Report_Data_Management/button_close'))

'STEP 29: Verify that there is no data in the TempReport Folder '
File[] file_contents_updated = directory.listFiles()

if ((file_contents_updated.length == null) || (file_contents_updated.length == 0)) {
    WebUI.comment(('File content' + file_contents_updated) + '\n')
} else {
    WebUI.comment(('File content' + file_contents_updated) + '\n')

    println(file_contents_updated.length)

}

WebUI.comment('********* Test_6.6_Report_Data_Management  Execution Ended *********' + '\n')

WebUI.comment('*****************************************************************************************************************************************************' + 
    '\n')

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()



