
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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import groovy.time.TimeCategory as TimeCategory
import java.nio.file.*
import java.io.IOException as IOException
import java.io.File as File
import java.util.HashMap as HashMap
import java.util.Map as Map
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions as ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities as DesiredCapabilities
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import groovy.transform.CompileStatic as CompileStatic
import java.awt.Toolkit as Toolkit
import java.awt.datatransfer.StringSelection as StringSelection
import java.awt.event.KeyEvent as KeyEvent
import java.awt.Robot as Robot
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper as MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil


'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

'Get the file file from the json'
String device_data_vif_file_path = all_data.user_config_data.PC_Device_Data_Vif_File_Path

String python_path = all_data.user_config_data.Python_exe_path

String Installation_path = all_data.user_config_data.Katalon_installation_path

String pc_xml_file_folder_path = all_data.user_config_data.PC_XML_File_Folder_Path

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

'driver object initialization'
WebDriver driver = DriverFactory.getWebDriver()

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

WebUI.comment('TestcaseName:: '+'Test_3.2.2.5_Clear_Device_Data'+'\n')

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

WebUI.delay(3)

'To click Get device capability for port1'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/button_Get Device Data'))

try {
	WebUI.delay(30)

	driver.findElement(By.xpath('//button[contains(text(),\'Ok\')]')).click()
	WebUI.delay(10)
   driver.findElement(By.xpath('//button[contains(text(),\'Ok\')]')).click()
	WebUI.delay(80)
}
catch (Exception e) {
	WebUI.delay(90)
}

'click Load xml vif file button & verify windows file open dialogue and select xml file '
Path device_data_path = Paths.get(pc_xml_file_folder_path +'\\'+ device_data_vif_file_path)

'To load the xml file using load xml file button'
WebUI.callTestCase(findTestCase('Load_Xml_File_RobotFramework'), [('key1') : device_data_path], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(5)

'STEP 1: To click on clear device data'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/button_Clear_Device_Data'))

'STEP 2:To Verify Device data gets cleared in all Rows'
WebUI.callTestCase(findTestCase('xml_verifier/Test_Clear_Device_Data_Xml_File_Verifier'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.comment('********* Test_3.2.2.5_Clear_Device_Data Execution Ended *********'+'\n')

WebUI.comment('*****************************************************************************************************************************************************'+'\n')



'To call common Popup reader'
//WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()


