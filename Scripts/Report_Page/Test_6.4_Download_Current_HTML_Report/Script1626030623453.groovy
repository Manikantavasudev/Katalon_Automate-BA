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
import java.awt.Desktop as Desktop
import java.awt.Robot as Robot
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper as MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import java.time.*
import java.text.SimpleDateFormat as SimpleDateFormat

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String pc_xml_file_folder = all_data.user_config_data.PC_XML_File_Folder_Path

'To read Report data'
Map report_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Report_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

def report_vif_file_path = report_data.report_page_data.Report_Vif_File_Path

List testcases_name = report_data.report_page_data.Testcases_Name

def vif_file_path = Paths.get((pc_xml_file_folder + '\\') + report_vif_file_path)

'To initialize driver object'
WebDriver driver = DriverFactory.getWebDriver()

'To find downloads folder directory and  vifdata xml file'
String home_path = System.getProperty('user.home')

def downloads_folder_path = new File(home_path + '/Downloads')

WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Product Capability'))

WebUI.setText(findTestObject('Report_panel_objects/Download_HTML_Report/input_ProjectName'), 
    Project_Name)

WebUI.click(findTestObject('Object Repository/Report_panel_objects/Download_HTML_Report/button_Save'))

WebUI.delay(GlobalVariable.short_delay)


WebUI.callTestCase(findTestCase('Load_Xml_File_RobotFramework'), [('key1') : vif_file_path], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(GlobalVariable.long_delay)


'To Click Test config panel'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Test Config'))

'To select and Execute Testcases '
CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectCertificationDropdown'(findTestObject('Object Repository/Test_Config_Panel/Certification_drop_down'), 
    findTestObject('Object Repository/Test_Config_Panel/Page_GRL - USB PD C2/a_All Supported Certifications'))

WebUI.delay(GlobalVariable.short_delay)

'To verify checkbox unselected state'
def expand_testlist_checkbox_sts

try {
    'To check expand list checkbox status'
    expand_testlist_checkbox_sts = WebUI.verifyElementNotChecked(findTestObject('Object Repository/Test_Config_Panel/Expand_Test_List'), 
        5)

    if (expand_testlist_checkbox_sts) {
        WebUI.comment(('expand_testlist_checkbox_sts::' + expand_testlist_checkbox_sts) + '\n')
    }
}
catch (Exception e) {
    WebUI.delay(GlobalVariable.short_delay)

    WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Expand_Test_List'))
} 

' To click Testcase name in Testconfig panel'
CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.selectListOfTestCase'(testcases_name)

WebUI.delay(GlobalVariable.short_delay)

WebUI.click(findTestObject('Object Repository/Test_Config_Panel/button_Start Execution'))

'Execute the test'
def date = new Date()

def sdf = new SimpleDateFormat('yyyy_MM_dd-HH_mm_ss')

def Time_date = sdf.format(date)

WebUI.comment('TestExecution_Execution_Start_Time:: '+Time_date)

'Wait for completion of Test Execution'

'To Verify that Results Panel gets selected & Wait for Execution Competed'
def run_status

WebUI.delay(GlobalVariable.short_delay)

try {
    run_status = true

    while (run_status) {
        'To check Unlive button status'
        def sts = driver.findElement(By.xpath('//button/img[@src=\'../../images/chartIcons/PNG/Live.png\']'))

        if (sts) {
            WebUI.delay(GlobalVariable.long_delay)

            run_status = true
        }
    }
}
catch (Exception e) {
    run_status = false
} 

WebUI.delay(GlobalVariable.long_delay)

WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Report'))

'STEP 1:Click Download Current HTML Report'
WebUI.click(findTestObject('Object Repository/Report_panel_objects/Download_HTML_Report/button_Download Current HTML Report'))

'STEP 2:Verify that Download File Dialog Appears'
WebUI.verifyElementPresent(findTestObject('Object Repository/Report_panel_objects/Download_HTML_Report/div_Download FileFileNameOkCancel'), 
    5)

WebUI.verifyElementVisible(findTestObject('Object Repository/Report_panel_objects/Download_HTML_Report/div_Download FileFileNameOkCancel'))

Report_Name = WebUI.getAttribute(findTestObject('Report_panel_objects/Download_HTML_Report/input_FileName'), 
    'value')

WebUI.delay(GlobalVariable.short_delay)

'STEP 3:Verify that .html Filename is prefixed with the project Name input in Product Capability Panel'
if (Report_Name.contains('.html')) {
    WebUI.comment('.html Filename is prefixed with the project Name input in Product Capability Panel')
}

if (Report_Name.contains(Project_Name)) {
    WebUI.comment('Project_Name is present in Report Name')
}

'STEP 4:Verify the filename has the right timestamp- Data followed by time of execution'
if (Report_Name.contains(Time_date)) {
   WebUI.comment('filename has the right timestamp- Date followed by time of execution')
}

'STEP 5:Verify that the file name is editable add a suffix of _1 to file name'
WebUI.verifyElementClickable(findTestObject('Report_panel_objects/Download_HTML_Report/input_FileName'), 
    FailureHandling.STOP_ON_FAILURE)

WebUI.delay(GlobalVariable.short_delay)

Edited_Report_Name = (Report_Name.minus('.html') + '_1.html')

WebUI.sendKeys(findTestObject('Report_panel_objects/Download_HTML_Report/input_FileName'), 
    Keys.chord(Keys.CONTROL, 'a'))

WebUI.sendKeys(findTestObject('Report_panel_objects/Download_HTML_Report/input_FileName'), 
    Keys.chord(Keys.BACK_SPACE))

WebUI.setText(findTestObject('Report_panel_objects/Download_HTML_Report/input_FileName'), 
    Edited_Report_Name)

WebUI.delay(GlobalVariable.short_delay)

'STEP 6:Click Cancel verify that no such file is downloaded'
WebUI.click(findTestObject('Object Repository/Report_panel_objects/Download_HTML_Report/button_Cancel'))

def grl_report_path_sts = new File((home_path + '/Downloads/') + Report_Name)

boolean html_file_sts_before_download = grl_report_path_sts.exists()

if (html_file_sts_before_download ) {
	
	WebUI.comment('Action is Performed after clicking Cancel' )
}
else {
	WebUI.comment('Action is Not performed after clicking cancel ')
}

'STEP 7:Click Download Current HTML Report'
WebUI.click(findTestObject('Object Repository/Report_panel_objects/Download_HTML_Report/button_Download Current HTML Report'))

'delete existing grl_report data in downloads folder'

boolean report_file_exists_sts = grl_report_path_sts.exists()

if (report_file_exists_sts) {
    boolean filedeleted = grl_report_path_sts.delete()

    WebUI.comment('Existing  Report file Succesfully deleted without any issues::' + filedeleted)
}

WebUI.delay(GlobalVariable.short_delay)

'STEP 8:Click ok '
WebUI.click(findTestObject('Object Repository/Report_panel_objects/Download_HTML_Report/button_Ok'))

WebUI.delay(GlobalVariable.short_delay)

'STEP 9:tVerify that the report with the default file name is saved in the default downloads folder'

boolean downloaded_report_file_sts = grl_report_path_sts.exists()

if (downloaded_report_file_sts) {

	WebUI.comment('Report File present in Downloads::' + downloaded_report_file_sts)
}

'STEP 10 :Click on html report file and verifthe y that is displayed in new browser'

/*def downloaded_html_file_path = Paths.get(grl_report_path_sts)

driver.get(downloaded_html_file_path)

WebUI.delay(GlobalVariable.short_delay)

String currentUrl = WebUI.getUrl()

WebUI.comment(currentUrl)

WebUI.closeWindowUrl(currentUrl, FailureHandling.STOP_ON_FAILURE)*/


'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()



