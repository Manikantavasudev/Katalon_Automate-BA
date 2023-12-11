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

String user_config_vif_file_path = key1

WebUI.sendKeys(findTestObject('Object Repository/Send_keys_function/Load_xml_file'),user_config_vif_file_path)
//WebDriver driver = DriverFactory.getWebDriver()
//
//try {
//	Path path = Paths.get(user_config_vif_file_path)
//
//	Boolean file_path_sts = Files.exists(path)
//
//	println(file_path_sts)
//
//	if (file_path_sts) {
//		WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/button_Load XML VIF File'))
//
//		WebUI.delay(GlobalVariable.short_delay)
//
//		StringSelection ss = new StringSelection(user_config_vif_file_path)
//
//		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null)
//
//		'Used Robot framework for handling the windows filepath object'
//		Robot robot = new Robot()
//
//		robot.keyPress(KeyEvent.VK_ENTER)
//
//		robot.keyRelease(KeyEvent.VK_ENTER)
//
//		robot.keyPress(KeyEvent.VK_CONTROL)
//
//		robot.keyPress(KeyEvent.VK_V)
//
//		robot.keyRelease(KeyEvent.VK_V)
//
//		robot.keyRelease(KeyEvent.VK_CONTROL)
//
//		robot.keyPress(KeyEvent.VK_ENTER)
//
//		robot.keyRelease(KeyEvent.VK_ENTER)
//		
//		try {
//			WebUI.delay(GlobalVariable.long_delay)
//			
//			def popup_sts = WebUI.verifyElementPresent(findTestObject('Object Repository/Browser_Main_View_Objects/Popup_Display_div'),
//				5)
//		
//			if (popup_sts) {
//				WebUI.click(findTestObject('Object Repository/Browser_Main_View_Objects/Popup_OK'))
//		
//				WebUI.delay(GlobalVariable.short_delay)
//		
//				WebUI.takeScreenshot()
//			}
//		}
//		catch (Exception e) {
//			'ui screenshot'
//			WebUI.takeScreenshot()
//		}
//		
//	} else {
//		'FilePath is not valid'
//		WebUI.comment('User provided Filepath is not valid')
//	}
//}
//catch (Exception e) {
//	WebUI.comment('Error log '+e)
//
//	
//}



