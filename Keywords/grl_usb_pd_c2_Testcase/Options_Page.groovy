package grl_usb_pd_c2_Testcase

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable
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
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import groovy.transform.CompileStatic
import org.openqa.selenium.WebElement
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import org.openqa.selenium.support.ui.Select
import java.nio.file.*;
import org.openqa.selenium.support.Color;


public class Options_Page {

	@CompileStatic
	@Keyword
	//'To get Options page Configuration dropdown list '
	def getOptionsConfigDropdownList (TestObject dropdown_icon,String dropdown_list) {

		WebDriver driver = DriverFactory.getWebDriver()
		List options_dropdown_list_values=[]
		'To click dropdown icon'
		WebUI.delay(5)
		WebUI.click(dropdown_icon)
		'To get dropdown list'
		List<WebElement> options_drpdwn_list_elements = driver.findElements(By.xpath(dropdown_list))
		WebUI.comment('The size of elements is ::' +  options_drpdwn_list_elements.size()+'\n')
		try {
			if( options_drpdwn_list_elements.size()>0) {
				for (WebElement  options_drpdwn_list_element : options_drpdwn_list_elements) {
					options_dropdown_list_values.add(options_drpdwn_list_element.getText())
				}
				WebUI.comment('Options Page Configuration dropdown list::'+options_dropdown_list_values+'\n')
				WebUI.delay(5)
				WebUI.click(dropdown_icon)
				return options_dropdown_list_values
			}
			else {
				WebUI.comment(' Dropdown Element is not in visible state'+'\n')
				assert false
			}
		}
		catch (Exception e) {
			WebUI.takeScreenshot()
			return null
		}
	}

	@CompileStatic
	@Keyword
	//'To verify the table data and Convert the data to list'
	def tableDataValueReader (String table_data_locator) {
		List table_header_and_data_list=[]
		try {
			WebDriver driver = DriverFactory.getWebDriver()
			List table_row_list = driver.findElements(By.xpath(table_data_locator))
			int table_row_size  = table_row_list.size()
			WebUI.comment('table_row_size:: '+table_row_size+'\n')
			for (int i = 1; i <= table_row_size; i++) {
				'To read table data and convert to list'
				WebElement table_data_element = driver.findElement(By.xpath('('+table_data_locator+')['+i+']'))
				String table_data_text = table_data_element.getAttribute('innerText')
				table_header_and_data_list.add(table_data_text.trim())
			}
			WebUI.comment('table_header_and_data_list:: '+table_header_and_data_list+'\n')
			return table_header_and_data_list
		}
		catch (Exception e) {
			WebUI.takeScreenshot()
			return null
		}

	}













}
