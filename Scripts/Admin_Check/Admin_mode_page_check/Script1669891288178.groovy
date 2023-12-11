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
import org.openqa.selenium.By as By
import org.openqa.selenium.Keys as Keys
import groovy.json.JsonSlurper as JsonSlurper
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.slf4j.LoggerFactory

def log = LoggerFactory.getLogger('some-logger')

'To open c2 server'
WebUI.callTestCase(findTestCase('Prerequisites_Script/Test_Prerequisites'), [:], FailureHandling.STOP_ON_FAILURE)

HashMap<String, Object> returnMap= new HashMap<String, Object>();
// 2 strings to get the command output and error message
def cmd_out = new StringBuilder();
def cmd_err = new StringBuilder();

'To read properties.json values'
File prop_obj = new File('properties.json')

Map all_properties_info = new JsonSlurper().parseFile(prop_obj, 'UTF-8')

'Json data'
String python_path = all_properties_info.user_config_data.Python_exe_path

log.info(python_path)

String Installation_path = all_properties_info.user_config_data.Katalon_installation_path

log.info(Installation_path)

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

'Check the application is loaded '
String text_Value = WebUI.verifyElementText(findTestObject('Object Repository/Admin_mode/get_Pageload_verfiy'), 'Set App Mode :')

'Check the Admin Mode is visible'
try {
	if (WebUI.verifyElementVisible(findTestObject('Object Repository/Admin_mode/Admin_Icon_clickable'))) {
		log.info('Element is visible')
	}
}
catch (Exception x) {
	println('Element is not visible')

	log.info('Make the App property Visible in Application page')
	log.info('Make a change over on App property in Admin mode PropertyType as True through python Script')
	log.info('Then the AdminMode gets visible')
	
	'Change the App property value from false to True '
	String change_AppProperty = Installation_path + '\\AdminChange\\PyFiles\\change_json_data.py'
	def task_to_change = [python_path, change_AppProperty].execute()
	task_to_change.consumeProcessOutput(cmd_out, cmd_err);
	task_to_change.waitFor();

	// Return the command outputs
	returnMap.put("command_output", cmd_out);
	returnMap.put("error_message", cmd_err);
	println(returnMap)

	WebUI.delay(10)  /* Wait for 10 Second task to complete the change. */

	CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
	
	'Restart the C2 server through the Python script'
	String reload_application = Installation_path + '\\AdminChange\\PyFiles\\Application_reload.py'
	def task_to_change1 = [python_path, reload_application].execute()
	task_to_change1.consumeProcessOutput(cmd_out, cmd_err);
	task_to_change1.waitFor();

	// Return the command outputs
	returnMap.put("command_output", cmd_out);
	returnMap.put("error_message", cmd_err);
	println(returnMap)

	WebUI.delay(30)  /* Wait for 30 Seconds to take restart the backend server application by python. */
	
	'Check whether the AdminMode is clickable or not '
	try {
		CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()
		
		WebUI.delay(10)  /* Wait for 10 Seconds to re-open the browser application */

		String element_clickable = WebUI.verifyElementClickable(findTestObject('Object Repository/Admin_mode/Admin_Icon_clickable'))
		log.info('The Element is clickable')
	}
	catch (Exception ex) {
		log.error('Element is not clickable at point exception')
	}
	
	try {
		
		'Check Admin Mode ElementText is present correctly'
		WebUI.click(findTestObject('Object Repository/Admin_mode/Admin_Icon_clickable'))
		
		'Check the page is re-direct to the AdminMode page'
		if (WebUI.verifyElementVisible(findTestObject('Object Repository/Admin_mode/adminmode_pageload_verify'))) {
			log.info('Page redirect to AdminMode page')
		} else {
			log.error('Page is not redirect to AdminMode page')
		}
	}
	catch (Exception ex) {
		log.error('Cannot able to click the element at point expection')
	}
	
	'Check whether the Element Text is correct or not '
	WebUI.verifyElementText(findTestObject('Object Repository/Admin_mode/Admin_Icon_Present'), 'Admin Mode')
}

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");