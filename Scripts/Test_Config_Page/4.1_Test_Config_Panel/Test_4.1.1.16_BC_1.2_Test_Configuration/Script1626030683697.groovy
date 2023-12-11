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

String exp_moi_name = moi_data.tc_moi_data.TC_BC12_DCP_Sink_Tests_Moi_Name

String exp_indidual_testcases_name = moi_data.tc_moi_data.BC12_Testcase_Name

List exp_bc12_secondary_detection_list = moi_data.tc_moi_data.BC12_Secondary_Detection_Drpdwn

List exp_maximum_current_val_list= moi_data.tc_moi_data.BC12_Maximum_Current_Val

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

WebUI.comment('TestcaseName:: '+'Test_4.1.1.16_BC_1.2_Test_Configuration_Tests'+'\n')

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

'STEP 3:To Click QC.BC.SNK.1 Initial Power Up Tests-Weak Battery Testcase'
CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectIndidualTestCase'(exp_moi_name, exp_indidual_testcases_name)

'STEP 4:To verify BC 1.2 Test Configuration label presence'
def moi_label_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Test_Config_Panel/Thunderbolt_Power_Tests/checkbox_and_textbox_label/p_Thunderbolt Power Test Configuration'),
	5)

assert moi_label_sts == true

'STEP 5:To verify following Dropdown list Option'

'To verify Secondary Detection Drop Down List'
def secondary_detection_list = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiConfigDropdownList'(findTestObject(
		'Object Repository/Test_Config_Panel/BC1.2_DCP_Sink_Tests/button_Secondary_Detection_Drpdown'), '//div[@aria-labelledby="tcBc1.2SecondaryDetectionComboBox"]/a')

assert exp_bc12_secondary_detection_list == secondary_detection_list


'To Verify Maximum Current EditControl'
for (String exp_max_current_val : exp_maximum_current_val_list) {
	String max_current_val = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.changeTextBoxValues'(findTestObject('Object Repository/Test_Config_Panel/BC1.2_DCP_Sink_Tests/input_Maximum_Current_Textbox'),exp_max_current_val)
}


'STEP 6: To uncheck BC 1.2  Tests and ceck the c2 testcases checkbox status'

WebUI.delay(GlobalVariable.long_delay)

WebUI.doubleClick(findTestObject('Object Repository/Test_Config_Panel/BC1.2_DCP_Sink_Tests/BC1.2_Moi_Checkbox'))

def moi_checkbox_sts = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiUnSelectedStatus'(findTestObject('Object Repository/Test_Config_Panel/BC1.2_DCP_Sink_Tests/BC1.2_Moi_Checkbox'))

assert moi_checkbox_sts == true

WebUI.comment('********* Test_4.1.1.16_BC_1.2_Test_Configuration_Tests  Execution Ended *********'+'\n')

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
