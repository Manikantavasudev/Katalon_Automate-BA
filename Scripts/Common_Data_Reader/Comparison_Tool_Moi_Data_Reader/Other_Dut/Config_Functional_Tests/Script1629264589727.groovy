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

'To Read Compare Tool Moi data'
File file_obj = new File('Dut_Testing_Data_Config\\Moi_Configuration_Input.json')

Map moi_data = new JsonSlurper().parseFile(file_obj, 'UTF-8')

def moi_ref = moi_data.All_Dut_Type_Except_Cable

def enable_usb_data_checkbox = moi_ref.Enable_USB_Data_Validation_Checkbox

def automate_usb_data_checkbox = moi_ref.Automate_USB_Data_Validation_Checkbox

def battery_connected_checkbox = moi_ref.Is_Battery_Connected_to_DUT

'driver object Initialization'
WebDriver driver = DriverFactory.getWebDriver()

WebUI.delay(GlobalVariable.short_delay)

'To click Connect EPR Test Fixture '
if (enable_usb_data_checkbox == 'YES') {
    CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectUnselectedCheckbox'(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_USB_Functional_Tests_Moi/checkbox/input_Enable_USB_Data_Validation'))
}

if (automate_usb_data_checkbox == 'YES') {
    CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectUnselectedCheckbox'(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_USB_Functional_Tests_Moi/checkbox/input_Automate_USB_Data_Validation'))
}

if (battery_connected_checkbox == 'YES') {
    CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectUnselectedCheckbox'(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_USB_Functional_Tests_Moi/checkbox/input_Is_Battery_Connected_to_DUT'))
}


