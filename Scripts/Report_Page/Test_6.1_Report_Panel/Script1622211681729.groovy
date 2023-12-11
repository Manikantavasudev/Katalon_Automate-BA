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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword as WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.nio.file.*
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.By as By
import java.io.IOException as IOException
import java.io.File as File
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.support.Color as Color
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

WebUI.delay(GlobalVariable.short_delay)

'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String pc_xml_file_folder = all_data.user_config_data.PC_XML_File_Folder_Path

'To read Report data'
Map report_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Report_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

def report_vif_file_path = report_data.report_page_data.Report_Vif_File_Path

List testcases_name = report_data.report_page_data.Testcases_Name

def vif_file_path = Paths.get((pc_xml_file_folder + '\\') + report_vif_file_path)

'Common variables'
def actual_view_report_text = actual_view_report_text

def actual_current_html_text = actual_current_html_text

def actual_current_dut_text = actual_current_dut_text

def actual_Report_management_text = actual_Report_management_text

def report_location = location

def test_lab = 'Bangalore'

def test_engineer = 'Suma'

def remarks_and_comments = 'Regression Test'

def report_folder_path_text = 'C:\\AppData'

'To initialize driver object'
WebDriver driver = DriverFactory.getWebDriver()

'STEP 1: Click Product Capability Panel and load a DRP 16 File the following file'

'STEP 2: Connect DRP16 device to Port1 using GRL_SPL_CABLE_1'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Product Capability'))

WebUI.delay(GlobalVariable.short_delay)

WebUI.callTestCase(findTestCase('Load_Xml_File_RobotFramework'), [('key1') : vif_file_path], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(GlobalVariable.long_delay)

def vif_data_list = []

vif_data_list.add(WebUI.getAttribute(findTestObject('Object Repository/Report_panel_objects/Report_panel/VIF_data/input_Vendor_Name_name'),
		'value'))

vif_data_list.add(WebUI.getAttribute(findTestObject('Object Repository/Report_panel_objects/Report_panel/VIF_data/input_Model_Part_Number_name'),
		'value'))

vif_data_list.add(WebUI.getAttribute(findTestObject('Object Repository/Report_panel_objects/Report_panel/VIF_data/input_Product_Revision_name'),
		'value'))

WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Test Config'))

WebUI.delay(GlobalVariable.short_delay)

WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Product Capability'))

'STEP 3.Do a Get Device Data'
WebUI.click(findTestObject('Object Repository/Report_panel_objects/Report_panel/button_Get_Device_Data'))

WebUI.delay(GlobalVariable.short_delay)

try {
	WebUI.delay(30)

	driver.findElement(By.xpath('//button[contains(text(),\'Ok\')]')).click()

	WebUI.delay(GlobalVariable.long_delay)

	driver.findElement(By.xpath('//button[contains(text(),\'Ok\')]')).click()

	WebUI.delay(GlobalVariable.long_delay)

	driver.findElement(By.xpath('//button[contains(text(),\'Ok\')]')).click()

	WebUI.delay(70)
}
catch (Exception e) {
	WebUI.delay(90)
}

'STEP 4.Click Test Config Panel'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Test Config'))

WebUI.delay(GlobalVariable.short_delay)

'STEP 5.Select TD.PD.LL3.E1 GoodCRC Specification Revision compatibility test case'
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

WebUI.delay(GlobalVariable.short_delay)

'STEP 6.Verify the following information is in Right Panel is as per VIF Data present in the Product Capability Column'
def Dut_information_list = []

Dut_information_list.add(WebUI.getAttribute(findTestObject('Object Repository/Report_panel_objects/Report_panel/Dut_info/Manufacturer_Info'),
		'value'))

Dut_information_list.add(WebUI.getAttribute(findTestObject('Object Repository/Report_panel_objects/Report_panel/Dut_info/Model_Number'), 'value'))

Dut_information_list.add(WebUI.getAttribute(findTestObject('Object Repository/Report_panel_objects/Report_panel/Dut_info/Serial_Number'),
		'value'))

for (int i = 1; i <= 3; i++) {
	if ((vif_data_list[i]) == (Dut_information_list[i])) {
		println((vif_data_list[i]) = (Dut_information_list[i]))
	}
}

WebUI.delay(GlobalVariable.long_delay)

'STEP 7.Input the Following InformationTest Information'
WebUI.setText(findTestObject('Object Repository/Report_panel_objects/Report_panel/input_Test_Lab'), test_lab)

WebUI.setText(findTestObject('Object Repository/Report_panel_objects/Report_panel/input_Test_Engineer'), test_engineer)

WebUI.setText(findTestObject('Object Repository/Report_panel_objects/Report_panel/input_Remarks_Comments'), remarks_and_comments)

WebUI.setText(findTestObject('Object Repository/Report_panel_objects/Report_panel/input_Report_Folder_Path'), report_folder_path_text)

WebUI.delay(GlobalVariable.short_delay)

'STEP 8.Execute the test'
WebUI.click(findTestObject('Object Repository/Report_panel_objects/Report_panel/button_Start_Execution'))

'STEP 9.Wait for completion of Test Execution'
WebUI.delay(GlobalVariable.short_delay)

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

WebUI.delay(GlobalVariable.long_delay)

'STEP 10.Click Report Panel '
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Report'))

'STEP 11. Verify for the presence of the following buttons'

'View Report'
def View_report = WebUI.getText(findTestObject('Object Repository/Report_panel_objects/Report_panel/button_View_Report'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Report_panel_objects/Report_panel/button_View_Report'), 5)

if (View_report == actual_view_report_text) {
	WebUI.comment(actual_view_report_text + ' is present')
}

'STEP 12.Download Current HTML Report'
def Html_Report = WebUI.getText(findTestObject('Object Repository/Report_panel_objects/Report_panel/button_Download_Current_HTML_Report'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Report_panel_objects/Report_panel/button_Download_Current_HTML_Report'), 5)

if (Html_Report == actual_current_html_text) {
	WebUI.comment(actual_current_html_text + ' is present')
}

'STEP 13.Download Current DUT Report'
def Current_Dut = WebUI.getText(findTestObject('Object Repository/Report_panel_objects/Report_panel/button_Download_Current_DUT_Report_Data'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Report_panel_objects/Report_panel/button_Download_Current_DUT_Report_Data'),
		2)

if (Current_Dut == actual_current_dut_text) {
	WebUI.comment(actual_current_dut_text + ' is present')
}

'STEP 14:Report Data Management'
def Data_manage = WebUI.getText(findTestObject('Object Repository/Report_panel_objects/Report_panel/button_Report_Data_Management'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Report_panel_objects/Report_panel/button_Report_Data_Management'), 5)

if (Data_manage == actual_Report_management_text) {
	WebUI.comment(actual_Report_management_text + ' is present')
}

'STEP 15.Status Label showing: Test Reports Location'
def list_status_report = WebUI.getText(findTestObject('Object Repository/Report_panel_objects/Report_panel/div_Report_Path'))

if (list_status_report.contains(report_location)) {
	WebUI.comment(report_location + ' is Present in Status Bar')
}

'Switching to Frames'
if(WebUI.switchToFrame(findTestObject('Object Repository/Report_panel_objects/Report_panel/Frame_xpath'), 5) )
{
	println("going inside a frame")
	'STEP 16: GLR ICON'
	def bool = WebUI.verifyElementPresent(findTestObject('Object Repository/Report_panel_objects/Report_panel/img_Grl_Icon'), 10)
	'STEP 17:Heading: GRL-USB-PD-C2 Compliance Test Report'
	'if running in C2-EPR string should be updated'
	if(WebUI.getText(findTestObject('Object Repository/Report_panel_objects/Report_panel/div_GRL-USB-PD-C2_Compliance_Test_Report')) == 'C2 Compliance Test Report')
	{

		WebUI.comment('Report Heading is not proper')
	}
}
WebUI.switchToDefaultContent()


'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()