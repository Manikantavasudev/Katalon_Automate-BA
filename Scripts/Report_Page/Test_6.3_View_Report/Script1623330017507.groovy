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
import org.openqa.selenium.By as By
import java.nio.file.*
import java.io.IOException as IOException
import java.io.File as File
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.support.Color as Color
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

WebDriver driver = DriverFactory.getWebDriver()

WebUI.delay(GlobalVariable.long_delay)

'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String pc_xml_file_folder = all_data.user_config_data.PC_XML_File_Folder_Path

'To read Report data'
Map report_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Report_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

def report_vif_file_path = report_data.report_page_data.Report_Vif_File_Path

def vif_file_path = Paths.get((pc_xml_file_folder + '\\') + report_vif_file_path)

'To click Product Capability page'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Product Capability'))

'To load vif file'
WebUI.callTestCase(findTestCase('Load_Xml_File_RobotFramework'), [('key1') : vif_file_path], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(GlobalVariable.long_delay)

'To execute testcases'

WebUI.callTestCase(findTestCase('Report_Page/Common_Test_Execution/Test_Common_Testcase_Execution'), [:], FailureHandling.STOP_ON_FAILURE)


'STEP 1: Click View Report'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Report'))

WebUI.mouseOver(findTestObject('Report_panel_objects/View_Report/button_View_Report'))

WebUI.click(findTestObject('Report_panel_objects/View_Report/button_View_Report'))

'Switching to Frame'
WebUI.switchToFrame(findTestObject('Object Repository/Report_panel_objects/View_Report/Frame_xpath'), 5)

'STEP 2: Verify that the current report is view in the screen'
WebUI.verifyElementText(findTestObject('Report_panel_objects/Report_panel/div_GRL-USB-PD-C2_Compliance_Test_Report'),'C2 Compliance Test Report')

WebUI.verifyElementText(findTestObject('Report_panel_objects/View_Report/h1_DUT_Information'), 'DUT Information')

def Dut_is_present = WebUI.getText(findTestObject('Report_panel_objects/View_Report/h1_DUT_Information'))

WebUI.comment(Dut_is_present)

WebUI.verifyElementText(findTestObject('Report_panel_objects/View_Report/h1_Test_Information'), 'Test Information')

WebUI.switchToDefaultContent()

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()



