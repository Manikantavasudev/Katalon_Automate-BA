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

'To open c2 server if it is not already opened'
WebUI.callTestCase(findTestCase('Prerequisites_Script/Test_Prerequisites'), [:], FailureHandling.STOP_ON_FAILURE)

'To read general_port_info.json values'
File general_port_info_obj = new File('Five_Port_Data_Config\\general_port_info.json')

Map general_port_info = new JsonSlurper().parseFile(general_port_info_obj, 'UTF-8')

'Json data'
def sum_report_name = general_port_info.summary_reports_name

def com_port = general_port_info.COM_Port

List Port_List = general_port_info.Port_List

'To read properties.json values'
File prop_obj = new File('properties.json')

Map all_properties_info = new JsonSlurper().parseFile(prop_obj, 'UTF-8')

def common_data = all_properties_info.user_config_data

'Json data'
String python_path = common_data.Python_exe_path

String Installation_path = common_data.Katalon_installation_path

String katalon_build_version = common_data.Katalon_Build_Version

String email_config_py_path = Installation_path + '\\Email_Config\\PyFiles\\Five_Port_Email_Config.py'

String email_config_json_path = Installation_path + '\\Email_Config\\email_config.json'

WebUI.callTestCase(findTestCase('Five_Port_Script/Test_Delete_Temp_File'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

WebUI.delay(GlobalVariable.long_delay)

'To find Timestamp of testexecution and intergrate to final reports folder'
def exe_timestamp = CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.getTimestampValue'()

def summary_report_folder = (sum_report_name + '_') + exe_timestamp

println(('summary_report_folder' + summary_report_folder) + 'The end')

'To check Ethernet connection status in connection setup panel'
CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.getConnectionStsWithClickbtn'()

'To c2 read application version'
def c2_app_version = CustomKeywords.'grl_usb_pd_c2_Testcase.ConnectionSetup_Page.getSoftwareVersion'()
'To enable admin mode'
try
{
	WebUI.click(findTestObject('Object Repository/Admin_mode/Click_Admin'))

	WebUI.click(findTestObject('Object Repository/Admin_mode/Disable_Popups'))

}
catch(Exception e)
{
	println("admin mode not enabled")
}

'To click options page'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Options'))

'To configure COM Port'
WebUI.clearText(findTestObject('Object Repository/Five_Port_Objects/Options/input_COM_Port'))

WebUI.sendKeys(findTestObject('Object Repository/Five_Port_Objects/Options/input_COM_Port'), general_port_info.COM_Port)

CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.portConnectBtnSts'()

'To click Connect button and Read port connection status in option page'
assert Port_List.size() > 0

WebUI.comment(((('User Provided Port list :: ' + Port_List) + ' ******** ') + ' Port list size :: ') + Port_List.size())

for (def port_num : Port_List) {
	'To configure MOI and Run the Testcases based on selected port'

	'configuring moi and run testcase using port1'
	if (port_num == 'Port1') {
		WebUI.callTestCase(findTestCase('Five_Port_Script/Port1_Config'), [('key1') : summary_report_folder], FailureHandling.STOP_ON_FAILURE)
	}
	
	'configuring moi and run testcase using port2'
	if (port_num == 'Port2') {
		WebUI.callTestCase(findTestCase('Five_Port_Script/Port2_Config'), [('key1') : summary_report_folder], FailureHandling.STOP_ON_FAILURE)
	}
	
	'configuring moi and run testcase using port3'
	if (port_num == 'Port3') {
		WebUI.callTestCase(findTestCase('Five_Port_Script/Port3_Config'), [('key1') : summary_report_folder], FailureHandling.STOP_ON_FAILURE)
	}
	
	'configuring moi and run testcase using port4'
	if (port_num == 'Port4') {
		WebUI.callTestCase(findTestCase('Five_Port_Script/Port4_Config'), [('key1') : summary_report_folder], FailureHandling.STOP_ON_FAILURE)
	}
	
	'configuring moi and run testcase using port5'
	if (port_num == 'Port5') {
		WebUI.callTestCase(findTestCase('Five_Port_Script/Port5_Config'), [('key1') : summary_report_folder], FailureHandling.STOP_ON_FAILURE)
	}
}

WebUI.delay(60)

'To copy overall summary report xls file from temp folder'

'To arrange input and output report folder structure in comparison report folder'
String copy_report_name_pypath = Installation_path + '\\Five_Port_Data_Config\\PyFiles\\Summary_Report_Copy.py'

def task_copy_summary_report = [python_path, "$copy_report_name_pypath", summary_report_folder].execute()

WebUI.delay(GlobalVariable.huge_delay)


WebUI.comment('Test Execution is Completed')



