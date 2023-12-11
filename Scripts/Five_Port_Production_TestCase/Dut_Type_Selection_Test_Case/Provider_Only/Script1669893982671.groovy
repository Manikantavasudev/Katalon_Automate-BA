import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
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
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.exception.WebElementNotFoundException as WebElementNotFoundException
import org.openqa.selenium.support.ui.Select as Select
import groovy.json.JsonSlurper as JsonSlurper
import org.slf4j.LoggerFactory as LoggerFactory

println('************************************************ Dependency ************************************************')

'To initialize driver object'
WebDriver driver = DriverFactory.getWebDriver()

'Logging fucntionality'
def log = LoggerFactory.getLogger('some-logger')

'To read properties.json values'
def jsonSlurper = new JsonSlurper()

File prop_obj = new File('Test_Json/Provider_TestCase.json')

Map Test_info = new JsonSlurper().parseFile(prop_obj, 'UTF-8')

println('************************************************ Test Cases Execution is started ************************************************')

'Click the Test Config Button'
try {
    WebUI.verifyElementClickable(findTestObject('Object Repository/Test_Case_Selection/Test_config_button'))

    log.info('The Element is verified and its clickable one')
}
catch (Exception ex) {
    log.error('Element is verified and its not clickable at point exception')
} 

try {
	WebUI.click(findTestObject('Object Repository/Test_Case_Selection/Test_config_button'))
	log.info('The Element is clicked and move to another tab')
	WebUI.delay(3)
}catch (Exception x) {
	log.error('Element is not clickable at point exception')
}

'Verify that you are in Test Selection page'
if (WebUI.verifyElementText(findTestObject('Object Repository/Test_Case_Selection/Test_config_page'), 'Test Selection')) {
	log.info('In the Test Config Page')
} else {
	log.error('Not in Test Config Page')
}

def C2_Test_Cases = "$Test_info.Expand_Test_List.Top"
def add_C2_Test_Case_open = ('//li[@class=\'' + C2_Test_Cases) + ' rc-tree-treenode-switcher-open\']//span[@class=\'rc-tree-switcher rc-tree-switcher_open\']'
def add_C2_Test_Case_close = ('//li[@class=\'' + C2_Test_Cases) + ' rc-tree-treenode-switcher-close\']//span[@class=\'rc-tree-switcher rc-tree-switcher_close\']'

try {
	driver.findElement(By.xpath(add_C2_Test_Case_open)).click()
	WebUI.delay(3)
	driver.findElement(By.xpath(add_C2_Test_Case_close)).click()
}
catch (Exception x) {
	log.error('TreeNode Switcher is not clickable at point exception')
}


println('************************************************ Selecting the Cable Test Cases to run Tree_Switcher_1 ************************************************')

'Selecting the Cable Test Cases to run Tree_Switcher_1'
def Tree_Switcher_1 = "$Test_info.Expand_Test_List.Tree_Switcher_Start01.Tree_Switcher_1"
def USB_PD_close = ('//li[@class=\'' + Tree_Switcher_1) + ' rc-tree-treenode-switcher-close\']//span[@class=\'rc-tree-switcher rc-tree-switcher_close\']'
def USB_PD_open = ('//li[@class=\'' + Tree_Switcher_1) + ' rc-tree-treenode-switcher-open rc-tree-treenode-checkbox-indeterminate\']//span[@class=\'rc-tree-switcher rc-tree-switcher_open\']'

try {
	driver.findElement(By.xpath(USB_PD_close)).click()
	WebUI.delay(3)
}
catch (Exception x) {
	log.error('TreeNode Switcher is not clickable at point exception')
}

def Tree_Sub_Switcher_1_01 = "$Test_info.Expand_Test_List.Tree_Switcher_Start01.Tree_Sub_Switcher_1_01"
driver.findElement(By.xpath(('//span[text()=\'' + Tree_Sub_Switcher_1_01) + '\']')).click()
def Tree_Sub_Switcher_1_14 = "$Test_info.Expand_Test_List.Tree_Switcher_Start01.Tree_Sub_Switcher_1_14"
driver.findElement(By.xpath(('//span[text()=\'' + Tree_Sub_Switcher_1_14) + '\']')).click()
try {
	driver.findElement(By.xpath(USB_PD_open)).click()
	WebUI.delay(3)
}
catch (Exception x) {
	log.error('TreeNode Switcher is not clickable at point exception')
}

println('************************************************ Selecting the Cable Test Cases to run Tree_Switcher_2 ************************************************')

'Selecting the Cable Test Cases to run Tree_Switcher_2'
def Tree_Switcher_2 = "$Test_info.Expand_Test_List.Tree_Switcher_Start02.Tree_Switcher_2"
def PD_COMM_ENGINE_close = ('//li[@class=\'' + Tree_Switcher_2) + ' rc-tree-treenode-switcher-close\']//span[@class=\'rc-tree-switcher rc-tree-switcher_close\']'
def PD_COMM_ENGINE_open = ('//li[@class=\'' + Tree_Switcher_2) + ' rc-tree-treenode-switcher-open rc-tree-treenode-checkbox-indeterminate\']//span[@class=\'rc-tree-switcher rc-tree-switcher_open\']'

try {
	driver.findElement(By.xpath(PD_COMM_ENGINE_close)).click()
	WebUI.delay(3)
}
catch (Exception x) {
	log.error('TreeNode Switcher is not clickable at point exception')
}

def Tree_Sub_Switcher_2_01 = "$Test_info.Expand_Test_List.Tree_Switcher_Start02.Tree_Sub_Switcher_2_01"
driver.findElement(By.xpath(('//span[text()=\'' + Tree_Sub_Switcher_2_01) + '\']')).click()
def Tree_Sub_Switcher_2_02 = "$Test_info.Expand_Test_List.Tree_Switcher_Start02.Tree_Sub_Switcher_2_02"
driver.findElement(By.xpath(('//span[text()=\'' + Tree_Sub_Switcher_2_02) + '\']')).click()
def Tree_Sub_Switcher_2_03 = "$Test_info.Expand_Test_List.Tree_Switcher_Start02.Tree_Sub_Switcher_2_03"
driver.findElement(By.xpath(('//span[text()=\'' + Tree_Sub_Switcher_2_03) + '\']')).click()
def Tree_Sub_Switcher_2_04 = "$Test_info.Expand_Test_List.Tree_Switcher_Start02.Tree_Sub_Switcher_2_04"
driver.findElement(By.xpath(('//span[text()=\'' + Tree_Sub_Switcher_2_04) + '\']')).click()

try {
	driver.findElement(By.xpath(PD_COMM_ENGINE_open)).click()
	WebUI.delay(3)
}
catch (Exception x) {
	log.error('TreeNode Switcher is not clickable at point exception')
}


println('************************************************ Selecting the Cable Test Cases to run Tree_Switcher_4 ************************************************')

'Selecting the Cable Test Cases to run: Tree_Switcher_4'
def Tree_Switcher_4 = "$Test_info.Expand_Test_List.Tree_Switcher_Start04.Tree_Switcher_4"
def UUSB_C_close = ('//li[@class=\'' + Tree_Switcher_4) + ' rc-tree-treenode-switcher-close\']//span[@class=\'rc-tree-switcher rc-tree-switcher_close\']'
def UUSB_C_open = ('//li[@class=\'' + Tree_Switcher_4) + ' rc-tree-treenode-switcher-open rc-tree-treenode-checkbox-indeterminate\']//span[@class=\'rc-tree-switcher rc-tree-switcher_open\']'

try {
	driver.findElement(By.xpath(UUSB_C_close)).click()
	WebUI.delay(3)
}
catch (Exception x) {
	log.error('TreeNode Switcher is not clickable at point exception')
}

def Tree_Sub_Switcher_4_02 = "$Test_info.Expand_Test_List.Tree_Switcher_Start04.Tree_Sub_Switcher_4_02"
driver.findElement(By.xpath(('//span[text()=\'' + Tree_Sub_Switcher_4_02) + '\']')).click()
def Tree_Sub_Switcher_4_03 = "$Test_info.Expand_Test_List.Tree_Switcher_Start04.Tree_Sub_Switcher_4_03"
driver.findElement(By.xpath(('//span[text()=\'' + Tree_Sub_Switcher_4_03) + '\']')).click()
def Tree_Sub_Switcher_4_04 = "$Test_info.Expand_Test_List.Tree_Switcher_Start04.Tree_Sub_Switcher_4_04"
driver.findElement(By.xpath(('//span[text()=\'' + Tree_Sub_Switcher_4_04) + '\']')).click()

try {
	driver.findElement(By.xpath(UUSB_C_open)).click()
	WebUI.delay(3)
}
catch (Exception x) {
	log.error('TreeNode Switcher is not clickable at point exception')
}

println('************************************************ Selecting the Cable Test Cases to run Tree_Switcher_5 ************************************************')

'Selecting the Cable Test Cases to run: Tree_Switcher_5'
def Tree_Switcher_5 = "$Test_info.Expand_Test_List.Tree_Switcher_Start05.Tree_Switcher_5"
def SPT_close = ('//li[@class=\'' + Tree_Switcher_5) + ' rc-tree-treenode-switcher-close\']//span[@class=\'rc-tree-switcher rc-tree-switcher_close\']'
def SPT_open = ('//li[@class=\'' + Tree_Switcher_5) + ' rc-tree-treenode-switcher-open rc-tree-treenode-checkbox-indeterminate\']//span[@class=\'rc-tree-switcher rc-tree-switcher_open\']'

try {
	driver.findElement(By.xpath(SPT_close)).click()
	WebUI.delay(3)
}
catch (Exception x) {
	log.error('TreeNode Switcher is not clickable at point exception')
}

def Tree_Sub_Switcher_5_01 = "$Test_info.Expand_Test_List.Tree_Switcher_Start05.Tree_Sub_Switcher_5_01"
driver.findElement(By.xpath(('//span[text()=\'' + Tree_Sub_Switcher_5_01) + '\']')).click()
def Tree_Sub_Switcher_5_05 = "$Test_info.Expand_Test_List.Tree_Switcher_Start05.Tree_Sub_Switcher_5_05"
driver.findElement(By.xpath(('//span[text()=\'' + Tree_Sub_Switcher_5_05) + '\']')).click()


try {
	driver.findElement(By.xpath(SPT_open)).click()
	WebUI.delay(3)
}
catch (Exception x) {
	log.error('TreeNode Switcher is not clickable at point exception')
}

println('************************************************ Selecting the Cable Test Cases to run Tree_Switcher_7 ************************************************')

'Selecting the Cable Test Cases to run: Tree_Switcher_7'
def Tree_Switcher_7 = "$Test_info.Expand_Test_List.Tree_Switcher_Start07.Tree_Switcher_7"
def QC_3_close = ('//li[@class=\'' + Tree_Switcher_7) + ' rc-tree-treenode-switcher-close\']//span[@class=\'rc-tree-switcher rc-tree-switcher_close\']'
def QC_3__open = ('//li[@class=\'' + Tree_Switcher_7) + ' rc-tree-treenode-switcher-open rc-tree-treenode-checkbox-indeterminate\']//span[@class=\'rc-tree-switcher rc-tree-switcher_open\']'

try {
	driver.findElement(By.xpath(QC_3_close)).click()
	WebUI.delay(3)
}
catch (Exception x) {
	log.error('TreeNode Switcher is not clickable at point exception')
}

def Tree_Sub_Switcher_7_01 = "$Test_info.Expand_Test_List.Tree_Switcher_Start07.Tree_Sub_Switcher_7_01"
driver.findElement(By.xpath(('//span[text()=\'' + Tree_Sub_Switcher_7_01) + '\']')).click()
def Tree_Sub_Switcher_7_02 = "$Test_info.Expand_Test_List.Tree_Switcher_Start07.Tree_Sub_Switcher_7_02"
driver.findElement(By.xpath(('//span[text()=\'' + Tree_Sub_Switcher_7_02) + '\']')).click()
def Tree_Sub_Switcher_7_10 = "$Test_info.Expand_Test_List.Tree_Switcher_Start07.Tree_Sub_Switcher_7_10"
driver.findElement(By.xpath(('//span[text()=\'' + Tree_Sub_Switcher_7_10) + '\']')).click()

try {
	driver.findElement(By.xpath(QC_3__open)).click()
	WebUI.delay(3)
}
catch (Exception x) {
	log.error('TreeNode Switcher is not clickable at point exception')
}

println('************************************************ Selecting the Cable Test Cases to run Tree_Switcher_8 ************************************************')

'Selecting the Cable Test Cases to run Tree_Switcher_8'
def Tree_Switcher_8 = "$Test_info.Expand_Test_List.Tree_Switcher_Start08.Tree_Switcher_8"
def QC_4_close = ('//li[@class=\'' + Tree_Switcher_8) + ' rc-tree-treenode-switcher-close\']//span[@class=\'rc-tree-switcher rc-tree-switcher_close\']'
def QC_4_open = ('//li[@class=\'' + Tree_Switcher_8) + ' rc-tree-treenode-switcher-open rc-tree-treenode-checkbox-indeterminate\']//span[@class=\'rc-tree-switcher rc-tree-switcher_open\']'

try {
	driver.findElement(By.xpath(QC_4_close)).click()
	WebUI.delay(3)
}
catch (Exception x) {
	log.error('TreeNode Switcher is not clickable at point exception')
}

def Tree_Sub_Switcher_8_03 = "$Test_info.Expand_Test_List.Tree_Switcher_Start08.Tree_Sub_Switcher_8_03"
driver.findElement(By.xpath(('//span[text()=\'' + Tree_Sub_Switcher_8_03) + '\']')).click()
def Tree_Sub_Switcher_8_10 = "$Test_info.Expand_Test_List.Tree_Switcher_Start08.Tree_Sub_Switcher_8_10"
driver.findElement(By.xpath(('//span[text()=\'' + Tree_Sub_Switcher_8_10) + '\']')).click()
def Tree_Sub_Switcher_8_13 = "$Test_info.Expand_Test_List.Tree_Switcher_Start08.Tree_Sub_Switcher_8_13"
driver.findElement(By.xpath(('//span[text()=\'' + Tree_Sub_Switcher_8_13) + '\']')).click()
def Tree_Sub_Switcher_8_17 = "$Test_info.Expand_Test_List.Tree_Switcher_Start08.Tree_Sub_Switcher_8_17"
driver.findElement(By.xpath(('//span[text()=\'' + Tree_Sub_Switcher_8_17) + '\']')).click()
def Tree_Sub_Switcher_8_27 = "$Test_info.Expand_Test_List.Tree_Switcher_Start08.Tree_Sub_Switcher_8_27"
driver.findElement(By.xpath(('//span[text()=\'' + Tree_Sub_Switcher_8_27) + '\']')).click()

try {
	driver.findElement(By.xpath(QC_4_open)).click()
	WebUI.delay(3)
}
catch (Exception x) {
	log.error('TreeNode Switcher is not clickable at point exception')
}

println('************************************************ Selecting the Cable Test Cases to run Tree_Switcher_11 ************************************************')

'Selecting the Cable Test Cases to run Tree_Switcher_11'
def Tree_Switcher_11 = "$Test_info.Expand_Test_List.Tree_Switcher_Start11.Tree_Switcher_11"
def OEM_close = ('//li[@class=\'' + Tree_Switcher_11) + ' rc-tree-treenode-switcher-close\']//span[@class=\'rc-tree-switcher rc-tree-switcher_close\']'
def OEM_open = ('//li[@class=\'' + Tree_Switcher_11) + ' rc-tree-treenode-switcher-open rc-tree-treenode-checkbox-indeterminate\']//span[@class=\'rc-tree-switcher rc-tree-switcher_open\']'

try {
	driver.findElement(By.xpath(OEM_close)).click()
	WebUI.delay(3)
}
catch (Exception x) {
	log.error('TreeNode Switcher is not clickable at point exception')
}

def Tree_Sub_Switcher_11_01 = "$Test_info.Expand_Test_List.Tree_Switcher_Start11.Tree_Sub_Switcher_11_01"
driver.findElement(By.xpath(('//span[text()=\'' + Tree_Sub_Switcher_11_01) + '\']')).click()

try {
	driver.findElement(By.xpath(OEM_open)).click()
	WebUI.delay(3)
}
catch (Exception x) {
	log.error('TreeNode Switcher is not clickable at point exception')
}


println('************************************************ Click the Start Execution Button ************************************************')

'Click the Start Execution Button'
try {
	WebUI.click(findTestObject('Object Repository/Test_Case_Selection/Start_Execution'))
} catch (Exception x) {
	log.error('Element is verified and its not clickable at point exception')
}

'Wait for untill the Test Execution is complete and to track the LIVE BUTTON'
try {
	WebUI.delay(10)
	run_status = true
	while (run_status)
	{
		'To check Unlive button status'
		def sts = driver.findElement(By.xpath('//button/img[@src=\'../../images/chartIcons/PNG/Live.png\']')).isDisplayed()
		println(sts)
		if (sts)
		{
			WebUI.delay(600) /* wait for live button status to be displayed */
			try
			{
				driver.findElement(By.xpath('//span[contains(text(),"Results")]')).click()
			}
			catch(Exception e)
			{
				println("Result tab is not clickable")
				try
				{
					WebUI.click(findTestObject('Object Repository/Press_Ok_Popup/ok_contains'))
					WebUI.delay(4)
					println("try:clicked popup ok")
					driver.findElement(By.xpath('//span[contains(text(),"Results")]')).click()
					println("try:clicked Result tab")
				}
				catch (Exception A)
				{
					WebUI.refresh()
					WebUI.delay(8)
					try {
						driver.findElement(By.xpath('//span[contains(text(),"Results")]')).click()
						println("Catch:try: result tab i s clickable")
					}
					catch(Exception z) {
						WebUI.refresh()
						WebUI.delay(8)
						driver.findElement(By.xpath('//span[contains(text(),"Results")]')).click()
						println("Catch:Catch: result tab i s clickable")
					}
				}
			}
		}
	}
}
catch (Exception e) {
	run_status = false
}