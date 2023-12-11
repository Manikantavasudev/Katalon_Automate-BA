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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.By as By
import java.nio.file.*
import java.io.IOException as IOException
import java.io.File as File
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.support.Color as Color
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
org.openqa.selenium.WebElement
CustomKeywords.'custom.Logger.CustomLoggerFactory.customizeLogger4CommentKeyword'()

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

WebUI.delay(GlobalVariable.short_delay)

'To initialize driver object'
WebDriver driver = DriverFactory.getWebDriver()

'To read vif file data '
def all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String pc_xml_file_folder_path = all_data.user_config_data.PC_XML_File_Folder_Path

'To read MOI Config data '
def moi_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Moi_Configuration_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

List filter_selection_testcases = moi_data.tc_moi_data.Filter_Section_Testcases

List expected_moi_list = moi_data.tc_moi_data.expected_moi_list

List remove_subtest_for_count = moi_data.tc_moi_data.remove_subtest_for_count

def vif_file_name = moi_data.tc_moi_data.Filter_Section_Vif_File_Path

'To read selected checkbox testcase name through filter buttons'
String selected_checkbox_locator = '//li[@class="rc-tree-treenode-switcher-close rc-tree-treenode-checkbox-checked"]/span[3]/span'

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

WebUI.comment('TestcaseName:: '+'Test_4.1.1.18_Filter_Selection'+'\n')

'To click Product Capability page'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

'To combine user config vif file path'
Path vif_file_path = Paths.get((pc_xml_file_folder_path + '\\') + vif_file_name)

'STEP 2:To load the xml file using load xml file button'
WebUI.callTestCase(findTestCase('Load_Xml_File_RobotFramework'), [('key1') : vif_file_path], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(GlobalVariable.short_delay)

'To click Testconfig page'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Test Config'))

'To click All Supported Certification dropdown'
CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectCertificationDropdown'(findTestObject('Object Repository/Test_Config_Panel/Certification_drop_down'), 
    findTestObject('Object Repository/Test_Config_Panel/Page_GRL - USB PD C2/a_All Supported Certifications'))

WebUI.delay(GlobalVariable.short_delay)

'To verify checkbox unselected state'
def checkbox_unselected_sts = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getCheckBoxUnSelectedStatus'()

assert checkbox_unselected_sts == true

'STEP 3: To Click  Following Testcases TD.PD.VNDI3.E11 PR Swap Sink,TD.PD.LL.E1 GoodCRC Timing,SPT.1 Load Test,QC.BC.SNK.1 Initial Power-up Test - Weak Battery '
CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectListOfTestCase'(filter_selection_testcases)

'STEP 4 :Click Start Execution Button'
WebUI.click(findTestObject('Object Repository/Test_Config_Panel/button_Start Execution'))

'STEP 5&6 :Verify that Results Panel gets selected & Wait for Execution Competed'
def run_status

try {
    run_status = true

    while (run_status) {
        'To check Unlive button status'
        def sts = driver.findElement(By.xpath('//button/img[@src=\'../../images/chartIcons/PNG/Live.png\']'))

        if (sts) {
            WebUI.delay(GlobalVariable.short_delay)

            run_status = true
        }
    }
}
catch (Exception e) {
    run_status = false
} 

'STEP 7:Verify the selected Testcases Results:'
def testcase_execution_result = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.readTestCaseExecutionResult'(expected_moi_list, 
    remove_subtest_for_count)

WebUI.comment('testcase_execution_result::   ' + testcase_execution_result+'\n')

'STEP 8:To click Testconfig page'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Test Config'))

'STEP 9:'
List testname_list_without_result = []

for (String testname_and_result : testcase_execution_result) {
    if (((testname_and_result.contains('pass') || testname_and_result.contains('fail')) || testname_and_result.contains(
        'incomplete')) || testname_and_result.contains('warning')) {
        String testname_without_result = testname_and_result

        String[] individual_testname = testname_without_result.split(':')

        testname_list_without_result.add((individual_testname[1]).trim())
    }
}

println(testname_list_without_result)

for (String testname_and_result : testcase_execution_result) {
    if (testname_and_result.contains('pass')) {
        WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Testconfig_icons/img_Passed_Testcases'))

        CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectedFilterButtonBgColor'(findTestObject('Object Repository/Test_Config_Panel/Filter_Section/button_Passed_icon_background_clr'))

        String[] individual_testname = testname_and_result.split(':')

        WebUI.delay(GlobalVariable.short_delay)

        List sel_checkbox_testname = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.filterSectionCheckboxSts'(selected_checkbox_locator)

        if (sel_checkbox_testname.contains((individual_testname[1]).trim())) {
            WebUI.comment('Filter Section buttons are Working as Expected   ****   Here is passed Testcase name::  ' + (individual_testname[
                1])+'\n')
        } else {
            assert false
        }
        
        WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Testconfig_icons/img_Passed_Testcases'))

        def unselected_filter_btn_clr = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.unselectedFilterButtonBgColor'(findTestObject('Object Repository/Test_Config_Panel/Testconfig_icons/img_Passed_Testcases'))

		assert graycolor ==unselected_filter_btn_clr
		
        'To check the pass testcase background colour after double click'
    } else if (testname_and_result.contains('fail') || testname_and_result.contains('warning')) {
        WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Testconfig_icons/img_Failed_Testcases'))

        CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectedFilterButtonBgColor'(findTestObject('Object Repository/Test_Config_Panel/Filter_Section/button_Passed_icon_background_clr'))

        String[] individual_testname = testname_and_result.split(':')

        WebUI.delay(GlobalVariable.short_delay)

        List sel_checkbox_testname = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.filterSectionCheckboxSts'(selected_checkbox_locator)

        if (sel_checkbox_testname.contains((individual_testname[1]).trim())) {
            WebUI.comment('Filter Section buttons are Working as Expected   ****   Here is Failed and Warning Testcase name::  ' + 
                (individual_testname[1])+'\n')
        } else {
            assert false
        }
        
        WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Testconfig_icons/img_Failed_Testcases'))

        def unselected_filter_btn_clr = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.unselectedFilterButtonBgColor'(findTestObject('Object Repository/Test_Config_Panel/Filter_Section/button_fail_icon_background_clr'))
        
		assert graycolor ==unselected_filter_btn_clr
		
		 } else {
        String[] individual_testname = testname_and_result.split(':')

        WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Testconfig_icons/img_Incomplete_Testcases'))

        CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectedFilterButtonBgColor'(findTestObject('Object Repository/Test_Config_Panel/Filter_Section/button_incomplete_icon_background_clr'))

        WebUI.delay(GlobalVariable.short_delay)

        List sel_checkbox_testname = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.filterSectionCheckboxSts'(selected_checkbox_locator)

        if (sel_checkbox_testname.contains((individual_testname[1]).trim())) {
            WebUI.comment('Filter Section buttons are Working as Expected   ****   Here is Incomplete Testcase name::  ' + 
                (individual_testname[1])+'\n')
        } else {
            assert false
        }
        
        WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Testconfig_icons/img_Incomplete_Testcases'))

        def unselected_filter_btn_clr= CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.unselectedFilterButtonBgColor'(findTestObject('Object Repository/Test_Config_Panel/Filter_Section/button_incomplete_icon_background_clr'))
		
		assert graycolor ==unselected_filter_btn_clr
    }
}


WebUI.comment('********* Test_4.1.1.18_Filter_Selection  Execution Ended *********'+'\n')

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()

