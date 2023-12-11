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
import org.openqa.selenium.support.Color as Color

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

WebUI.delay(GlobalVariable.long_delay)

'driver object initialization'
WebDriver driver = DriverFactory.getWebDriver()

'To read result data'
Map result_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Results_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

def user_config_vif_file_path  = result_data.result_page_data.Trace_File_Path

String channel_list_locator = '//div[@class="custom-dropdown-items-container"]/label'

'To click Result page'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Results'))

WebUI.delay(GlobalVariable.short_delay)

//WebUI.click(findTestObject('Object Repository/Send_keys_function/Upload_trace_file'))

//WebUI.sendKeys(findTestObject('Object Repository/Send_keys_function/Upload_trace_file'), user_config_vif_file_path)

WebUI.callTestCase(findTestCase('Load_Trace_File_RobotFramework'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(20)

List channel_list = []
try {
	
	WebUI.click(findTestObject('Object Repository/Result/Live_Button/img_channel_dropdown'))
	
	List channel_drpdwn_list_elements = driver.findElements(By.xpath(channel_list_locator))
	
		
    for (WebElement channel_drpdwn_list_element : channel_drpdwn_list_elements) {
		
		WebUI.delay(GlobalVariable.short_delay)
        channel_list.add(channel_drpdwn_list_element.getAttribute("innerText"))
		
    }
}
catch (Exception e) {
    WebUI.takeScreenshot()

} 

if (channel_list.size()>0) {

WebUI.comment(('Channel List::' + channel_list) + '\n')

}

else {
	WebUI.comment(('Channel List is Empty::' + channel_list) + '\n')
	assert false
}
'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()




