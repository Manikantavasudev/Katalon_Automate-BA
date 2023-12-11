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
import groovy.time.TimeCategory as TimeCategory
import java.nio.file.*
import java.io.IOException as IOException
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.Alert as Alert
import org.openqa.selenium.By as By
import java.io.File as File
import java.util.HashMap as HashMap
import java.util.Map as Map
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions as ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities as DesiredCapabilities
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import groovy.transform.CompileStatic as CompileStatic
import java.awt.Toolkit as Toolkit
import java.awt.datatransfer.StringSelection as StringSelection
import java.awt.event.KeyEvent as KeyEvent
import java.awt.Robot as Robot
CustomKeywords.'custom.Logger.CustomLoggerFactory.customizeLogger4CommentKeyword'()

'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

List pc_c2_vif_file_path = all_data.user_config_data.PC_C2_Vif_File_Path

String pc_xml_file_folder_path = all_data.user_config_data.PC_XML_File_Folder_Path

'To find downloads folder directory and vifdata xml  file'

String home_path = System.getProperty('user.home')

File downloads_folder_path = new File(home_path + '/Downloads')

'To convert file object as String'
String download_folder = downloads_folder_path

File xml_file_path_sts = new File(((home_path + '/Downloads/') + 'VifData') + '.xml')

'To handle the Suppress Warnings while downloading xml file'
System.setProperty('webdriver.chrome.driver', 'chromedriver.exe')

WebUI.delay(GlobalVariable.short_delay)

Map prefs = new HashMap<String, Object>()

prefs.put('download.default_directory', download_folder)

prefs.put('download.prompt_for_download', false)

prefs.put('download.extensions_to_open', 'application/xml')

prefs.put('safebrowsing.enabled', true)

ChromeOptions options = new ChromeOptions()

options.setExperimentalOption('prefs', prefs)

options.addArguments('start-maximized')

options.addArguments('--safebrowsing-disable-download-protection')

options.addArguments('safebrowsing-disable-extension-blacklist')

WebDriver driver = new ChromeDriver(options)

DriverFactory.changeWebDriver(driver)

'To navigate url& Maximize Browser '
WebUI.navigateToUrl('http://localhost:5001/')

WebUI.maximizeWindow()

WebUI.delay(GlobalVariable.long_delay)

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

WebUI.comment('TestcaseName:: '+'Test_3.2.1.3_Generate_Vif(VIF Data)'+'\n')


'To Check Product Capability Page Availability'
def product_capability_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

'Testcase will fail if Product Capability Page not present'
if (product_capability_sts) {
    WebUI.comment('Product Capability Page is Present'+'\n')
} else {
    WebUI.comment('Product Capability Page is not Present,Something went wrong '+'\n')

    assert false
}

WebUI.delay(GlobalVariable.short_delay)

'To click Product Capability page'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

'To click Informational Radio Button'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/input_Informational (No VIF)_RadioBtn'))

for (String separate_vif_file_path : pc_c2_vif_file_path) {
	
	' To combine load xml file folder and Xml file '
	Path vif_file_path = Paths.get(pc_xml_file_folder_path +'\\'+ separate_vif_file_path)
	 
    'To delete Existing VifData1.xml file'
	
	try {
				
		boolean file_exists_sts = xml_file_path_sts.exists()
		
		if (file_exists_sts) {
		  
			boolean filedeleted = xml_file_path_sts.delete()
			
			WebUI.comment('Existing VIF Data xml file Succesfully deleted without any issues::' + filedeleted)
			
		} else {
			WebUI.comment('XML file is not present under Downloads folder'+'\n')
		}
	}
	catch (NoSuchFileException e) {
		WebUI.comment('File not present:: ' + e+'\n')
	}
    
    'To load the xml file using load xml file button'
    WebUI.callTestCase(findTestCase('Load_Xml_File_RobotFramework'), [('key1') : vif_file_path], FailureHandling.STOP_ON_FAILURE)

    WebUI.delay(GlobalVariable.short_delay)

    'STEP 1:To click on Generate VIF(VIF Data)button'
    WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/button_Generate VIF(VIF Data)'))

    WebUI.delay(GlobalVariable.short_delay)

    'STEP 2:To Verify Save dialogue button text'
    def save_file_text = WebUI.getText(findTestObject('Object Repository/Product_Capability_Objects/Generate_Vif_Data/strong_Save_File_text'))

    assert save_file_text == 'Save File'

    'STEP 3:To verify VifData.xml appears as file name'
    def file_name_text = WebUI.getAttribute(findTestObject('Object Repository/Product_Capability_Objects/Generate_Vif_Data/input_Filename_Textbox'), 
        'value')

    assert file_name_text == 'VifData.xml'

    WebUI.clearText(findTestObject('Object Repository/Product_Capability_Objects/Generate_Vif_Data/input_Filename_Textbox'))

    'STEP 4:To verify file name is editable'
    WebUI.sendKeys(findTestObject('Object Repository/Product_Capability_Objects/Generate_Vif_Data/input_Filename_Textbox'), 
        'VifData.xml')

    def file_name_alt_text = WebUI.getAttribute(findTestObject('Object Repository/Product_Capability_Objects/Generate_Vif_Data/input_Filename_Textbox'), 
        'value')

    assert file_name_alt_text == 'VifData.xml'

    WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Generate_Vif_Data/button_Popup_Ok'))

    WebUI.delay(GlobalVariable.long_delay)

    'STEP 5:To verify VifData.xml file is present under Downloads Folder'
	boolean vif_file_exists_sts = xml_file_path_sts.exists()

    WebUI.delay(GlobalVariable.long_delay)

    if (vif_file_exists_sts) {
        WebUI.comment('VifData.xml file is present in Downloads folder'+'\n')
    } else {
        WebUI.comment('VifData.xml file is not downloaded properly'+'\n')
    }
    

    'To call modified Generate Vif method'
    
	WebUI.callTestCase(findTestCase('xml_verifier/Test_Modified_Generate_Vif_Xml_File_Verifier'), ['key1':vif_file_path], FailureHandling.STOP_ON_FAILURE)
}


WebUI.comment('********* Test_3.2.1.3_Generate_Vif(VIF Data) Execution Ended *********'+'\n')

WebUI.comment('*****************************************************************************************************************************************************'+'\n')


'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'To close browser'
WebUI.closeBrowser()



