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

'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String cs_c2_valid_ip_address = all_data.user_config_data.CS_C2_Valid_IP_Address

String cs_c2_serial_number = all_data.user_config_data.CS_C2_Serial_Number

String cs_c2_firmware_version = all_data.user_config_data.CS_C2_Firmware_Version

String cs_c2_tester_ip_info = all_data.user_config_data.CS_C2_Tester_IP_Address_Info

String cs_c2_test_cable_calibration_sts = all_data.user_config_data.CS_C2_Test_Cable_Calibration_Status

'STEP 1 : Input IP address that is displayed on the Tester Display Screen'
WebUI.click(findTestObject('Object Repository/Connection_Setup_Objects/i_Clear_Ip'))

WebUI.sendKeys(findTestObject('Object Repository/Connection_Setup_Objects/input_C2_Ip_Address_Textbox'), cs_c2_valid_ip_address)

'STEP 2 : To click Connect Button'
WebUI.click(findTestObject('Object Repository/Connection_Setup_Objects/button_Connect'))

'STEP 3: To Verify Tester Details'
WebUI.delay(40)

'To get Connection Status and Text Color'
def connection_sts = CustomKeywords.'grl_usb_pd_c2_Testcase.ConnectionSetup_Page.getConnectionStatus'()

if (connection_sts) {
    'To Verify Seriel number,Firmware Version,Tester Ip,Port Info,Calibration Status displayed in Tester Screen'
    CustomKeywords.'grl_usb_pd_c2_Testcase.ConnectionSetup_Page.getTesterDetails'(cs_c2_serial_number, cs_c2_firmware_version, 
        cs_c2_tester_ip_info, cs_c2_test_cable_calibration_sts)
} else {
    'Tester is not connected with c2 application'
    assert false
}

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()



