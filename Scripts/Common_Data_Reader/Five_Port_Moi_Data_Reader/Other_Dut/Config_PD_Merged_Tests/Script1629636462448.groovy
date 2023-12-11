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
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper

'To read json file name'
String json_file_name = key1

'To Read Compare Tool Moi data'
File file_obj = new File(('Five_Port_Data_Config' + '\\') + json_file_name)

Map moi_data = new JsonSlurper().parseFile(file_obj, 'UTF-8')

def moi_ref = moi_data.All_Dut_Type_Except_Cable

def noise_type_drpdwn = moi_ref.PD_Merged_Noise_Type_Drpdwn_Val

def connect_epr_checkbox = moi_ref.Connect_EPR_Test_Fixture_Checkbox

def noise_type_xpath = [('Two Tone Noise') : 1, ('Square Wave Noise') : 2]

'driver object Initialization'
WebDriver driver = DriverFactory.getWebDriver()

'To select noise_type_drpdwn value'
for (def noise_type : noise_type_xpath) {
    if (noise_type.key == noise_type_drpdwn) {
        'To select noise_type_drpdwn value'
        WebUI.click(findTestObject('Python_Aggregation_Moi_Objects/Config_PD_Merged_Tests_Moi/dropdown/button_Noise_Type'))

        WebUI.delay(GlobalVariable.short_delay)

        WebElement noise_type_ele = driver.findElement(By.xpath(('(//div[@aria-labelledby="tcPdMergedNoiseTypeComboBox"]/a)[' + 
                noise_type.value) + ']'))

        JavascriptExecutor executor = ((driver) as JavascriptExecutor)

        executor.executeScript('arguments[0].click();', noise_type_ele)
    }
}

'To click Connect EPR Test Fixture '
if (connect_epr_checkbox == 'YES') {
    CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectUnselectedCheckbox'(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_PD_Merged_Tests_Moi/checkbox/input_Connect_EPR_Test_Fixture'))
}



