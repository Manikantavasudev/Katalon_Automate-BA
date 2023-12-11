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

Map moi_data = new JsonSlurper().parseFile(file_obj,'UTF-8')

def moi_ref = moi_data.All_Dut_Type_Except_Cable

'To read moi config dropdown'
String qc_2_3_dut_type_value = moi_ref.QC3_Qc_2_3_Dut_Type_Drpdwn_val

String connector_type_value = moi_ref.QC3_Connector_Type_Drpdwn_Val

String connecter_type_cable_value = moi_ref.QC3_Connector_Type_Cable_Drpdwn_Val

'To read input textbox value'
def inp_cable_ir_drop = moi_ref.QC3_Cable_IR_drop_Textbox

def inp_5v_max_current = moi_ref.QC3_5V_Max_Current_Textbox

def inp_9v_max_current = moi_ref.QC3_9V_Max_Current_Textbox

def inp_12v_max_current = moi_ref.QC3_12V_Max_Current_Textbox

def inp_20v_max_current = moi_ref.QC3_20V_Max_Current_Textbox

def checkbox_20v_max_current = moi_ref.QC3_20V_Max_Current_Checkbox

def inp_dut_rated_power_val = moi_ref.QC3_Dut_Rated_Power_Textbox

'To read moi config dropdown'
String ports_value = moi_ref.QC3_Ports_Drpdwn_Val

String pd_support_value = moi_ref.QC3_PD_Support_Drpdwn_Val

String qc_dut_type_value = moi_ref.QC3_Qc_Dut_Type_Drpdwn_Val

def inp_input_supply_voltage = moi_ref.QC3_Input_Supply_Voltage_Textbox

'To read dropdown value for configuring the input textbox'

String exp_connecter_type_cable_value = 'Spl_Emarker_Cable'

String exp_qc_dut_type_value = 'Car_Charger'


'driver object Initialization'
WebDriver driver = DriverFactory.getWebDriver()

'To select QC 2.0/3.0 DUT Type dropdown option'
WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/dropdown/button_QC_2.0_3.0_Dut_Type_dropdown'))

WebUI.delay(GlobalVariable.short_delay)

driver.findElement(By.xpath(('//a[text()=\'' + qc_2_3_dut_type_value) + '\']')).click()


'To select Connector Type dropdown option'
if(qc_2_3_dut_type_value =="QC4Plus") {
	println(connector_type_value)
}
else {
	
	
	WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/dropdown/button_Connector_Type_dropdown'))
	
	WebUI.delay(GlobalVariable.short_delay)
	
	driver.findElement(By.xpath(('//a[text()=\'' + connector_type_value) + '\']')).click()
}

'To select Connector Type Cable dropdown option'
if(connector_type_value=="TypeA_TypeC") {

WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/dropdown/button_Connector_Type_Cable_dropdown'))

WebUI.delay(GlobalVariable.short_delay)

driver.findElement(By.xpath(('//a[text()=\'' + connecter_type_cable_value) + '\']')).click()

'To input Cable IR drop inbox'
WebUI.delay(GlobalVariable.short_delay)

WebUI.clearText(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/textbox'))

WebUI.sendKeys(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/textbox/input_Cable_IR_drop'), inp_cable_ir_drop)



}
else {
	WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/dropdown/button_Connector_Type_Cable_dropdown'))
	
	WebUI.delay(GlobalVariable.short_delay)
	
	driver.findElement(By.xpath(('//a[text()=\'' + connecter_type_cable_value) + '\']')).click()
	
}



'To input 5V Max Current inbox'

WebUI.clearText(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/textbox/input_5V_Max_Current'))

WebUI.sendKeys(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/textbox/input_5V_Max_Current'), inp_5v_max_current)


'To input 9V Max Current inbox'

WebUI.clearText(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/textbox/input_9V_Max_Current'))

WebUI.sendKeys(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/textbox/input_9V_Max_Current'), inp_9v_max_current)



'To input 12V Max Current inbox'
WebUI.clearText(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/textbox/input_12V_Max_Current'))

WebUI.sendKeys(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/textbox/input_12V_Max_Current'), inp_12v_max_current)

'To input 20V Max Current inbox'
WebUI.delay(GlobalVariable.short_delay)

if(checkbox_20v_max_current == "YES") {
	
try {
	
def timeout_popup_sts = WebUI.verifyElementChecked(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/textbox/input_20V_Max_Current_Checkbox'),30)


if (timeout_popup_sts) {
	WebUI.clearText(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/textbox/input_20V_Max_Current'))

	WebUI.sendKeys(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/textbox/input_20V_Max_Current'), inp_20v_max_current)
}
}
catch (Exception e) {
	'To select 20V Max Current checkbox'

	WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/textbox/input_20V_Max_Current_Checkbox'))

	WebUI.(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/textbox/input_20V_Max_Current'))

	WebUI.sendKeys(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/textbox/input_20V_Max_Current'), inp_20v_max_current)
}
}

if(checkbox_20v_max_current == "NO") {
	
try {
	
def timeout_popup_sts = WebUI.verifyElementChecked(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/textbox/input_20V_Max_Current_Checkbox'),30)


if (timeout_popup_sts) {
	WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/textbox/input_20V_Max_Current_Checkbox'))
	
}
}
catch (Exception e) {
	'To select 20V Max Current checkbox'
	WebUI.delay(GlobalVariable.short_delay)
	
	
}
}

WebUI.delay(GlobalVariable.short_delay)


'To input DUT Rated Power inbox'

WebUI.clearText(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/textbox/input_DUT_Rated_Power'))

WebUI.sendKeys(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/textbox/input_DUT_Rated_Power'), inp_dut_rated_power_val)


'To select Ports dropdown option'


WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/dropdown/button_Ports_dropdown'))

WebUI.delay(GlobalVariable.short_delay)

driver.findElement(By.xpath(('//a[text()=\'' + ports_value) + '\']')).click()

'To select PD Support dropdown option'


WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/dropdown/button_PD_Support_dropdown'))

WebUI.delay(GlobalVariable.short_delay)

driver.findElement(By.xpath(('//a[text()=\'' + pd_support_value) + '\']')).click()

'To select QC DUT Type dropdown option'

if(exp_qc_dut_type_value == qc_dut_type_value)
{
WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/dropdown/button_QC_Dut_Type_dropdown'))

WebUI.delay(GlobalVariable.short_delay)

driver.findElement(By.xpath(('//a[text()=\'' + qc_dut_type_value) + '\']')).click()

WebUI.clearText(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/textbox/input_Input_Supply_Voltage'))

WebUI.sendKeys(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/textbox/input_Input_Supply_Voltage'), inp_input_supply_voltage)

}

else {
	WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/dropdown/button_QC_Dut_Type_dropdown'))
	
	WebUI.delay(GlobalVariable.short_delay)
	
	driver.findElement(By.xpath(('//a[text()=\'' + qc_dut_type_value) + '\']')).click()
	
}




