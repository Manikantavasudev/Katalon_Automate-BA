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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable


'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String python_path = all_data.user_config_data.Python_exe_path

String Installation_path = all_data.user_config_data.Katalon_installation_path


'Step 1: Ensure no C2 Browser App is running'
status = CustomKeywords.'checking_instance_of_app.instance_checking_c2_app.checking_instance'()

'If no instance will execute the further steps'
if (status == 'True') {
	'call fisrt instance of c2 application'
	def task1 = [python_path, Installation_path+'\\Test Cases\\Python_files\\Instance_app_helper\\instance_call.py'].execute()

	WebUI.delay(GlobalVariable.short_delay)

	'Step 3: Verify that GRL-C2 Browser App application'

	'Browser Initialization'
	CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

	'Step 4: Verify that GRL-C2 Browser App Server is running'
	status = CustomKeywords.'checking_instance_of_app.instance_checking_c2_app.checking_instance'()

	WebUI.comment('C2 Server Status::'+status)

	'Step 5: Connect to C2'
	WebUI.click(findTestObject('Object Repository/Connection_Setup_Objects/button_Connect'))

	'Step 6: Get Device Capability'
	def product_capability_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Product Capability'))

	'Testcase will fail if Product Capability Page not present'
	if (product_capability_sts) {
		WebUI.comment('Product Capability Page is Present')

		'To click Product Capability page'
		WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

		def task2 = [python_path, Installation_path+'\\Test Cases\\Python_files\\Instance_app_helper\\instance_call.py'].execute()

		WebUI.delay(GlobalVariable.short_delay)

		'Browser Initialization'
		CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

		WebUI.delay(GlobalVariable.long_delay)

		WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Product Capability'))

		'Browser Teardown'
		CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
	} else {
		WebUI.comment('Product Capability Page is not Present,Something went wrong ')

		WebUI.takeScreenshot()

		assert false

		'Browser Teardown'
		CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
	}
} else {
	println('C2 software has to start manually.. please run after that...')

	'Browser Teardown'
	CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
}



