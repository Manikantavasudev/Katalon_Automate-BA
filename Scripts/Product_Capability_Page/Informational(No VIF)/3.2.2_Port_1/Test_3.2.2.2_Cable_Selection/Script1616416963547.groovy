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

'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

List pc_c2_info_port1_cable_list = all_data.user_config_data.PC_Info_Port1_Cable_List

List pc_c2_info_port2_cable_list = all_data.user_config_data.PC_Info_Port2_Cable_List


WebDriver driver = DriverFactory.getWebDriver()

WebUI.comment('*****************************************************************************************************************************************************' + 
    '\n')

WebUI.comment(('TestcaseName:: ' + 'Test_3.2.2.2_Cable_Selection') + '\n')

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

'STEP 1:Tpo verify the Port1&port2 cable selection list'
List port1_cable_list = CustomKeywords.'grl_usb_pd_c2_Testcase.ProductCapability_Page.port1InformationalCabelDropdownList'()

'To compare the expected cable slection list and actual cable selection list in port1'
assert port1_cable_list == pc_c2_info_port1_cable_list

List port2_cable_list = CustomKeywords.'grl_usb_pd_c2_Testcase.ProductCapability_Page.port2InformationalCabelDropdownList'()

'To compare the expected cable slection list and actual cable selection list in port1'
assert port2_cable_list == pc_c2_info_port2_cable_list

'STEP 2&3 :To click cable selection list one by one and Tpo verify selected cable text'

'To verify Port 1 cabel values'
try {
    if (port1_cable_list.size() > 0) {
        for (String port1_cable_element : port1_cable_list) {
            WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_1_cabel_dropdown_icon'))

			WebUI.delay(GlobalVariable.short_delay)
			
            driver.findElement(By.linkText(port1_cable_element)).click()
			
			WebUI.delay(GlobalVariable.short_delay)
			
            def port1_selected_cabel = WebUI.getText(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_1_cabel_dropdown_icon'))

            if (((port1_selected_cabel == port1_cable_element) || port1_selected_cabel.contains('No Cable ( For Cable Testing )')) || 
            port1_selected_cabel.contains('No Cable')) {
            }
			else {
				assert false
			}
        }
    } else {
        WebUI.comment('Cabel Selection Dropdown is not in visible state' + '\n')

        assert false
    }
}
catch (Exception e) {
    WebUI.takeScreenshot()
} 



'To verify Port 2 cabel values'
try {
    if (port2_cable_list.size() > 0) {
        for (String port2_cable_element : port2_cable_list) {
            WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_2_cabel_dropdown_icon'))

            driver.findElement(By.linkText(port2_cable_element)).click()

            WebUI.delay(GlobalVariable.short_delay)

            def port2_selected_cabel = WebUI.getText(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_2_cabel_dropdown_icon'))

            println(port2_selected_cabel)

            if (((port2_selected_cabel == port2_cable_element) || port2_selected_cabel.contains('No Cable ( For Cable Testing )')) || 
            port2_selected_cabel.contains('No Cable')) {
            }
			else {
				assert false
			}
        }
    } else {
        WebUI.comment('Cabel Selection Dropdown is not in visible state' + '\n')

        assert false
    }
}
catch (Exception e) {
    WebUI.takeScreenshot()
} 



WebUI.comment('********* Test_3.2.2.2_Cable_Selection Execution Ended *********' + '\n')

WebUI.comment('*****************************************************************************************************************************************************' + 
    '\n')


'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()


