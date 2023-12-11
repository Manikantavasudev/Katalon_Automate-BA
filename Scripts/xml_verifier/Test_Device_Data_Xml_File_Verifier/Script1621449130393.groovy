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
import groovy.json.JsonSlurper
import groovy.json.JsonParserType
CustomKeywords.'custom.Logger.CustomLoggerFactory.customizeLogger4CommentKeyword'()



'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String pc_c2_project_name = all_data.user_config_data.PC_C2_Project_Name

//String pc_c2_vif_file_path = all_data.user_config_data.PC_C2_Vif_File_Path

String vif_file_expected_Popup_Content = all_data.user_config_data.PC_C2_Vif_File_Popup_Content

String python_path = all_data.user_config_data.Python_exe_path

String Installation_path = all_data.user_config_data.Katalon_installation_path

String home_path = System.getProperty('user.home')

def generate_xml_filepath = new File(((home_path + '/Downloads/') + 'DeviceData') + '.xml')

String user_config_vif_file_path=key1

WebUI.comment("UserConfiguredFilePathName::  "+user_config_vif_file_path+'\n')


//vif_file for path
String vif_file
//vif_file_data is retrieve the data from vif file
String vif_file_data
//private Map<String, List<Person>> vif_file_data = new HashMap<>()

WebUI.callTestCase(findTestCase('xml_verifier/Test_Xml_File_Expander'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(10)

WebDriver driver = DriverFactory.getWebDriver()

'To locate table'
WebElement Table = driver.findElement(By.xpath('//table[@id=\'vif-table\']//tbody'))

'To locate rows of table it will Capture all the rows available in the table'
List rows_table = Table.findElements(By.tagName('tr'))

'To calculate no of rows In table'
int rows_count = rows_table.size()

List cellvalue = []



List avoid_data = ['mA', 'mV', 'mW', 'hex']

//Loop:
//int row = 1; row <= rows_count+1; row++
'Loop will execute for all the rows of the table'
for (int row = 1; row <= rows_count; row++) {
	//int i=0
	'To locate columns(cells) of that specific row'

	//List Columns_row = rows_table.get(row).findElements(By.tagName('td'))
	'To calculate no of columns(cells) In that specific row'

	//int columns_count = Columns_row.size()
	'Loop will execute till the last cell of that specific row'
	for (int column = 1; column <= 3; column++) {
		'It will retrieve text from each cell'
		WebElement celltext_val = driver.findElement(By.xpath(((('//tbody[@id=\'vif-edit-table\']/tr[' + row) + ']/td[') +
				column) + ']'))

		println(celltext_val)

		String celltext = celltext_val.getText()

		if (celltext.equals('') || avoid_data.contains(celltext)) {
			try {
				WebElement celltextUpdated_val = driver.findElement(By.xpath(('//tbody[@id=\'vif-edit-table\']/tr[' + row) +
						']/td[2]/div/input'))

				def modified_celltext_val = celltextUpdated_val.getAttribute('value')

				cellvalue.add(modified_celltext_val)
			}
			catch (Exception e) {
				celltext_null_val = ''

				cellvalue.add(celltext_null_val)
			}
		} else {
			cellvalue.add(celltext)
		}
	}
}



vif_file = generate_xml_filepath
avoid_simple = [':']



WebUI.comment('HERE is the Validation data from  VIF file to UI'+'\n')
verifier(cellvalue,vif_file,python_path,Installation_path)

//verifier help to validate the data of vif file
def verifier(cellvalue,vif_file,python_path,Installation_path) {
	List updated_ui_chunks_list=[]
	def result = false
	int count_of_collection
	def datalist = []
	def tempList = []
	def count = 0
	WebUI.comment('LIST'+cellvalue)
	def new_xml_list = cellvalue.collate(3)
	def total_valid_count = 0
	int xml_list_chunks = new_xml_list.size()
	def true_count = 0
	def false_count = 0
	def true_match_count = 0
	def flase_match_count = 0
	String all_col_data_1
	'Executing the python file for extracting the data from the vif file'
	def task = [
		python_path ,
		Installation_path+"\\Test Cases\\Python_files\\Xml_Value_Reader_Download.py",
		"$vif_file"
	].execute()
	def json_value = new JsonSlurper().setType(JsonParserType.LAX).parseText(task.text)
	println('json value '+json_value)
	for (def file_data in json_value) {
		def temp_val
	for (int k = 0; k < xml_list_chunks; k++) {
		def all_col_data = new_xml_list[k]
		updated_ui_chunks_list.add(all_col_data[0])
		updated_ui_chunks_list.add(all_col_data[1]+':'+all_col_data[2])
		if ((all_col_data[2]) != '') {
				all_col_data_1=all_col_data[2]				
				if((file_data.value)!='') {
				if(file_data.key == all_col_data[0])
					
					{
					  if(file_data.value.contains('[')) {
						  result = all_col_data[1] in file_data.value
						if(result) {
							true_count = true_count+1
							print_report(file_data.key,file_data.value,status)
							break
						}}
					
					if(all_col_data_1.contains('mA')) {
						all_col_data_1= all_col_data_1.replaceAll(" mA","")
						temp_val= file_data.value
						result = temp_val.contains(all_col_data_1)
						if(result)
							{
									true_count = true_count+1
									print_report(file_data.key,temp_val ,result)
									break
								}
					}
					if(all_col_data_1.contains('mV')) {
						all_col_data_1= all_col_data_1.replaceAll(" mV","")
						temp_val= file_data.value
						result = temp_val.contains(all_col_data_1)
						if(result)
							{
									true_count = true_count+1
									print_report(file_data.key,temp_val ,result)
									break
								}
				 }
				if(all_col_data_1.contains('mW')) {
					all_col_data_1= all_col_data_1.replaceAll(" mW","")
					temp_val= file_data.value
					result = temp_val.contains(all_col_data_1)
					if(result)
						{
								true_count = true_count+1
								print_report(file_data.key,temp_val ,result)
								break
							}
			 }
			 
						  
			if(all_col_data_1.contains('hex')) {
				all_col_data_1= all_col_data_1.replaceAll(" hex","")
				temp_val= file_data.value
				result = temp_val.contains(all_col_data_1)
				if(result)
					{
							true_count = true_count+1
							print_report(file_data.key,temp_val ,result)
							break
						}
					}
					
					if((file_data.value!='')) {
						temp_val= file_data.value
						result = temp_val.contains(all_col_data_1)
						if(result)
							{
									true_count = true_count+1
									print_report(file_data.key,temp_val ,result)
									break
								}
						else {
							result = false
							true_count = true_count+1
							print_report(file_data.key,temp_val ,result)
							break
						}		
					}
					
			}
					}
				
				}
				}			
	}
	//WebUI.comment('updated_ui_chunks_list '+ updated_ui_chunks_list)
	}

def print_report(param,data,result) {
	WebUI.comment(((((' PARAM NAME is:      ' + (param)) + ' VALUE is:  ') + (data)) + '     Verification result is ') +result+'\n')
}














































































