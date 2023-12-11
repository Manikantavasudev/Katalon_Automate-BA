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

'5.Clicking Test Config Panel'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Test Config'))

'Selecting One testcase'
WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Start_and_Stop/span_Power Delivery 3.0 Tests_rc-tree-checkbox'))

WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Start_and_Stop/span_C2 Test Cases_rc-tree-switcher rc-tree_99445a'))

WebUI.delay(GlobalVariable.short_delay)

'1.\tVerify that Start Execution is in green Test'
def Start_execution_text_color = WebUI.getCSSValue(findTestObject('Object Repository/Test_Config_Panel/Start_and_Stop/button_Start Execution'), 'color')

WebUI.verifyEqual(Start_execution_text_color, 'rgba(0, 128, 0, 1)')

'2.\tVerify that button background color is White'
def Start_Execution_button_bef_mouse_over = WebUI.getCSSValue(findTestObject('Object Repository/Test_Config_Panel/Start_and_Stop/button_Start Execution'),'background-color')

WebUI.verifyEqual(Start_Execution_button_bef_mouse_over, 'rgba(255, 255, 255, 1)')

'3.\tHover over Start Test Execution Button'
WebUI.mouseOver(findTestObject('Object Repository/Test_Config_Panel/Start_and_Stop/button_Start Execution'))

'4.\tVerify the Button Background color changes to green'
def Start_exe_after_mouse_over_Background_color = WebUI.getCSSValue(findTestObject('Object Repository/Test_Config_Panel/Start_and_Stop/button_Start Execution'), 'background-color')

WebUI.verifyEqual(Start_exe_after_mouse_over_Background_color, 'rgba(0, 128, 0, 1)')

'6.\tClick Start Execution Button'
WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Start_and_Stop/button_Start Execution'))

WebUI.delay(GlobalVariable.short_delay)

'7.\tVerify that Results panel gets selected'
WebUI.verifyElementPresent(findTestObject('Object Repository/Test_Config_Panel/Start_and_Stop/text_sum'), 5)

'8.\tVerify that The Button Text Gets changes to Stop Execution '
WebUI.verifyElementText(findTestObject('Object Repository/Test_Config_Panel/Start_and_Stop/button_Stop Execution'), 'Stop Execution')

'9.\tVerify that the text color is red in color '
def Stop_exe_text_color = WebUI.getCSSValue(findTestObject('Object Repository/Test_Config_Panel/Start_and_Stop/button_Stop Execution'), 'color')

WebUI.verifyEqual(Stop_exe_text_color, 'rgba(255, 0, 0, 1)')

'Wait for completion of Test Execution'
while (WebUI.getCSSValue(findTestObject('Object Repository/Test_Config_Panel/Start_and_Stop/button_Stop Execution'), 'color') == redcolor) {
    WebUI.delay(3)
}

WebUI.delay(GlobalVariable.short_delay)

'10.\tVerify that after test execution the Button Text changes to Start Execution in Green color'
def start_exe_text_color_after_execution = WebUI.getCSSValue(findTestObject('Object Repository/Test_Config_Panel/Start_and_Stop/After_exe'),'color')

WebUI.verifyEqual(start_exe_text_color_after_execution, 'rgba(0, 128, 0, 1)')

println(' that Start Execution is in green Test' + Start_execution_text_color)

println(' that button background color is White :' + Start_Execution_button_bef_mouse_over)

println(' The Button Background color changes to green :' + Start_exe_after_mouse_over_Background_color)

println(' the text color is red in color : ' + Stop_exe_text_color)

println('Stopping the execution and checking start execution text color ' + start_exe_text_color_after_execution)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
