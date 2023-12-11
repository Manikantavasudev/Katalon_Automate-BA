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
import java.nio.file.*
import java.io.IOException as IOException
import java.io.File as File


'driver object Initialization'
WebDriver driver = DriverFactory.getWebDriver()

'To read general_port_info.json values'
File port2_config_obj = new File('Five_Port_Data_Config\\port2_config.json')

Map port2_info = new JsonSlurper().parseFile(port2_config_obj, 'UTF-8')

'To read properties.json values'
File prop_obj = new File('properties.json')

Map all_properties_info = new JsonSlurper().parseFile(prop_obj, 'UTF-8')

'Json data'
String python_path = all_properties_info.user_config_data.Python_exe_path

String Installation_path = all_properties_info.user_config_data.Katalon_installation_path

def vif_file_path = port2_info.Vif_File_Path

def golden_reports_path = port2_info.Golden_Reports_Path

List moi_list_checkbox = port2_info.MOI_List

String IP =  all_properties_info.user_config_data.CS_C2_Tester_IP_Address_Info

'gerneral values'
def port2_val = '2'

def project_name = 'Port2'

def summary_report_folder = key1

'To read Aggregation Tool configuration value'
File config_json_info = new File(Installation_path + '\\C2_TestResult_AggregationTool\\Config.json')

Map config_json_info_dict = new JsonSlurper().parseFile(config_json_info, 'UTF-8')

def compare_report_path = config_json_info_dict.ReportFilePath

'To read general_port_info.json values'
File general_port_info_obj = new File('Five_Port_Data_Config\\general_port_info.json')

Map general_port_info = new JsonSlurper().parseFile(general_port_info_obj, 'UTF-8')

WebUI.click(findTestObject('Object Repository/Connection_Setup_Objects/Connection _tab'))
WebUI.click(findTestObject('Object Repository/Connection_Setup_Objects/Click_connect'))
WebUI.delay(10)
try
{
	WebUI.click(findTestObject('Object Repository/Connection_Setup_Objects/Check_connected'))
}
catch(Exception e)
{
	WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/Clear_text'))
	WebUI.delay(2)
	WebUI.setText(findTestObject('Object Repository/Page_GRL - USB PD C2/input_Please enterselect C2 address_rc-sele_72428c'),
			IP )
	WebUI.delay(2)
	WebUI.click(findTestObject('Object Repository/Connection_Setup_Objects/Click_connect'))
	WebUI.delay(5)
}


'To navigate options page'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Options'))

'To Configure port Info in options page'
WebUI.delay(GlobalVariable.short_delay)

'To configure COM Port'
WebUI.clearText(findTestObject('Object Repository/Five_Port_Objects/Options/input_COM_Port'))

"com port selection"
WebUI.delay(2)
WebUI.sendKeys(findTestObject('Object Repository/Five_Port_Objects/Options/input_COM_Port'), general_port_info.COM_Port)
WebUI.delay(5)


CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.configureSelectPort'(port2_val)

WebUI.delay(GlobalVariable.short_delay)

'To Enable port in options page'
CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.enablePort'(port2_val, findTestObject('Object Repository/Five_Port_Objects/Options/Port_Info/div_Enable_Port2_Toggle'))

'To click connect button in options page'

CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.portConnectBtnSts'()
int i=0;
try
{
	WebUI.click(findTestObject('Object Repository/Admin_mode/Click_Admin'))
	'To click Product Capability page'
	WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))
	i=1;
}
catch(Exception e)
{
	'To click Product Capability page'
	WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))
	i=2;
}

'To click Product Capability page'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

WebUI.delay(GlobalVariable.short_delay)

'To Configure Project Name'
CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.setProjectName'(project_name)

'To click Load xml vif file button & verify windows file open dialogue and select xml file '
WebUI.callTestCase(findTestCase('Load_Xml_File_RobotFramework'), [('key1') : vif_file_path], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(GlobalVariable.short_delay)

'To get selected dut type value'
def selected_dut_type = WebUI.getAttribute(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_1_dut_dropdown_icon'),
		'innerText')

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

'To click MOI list checkbox'
try {
	for (String moi_name : moi_list_checkbox) {
		'To select moi name'
		WebUI.delay(3)

		driver.findElement(By.xpath(('//span[contains(text(),\'' + moi_name) + '\')]')).click()
	}
}
catch (Exception e) {
	WebUI.comment('User provided moi name is not Exist:: ' + moi_list_checkbox)
}

WebUI.delay(GlobalVariable.short_delay)

'To enter popup timer'
def timer_popup_val = GlobalVariable.timeout_popup_val

CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.enterPopupTimer'(timer_popup_val)

'To configure User provider  MOI'
if (selected_dut_type == 'Cable') {
	WebUI.callTestCase(findTestCase('Common_Data_Reader/Five_Port_Moi_Data_Reader/Moi_Selection/Moi_Selection_CableDut'),
			[('key_json') : 'port2_config.json'], FailureHandling.STOP_ON_FAILURE)
} else {
	WebUI.callTestCase(findTestCase('Common_Data_Reader/Five_Port_Moi_Data_Reader/Moi_Selection/Moi_Selection_OtherDut'),
			[('key_dut') : selected_dut_type, ('key_json') : 'port2_config.json'], FailureHandling.STOP_ON_FAILURE)
}

'TestConfig Page Screenshot '
WebUI.takeScreenshot()

WebUI.delay(GlobalVariable.short_delay)

try {

	'To click start Execution'
	//WebUI.click(findTestObject('Object Repository/Test_Config_Panel/button_Start Execution'))

	WebElement start_execution_ele = driver.findElement(By.xpath('//button[@id="tcStartAndStopExecutionBtn"]'))

	JavascriptExecutor executor = ((driver) as JavascriptExecutor)

	executor.executeScript('arguments[0].click();', start_execution_ele)

}
catch (Exception e) {
	WebUI.comment('Start Execution button is not clickable')
}


int iteration_count = 0
int iteration_limit = 6

while (iteration_count < iteration_limit) {
	'To read Test Execution status'
	if(i== 1)
	{
		CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.readTestExecutionStsForPopupDisable'()
	}
	if(i==2)
	{
		CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.readTestExecutionSts'()
	}


	iteration_count = (iteration_count + 1)

}
'To disable selected port'

CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.disablePort'(port2_val, findTestObject('Object Repository/Five_Port_Objects/Options/Port_Info/div_Enable_Port2_Toggle'))

String config_json_path = Installation_path + '\\C2_TestResult_AggregationTool\\Config.json'

String golden_csv_input_pypath = Installation_path + '\\Five_Port_Data_Config\\PyFiles\\Five_Port_Report_Config.py'

WebUI.comment('*******************************************************************************************************************************************************')

'To call python file to get latest report folder and update the latest  folder to json'
def task_json_config = [python_path, golden_csv_input_pypath, config_json_path, golden_reports_path].execute()

WebUI.delay(GlobalVariable.long_delay)

int json_config_itr_cnt = 0
int json_config_itr_limit = 30

while (json_config_itr_cnt < json_config_itr_limit) {

	'To read Aggregation Tool configuration value'
	File config_obj = new File(Installation_path + '\\C2_TestResult_AggregationTool\\Config.json')

	Map config_json_obj = new JsonSlurper().parseFile(config_obj, 'UTF-8')

	String file_location = config_json_obj.FileLocation
	println(file_location)

	if (file_location.contains('Port2')) {
		break
	}
	WebUI.delay(GlobalVariable.short_delay)
	json_config_itr_cnt = (json_config_itr_cnt + 1)
}


WebUI.comment('*******************************************************************************************************************************************************')


'To call python Aggregation Tool to Compare the current Report  Results with golden dut results'
String aggregation_tool_path = Installation_path + '\\C2_TestResult_AggregationTool\\MainProgram.py'

def task_comp_tool = [python_path, "$aggregation_tool_path"].execute()

WebUI.delay(GlobalVariable.huge_delay)

WebUI.comment('*******************************************************************************************************************************************************')

'To call Html file result Verifier file to verify User Config data and Executed moi and Testcase data'
String html_file_verifier_path = Installation_path + '\\C2_TestResult_AggregationTool\\VerifyUserInput\\Summary_Html_File_Verifier.py'

String moi_config_json_path = Installation_path + '\\Five_Port_Data_Config\\port2_config.json'

def html_result_verifier = [python_path, "$html_file_verifier_path",moi_config_json_path,project_name,summary_report_folder,Installation_path].execute()

println(html_result_verifier.text)

WebUI.delay(GlobalVariable.long_delay)

int html_config_itr_cnt = 0
int html_config_itr_limit = 30
File txt_file_path

while (html_config_itr_cnt < html_config_itr_limit) {

	try {

		txt_file_path = new File( Installation_path + '/'+'Five_Port_Data_Config/PyFiles/temp_port'+'/' + 'Port2.txt')

		boolean file_exists_sts = txt_file_path.exists()

		if (file_exists_sts) {
			WebUI.comment('File  present:: '+txt_file_path)
			break
		}
	}
	catch (NoSuchFileException e) {
		WebUI.comment('File not present:: '+txt_file_path)
	}

	WebUI.delay(GlobalVariable.short_delay)
	html_config_itr_cnt = (html_config_itr_cnt + 1)
}

WebUI.comment('*******************************************************************************************************************************************************')


'To arrange input and output report folder structure in comparison report folder'
String alter_report_struct = Installation_path + '\\Five_Port_Data_Config\\PyFiles\\Alter_Output_Report.py'

def task_alter_report = [python_path, "$alter_report_struct", summary_report_folder, 'Port2'].execute()

WebUI.delay(GlobalVariable.huge_delay)

WebUI.comment('*******************************************************************************************************************************************************')

'To Integrate xls Summary report'
String integrate_xls_summary = Installation_path + '\\Five_Port_Data_Config\\PyFiles\\Xls_Reader_Updated.py'

def task_integrate_report = [python_path, "$integrate_xls_summary ", 'Port2'].execute()

WebUI.delay(GlobalVariable.long_delay)


int integrate_sum_itr_cnt = 0
int integrate_sum_itr_limit = 30
File summary_file_path

while (integrate_sum_itr_cnt < integrate_sum_itr_limit) {

	try {

		summary_file_path = new File(compare_report_path + '/'+summary_report_folder+'/' + 'Summary-Port2.xlsx')

		boolean sum_file_exists_sts = summary_file_path.exists()

		if (sum_file_exists_sts) {
			WebUI.comment('File present:: '+summary_file_path)

			break
		}
	}
	catch (NoSuchFileException e) {
		WebUI.comment('File not present:: '+summary_file_path)
	}

	WebUI.delay(GlobalVariable.short_delay)
	integrate_sum_itr_cnt = (integrate_sum_itr_cnt + 1)
}

WebUI.comment('*******************************************************************************************************************************************************')


