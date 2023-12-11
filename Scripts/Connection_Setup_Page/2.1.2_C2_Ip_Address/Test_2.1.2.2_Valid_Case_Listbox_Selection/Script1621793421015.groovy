
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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.support.ui.Select as Select
import java.awt.Toolkit as Toolkit
import java.awt.datatransfer.StringSelection as StringSelection
import java.awt.event.KeyEvent as KeyEvent
import java.awt.Robot as Robot
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.support.Color as Color
import org.openqa.selenium.WebDriver as WebDriver

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String cs_c2_valid_ip_address = all_data.user_config_data.CS_C2_Valid_IP_Address

String cs_c2_serial_number = all_data.user_config_data.CS_C2_Serial_Number

String cs_c2_firmware_version = all_data.user_config_data.CS_C2_Firmware_Version

String cs_c2_tester_ip_info = all_data.user_config_data.CS_C2_Tester_IP_Address_Info

String cs_c2_test_cable_calibration_sts = all_data.user_config_data.CS_C2_Test_Cable_Calibration_Status

WebDriver driver = DriverFactory.getWebDriver()

WebUI.delay(20)

'This step will click Scan Network button'
WebUI.click(findTestObject('Object Repository/Connection_Setup_Objects/button_Scan Network'))

'STEP 1:To select dropdown ip with green icon without selecting connect button'

'To get pass icon ip address list '
List pass_icon_ip_list = CustomKeywords.'grl_usb_pd_c2_Testcase.ConnectionSetup_Page.getDropdownIpWithPassIcon'()

'STEP 2:To click dropdown  pass icon ip without click connect button'
CustomKeywords.'grl_usb_pd_c2_Testcase.ConnectionSetup_Page.getValidCaseListSelection'(pass_icon_ip_list, cs_c2_valid_ip_address)


'STEP 3: To Verify Tester Details'


'To get Connection Status and Text Color'
def connection_sts=CustomKeywords.'grl_usb_pd_c2_Testcase.ConnectionSetup_Page.getConnectionStatus'()

if(connection_sts) {
	'To Verify Seriel number,Firmware Version,Tester Ip,Port Info,Calibration Status displayed in Tester Screen'
	CustomKeywords.'grl_usb_pd_c2_Testcase.ConnectionSetup_Page.getTesterDetails'(cs_c2_serial_number,cs_c2_firmware_version,cs_c2_tester_ip_info,cs_c2_test_cable_calibration_sts)
		
}
else {
	'Tester is not connected with c2 application'
	assert false
}

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)


'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()









