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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import groovy.time.TimeCategory as TimeCategory
import java.nio.file.*
import java.io.IOException as IOException
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.Alert as Alert
import org.openqa.selenium.By as By
import java.io.File as File

WebDriver driver = DriverFactory.getWebDriver()

'To Identify the vif table'

WebElement Table_data = driver.findElement(By.xpath('//table[@id=\'vif-table\']//tbody'))

'To locate rows of table it will Capture all the rows available in the table'
List rows_table = Table_data.findElements(By.tagName('tr'))

'To calculate no of rows In table'
int tot_rows_count = rows_table.size()

println(tot_rows_count)

driver.findElement(By.xpath(('//tbody[@id=\'vif-edit-table\']/tr[' + tot_rows_count) + ']/td')).click()

WebUI.delay(5)

'To locate table'
WebElement Table_of_child = driver.findElement(By.xpath('//table[@id=\'vif-table\']//tbody'))

'To locate rows of table it will Capture all the rows available in the table'
List rowstable = Table_of_child.findElements(By.tagName('tr'))

'To calculate no of rows In table'
int rows_of_child = rowstable.size()

println(rows_of_child)

//Loop:
'Loop will execute for all the rows of the table'
for (int row = rows_of_child; row > tot_rows_count; row--) {
	'To locate columns(cells) of that specific row'
	WebUI.delay(3)

	driver.findElement(By.xpath(('//tbody[@id=\'vif-edit-table\']/tr[' + row) + ']/td')).click()
}

WebUI.delay(3)

'To locate table'
WebElement original_Table = driver.findElement(By.xpath('//table[@id=\'vif-table\']//tbody'))

'To locate rows of table it will Capture all the rows available in the table'
List data = original_Table.findElements(By.tagName('tr'))

'To calculate no of rows In table'
int count_of_data = data.size()



'To locate table'
WebElement Table1 = driver.findElement(By.xpath('//table[@id=\'vif-table\']//tbody'))

'To locate rows of table it will Capture all the rows available in the table'
List rows_table1 = Table1.findElements(By.tagName('tr'))

'To calculate no of rows In table'
int rows_count1 = rows_table1.size()

//Loop:
//int row = 1; row <= rows_count+1; row++
'Loop will execute for all the rows of the table'
for (int row = 1; row <= rows_count1; row++) {
	//int i=0
	'To locate columns(cells) of that specific row'

	//List Columns_row = rows_table.get(row).findElements(By.tagName('td'))
	'To calculate no of columns(cells) In that specific row'

	//int columns_count = Columns_row.size()
	'Loop will execute till the last cell of that specific row'
	for (int column = 1; column <= 3; column++) {
		'It will retrieve text from each cell'
		WebElement celltext_val = driver.findElement(By.xpath(('//tbody[@id=\'vif-edit-table\']/tr[' + row) +']/td[3]'))

		println(celltext_val)
		String celltext = celltext_val.getText()
		if (celltext.equals('')||celltext==null) {
			println("******")
			}
		 else {
			 
			 println(celltext)
			 assert false
		 }
			
		}
	}

	'Browser Teardown'
	CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
	

