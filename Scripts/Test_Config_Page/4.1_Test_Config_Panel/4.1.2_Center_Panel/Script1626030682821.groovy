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
import org.openqa.selenium.WebDriver as WebDriver

CustomKeywords.'custom.Logger.CustomLoggerFactory.customizeLogger4CommentKeyword'()

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

WebDriver driver = DriverFactory.getWebDriver()

'Expected popup timeout tooltip info'
exp_popup_timer_tooltip = popup_timer_tooltip_info

'Expected enable debug mode tooltip info'
exp_enable_debug_tooltip = enable_debug_tooltip_info

WebUI.comment('*****************************************************************************************************************************************************' + 
    '\n')

WebUI.comment(('TestcaseName:: ' + 'Test_4.1.2_Center_Panel') + '\n')

'Verify the presence of the following controls and hover text in the center panel'

'To click Testconfig page'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Test Config'))

WebUI.delay(GlobalVariable.short_delay)

'STEP 1 :Time out Pop-up Messages check box'
def timer_popup_val = GlobalVariable.timeout_popup_val

'To check timeout popup checkbox Availability and popup timer textbox'
try {
    def timeout_popup_checkbox_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Test_Config_Panel/Test_Execution/input_Timeout_Popup_Checkbox'), 
        10)

    def timeout_popup_input_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Test_Config_Panel/Test_Execution/input_Popup_Timer'), 
        10)

    if (timeout_popup_checkbox_sts && timeout_popup_input_sts) {
        WebUI.comment((((('Timeout Popup checkbox sts :: ' + timeout_popup_checkbox_sts) + ' **** ') + 'Timeout Popup Textbox sts ::  ') + 
            timeout_popup_input_sts) + '\n')
    }
	
	
}
catch (Exception e) {
    assert false
} 

'STEP 2&3: Re Run Selected Tests Checkbox and check Rerun Textbox edit control'

'To check timeout popup checkbox Availability and popup timer textbox'
try {
    def rerun_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Test_Config_Panel/Test_Execution/Center_Panel/input_Rerun_Selected_Tests_Checkbox'), 
        10)

    def rerun_textbox_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Test_Config_Panel/Test_Execution/Center_Panel/input_Rerun_Selected_Textbox'), 
        10)

    if (rerun_sts && rerun_textbox_sts) {
        WebUI.comment((((('Rerun Selected Tests checkbox sts :: ' + rerun_sts) + ' **** ') + 'Rerun Selected Tests Textbox sts :: ') + 
            rerun_textbox_sts) + '\n')
    }
}
catch (Exception e) {
	WebUI.comment('Popup Timer is not in enabled state')
} 

'STEP 4:To check Timeout Popup Help Icon Tooltip Info '
try {
    WebUI.doubleClick(findTestObject('Object Repository/Test_Config_Panel/Test_Execution/Center_Panel/Popup_Timer_Helpicon'))

    WebUI.delay(GlobalVariable.long_delay)

    def tooltip_help_icon_info = driver.findElement(By.xpath('//div[@role=\'tooltip\']')).getText()

    WebUI.comment(('Popup Timer Help Icon::  ' + tooltip_help_icon_info) + '\n')

    assert exp_popup_timer_tooltip == tooltip_help_icon_info

    'STEP 5:Check Enable Debug mode checkbox'
    def enable_debug_mode_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Test_Config_Panel/Test_Execution/Center_Panel/input_Enable_Debug_Mode_Checkbox'), 
        10)

    assert enable_debug_mode_sts == true

    'STEP 6:Enable Debug Mode Tooltip Info'
    WebUI.doubleClick(findTestObject('Object Repository/Test_Config_Panel/Test_Execution/Center_Panel/Enable_Debug_Mode_Helpicon'))

    WebUI.delay(GlobalVariable.long_delay)

    def enable_debug_mode_info = driver.findElement(By.xpath('//div[@role=\'tooltip\']')).getText()

    WebUI.comment(('Enable Debug Tooltip info:: ' + enable_debug_mode_info) + '\n')

    assert exp_enable_debug_tooltip == enable_debug_mode_info
}
catch (Exception e) {
    WebUI.comment('Tooltip Info is not present as Expected')
} 

'STEP 7&8: To check MOI Configurations label & To check *Select test case for MOI Configurations'
try {
    def moi_config_label_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Test_Config_Panel/Testconfig_icons/p_Moi_Configuration_Label'), 
        10)

    def selected_test_label_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Test_Config_Panel/Testconfig_icons/p_Selected_Testcount_label'), 
        10)

    if (moi_config_label_sts && selected_test_label_sts) {
        WebUI.comment((((('Moi Configuration label  sts :: ' + moi_config_label_sts) + ' **** ') + 'Selected Testcase for moi labbel sts ::  ') + 
            selected_test_label_sts) + '\n')
    }
}
catch (Exception e) {
    WebUI.comment('MOI Configurations and Select Testcases label is not present')

    assert false
} 

WebUI.comment('********* Test_4.1.2_Center_Panel  Execution Ended *********' + '\n')

WebUI.comment('*****************************************************************************************************************************************************' + 
    '\n')

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()




