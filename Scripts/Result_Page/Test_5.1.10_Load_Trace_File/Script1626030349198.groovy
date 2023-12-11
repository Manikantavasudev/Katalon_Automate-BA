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
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import org.openqa.selenium.By as By
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import java.nio.file.*
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.WebElement as WebElement

'To read result data'
Map result_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Results_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

def user_config_vif_file_path  = result_data.result_page_data.Trace_File_Path

def Tracename= result_data.result_page_data.Trace

def exp_load_trace_popup_text = exp_load_trace_popup_txt

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

WebUI.delay(GlobalVariable.long_delay)

'driver object initialization'
WebDriver driver = DriverFactory.getWebDriver()

'To Execute Testcases'
WebUI.callTestCase(findTestCase('Result_Page/Common_TestExecution'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(GlobalVariable.long_delay)

'STEP 1:Click Load Trace Button'
WebUI.click(findTestObject('Object Repository/Result/Save_File/img_LoadTrace'))

WebUI.delay(GlobalVariable.short_delay)

'STEP 2:Verify that the following message appears'
def load_trace_popup_txt = WebUI.getText(findTestObject('Object Repository/Result/Load_Trace_File/strong_Load_Trace_Popup_Text'))

assert exp_load_trace_popup_text == load_trace_popup_txt

'STEP 3:Click Cancel '
WebUI.delay(GlobalVariable.short_delay)

WebUI.click(findTestObject('Object Repository/Result/Load_Trace_File/button_Cancel'))

'STEP 4:Verify that no action happens'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Result/Load_Trace_File/strong_Load_Trace_Popup_Text'), 5)

'STEP 5&6&7 :Click Load Trace Button & Click OK on the Warning Dialog & Verify that File Open Dialog Appears'
WebUI.click(findTestObject('Object Repository/Result/Save_File/img_LoadTrace'))

WebUI.delay(GlobalVariable.short_delay)

WebUI.click(findTestObject('Object Repository/Result/Load_Trace_File/button_Ok'))

WebUI.delay(GlobalVariable.short_delay)

StringSelection ss = new StringSelection(user_config_vif_file_path)

Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null)

Robot robot = new Robot()

robot.keyPress(KeyEvent.VK_ENTER)

robot.keyRelease(KeyEvent.VK_ENTER)

robot.keyPress(KeyEvent.VK_CONTROL)

robot.keyPress(KeyEvent.VK_V)

robot.keyRelease(KeyEvent.VK_V)

robot.keyRelease(KeyEvent.VK_CONTROL)

robot.keyPress(KeyEvent.VK_ENTER)

robot.keyRelease(KeyEvent.VK_ENTER)

StringSelection ss2 = new StringSelection(Tracename)

Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss2, null)

robot.keyPress(KeyEvent.VK_CONTROL)

robot.keyPress(KeyEvent.VK_V)

robot.keyRelease(KeyEvent.VK_V)

robot.keyRelease(KeyEvent.VK_CONTROL)

robot.keyPress(KeyEvent.VK_ENTER)

robot.keyRelease(KeyEvent.VK_ENTER)


'STEP 8&9:Select a Trace File from location '
WebUI.delay(GlobalVariable.long_delay)

'STEP 10&11:Verify that Selected Trace Files gets loaded in Results View &Verify that the Test Case name gets reflected in the Test Results Panel'

def loaded_testcase_name = WebUI.getAttribute(findTestObject('Object Repository/Result/Load_Trace_File/span_Loaded_Testcase_Name'), 'innerText')

WebUI.comment('Loaded Testcase Name in Result view :: ' + loaded_testcase_name)

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()

