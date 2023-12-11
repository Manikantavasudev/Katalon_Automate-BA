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
WebUI.openBrowser('')
WebUI.navigateToUrl('http://localhost:5001/')
WebUI.maximizeWindow()
WebUI.verifyElementClickable(findTestObject('Object Repository/Page_GRL - USB PD C2/button_Connect'))
WebUI.verifyElementClickable(findTestObject('Object Repository/Product_Capability_Objects/click _Eload_update'))

update_Eload_text_sts = WebUI.getText(findTestObject('Object Repository/Product_Capability_Objects/click _Eload_update'))

println(update_Eload_text_sts)
if(update_Eload_text_sts == "Update E-Load Firmware")
{
	WebUI.comment('update c2 ELoad button name proper as expected')
}
else
{
	WebUI.comment('update c2 ELoad name is not expected')
	
}
WebUI.refresh()

WebUI.delay(8)
WebUI.refresh()
WebUI.delay(8)
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()