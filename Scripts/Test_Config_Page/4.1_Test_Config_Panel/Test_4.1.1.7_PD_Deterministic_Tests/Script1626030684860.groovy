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

String exp_dut = 'Cable'

'To read MOI Config data '
def moi_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Moi_Configuration_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String exp_moi_name = moi_data.tc_moi_data.TC_PD2_Communication_Engine_Tests

String exp_indidual_testcases_name = moi_data.tc_moi_data.PD2_Deterministic_Testcase_Name

List exp_pd2_vconn_type_drpdwn = moi_data.tc_moi_data.PD2_Deterministic_Vconn_Type_Drpdwn

'PD2 Vconn tooltip Info '
String pd2_tooltip_info = pd2_tooltip_info

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

WebUI.comment('TestcaseName:: '+'Test_4.1.1.7_PD_Deterministic_Tests'+'\n')

'To initialize driver object'
WebDriver driver = DriverFactory.getWebDriver()

WebUI.delay(GlobalVariable.short_delay)

WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

'To get port1 dut type list'
def port1_duttype_list = CustomKeywords.'grl_usb_pd_c2_Testcase.ProductCapability_Page.port1InformationaldutDropdownList'()

'To click Port1 DUT dropdown icon'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_1_dut_dropdown_icon'))

WebUI.delay(GlobalVariable.short_delay)

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

WebUI.delay(GlobalVariable.short_delay)

'STEP 3:To Click TD.PD.VDMU.E1 Fields Checks Discover Identity Test Case'
CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectIndidualTestCase'(exp_moi_name, exp_indidual_testcases_name)

WebUI.delay(GlobalVariable.short_delay)

'STEP 4:Verify that PD2 Deterministic Test Configuration  is present on right panel'
def pd2_label_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Test_Config_Panel/PD2_Deterministic_Tests/Label_and_checkbox/p_PD2_Deterministic_Tests_Label'))

assert pd2_label_sts == true

'STEP 5:Verify that VConn Voltage Drop Down List is present'
def vconn_dut_type_list = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiConfigDropdownList'(findTestObject(
		'Object Repository/Test_Config_Panel/PD2_Deterministic_Tests/dropdown/button_pd2_deterministic_dropdown'), "//div[@aria-labelledby='tcPd2DeterministicVconnVoltageComboBox']/a")

assert exp_pd2_vconn_type_drpdwn == vconn_dut_type_list

'STEP 6:Verify that the help icon is present & Verify tooltip Info'
WebUI.doubleClick(findTestObject('Object Repository/Test_Config_Panel/PD2_Deterministic_Tests/Label_and_checkbox/img_pd2_deterministic_helpicon'))

WebUI.delay(GlobalVariable.short_delay)

def tooltip_help_icon_info =driver.findElement(By.xpath("//div[@role='tooltip']")).getText()

WebUI.comment('tooltip_help_icon_info :: '+tooltip_help_icon_info+'\n')

assert pd2_tooltip_info == tooltip_help_icon_info


'STEP 7:To uncheck PD Deterministic Tests and ceck the c2 testcases checkbox status'
WebUI.doubleClick(findTestObject('Test_Config_Panel/PD2_Deterministic_Tests/Label_and_checkbox/PD2_Deterministic_Moi_Checkbox'))

def moi_checkbox_sts = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiUnSelectedStatus'(findTestObject('Test_Config_Panel/PD2_Deterministic_Tests/Label_and_checkbox/PD2_Deterministic_Moi_Checkbox'))

assert moi_checkbox_sts == true

WebUI.comment('********* Test_4.1.1.7_PD_Deterministic_Tests  Execution Ended *********'+'\n')

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()

