import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import java.awt.Toolkit as Toolkit
import java.awt.datatransfer.StringSelection as StringSelection
import java.awt.event.KeyEvent as KeyEvent
import internal.GlobalVariable as GlobalVariable
import java.awt.Robot as Robot
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
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

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

WebUI.delay(2)

'To find downloads folder directory and  vifdata xml file'
String home_path = System.getProperty('user.home')

def screenshot_path_sts = new File(((home_path + '/Downloads/') + 'screenshot') + '.png')

'To delete Existing screenshot.png  image'
try {
    boolean file_exists_sts = screenshot_path_sts.exists()

    if (file_exists_sts) {
        boolean imgdeleted = screenshot_path_sts.delete()

        WebUI.comment('Existing Screenshot png image Succesfully deleted without any issues::' + imgdeleted)
    } else {
        WebUI.comment('Existing Screenshot png image not present under Downloads folder' + '\n')
    }
}
catch (NoSuchFileException e) {
    WebUI.comment(('screenshot not present:: ' + e) + '\n')
} 

'Click on Result panel'
WebUI.click(findTestObject('Object Repository/Result/ScreenShot/Screen/div_Results'))

WebUI.delay(GlobalVariable.short_delay)

'Click Screenshot button'
WebUI.click(findTestObject('Object Repository/Result/ScreenShot/Screen/button_Start Execution_btn_screenshot'))

WebUI.delay(30)

'Verify Screenshot.png file gets save in default download local'
boolean img_exists_sts = screenshot_path_sts.exists()

println(img_exists_sts)
if (img_exists_sts) {
    WebUI.comment('Screenshot image is present in Downloads folder' + '\n')
} else {
    WebUI.comment('Screenshot image is not downloaded properly' + '\n')

}

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()


