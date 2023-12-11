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
import java.awt.Toolkit as Toolkit
import java.awt.datatransfer.StringSelection as StringSelection
import java.awt.event.KeyEvent as KeyEvent
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
import java.nio.file.*

'Browser Initialization'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserInitialization'()

WebUI.delay(GlobalVariable.long_delay)

'driver object initialization'
WebDriver driver = DriverFactory.getWebDriver()

'download folder path'

String exp_trace_file_name = 'GRL_Signal_File1.grltrace'

String home_path = System.getProperty('user.home')

def trace_file_path = new File(((home_path + '/Downloads/') + 'GRL_Signal_File1') + '.grltrace')

'delete Existing trace file in downloads folder'
try {
	boolean trace_file_exists_sts = trace_file_path.exists()
	
	if (trace_file_exists_sts) {
	  
		boolean filedeleted = trace_file_path.delete()
		
		WebUI.comment('Existing Trace file Succesfully deleted without any issues::' + filedeleted)
		
	} else {
		WebUI.comment('Trace file is not present under Downloads folder'+'\n')
	}
}
catch (NoSuchFileException e) {
	WebUI.comment('Trace File not present:: ' + e+'\n')
}

'To click Result page'
WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Results'))

WebUI.delay(GlobalVariable.short_delay)

WebUI.callTestCase(findTestCase('Load_Trace_File_RobotFramework'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.delay(20)

'STEP 1:Click Save Trace file button on the right panel'
WebUI.click(findTestObject('Object Repository/Result/Save_File/img_SaveTrace'))

'STEP 2&3:Verify Save File Dialog appears & Verify the file name is GRL_Signal_File.grltrace '
def existing_tracefile_name = WebUI.getAttribute(findTestObject('Object Repository/Result/Save_File/input_FileName'), 'value')

WebUI.comment('TraceFile name before editing:: ' + existing_tracefile_name)

WebUI.clearText(findTestObject('Object Repository/Result/Save_File/input_FileName'))

'STEP 4:Modify file name to Test1.grlTrace'
WebUI.setText(findTestObject('Object Repository/Result/Save_File/input_FileName'), exp_trace_file_name)

'STEP 5:Click OK'
WebUI.click(findTestObject('Object Repository/Result/Save_File/button_Ok'))

'STEP 6:Verify that the file gets save in the default path'
WebUI.delay(GlobalVariable.long_delay)

boolean trace_file_sts = trace_file_path.exists()

if(trace_file_sts) {
WebUI.comment('Downloaded TraceFile is present in downloads folder:: ' + trace_file_sts)

}

'To call common Popup reader'
WebUI.callTestCase(findTestCase('Common_Data_Reader/Popup_Handler/Common_Popup_Handler'), [:], FailureHandling.STOP_ON_FAILURE)

'Browser Teardown'
CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()



