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

List exp_app_mode_drpdwn = option_data.option_page_data.App_Mode_Drpdwn

List exp_dp_aux_sniffer_drpdwn = option_data.option_page_data.DP_AUX_Sniffer_Drpdwn

List exp_port_type_drpdwn = option_data.option_page_data.Port_Type_Drpdwn

List exp_controller_mode_drpdwn = option_data.option_page_data.Controller_Mode_Drpdwn

List exp_test_cable_type_drpdwn = option_data.option_page_data.Test_Cable_Type_Drpdwn

List exp_pd_spec_type_drpdwn = option_data.option_page_data.PD_Spec_Type_Drpdwn

List exp_cable_emulation_drpdwn = option_data.option_page_data.Cable_Emulation_Drpdwn

List exp_rp_level_drpdwn = option_data.option_page_data.Rp_Level_Drpdwn

WebUI.delay(GlobalVariable.long_delay)

'STEP 1:Go to Options tab'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Options'))

'STEP 2:Select Config Controller Tab'
WebUI.click(findTestObject('Object Repository/Options_Panel_Objects/Config_Controller/a_Config Controller'))

WebUI.delay(GlobalVariable.short_delay)

'STEP 3:Verify that the following controls are present in Configure Panel'

'App Mode Dropdown list with the following options'
String app_mode_locator = option_data.option_page_data.App_Mode_Locator

def app_mode_drpdwn = CustomKeywords.'grl_usb_pd_c2_Testcase.Options_Page.getOptionsConfigDropdownList'(findTestObject('Object Repository/Options_Panel_Objects/Config_Controller/Configure_Panel/button_App Mode'), 
    app_mode_locator)

assert exp_app_mode_drpdwn == app_mode_drpdwn

'DP Aux Mode Sniffer connected to Dropdown list with the following options'
String dp_aux_sniffer_locator = option_data.option_page_data.DP_AUX_Sniffer_Locator

def dp_aux_sniffer_drpdwn = CustomKeywords.'grl_usb_pd_c2_Testcase.Options_Page.getOptionsConfigDropdownList'(findTestObject(
        'Object Repository/Options_Panel_Objects/Config_Controller/Configure_Panel/button_DP_AUX_Sniffer'), dp_aux_sniffer_locator)

assert exp_dp_aux_sniffer_drpdwn == dp_aux_sniffer_drpdwn

'port Type Dropdown list with the following options'
String port_type_locator = option_data.option_page_data.Port_Type_Locator

def port_type_drpdwn = CustomKeywords.'grl_usb_pd_c2_Testcase.Options_Page.getOptionsConfigDropdownList'(findTestObject(
        'Object Repository/Options_Panel_Objects/Config_Controller/Configure_Panel/button_Port_Type'), port_type_locator)

assert exp_port_type_drpdwn == port_type_drpdwn

'Controller Mode Dropdown list with the following options'
String controller_mode_locator = option_data.option_page_data.Controller_Mode_Locator

def controller_mode_drpdwn = CustomKeywords.'grl_usb_pd_c2_Testcase.Options_Page.getOptionsConfigDropdownList'(findTestObject(
        'Object Repository/Options_Panel_Objects/Config_Controller/Configure_Panel/button_Controller_Mode'), controller_mode_locator)

assert exp_controller_mode_drpdwn == controller_mode_drpdwn

'Test Cable Type Dropdown list with the following options'
String test_cable_type_locator = option_data.option_page_data.Test_Cable_Type_Locator

def test_cable_type_drpdwn = CustomKeywords.'grl_usb_pd_c2_Testcase.Options_Page.getOptionsConfigDropdownList'(findTestObject(
        'Object Repository/Options_Panel_Objects/Config_Controller/Configure_Panel/button_Test_Cable_Type'), test_cable_type_locator)

assert exp_test_cable_type_drpdwn == test_cable_type_drpdwn

'PD Spec Type Dropdown list with the following options'
String pd_spec_type_locator = option_data.option_page_data.PD_Spec_Type_Locator

def pd_spec_type_drpdwn = CustomKeywords.'grl_usb_pd_c2_Testcase.Options_Page.getOptionsConfigDropdownList'(findTestObject(
        'Object Repository/Options_Panel_Objects/Config_Controller/Configure_Panel/button_PD_Spec_Type'), pd_spec_type_locator)

assert exp_pd_spec_type_drpdwn == pd_spec_type_drpdwn

'Cable Emulation Dropdown list with the following options'
String cable_emulation_locator = option_data.option_page_data.Cable_Emulation_Locator

def cable_emulation_drpdwn = CustomKeywords.'grl_usb_pd_c2_Testcase.Options_Page.getOptionsConfigDropdownList'(findTestObject(
        'Object Repository/Options_Panel_Objects/Config_Controller/Configure_Panel/button_Cable_Emulation'), cable_emulation_locator)

assert exp_cable_emulation_drpdwn == cable_emulation_drpdwn

'Rp Level Dropdown list with the following options'
String rp_level_locator = option_data.option_page_data.Rp_Level_Locator

def rp_level_drpdwn = CustomKeywords.'grl_usb_pd_c2_Testcase.Options_Page.getOptionsConfigDropdownList'(findTestObject('Object Repository/Options_Panel_Objects/Config_Controller/Configure_Panel/button_Rp_Level'), 
    rp_level_locator)

assert exp_rp_level_drpdwn == rp_level_drpdwn

'To check Apply button present or not'
def apply_button_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Options_Panel_Objects/Config_Controller/Configure_Panel/button_Apply'),
	10)

assert apply_button_sts == true

'To check Attach button present or not'
def attach_button_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Options_Panel_Objects/Config_Controller/Configure_Panel/button_Attach'),
	10)

assert attach_button_sts == true

'To check detach button present or not'
def detach_button_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Options_Panel_Objects/Config_Controller/Configure_Panel/button_Detach'),
	10)

assert detach_button_sts == true


'To check VBUS,CC1,CC2 checkbox present or not'

def vbus_checkbox_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Options_Panel_Objects/Config_Controller/Configure_Panel/input_VBUS_checkbox'),
	10)

def cc1_chceckbox_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Options_Panel_Objects/Config_Controller/Configure_Panel/input_CC1_checkbox'),
	10)
def cc2_checkbox_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Options_Panel_Objects/Config_Controller/Configure_Panel/input_CC2_checkbox'),
	10)
if(vbus_checkbox_sts&&cc1_chceckbox_sts&&cc2_checkbox_sts) {
	
	WebUI.comment('vbus_checkbox_sts:: '+vbus_checkbox_sts +'*****'+'cc1_chceckbox_sts:: '+cc1_chceckbox_sts+'*****'+'cc2_checkbox_sts::'+cc2_checkbox_sts)
}

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()




















