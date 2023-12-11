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
import java.awt.Robot as Robot
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import java.awt.Toolkit as Toolkit
import java.awt.datatransfer.StringSelection as StringSelection
import java.awt.event.KeyEvent as KeyEvent
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
import groovy.json.JsonSlurper as JsonSlurper
import groovy.json.JsonParserType as JsonParserType
import groovy.json.JsonOutput as JsonOutput
import java.io.File as File
import java.util.Scanner as Scanner
import java.io.FileNotFoundException as FileNotFoundException
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor


'To read json file name'
String json_file_name = key1

'To Read Compare Tool Moi data'
File file_obj = new File(('Five_Port_Data_Config' + '\\') + json_file_name)

Map moi_data = new JsonSlurper().parseFile(file_obj, 'UTF-8')

def moi_ref = moi_data.All_Dut_Type_Except_Cable

def secondary_detection_drpdwn = moi_ref.BC12_DCP_Secondary_Detection_Drpdwn_Val

def inp_maximum_current = moi_ref.BC12_DCP_Maximum_Current_Textbox

'To create Dictionary value for vconn voltage and navigate to proper xpath '
def sec_detection_xpath = [('Implemented') : 1, ('Not_Implemented') : 2]

WebUI.delay(GlobalVariable.short_delay)

'driver object Initialization'
WebDriver driver = DriverFactory.getWebDriver()

'To select secondary detection value'
for (def sec_detection : sec_detection_xpath) {
    if (sec_detection.key == secondary_detection_drpdwn) {
        'To select secondary detection value'
        WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_BC12_DCP_Sink_Tests_Moi/dropdown/button_Secondary_Detection'))

        WebUI.delay(GlobalVariable.short_delay)

        WebElement sec_detetection_ele = driver.findElement(By.xpath(('(//div[@aria-labelledby="tcBc1.2SecondaryDetectionComboBox"]/a)[' + 
                sec_detection.value) + ']'))

        JavascriptExecutor executor = ((driver) as JavascriptExecutor)

        executor.executeScript('arguments[0].click();', sec_detetection_ele)
    }
}

'To input maximum current textbox value'
WebUI.delay(GlobalVariable.short_delay)

WebUI.clearText(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_BC12_DCP_Sink_Tests_Moi/textbox/input_Maximum_Current'))

WebUI.sendKeys(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_BC12_DCP_Sink_Tests_Moi/textbox/input_Maximum_Current'), 
    inp_maximum_current)



