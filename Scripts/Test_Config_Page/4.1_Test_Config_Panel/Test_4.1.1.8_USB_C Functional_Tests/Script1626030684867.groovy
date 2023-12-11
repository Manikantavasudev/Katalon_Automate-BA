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

String exp_moi_name = moi_data.tc_moi_data.TC_USB_C_Functional_Moi_Name

String exp_indidual_testcases_name = moi_data.tc_moi_data.USB_C_Functional_Testcase_Name

def exp_usb_device_url = moi_data.tc_moi_data.USB_C_Device_Url_Val

'Expected device url tooltip info'
exp_device_url_tooltip = device_url_tooltip_info

'Expected Is Battery connected to dut tooltip info'
exp_battery_conn_tooltip = battery_conn_tooltip_info

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

WebUI.comment('TestcaseName:: '+'Test_4.1.1.8_USB_C_Functional_Tests'+'\n')

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

WebUI.delay(GlobalVariable.short_delay)

'STEP 3:To Click  TD.4.11.2 Sink Dead Battery Test Test Case'
CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectIndidualTestCase'(exp_moi_name, exp_indidual_testcases_name)

'STEP 4:To verify USB C Functional Tests label present in right panel'
def moi_label_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Test_Config_Panel/USB_C_Functional_Tests/p_USB-C_Functional_Test_Configuration'))

assert moi_label_sts == true

'STEP 5:To verify following controls are present'

'STEP 6:To check Enable USB Data Validation check box'
WebUI.click(findTestObject('Object Repository/Test_Config_Panel/USB_C_Functional_Tests/input_Enable_USB_Data_Checkbox'))

'STEP 7:Automate USB Data validation check box'
WebUI.click(findTestObject('Object Repository/Test_Config_Panel/USB_C_Functional_Tests/input_Automate_USB_Data_Checkbox'))

'STEP 8:Click on Setup Image and verify that the below setup image appers'
WebUI.click(findTestObject('Object Repository/Test_Config_Panel/USB_C_Functional_Tests/a_Setup Image'))

WebUI.delay(GlobalVariable.short_delay)

'STEP 9:To click ok button'
WebUI.click(findTestObject('Object Repository/Test_Config_Panel/USB_C_Functional_Tests/button_Ok'))

'STEP 10:To check Device URL Textbox'
def check_device_url_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Test_Config_Panel/USB_C_Functional_Tests/input_Device_URL_Textbox'))

assert check_device_url_sts == true

'STEP 11:To verify Device URL help icon with the below contents'
WebUI.doubleClick(findTestObject('Object Repository/Test_Config_Panel/USB_C_Functional_Tests/img_Device_url_tooltip_info'))

WebUI.delay(GlobalVariable.long_delay)

def device_url_tooltip_info = driver.findElement(By.xpath('//div[@role=\'tooltip\']')).getText()

WebUI.comment('device_url_tooltip_info :: '+device_url_tooltip_info+'\n')

assert exp_device_url_tooltip == device_url_tooltip_info

'STEP 12:To verify Is Battery Connected to DUT  check box'
def battery_connected_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Test_Config_Panel/USB_C_Functional_Tests/input_Battery_Connected_Dut_Checkbox'))

assert battery_connected_sts == true

'STEP 13:To verify that the Help Icon has the below text'
WebUI.doubleClick(findTestObject('Object Repository/Test_Config_Panel/USB_C_Functional_Tests/img_Is_Battery_Connected_tooltip_info'))

WebUI.delay(GlobalVariable.long_delay)

def battery_connected_tooltip_info = driver.findElement(By.xpath('//div[@role=\'tooltip\']')).getText()

WebUI.comment('battery_connected_tooltip_info :: '+battery_connected_tooltip_info+'\n')

assert exp_battery_conn_tooltip == battery_connected_tooltip_info

'STEP 14: To uncheck USB C Functional Tests and  the c2 testcases checkbox status'

WebUI.doubleClick(findTestObject('Object Repository/Test_Config_Panel/USB_C_Functional_Tests/span_USB_C_Functional_Checkbox'))

def moi_checkbox_sts = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiUnSelectedStatus'(findTestObject('Object Repository/Test_Config_Panel/USB_C_Functional_Tests/span_USB_C_Functional_Checkbox'))

assert moi_checkbox_sts == true

WebUI.comment('********* Test_4.1.1.8_USB_C_Functional_Tests  Execution Ended *********'+'\n')

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()

