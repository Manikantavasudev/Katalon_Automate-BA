import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import java.awt.Toolkit as Toolkit
import java.awt.datatransfer.StringSelection as StringSelection
import java.awt.event.KeyEvent as KeyEvent
import internal.GlobalVariable as GlobalVariable
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
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper as MobileElementCommonHelper
import groovy.transform.CompileStatic as CompileStatic
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.exception.WebElementNotFoundException as WebElementNotFoundException
import org.openqa.selenium.support.ui.Select as Select
import java.nio.file.*

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

WebUI.delay(GlobalVariable.long_delay)

'To find downloads folder directory and debug log file'

String home_path = System.getProperty('user.home')

File downloaded_log_file = new File(((home_path + '/Downloads/') + 'Debug_Log') + '.txt')

boolean file_exists_sts = downloaded_log_file.exists()

'STEP 1:Click Help panel .'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Help'))

'STEP 2:Verify Download Debug Logs link is present and Visible.'
def Link_is_Present = WebUI.verifyElementPresent(findTestObject('Help_panel_Objects/Page_Help/a_Download_Debug_Logs'), 5)

WebUI.comment(' Debug Logs Link is present :: '+Link_is_Present)

def Link_is_Visible = WebUI.verifyElementVisible(findTestObject('Help_panel_Objects/Page_Help/a_Download_Debug_Logs'))

WebUI.comment('Debug Logs Link is Visible :: '+Link_is_Visible)

'Deleting the debug_log text file if exists'
if (file_exists_sts) {
    boolean filedeleted = downloaded_log_file.delete()

    WebUI.comment('Existing Debug Logs Succesfully deleted without any issues::' + filedeleted)
}

'STEP 3:Verify the link is clickable and Click the link'
def Link_is_Clickable = WebUI.verifyElementClickable(findTestObject('Help_panel_Objects/Page_Help/a_Download_Debug_Logs'), 
    FailureHandling.STOP_ON_FAILURE)

WebUI.comment('Debug Logs Link is clickable ::' + Link_is_Clickable)

WebUI.click(findTestObject('Help_panel_Objects/Page_Help/a_Download_Debug_Logs'))

WebUI.delay(GlobalVariable.short_delay)

'STEP 4:Verify it get downloaded in default downloads folder'

if (file_exists_sts) {
	
    WebUI.comment('Debug Logs present in Downloads')
} 
else {
	
    WebUI.comment('Debug Logs is not Downloaded')
}

WebUI.delay(GlobalVariable.short_delay)

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()



