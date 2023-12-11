import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
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
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import java.awt.Toolkit as Toolkit
import java.awt.datatransfer.StringSelection as StringSelection
import org.openqa.selenium.By as By
import org.openqa.selenium.support.ui.Select as Select
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper as MobileElementCommonHelper
import groovy.transform.CompileStatic as CompileStatic
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.exception.WebElementNotFoundException as WebElementNotFoundException
import org.openqa.selenium.support.Color as Color
'To open c2 server'
WebUI.callTestCase(findTestCase('Prerequisites_Script/Test_Prerequisites'), [:], FailureHandling.STOP_ON_FAILURE)


'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/SrcPdos_Data_reader'), [:], FailureHandling.STOP_ON_FAILURE)

def common_data = all_data.user_src_config_data



'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

'To click Product Capability page'
WebUI.delay(GlobalVariable.short_delay)

'To read Connection Status'
CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.getConnectionStsWithClickbtn'()

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Options_Configuration/Click_option'))
WebUI.delay(2)
WebUI.click(findTestObject('Object Repository/Options_Configuration/CLick_Config'))
WebUI.delay(2)
WebUI.click(findTestObject('Object Repository/Options_Configuration/CLick CC1'))
WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Options_Configuration/Click_cc2'))



WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Options_Configuration/Click_messageHeader'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Options_Configuration/Click_Data'))

WebUI.click(findTestObject('Object Repository/Options_Configuration/Message_Type'))

WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Options_Configuration/Src-Capability'))

WebUI.delay(2)

int i = 0
String totalPDOS = common_data.Total_PDOs
int numOFPDOs = Integer.parseInt(totalPDOS);

String[] voltage = [common_data._1PDOs_voltage, common_data._2PDOs_voltage, common_data._3PDOs_voltage, common_data._4PDOs_voltage
	, common_data._5PDOs_voltage, common_data._6PDOs_voltage, common_data._7PDOs_voltage]

String[] current = [common_data._1PDOs_current, common_data._2PDOs_current, common_data._3PDOs_current, common_data._4PDOs_current
	, common_data._5PDOs_current, common_data._6PDOs_current, common_data._7PDOs_current]

String[] currentValue = ['500', '1000', '1500', '2000', '2500', '3000']

WebDriver driver = DriverFactory.getWebDriver()



int j = 0
if (numOFPDOs >= 1 && numOFPDOs <= 7 )
{
	boolean increaseCurrent= true
	boolean decreaseCurrent = false
	int value = 0
	while (((value < currentValue.length) || (decreaseCurrent == false)) && (i <99))
	{

		'enter current'
		int k = 0
		WebUI.clearText(findTestObject('Object Repository/Options_Configuration/click_PDO'))
		WebUI.setText(findTestObject('Object Repository/Options_Configuration/click_PDO'),totalPDOS)
		WebUI.delay(1)

		if (numOFPDOs >= 1)
		{
			println(numOFPDOs)
			'voltage'
			static_ip = driver.findElement(By.xpath('(//input[@type="text"])[90]'))
			executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click();', static_ip)
			WebUI.delay(2)
			static_ip.clear();
			WebUI.delay(2)
			executor.executeScript("arguments[0].value='"+ voltage[1] +"';", static_ip);
			WebUI.delay(1)


			"current"
			static_ip = driver.findElement(By.xpath('(//input[@class="config-editor-textbox"])[3]'))
			executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click();', static_ip)
			WebUI.delay(2)
			static_ip.clear();
			WebUI.delay(2)
			executor.executeScript("arguments[0].value='"+ currentValue[value] +"';", static_ip);
			WebUI.delay(1)


		}

		if (numOFPDOs >= 2) {
			println(numOFPDOs)
			'voltage'
			static_ip = driver.findElement(By.xpath('(//input[@class="config-editor-textbox"])[4]'))
			executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click();', static_ip)
			WebUI.delay(2)
			static_ip.clear();
			WebUI.delay(2)
			executor.executeScript("arguments[0].value='"+ voltage[1] +"';", static_ip);
			WebUI.delay(1)


			"current"
			static_ip = driver.findElement(By.xpath('(//input[@class="config-editor-textbox"])[5]'))
			executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click();', static_ip)
			WebUI.delay(2)
			static_ip.clear();
			WebUI.delay(2)
			executor.executeScript("arguments[0].value='"+ currentValue[value] +"';", static_ip);
			WebUI.delay(1)

		}

		if (numOFPDOs >= 3) {
			'voltage'
			static_ip = driver.findElement(By.xpath('(//input[@class="config-editor-textbox"])[6]'))
			executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click();', static_ip)
			WebUI.delay(2)
			static_ip.clear();
			WebUI.delay(2)
			executor.executeScript("arguments[0].value='"+ voltage[1] +"';", static_ip);
			WebUI.delay(1)


			"current"
			static_ip = driver.findElement(By.xpath('(//input[@class="config-editor-textbox"])[7]'))
			executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click();', static_ip)
			WebUI.delay(2)
			static_ip.clear();
			WebUI.delay(2)
			executor.executeScript("arguments[0].value='"+ currentValue[value] +"';", static_ip);
			WebUI.delay(1)

		}

		if (numOFPDOs >= 4) {
			'voltage'
			static_ip = driver.findElement(By.xpath('(//input[@class="config-editor-textbox"])[8]'))
			executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click();', static_ip)
			WebUI.delay(2)
			static_ip.clear();
			WebUI.delay(2)
			executor.executeScript("arguments[0].value='"+ voltage[1] +"';", static_ip);
			WebUI.delay(1)


			"current"
			static_ip = driver.findElement(By.xpath('(//input[@class="config-editor-textbox"])[9]'))
			executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click();', static_ip)
			WebUI.delay(2)
			static_ip.clear();
			WebUI.delay(2)
			executor.executeScript("arguments[0].value='"+ currentValue[value] +"';", static_ip);
			WebUI.delay(1)

		}

		if (numOFPDOs >= 5) {
			'voltage'
			static_ip = driver.findElement(By.xpath('(//input[@class="config-editor-textbox"])[10]'))
			executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click();', static_ip)
			WebUI.delay(2)
			static_ip.clear();
			WebUI.delay(2)
			executor.executeScript("arguments[0].value='"+ voltage[1] +"';", static_ip);
			WebUI.delay(1)


			"current"
			static_ip = driver.findElement(By.xpath('(//input[@class="config-editor-textbox"])[11]'))
			executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click();', static_ip)
			WebUI.delay(2)
			static_ip.clear();
			WebUI.delay(2)
			executor.executeScript("arguments[0].value='"+ currentValue[value] +"';", static_ip);
			WebUI.delay(1)

		}

		if (numOFPDOs >= 6) {
			'voltage'
			static_ip = driver.findElement(By.xpath('(//input[@class="config-editor-textbox"])[12]'))
			executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click();', static_ip)
			WebUI.delay(2)
			static_ip.clear();
			WebUI.delay(2)
			executor.executeScript("arguments[0].value='"+ voltage[1] +"';", static_ip);
			WebUI.delay(1)


			"current"
			static_ip = driver.findElement(By.xpath('(//input[@class="config-editor-textbox"])[13]'))
			executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click();', static_ip)
			WebUI.delay(2)
			static_ip.clear();
			WebUI.delay(2)
			executor.executeScript("arguments[0].value='"+ currentValue[value] +"';", static_ip);
			WebUI.delay(1)


		}

		if (numOFPDOs == 7) {
			'voltage'
			static_ip = driver.findElement(By.xpath('(//input[@class="config-editor-textbox"])[14]'))
			executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click();', static_ip)
			WebUI.delay(2)
			static_ip.clear();
			WebUI.delay(2)
			executor.executeScript("arguments[0].value='"+ voltage[1] +"';", static_ip);
			WebUI.delay(1)


			"current"
			static_ip = driver.findElement(By.xpath('(//input[@class="config-editor-textbox"])[15]'))
			executor = ((driver) as JavascriptExecutor)
			executor.executeScript('arguments[0].click();', static_ip)
			WebUI.delay(2)
			static_ip.clear();
			WebUI.delay(2)
			executor.executeScript("arguments[0].value='"+ currentValue[value] +"';", static_ip);
			WebUI.delay(1)

		}


					WebUI.click(findTestObject('Object Repository/Options_Configuration/Click_Send'))
		
					WebUI.delay(1)
		
					WebUI.click(findTestObject('Object Repository/Options_Configuration/Click_Apply'))
		
					WebUI.delay(1)
		
					WebUI.click(findTestObject('Object Repository/Options_Configuration/Click_start'))
		
					WebUI.delay(5)
		
					WebUI.click(findTestObject('Object Repository/Options_Configuration/Click_option'))
					WebUI.delay(1)
		
					WebUI.click(findTestObject('Object Repository/Options_Configuration/Click_Attach'))
		
					WebUI.delay(2)
					WebUI.click(findTestObject('Object Repository/Options_Configuration/Click_result'))
		if(decreaseCurrent == false)
		{
			value++
		}

		if(decreaseCurrent == true)
		{
			value--
			if(value == -1)
			{
				decreaseCurrent = false
			}
		}
		if (value == currentValue.length)
		{
			decreaseCurrent = true
			value = currentValue.length-1
		}
		
					CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.readTestExecutionSts'()
		
					try {
						WebUI.click(findTestObject('Object Repository/Options_Configuration/CLick_stop'))
					}
					catch (Exception e) {
						WebUI.refresh()
		
						WebUI.delay(10)
		
						WebUI.click(findTestObject('Object Repository/Options_Configuration/CLick_stop'))
					}
		
					WebUI.delay(3)
		i++;
		println(i+" times")
	}
}
else
{
	println('pdos is invalid')
}


