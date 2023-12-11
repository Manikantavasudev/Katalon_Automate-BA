package grl_usb_pd_c2_Testcase

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import groovy.transform.CompileStatic
import org.openqa.selenium.WebElement

import internal.GlobalVariable

public class Browser_SetUp_Teardown {


	@CompileStatic
	@Keyword
	//This method will initialize Browser

	def static browserInitialization() {
		WebUI.openBrowser('')

		WebUI.navigateToUrl('http://localhost:5001/')

		WebUI.maximizeWindow()
	}

	@CompileStatic
	@Keyword
	//This method will close Browser

	def static browserTeardown() {
		WebUI.closeBrowser()
	}

}
