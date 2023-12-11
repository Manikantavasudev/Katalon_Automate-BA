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
import java.awt.Desktop as Desktop
import java.awt.Robot as Robot
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper as MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import java.time.*
import java.text.SimpleDateFormat as SimpleDateFormat

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

WebUI.delay(GlobalVariable.long_delay)

'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String python_path = all_data.user_config_data.Python_exe_path

String katalon_path = all_data.user_config_data.Katalon_installation_path

String pc_xml_file_folder = all_data.user_config_data.PC_XML_File_Folder_Path

'To read Report data'
Map report_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Report_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

def report_vif_file_path = report_data.report_page_data.Report_Vif_File_Path

List testcases_name = report_data.report_page_data.Testcases_Name

def vif_file_path = Paths.get((pc_xml_file_folder + '\\') + report_vif_file_path)

'To initialize driver object'
WebDriver driver = DriverFactory.getWebDriver()

'To find downloads folder directory and  vifdata xml file'
String home_path = System.getProperty('user.home')

def downloads_folder_path = new File(home_path + '/Downloads')

'Common data Variable'

String project_name  = Project_Name

def edited_report_name

def report_name

WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Product Capability'))

WebUI.setText(findTestObject('Report_panel_objects/Download_Current_DUT_Report/input_Project_Name'), project_name)

WebUI.click(findTestObject('Object Repository/Report_panel_objects/Download_Current_DUT_Report/button_Save'))

WebUI.delay(GlobalVariable.short_delay)

WebUI.callTestCase(findTestCase('Load_Xml_File_RobotFramework'), [('key1') : vif_file_path], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(GlobalVariable.long_delay)

'To Click Test config panel'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Test Config'))

'To select and Execute Testcases '
CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectCertificationDropdown'(findTestObject('Object Repository/Test_Config_Panel/Certification_drop_down'),
	findTestObject('Object Repository/Test_Config_Panel/Page_GRL - USB PD C2/a_All Supported Certifications'))

WebUI.delay(GlobalVariable.short_delay)

'To verify checkbox unselected state'
def expand_testlist_checkbox_sts

try {
	'To check expand list checkbox status'
	expand_testlist_checkbox_sts = WebUI.verifyElementNotChecked(findTestObject('Object Repository/Test_Config_Panel/Expand_Test_List'),
		5)

	if (expand_testlist_checkbox_sts) {
		WebUI.comment(('expand_testlist_checkbox_sts::' + expand_testlist_checkbox_sts) + '\n')
	}
}
catch (Exception e) {
	WebUI.delay(GlobalVariable.short_delay)

	WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Expand_Test_List'))
}

' To click Testcase name in Testconfig panel'
CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectListOfTestCase'(testcases_name)

WebUI.click(findTestObject('Object Repository/Test_Config_Panel/button_Start Execution'))

WebUI.delay(GlobalVariable.short_delay)

'To find Execution Time stamp'

def date = new Date()

def sdf = new SimpleDateFormat('yyyy_MM_dd-HH_mm_ss')

def report_time_stamp = sdf.format(date)

WebUI.comment('Test Execution TimeStamp ::  '+report_time_stamp)


'To Verify that Results Panel gets selected & Wait for Execution Competed'
def run_status

WebUI.delay(GlobalVariable.short_delay)

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

WebUI.delay(GlobalVariable.long_delay)

WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Report'))

WebUI.delay(GlobalVariable.short_delay)

WebUI.click(findTestObject('Report_panel_objects/Download_Current_DUT_Report/button_Download_Current_DUT_Report_Data'))

WebUI.verifyElementPresent(findTestObject('Report_panel_objects/Download_Current_DUT_Report/div_DownloadFile_FileName_Ok_Cancel'), 
    5)

WebUI.verifyElementVisible(findTestObject('Report_panel_objects/Download_Current_DUT_Report/div_DownloadFile_FileName_Ok_Cancel'))

report_name = WebUI.getAttribute(findTestObject('Report_panel_objects/Download_Current_DUT_Report/input_FileName_text'), 
    'value')

println('report_name')
WebUI.delay(GlobalVariable.short_delay)

if (report_name.contains('.zip')) {
    WebUI.comment('.zip Filename is prefixed with the project Name input in Product Capability Panel')
}

if (report_name.contains(project_name)) {
    WebUI.comment('Project_Name is present in Report Name')
}

if (report_name.contains(report_time_stamp)) {
    WebUI.comment('filename has the right timestamp- Date followed by time of execution')
} else {
    WebUI.comment('Timestamp is Wrong')
}

WebUI.verifyElementClickable(findTestObject('Report_panel_objects/Download_Current_DUT_Report/input_FileName_text'), FailureHandling.STOP_ON_FAILURE)

WebUI.delay(GlobalVariable.short_delay)

edited_report_name = (report_name.minus('.zip') + '_1.zip')

WebUI.sendKeys(findTestObject('Report_panel_objects/Download_Current_DUT_Report/input_FileName_text'), Keys.chord(Keys.CONTROL, 
        'a'))

WebUI.sendKeys(findTestObject('Report_panel_objects/Download_Current_DUT_Report/input_FileName_text'), Keys.chord(Keys.BACK_SPACE))

WebUI.setText(findTestObject('Report_panel_objects/Download_Current_DUT_Report/input_FileName_text'), edited_report_name)

WebUI.click(findTestObject('Object Repository/Report_panel_objects/Download_Current_DUT_Report/button_Cancel'))

WebUI.delay(GlobalVariable.short_delay)

WebUI.click(findTestObject('Report_panel_objects/Download_Current_DUT_Report/button_Download_Current_DUT_Report_Data'))

def zip_file_path_sts = new File((home_path + '/Downloads/') + report_name)

boolean zip_file_sts_before_download = zip_file_path_sts.exists()

if (zip_file_sts_before_download ) {
	
	boolean zip_file_deleted = zip_file_path_sts.delete()
	
	WebUI.comment('Existing zip File deleted Successfully :: '+zip_file_deleted )
}
else {
	WebUI.comment('Zip file is not exists before click ok button')
}


WebUI.click(findTestObject('Object Repository/Report_panel_objects/Download_Current_DUT_Report/button_Ok'))

WebUI.delay(GlobalVariable.long_delay)


def task = [python_path, katalon_path + '\\Test Cases\\Python_files\\Extract_Zip_File.py', "$zip_file_path_sts", "$downloads_folder_path"].execute()

def report_name_without_extension = report_name.minus('.zip')

WebUI.comment('report_name_without_extension ::'+report_name_without_extension)

WebUI.delay(GlobalVariable.long_delay)

def extracted_zip_path_sts = new File((home_path + '/Downloads/') + report_name_without_extension)

String Rfile = extracted_zip_path_sts.listFiles().sort({ 
        -(it.lastModified())
    }).head()

File Dut_file = new File(Rfile)

List Dut_Report_Folder_List = Arrays.asList(Dut_file.list())

def length = Dut_Report_Folder_List.size()

for (int i = 0; i < length; i++) {
    println(Dut_Report_Folder_List[i])
}


'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()


