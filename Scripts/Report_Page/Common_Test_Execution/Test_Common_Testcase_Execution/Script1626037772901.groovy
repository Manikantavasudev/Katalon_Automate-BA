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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

WebUI.delay(GlobalVariable.short_delay)

WebDriver driver = DriverFactory.getWebDriver()

'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String pc_xml_file_folder = all_data.user_config_data.PC_XML_File_Folder_Path

'To read Report data'
Map report_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Report_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

def report_vif_file_path = report_data.report_page_data.Report_Vif_File_Path

List testcases_name = report_data.report_page_data.Testcases_Name

def vif_file_path = Paths.get((pc_xml_file_folder + '\\') + report_vif_file_path)


WebUI.delay(GlobalVariable.short_delay)

'To Click Test config panel'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Test Config'))

'To select and Execute Testcases '

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
		WebUI.comment(('expand_testlist_checkbox_sts::' + expand_testlist_checkbox_sts) + '\n')
	}
}
catch (Exception e) {
	WebUI.delay(GlobalVariable.short_delay)

	WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Expand_Test_List'))
}

' To click Testcase name in Testconfig panel'
CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectListOfTestCase'(testcases_name)

WebUI.delay(GlobalVariable.short_delay)

WebUI.click(findTestObject('Object Repository/Test_Config_Panel/button_Start Execution'))

'To Verify that Results Panel gets selected & Wait for Execution Competed'
def run_status

WebUI.delay(GlobalVariable.short_delay)

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

WebUI.delay(GlobalVariable.long_delay)



