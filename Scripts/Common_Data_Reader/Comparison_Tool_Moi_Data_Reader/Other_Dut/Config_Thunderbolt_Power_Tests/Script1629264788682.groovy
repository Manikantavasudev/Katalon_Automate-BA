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
import java.io.FileNotFoundException as FileNotFoundException
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor

'To Read Compare Tool Moi data'
File file_obj = new File('Dut_Testing_Data_Config\\Moi_Configuration_Input.json')

Map moi_data = new JsonSlurper().parseFile(file_obj, 'UTF-8')

def moi_ref = moi_data.All_Dut_Type_Except_Cable

'To read moi config dropdown'
String number_of_ports_drpdown = moi_ref.Thunderbolt_Number_Of_Ports_Drpdwn_Val

String powered_type_drpdown = moi_ref.Thunderbolt_Powered_Type_Drpdwn_Val

String device_type_drpdown = moi_ref.Thunderbolt_Device_Type_Drpdwn_Val

String tbt_version_drpdown = moi_ref.Thunderbolt_TBT_Version_Drpdwn_Val

'To read input textbox value'
def stress_timer_textbox = moi_ref.Thunderbolt_Stress_Test_Timer_Textbox

'To click checkbox'
def portA_capmismatch_checkbox = moi_ref.Thunderbolt_PortA_CapMisMatch_Checkbox

def portB_capmismatch_checkbox = moi_ref.Thunderbolt_PortB_CapMisMatch_Checkbox

def portA_giveback_flag_checkbox = moi_ref.Thunderbolt_PortA_GiveBackFlag_Checkbox

def portB_giveback_flag_checkbox = moi_ref.Thunderbolt_PortB_GiveBackFlag_Checkbox

def number_of_ports_drpdwn_dict = [('Single_Port') : 1, ('Two_Port') : 2]

def powered_type_drpdwn_dict = [('Self_Powered') : 1, ('Bus_Powered') : 2]

def device_type_drpdwn_dict = [('Host') : 1, ('Device') : 2]

def tbt_version_drpdwn_dict = [('TBT_3') : 1, ('TBT_4') : 2]

'driver object Initialization'
WebDriver driver = DriverFactory.getWebDriver()

'To select Number Of Ports dropdown option'

for (def number_of_ports :number_of_ports_drpdwn_dict) {
	if (number_of_ports.key == number_of_ports_drpdown) {
		'To select  cable end'
		WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Thunderbolt_Power_Tests_Moi/dropdown/button_Number_Of_Ports_dropdown'))

		WebUI.delay(GlobalVariable.short_delay)

		WebElement number_ports_element = driver.findElement(By.xpath(('(//div[@aria-labelledby="tcTBTNumberOfPortsComboBox"]/a)[' +
				number_of_ports.value) + ']'))

		JavascriptExecutor number_port_executor = ((driver) as JavascriptExecutor)

		number_port_executor.executeScript('arguments[0].click();', number_ports_element)
	}
}


'To select Powered Type dropdown option'
if (!(device_type_drpdown == 'Host')) {
 
	'To select cable end drpdwn value'
	for (def power_type : powered_type_drpdwn_dict) {
		if (power_type.key == powered_type_drpdown) {
			'To select  cable end'
			WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Thunderbolt_Power_Tests_Moi/dropdown/button_Powered_Type_dropdown'))
	
			WebUI.delay(GlobalVariable.short_delay)
	
			WebElement power_type_element = driver.findElement(By.xpath(('(//div[@aria-labelledby="tcTBTPoweredTypeComboBox"]/a)[' +
					power_type.value) + ']'))
	
			JavascriptExecutor power_type_executor = ((driver) as JavascriptExecutor)
	
			power_type_executor.executeScript('arguments[0].click();', power_type_element)
		}
	}
	
}

'To select Device Type dropdown option'


for (def device_type :device_type_drpdwn_dict) {
	if (device_type.key == device_type_drpdown) {
		'To select  cable end'
		WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Thunderbolt_Power_Tests_Moi/dropdown/button_Device_Type_dropdown'))

		WebUI.delay(GlobalVariable.short_delay)

		WebElement device_type_element = driver.findElement(By.xpath(('((//div[@aria-labelledby="tcTBTDeviceTypeComboBox"])[1]/a)[' +
				device_type.value) + ']'))

		JavascriptExecutor device_type_executor = ((driver) as JavascriptExecutor)

		device_type_executor.executeScript('arguments[0].click();',device_type_element)
	}
}

'To select TBT Version dropdown option'

for (def tbt_version :tbt_version_drpdwn_dict) {
	if (tbt_version.key == tbt_version_drpdown) {
		'To select  cable end'
		WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Thunderbolt_Power_Tests_Moi/dropdown/button_TBT_Version_dropdown'))

		WebUI.delay(GlobalVariable.short_delay)

		WebElement tbt_version_element = driver.findElement(By.xpath(('((//div[@aria-labelledby="tcTBTDeviceTypeComboBox"])[2]/a)[' +
				tbt_version.value) + ']'))

		JavascriptExecutor tbt_type_executor = ((driver) as JavascriptExecutor)

		tbt_type_executor.executeScript('arguments[0].click();',tbt_version_element)
	}
}


'To input Stress Test Timer'
WebUI.clearText(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Thunderbolt_Power_Tests_Moi/textbox/input_Stress_Test_Timer'))

WebUI.sendKeys(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Thunderbolt_Power_Tests_Moi/textbox/input_Stress_Test_Timer'), 
    stress_timer_textbox)

'To click Checkbox values'
if (number_of_ports_drpdown == 'Single_Port') {
    'If number of ports is Single port it will support only PortA capmismatch checkbox and PortA giveback flag checkbox'
    if (portA_capmismatch_checkbox == 'YES') {
        CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectUnselectedCheckbox'(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Thunderbolt_Power_Tests_Moi/checkbox/input_Port-A_CapMisMatch'))
    }
    
    if (portA_giveback_flag_checkbox == 'YES') {
        CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectUnselectedCheckbox'(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Thunderbolt_Power_Tests_Moi/checkbox/input_Port-A_GiveBackFlag'))
    }
}

if (number_of_ports_drpdown == 'Two_Port') {
    'If number of ports is Two port it will support all type of checkbox'
    if (portA_capmismatch_checkbox == 'YES') {
        CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectUnselectedCheckbox'(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Thunderbolt_Power_Tests_Moi/checkbox/input_Port-A_CapMisMatch'))
    }
    
    if (portB_capmismatch_checkbox == 'YES') {
        CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectUnselectedCheckbox'(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Thunderbolt_Power_Tests_Moi/checkbox/input_Port-B_CapMisMatch'))
    }
    
    if (portA_giveback_flag_checkbox == 'YES') {
        CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectUnselectedCheckbox'(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Thunderbolt_Power_Tests_Moi/checkbox/input_Port-A_GiveBackFlag'))
    }
    
    if (portB_giveback_flag_checkbox == 'YES') {
        CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectUnselectedCheckbox'(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Thunderbolt_Power_Tests_Moi/checkbox/input_Port-B_GiveBackFlag'))
    }
}



