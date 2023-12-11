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
import java.awt.Toolkit as Toolkit
import java.awt.datatransfer.StringSelection as StringSelection
import java.awt.event.KeyEvent as KeyEvent
import java.awt.Robot as Robot
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import java.nio.file.*
CustomKeywords.'custom.Logger.CustomLoggerFactory.customizeLogger4CommentKeyword'()

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

'This step will call Common Data Reader Testcase and it will read the user provided json data'
def all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

def pc_drp_vif_file_path = all_data.user_config_data.PC_DRP_Dut_Vif_File_Path

def pc_consumer_only_vif_file_path = all_data.user_config_data.PC_Consumer_Only_Dut_Vif_File_Path

def pc_provider_only_vif_file_path = all_data.user_config_data.PC_Provider_Only_Dut_Vif_File_Path

def pc_cable_vif_file_path = all_data.user_config_data.PC_Cable_Dut_Vif_File_Path

def pc_xml_file_folder_path = all_data.user_config_data.PC_XML_File_Folder_Path

WebDriver driver = DriverFactory.getWebDriver()

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

WebUI.comment('TestcaseName:: '+'Test_3.2.2.1_Dut_Type'+'\n')

'To Check Product Capability Page Availability'
def product_capability_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

'Testcase will fail if Product Capability Page not present'
if (product_capability_sts) {
    WebUI.comment('Product Capability Page is Present'+'\n')
} else {
    WebUI.comment('Product Capability Page is not Present,Something went wrong '+'\n')

    assert false
}

WebUI.delay(GlobalVariable.short_delay)

'To click Product Capability page'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

'STEP 1&2:To loading drp dut Vif file & To verify the dut selected in dropdown list '

    WebUI.delay(GlobalVariable.long_delay)

    Path drp_path = Paths.get(pc_xml_file_folder_path+'\\'+pc_drp_vif_file_path)
	
    WebUI.callTestCase(findTestCase('Load_Xml_File_RobotFramework'), [('key1') : drp_path], FailureHandling.STOP_ON_FAILURE)

    String dut_drp_text = WebUI.getText(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_1_dut_dropdown_icon'))

    assert dut_drp_text == 'Dual Role Power[DRP]'
	
	WebUI.comment('user provided file path::  '+drp_path+' Matching_Dut_Type::  '+dut_drp_text+'\n')

'STEP 3&4:To loading Consumer Only dut Vif file & To verify the dut selected in dropdown list '

    Path consumer_path = Paths.get(pc_xml_file_folder_path+'\\'+pc_consumer_only_vif_file_path)

    WebUI.callTestCase(findTestCase('Load_Xml_File_RobotFramework'), [('key1') : consumer_path], FailureHandling.STOP_ON_FAILURE)

    WebUI.delay(GlobalVariable.long_delay)

    String dut_consumer_only_text = WebUI.getText(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_1_dut_dropdown_icon'))

    assert dut_consumer_only_text == 'Consumer Only'
	
	WebUI.comment('user provided file path::  '+consumer_path+'  Matching_Dut_Type:: '+dut_consumer_only_text+'\n')


'STEP 5&6:To loading Provider Only dut Vif file & To verify the dut selected in dropdown list '

    Path prov_path = Paths.get(pc_xml_file_folder_path+'\\'+pc_provider_only_vif_file_path)
	
    WebUI.callTestCase(findTestCase('Load_Xml_File_RobotFramework'), [('key1') : prov_path], FailureHandling.STOP_ON_FAILURE)

    WebUI.delay(GlobalVariable.long_delay)

    String dut_provider_only_text = WebUI.getText(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_1_dut_dropdown_icon'))

    assert dut_provider_only_text == 'Provider Only'
	
	WebUI.comment('user provided file path::  '+prov_path+'  Matching_Dut_Type:: '+dut_provider_only_text+'\n')


'STEP 7&8:To loading Cable dut Vif file & To verify the dut selected in dropdown list '

   Path cable_path = Paths.get(pc_xml_file_folder_path+'\\'+pc_cable_vif_file_path)

    WebUI.callTestCase(findTestCase('Load_Xml_File_RobotFramework'), [('key1') : cable_path], FailureHandling.STOP_ON_FAILURE)

    WebUI.delay(GlobalVariable.long_delay)

    String dut_cable_text = WebUI.getText(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_1_dut_dropdown_icon'))

    assert dut_cable_text == 'Cable'
	
	WebUI.comment('user provided file path::  '+cable_path+'  Matching_Dut_Type:: '+dut_cable_text+'\n')

	WebUI.comment('********* Test_3.2.2.1_Dut_Type Execution Ended *********'+'\n')
	
	WebUI.comment('*****************************************************************************************************************************************************'+'\n')
	
	
'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)
			
'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()




