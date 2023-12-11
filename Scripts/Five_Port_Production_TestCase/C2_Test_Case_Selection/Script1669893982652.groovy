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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By as By
import org.openqa.selenium.Keys as Keys
import groovy.json.JsonSlurper as JsonSlurper
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.slf4j.LoggerFactory as LoggerFactory

def log = LoggerFactory.getLogger('some-logger')


//'Enable the Admin Mode'
//WebUI.callTestCase(findTestCase('Admin_Check/Admin_mode_page_check'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Five_Port_Production_TestCase/C2_New_Initialization'), [:], FailureHandling.STOP_ON_FAILURE)

'To read properties.json values'
def jsonSlurper = new JsonSlurper()

File general_port_info_obj = new File('Five_Port_Data_Config\\general_port_info.json')
Map general_port_info = new JsonSlurper().parseFile(general_port_info_obj, 'UTF-8')

List Port_List = general_port_info.Port_List
def sum_report_name = general_port_info.summary_reports_name

'To find Timestamp of testexecution and intergrate to final reports folder'
def exe_timestamp = CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.getTimestampValue'()

def summary_report_folder = (sum_report_name + '_') + exe_timestamp

println(('summary_report_folder' + summary_report_folder) + 'The end')

'To initialize driver object'
WebDriver driver = DriverFactory.getWebDriver()

assert Port_List.size() > 0

WebUI.comment(((('User Provided Port list :: ' + Port_List) + ' ******** ') + ' Port list size :: ') + Port_List.size())
for (def port_num : Port_List)
	{
		'Configuring the PORT 1'
		if (port_num == 'Port1') {
			log.info("PORT1 is going to Execute")
			WebUI.callTestCase(findTestCase('Test Cases/Five_Port_Production_TestCase/Port_Configuration/Port1_Selection'), [('key1') : summary_report_folder], FailureHandling.STOP_ON_FAILURE)
			CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			}
					
		'Configuring the PORT 2'
		if (port_num == 'Port2') {
			WebUI.callTestCase(findTestCase('Test Cases/Five_Port_Production_TestCase/C2_New_Initialization'), [:], FailureHandling.STOP_ON_FAILURE)
			log.info("PORT2 is going to Execute")
			WebUI.callTestCase(findTestCase('Test Cases/Five_Port_Production_TestCase/Port_Configuration/Port2_Selection'), [('key1') : summary_report_folder], FailureHandling.STOP_ON_FAILURE)
			CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			}
			
		'Configuring the PORT 3'
		if (port_num == 'Port3') {
			WebUI.callTestCase(findTestCase('Test Cases/Five_Port_Production_TestCase/C2_New_Initialization'), [:], FailureHandling.STOP_ON_FAILURE)
			log.info("PORT3 is going to Execute")
			WebUI.callTestCase(findTestCase('Test Cases/Five_Port_Production_TestCase/Port_Configuration/Port3_Selection'), [('key1') : summary_report_folder], FailureHandling.STOP_ON_FAILURE)
			CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			}
					
		'Configuring the PORT 4'
		if (port_num == 'Port4') {
			WebUI.callTestCase(findTestCase('Test Cases/Five_Port_Production_TestCase/C2_New_Initialization'), [:], FailureHandling.STOP_ON_FAILURE)
			log.info("PORT4 is going to Execute")
			WebUI.callTestCase(findTestCase('Test Cases/Five_Port_Production_TestCase/Port_Configuration/Port4_Selection'), [('key1') : summary_report_folder], FailureHandling.STOP_ON_FAILURE)
			CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			}
					
		'Configuring the PORT 5'
		if (port_num == 'Port5') {
			WebUI.callTestCase(findTestCase('Test Cases/Five_Port_Production_TestCase/C2_New_Initialization'), [:], FailureHandling.STOP_ON_FAILURE)
			log.info("PORT5 is going to Execute")
			WebUI.callTestCase(findTestCase('Test Cases/Five_Port_Production_TestCase/Port_Configuration/Port5_Selection'), [('key1') : summary_report_folder], FailureHandling.STOP_ON_FAILURE)
			CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			}
	}