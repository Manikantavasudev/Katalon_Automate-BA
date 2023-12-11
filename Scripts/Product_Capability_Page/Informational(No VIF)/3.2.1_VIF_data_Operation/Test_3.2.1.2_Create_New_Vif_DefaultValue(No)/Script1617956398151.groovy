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
import org.openqa.selenium.By as By
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import groovy.time.TimeCategory as TimeCategory
import java.nio.file.*
import java.io.IOException as IOException
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

'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String downloads_folder_path = all_data.user_config_data.Downloads_Folder_Path

List pc_info_port1_dut_list = all_data.user_config_data.PC_Info_Port1_Dut_List

List pc_create_vif_dut_list = all_data.user_config_data.PC_Create_Vif_Dut_List

//String downloads_folder_path = all_data.user_config_data.Downloads_Folder_Path

String generate_xml_file_path=downloads_folder_path+"\\"+"VifData.xml"


'To handle the Suppress Warnings while downloading xml file'
Map prefs = new HashMap<String, Object>()

prefs.put('download.default_directory', downloads_folder_path)

prefs.put('download.prompt_for_download', false)

prefs.put('download.extensions_to_open', 'application/xml')

prefs.put('safebrowsing.enabled', true)

ChromeOptions options = new ChromeOptions()

options.setExperimentalOption('prefs', prefs)

options.addArguments('start-maximized')

options.addArguments('--safebrowsing-disable-download-protection')

options.addArguments('safebrowsing-disable-extension-blacklist')

WebDriver driver = new ChromeDriver(options)

//WebDriver driver = DriverFactory.getWebDriver(options) 

//DriverFactory.changeWebDriver(driver)

'To navigate url& Maximize Browser '
WebUI.navigateToUrl('http://localhost:5001/')

WebUI.maximizeWindow()

WebUI.delay(GlobalVariable.long_delay)


'To Check Product Capability Page Availability'
def product_capability_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

'Testcase will fail if Product Capability Page not present'
if (product_capability_sts) {
    WebUI.comment('Product Capability Page is Present')
} else {
    WebUI.comment('Product Capability Page is not Present,Something went wrong ')

    assert false
}

'To click Product Capability page'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

WebUI.delay(GlobalVariable.long_delay)

'To click on Informational (NO VIF) radio button'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/input_Informational (No VIF)_RadioBtn'))

for (String pc_create_vif_dut : pc_create_vif_dut_list) {
    'STEP 1: To click on Create New VIF Button'
    WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/button_Create New VIF'))

    'STEP 2 : To verify VIF Config dialogue Appears'
    def vif_config_text = WebUI.getText(findTestObject('Object Repository/Product_Capability_Objects/Create_New_VIF/div_VIF Config'))

    'STEP 3:To verify DUT dropdwon list'
    CustomKeywords.'grl_usb_pd_c2_Testcase.ProductCapability_Page.createNewVifDutDropdownList'()

    //WebDriver driver = DriverFactory.getWebDriver()
    'STEP 4:To Verify Set Default Value Radio Button is present with Yes/NO choice'
    def set_default_yes_value_sts = driver.findElement(By.xpath('//input[@id=\'pcVifConfigDefaultValueYesRadioBtn\']')).isSelected()

    def set_default_no_value_sts = driver.findElement(By.xpath('//input[@id=\'pcVifConfigDefaultValueNoRadioBtn\']')).isSelected()

    if (set_default_no_value_sts) {
        'STEP 5:To select No option'
        WebUI.comment('Set default Value status NO::' + set_default_no_value_sts)
    } else {
        WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Create_New_VIF/input_No_pcVifConfigDefaultValueNoRadioBtn'))

        WebUI.comment('Set default Value status is NO::' + set_default_no_value_sts)
    }
    
    'STEP 6:Select Consumer only Dropdown list'
    WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Create_New_VIF/button_Create_New_Vif_Dropdown_Icon'))

   WebUI.delay(GlobalVariable.short_delay)

    WebElement re_sts = driver.findElement(By.linkText(pc_create_vif_dut))

    JavascriptExecutor executor = ((driver) as JavascriptExecutor)

    executor.executeScript('arguments[0].click();', re_sts)

    println(re_sts)

    'STEP 7:To click ok button'
    WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Create_New_VIF/button_Ok'))

    'STEP 8:To verify VIF Config Dialog Appears is closed'
    WebUI.verifyElementNotPresent(findTestObject('Object Repository/Product_Capability_Objects/Create_New_VIF/div_VIF Config'), 
        5)

    'STEP 9:To verify Consumer Only dut type is selected on port1'
    def dut_type_text_sts = WebUI.getText(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_1_dut_dropdown_icon'))

    println(dut_type_text_sts)

    if ((dut_type_text_sts == pc_create_vif_dut) || 'Dual Role Power') {
        println(pc_create_vif_dut)

        'STEP 10&11:Verify DUT is enabled state and Verify DUT dropdown  list'
        List info_p1_dut_list = CustomKeywords.'grl_usb_pd_c2_Testcase.ProductCapability_Page.port1InformationaldutDropdownList'()

        assert info_p1_dut_list == pc_info_port1_dut_list
    } else {
        WebUI.comment('Selected Dut type is not Matching::' + dut_type_text_sts)

        assert false
    }
    
    'To delete Existing VifData.xml file'
    Path path = Paths.get(downloads_folder_path + '\\VifData.xml')

    Boolean xml_file_path_sts = Files.exists(path)

    def sts = Files.deleteIfExists(Paths.get(downloads_folder_path + '\\VifData.xml'))

    println(sts)

    try {
        if (xml_file_path_sts) {
            Files.deleteIfExists(Paths.get(downloads_folder_path + '\\VifData.xml'))
        } else {
            WebUI.comment('XML file is not present under Downloads folder')
        }
    }
    catch (NoSuchFileException e) {
        WebUI.comment('File not present:: ' + e)
    } 
    
    'STEP 12:To Verify Vif data in ui with Auto Generted vif file data'

    'To click on Generate VIF(VIF Data)button'
    WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/button_Generate VIF(VIF Data)'))

    'To Verify Save dialogue button text'
    def save_file_text = WebUI.getText(findTestObject('Object Repository/Product_Capability_Objects/Generate_Vif_Data/strong_Save_File_text'))

    assert save_file_text == 'Save File'

    'To click  popup button '
    WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Generate_Vif_Data/button_Popup_Ok'))

    WebUI.delay(GlobalVariable.long_delay)

   WebUI.callTestCase(findTestCase('xml_verifier/Test_Xml_UI_Verifier'), [('key1') : generate_xml_file_path], FailureHandling.STOP_ON_FAILURE)
}

'To close browser'
WebUI.closeBrowser()



