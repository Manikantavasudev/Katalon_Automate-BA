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
import groovy.json.JsonSlurper as JsonSlurper
import org.openqa.selenium.By as By
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import groovy.json.JsonBuilder as JsonBuilder
import org.openqa.selenium.WebDriver as WebDriver
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
import groovy.json.JsonParserType as JsonParserType
import groovy.json.JsonOutput as JsonOutput
import java.io.File as File
import java.util.Scanner as Scanner
import java.io.FileNotFoundException as FileNotFoundException
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import java.io.IOException as IOException

'To call Enable porttype'

port_val = key1

if(port_val=="1") {
CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.enablePort'(port_val, findTestObject('Object Repository/Five_Port_Objects/Options/Port_Info/div_Enable_Port1_Toggle'))


}

if(port_val=="2") {
	CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.enablePort'(port_val, findTestObject('Object Repository/Five_Port_Objects/Options/Port_Info/div_Enable_Port2_Toggle'))
	
}

if(port_val=="3") {
	CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.enablePort'(port_val, findTestObject('Object Repository/Five_Port_Objects/Options/Port_Info/div_Enable_Port3_Toggle'))
	
}

if(port_val=="4") {
	CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.enablePort'(port_val, findTestObject('Object Repository/Five_Port_Objects/Options/Port_Info/div_Enable_Port4_Toggle'))
	
	
}
if(port_val=="5") {
	CustomKeywords.'grl_usb_pd_c2_Testcase.Five_Port.enablePort'(port_val, findTestObject('Object Repository/Five_Port_Objects/Options/Port_Info/div_Enable_Port5_Toggle'))
	
	
}



















