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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.By as By
import groovy.transform.CompileStatic
import org.openqa.selenium.WebElement
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import org.openqa.selenium.support.ui.Select
import java.nio.file.*;

import internal.GlobalVariable

public class Report_Panel {


	@CompileStatic
	@Keyword
	//'To get Table 1 values'
	def getTable1DataValueInReport() {

		WebDriver driver = DriverFactory.getWebDriver()
		'To read table 1'
		List all_header_table1_data = []

		'Locator for read table1'
		List table_1_header_list = driver.findElements(By.xpath('//table[1]/tbody/tr/th/p/b'))

		'To locate table data header'
		int table1_header_size = table_1_header_list.size()

		for (int i = 1; i <= table1_header_size; i++) {
			WebElement table1_header_webele = driver.findElement(By.xpath(('(//table[1]/tbody/tr/th/p/b)[' + i) + ']'))

			String table1_header_val = table1_header_webele.getText()

			all_header_table1_data.add(table1_header_val.trim())
		}


		return all_header_table1_data
	}

	@CompileStatic
	@Keyword
	//'To get Table 2 values'
	def getTable2DataValueInReport() {

		WebDriver driver = DriverFactory.getWebDriver()
		List all_header_table2_data = []

		'Locator for read table2'
		List table_2_header_list = driver.findElements(By.xpath('//table[2]/tbody/tr/th/p/b'))

		'To locate table data header'
		int table2_header_size = table_2_header_list.size()

		for (int i = 1; i <= table2_header_size; i++) {
			WebElement table2_header_webele = driver.findElement(By.xpath(('(//table[2]/tbody/tr/th/p/b)[' + i) + ']'))

			String table2_header_val = table2_header_webele.getText()

			all_header_table2_data.add(table2_header_val.trim())
		}


		return all_header_table2_data
	}

	@CompileStatic
	@Keyword
	//'To get Table 3 values'
	def getTable3DataValueInReport() {

		WebDriver driver = DriverFactory.getWebDriver()
		'STEP 7:To read table 3'
		List all_header_table3_data = []

		'Locator for read table3'
		List table_3_header_list = driver.findElements(By.xpath('//table[3]/tbody/tr/th/p/b'))

		'To locate table data header'
		int table3_header_size = table_3_header_list.size()

		for (int i = 1; i <= table3_header_size; i++) {
			WebElement table3_header_webele = driver.findElement(By.xpath(('(//table[3]/tbody/tr/th/p/b)[' + i) + ']'))

			String table3_header_val = table3_header_webele.getText()

			all_header_table3_data.add(table3_header_val.trim())
		}


		return all_header_table3_data
	}

	@CompileStatic
	@Keyword
	//'To get Table 4 values'
	def getTable4DataValueInReport() {

		WebDriver driver = DriverFactory.getWebDriver()
		List all_header_table4_data = []

		'Locator for read table4'
		List table_4_header_list = driver.findElements(By.xpath('//table[4]/tbody/tr/th/p/b'))

		'To locate table data header'
		int table4_header_size = table_4_header_list.size()

		for (int i = 1; i <= table4_header_size; i++) {
			WebElement table4_header_webele = driver.findElement(By.xpath(('(//table[4]/tbody/tr/th/p/b)[' + i) + ']'))

			String table4_header_val = table4_header_webele.getText()

			all_header_table4_data.add(table4_header_val.trim())
		}


		return all_header_table4_data
	}


	@CompileStatic
	@Keyword
	//'To get Table 5 values'
	def getTable5DataValueInReport() {

		WebDriver driver = DriverFactory.getWebDriver()
		List all_header_table5_data = []

		'Locator for read table5'
		List table_5_header_list = driver.findElements(By.xpath('//table[5]/tbody/tr/td[1]/p/b'))

		'To locate table data header'
		int table5_header_size = table_5_header_list.size()

		println(table5_header_size)

		for (int i = 1; i <= table5_header_size; i++) {
			WebElement table5_header_col1_webele = driver.findElement(By.xpath(('(//table[5]/tbody/tr/td[1]/p/b)[' + i) + ']'))

			WebElement table5_header_col2_webele = driver.findElement(By.xpath(('(//table[5]/tbody/tr/td[2]/p/b)[' + i) + ']'))

			String table5_header_col1_val = table5_header_col1_webele.getText()

			String table5_header_col2_val = table5_header_col2_webele.getText()

			all_header_table5_data.add((table5_header_col1_val.trim() + ':') + table5_header_col2_val.trim())
		}

		return all_header_table5_data
	}

	@CompileStatic
	@Keyword
	//'To get Table 6 values'
	def getTable6DataValueInReport() {

		WebDriver driver = DriverFactory.getWebDriver()
		List all_header_table6_data = []

		'Locator for read table5'
		List table_6_header_list = driver.findElements(By.xpath('//table[6]/tbody/tr/td[1]/p/b'))

		'To locate table data header'
		int table6_header_size = table_6_header_list.size()

		println(table6_header_size)

		for (int i = 1; i <= table6_header_size; i++) {
			WebElement table6_header_col1_webele = driver.findElement(By.xpath(('(//table[6]/tbody/tr/td[1]/p/b)[' + i) + ']'))

			WebElement table6_header_col2_webele = driver.findElement(By.xpath(('(//table[6]/tbody/tr/td[2]/p/b)[' + i) + ']'))

			String table6_header_col1_val = table6_header_col1_webele.getText()

			String table6_header_col2_val = table6_header_col2_webele.getText()

			all_header_table6_data.add((table6_header_col1_val.trim() + ':') + table6_header_col2_val.trim())
		}


		return all_header_table6_data
	}

	@CompileStatic
	@Keyword
	//'To get Table 7 values'
	def getTable7DataValueInReport() {

		WebDriver driver = DriverFactory.getWebDriver()
		List all_header_table7_data = []

		'Locator for read table7'
		List table_7_header_list = driver.findElements(By.xpath('//table[7]/tbody/tr/td[1]/p/b'))

		'To locate table data header'
		int table7_header_size = table_7_header_list.size()

		println(table7_header_size)

		for (int i = 1; i <= table7_header_size; i++) {
			WebElement table7_header_col1_webele = driver.findElement(By.xpath(('(//table[7]/tbody/tr/td[1]/p/b)[' + i) + ']'))

			WebElement table7_header_col2_webele = driver.findElement(By.xpath(('(//table[7]/tbody/tr/td[2]/p/b)[' + i) + ']'))

			String table7_header_col1_val = table7_header_col1_webele.getText()

			String table7_header_col2_val = table7_header_col2_webele.getText()

			all_header_table7_data.add((table7_header_col1_val.trim() + ':') + table7_header_col2_val.trim())
		}


		return all_header_table7_data
	}

	@CompileStatic
	@Keyword
	//'To get Table 7 values'
	def liveButtonStatusReading() {
		WebDriver driver = DriverFactory.getWebDriver()
		'To click Testconfig page'
		WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Test Config'))

		'Click Start Execution Button'
		WebUI.click(findTestObject('Object Repository/Test_Config_Panel/button_Start Execution'))

		'Verify that Results Panel gets selected & Wait for Execution Competed'
		def run_status

		try {
			run_status = true

			while (run_status) {
				'To check Unlive button status'
				def sts = driver.findElement(By.xpath('//button/img[@src=\'../../images/chartIcons/PNG/Live.png\']'))

				if (sts) {
					WebUI.delay(10)

					run_status = true
				}
			}
			WebUI.delay(5)
		}
		catch (Exception e) {
			run_status = false
		}
	}














































}
