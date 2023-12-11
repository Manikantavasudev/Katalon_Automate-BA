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
import groovy.json.JsonSlurper as JsonSlurper
import org.openqa.selenium.By as By
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import groovy.json.JsonBuilder as JsonBuilder
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper as MobileElementCommonHelper
import groovy.transform.CompileStatic as CompileStatic
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.exception.WebElementNotFoundException as WebElementNotFoundException
import org.openqa.selenium.support.ui.Select as Select
import java.nio.file.*
import groovy.json.JsonParserType as JsonParserType
import groovy.json.JsonOutput as JsonOutput
import java.io.File as File
import java.util.Scanner as Scanner
import java.io.FileNotFoundException as FileNotFoundException
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor

'To open c2 server'
WebUI.callTestCase(findTestCase('Prerequisites_Script/Test_Prerequisites'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(GlobalVariable.huge_delay)

String moi_list_locator = '//ul[@class="rc-tree-child-tree rc-tree-child-tree-open"]/li/span[3]/span'

'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

def common_data = all_data.user_config_data

String python_path = common_data.Python_exe_path

String Installation_path = common_data.Katalon_installation_path

String katalon_build_version = common_data.Katalon_Build_Version

'To read moi_configuration input .json values'
File file_obj = new File('Dut_Testing_Data_Config\\Moi_Configuration_Input.json')

Map input_prop = new JsonSlurper().parseFile(file_obj, 'UTF-8')

'To Read xml file from json '
String vif_file_path = input_prop.Vif_File_Path

'To read moi user config checkbox'
List moi_list_checkbox = input_prop.MOI_List

'To read project name from json'
String proj_name = input_prop.Project_Name

'To read golden csv from json'
def golden_reports_path = input_prop.Golden_Reports_Path

'To get py and json file path'
String prereq_py_path = Installation_path + '\\Dut_Testing_Data_Config\\PyFiles\\Installer_Prerequisites.py'

String email_config_py_path = Installation_path + '\\Email_Config\\PyFiles\\Dut_Testscript_Email_Config.py'

String email_config_json_path = Installation_path + '\\Email_Config\\email_config.json'

String config_json_path = Installation_path + '\\C2_TestResult_AggregationTool\\Config.json'

String timestamp_json_path = Installation_path + '\\Dut_Testing_Data_Config\\PyFiles\\Report_TimeStamp.py'

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

'driver object Initialization'
WebDriver driver = DriverFactory.getWebDriver()

'To click Product Capability page'
WebUI.delay(GlobalVariable.long_delay)

'To read Connection Status'
CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.getConnectionStsWithClickbtn'()

'To c2 read application version'


def c2_app_version = CustomKeywords.'grl_usb_pd_c2_Testcase.ConnectionSetup_Page.getSoftwareVersion'()
'To get Timestamp of testexecution '

def start_exe_timestamp = CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.getTimestampValue'()
int i;

try
{
	WebUI.click(findTestObject('Object Repository/Admin_mode/Click_Admin'))

	WebUI.click(findTestObject('Object Repository/Admin_mode/Disable_Popups'))

	WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))
	"able to click admin mode & click no popup toggle bar i=0"
	i=0;
}
catch(Exception x)
{
	WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))
	i=1;
}
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))
'To select project name'
CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.setProjectName'(proj_name)

'To click Load xml vif file button & verify windows file open dialogue and select xml file '
WebUI.callTestCase(findTestCase('Load_Xml_File_RobotFramework'), [('key1') : vif_file_path], FailureHandling.STOP_ON_FAILURE)

println(WebUI.getText(findTestObject('Object Repository/Send_keys_function/Load_xml_file')))
if( WebUI.getText(findTestObject('Object Repository/Send_keys_function/Load_xml_file')) != "Load XML VIF File")
{

	WebUI.delay(GlobalVariable.short_delay)

	'To get selected dut type value'
	def selected_dut_type = WebUI.getAttribute(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_1_dut_dropdown_icon'),
			'innerText')

	println(selected_dut_type)

	'To take product capability page screenshot'
	WebUI.takeScreenshot()

	'To click Testconfig page'
	WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Test Config'))

	WebUI.delay(GlobalVariable.short_delay)

	'To click All Supported Certification dropdown'
	CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectCertificationDropdown'(findTestObject('Object Repository/Test_Config_Panel/Certification_drop_down'),
			findTestObject('Object Repository/Test_Config_Panel/Page_GRL - USB PD C2/a_All Supported Certifications'))

	'To click Expand Test List  checkbox'
	CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.clickExpandTestList'()

	//	String updated_moi_name
	//
	//	'To click MOI list checkbox'
	//	for (String moi_name : moi_list_checkbox) {
	//		try {
	//			updated_moi_name = moi_name
	//
	//			'To select moi name'
	//			WebUI.delay(3)
	//
	//			driver.findElement(By.xpath(('//span[contains(text(),\'' + moi_name) + '\')]')).click()
	//		}
	//		catch (Exception e) {
	//			WebUI.comment('User provided moi name is not Exist:: ' + updated_moi_name)
	//			assert false
	//		}
	//	}
	//
		WebUI.delay(GlobalVariable.short_delay)
	
		def timer_popup_val = GlobalVariable.timeout_popup_val
	
		'To check timeout popup checkbox is Enabled or not'
		CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.popupTimer'(timer_popup_val)
	//
	//	'To configure User provider  MOI'
	//	if (selected_dut_type == 'Cable') {
	//		WebUI.callTestCase(findTestCase('Common_Data_Reader/Comparison_Tool_Moi_Data_Reader/Moi_Selection/Moi_Selection_CableDut'),
	//		[:], FailureHandling.STOP_ON_FAILURE)
	//	} else {
	//		WebUI.callTestCase(findTestCase('Common_Data_Reader/Comparison_Tool_Moi_Data_Reader/Moi_Selection/Moi_Selection_OtherDut'),
	//		[('key1') : selected_dut_type], FailureHandling.STOP_ON_FAILURE)
	//	}
	//
	//	'To read Manufacturer name(loaded vif name)'
	//	WebUI.comment('loaded vif file::' + vif_file_path)
	//
	//	WebUI.delay(GlobalVariable.short_delay)
	//
	//	def manufacturer_name = WebUI.getAttribute(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/input_Manufacturer'),
	//	'value')
	//
	//	WebUI.comment('Manufacturer name based on loaded vif file::' + manufacturer_name)
	//
	//	'TestConfig Page Screenshot '
	//	WebUI.takeScreenshot()
	//
	//	WebUI.delay(GlobalVariable.short_delay)
	if(selected_dut_type =="Dual Role Power[DRP]" )
	{
		CustomKeywords.'grl_usb_pd_c2_Testcase.MOI_Selection.forDRPMOISelection'()
	}
	else if(selected_dut_type =="Consumer Only" )
	{
		CustomKeywords.'grl_usb_pd_c2_Testcase.MOI_Selection.forConsumerOnly'()
	}
	else if(selected_dut_type =="Cable")
	{
		CustomKeywords.'grl_usb_pd_c2_Testcase.MOI_Selection.forCable'()
	}
	else if(selected_dut_type== "Provider Only")
	{
		CustomKeywords.'grl_usb_pd_c2_Testcase.MOI_Selection.ProviderOnly'()
	}
	else
	{
		println("UUT type is missing")
	}

	try {

		'To click start Execution'

		WebElement start_execution_ele = driver.findElement(By.xpath('//button[@id="tcStartAndStopExecutionBtn"]'))

		JavascriptExecutor executor = ((driver) as JavascriptExecutor)

		executor.executeScript('arguments[0].click();', start_execution_ele)

	}
	catch (Exception e) {
		WebUI.comment('Start Execution button is not clickable')
	}

	WebUI.delay(GlobalVariable.short_delay)

	int iteration_count = 0
	int iteration_limit = 8

	while (iteration_count < iteration_limit)
	{
		'To read Test Execution status'
		if(i== 0)
		{
			CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.readTestExecutionStsForPopupDisable'()
		}
		else
		{
			CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.readTestExecutionSts'()
		}


		iteration_count = (iteration_count + 1)

	}
	CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.readTestExecutionSts'()

	WebUI.comment('********************************** TestCase Execution is Completed SuccessFully *******************************************************************')

//	'To call python file to get latest report folder and update the latest  folder and golden report to config.json'
//	def task_update_json = [python_path, "$timestamp_json_path", "$config_json_path", golden_reports_path].execute()
//
//	WebUI.delay(GlobalVariable.huge_delay)
//
//	'To call python Aggregation Tool to Compare the current Report  Results with golden dut results'
//	String aggregation_tool_path = Installation_path + '\\C2_TestResult_AggregationTool\\MainProgram.py'
//
//	def task_new = [python_path, "$aggregation_tool_path"].execute()
//
//	WebUI.delay(GlobalVariable.huge_delay)
//
//	'To get stop time of testexecution '
//	def stop_exe_timestamp = CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.getTimestampValue'()
//
//	'To call Email Configuration and pass the related parameters Test start time,stop time,application version'
//	def email_sub_info = [start_exe_timestamp, stop_exe_timestamp, c2_app_version, katalon_build_version]
//
//	def send_auto_email = [python_path, "$email_config_py_path", "$email_config_json_path", start_exe_timestamp,stop_exe_timestamp,c2_app_version,katalon_build_version].execute()
//
//	WebUI.comment('Test Execution is Completed')

}
else
{
	WebUI.comment("vif file not uploaded")
}





