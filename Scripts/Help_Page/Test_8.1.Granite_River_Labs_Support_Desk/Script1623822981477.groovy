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

String exp_help_desk_url = Expected_Help_Desk_URL

WebUI.delay(GlobalVariable.long_delay)

'STEP 1.Click Help panel'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Help'))

WebUI.delay(GlobalVariable.short_delay)

'STEP 2.Verify Granite River Labs Support Desk link is present and Visible.'
WebUI.verifyElementVisible(findTestObject('Help_panel_Objects/Page_Help/a_Granite_River_Labs_Support_Desk'))

WebUI.verifyElementPresent(findTestObject('Help_panel_Objects/Page_Help/a_Granite_River_Labs_Support_Desk'), 5)

'3.Verify the link is clickable and Click the link'
WebUI.verifyElementClickable(findTestObject('Help_panel_Objects/Page_Help/a_Granite_River_Labs_Support_Desk'))

WebUI.click(findTestObject('Help_panel_Objects/Page_Help/a_Granite_River_Labs_Support_Desk'))

'To switch to help desk url window'
WebUI.switchToWindowIndex(1)

WebUI.delay(GlobalVariable.long_delay)

'4.Verify it navigates to https://graniteriverlabs.freshdesk.com/support/login/ webpage'
def actual_help_desk_url = WebUI.getUrl()

WebUI.comment(actual_help_desk_url)

'Verify the Expected and Acutal Link'
if (exp_help_desk_url == actual_help_desk_url) {
    WebUI.comment('The link is Navigating to Granite River Support Labs')
} else {
    WebUI.comment('Link is not Navigating to Granite River Support Labs ')

    assert false
}

WebUI.delay(GlobalVariable.short_delay)

'To close the help desk url window'
WebUI.closeWindowUrl(actual_help_desk_url)

'To switch the localhost window'
WebUI.switchToWindowIndex(0)

WebUI.delay(GlobalVariable.short_delay)

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()



