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
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By
import java.nio.file.*
import java.io.IOException as IOException
import java.io.File as File


CustomKeywords.'custom.Logger.CustomLoggerFactory.customizeLogger4CommentKeyword'()

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String pc_c2_project_name = all_data.user_config_data.PC_C2_Project_Name

List pc_c2_vif_file_path = all_data.user_config_data.PC_C2_Vif_File_Path

String vif_file_expected_Popup_Content = all_data.user_config_data.PC_C2_Vif_File_Popup_Content

String pc_xml_file_folder_path = all_data.user_config_data.PC_XML_File_Folder_Path

WebDriver driver = DriverFactory.getWebDriver()

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

WebUI.comment('TestcaseName:: '+'Test_3.1.2_Load_Xml_Vif_File(Compliance_Mode)'+'\n')

'To Check Product Capability Page Availability'
def product_capability_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

'Testcase will fail if Product Capability Page not present'
if (product_capability_sts) {
    WebUI.comment('Product Capability Page is Present'+'\n')
} else {
    WebUI.comment('Product Capability Page is not Present,Something went wrong '+'\n')

    assert false
}

'To click Product Capability page'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

WebUI.delay(GlobalVariable.long_delay)

'To click Compliance Radio Button'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/input_Compliance_pcComplianceTestRadioBtn'))

WebUI.delay(GlobalVariable.short_delay)

for (String separate_vif_file_path : pc_c2_vif_file_path) {
	
	' To combine load xml file folder and Xml file '
	Path vif_file_path = Paths.get(pc_xml_file_folder_path +'\\'+ separate_vif_file_path)
	 
	
	 WebUI.delay(GlobalVariable.short_delay)
	
    'STEP 1&2&3:To click Load xml vif file button & verify windows file open dialogue and select xml file '
	 WebUI.callTestCase(findTestCase('Load_Xml_File_RobotFramework'), [('key1') : vif_file_path], FailureHandling.STOP_ON_FAILURE)
	
    'STEP 4:To verify filename getting updated on Load vif  xml file button'

	String Vif_path = vif_file_path
	
	CustomKeywords.'grl_usb_pd_c2_Testcase.ProductCapability_Page.fileNameValidation'(Vif_path)

    'STEP 5&6: To verify the popup content after uploading the filename & To verify the popup is diasppered within 10 S'
   // CustomKeywords.'grl_usb_pd_c2_Testcase.ProductCapability_Page.vifFilePopupContentValidation'(vif_file_expected_Popup_Content)

    'Step 7 :To verify Vif file data with Vif file ui data'
	
	WebUI.callTestCase(findTestCase('xml_verifier/Test_Xml_UI_Verifier'), ["key1":vif_file_path], FailureHandling.STOP_ON_FAILURE)
}

WebUI.comment('********* Test_3.1.2 Load_Xml_Vif_File Execution Ended *********'+'\n')

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()



