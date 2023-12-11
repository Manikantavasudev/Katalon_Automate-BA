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

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

String Installation_path = all_data.user_config_data.Katalon_installation_path

'To delete vif_file_validator Log file for appending the new data'
File validator_log_file = new File(Installation_path + '\\vif_file_validator.log')

if (validator_log_file.exists()) {
    validator_log_file.delete()
}

validator_log_file.createNewFile()

InputStream validator_log_file_obj = new FileInputStream(validator_log_file)

CustomKeywords.'custom.Logger.CustomLoggerFactory.customizeLogger4CommentKeyword'()

WebUI.comment('*****************************************************************************************************************************************************' + 
    '\n')

WebUI.comment(('TestcaseName:: ' + 'Test_3.1_Compliance_Mode') + '\n')

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

'STEP 1:To Check Project Name editbox'
def project_name_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/input_Project Name'))

'Testcase will fail if Project name editbox not  present'
if (product_capability_sts) {
    WebUI.comment('Project name editbox is Present' + '\n')
} else {
    WebUI.comment('Project name editbox is not  Present' + '\n')

    assert false
}

'STEP 2:To Check Informational (NO VIF) Mode Radio Button'
def info_radio_btn_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/input_Informational (No VIF)_RadioBtn'))

'Testcase will fail if Informational (NO VIF) Mode Radio Button not  present'
if (info_radio_btn_sts) {
    WebUI.comment('Informational (NO VIF) Mode Radio Button Present' + '\n')
} else {
    WebUI.comment('Informational (NO VIF) Mode Radio Button not  Present' + '\n')

    assert false
}

'STEP 3:To Check Compliance Mode Radio Button'
def compliance_radio_btn_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/input_Compliance_pcComplianceTestRadioBtn'))

'Testcase will fail if Compliance Mode not  present'
if (compliance_radio_btn_sts) {
    WebUI.comment('Compliance Mode Radio Button is Present' + '\n')
} else {
    WebUI.comment('Compliance Mode Radio Button is not Present' + '\n')

    assert false
}

'To click Compliance Radio Button'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/input_Compliance_pcComplianceTestRadioBtn'))

'STEP 4:To Check VIF Data Operation Label Availability'
def vif_data_label_sts = WebUI.verifyElementVisible(findTestObject('Product_Capability_Objects/Compliance/p_VIF Data Operation'))

'Testcase will fail if VIF Data Operation label is  not  present'
if (vif_data_label_sts) {
    WebUI.comment('VIF Data Operation label is Present' + '\n')
} else {
    WebUI.comment('VIF Data Operation label is not Present' + '\n')

    assert false
}

'STEP 5:To Check port1 Label Availability '
def port1_label_sts = WebUI.verifyElementVisible(findTestObject('Product_Capability_Objects/Compliance/p_Port 1'))

'Testcase will fail if port1 Label is  not present'
if (port1_label_sts) {
    WebUI.comment('port1 Label is Present' + '\n')
} else {
    WebUI.comment('port1 Label is  not Present ' + '\n')

    assert false
}

'STEP 6 :To Check Cabel Selection Label Availability '
def cabel_selection_sts = WebUI.verifyElementVisible(findTestObject('Product_Capability_Objects/Compliance/span_Cable Selection'))

'Testcase will fail if Cabel Selection Label  is  not present'
if (cabel_selection_sts) {
    WebUI.comment('Cabel Selection Label  is present' + '\n')
} else {
    WebUI.comment('Cabel Selection Label  is not present' + '\n')

    assert false
}

'STEP 7 :To Check Cabel Selection dropdown list'
CustomKeywords.'grl_usb_pd_c2_Testcase.ProductCapability_Page.complianceCabelSelectionDropdown'()

'STEP 8 :To check Help icon'
def help_icon_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/Compliance/div_Cabel_Help_Icon'))

'Testcase will fail if Help icon button is  not present'
if (help_icon_sts) {
    WebUI.comment('Help icon is present' + '\n')
} else {
    WebUI.comment('Help icon is  not  present' + '\n')

    assert false
}

'STEP 9:To check USB-IF VIF Download Link Availability'
def usb_if_link_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/a_USB-IF VIF Generator Download Link'))

'Testcase will fail if USB-IF VIF Download link  is  not present'
if (usb_if_link_sts) {
    WebUI.comment('USB-IF VIF Download link is present' + '\n')
} else {
    WebUI.comment('USB-IF VIF Download link is not present' + '\n')

    assert false
}

WebUI.comment('********* Test_3.1_Compliance_Mode Execution Ended *********' + '\n')

WebUI.comment('*****************************************************************************************************************************************************' + 
    '\n')


'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()



