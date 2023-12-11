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
import org.openqa.selenium.WebElement

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

WebUI.delay(GlobalVariable.long_delay)

'driver object initialization'
WebDriver driver = DriverFactory.getWebDriver()

'To Execute Testcases'
WebUI.callTestCase(findTestCase('Result_Page/Common_TestExecution'), [:], FailureHandling.STOP_ON_FAILURE)

'STEP 1:Verify that after the test execution markers are displayed on the charts as shown below'
def enable_marker_text = WebUI.getAttribute(findTestObject('Object Repository/Result/Enable_Disable_Markers/Enable_Disable_Markers_Common'),
		'class')

if (enable_marker_text.contains('grey')) {
	WebUI.comment('Horizontal Markers are Enabled state')
} else {
	WebUI.comment('Horizontal Markers are not in  Enabled state')

	assert false

	WebUI.takeScreenshot()
}
'STEP 2&3 :Drag the Red and Blue  Marker and verify that time and Vbus and delta Time values are getting updated'

//def red_marker_val=driver.findElement(By.xpath("(//div[@class='marker-label'])[1]")).getText()
def red_marker_val = WebUI.getText(findTestObject('Object Repository/Marker/red_marker1'))
println(red_marker_val)

def  blue_marker_val=WebUI.getText(findTestObject("Object Repository/Marker/Blue_marker_1"))
println(blue_marker_val)

WebUI.dragAndDropByOffset(findTestObject("Object Repository/Marker/red_marker1"), 60, 0)

WebElement scrollArea = driver.findElement(By.xpath("(//div[@class='marker-label'])[1]"));
WebUI.delay(GlobalVariable.short_delay)
// Scroll to div's most right:
((JavascriptExecutor) driver).executeScript("arguments[0].scrollRight = arguments[0].offsetWidth", scrollArea);

WebUI.delay(GlobalVariable.short_delay)
WebElement scrollArea1 = driver.findElement(By.xpath("(//div[@class='marker-label'])[2]"));
WebUI.delay(GlobalVariable.short_delay)
// Scroll to div's most right:
((JavascriptExecutor) driver).executeScript("arguments[0].scrollRight = arguments[0].offsetWidth", scrollArea1);


def red_label1=driver.findElement(By.xpath("(//div[@class='marker-label'])[1]")).getText()
println(red_label1)

def blue_label1=driver.findElement(By.xpath("(//div[@class='marker-label'])[2]")).getText()
println(blue_label1)

'STEP 4 &5: Click on Enable Disable Marker & Verify that Markers get hidden'

WebUI.delay(GlobalVariable.short_delay)

WebUI.click(('Object Repository/Result/Enable_Disable_Markers/Enable_Disable_Markers_Common'))
if(!(enable_marker_text.contains('grey'))) {
	WebUI.comment('Horizontal Markers are in Disabled state')
}
else {
	WebUI.comment('Horizontal Markers are in Enabled state')

	WebUI.takeScreenshot()
}


'STEP 6 &Hover on Enable Disable Markers'
try {
	WebUI.doubleClick(findTestObject('Object Repository/Result/Enable_Disable_Markers/Enable_Disable_Markers_Common'))

	WebUI.delay(GlobalVariable.long_delay)

	'STEP 7&Verify that Enable/Disable Horizontal Markers message appears'
	def tooltip_help_icon_info =driver.findElement(By.xpath("//div[@role='tooltip']")).getText()

	WebUI.comment('Enable/Horizontal Markers Tooltip Info :: '+tooltip_help_icon_info)


}
catch (Exception e) {
	WebUI.takeScreenshot()
}

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()





