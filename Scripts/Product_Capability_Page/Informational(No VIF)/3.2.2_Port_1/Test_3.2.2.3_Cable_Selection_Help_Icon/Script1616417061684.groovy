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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.By as By

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

WebDriver driver = DriverFactory.getWebDriver()


WebUI.comment('*****************************************************************************************************************************************************'+'\n')

WebUI.comment('TestcaseName:: '+'Test_3.2.2.3_Cable_Selection_Help_Icon'+'\n')

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

WebUI.delay(GlobalVariable.short_delay)

'To click Compliance Radio Button'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/input_Compliance_pcComplianceTestRadioBtn'))

'STEP 1:Click in help Icon next to Cable Selection DropDown List'
WebUI.doubleClick(findTestObject('Object Repository/Product_Capability_Objects/Compliance/div_Cabel_Help_Icon'))

WebUI.delay(GlobalVariable.long_delay)

def tooltip_help_icon_info =driver.findElement(By.xpath("//div[@role='tooltip']")).getText()

'STEP 2 :Verify the following tooltip message'
def exp_tooltip_info = GlobalVariable.cable_selection_help_icon

assert exp_tooltip_info == tooltip_help_icon_info

WebUI.comment('tooltip_help_icon_info is ::  '+tooltip_help_icon_info+'\n')

WebUI.comment('********* Test_3.2.2.3_Cable_Selection_Help_icon *********'+'\n')

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()

