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
import org.openqa.selenium.support.ui.Select as Select
import java.awt.Toolkit as Toolkit
import java.awt.datatransfer.StringSelection as StringSelection
import java.awt.event.KeyEvent as KeyEvent
import java.awt.Robot as Robot
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.support.Color as Color
import org.openqa.selenium.WebDriver as WebDriver

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

'This step will call Common Data Reader Testcase and it will read the user provided json data'
Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

'driver object initialization'
WebDriver driver = DriverFactory.getWebDriver()

'Expected popup text variables'
String exp_fw_update_progress_txt = 'Firmware update is in progress. Please wait for few minutes'

String exp_update_c2_firmware_txt = 'Update C2 Firmware'

String exp_prerequisite_image_text = 'Please refer above setup image and connect firmware update USB port of C2 to the test PC using standard USB Type-B cable where C2 Browser Application is running'

'STEP 1:To verify Update Firmware button is present under Connection Setup panel'
WebUI.verifyElementPresent(findTestObject('Object Repository/Firmware_Update_Objects/button_Update Firmware'), 5)

'STEP 2:To click on Update firmware button'
WebUI.click(findTestObject('Object Repository/Firmware_Update_Objects/button_Update Firmware'))

'Disable the Smart Wait function when it\'s enabled by  default in project settings.'
WebUI.disableSmartWait()

try {
    'STEP 3: To Verify the following status message appears.“Firmware Update in Progress. Please wait for Few Minutes” '

    //String fw_update_in_progress_text = WebUI.getText(findTestObject('Object Repository/Firmware_Update_Objects/p_Firmware_Update_In_Progress'))
    'STEP 4:To verify C2 Firmware dailogue appears'
    String update_c2_firmware_txt = WebUI.getText(findTestObject('Object Repository/Firmware_Update_Objects/strong_Update C2 Firmware'))

    assert update_c2_firmware_txt == exp_update_c2_firmware_txt

    if (update_c2_firmware_txt == exp_update_c2_firmware_txt) {
        WebUI.comment('Update Firmware Text is present as Expected:: ' + update_c2_firmware_txt)
    } else {
        WebUI.comment('Update Firmware Text is Not present as Expected:: ' + update_c2_firmware_txt)
    }
    
    'STEP 5:To verify Firmware Update Setup Image'
    String prerequisite_image_text = WebUI.getText(findTestObject('Object Repository/Firmware_Update_Objects/strong_Prerequisite_Image_Text'))

    if (prerequisite_image_text == exp_prerequisite_image_text) {
        WebUI.comment('To verify Fimware Update Setup Image text::  ' + prerequisite_image_text)
    } else {
        WebUI.comment(' Fimware Update Setup Image text is not present as Expected::  ' + prerequisite_image_text)
    }
    
    'STEP 6:Click OK'
    WebUI.click(findTestObject('Object Repository/Firmware_Update_Objects/button_Ok'))

    'STEP 7 :Verify that Firmware Updated Successfully. Please wait untill Tester has rebooted to power the controller message box appears'
    String firmware_successfully_text = WebUI.getText(findTestObject('Object Repository/Firmware_Update_Objects/strong_Firmware_Updated_Successfully'))

    if (firmware_successfully_text == 'Firmware updated successfully. Please wait until the tester has rebooted to power the controller') {
		WebUI.comment(' Fimware Update Successfully text is as Expected::  ' + firmware_successfully_text)
    }
    
    WebUI.click(findTestObject('Object Repository/Firmware_Update_Objects/button_Ok'))

    'STEP 8:Verify the Status Message changes to “Successfully Updated” appears for 2 sec and then disappears.'
    WebUI.delay(15)

    'STEP 10:Verify that the Tester Status shows IP address unreachable'
    String connection_sts_text = WebUI.getText(findTestObject('Object Repository/Firmware_Update_Objects/div_Tester_Status'))

    if (connection_sts_text.contains('unreachable')) {
        WebUI.comment('Tester Status is Expected::' + connection_sts_text)
    } else {
        WebUI.comment('Expected Test Status is not Present::' + connection_sts_text)

        assert false
    }
    
    'STEP 11:Verify that the Update Firmware Button gets disabled'
    CustomKeywords.'grl_usb_pd_c2_Testcase.ConnectionSetup_Page.getUpdateFirmwareDisableStatus'()

    'STEP 12:Wait for 2 minutes'
    WebUI.delay(120)

    'STEP 13:After Tester boots up Verify the GRL screen is visible in the Tester'

    'STEP 14:To Select Connect button'
    WebUI.click(findTestObject('Object Repository/Connection_Setup_Objects/button_Connect'))

    'Step 15:Verify that Update Firmware button gets disabled'
    CustomKeywords.'grl_usb_pd_c2_Testcase.ConnectionSetup_Page.getUpdateFirmwareDisableStatus'()

    'To check Connection Status after click the Connect button'
    def connection_sts = CustomKeywords.'grl_usb_pd_c2_Testcase.ConnectionSetup_Page.getConnectionStatus'()

    assert connection_sts
}
catch (Exception e) {
    WebUI.takeScreenshot()

    WebUI.comment('Please Update Firmware Manually,Not able to Update Fimware Using Automation Scripts')
} 

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()



