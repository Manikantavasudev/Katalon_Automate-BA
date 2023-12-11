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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.By as By



'To initialize driver object'
WebDriver driver = DriverFactory.getWebDriver()

WebUI.delay(GlobalVariable.short_delay)

'Common moi data'
exp_moi = key1

'Quick Charger 3.0 Tests-v1.4'
String qc_2_3_dut_type_value = 'QC2'

String connector_type_value = 'TypeA_TypeC'

String connecter_type_cable_value = 'Spl_Emarker_Cable'

String ports_value = 'Single_Port'

String pd_support_value = 'Non-PD'

String qc_dut_type_value = 'Power_Adaptor'

'DisplayPort Alternate Mode Tests-v4'
String dp_device_type_drpdwn_value = 'DP_Sink'

'Thunderbolt Power Tests-Rev1.5 Ver0.9'

String number_of_ports ='Single_Port'

String powered_type = 'Self_Powered'

String device_type = 'Device'

String tbt_version = 'TBT_3'


'Quick Charger 3.0 Tests'

if((exp_moi=='Quick Charger 3.0 Tests-v1.4')||(exp_moi=='QC3+ Tests-v1.2')) {
	
'To select QC 2.0/3.0 DUT Type dropdown option'
WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/dropdown/button_QC_2.0_3.0_Dut_Type_dropdown'))

WebUI.delay(GlobalVariable.short_delay)

driver.findElement(By.xpath(('//a[text()=\'' + qc_2_3_dut_type_value) + '\']')).click()


'To select Connector Type dropdown option'
WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/dropdown/button_Connector_Type_dropdown'))

WebUI.delay(GlobalVariable.short_delay)

driver.findElement(By.xpath(('//a[text()=\'' + connector_type_value) + '\']')).click()


'To select Connector Type Cable dropdown option'

WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/dropdown/button_Connector_Type_Cable_dropdown'))

WebUI.delay(GlobalVariable.short_delay)

driver.findElement(By.xpath(('//a[text()=\'' + connecter_type_cable_value) + '\']')).click()


'To select Ports dropdown option'


WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/dropdown/button_Ports_dropdown'))

WebUI.delay(GlobalVariable.short_delay)

driver.findElement(By.xpath(('//a[text()=\'' + ports_value) + '\']')).click()

'To select PD Support dropdown option'

WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/dropdown/button_PD_Support_dropdown'))

WebUI.delay(GlobalVariable.short_delay)

driver.findElement(By.xpath(('//a[text()=\'' + pd_support_value) + '\']')).click()


'To select QC DUT Type dropdown option'

WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Quick_Charger_3.0_Moi/dropdown/button_QC_Dut_Type_dropdown'))

WebUI.delay(GlobalVariable.short_delay)

driver.findElement(By.xpath(('//a[text()=\'' + qc_dut_type_value) + '\']')).click()

}

if(exp_moi=='DisplayPort Alternate Mode Tests-v4') {
	
	'To select DP Device Type dropdown option'
	
	WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Thunderbolt_Power_Tests_Moi/dropdown/button_Device_Type_dropdown'))
	
	WebUI.delay(GlobalVariable.short_delay)
	
	driver.findElement(By.xpath(('//a[text()=\'' + dp_device_type_drpdwn_value) + '\']')).click()
	
		
}
if(exp_moi=='Thunderbolt Power Tests-Rev1.5 Ver0.9') {
	
	'Number Of Ports dropdown option'
	
	WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Thunderbolt_Power_Tests_Moi/dropdown/button_Number_Of_Ports_dropdown'))
	
	WebUI.delay(GlobalVariable.short_delay)
	
	driver.findElement(By.xpath(('//a[text()=\'' + number_of_ports) + '\']')).click()
			
	'Powered Type dropdown option'
	
	WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Thunderbolt_Power_Tests_Moi/dropdown/button_Powered_Type_dropdown'))
	
	WebUI.delay(GlobalVariable.short_delay)
	
	driver.findElement(By.xpath(('//a[text()=\'' + powered_type) + '\']')).click()
	
	'Device Type dropdown option'
		
	WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Thunderbolt_Power_Tests_Moi/dropdown/button_Device_Type_dropdown'))
	
	WebUI.delay(GlobalVariable.short_delay)
	
	driver.findElement(By.xpath(('//a[text()=\'' + device_type) + '\']')).click()
	
	
	'TBT Version dropdown option'
		
	WebUI.click(findTestObject('Object Repository/Python_Aggregation_Moi_Objects/Config_Thunderbolt_Power_Tests_Moi/dropdown/button_TBT_Version_dropdown'))
	
	WebUI.delay(GlobalVariable.short_delay)
	
	driver.findElement(By.xpath(('//a[text()=\'' + tbt_version) + '\']')).click()
	
	
}




















