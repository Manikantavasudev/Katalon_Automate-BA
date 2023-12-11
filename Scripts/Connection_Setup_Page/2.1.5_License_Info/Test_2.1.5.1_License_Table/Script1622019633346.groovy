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
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.By as By
import org.openqa.selenium.support.Color as Color

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

'Tabele header list'
List header_data_list = []

'Module Name List'
List module_name_list = []

'Exp table header data list'
List exp_header_data_list = license_info_header_list

'Exp Module Name list'
List exp_module_name_list = license_module_name_list

'Exp Perm license Type background clr'
String exp_perm_license_clr = perm_license_clr

'Exp Expired license Type background clr'
String exp_expired_license_clr = expired_license_clr

WebDriver driver = DriverFactory.getWebDriver()

WebUI.delay(GlobalVariable.long_delay)

'To verify Tester Status'
def Tester_Status = WebUI.getText(findTestObject('Object Repository/Connection_Setup_Objects/b_Connection_Status'))

if (Tester_Status == 'Connected') {
    'STEP 1:Verify that there is a license Info Table present'
    def license_info_table = WebUI.verifyElementPresent(findTestObject('Object Repository/Connection_Setup_Objects/License_Info/strong_License Info'), 
        10)

    assert license_info_table == true

    'STEP 2: To Verify Module Name,License Type,License Period headers Present'
    List table_header = driver.findElements(By.xpath('//div[@class="scroll-license-info"]/div/table/thead/tr/th'))

    'To locate table data header'
    int header_size = table_header.size()

    for (int i = 1; i <= header_size; i++) {
        WebElement table_header_webele = driver.findElement(By.xpath(('//div[@class=\'scroll-license-info\']/div/table/thead/tr/th[' +i) + ']'))

        String table_header_val = table_header_webele.getText()

        header_data_list.add(table_header_val)
    }
    
    WebUI.comment('License Header Name List::  ' + header_data_list)

    assert exp_header_data_list == header_data_list

    'STEP 3:To Verify that the License table Module Name has the following entries'
    List license_module_name_list = driver.findElements(By.xpath('//div[@class=\'scroll-license-info\']/div/table/tbody/tr/td[1]/div/div'))

    'To locate Module Name list'
    int module_name_size = license_module_name_list.size()

    for (int j = 1; j <= module_name_size; j++) {
        WebElement module_name_webele = driver.findElement(By.xpath(('//div[@class=\'scroll-license-info\']/div/table/tbody/tr/td[1]/div[' +j) + ']/div'))

        String module_name_val = module_name_webele.getText()

        module_name_list.add(module_name_val)
    }
    
    WebUI.comment('License Module Name List::  ' + module_name_list)

    assert exp_module_name_list == module_name_list

    'STEP 3:To Verify that the License Type has the following entries'
    List license_type_list = driver.findElements(By.xpath('//div[@class=\'scroll-license-info\']/div/table/tbody/tr/td[2]/div/div'))

    'To locate License Type list'
    int license_type_size = license_type_list.size()

    for (int k = 1; k <= license_type_size; k++) {
        WebElement license_type_webele = driver.findElement(By.xpath(('//div[@class=\'scroll-license-info\']/div/table/tbody/tr/td[2]/div[' +k) + ']/div'))

        String license_type_val = license_type_webele.getText()

        'To verify background colour based on license Type'
        if (license_type_val == 'PERM') {
            String license_type_bg_clr = license_type_webele.getCssValue('background-color')

            String hex_clr_val = Color.fromString(license_type_bg_clr).asHex()
			
            'To verify PERM license type colour'			
			assert exp_perm_license_clr == hex_clr_val
			
            WebUI.comment('License Type is::  ' + license_type_val+'   ******  '+'License Type colour is::  '+ 'Green')
        } else {
            String license_type_bg_clr = license_type_webele.getCssValue('background-color')

            String hex_clr_val = Color.fromString(license_type_bg_clr).asHex()
            
			'To verify PERM license type colour'
			assert exp_expired_license_clr == hex_clr_val
			
            WebUI.comment('License Type is::  ' + license_type_val+'   ******  '+'License Type colour is::  '+ 'Pink')
			
			WebUI.takeScreenshot()
        }
    }
} else {
    WebUI.comment('PC is not connected with C2')
	
	WebUI.takeScreenshot()
	
    assert false
}

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()

