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
import org.openqa.selenium.Keys as Keys
import java.nio.file.*
import java.io.IOException as IOException
import java.io.File as File
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import org.openqa.selenium.WebElement as WebElement

CustomKeywords.'custom.Logger.CustomLoggerFactory.customizeLogger4CommentKeyword'()

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

String exp_dut = 'Provider Only'

'To read MOI Config data '
def moi_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Moi_Configuration_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String exp_moi_name = moi_data.tc_moi_data.TC_MFi_Charger_Tests_Moi_Name

String exp_indidual_testcases_name = moi_data.tc_moi_data.MFi_Charger_Testcase_Name

List exp_eload_channel_list = moi_data.tc_moi_data.MFi_Charger_Eload_Channel_Drpdown

String exp_mfi_charger_tooltip_info = moi_data.tc_moi_data.MFI_Charger_Tooltip_Info

String exp_mfi_vif_file_path = moi_data.tc_moi_data.MFI_Charger_Vif_File_Path

'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

'xml folder path'
String pc_xml_file_folder_path = all_data.user_config_data.PC_XML_File_Folder_Path

def vif_file_path = Paths.get((pc_xml_file_folder_path + '\\') + exp_mfi_vif_file_path)


WebUI.comment('*****************************************************************************************************************************************************'+'\n')

WebUI.comment('TestcaseName:: '+'Test_4.1.1.15_MFi_Charger_Tests'+'\n')

'To initialize driver object'
WebDriver driver = DriverFactory.getWebDriver()

WebUI.delay(GlobalVariable.short_delay)

WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

'To load xml file '

WebUI.delay(GlobalVariable.short_delay)

WebUI.callTestCase(findTestCase('Load_Xml_File_RobotFramework'), [('key1') : vif_file_path], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(GlobalVariable.long_delay)

'To click Testconfig page'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Test Config'))

'STEP 1&2:To check Expand Test List and C2 TestCases checkbox is unselected or not'
def checkbox_unselected_sts = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getCheckBoxUnSelectedStatus'()

assert checkbox_unselected_sts == true

'To click All Supported Certification dropdown'
CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectCertificationDropdown'(findTestObject('Object Repository/Test_Config_Panel/Certification_drop_down'), 
    findTestObject('Object Repository/Test_Config_Panel/Page_GRL - USB PD C2/a_All Supported Certifications'))

'STEP 3:To Click MFi.TD.4.9.1 Voltage drop between transition test Testcase'
CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectIndidualTestCase'(exp_moi_name, exp_indidual_testcases_name)

'STEP 4:To verify MFi Test Configuration label presence'
def moi_label_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Test_Config_Panel/MFi_Charger_Tests/checkbox_and_buttons/p_MFi Test Configuration'), 
    5)

assert moi_label_sts == true

'STEP 5:To verify following Dropdown list Option'

'To verify Eload Channel Drop Down List'
def eload_channel_list = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiConfigDropdownList'(findTestObject(
        'Object Repository/Test_Config_Panel/MFi_Charger_Tests/dropdown/button_Eload_Channel_Drpdown'), '//div[@aria-labelledby="tcMfiChargerELoadChannelComboBox"]/a')

assert eload_channel_list == exp_eload_channel_list 

'To verify Charger has captive lightning plug checkbox'
def capative_checkbox_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Test_Config_Panel/MFi_Charger_Tests/checkbox_and_buttons/label_Charger_has_captive_lightning_checkbox'), 
    5)

assert capative_checkbox_sts == true

WebUI.delay(GlobalVariable.short_delay)


'To verify Scan Eload Button presence'
def scan_eload_btn_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Test_Config_Panel/MFi_Charger_Tests/checkbox_and_buttons/button_Scan Eload'), 
    5)

assert scan_eload_btn_sts == true

'To verify External eload VISA Address label'
def external_elode_visa_add_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Test_Config_Panel/MFi_Charger_Tests/checkbox_and_buttons/span_External eLoad VISA Address'), 
    5)

assert external_elode_visa_add_sts == true

'To verify Connect Button presence'
def connect_btn_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Test_Config_Panel/MFi_Charger_Tests/checkbox_and_buttons/button_Connect'), 
    5)

assert connect_btn_sts == true

'To uncheck MFI Charger Tests and ceck the c2 testcases checkbox status'

WebUI.doubleClick(findTestObject('Object Repository/Test_Config_Panel/MFi_Charger_Tests/Mfi_Charger_Moi_Checkbox'))

def moi_checkbox_sts = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiUnSelectedStatus'(findTestObject('Object Repository/Test_Config_Panel/MFi_Charger_Tests/Mfi_Charger_Moi_Checkbox'))

assert moi_checkbox_sts == true

WebUI.comment('********* Test_4.1.1.15_MFi_Charger_Tests  Execution Ended *********'+'\n')

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()

