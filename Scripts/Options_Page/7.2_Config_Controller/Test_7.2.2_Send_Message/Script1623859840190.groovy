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

'This step will call option panel dropdown list'
def option_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Option_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

List exp_sop_type_drpdwn = option_data.option_page_data.Sop_Type_Drpdwn

List exp_message_type_drpdwn = option_data.option_page_data.Message_Type_Drpdwn

String sop_type_locator = option_data.option_page_data.Sop_Type_Locator

String message_type_locator = option_data.option_page_data.Message_Type_Locator

WebUI.delay(10)

'Go to Options tab'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Options'))

WebUI.click(findTestObject('Object Repository/Options_Panel_Objects/Config_Controller/a_Config Controller'))

WebUI.delay(5)

'STEP 1:Verify that the Send Message panel has the following dropdown list'

'SOP Type Dropdown list with the following options'
WebUI.delay(5)

def sop_type_drpdwn = CustomKeywords.'grl_usb_pd_c2_Testcase.Options_Page.getOptionsConfigDropdownList'(findTestObject('Object Repository/Options_Panel_Objects/Config_Controller/Send_Message/button_SOP'), 
    sop_type_locator)

assert exp_sop_type_drpdwn == sop_type_drpdwn

'Message Type Dropdown list with the following options'
WebUI.delay(5)

def message_type_drpdwn = CustomKeywords.'grl_usb_pd_c2_Testcase.Options_Page.getOptionsConfigDropdownList'(findTestObject(
        'Object Repository/Options_Panel_Objects/Config_Controller/Send_Message/button_Message_Type'), message_type_locator)

assert exp_message_type_drpdwn == message_type_drpdwn

'To check following textbox SVID(0x0000) 0x FF001 '
def svid_textbox_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Options_Panel_Objects/Config_Controller/Send_Message/input_SVID(0X0000)_value'), 
    10)

assert svid_textbox_sts == true

'To check send button present or not'
def send_button_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Options_Panel_Objects/Config_Controller/Send_Message/button_Send'), 
    10)

assert send_button_sts == true


'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
