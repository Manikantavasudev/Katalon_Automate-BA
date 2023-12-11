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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.By as By
CustomKeywords.'custom.Logger.CustomLoggerFactory.customizeLogger4CommentKeyword'()

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

String exp_help_icon_tooltip_text = 'Select this option if FR_Swap AUTO test fixture is available in the test setupThis test fixture is required for running Fast Role Swap test cases when DUT acts as Initial Source Fast Role Swap test results are for informational only for now and these are not mandatory for certification testing'

String exp_dut = 'Dual Role Power[DRP]'

'To read MOI Config data '
def moi_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Moi_Configuration_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String exp_moi_name = moi_data.tc_moi_data.TC_Power_Delivery_3_Moi_Name

String exp_indidual_testcases_name = moi_data.tc_moi_data.Power_Delivery_Testcase_Name

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

WebUI.comment('TestcaseName:: '+'Test_4.1.1.4_C2_Test_Case_Checkbox'+'\n')


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

'To click Testconfig page'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Test Config'))

'STEP 1&2:To check Expand Test List and C2 TestCases checkbox is unselected or not'
def checkbox_unselected_sts = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getCheckBoxUnSelectedStatus'()

assert checkbox_unselected_sts == true

'To click All Supported Certification dropdown'
CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectCertificationDropdown'(findTestObject('Object Repository/Test_Config_Panel/Certification_drop_down'), 
    findTestObject('Object Repository/Test_Config_Panel/Page_GRL - USB PD C2/a_All Supported Certifications'))

WebUI.delay(GlobalVariable.short_delay)

'STEP 3:To Click QTD.PD.LL3.E1 GoodCRC Specification Compatibility Testcase'
CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectIndidualTestCase'(exp_moi_name, exp_indidual_testcases_name)

'STEP 4:To verify power Delivery 3.0 is present in right panel'
def moi_label_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Test_Config_Panel/Power_Delivery_3.0_Tests/p_Power Delivery 3.0 Test Configuration'))

assert moi_label_sts == true

WebUI.delay(GlobalVariable.long_delay)

'STEP 5:To verify FR_Swap AUTO Box Connected check box is present'
def fr_swap_auto_checkbox_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Test_Config_Panel/Power_Delivery_3.0_Tests/label_FR_Swap AUTO Box Connected'))

assert fr_swap_auto_checkbox_sts == true

'STEP 6:To uncheck Power Delivery 3.0 Tests and ceck the c2 testcases checkbox status'

WebUI.doubleClick(findTestObject('Object Repository/Test_Config_Panel/Power_Delivery_3.0_Tests/Power_Delivery_Moi_Checkbox'))

def moi_checkbox_sts = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiUnSelectedStatus'(findTestObject('Object Repository/Test_Config_Panel/Power_Delivery_3.0_Tests/Power_Delivery_Moi_Checkbox'))

assert moi_checkbox_sts == true

WebUI.comment('********* Test_4.1.1.4_C2_Test_Case_Checkbox  Execution Ended *********'+'\n')

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()



