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
CustomKeywords.'custom.Logger.CustomLoggerFactory.customizeLogger4CommentKeyword'()

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

WebUI.delay(GlobalVariable.short_delay)

'To initialize driver object'
WebDriver driver = DriverFactory.getWebDriver()

'To read vif file data '
def all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

def vif_file_name = all_data.user_config_data.TC_Filter_Vif_File_Path

String pc_xml_file_folder_path = all_data.user_config_data.PC_XML_File_Folder_Path

'To read Report data'
def report_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Report_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

List report_vif_file_path = report_data.report_page_data.Detailed_Report_Vif_File_Path

List testcases_name = report_data.report_page_data.Testcases_Name

List exp_table_1_data_header = report_data.report_page_data.Table_1_Data_Header

List exp_table_2_data_header = report_data.report_page_data.Table_2_Data_Header

List exp_table_3_data_header = report_data.report_page_data.Table_3_Data_Header

List exp_table_4_data_header = report_data.report_page_data.Table_4_Data_Header

def cable_type_alt_data = report_data.report_page_data.Cable_Type_Alternative_Data

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

WebUI.comment('TestcaseName:: '+'Test_6.2_Detailed_Report_Structure'+'\n')

def exp_table7_ui_data = []
WebUI.delay(GlobalVariable.long_delay)
   
for (String separate_vif_file : report_vif_file_path) {
	
	'To click Connection Setup panel'
	WebUI.delay(GlobalVariable.long_delay)
	
	WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Connection_Setup'))
	
	'To read grl_usb_pd_c2_serial number'
	def grl_usb_pd_c2_serial_no = WebUI.getText(findTestObject('Object Repository/Tester_Details/Page_GRL - USB PD C2/b_Serial_Number'))
	
	exp_table7_ui_data.add('GRL_USB_PD_C2_Serial_No'+':'+grl_usb_pd_c2_serial_no.trim())
	
	'To read grl_usb_pd_software_version'
	def appl_full_version = WebUI.getText(findTestObject('Object Repository/Report_Page_Updated_Objects/Detailed_Report_Structure/p_Application_Version'))
	
	String appl_version = appl_full_version.substring(appl_full_version.indexOf('(') + 1, appl_full_version.indexOf(')'))
	 
	exp_table7_ui_data.add('GRL_USB_PD_Software_Version'+':'+appl_version.trim())
	
	'To read grl_usb_pd_firmware version &&  grl_usb_pd_eload version number'
	def grl_usb_pd_c2_fw_eload_no = WebUI.getText(findTestObject('Object Repository/Tester_Details/Page_GRL - USB PD C2/b_Firmware_Version'))
	
	 def fw_and_eload_val = grl_usb_pd_c2_fw_eload_no.split('/')
	
	def grl_usb_pd_c2_elode_version = ((fw_and_eload_val[1]) + '/') + (fw_and_eload_val[2])
	
	def grl_usb_pd_c2_fw_version = fw_and_eload_val[0]
	
	exp_table7_ui_data.add('GRL_USB_PD_Firmware_Version'+':'+grl_usb_pd_c2_fw_version.trim())
	
	exp_table7_ui_data.add('GRL USB-PD Eload Firmware Version'+':'+grl_usb_pd_c2_elode_version.trim())
	
	'To read calibration status'
	def board_calibration_sts = WebUI.getText(findTestObject('Object Repository/Tester_Details/Page_GRL - USB PD C2/b_C2_Tester_Calibration'))
	
	exp_table7_ui_data.add('Board Calibration'+':'+board_calibration_sts.trim())
	
	exp_table7_ui_data.add('VIF_File_Name'+':'+separate_vif_file)
	
    'To click Product Capability page'
    WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

    'To combine user config vif file path'
    Path vif_file_path = Paths.get((pc_xml_file_folder_path + '\\') + separate_vif_file)
		
    'STEP 2:To load the xml file using load xml file button'
    WebUI.callTestCase(findTestCase('Load_Xml_File_RobotFramework'), [('key1') : vif_file_path], FailureHandling.STOP_ON_FAILURE)

    WebUI.delay(GlobalVariable.short_delay)

    'To find Application mode'
    def app_sts = WebUI.verifyElementChecked(findTestObject('Object Repository/Product_Capability_Objects/input_Informational (No VIF)_RadioBtn'), 
        10)
	if(app_sts) {
	exp_table7_ui_data.add('Application mode'+':'+'Informational')
	}

    'To read Device Type(DUT Type)'
    def device_type = WebUI.getText(findTestObject('Object Repository/Report_Page_Updated_Objects/Detailed_Report_Structure/div_Device_Type'))

	exp_table7_ui_data.add('Device_Type'+':'+device_type)

    'To read Cable Type'
    def cable_type = WebUI.getText(findTestObject('Object Repository/Report_Page_Updated_Objects/Detailed_Report_Structure/div_Cable_Type'))
	String cable_alt_text = cable_type_alt_data.get(cable_type)
	exp_table7_ui_data.add('Cable Type'+':'+cable_alt_text)
  
    'To click Testconfig page'
    WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Test Config'))

    'To click All Supported Certification dropdown'
    CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectCertificationDropdown'(findTestObject('Object Repository/Test_Config_Panel/Certification_drop_down'), 
        findTestObject('Object Repository/Test_Config_Panel/Page_GRL - USB PD C2/a_All Supported Certifications'))

    WebUI.delay(GlobalVariable.short_delay)

    'To verify checkbox unselected state'
    def expand_testlist_checkbox_sts

    try {
        'To check expand list checkbox status'
        expand_testlist_checkbox_sts = WebUI.verifyElementNotChecked(findTestObject('Object Repository/Test_Config_Panel/Expand_Test_List'), 
            5)

        if (expand_testlist_checkbox_sts) {
            WebUI.comment('expand_testlist_checkbox_sts::' + expand_testlist_checkbox_sts+'\n')
        }
    }
    catch (Exception e) {
        WebUI.delay(GlobalVariable.short_delay)

        WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Expand_Test_List'))
    } 
    
    'STEP 3: To click Testcase name in Testconfig panel'
    CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectListOfTestCase'(testcases_name)

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
                WebUI.delay(GlobalVariable.long_delay)

                run_status = true
            }
        }
    }
    catch (Exception e) {
        run_status = false
    } 
    
    WebUI.delay(GlobalVariable.short_delay)

    'To click Report Panel'
    WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Report'))

    'Switching to text area which is situated in Iframe'
    WebUI.switchToFrame(findTestObject('Object Repository/Report_Page_Updated_Objects/i_frame'), 60)

    'STEP 3:To read table 1'
    List table_1_header_list = CustomKeywords.'grl_usb_pd_c2_Testcase.Report_Panel.getTable1DataValueInReport'()
    assert exp_table_1_data_header ==  table_1_header_list 
    WebUI.comment('Table 1 Header Name List::  ' + table_1_header_list+'\n')

    'STEP 4:To read table 2'
    List table_2_header_list = CustomKeywords.'grl_usb_pd_c2_Testcase.Report_Panel.getTable2DataValueInReport'()
	assert exp_table_2_data_header ==  table_2_header_list
    WebUI.comment('Table 2 Header Name List::  ' + table_2_header_list+'\n')

    'STEP 6:To read table 3'
    List table_3_header_list = CustomKeywords.'grl_usb_pd_c2_Testcase.Report_Panel.getTable3DataValueInReport'()
	assert exp_table_3_data_header ==  table_3_header_list
    WebUI.comment('Table 3 Header Name List::  ' + table_3_header_list+'\n')

    'STEP 7:To read table 4'
    List table_4_header_list = CustomKeywords.'grl_usb_pd_c2_Testcase.Report_Panel.getTable4DataValueInReport'()
	assert exp_table_4_data_header ==  table_4_header_list
    WebUI.comment('Table 4 Header Name List::  ' + table_4_header_list+'\n')

    'STEP 9:To read table 5'
    List table_5_header_list = CustomKeywords.'grl_usb_pd_c2_Testcase.Report_Panel.getTable5DataValueInReport'()
	
    WebUI.comment('Table 5 Header Name List::  ' + table_5_header_list+'\n')

    'STEP 10:To read table 6'
    List table_6_header_list = CustomKeywords.'grl_usb_pd_c2_Testcase.Report_Panel.getTable6DataValueInReport'()

    WebUI.comment('Table 6 Header Name List::  ' + table_6_header_list+'\n')

    'STEP 11:To read table 7'
    List table_7_header_list = CustomKeywords.'grl_usb_pd_c2_Testcase.Report_Panel.getTable7DataValueInReport'()

    WebUI.comment('Table 7 Header Name List::  ' + table_7_header_list+'\n')
    
    WebUI.comment('Expected_table7_ui_data'+exp_table7_ui_data+'\n')	
	
	'To compare expected ui data with table7 report data'
	
	for(String table7_ui_data:exp_table7_ui_data) {
		if(table_7_header_list.contains(table7_ui_data)) {
			WebUI.comment('ui_data matching with table 7 report data ****  '+table7_ui_data+'\n')
		}
		else {
			WebUI.comment('ui_data not matching with table 7 report data ****  '+table7_ui_data+'\n')
		}
		
	
	}
	exp_table7_ui_data.clear()
	
	
    'Switching back to the main window or parent window frame'
    WebUI.switchToDefaultContent()
}

WebUI.comment('********* Test_6.2_Detailed_Report_Structure  Execution Ended *********'+'\n')

WebUI.comment('*****************************************************************************************************************************************************'+'\n')


'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()




