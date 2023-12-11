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

'To read result data'
Map Help_data = WebUI.callTestCase(findTestCase('Test Cases/Common_Data_Reader/Popup_Handler/Help_tab'), [:], FailureHandling.STOP_ON_FAILURE)

def expected_text_about_app = Help_data.Help_string_value.Expected_text_about_app
def expected_description_para1 = Help_data.Help_string_value.Expected_description_para1
def expected_description_para2 = Help_data.Help_string_value.Expected_description_para2

'STEP 1:Click Help panel .'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Help'))

WebUI.delay(GlobalVariable.short_delay)

'STEP 2:Verify GLR icon is present in Help panel.(In latest UI GRL icon is removed)'
//WebUI.verifyElementPresent(findTestObject('Object Repository/Help_panel_Objects/Page_Help/img_GRL_Icon'), 5)

//WebUI.verifyElementVisible(findTestObject('Object Repository/Help_panel_Objects/Page_Help/img_GRL_Icon'))

'STEP 3:Verify Name and App Version is present or displayed in help panel.'
WebUI.verifyElementVisible(findTestObject('Object Repository/Help_panel_Objects/Page_Help/div_Help_Icon_App_Version'))


'STEP 4:Verify the Version and Name of software by comparing the Main bar and  help panel.'
def app_version_text = WebUI.getText(findTestObject('Object Repository/Help_panel_Objects/Page_Help/div_Help_Icon_App_Version'))

def app_ver_in_helpicon = app_version_text.minus('App Version : ')

WebUI.comment('Application Version in Help panel::' + app_ver_in_helpicon)

def mainbar_app_name = WebUI.getText(findTestObject('Object Repository/Help_panel_Objects/Page_Help/p_Application_Version_Mainbar'))

String expected_app_ver = mainbar_app_name.substring(mainbar_app_name.indexOf('(') + 1, mainbar_app_name.indexOf(')'))

WebUI.comment(expected_app_ver)

if (expected_app_ver.trim() == app_ver_in_helpicon.trim()) {
    WebUI.comment('App Version name is same as in Main Bar:: ' + expected_app_ver)
} else {
    WebUI.comment('App Version name is not matching with Main Bar:: ' + expected_app_ver)

    assert false
}

'STEP 5:Verify Text(GRL-USB-PD-C2: The only instrument you need for USB Type-C Power Delivery and Alt Mode compliance testing.) is Present and Visible.'
def actual_text_about_app = WebUI.getText(findTestObject('Object Repository/Help_panel_Objects/Page_Help/div_GRL_USB_Instrument_Info'))

if (expected_text_about_app == actual_text_about_app) {
    WebUI.comment('The text about C2 app is Proper.')
} else {
    WebUI.comment('The text is not Proper')

    assert false
}

'STEP 6:Verify that Overview and Description is Present and visible in Help panel.'
WebUI.delay(GlobalVariable.short_delay)

WebUI.verifyElementPresent(findTestObject('Object Repository/Help_panel_Objects/Page_Help/h2_Overview'), 5)

WebUI.verifyElementPresent(findTestObject('Object Repository/Help_panel_Objects/Page_Help/h2_Description'), 5)

'To verify Description Text'
def actual_description_para1 = WebUI.getText(findTestObject('Object Repository/Help_panel_Objects/Page_Help/div_Description_Paragraph1'))

def actual_description_para2 = WebUI.getText(findTestObject('Object Repository/Help_panel_Objects/Page_Help/div_Description_Paragraph2'))

println(actual_description_para1)
println(expected_description_para1)
println("vvfchjc")
println(actual_description_para2)
println(expected_description_para2)


if ((actual_description_para1 == expected_description_para1) && (actual_description_para2 == expected_description_para2)) {
    WebUI.comment('Description paragraph 1 Text is Present  as Expected:: ' + actual_description_para1)

    WebUI.comment('Description paragraph 2 Text is Present  as Expected:: ' + actual_description_para2)
} else {
    WebUI.comment('Description paragraph 1 and 2 Text is Present  as Expected::')

    assert false
}

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()



