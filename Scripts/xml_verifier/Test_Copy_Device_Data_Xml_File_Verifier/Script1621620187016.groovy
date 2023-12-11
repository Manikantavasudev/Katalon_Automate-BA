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
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.By as By
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper as MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import groovy.json.JsonSlurper
import groovy.json.JsonParserType
CustomKeywords.'custom.Logger.CustomLoggerFactory.customizeLogger4CommentKeyword'()

String user_config_vif_file_path=key1


WebUI.comment("UserConfiguredFilePathName::  "+user_config_vif_file_path+'\n')

WebUI.callTestCase(findTestCase('xml_verifier/Test_Xml_File_Expander'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(10)

WebDriver driver = DriverFactory.getWebDriver()

'To locate table'
WebElement Table = driver.findElement(By.xpath('//table[@id=\'vif-table\']//tbody'))

'To locate rows of table it will Capture all the rows available in the table'
List rows_table = Table.findElements(By.tagName('tr'))

'To calculate no of rows In table'
int rows_count = rows_table.size()

List cellvalue = []

List avoid_data = ['mA', 'mV', 'mW', 'hex']

//Loop:
//int row = 1; row <= rows_count+1; row++
'Loop will execute for all the rows of the table'
for (int row = 1; row <= rows_count; row++) {
	//int i=0
	'To locate columns(cells) of that specific row'

	//List Columns_row = rows_table.get(row).findElements(By.tagName('td'))
	'To calculate no of columns(cells) In that specific row'

	//int columns_count = Columns_row.size()
	'Loop will execute till the last cell of that specific row'
	for (int column = 1; column <= 3; column++) {
		'It will retrieve text from each cell'
		WebElement celltext_val = driver.findElement(By.xpath(((('//tbody[@id=\'vif-edit-table\']/tr[' + row) + ']/td[') +
				column) + ']'))

		println(celltext_val)

		String celltext = celltext_val.getText()

		if (celltext.equals('') || avoid_data.contains(celltext)) {
			try {
				WebElement celltextUpdated_val = driver.findElement(By.xpath(('//tbody[@id=\'vif-edit-table\']/tr[' + row) +
						']/td[2]/div/input'))

				def modified_celltext_val = celltextUpdated_val.getAttribute('value')

				cellvalue.add(modified_celltext_val)
			}
			catch (Exception e) {
				celltext_null_val = ''

				cellvalue.add(celltext_null_val)
			}
		} else {
			cellvalue.add(celltext)
		}
	}
}

	def result = false
	println('LIST'+cellvalue)
	def new_xml_list = cellvalue.collate(3)
	def total_valid_count = 0
	int xml_list_chunks = new_xml_list.size()

	for (int k = 0; k < xml_list_chunks; k++) {
		def all_col_data = new_xml_list[k]
		result = all_col_data[1]==all_col_data[2]
		print_report(all_col_data[1],all_col_data[2] ,result)
	}
	
def print_report(param,data,result) {
	WebUI.comment(((((' VIF Data is:      ' + (param)) + ' Device Data is: ') + (data)) + '     Verification result is ') +result+'\n')
}













































































