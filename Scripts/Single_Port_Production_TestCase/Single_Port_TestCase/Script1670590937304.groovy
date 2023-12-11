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

'Enable the Admin Mode'
WebUI.callTestCase(findTestCase('Admin_Check/Admin_mode_page_check'), [:], FailureHandling.STOP_ON_FAILURE)

'Declare jsonSlurper'
def jsonSlurper = new JsonSlurper()

'To read properties.json values'
File prop_obj = new File('properties.json')
Map Test_info = new JsonSlurper().parseFile(prop_obj, 'UTF-8')

'To read singleport.json values'
File single_port_info_obj = new File('singleport.json')
Map single_port_info = new JsonSlurper().parseFile(single_port_info_obj, 'UTF-8')
def sum_report_name = single_port_info.single_port_summary_reports_name

'To find Timestamp of testexecution and intergrate to final reports folder'
def exe_timestamp = CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.getTimestampValue'()
def single_summary_report_folder = (sum_report_name + '_') + exe_timestamp
println(('summary_report_folder' + single_summary_report_folder) + 'The end')

'To open c2 server'
WebUI.callTestCase(findTestCase('Prerequisites_Script/Test_Prerequisites'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

'To initialize driver object'
WebDriver driver = DriverFactory.getWebDriver()

'Check the admin icon is clickable or Not'
try {
	WebUI.click(findTestObject('Object Repository/Admin_mode/Admin_Icon_clickable'))
	WebUI.delay(5)
	'Get the disablePopUp value'
	def pop_up = WebUI.getText(findTestObject('Object Repository/Test_Case_Selection/popUp'))
	println(pop_up)
	if(pop_up == 'Off') {
		driver.findElement(By.xpath('//div[@id="disablePopups"]')).click()
	}
} catch (Exception x) {
	log.error('Element is not clickable at point exception')
}

'Click Connect button'
driver.findElement(By.xpath('//span[contains(text(), "Connection Setup")]')).click()

'Passing the C2 IP address'
String CS_C2_Tester_IP_Address_Info = Test_info.user_config_data.CS_C2_Tester_IP_Address_Info
println(CS_C2_Tester_IP_Address_Info)
WebUI.click(findTestObject('Object Repository/Test_Case_Selection/ClearText'))
driver.findElement(By.xpath('//input[@class=\'rc-select-search__field\']')).sendKeys(CS_C2_Tester_IP_Address_Info)

'To check Ethernet connection status in connection setup panel(ALP)'
CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.getConnectionStsWithClickbtn'()

log.info("Single PORT is going to Execute")
WebUI.callTestCase(findTestCase('Test Cases/Single_Port_Production_TestCase/Port_Configuration/Port_Selection'), [('key1') : single_summary_report_folder], FailureHandling.STOP_ON_FAILURE)
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");