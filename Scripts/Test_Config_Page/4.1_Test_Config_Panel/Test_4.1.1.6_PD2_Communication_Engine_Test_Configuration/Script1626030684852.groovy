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

String exp_dut ='Cable'

'To read MOI Config data '
def moi_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Moi_Configuration_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String exp_moi_name = moi_data.tc_moi_data.TC_PD2_Deterministic_Tests

String exp_indidual_testcases_name = moi_data.tc_moi_data.PD2_Communication_Testcase_Name

List exp_pd2_communication_noise_type_drpdwn=moi_data.tc_moi_data.PD2_Communication_Noise_Type_Drpdwn

List exp_pd_vconn_type_drpdwn = moi_data.tc_moi_data.PD2_Communication_Vconn_Type_Drpdwn

'Vconn tooltip icon'
exe_vconn_tooltip_icon = vconn_tooltip_icon

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

WebUI.comment('TestcaseName:: '+'Test_4.1.1.6_PD2_Communication_Engine_Test_Configuration'+'\n')


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

'STEP 3:To Click TDA.2.1.1.1 BMC PHY TX EYE Test Case'
CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectIndidualTestCase'(exp_moi_name, exp_indidual_testcases_name)

'STEP 4:To verify PD2 Communication label present in right panel'
def moi_label_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Test_Config_Panel/PD2_Communication_Engine_Test/Label_and_Checkbox/p_PD2 Communication Engine Test Configuration'))

assert moi_label_sts == true

'STEP 5 &6 : To Verify Noise Type List is present'

def noise_type_drpdwn_list = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiConfigDropdownList'(findTestObject(
		'Object Repository/Test_Config_Panel/PD2_Communication_Engine_Test/dropdown/button_Noise_Type_Dropdown'), "//div[@aria-labelledby='tcPd2CommunicationNoiseTypeComboBox']/a")

assert exp_pd2_communication_noise_type_drpdwn == noise_type_drpdwn_list

'STEP 7 : To verify Vconn Drpdown list'

def vconn_type_drpdwn_list = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiConfigDropdownList'(findTestObject(
		'Object Repository/Test_Config_Panel/PD2_Communication_Engine_Test/dropdown/button_Vconn_Type_Dropdown'), "//div[@aria-labelledby='tcPd2CommunicationVconnVlotageComboBox']/a")

assert exp_pd_vconn_type_drpdwn == vconn_type_drpdwn_list

'STEP 8&9 :Verify that the help icon is present & Verify tooltip Info'

WebUI.doubleClick(findTestObject('Object Repository/Test_Config_Panel/PD2_Communication_Engine_Test/dropdown_list_helpicon/img_vconn_tooltip_icon'))

WebUI.delay(GlobalVariable.short_delay)

def tooltip_help_icon_info =driver.findElement(By.xpath("//div[@role='tooltip']")).getText()

WebUI.comment('tooltip_help_icon_info :: '+tooltip_help_icon_info+'\n')

assert exe_vconn_tooltip_icon == tooltip_help_icon_info

'STEP 10: To uncheck PD2 Communication Engine Tests the c2 testcases checkbox status'

WebUI.doubleClick(findTestObject('Test_Config_Panel/PD2_Communication_Engine_Test/Label_and_Checkbox/PD2_Communication_Moi_Checkbox'))

def moi_checkbox_sts = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.getMoiUnSelectedStatus'(findTestObject('Test_Config_Panel/PD2_Communication_Engine_Test/Label_and_Checkbox/PD2_Communication_Moi_Checkbox'))

assert moi_checkbox_sts == true

WebUI.comment('********* Test_4.1.1.6_PD2_Communication_Engine_Test_Configuration  Execution Ended *********'+'\n')

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()