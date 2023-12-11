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
import java.util.HashMap as HashMap
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import groovy.time.TimeCategory as TimeCategory
import java.nio.file.*
import java.io.IOException as IOException
import java.io.File as File
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

//WebDriver driver = DriverFactory.getWebDriver()
Boolean exp_edit_vif_state = false

'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String pc_xml_file_folder_path = all_data.user_config_data.PC_XML_File_Folder_Path


'To find downloads folder directory and vifdata xml  file'
String home_path = System.getProperty('user.home')

File downloads_folder_path = new File(home_path + '/Downloads')

'To convert file object as String'
String download_folder = downloads_folder_path

def generate_xml_file_path = new File(((home_path + '/Downloads/') + 'VifData') + '.xml')

'This step will call edit vif testcase it will read the edit vif file path'
Map edit_viffile_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Edit_Vif_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

List edit_vif_file_path = edit_viffile_data.edit_vif_data.Edit_Vif_File_Path

'CN13_P2_SPL_CableMatters Vif file data'
def cn13_vif_data_row = edit_viffile_data.edit_vif_data.CN13_vif_data_row

def cn13_vif_dropdown = edit_viffile_data.edit_vif_data.CN13_vif_dropdown

'CA03__Chrontel__Two__3.0 Vif file data'
def ca03_vif_data_row = edit_viffile_data.edit_vif_data.CA03_vif_data_row

def ca03_vif_dropdown = edit_viffile_data.edit_vif_data.CA03_vif_dropdown

'DRP01__TrySrc__Surface__3 Vif file data'
def drp01_vif_data_row = edit_viffile_data.edit_vif_data.drp01_vif_data_row

def drp01_vif_dropdown = edit_viffile_data.edit_vif_data.drp01_vif_dropdown

'P45__Belkin__QC4+__0 Vif file data'
def p45_vif_data_row = edit_viffile_data.edit_vif_data.p45_vif_data_row

def p45_vif_dropdown = edit_viffile_data.edit_vif_data.p45_vif_dropdown

def drp01_trysrc_surface_3_map = [(2) : '3.20', (3) : 'DRP02', (4) : 'TrySrc1', (5) : 'Surface1', (6) : '3', (7) : '1:Cable'
    , (8) : '2:Silicon', (11) : '2', (12) : '0:Type-A', (13) : 'YES', (14) : '0', (15) : 'NO', (16) : '1:Consumer/Provider'
    , (17) : '2:DRP', (18) : 'NO', (19) : '0:None', (20) : '3', (21) : '2', (22) : '2', (23) : '2', (24) : '1:Revision 2.0'
    , (25) : 'NO', (26) : 'NO', (27) : 'NO', (28) : 'YES', (29) : 'YES', (30) : 'NO', (31) : 'ABCD', (32) : 'DCBA', (33) : 'YES'
    , (34) : '0:Cable', (35) : 'YES', (36) : 'NO', (37) : 'YES', (38) : '2', (39) : '3', (40) : 'NO', (41) : 'NO', (42) : 'YES'
    , (43) : 'NO', (44) : 'NO', (45) : 'NO', (46) : 'NO', (47) : 'NO', (48) : 'NO', (49) : 'YES', (50) : 'NO', (51) : 'YES'
    , (52) : 'YES', (53) : 'YES', (54) : 'YES', (55) : 'YES', (56) : 'YES', (57) : '2:3A', (58) : 'YES', (59) : '1:UFP-powered'
    , (60) : 'NO', (61) : 'YES', (62) : '1:Gen 3(40 Gb)', (63) : 'NO', (64) : 'YES', (65) : 'NO', (66) : 'NO', (67) : 'NO'
    , (68) : 'NO', (69) : 'NO', (70) : 'NO', (71) : '2', (72) : '1:HBR', (73) : '0:_1lane', (74) : '12', (75) : 'NO', (76) : '0:USB 2'
    , (77) : 'YES', (78) : 'YES', (79) : 'YES', (80) : 'NO', (81) : 'YES', (82) : '0:DCP', (83) : '2:1.5A @ 5V', (84) : 'NO'
    , (85) : 'NO', (86) : 'NO', (87) : 'YES', (88) : 'NO', (89) : 'YES', (90) : '3', (91) : 'NO', (92) : 'YES', (93) : '3:PSD'
    , (94) : '3:Power Brick', (95) : '3', (96) : 'YES', (97) : '0003', (98) : '3000', (99) : '1500', (100) : '0100', (101) : '1:Shared'
    , (103) : '10000', (104) : 'YES', (105) : 'YES', (106) : '2', (107) : 'YES', (109) : '3:PPS', (110) : '3:200% IOC', (111) : '2000'
    , (112) : '5000']

def cn13_p2_spl_cablematters_map = [(2) : '3.28', (3) : 'CN20', (4) : 'CON', (5) : '8', (6) : '5', (7) : '1:Cable', (8) : '2:Silicon'
    , (11) : 'SPL', (12) : '0:Type-A', (13) : 'YES', (14) : 'NO', (15) : '4:DRP', (16) : '0:SRC', (17) : 'YES', (18) : '3:Both'
    , (19) : '2', (20) : '1', (21) : '2', (22) : '3', (23) : '2:Revision 3.0', (24) : 'NO', (25) : 'YES', (26) : 'YES', (27) : 'YES'
    , (28) : 'YES', (29) : 'YES', (30) : 'YES', (31) : 'YES', (32) : 'YES', (33) : 'YES', (34) : 'YES', (35) : 'YES', (36) : 'YES'
    , (37) : 'YES', (38) : 'YES', (39) : 'YES', (40) : 'YES', (41) : 'YES', (42) : 'YES', (43) : 'YES', (44) : 'YES', (45) : 'YES'
    , (46) : '2:Both', (47) : 'YES', (48) : 'YES', (50) : '5000', (51) : 'YES', (52) : 'YES', (53) : 'YES', (54) : '0', (56) : '3:PPS'
    , (57) : '6000', (58) : '7000']

def ca03_chrontel_two_map = [(2) : '3.20', (3) : 'CA0', (4) : 'Chrontel1', (5) : 'Three', (6) : '3', (7) : '2:Re-timer', (8) : '1:Reference Platform'
    , (11) : 'YES', (12) : 'NO', (13) : '2', (14) : '0', (15) : '1', (16) : '3', (17) : 'Unknown Revision', (18) : 'YES'
    , (19) : 'NO', (20) : 'YES', (21) : 'YES', (22) : 'YES', (23) : '1238', (24) : 'YES', (25) : 'YES', (26) : '4:Active Cable'
    , (27) : 'YES', (28) : '063A', (29) : '7238', (30) : 'FFFF', (31) : '1', (32) : '1', (33) : '1:Type-B', (34) : '3:Captive'
    , (35) : '<2ns', (36) : 'Both ends Passive', (37) : 'NO', (38) : '1:3A', (39) : '0:USB 2.0 only', (40) : '3:USB4 Gen3'
    , (41) : '3:50V', (42) : 'YES', (43) : 'YES', (44) : 'NO', (45) : '1:Reserverd']

def p45_belkin_qc4_map = [(2) : '3.28', (3) : 'p80', (4) : 'Belk', (5) : 'QBc-', (6) : '1', (7) : '1:Cable', (8) : '2:Silicon'
    , (11) : '1', (12) : '0:Type-A', (13) : 'YES', (14) : 'NO', (15) : '4:DRP', (16) : '1:SNK', (17) : 'YES', (18) : '3:Both'
    , (19) : '1', (20) : '2', (21) : '3', (22) : '4', (23) : '1:Revision 2.0', (24) : 'NO', (25) : 'YES', (26) : 'YES', (27) : 'YES'
    , (28) : 'YES', (29) : 'YES', (30) : 'NO', (31) : '0:Cable', (32) : 'NO', (33) : 'NO', (34) : 'YES', (35) : '2', (36) : '3'
    , (37) : 'YES', (38) : 'NO', (39) : 'NO', (40) : 'YES', (41) : 'YES', (42) : 'NO', (43) : 'NO', (44) : 'YES', (45) : 'YES'
    , (46) : 'YES', (47) : 'YES', (48) : 'YES', (49) : 'YES', (50) : '0:Default', (51) : 'YES', (52) : '2:Both', (53) : 'YES'
    , (54) : 'YES', (55) : '1:CDP', (56) : '3:3A @ 5V', (57) : 'NO', (58) : '1', (59) : 'YES', (60) : 'YES', (61) : '3:PSD'
    , (62) : '1:PDUSB Hub', (63) : '2', (64) : 'YES', (65) : '00A0', (66) : '00B0', (67) : '00C0', (68) : '00D0', (69) : '1:Shared'
    , (71) : '20000', (72) : 'NO', (73) : 'YES', (74) : '4', (75) : 'YES', (77) : '1:Battery', (78) : '3:200% IOC', (79) : '3000'
    , (80) : '5000', (82) : '1:Battery', (83) : '3:200% IOC', (84) : '4000', (85) : '8000', (87) : '1:Battery', (88) : '3:200% IOC'
    , (89) : '10000', (90) : '8000', (92) : '1:Battery', (93) : '1000', (94) : '2000', (95) : '3000']

'Xml file path list for edit vif'
List edit_vif_xml_file_list = []

'To handle the Suppress Warnings while downloading xml file'
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

WebUI.comment('*****************************************************************************************************************************************************' + 
    '\n')

WebUI.comment(('TestcaseName:: ' + 'Test_3.2.1.4_Edit_Vif') + '\n')

'To Check Product Capability Page Availability'
def product_capability_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

'Testcase will fail if Product Capability Page not present'
if (product_capability_sts) {
    WebUI.comment('Product Capability Page is Present' + '\n')
} else {
    WebUI.comment('Product Capability Page is not Present,Something went wrong ' + '\n')

    assert false
}

'To click Product Capability page'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

WebUI.delay(5)

'STEP 1:To Verify Edit Vif button is in off state'
def edit_vif_state = driver.findElement(By.id('pcEditVifToggleSwitch')).isSelected()

'STEP 2:To click Edit Vif button'
if (edit_vif_state == false) {
    WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/div_Off_pcEditVifToggleSwitch'))
} else {
    assert false
}

for (def separate_file_path : edit_vif_file_path) {
    'Toget File name'
    //int index = separate_file_path.lastIndexOf('\\')

   // String user_provided_filename = separate_file_path.substring(index + 1)

    //WebUI.comment(('User Provided File Name is ::' + user_provided_filename) + '\n')

    WebUI.delay(5)
	
	Path edit_vif_data_path = Paths.get(pc_xml_file_folder_path +'\\'+ separate_file_path)

    'To load the xml file using load xml file button'
    WebUI.callTestCase(findTestCase('Load_Xml_File_RobotFramework'), [('key1') : edit_vif_data_path], FailureHandling.STOP_ON_FAILURE)

    WebUI.delay(10)

    'To Expand Dynmaic data dropdown'
    WebUI.callTestCase(findTestCase('xml_verifier/Test_Xml_File_Expander'), [:], FailureHandling.STOP_ON_FAILURE)

    'STEP 3&4:Change vif data for all parameters'
    if (separate_file_path.contains('CA03__Chrontel__Two')) {
        for (def file_data : ca03_vif_data_row) {
            if (!(ca03_vif_dropdown.contains(file_data))) {
                driver.findElement(By.xpath(('//tr[' + file_data) + ']/td[2]/div/input')).clear()

                String input_data = ca03_chrontel_two_map.get(file_data)

                driver.findElement(By.xpath(('//tr[' + file_data) + ']/td[2]/div/input')).sendKeys(input_data)
            } else {
                driver.findElement(By.cssSelector(('tr:nth-child(' + file_data) + ') .dropdown-toggle')).click()

                String input_data = ca03_chrontel_two_map.get(file_data)

                driver.findElement(By.linkText(input_data)).click()
            }
        }

    } else if (separate_file_path.contains('DRP01')) {
        for (def file_data : drp01_vif_data_row) {
            if (!(drp01_vif_dropdown.contains(file_data))) {
                driver.findElement(By.xpath(('//tr[' + file_data) + ']/td[2]/div/input')).clear()

                driver.findElement(By.xpath(('//tr[' + file_data) + ']/td[2]/div/input')).sendKeys(drp01_trysrc_surface_3_map.get(
                        file_data))
            } else {
                driver.findElement(By.cssSelector(('tr:nth-child(' + file_data) + ') .dropdown-toggle')).click()

                driver.findElement(By.linkText(drp01_trysrc_surface_3_map.get(file_data))).click()
            }
        }
    } else if (separate_file_path.contains('P45')) {
        for (def file_data : p45_vif_data_row) {
            if (!(p45_vif_dropdown.contains(file_data))) {
                driver.findElement(By.xpath(('//tr[' + file_data) + ']/td[2]/div/input')).clear()

                String input_data = p45_belkin_qc4_map.get(file_data)

                driver.findElement(By.xpath(('//tr[' + file_data) + ']/td[2]/div/input')).sendKeys(input_data)
            } else {
                driver.findElement(By.cssSelector(('tr:nth-child(' + file_data) + ') .dropdown-toggle')).click()

                String input_data = p45_belkin_qc4_map.get(file_data)

                driver.findElement(By.linkText(input_data)).click()
            }
        }
    } else {
        for (def file_data : cn13_vif_data_row) {
            if (!(cn13_vif_dropdown.contains(file_data))) {
                driver.findElement(By.xpath(('//tr[' + file_data) + ']/td[2]/div/input')).clear()

                driver.findElement(By.xpath(('//tr[' + file_data) + ']/td[2]/div/input')).sendKeys(cn13_p2_spl_cablematters_map.get(
                        file_data))
            } else {
                driver.findElement(By.cssSelector(('tr:nth-child(' + file_data) + ') .dropdown-toggle')).click()

                driver.findElement(By.linkText(cn13_p2_spl_cablematters_map.get(file_data))).click()
            }
        }
    }
    
    'STEP 5:Click Generate Vif data'

    'To delete Existing VifData.xml file'
    try {
        boolean file_exists_sts = generate_xml_file_path.exists()

        if (file_exists_sts) {
            boolean filedeleted = generate_xml_file_path.delete()

            WebUI.comment('Existing VIF Data xml file Succesfully deleted without any issues::' + filedeleted)
        } else {
            WebUI.comment('XML file is not present under Downloads folder' + '\n')
        }
    }
    catch (NoSuchFileException e) {
        WebUI.comment(('File not present:: ' + e) + '\n')
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

    WebUI.callTestCase(findTestCase('xml_verifier/Test_Edit_Vif_Xml_File_Verifier'), [('key1') : edit_vif_data_path], FailureHandling.STOP_ON_FAILURE)
}

WebUI.comment('********* Test_3.2.1.4_Edit_Vif Execution Ended *********' + '\n')

WebUI.comment('*****************************************************************************************************************************************************' + 
    '\n')


'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'To close browser'
WebUI.closeBrowser()



