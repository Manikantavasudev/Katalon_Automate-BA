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

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

'This step will call option panel dropdown list'
def option_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Option_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String cable_type_locator = option_data.option_page_data.Cable_Type_Locator

List exp_cable_type_drpdwn = option_data.option_page_data.Cable_Type_Drpdwn

String calib_table_header_locator = option_data.option_page_data.Cable_Calibration_Tableheader_Locator

String calib_table_data_locator = option_data.option_page_data.Cable_Calibration_Tabledata_Locator

WebUI.delay(10)

'STEP 1:Go to Options tab'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Options'))

'STEP 2: Select Cable IR Drop Calibration Tab'
WebUI.delay(5)

WebUI.click(findTestObject('Object Repository/Options_Panel_Objects/Cable_IR_Drop_Calibration/a_Cable_IR_Drop_Calibration'))

'STEP 3: Verify that the following controls are present'

'Cable Name Edit Box'
def cable_name_editbox_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Options_Panel_Objects/Cable_IR_Drop_Calibration/input_Cable_Name'), 
    10)

assert cable_name_editbox_sts == true

'Cable Type Drop Down List with the following options'
//def cable_type_drpdwn = CustomKeywords.'grl_usb_pd_c2_Testcase.Options_Page.getOptionsConfigDropdownList'(findTestObject(
       // 'Object Repository/Options_Panel_Objects/Cable_IR_Drop_Calibration/button_Cable_Type_dropdown'), cable_type_locator)

//assert exp_cable_type_drpdwn == cable_type_drpdwn

'Calibration Status Label'
def calibration_status_label = WebUI.verifyElementPresent(findTestObject('Object Repository/Options_Panel_Objects/Cable_IR_Drop_Calibration/span_Calibration_Status'), 
    10)

assert calibration_status_label == true

'Calibrate Button'
def calibrate_button_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Options_Panel_Objects/Cable_IR_Drop_Calibration/button_Calibrate'), 
    10)

assert calibrate_button_sts == true

'Download File Button'
def download_file_btn__sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Options_Panel_Objects/Cable_IR_Drop_Calibration/button_Download_File'), 
    10)

assert download_file_btn__sts == true

'Calibration Table with following headers'
CustomKeywords.'grl_usb_pd_c2_Testcase.Options_Page.tableDataValueReader'(calib_table_header_locator)

'Verify that the location has the following entries'

def table_header = CustomKeywords.'grl_usb_pd_c2_Testcase.Options_Page.tableDataValueReader'(calib_table_data_locator)

println(table_header)

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()

'Browser Teardown'
def table_data = CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()


