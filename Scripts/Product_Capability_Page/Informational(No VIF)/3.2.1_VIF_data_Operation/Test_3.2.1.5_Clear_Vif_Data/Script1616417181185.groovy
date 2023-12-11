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
CustomKeywords.'custom.Logger.CustomLoggerFactory.customizeLogger4CommentKeyword'()

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

WebUI.delay(GlobalVariable.short_delay)

'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

List pc_c2_info_port1_dut_list = all_data.user_config_data.PC_Info_Port1_Dut_List

List pc_c2_info_port2_dut_list = all_data.user_config_data.PC_Info_Port2_Dut_List

List pc_c2_vif_file_path = all_data.user_config_data.PC_C2_Vif_File_Path

WebUI.comment('*****************************************************************************************************************************************************'+'\n')

WebUI.comment('TestcaseName:: '+'Test_3.2.1.5_Clear_Vif_Data'+'\n')

'To Check Product Capability Page Availability'
def product_capability_sts = WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

'Testcase will fail if Product Capability Page not present'
if (product_capability_sts) {
    WebUI.comment('Product Capability Page is Present'+'/n')
} else {
    WebUI.comment('Product Capability Page is not Present,Something went wrong '+'/n')

    assert false
}

'To click Product Capability page'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/span_Product Capability'))

WebDriver driver = DriverFactory.getWebDriver()

for(String separate_vif_file_path:pc_c2_vif_file_path) {
'To load xml file by using custom keyword'

'STEP 1:To click Clear VIF button'
WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/a_Clear VIF Data'))

'STEP 2:To verify Table component'
WebUI.verifyElementNotPresent(findTestObject('Object Repository/Product_Capability_Objects/td_VIF Product Type'), 5)

'STEP 3&4 :To verify dut dropdown list enable or not & To verify selected dut text one by one'
List port1_dut_list = CustomKeywords.'grl_usb_pd_c2_Testcase.ProductCapability_Page.port1InformationaldutDropdownList'()

'To compare the expected cable slection list and actual cable selection list in port1'
assert port1_dut_list == pc_c2_info_port1_dut_list

'To verify Port 1 dropdown values'
try {
    if (port1_dut_list.size() > 0) {
        for (String port1_dut_element : port1_dut_list) {
            WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_1_dut_dropdown_icon'))

            driver.findElement(By.linkText(port1_dut_element)).click()

            WebUI.delay(GlobalVariable.short_delay)

            def port1_selected_dut = WebUI.getText(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_1_dut_dropdown_icon'))

            assert port1_selected_dut == port1_dut_element
        }
    } else {
        WebUI.comment('Dut Dropdown is not in visible state'+'/n')

        assert false
    }
}
catch (Exception e) {
    WebUI.takeScreenshot()
} 
}


WebUI.comment('********* Test_3.2.1.5_Clear_Vif_Data Execution Ended *********'+'\n')

WebUI.comment('*****************************************************************************************************************************************************'+'\n')


'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()


