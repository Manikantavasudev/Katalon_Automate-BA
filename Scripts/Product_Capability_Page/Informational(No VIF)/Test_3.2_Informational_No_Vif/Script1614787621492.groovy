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

String pc_c2_project_name = all_data.user_config_data.PC_C2_Project_Name


WebUI.comment('*****************************************************************************************************************************************************'+'\n')

WebUI.comment('TestcaseName:: '+'Test_3.2_Informational_No_Vif'+'\n')

'To Check Product Capability Page Availability'
def product_capability_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

'Testcase will fail if Product Capability Page not present'
if (product_capability_sts) {
    WebUI.comment('Product Capability Page is Present'+'\n')
} else {
    WebUI.comment('Product Capability Page is not Present,Something went wrong '+'\n')

    assert false
}

'To click Product Capability page'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

'STEP 1:To click on Informational (NO VIF) radio button'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/input_Informational (No VIF)_RadioBtn'))

'STEP 2: To click Clear VIF Data'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/a_Clear VIF Data'))

'STEP 3:To verify the following controls are visible'

'To check Load xml vif file button status'
def load_xml_vif_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/button_Load XML VIF File'))

WebUI.comment('Load XML VIF file button status:: ' + load_xml_vif_sts+'\n')

'To check Create New vif button status'
def create_new_vif_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/button_Create New VIF'))

WebUI.comment('Create New VIF button status:: ' + create_new_vif_sts+'\n')

'To check Generate VIF(VIF Data) button status'
def generate_vif_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/button_Generate VIF(VIF Data)'))

WebUI.comment('Generate VIF(VIF Data) button status::' + generate_vif_sts+'\n')

'To check Edit vif button status'
def edit_vif_toggle_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/div_Off_pcEditVifToggleSwitch'))

WebUI.comment('Edit VIF toggle button status:: ' + edit_vif_toggle_sts+'\n')

'To check Clear Vif Data button status'
def clear_vif_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/a_Clear VIF Data'))

WebUI.comment('Clear VIF Data button Status:: ' + clear_vif_sts+'\n')

'Testcase will fail if following buttons are not visible '
if ((((load_xml_vif_sts && create_new_vif_sts) && generate_vif_sts) && edit_vif_toggle_sts) && clear_vif_sts) {
    WebUI.comment('Load XML VIF File button & Create New VIF button & Generate VIF(VIF Data) button ,Edit VIF button &Clear VIF button are Present'+'\n')
} else {
    WebUI.comment('Some button locators are Not Present '+'\n')

    assert false
}

'To check Port 1& Port 2 controls status'

'To check port1 dut Type & Cabel Selection controls'
def port_1_duttype_label_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/a_Clear VIF Data'))

WebUI.comment('Clear VIF Data button Status:: ' + port_1_duttype_label_sts+'\n')

def port1_cabel_selection_label_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/a_Clear VIF Data'))

WebUI.comment('Clear VIF Data button Status:: ' + port1_cabel_selection_label_sts+'\n')

'Testcase will fail if following buttons are not visible '
if (port_1_duttype_label_sts && port1_cabel_selection_label_sts) {
    WebUI.comment('Dut Type label and Cabel Selection label present'+'\n')

    'To check DUT Type dropdown list and Cabel Selection dropdown list'
    CustomKeywords.'grl_usb_pd_c2_Testcase.ProductCapability_Page.port1InformationaldutDropdownList'()

    CustomKeywords.'grl_usb_pd_c2_Testcase.ProductCapability_Page.port1InformationalCabelDropdownList'()

    CustomKeywords.'grl_usb_pd_c2_Testcase.ProductCapability_Page.port2InformationaldutDropdownList'()

    CustomKeywords.'grl_usb_pd_c2_Testcase.ProductCapability_Page.port2InformationalCabelDropdownList'()
} else {
    WebUI.comment('Dut Type label and Cabel Selection label Not  present'+'\n')

    assert false
}

WebUI.comment('********* Test_3.2_Informational_No_Vif Execution Ended *********'+'\n')

WebUI.comment('*****************************************************************************************************************************************************'+'\n')


'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()


