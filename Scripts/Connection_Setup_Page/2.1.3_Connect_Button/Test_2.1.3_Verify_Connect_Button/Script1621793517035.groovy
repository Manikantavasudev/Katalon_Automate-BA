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
import org.openqa.selenium.By as By
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper as MobileElementCommonHelper
import groovy.transform.CompileStatic as CompileStatic
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.exception.WebElementNotFoundException as WebElementNotFoundException

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

WebUI.delay(8)
'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String cs_c2_valid_ip_address = all_data.user_config_data.CS_C2_Valid_IP_Address

String cs_c2_serial_number = all_data.user_config_data.CS_C2_Serial_Number

String cs_c2_firmware_version = all_data.user_config_data.CS_C2_Firmware_Version

String cs_c2_tester_ip_info = all_data.user_config_data.CS_C2_Tester_IP_Address_Info

String cs_c2_test_cable_calibration_sts = all_data.user_config_data.CS_C2_Test_Cable_Calibration_Status

'STEP 1:To check connect button enable state'

'This step will Verify Connect Button availability in Connection Setup Panel'
def connect_button_status = WebUI.verifyElementPresent(findTestObject('Object Repository/Connection_Setup_Objects/button_Connect'), 
    10)

'Testcase will fail if Connect button not present'
if (connect_button_status) {
    WebUI.comment('Connect button is Present  under Connection Setup Panel')
} else {
    WebUI.comment('Connect button is not there under Connection Setup Panel')

    assert false
}

'STEP 2:Input the valid Ip address'
WebUI.click(findTestObject('Object Repository/Connection_Setup_Objects/i_Clear_Ip'))

WebUI.sendKeys(findTestObject('Object Repository/Connection_Setup_Objects/input_C2_Ip_Address_Textbox'), cs_c2_valid_ip_address)

'Disable the Smart Wait function when it\'s enabled by  default in project settings.'
WebUI.disableSmartWait()

'STEP 3 : To click Connect Button'
WebUI.click(findTestObject('Object Repository/Connection_Setup_Objects/button_Connect'))

'STEP 4:To check Scan Network button disable state'
def scan_btn_disable_sts = WebUI.getAttribute(findTestObject('Object Repository/Connection_Setup_Objects/button_Scan Network'), 
    'disabled')

'Testcase will fail if Scan button is not in disable state'
if (scan_btn_disable_sts) {
    WebUI.comment('Scan button disabled while connect button is loading')
} else {
    WebUI.comment('Scan Button is enabled while  connect button is loading')

    assert false
}

'STEP 5:To check  Update Firmware  button disable state'
def firmware_btn_disable_sts = WebUI.getAttribute(findTestObject('Object Repository/Connection_Setup_Objects/button_Update Firmware'), 
    'disabled')

'Testcase will fail if Update firmware button is not in disable state'
if (firmware_btn_disable_sts) {
    WebUI.comment('Update firmware button disabled while scanNetwork is loading')
} else {
    WebUI.comment('Update firmware is enabled while scanNetwork is loading')

    assert false
}

'STEP 6:To check Eload Firmware button disable state'
def elod_btn_disable_sts = WebUI.getAttribute(findTestObject('Object Repository/Connection_Setup_Objects/button_Update Eload Firmware'), 
    'disabled')

'Testcase will fail if Elode button is not in disable state'
if (elod_btn_disable_sts) {
    WebUI.comment('Elode button disabled while scanNetwork is loading')
} else {
    WebUI.comment('Elode Button is enabled while scanNetwork is loading')

    assert false
}

'STEP 7: This delay is specified for handling the Scan Network loading'
WebUI.delay(40)

'STEP 8:To check Scan Network button enable state'
def scan_btn_enable_sts = WebUI.verifyElementClickable(findTestObject('Object Repository/Connection_Setup_Objects/button_Scan Network'))

'Testcase will fail if Scan Network button is not in enable state'
if (scan_btn_enable_sts) {
    WebUI.comment('Scan Network button button is Enable state')
} else {
    WebUI.comment('Scan Network button Button is not in Enable state')

    assert false
}

'STEP 9:To check  Update Firmware  button enable state'
//boolean  firmware_btn_enable_sts = WebUI.verifyElementClickable(findTestObject('Object Repository/Connection_Setup_Objects/button_Update Firmware'))

'Testcase will fail if Scan Network button is not in enable state'
if ( WebUI.verifyElementClickable(findTestObject('Object Repository/Connection_Setup_Objects/button_Update Firmware'))) {
    WebUI.comment('Update Firmware button button is Enable state')
} else {
    WebUI.comment(' Update Firmware button Button is not in Enable state')

    assert false
}

'STEP 10:To check Eload Firmware button enable state'
def elode_btn_enable_sts = WebUI.verifyElementClickable(findTestObject('Object Repository/Connection_Setup_Objects/button_Update Eload Firmware'))

'Testcase will fail if Scan Network button is not in enable state'
if (elode_btn_enable_sts) {
    WebUI.comment('Elode button button is Enable state')
} else {
    WebUI.comment('Elode button Button is not in Enable state')

    assert false
}

'STEP 11:To Validate the Tester details'

'To get Connection Status and Text Color'
def connection_sts = CustomKeywords.'grl_usb_pd_c2_Testcase.ConnectionSetup_Page.getConnectionStatus'()

if (connection_sts) {
    'To Verify Seriel number,Firmware Version,Tester Ip,Port Info,Calibration Status displayed in Tester Screen'
    CustomKeywords.'grl_usb_pd_c2_Testcase.ConnectionSetup_Page.getTesterDetails'(cs_c2_serial_number, cs_c2_firmware_version, cs_c2_tester_ip_info, cs_c2_test_cable_calibration_sts)
} else {
    'Tester is not connected with c2 application'
    assert false
}

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()



