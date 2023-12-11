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

'To read json file name'
String json_file_name = key_json

'To read selected dut name'
def selected_dut_type = key_dut

'To Read Compare Tool Moi data'
File file_obj = new File(('Five_Port_Data_Config' + '\\') + json_file_name)

Map input_prop = new JsonSlurper().parseFile(file_obj, 'UTF-8')

'To read moi ulist'
List moi_list = input_prop.MOI_List

def moi_ref = input_prop.All_Dut_Type_Except_Cable

def pd3_edit_config = moi_ref.Power_Delivery_3_Edit_Config

def pd2_comm_config = moi_ref.PD2_Communication_Engine_Edit_Config

def usb_edit_config = moi_ref.USB_Functional_Edit_Config

def source_power_edit_config = moi_ref.Source_Power_Edit_Config

def pd_merge_edit_config = moi_ref.PD_Merged_Edit_Config

def quick3_edit_config = moi_ref.Quick_Charger_3_Edit_Config

def quick4_edit_config = moi_ref.Quick_Charger_4_Edit_Config

def thunderbolt_edit_config = moi_ref.Thunderbolt_Power_Tests_Edit_Config

def displayport_edit_config = moi_ref.DisplayPort_Alternate_Mode_Edit_Config

def dcp_sink_edit_config = moi_ref.BC12_DCP_Sink_Edit_Config

'driver object Initialization'
WebDriver driver = DriverFactory.getWebDriver()

if (moi_list.contains('Power Delivery 3.0 Tests-v1.19')||moi_list.contains('Power Delivery 3.0 Tests')) {
    if (pd3_edit_config == 'YES') {
        if ((selected_dut_type == 'Provider Consumer') || (selected_dut_type == 'Dual Role Power[DRP]')) {
            WebUI.callTestCase(findTestCase('Common_Data_Reader/Five_Port_Moi_Data_Reader/Other_Dut/Config_Power_Delivery_3_Tests'), 
                [('key1') : json_file_name], FailureHandling.STOP_ON_FAILURE)
        }
    }
}

if (moi_list.contains('PD2 Communication Engine Tests-v1.09')||moi_list.contains('PD2 Communication Engine Tests')) {
    if (pd2_comm_config == 'YES') {
        WebUI.callTestCase(findTestCase('Common_Data_Reader/Five_Port_Moi_Data_Reader/Other_Dut/Config_PD2_Communication_Engine_Tests'), 
            [('key1') : json_file_name], FailureHandling.STOP_ON_FAILURE)
    }
}

if (moi_list.contains('USB-C Functional Tests-v0.82')||moi_list.contains('USB-C Functional Tests')) {
    if (usb_edit_config == 'YES') {
        WebUI.callTestCase(findTestCase('Common_Data_Reader/Five_Port_Moi_Data_Reader/Other_Dut/Config_Functional_Tests'), 
            [('key1') : json_file_name], FailureHandling.STOP_ON_FAILURE)
    }
}

if (moi_list.contains('Source Power Tests-v0.74')||moi_list.contains('Source Power Tests')) {
    if (source_power_edit_config == 'YES') {
        WebUI.callTestCase(findTestCase('Common_Data_Reader/Five_Port_Moi_Data_Reader/Other_Dut/Config_Source_Power_Tests'), 
            [('key1') : json_file_name], FailureHandling.STOP_ON_FAILURE)
    }
}

if (moi_list.contains('PD Merged Tests (Beta)-v1.3 RC12')||moi_list.contains('PD Merged Tests (Beta)')) {
    if (pd_merge_edit_config == 'YES') {
        WebUI.callTestCase(findTestCase('Common_Data_Reader/Five_Port_Moi_Data_Reader/Other_Dut/Config_PD_Merged_Tests'), 
            [('key1') : json_file_name], FailureHandling.STOP_ON_FAILURE)
    }
}

if (moi_list.contains('Quick Charger 3.0 Tests-v1.4') || moi_list.contains('QC3+ Tests')||moi_list.contains('QC3+ Tests-v1.2')||moi_list.contains('Quick Charger 3.0 Tests')) {
    if (quick3_edit_config == 'YES') {
        WebUI.callTestCase(findTestCase('Common_Data_Reader/Five_Port_Moi_Data_Reader/Other_Dut/Config_Quick_Charger_3_Tests'), 
            [('key1') : json_file_name], FailureHandling.STOP_ON_FAILURE)
    }
}

if (moi_list.contains('Quick Charge 4 Tests-v1.0')||moi_list.contains('Quick Charge 4 Tests')) {
    if (quick4_edit_config == 'YES') {
        WebUI.callTestCase(findTestCase('Common_Data_Reader/Five_Port_Moi_Data_Reader/Other_Dut/Config_Quick_Charge_4_Tests'), 
            [('key1') : json_file_name], FailureHandling.STOP_ON_FAILURE)
    }
}

if (moi_list.contains('DisplayPort Alternate Mode Tests-v4')||moi_list.contains('DisplayPort Alternate Mode Tests')) {
    if (displayport_edit_config == 'YES') {
        WebUI.callTestCase(findTestCase('Common_Data_Reader/Five_Port_Moi_Data_Reader/Other_Dut/Config_DisplayPort_Alternate_Mode_Tests'), 
            [('key1') : json_file_name], FailureHandling.STOP_ON_FAILURE)
    }
}

if (moi_list.contains('BC1.2 DCP Sink Tests')||moi_list.contains('BC1.2 DCP Sink Tests')) {
    if (dcp_sink_edit_config == 'YES') {
        WebUI.callTestCase(findTestCase('Common_Data_Reader/Five_Port_Moi_Data_Reader/Other_Dut/Config_BC12_DCP_Sink_Tests'), 
            [('key1') : json_file_name], FailureHandling.STOP_ON_FAILURE)
    }
}

if (moi_list.contains('Thunderbolt Power Tests-Rev1.5 Ver0.9')||moi_list.contains('Thunderbolt Power Tests')) {
    if (thunderbolt_edit_config == 'YES') {
        WebUI.callTestCase(findTestCase('Common_Data_Reader/Five_Port_Moi_Data_Reader/Other_Dut/Config_Thunderbolt_Power_Tests'), 
            [('key1') : json_file_name], FailureHandling.STOP_ON_FAILURE)
    }
}



