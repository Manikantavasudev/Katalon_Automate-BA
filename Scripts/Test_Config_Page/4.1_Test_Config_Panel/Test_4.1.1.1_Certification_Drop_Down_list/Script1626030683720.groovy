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

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()


'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String python_path = all_data.user_config_data.Python_exe_path

String Installation_path = all_data.user_config_data.Katalon_installation_path

WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

'To delete certification_file_validator Log file for appending the new data'

File cert_log_file = new File(Installation_path + '\\certification_file_validator.log');
if(cert_log_file.exists()){
	
	cert_log_file.delete();
}
cert_log_file.createNewFile();

InputStream cert_log_file_obj = new FileInputStream(cert_log_file);

'Logger function'
CustomKeywords.'custom.Logger.Certification_LoggerFactory.customizeLogger4CommentKeyword'()

def dut_certification_test_list = []

def modifiedList = []

def dut_list = []

List all_datas = []

List actual_ui_count_test_list = []

List remove_subtest_for_count = ['QC_LEGACY_Shorting', 'QC_LEGACY_HVDCP', 'QC_LEGACY_PD_Req', 'QC_LEGACY_PD_Removal', 'QC_LEGACY_PD_Phy_Err_Rej'
    , 'QC_LEGACY_PD_Req_Reg', 'QC_LEGACY_Trans', 'QC_LEGACY_Continuous_mode', 'QC_LEGACY_Pwr_pf', 'QC_LEGACY_USBPD_Trans'
    , 'PDMER_PHY_TEST_ALL_UUT', 'PDMER_PHY_TEST_SRC_SNK_CAP_UUT', 'PDMER_PROT_TEST_ALL_PD2_PD3_Mode', 'PDMER_PROT_TEST_ALL_PD3_Mode'
    , 'PDMER_PROT_TEST_SNK_SRC_CAP_PD3_Mode', 'PDMER_PROT_TEST_SRC_CAP_PD2_PD3_Mode', 'PDMER_PROT_TEST_SRC_CAP_PD3_Mode'
    , 'PDMER_PROT_TEST_SNK_CAP_PD2_PD3_Mode', 'PDMER_PROT_TEST_SNK_CAP_PD3_Mode', 'PDMER_PROT_VDM_SRC_CAP_PD2_PD3', 'PDMER_PROT_VDM_SNK_CAP_PD2_PD3'
    , 'PDMER_PROT_VDM_CABLE_PD2_PD3', 'PDMER_PROT_VDM_CABLE_PD3', 'PDMER_PROT_PS_SRC_CAP_UUT_PD2_PD3', 'PDMER_PROT_PS_SNK_CAP_UUT_PD2_PD3'
    , 'PDMER_EPR_SRC', 'PDMER_EPR_SNK']

List remove_moi_for_count = ['MOI_Power Delivery 3.0 Tests-v1.19', 'MOI_PD2 Communication Engine Tests-v1.09', 'MOI_PD2 Deterministic Tests-v1.14'
    , 'MOI_USB-C Functional Tests-v0.82', 'MOI_Source Power Tests-v0.74', 'MOI_USB Power Delivery Compliance Test Specification-v1.3 RC12', 'MOI_Quick Charger 3.0 Tests-v1.4'
    , 'MOI_Quick Charge 4 Tests-v1.0', 'MOI_Thunderbolt Power Tests-Rev1.5 Ver0.9', 'MOI_DisplayPort Alternate Mode Tests-v4', 'MOI_BC1.2 DCP Sink Tests'
    , 'MOI_MFi Charger Tests-v1.0', 'QC3+ Tests-v1.2']

def expected_moi_list = ['Power Delivery 3.0 Tests-v1.19', 'PD2 Communication Engine Tests-v1.09', 'PD2 Deterministic Tests-v1.14', 'USB-C Functional Tests-v0.82'
    , 'Source Power Tests-v0.74', 'USB Power Delivery Compliance Test Specification-v1.3 RC12', 'Quick Charger 3.0 Tests-v1.4', 'Quick Charge 4 Tests-v1.0', 'Thunderbolt Power Tests-Rev1.5 Ver0.9'
    , 'DisplayPort Alternate Mode Tests-v4', 'BC1.2 DCP Sink Tests', 'MFi Charger Tests-v1.0', 'QC3+ Tests-v1.2']

def dut_dict = [('Consumer Only') : 'IsCons', ('Consumer Provider') : 'IsCP', ('Provider Consumer') : 'IsPC', ('Provider Only') : 'IsProv'
    , ('Dual Role Power[DRP]') : 'IsDRP', ('Cable') : 'IsCable', ('Type C Only') : 'IsType_C_SRC']



'To get port1 dut type list'
def port1_duttype_list = CustomKeywords.'grl_usb_pd_c2_Testcase.ProductCapability_Page.port1InformationaldutDropdownList'()

for (String port1_duttype : port1_duttype_list) {
    'To click Product Capability page'
    WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

    'To initialize driver object'
    WebDriver driver = DriverFactory.getWebDriver()

    'To click Port1 DUT dropdown icon'
    WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_1_dut_dropdown_icon'))

    WebUI.delay(GlobalVariable.short_delay)

    driver.findElement(By.xpath(('//a[text()=\'' + port1_duttype) + '\']')).click()

    WebUI.comment('selected Dut type::   ' + port1_duttype)

    WebUI.delay(GlobalVariable.short_delay)

    'To click Testconfig page'
    WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Test Config'))

	def certification_list = CustomKeywords.'grl_usb_pd_c2_Testcase.TestConfig_Page.certificationDropdownList'()
	
	for(String certification_name:certification_list) {
    'To click Dropdown icon '
    WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Certification_drop_down'))

    //WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Page_GRL - USB PD C2/a_All Supported Certifications'))
	
    driver.findElement(By.xpath(('//a[text()=\'' + certification_name) + '\']')).click()

    WebUI.delay(GlobalVariable.short_delay)

    WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Expand_Test_List'))

    'To get the list of Testcase based on the Dut type selection'
    List certification_dut_based_testcaselist = driver.findElements(By.xpath('//ul[@class=\'rc-tree-child-tree rc-tree-child-tree-open\']/li/span/span'))

    for (WebElement certification_dut_testcase : certification_dut_based_testcaselist) {
        //
        def moi_and_testcase = certification_dut_testcase.getText()

        if (expected_moi_list.contains(moi_and_testcase)) {
            exp_testlist_moi = 'MOI_'

            dut_certification_test_list.add(exp_testlist_moi + moi_and_testcase)
        } else {
            dut_certification_test_list.add(moi_and_testcase)
        }
    }
    
    'excluding subset'
    dut_certification_test_list = (dut_certification_test_list - remove_subtest_for_count)

    'excluding moi for counting the ui testcase list'
    actual_ui_count_test_list = (dut_certification_test_list - remove_moi_for_count)

    String tot_actual_ui_testcase_count = actual_ui_count_test_list.size()

    WebUI.uncheck(findTestObject('Object Repository/Test_Config_Panel/Expand_Test_List'))

    String updated_dut_list = new groovy.json.JsonBuilder(dut_certification_test_list).toString()

    //WebUI.comment('Certification dut based Testcase list' + updated_dut_list)
    modifiedList = dut_certification_test_list.collect({ 
            ('"' + it) + '"'
        })
    WebUI.comment('*********************************************************************************************************************************'+'\n')

    WebUI.comment(('UI selected Certification     ' + '*****    ') + certification_name)

    WebUI.comment(('UI selected DUT       ' + '*****    ') + port1_duttype)

    'To get selected Test count value'
    String selected_test_count = driver.findElement(By.xpath('//div[@class=\'selected-test-count-div\']/span/strong')).getText()

    String ui_testcase_count = selected_test_count.split('/')[1]

    String tot_ui_testcase_count = ui_testcase_count

    //WebUI.comment(('UI Selected Test Count  ' + '*****   ') + selected_test_count)
    dut_list = modifiedList

    String selective_dut = dut_dict.get(port1_duttype)

    all_datas.add(selective_dut)

    all_datas.add(certification_name)

    all_datas.add(dut_certification_test_list)

    println('alldatas' + all_datas)

    def testcase_lists = [('dut_testlist') : all_datas]

    String ui_filename = 'ui_testcase_list.json'

    def json_str = JsonOutput.toJson(testcase_lists)

    def json_beauty = JsonOutput.prettyPrint(json_str)

    File file = new File(ui_filename)

    WebUI.delay(GlobalVariable.short_delay)

    file.write(json_beauty)

    WebUI.delay(GlobalVariable.long_delay)

    String certification_py_path = Installation_path + '\\Xml_TestCaseListReader.py'

    String json_data_path = Installation_path + '\\TestData.json'
	
	String certification_json_path =  Installation_path +'\\Test Cases\\Python_files\\Certification.json'

    println('Certification dut based Testcase list' + updated_dut_list)

    def task = [python_path, certification_py_path, json_data_path,ui_filename].execute()

    def text_val = task.text

    println(text_val)

    WebUI.comment('Here is ui testcase count status')

    if (tot_actual_ui_testcase_count == tot_ui_testcase_count) {
        WebUI.comment(((('ui_display_testcase_count:: ' + tot_ui_testcase_count) + ' == ') + 'actual_ui_testcase_count:: ') + 
            tot_actual_ui_testcase_count)
    } else {
        WebUI.comment(((('ui_display_testcase_count:: ' + tot_ui_testcase_count) + ' != ') + 'actual_ui_testcase_count:: ') + 
            tot_actual_ui_testcase_count)
    }
    
    WebUI.comment('Here is the dut based Certification validation Status')

    File file1 = new File(Installation_path + '\\output1.txt')

    String certification_validation_res = file1.getText()

    WebUI.comment(certification_validation_res)

    String tot_be_testcase_count

    // read all the lines into a list, each line is an element in the list
    File file_val = new File(Installation_path + '\\output1.txt')

    def lines = file_val.readLines()

    for (def line : lines) {
        if (line.contains('Matched TestCase Count:')) {
            tot_be_testcase_count = (line.split(':')[1])
        }
    }
    
    WebUI.comment('*********************************************************************************************************************************'+'\n')

    'To clear all the existing list in ui'
    dut_certification_test_list.clear()

    dut_list.clear()

    modifiedList.clear()

    all_datas.clear()

    actual_ui_count_test_list.clear()
}
}


'To read source file path'
def source_file_path = Installation_path + '\\certification_file_validator.log'

'To read destination file path'
def destination_file_path = Installation_path + '\\Log Files\\Certification_File_Validator.log'

File des_log_file = new File(destination_file_path);
if(des_log_file.exists()){
	
	des_log_file.delete();
}
des_log_file.createNewFile();

'To copy the source log file into destination file'
def src_log_file = new File(source_file_path)

def dst_log_file = new File(destination_file_path)

dst_log_file << src_log_file.text


'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()



