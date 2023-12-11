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

CustomKeywords.'custom.Logger.CustomLoggerFactory.customizeLogger4CommentKeyword'()

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

String exp_dut = 'Dual Role Power[DRP]'

'To read MOI Config data '
def moi_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Moi_Configuration_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String exp_moi_name = moi_data.tc_moi_data.TC_Thunderbolt_Power_Tests_Moi_Name

String exp_indidual_testcases_name = moi_data.tc_moi_data.Thunderbolt_Testcase_Name

List exp_number_of_ports_drpdwn_list = moi_data.tc_moi_data.Thunderbolt_Number_of_Ports_Drpdwn

List exp_powered_type_drpdwn_list = moi_data.tc_moi_data.Thunderbolt_Powered_Type_Drpdwn

List exp_device_type_drpdwn_list = moi_data.tc_moi_data.Thunderbolt_Device_Type_Drpdwn

List exp_tbt_version_drpdwn_list = moi_data.tc_moi_data.Thunderbolt_TBT_Version_Drpdwn

List exp_stress_test_timer_list = moi_data.tc_moi_data.Thunderbolt_Stress_Test_Timer

WebUI.comment('*****************************************************************************************************************************************************' + 
    '\n')

WebUI.comment(('TestcaseName:: ' + 'Test_4.1.1.13_Thunderbolt_Power_Tests') + '\n')

'To initialize driver object'
WebDriver driver = DriverFactory.getWebDriver()

WebUI.delay(GlobalVariable.short_delay)

WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

'To get port1 dut type list'
def port1_duttype_list = CustomKeywords.'grl_usb_pd_c2_Testcase.ProductCapability_Page.port1InformationaldutDropdownList'()

'To click Port1 DUT dropdown icon'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_1_dut_dropdown_icon'))

WebUI.delay(GlobalVariable.short_delay)

'To click dut Type'
driver.findElement(By.xpath(('//a[text()=\'' + exp_dut) + '\']')).click()

WebUI.delay(GlobalVariable.short_delay)

'To click Testconfig page'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Test Config'))

'STEP 1&2:To check Expand Test List and C2 TestCases checkbox is unselected or not'
def checkbox_unselected_sts = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getCheckBoxUnSelectedStatus'()

assert checkbox_unselected_sts == true

'To click All Supported Certification dropdown'
CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectCertificationDropdown'(findTestObject('Object Repository/Test_Config_Panel/Certification_drop_down'), 
    findTestObject('Object Repository/Test_Config_Panel/Page_GRL - USB PD C2/a_All Supported Certifications'))

'STEP 3:To Click TBT.2.1 Power Capacity Host Testcase'
CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectIndidualTestCase'(exp_moi_name, exp_indidual_testcases_name)

'STEP 4:To verify Thunderbolt Power Test Configuration label presence'
def moi_label_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Test_Config_Panel/Thunderbolt_Power_Tests/checkbox_and_textbox_label/p_Thunderbolt Power Test Configuration'), 
    5)

assert moi_label_sts == true

'To configure Moi input'

WebUI.callTestCase(findTestCase('Test_Config_Page/Moi_Data_Configuration/Moi_data_Configuration'), ['key1':'Thunderbolt Power Tests-Rev1.5 Ver0.9'], FailureHandling.STOP_ON_FAILURE)

'STEP 5:To verify following Dropdown list Option'

'To verify Number of Ports Drop Down List'
def number_of_ports_list = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiConfigDropdownList'(findTestObject(
        'Object Repository/Test_Config_Panel/Thunderbolt_Power_Tests/dropdown_icon/button_Number_of_Ports_Drpdown'), '//div[@aria-labelledby="tcTBTNumberOfPortsComboBox"]/a')

assert exp_number_of_ports_drpdwn_list == number_of_ports_list

'To Verify Powered Type Dropdwon List'
def powered_type_list = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiConfigDropdownList'(findTestObject(
        'Object Repository/Test_Config_Panel/Thunderbolt_Power_Tests/dropdown_icon/button_Powered_Type_Drpdown'), '//div[@aria-labelledby="tcTBTPoweredTypeComboBox"]/a')

assert exp_powered_type_drpdwn_list == powered_type_list

'To Verify Device Type Dropdwon List'
def device_type_list = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiConfigDropdownList'(findTestObject('Object Repository/Test_Config_Panel/Thunderbolt_Power_Tests/dropdown_icon/button_Device_Type_Drpdown'), 
    '//div[@aria-labelledby="tcTBTDeviceTypeComboBox"]/a')

assert exp_device_type_drpdwn_list == device_type_list

'To Verify TBT Version Dropdwon List'
def tbt_version_list = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiConfigDropdownList'(findTestObject('Object Repository/Test_Config_Panel/Thunderbolt_Power_Tests/dropdown_icon/button_TBT_Version_Drpdown'), 
    '//div[@aria-labelledby="tcTBTDeviceTypeComboBox"]/a')

assert exp_tbt_version_drpdwn_list == tbt_version_list

'To Verify Stress Test Timer EditControl'
for (String exp_stress_test_timer_val : exp_stress_test_timer_list) {
    String stress_test_timer_textbox_val = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.changeTextBoxValues'(findTestObject(
            'Object Repository/Test_Config_Panel/Thunderbolt_Power_Tests/checkbox_and_textbox_label/input_Stress_Test_Timer'), 
        exp_stress_test_timer_val)

    assert exp_stress_test_timer_val == stress_test_timer_textbox_val
}

'To verify Following checkbox presence'
def port_a_caps_mismatch_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Test_Config_Panel/Thunderbolt_Power_Tests/checkbox_and_textbox_label/label_Port-A CapMisMatch'), 
    5)

assert port_a_caps_mismatch_sts == true

'To verify Following checkbox presence'
def port_b_caps_mismatch_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Test_Config_Panel/Thunderbolt_Power_Tests/checkbox_and_textbox_label/label_Port-B CapMisMatch'), 
    5)

assert port_b_caps_mismatch_sts == true

'To verify Following checkbox presence'
def port_a_givebackflag_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Test_Config_Panel/Thunderbolt_Power_Tests/checkbox_and_textbox_label/label_Port-A GiveBackFlag'), 
    5)

assert port_a_givebackflag_sts == true

'To verify Following checkbox presence'
def port_b_givebackflag_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Test_Config_Panel/Thunderbolt_Power_Tests/checkbox_and_textbox_label/label_Port-B GiveBackFlag'), 
    5)

assert port_b_givebackflag_sts == true

'To uncheck Thunderbolt Power MOI Tests and check the c2 testcases checkbox status'
WebUI.doubleClick(findTestObject('Object Repository/Test_Config_Panel/Thunderbolt_Power_Tests/Thunderbolt_Power_Moi_Checkbox'))

def moi_checkbox_sts = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiUnSelectedStatus'(findTestObject('Object Repository/Test_Config_Panel/Thunderbolt_Power_Tests/Thunderbolt_Power_Moi_Checkbox'))

assert moi_checkbox_sts == true

WebUI.comment('********* Test_4.1.1.13_Thunderbolt_Power_Tests  Execution Ended *********' + '\n')

WebUI.comment('*****************************************************************************************************************************************************' + 
    '\n')

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()


