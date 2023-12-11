import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import org.openqa.selenium.By as By
CustomKeywords.'custom.Logger.CustomLoggerFactory.customizeLogger4CommentKeyword'()

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String pc_c2_project_name = all_data.user_config_data.PC_C2_Project_Name


WebUI.comment('*****************************************************************************************************************************************************'+'\n')

WebUI.comment('TestcaseName:: '+'Test_3.1.1_Project_Name'+'\n')

'To Check Product Capability Page Availability'

def product_capability_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

'Testcase will fail if Product Capability Page not present'
if (product_capability_sts) {
	WebUI.comment('Product Capability Page is Present'+'\n')	
	
} else {
	WebUI.comment('Product Capability Page is not Present,Something went wrong '+'\n')
	assert false
}

'To click Product Capability page'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

'STEP 1:Project Name edit control'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/input_Project Name'))

'This step will clear the Existing Project Name'

WebUI.clearText(findTestObject('Object Repository/Product_Capability_Objects/input_Project Name'))

'STEP 2:To input the project Name'

WebUI.setText(findTestObject('Product_Capability_Objects/input_Project Name'), pc_c2_project_name)

'STEP 3:To  Check Editbox Border Blink'

def editbox_blinking_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/project_Name_With_Blinking_editbox'))

'Testcase will fail if Editbox is not Blinking'
if (editbox_blinking_sts) {
	WebUI.comment('Editbox Blinking is present'+'\n')
} else {
	WebUI.comment('Editbox Blinking is not present'+'\n')
	assert false
}

'STEP 4:To Check Save Button Availability'

def save_button_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/button_Save'))

'Testcase will fail if Save button is not present'
if (save_button_sts) {
	'STEP 5:To click Save Button'
	WebUI.comment('Save button is present'+'\n')
	WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/button_Save'))
} else {
	WebUI.comment('Save button is not present'+'\n')
	assert false
}

'STEP 6:To Check Editbox Blinking State '

if (WebUI.verifyElementNotPresent(findTestObject('Object Repository/Product_Capability_Objects/project_Name_With_Blinking_editbox'), 
    5)) {
    WebUI.comment('Blinking Editbox not there after click the Save Button'+'\n')
} else {
    WebUI.comment('Blinking Editbox is there Something went wrong while clicking the Save Button'+'\n')
}

WebUI.comment('********* Test_3.1.1_Project_Name Execution Ended *********'+'\n')

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)


'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()

