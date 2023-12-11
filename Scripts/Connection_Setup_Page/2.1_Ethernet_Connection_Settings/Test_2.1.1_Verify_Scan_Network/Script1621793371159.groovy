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
import internal.GlobalVariable
import org.openqa.selenium.By
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import groovy.transform.CompileStatic
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.exception.WebElementNotFoundException


'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()


'STEP 1:This step will Verify Scan Network Button availability in Connection Setup Panel'
def Scan_button_status = WebUI.verifyElementPresent(findTestObject('Connection_Setup_Objects/button_Scan Network'), 10)

'Testcase will fail if scanNetwork button not present'
if (Scan_button_status) {
	println('ScanNetwork button is Present  under Connection Setup Panel')
} else {
	println('ScanNetwork button is not there under Connection Setup Panel')

	assert false
}


'STEP 2:This step will Verify Scan Network Button is clickable or not '
def Scan_button_clickable = WebUI.verifyElementClickable(findTestObject('Object Repository/Connection_Setup_Objects/button_Scan Network'))

'Testcase will fail if scanNetwork button is not clickable'
if (Scan_button_clickable) {
	println('ScanNetwork button is clickable')
} else {
	println('ScanNetwork button is not clickable')

	assert false
}


'STEP 4:This step will click Scan Network button'
WebUI.click(findTestObject('Object Repository/Connection_Setup_Objects/button_Scan Network'))

'Disable the Smart Wait function when it\'s enabled by  default in project settings.'
WebUI.disableSmartWait()


'STEP 5:This step will Verify Scan Network loading status'
def loading_icon_status = WebUI.verifyElementVisible(findTestObject('Object Repository/Connection_Setup_Objects/div_Scan Network_loading'))

'Testcase will fail if loading icon is not Present'
if (loading_icon_status) {
	WebUI.comment('Loading icon is Present after the scanNetwork click')
} else {
	WebUI.comment('Loading icon is not Present')

	assert false
}

'STEP 6:This step will Verify Connect Button disable status'

def connect_btn_disable_sts = WebUI.getAttribute(findTestObject('Object Repository/Connection_Setup_Objects/button_Connect'),
	'disabled')

'Testcase will fail if connect button is not in disable state'
if (connect_btn_disable_sts) {
	WebUI.comment('Connect button disabled while scanNetwork is loading')
} else {
	WebUI.comment('Connect Button is enabled while scanNetwork is loading')

	assert false
}

'STEP 8:This delay is specified for handling the Scan Network loading'
WebUI.delay(40)


'STEP 9:This step will Verify Connect button clickable status'
def connect_btn_enable = WebUI.verifyElementClickable(findTestObject('Object Repository/Connection_Setup_Objects/button_Connect'))

'Testcase will fail if connect button is not in enable state'
if (connect_btn_enable) {
	WebUI.comment('Connect button is Enable state')
} else {
	WebUI.comment('Connect Button is not in Enable state')

	assert false
}


'STEP 11:This step will Verify Scan Network Dropdown List Expanded State'
CustomKeywords.'grl_usb_pd_c2_Testcase.ConnectionSetup_Page.scanNetworkDropdown'()


'STEP 12:This step will list out ip address with pass icon'

CustomKeywords.'grl_usb_pd_c2_Testcase.ConnectionSetup_Page.getDropdownIpWithPassIcon'()


'STEP 13:This step will check Loading icon Availability'
def loading_icon_status_notpresent = WebUI.verifyElementNotPresent(findTestObject('Object Repository/Connection_Setup_Objects/div_Scan Network_loading'),
	60)

if (loading_icon_status_notpresent) {
	WebUI.comment('Loading icon is not Present')
} else {
	WebUI.comment('Loading icon is still there in Connection Setup Panel')

	assert false
}

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()




