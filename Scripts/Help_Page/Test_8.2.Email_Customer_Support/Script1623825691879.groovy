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

WebUI.delay(GlobalVariable.long_delay)

'STEP 1:Click Help panel .'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Help'))

'STEP 2:Verify Email Customer Support link is present and Visible.'
def link_is_present = WebUI.verifyElementPresent(findTestObject('Help_panel_Objects/Page_Help/a_Email_Customer_Support'), 
    5)

WebUI.comment('Email Customer Support Link is present :: ' + link_is_present)

def link_is_visible = WebUI.verifyElementVisible(findTestObject('Help_panel_Objects/Page_Help/a_Email_Customer_Support'))

WebUI.comment('Email Customer Support Link is Visible ::' + link_is_visible)

assert link_is_present && link_is_visible

'STEP 3:Verify the link is clickable and Click the link'
def link_is_clickable = WebUI.verifyElementClickable(findTestObject('Help_panel_Objects/Page_Help/a_Email_Customer_Support'), 
    FailureHandling.STOP_ON_FAILURE)

WebUI.comment('Link is clickable ::' + link_is_clickable)

assert link_is_clickable

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()



