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

String exp_moi_name = moi_data.tc_moi_data.TC_Quick_Charger_3_Moi_Name

String exp_indidual_testcases_name = moi_data.tc_moi_data.QC3_Testcase_Name

List exp_qc_2_3_dut_type_list = moi_data.tc_moi_data.QC3_Qc_2_3_Dut_Type_Drpdwn

List exp_connector_type_list = moi_data.tc_moi_data.QC3_Connector_Type_Drpdwn

List exp_connector_type_cable_list = moi_data.tc_moi_data.QC3_Connector_Type_Cable_Drpdwn

List exp_ports_list = moi_data.tc_moi_data.QC3_Ports_Drpdwn

List exp_pd_support_list = moi_data.tc_moi_data.QC3_PD_Support_Drpdwn

List exp_qc_dut_type_list = moi_data.tc_moi_data.QC3_Qc_Dut_Type_Drpdwn

List exp_5v_max_current_val_list = moi_data.tc_moi_data.QC3_5V_Max_Current_Val

List exp_9v_max_current_val_list = moi_data.tc_moi_data.QC3_9V_Max_Current_Val

List exp_12v_max_current_val_list = moi_data.tc_moi_data.QC3_12V_Max_Current_Val

List exp_20v_max_current_val_list = moi_data.tc_moi_data.QC3_20V_Max_Current_Val

List exp_dut_rated_power_val_list = moi_data.tc_moi_data.QC3_Dut_Rated_Power_Val

WebUI.comment('*****************************************************************************************************************************************************' + 
    '\n')

WebUI.comment(('TestcaseName:: ' + 'Test_4.1.1.11_Quick_Charger_3.0_Tests') + '\n')

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

'STEP 3:To Click QC_LEGACY_Shorting Testcase'
CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectIndidualTestCase'(exp_moi_name, exp_indidual_testcases_name)

'STEP 4:To verify Quick Charger 3.0 Test Configuration text presence & To Verify Following options'
def quick_charger_3_text_sts = WebUI.verifyElementPresent(findTestObject('Test_Config_Panel/Quick_Charge_3.0_Tests/textbox/p_Quick Charger 3.0 Test Configuration'), 
    5)

assert quick_charger_3_text_sts == true

'To configure MOI Input'
WebUI.callTestCase(findTestCase('Test_Config_Page/Moi_Data_Configuration/Moi_data_Configuration'), [('key1') : 'Quick Charger 3.0 Tests-v1.4'], 
    FailureHandling.STOP_ON_FAILURE)

'To verify QC 2.0/3.0 DUT Type Drop Down List'
def qc_2_3_dut_type_list = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiConfigDropdownList'(findTestObject(
        'Object Repository/Test_Config_Panel/Quick_Charge_3.0_Tests/dropdown/button_QC 2.0_3.0_Dut_Type_dropdown'), '//div[@aria-labelledby=\'tcQc3Qc2.0/3.0DutTypeComboBox\']/a')

assert qc_2_3_dut_type_list == exp_qc_2_3_dut_type_list

'To Verify Connector_Type_Dropdown_List'
def connector_type_list = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiConfigDropdownList'(findTestObject(
        'Object Repository/Test_Config_Panel/Quick_Charge_3.0_Tests/dropdown/button_Connector Type_dropdown'), '//div[@aria-labelledby=\'tcQc3ConnectorTypeComboBox\']/a')

assert connector_type_list == exp_connector_type_list

'To Verify Connector Type Cable Dropdwon List'
def connector_type_cable_list = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiConfigDropdownList'(findTestObject(
        'Object Repository/Test_Config_Panel/Quick_Charge_3.0_Tests/dropdown/button_Connector Type Cable_dropdown'), '//div[@aria-labelledby=\'tcQc3ConnectorTypeCableComboBox\']/a')

assert connector_type_cable_list == exp_connector_type_cable_list

'To Verify 5V Max Current EditControl'
for (String exp_5v_max_current_val : exp_5v_max_current_val_list) {
    String max_current_val = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.changeTextBoxValues'(findTestObject(
            'Object Repository/Test_Config_Panel/Quick_Charge_3.0_Tests/textbox/input_5V_Max_Current'), exp_5v_max_current_val)

    assert max_current_val == exp_5v_max_current_val
}

'To Verify 9V Max Current EditControl'
for (String exp_9v_max_current_val : exp_9v_max_current_val_list) {
    String max_current_val = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.changeTextBoxValues'(findTestObject(
            'Object Repository/Test_Config_Panel/Quick_Charge_3.0_Tests/textbox/input_9V_Max_Current'), exp_9v_max_current_val)

    assert max_current_val == exp_9v_max_current_val
}

'To Verify 12V Max Current EditControl'
for (String exp_12v_max_current_val : exp_12v_max_current_val_list) {
    String max_current_val = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.changeTextBoxValues'(findTestObject(
            'Object Repository/Test_Config_Panel/Quick_Charge_3.0_Tests/textbox/input_12V_Max_Current'), exp_12v_max_current_val)

    assert max_current_val == exp_12v_max_current_val
}

'To select 20V Max Current checkbox'
try {
    def timeout_popup_sts = WebUI.verifyElementChecked(findTestObject('Object Repository/Test_Config_Panel/Quick_Charge_3.0_Tests/textbox/input_20V_Max_Current_Checkbox'), 
        30)

    if (timeout_popup_sts) {
        for (String exp_20v_max_current_val : exp_20v_max_current_val_list) {
            String max_current_val = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.changeTextBoxValues'(findTestObject(
                    'Object Repository/Test_Config_Panel/Quick_Charge_3.0_Tests/textbox/input_20V_Max_Current'), exp_20v_max_current_val)

            assert max_current_val == exp_20v_max_current_val
        }
    }
}
catch (Exception e) {
    'To select 20V Max Current checkbox'
    WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Quick_Charge_3.0_Tests/textbox/input_20V_Max_Current_Checkbox'))

    for (String exp_20v_max_current_val : exp_20v_max_current_val_list) {
        String max_current_val = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.changeTextBoxValues'(findTestObject(
                'Object Repository/Test_Config_Panel/Quick_Charge_3.0_Tests/textbox/input_20V_Max_Current'), exp_20v_max_current_val)

        assert max_current_val == exp_20v_max_current_val
    }
} 

WebUI.delay(GlobalVariable.short_delay)

'To Verify DUT Rated Power EditControl'
for (String exp_dut_rated_power_val : exp_dut_rated_power_val_list) {
    String dut_rated_power_value = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.changeTextBoxValues'(findTestObject(
            'Object Repository/Test_Config_Panel/Quick_Charge_3.0_Tests/textbox/input_DUT_Rated_Power'), exp_dut_rated_power_val)

    assert dut_rated_power_value == exp_dut_rated_power_val
}

'To Verify Ports Dropdown List'
def ports_dropdwn_list = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiConfigDropdownList'(findTestObject(
        'Object Repository/Test_Config_Panel/Quick_Charge_3.0_Tests/dropdown/button_Ports_dropdown'), '//div[@aria-labelledby=\'tcQc3PortsComboBox\']/a')

assert ports_dropdwn_list == exp_ports_list

'To Verify PD Support Dropdown List'
def pd_support_dropdwn_list = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiConfigDropdownList'(findTestObject(
        'Object Repository/Test_Config_Panel/Quick_Charge_3.0_Tests/dropdown/button_PD_Support_dropdown'), '//div[@aria-labelledby=\'tcQc3PdSupportComboBox\']/a')

assert pd_support_dropdwn_list == exp_pd_support_list

'To Verify QC Dut Type Dropdown List'
def qc_dut_type_dropdwn_list = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiConfigDropdownList'(findTestObject(
        'Object Repository/Test_Config_Panel/Quick_Charge_3.0_Tests/dropdown/button_QC_Dut_Type_dropdown'), '//div[@aria-labelledby=\'tcQc3QcDutTypeComboBox\']/a')

assert qc_dut_type_dropdwn_list == exp_qc_dut_type_list

'To uncheck Quick Charger 3.0  Tests  MOI and check the c2 testcases checkbox status'
WebUI.doubleClick(findTestObject('Object Repository/Test_Config_Panel/Quick_Charge_3.0_Tests/Quick_Charger_3_Moi_Checkbox'))

def moi_checkbox_sts = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiUnSelectedStatus'(findTestObject('Object Repository/Test_Config_Panel/Quick_Charge_3.0_Tests/Quick_Charger_3_Moi_Checkbox'))

assert moi_checkbox_sts == true

WebUI.comment('********* Test_4.1.1.11_Quick_Charger_3.0_Tests  Execution Ended *********' + '\n')

WebUI.comment('*****************************************************************************************************************************************************' + 
    '\n')

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()



