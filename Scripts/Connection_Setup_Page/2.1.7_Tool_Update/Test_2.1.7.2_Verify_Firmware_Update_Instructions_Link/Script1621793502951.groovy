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

'STEP 1:select Update Firmware Instructions  link'
WebUI.click(findTestObject('Object Repository/Connection_Setup_Objects/a_Firmware Update Instructions'))

'5S delay to wait for Update Firmware Instructions image loading'
WebUI.delay(5)

'STEP 2:To verify Update C2 Firmware Instructions dialogue'

def update_firmware_text_sts = WebUI.getText(findTestObject('Connection_Setup_Objects/strong_C2_Update_Firmware_dialogue'))

println(update_firmware_text_sts)

if(update_firmware_text_sts=="Update C2 Firmware Instruction") {
	WebUI.comment('update c2 firmware instruction dialogue status proper as expected::'+update_firmware_text_sts)
}
else {
	WebUI.comment('update c2 firmware instruction dialogue is not appear')
	assert false
}
'To take update c2 firmware instruction diagram screenshot'
WebUI.takeScreenshot()

'STEP 3:To click ok button'

WebUI.click(findTestObject('Object Repository/Connection_Setup_Objects/button_Ok'))


'STEP 4:To verify update c2 firmware instruction dialogue get closed or not'
def update_firmware_text_disable = WebUI.verifyElementNotPresent(findTestObject('Connection_Setup_Objects/strong_C2_Update_Firmware_dialogue'),
	5)

if(update_firmware_text_disable) {
	WebUI.comment('Update C2 Firmware Instructions status is not there as expected::'+update_firmware_text_disable)
}
else {
	WebUI.comment('Update C2 Firmware Instructions still there,not able to close setup diagram link')
	assert false
}

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()


