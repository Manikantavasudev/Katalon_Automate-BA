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

int i

try {
    WebUI.click(findTestObject('Object Repository/Admin_mode/Click_Admin'))

    WebUI.click(findTestObject('Object Repository/Admin_mode/Disable_Popups'))

    WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

    'able to click admin mode & click no popup toggle bar i=0'
    i = 0
}
catch (Exception x) {
    WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

    i = 1
} 

WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

'To select project name'
CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.setProjectName'(proj_name)

'To click Load xml vif file button & verify windows file open dialogue and select xml file '
WebUI.callTestCase(findTestCase('Load_Xml_File_RobotFramework'), [('key1') : vif_file_path], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Page_GRL - USB PD C2/button_Get Device Data'))

WebUI.delay(60)

WebUI.click(findTestObject('Browser_Main_View_Objects/Popup_OK'))

