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
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.KeyEvent
import internal.GlobalVariable
import java.awt.Robot
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver

WebDriver driver = DriverFactory.getWebDriver()

WebElement Table_data = driver.findElement(By.xpath('//table[@id=\'vif-table\']//tbody'))

'To locate rows of table it will Capture all the rows available in the table'
List rows_table = Table_data.findElements(By.tagName('tr'))

'To calculate no of rows In table'
int tot_rows_count = rows_table.size()

println(tot_rows_count)

driver.findElement(By.xpath(('//tbody[@id=\'vif-edit-table\']/tr[' + tot_rows_count) + ']/td')).click()

WebUI.delay(GlobalVariable.short_delay)

//List<WebElement> Column = driver.findElement(By.xpath('//*[@id="vif-table"]/thead/tr/th[1]')).click()

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
	//List<WebElement> Columns_row = rowstable.get(row).findElements(By.tagName('td'))
	'To locate columns(cells) of that specific row'
	WebUI.delay(GlobalVariable.short_delay)

	driver.findElement(By.xpath(('//tbody[@id=\'vif-edit-table\']/tr[' + row) + ']/td')).click()

	'To calculate no of columns(cells) In that specific row' 
}

WebUI.delay(GlobalVariable.short_delay)

'To locate table'
WebElement original_Table = driver.findElement(By.xpath('//table[@id=\'vif-table\']//tbody'))

'To locate rows of table it will Capture all the rows available in the table'
List data = original_Table.findElements(By.tagName('tr'))

'To calculate no of rows In table'
int count_of_data = data.size()

println(count_of_data)

WebUI.delay(GlobalVariable.short_delay)





