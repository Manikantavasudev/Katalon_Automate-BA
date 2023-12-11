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
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import groovy.json.JsonSlurper as JsonSlurper
import org.slf4j.LoggerFactory as LoggerFactory

def log = LoggerFactory.getLogger('some-logger')

'driver object Initialization'
WebDriver driver = DriverFactory.getWebDriver()

HashMap<String, Object> returnMap= new HashMap<String, Object>();
// 2 strings to get the command output and error message
def cmd_out = new StringBuilder();
def cmd_err = new StringBuilder();

'To read port1_config.json values'
File port1_config_obj = new File('Five_Port_Data_Config\\port1_config.json')
Map port1_info = new JsonSlurper().parseFile(port1_config_obj, 'UTF-8')

'To read general_port_info.json values'
File general_port_info_obj = new File('Five_Port_Data_Config\\general_port_info.json')
Map general_port_info = new JsonSlurper().parseFile(general_port_info_obj, 'UTF-8')

'To read properties.json values'
File prop_obj = new File('properties.json')
Map all_properties_info = new JsonSlurper().parseFile(prop_obj, 'UTF-8')
String python_path = all_properties_info.user_config_data.Python_exe_path
String Installation_path = all_properties_info.user_config_data.Katalon_installation_path

Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)
def common_data = all_data.user_config_data
String timestamp_json_path = Installation_path + '\\Dut_Testing_Data_Config\\PyFiles\\Report_TimeStamp.py'
String config_json_path = Installation_path + '\\C2_TestResult_AggregationTool\\Config.json'
String email_config_py_path = Installation_path + '\\Email_Config\\PyFiles\\Dut_Testscript_Email_Config.py'
String email_config_json_path = Installation_path + '\\Email_Config\\email_config.json'
String katalon_build_version = common_data.Katalon_Build_Version

def vif_file_path = port1_info.Vif_File_Path
def load_vif_field_config = port1_info.Load_Vif_Field
def dut_type = port1_info.Dut_Type
def golden_reports_path = port1_info.Golden_Reports_Path

'gerneral values'
def port1_val = '1'
def project_name = 'Port1'

'To read Aggregation Tool configuration value'
File config_json_info = new File(Installation_path + '\\C2_TestResult_AggregationTool\\Config.json')
Map config_json_info_dict = new JsonSlurper().parseFile(config_json_info, 'UTF-8')
def compare_report_path = config_json_info_dict.ReportFilePath
List moi_list_checkbox = port1_info.MOI_List
def summary_report_folder = key1

def c2_app_version = CustomKeywords.'grl_usb_pd_c2_Testcase.ConnectionSetup_Page.getSoftwareVersion'()
def start_exe_timestamp = CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.getTimestampValue'()

'To navigate options page'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Options'))

'To configure COM Port(ALP)'
WebUI.clearText(findTestObject('Object Repository/Five_Port_Objects/Options/input_COM_Port'))
WebUI.sendKeys(findTestObject('Object Repository/Five_Port_Objects/Options/input_COM_Port'), general_port_info.COM_Port)

'To Configure port Info in options page'
CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.configureSelectPort'(port1_val)
WebUI.delay(GlobalVariable.short_delay)

'To Enable port in options page'
driver.findElement(By.xpath("//span [contains(text(), 'Options')]")).click()
def pop_up1 = WebUI.getText(findTestObject('Object Repository/Test_Case_Selection/option_pop_up'))
println(pop_up1)
if(pop_up1 == 'Off') {
CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.enablePort'(port1_val, findTestObject('Object Repository/Five_Port_Objects/Options/Port_Info/div_Enable_Port1_Toggle'))
}

'To click connect button in options page '
CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.portConnectBtnSts'()

'To Read Connection btn status in Connection Setup page '
CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.getTesterConnectionSts'()

'To click Product Capability page'
WebUI.click(findTestObject('Object Repository/Test_Case_Selection/product capability_button'))
WebUI.delay(GlobalVariable.short_delay)

'To Configure Project Name'
CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.setProjectName'(project_name)

'To click Load xml vif file button & verify the xml is loaded or not '
try {
	if (load_vif_field_config == 'YES') {
	WebUI.sendKeys(findTestObject('Object Repository/Test_Case_Selection/Load_xml_file'),vif_file_path)
	WebUI.delay(GlobalVariable.short_delay)
	} else {
		println("Loaded as No")
		CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.setDutType'(dut_type)
		WebUI.delay(GlobalVariable.short_delay)
	}
} catch (Exception x){
	CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.setDutType'(dut_type)
	WebUI.delay(GlobalVariable.short_delay)
}

'Depend on the DUT Type it will execute the test case'
def DUT_TYPE = WebUI.getText(findTestObject('Object Repository/Test_Case_Selection/Dut_Type'))
println(DUT_TYPE)

if (DUT_TYPE == 'Cable') {
	println("It's cable")
	WebUI.callTestCase(findTestCase('Five_Port_Production_TestCase/Dut_Type_Selection_Test_Case/Cable'), [:], FailureHandling.STOP_ON_FAILURE)
} else if (DUT_TYPE == 'Dual Role Power[DRP]'){
	println("It is DRP")
	WebUI.click(findTestObject('Object Repository/Test_Case_Selection/dropDown_CableSelection'))
	driver.findElement(By.xpath("//a[contains(text(),'GRL-SPL EPR Test Cable 1')]")).click()
	WebUI.callTestCase(findTestCase('Test Cases/Five_Port_Production_TestCase/Dut_Type_Selection_Test_Case/Dual_Role_Power'), [:], FailureHandling.STOP_ON_FAILURE)
} else if (DUT_TYPE == 'Consumer Only') {
	println("It is Consumer Only")
	WebUI.callTestCase(findTestCase('Test Cases/Five_Port_Production_TestCase/Dut_Type_Selection_Test_Case/Consumer_Only'), [:], FailureHandling.STOP_ON_FAILURE)
} else if (DUT_TYPE == 'Provider Only') {
	println("It is Provider Only")
	WebUI.click(findTestObject('Object Repository/Test_Case_Selection/dropDown_CableSelection'))
	driver.findElement(By.xpath("//a[contains(text(),'GRL-SPL EPR Test Cable 1')]")).click()
	WebUI.callTestCase(findTestCase('Test Cases/Five_Port_Production_TestCase/Dut_Type_Selection_Test_Case/Provider_Only'), [:], FailureHandling.STOP_ON_FAILURE)
}

'To disable toggle button '
CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.disablePort'(port1_val, findTestObject('Object Repository/Five_Port_Objects/Options/Port_Info/div_Enable_Port1_Toggle'))

println('************************************************ TestCase Execution is Completed SuccessFully ************************************************')

String config_json_pathy = Installation_path + '\\C2_TestResult_AggregationTool\\Config.json'

String golden_csv_input_pypath = Installation_path + '\\Five_Port_Data_Config\\PyFiles\\Five_Port_Report_Config.py'

WebUI.comment('*******************************************************************************************************************************************************')

'To call python file to get latest report folder and update the latest  folder to json'
def task_json_config = [python_path, golden_csv_input_pypath, config_json_pathy, golden_reports_path].execute()

WebUI.delay(GlobalVariable.long_delay)

int json_config_itr_cnt = 0

int json_config_itr_limit = 20

while (json_config_itr_cnt < json_config_itr_limit) {
	'To read Aggregation Tool configuration value'
	File config_obj = new File(Installation_path + '\\C2_TestResult_AggregationTool\\Config.json')

	Map config_json_obj = new JsonSlurper().parseFile(config_obj, 'UTF-8')

	String file_location = config_json_obj.FileLocation

	println(file_location)

	if (file_location.contains('Port1'))
	{
		break
	}

	WebUI.delay(GlobalVariable.short_delay)

	json_config_itr_cnt = (json_config_itr_cnt + 1)
}

WebUI.comment('*******************************************************************************************************************************************************')

'To call python Aggregation Tool to Compare the current Report  Results with golden dut results'
String aggregation_tool_path = Installation_path + '\\C2_TestResult_AggregationTool\\MainProgram.py'

def task_comp_tool = [python_path, "$aggregation_tool_path"].execute()

WebUI.delay(40)

WebUI.comment('*******************************************************************************************************************************************************')

'To call Html file result Verifier file to verify User Config data and Executed moi and Testcase data'
String html_file_verifier_path = Installation_path + '\\C2_TestResult_AggregationTool\\VerifyUserInput\\Summary_Html_File_Verifier.py'

String moi_config_json_path = Installation_path + '\\Five_Port_Data_Config\\port1_config.json'

def html_result_verifier = [python_path, "$html_file_verifier_path", moi_config_json_path, project_name, summary_report_folder
	, Installation_path].execute()

println(html_result_verifier.text)

WebUI.delay(GlobalVariable.long_delay)


WebUI.comment('*******************************************************************************************************************************************************')

'To arrange input and output report folder structure in comparison report folder'
String alter_report_struct = Installation_path + '\\Five_Port_Data_Config\\PyFiles\\Alter_Output_Report.py'

def task_alter_report = [python_path, "$alter_report_struct", summary_report_folder, 'Port1'].execute()

WebUI.delay(GlobalVariable.huge_delay)

WebUI.comment('*******************************************************************************************************************************************************')

'To Integrate xls Summary report'
String integrate_xls_summary = Installation_path + '\\Five_Port_Data_Config\\PyFiles\\Xls_Reader_Updated.py'

def task_integrate_report = [python_path, "$integrate_xls_summary ", 'Port1'].execute()

WebUI.delay(GlobalVariable.long_delay)

'To get stop time of testexecution '
def stop_exe_timestamp = CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.getTimestampValue'()

'To call Email Configuration and pass the related parameters Test start time,stop time,application version'
def email_sub_info = [start_exe_timestamp, stop_exe_timestamp, c2_app_version, katalon_build_version]

def send_auto_email = [python_path, "$email_config_py_path", "$email_config_json_path", start_exe_timestamp,stop_exe_timestamp,c2_app_version,katalon_build_version].execute()

WebUI.comment('*******************************************************************************************************************************************************')