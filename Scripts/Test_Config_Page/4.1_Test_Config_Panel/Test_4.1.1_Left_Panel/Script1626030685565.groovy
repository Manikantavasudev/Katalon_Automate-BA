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
import java.awt.Robot as Robot
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import org.openqa.selenium.By as By
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper as MobileElementCommonHelper
import groovy.transform.CompileStatic as CompileStatic
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.exception.WebElementNotFoundException as WebElementNotFoundException
import org.openqa.selenium.support.ui.Select as Select
import java.nio.file.*


'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

'To read search text data '
def all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

def tc_certification_dropdown_list = all_data.user_config_data.TC_Certification_Dropdown_List

String Installation_path = all_data.user_config_data.Katalon_installation_path

'To delete vif_file_validator Log file for appending the new data'
File validator_log_file = new File(Installation_path + '\\vif_file_validator.log')

if (validator_log_file.exists()) {
	validator_log_file.delete()
}

validator_log_file.createNewFile()

InputStream validator_log_file_obj = new FileInputStream(validator_log_file)

CustomKeywords.'custom.Logger.CustomLoggerFactory.customizeLogger4CommentKeyword'()

'ToolTip variables'
String passed_icon_tooltip = 'Click here to select passed test cases'

String failed_icon_tooltip = 'Click here to select failed test cases'

String incomplete_icon_tooltip = 'Click here to select incompleted test cases'

String warning_icon_tooltip = 'Click here to select test cases with warnings'

String clear_icon_tooltip = 'Click here to Clear test selection'

String pc_sleep_setting_tooltip = 'Please change PC sleep setting'

WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Test Config'))

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

WebUI.comment('TestcaseName:: '+'Test_4.1.1_Left_Panel'+'\n')

'STEP 1:To check Passed TestCase icon'
def pass_testcase_icon_sts = WebUI.verifyElementVisible(findTestObject('Test_Config_Panel/Testconfig_icons/img_Passed_Testcases'))

WebUI.comment('pass_testcase_icon_sts::' + pass_testcase_icon_sts+'\n')

'STEP 2:To check Failed TestCase icon'
def fail_testcase_icon_sts = WebUI.verifyElementVisible(findTestObject('Test_Config_Panel/Testconfig_icons/img_Failed_Testcases'))

WebUI.comment('fail_testcase_icon_sts::' + fail_testcase_icon_sts+'\n')

'STEP 3:To check Incomplete Test Case icon'
def incomplete_testcase_icon_sts = WebUI.verifyElementVisible(findTestObject('Test_Config_Panel/Testconfig_icons/img_Incomplete_Testcases'))

WebUI.comment('incomplete_testcase_icon_sts::' + incomplete_testcase_icon_sts+'\n')

'STEP 4:To check Warning TestCase icon'
def warnings_testcase_icon_sts = WebUI.verifyElementVisible(findTestObject('Test_Config_Panel/Testconfig_icons/img_Warnings_Testcases'))

WebUI.comment('warnings_testcase_icon_sts::' + warnings_testcase_icon_sts+'\n')

'STEP 5:To check Clear TestCase icon'
def filter_testcase_icon_sts = WebUI.verifyElementVisible(findTestObject('Test_Config_Panel/Testconfig_icons/img_Filter_Clear_Testcases'))

WebUI.comment('filter_testcase_icon_sts::' + filter_testcase_icon_sts+'\n')

'STEP 6:To check Start Execution icon'
def start_execution_btn_sts = WebUI.getAttribute(findTestObject('Test_Config_Panel/Testconfig_icons/button_StartExecution_Disable'),
	'disabled')

WebUI.comment('start_execution_btn_sts::' + start_execution_btn_sts+'\n')

'STEP 7:To check Help icon'
def sleep_settings_icon_sts = WebUI.verifyElementVisible(findTestObject('Test_Config_Panel/Testconfig_icons/img_Sleep_Settings'))

WebUI.comment('sleep_settings_icon_sts::' + sleep_settings_icon_sts+'\n')

'STEP 8:To check Search textbox presence'
def input_search_textbox_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Test_Config_Panel/input_Search_Textbox'))

WebUI.comment('input_search_textbox_sts::' + input_search_textbox_sts+'\n')

'STEP 9:To check Expand Testlist Checkbox'
def expand_testlist_checkbox_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Test_Config_Panel/Expand_Test_List'))

WebUI.comment('expand_testlist_checkbox_sts::' + expand_testlist_checkbox_sts+'\n')

'STEP 10:To check Selected Test lebel presence'
def selected_testlist_count_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Test_Config_Panel/Test_list_count'))

WebUI.comment('selected_testlist_count_sts::' + selected_testlist_count_sts+'\n')

'STEP 11:To check C2 Testcase checkbox'
def c2_testcase_checkbox_sts = WebUI.verifyElementVisible(findTestObject('Test_Config_Panel/Testconfig_icons/span_C2_Testcases_Checkbox'))

WebUI.comment('c2_testcase_checkbox_sts::' + c2_testcase_checkbox_sts+'\n')

if ((((((((((pass_testcase_icon_sts && fail_testcase_icon_sts) && incomplete_testcase_icon_sts) && warnings_testcase_icon_sts) &&
filter_testcase_icon_sts) && sleep_settings_icon_sts) && start_execution_btn_sts) && input_search_textbox_sts) && selected_testlist_count_sts) &&
c2_testcase_checkbox_sts) && expand_testlist_checkbox_sts) {
	WebUI.comment('Test Config labels are Present'+'\n')
} else {
	assert false
}

'STEP 12:To check Certification Dropdown List'
def certification_list = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.certificationDropdownList'()

assert certification_list == tc_certification_dropdown_list

'STEP 13:To check Following ToolTip Info details'

'To Check Passed Testcase Tooltip info'
def exp_pass_icon_tooltip_sts = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getToolTipInfoValues'(findTestObject(
		'Object Repository/Test_Config_Panel/Testconfig_icons/img_Passed_Testcases'), passed_icon_tooltip)

assert exp_pass_icon_tooltip_sts == passed_icon_tooltip

'To Check Failed Testcase Tooltip info'
def exp_fail_icon_tooltip_sts = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getToolTipInfoValues'(findTestObject(
		'Object Repository/Test_Config_Panel/Testconfig_icons/img_Failed_Testcases'), failed_icon_tooltip)

assert exp_fail_icon_tooltip_sts == failed_icon_tooltip

'To Check Incomplete Testcase Tooltip info'
def exp_incomplete_icon_tooltip_sts = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getToolTipInfoValues'(findTestObject(
		'Object Repository/Test_Config_Panel/Testconfig_icons/img_Incomplete_Testcases'), incomplete_icon_tooltip)

assert exp_incomplete_icon_tooltip_sts == incomplete_icon_tooltip

'To Check Warning Testcase Tooltip info'
def exp_warning_icon_tooltip_sts = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getToolTipInfoValues'(findTestObject(
		'Object Repository/Test_Config_Panel/Testconfig_icons/img_Warnings_Testcases'), warning_icon_tooltip)

assert exp_warning_icon_tooltip_sts == warning_icon_tooltip

'To Check Clear Testcase Tooltip info'
def exp_clear_icon_tooltip_sts = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getToolTipInfoValues'(findTestObject(
		'Object Repository/Test_Config_Panel/Testconfig_icons/img_Filter_Clear_Testcases'), clear_icon_tooltip)

assert exp_clear_icon_tooltip_sts == clear_icon_tooltip

WebUI.delay(GlobalVariable.short_delay)

'To Check PC Sleep Settings Tooltip info'
def exp_pc_settings_tooltip_sts = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getToolTipInfoValues'(findTestObject(
		'Object Repository/Test_Config_Panel/Testconfig_icons/img_Sleep_Settings'), pc_sleep_setting_tooltip)

assert exp_pc_settings_tooltip_sts == pc_sleep_setting_tooltip

WebUI.comment('********* Test_4.1.1_Left_Panel  Execution Ended *********'+'\n')

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()


