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

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

WebUI.delay(10)

'STEP 1:select setup diagram link'
WebUI.click(findTestObject('Object Repository/Connection_Setup_Objects/a_Setup Diagram'))

'5S delay to wait for setup diagram image loading'
WebUI.delay(5)

'STEP 2:To verify C2 Setup Diagram dialogue'

def setup_diagram_text_sts = WebUI.getText(findTestObject('Object Repository/Connection_Setup_Objects/strong_C2 Setup Diagram'))

println(setup_diagram_text_sts)

if(setup_diagram_text_sts=="C2 Setup Diagram") {
	WebUI.comment('Setup diagram dialogue status proper as expected::'+setup_diagram_text_sts)
}
else {
	WebUI.comment('Setup diagram dialogue status is not appear')
	assert false
}
'To take setup diagram screenshot'
WebUI.takeScreenshot()

'STEP 4:To click ok button'

WebUI.click(findTestObject('Object Repository/Connection_Setup_Objects/button_Ok'))


'STEP 5:To verify setup diagram dialogue get closed or not'
def setup_diagram_text_disable = WebUI.verifyElementNotPresent(findTestObject('Object Repository/Connection_Setup_Objects/strong_C2 Setup Diagram'),
	5)

if(setup_diagram_text_disable) {
	WebUI.comment('Setup diagram dialogue status is not there as expected::'+setup_diagram_text_disable)
}
else {
	WebUI.comment('Setup diagram dialogue still there,not able to close setup diagram link')
	assert false
}

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
