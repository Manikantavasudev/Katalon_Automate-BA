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
import internal.GlobalVariable
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
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import groovy.transform.CompileStatic
import org.openqa.selenium.WebElement
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import org.openqa.selenium.support.ui.Select
import java.nio.file.*;
import org.openqa.selenium.support.Color;
import java.time.*
import java.text.SimpleDateFormat as SimpleDateFormat
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor

public class Five_Port {


	@CompileStatic
	@Keyword
	def getTesterConnectionSts () {
		'To navigate Connection Setup page'
		WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Connection_Setup'))

		int iteration_count = 0
		int iteration_limit = 20
		def tester_status
		while (iteration_count < iteration_limit) {
			'To Read Connection btn status '
			tester_status = WebUI.getText(findTestObject('Object Repository/Connection_Setup_Objects/b_Connection_Status'))

			if (tester_status.contains('Connected')) {
				WebUI.comment('Tester Connection Status :: ' + tester_status)
				break
			}
			WebUI.delay(5)
			iteration_count = (iteration_count + 1)
		}
	}

	@CompileStatic
	@Keyword
	def getConnectionStsWithClickbtn () {
		'To navigate Connection Setup page'
		WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Connection_Setup'))

		WebUI.delay(5)

		'To click connect button'
		WebUI.click(findTestObject('Object Repository/Connection_Setup_Objects/button_Connect'))

		int iteration_count = 0
		int iteration_limit = 20
		def tester_status
		while (iteration_count < iteration_limit) {
			'To Read Connection btn status '
			tester_status = WebUI.getText(findTestObject('Object Repository/Connection_Setup_Objects/b_Connection_Status'))
			println(tester_status)
			if (tester_status.contains('Connected')) {
				WebUI.comment('Tester Connection Status :: ' + tester_status)
				break
			}
			WebUI.delay(5)
			iteration_count = (iteration_count + 1)
		}

		assert tester_status == 'Connected'
	}


	@CompileStatic
	@Keyword
	def setDutType (String dut_type_locator) {

		WebDriver driver = DriverFactory.getWebDriver()

		'To select dut dropdown icon'
		WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_1_dut_dropdown_icon'))

		WebUI.delay(5)

		'To configure dut type'
		driver.findElement(By.linkText(dut_type_locator)).click()
	}


	@CompileStatic
	@Keyword
	def clickExpandTestList () {

		'To verify checkbox unselected state'
		def expand_testlist_checkbox_sts

		try {
			'To check expand list checkbox status'
			expand_testlist_checkbox_sts = WebUI.verifyElementNotChecked(findTestObject('Object Repository/Test_Config_Panel/Expand_Test_List'),
					5)

			if (expand_testlist_checkbox_sts) {
				WebUI.comment('expand_testlist_checkbox_sts:: ' + expand_testlist_checkbox_sts+'\n')

				WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Expand_Test_List'))

				WebUI.delay(5)
			}
		}
		catch (Exception e) {
			WebUI.delay(5)
		}
	}


	//	@CompileStatic
	//	@Keyword
	//	def readTestExecutionSts() {
	//		def run_status
	//		try {
	//			WebUI.delay(10)
	//			WebDriver driver = DriverFactory.getWebDriver()
	//			run_status = true
	//			int time = 0
	//			int popuptime = 0
	//
	//			while (run_status) {
	//				'To check Unlive button status'
	//				def sts = driver.findElement(By.xpath('//button/img[@src=\'../../images/chartIcons/PNG/Live.png\']'))
	//				if (sts) {
	//					try {
	//						WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))
	//						WebUI.delay(5)
	//					}
	//					catch(Exception e) {
	//						println("Result tab is not clickable")
	//						try {
	//							WebUI.click(findTestObject('Object Repository/Press_Ok_Popup/ok_contains'))
	//							WebUI.delay(4)
	//							println("try:clicked popup ok")
	//							WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))
	//							println("try:clicked Result tab")
	//						}
	//						catch (Exception A) {
	//							WebUI.refresh()
	//							WebUI.delay(8)
	//							try {
	//								WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))
	//								println("Catch:try: result tab i s clickable")
	//							}
	//							catch(Exception z) {
	//								WebUI.refresh()
	//								WebUI.delay(8)
	//								WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))
	//								println("Catch:Catch: result tab i s clickable")
	//							}
	//						}
	//					}
	//					WebUI.delay(12)
	//					run_status = true
	//					if(time%20==0) {
	//						//WebUI.deleteAllCookies()
	//					}
	//					'to refresh page for every 1 hour to avoid memory issue for 20 min= time(240) '
	//					if(time == 150) {
	//						println(getTimestampValue())
	//						WebUI.refresh()
	//						WebUI.delay(8)
	//						try {
	//							time = 0
	//							WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))
	//							println("try:time:150: result tab is clickable")
	//						}
	//						catch(Exception x) {
	//							WebUI.delay(2)
	//							WebUI.click(findTestObject('Object Repository/Press_Ok_Popup/ok_contains'))
	//							println("try :time150: clicked popup ok")
	//							WebUI.delay(4)
	//							WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))
	//							println("try:time150: click result tab")
	//						}
	//					}
	//					time++
	//				}
	//				else {
	//					run_status= false
	//				}
	//			}
	//		}
	//		catch (Exception e) {
	//			run_status = false
	//		}
	//	}


	@CompileStatic
	@Keyword
	def readTestExecutionSts() {
		def run_status
		try {
			WebUI.delay(10)
			WebDriver driver = DriverFactory.getWebDriver()
			run_status = true
			int time = 0

			while (run_status)
			{
				String got
				'To check Unlive button status'
				def sts = driver.findElement(By.xpath('//button/img[@src=\'../../images/chartIcons/PNG/Live.png\']'))
				if (sts)
				{
					WebUI.delay(900)
					try
					{
						WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))
					}
					catch(Exception e)
					{
						println("Result tab is not clickable")
						try
						{
							WebUI.click(findTestObject('Object Repository/Press_Ok_Popup/ok_contains'))
							WebUI.delay(4)
							println("try:clicked popup ok")
							WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))
							println("try:clicked Result tab")
						}
						catch (Exception A)
						{
							WebUI.refresh()
							WebUI.delay(8)
							try {
								WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))
								println("Catch:try: result tab i s clickable")
							}
							catch(Exception z) {
								WebUI.refresh()
								WebUI.delay(8)
								WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))
								println("Catch:Catch: result tab i s clickable")
							}
						}
					}
					time++;
					if(time == 4)
					{
						try
						{
							got= WebUI.getAttribute((findTestObject('Object Repository/Press_Ok_Popup/CurrentTestcase')), "title")
							if(got.contains("TD.4") || got =="TEST.PD.PS.SRC.3 Initial Source PDO Transition"  || got =="TEST.PD.PS.SNK.3 Multiple Request Load Test Post PRSwap")
							{
								WebUI.delay(700)
								println(got)
							}

						}
						catch(Exception z1)
						{
							println("exception in try : finding which testcase is executing")
							WebUI.delay(600)
						}


						WebUI.refresh()
						WebUI.delay(8)
						WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))
						time= 0
						WebUI.deleteAllCookies()


					}
				}
			}
		}
		catch (Exception e) {
			run_status = false
		}
	}



	@CompileStatic
	@Keyword
	def enterPopupTimer(String timer_popup_val) {


		'To check timeout popup checkbox is Enabled or not'
		try {
			def timeout_popup_sts = WebUI.verifyElementChecked(findTestObject('Object Repository/Test_Config_Panel/Test_Execution/input_Timeout_Popup_Checkbox'),
					30)

			if (timeout_popup_sts) {
				WebUI.clearText(findTestObject('Object Repository/Test_Config_Panel/Test_Execution/input_Popup_Timer'))

				WebUI.sendKeys(findTestObject('Object Repository/Test_Config_Panel/Test_Execution/input_Popup_Timer'), timer_popup_val)
			}
		}
		catch (Exception e) {
			WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Test_Execution/input_Timeout_Popup_Checkbox'))

			WebUI.clearText(findTestObject('Object Repository/Test_Config_Panel/Test_Execution/input_Popup_Timer'))

			WebUI.sendKeys(findTestObject('Object Repository/Test_Config_Panel/Test_Execution/input_Popup_Timer'), timer_popup_val)
		}
	}

	@CompileStatic
	@Keyword
	def portConnectBtnSts() {
		'To configure COM Port'
		'To click connect button'
		WebUI.click(findTestObject('Object Repository/Five_Port_Objects/Options/button_Connect'))

		int iteration_count = 0
		int iteration_limit = 8
		def expected_port_sts = 'pass'
		def port_connection_btn_sts

		while (iteration_count < iteration_limit) {

			port_connection_btn_sts = WebUI.getAttribute(findTestObject('Object Repository/Five_Port_Objects/Options/img_Connect_check_status_icon'),
					'src')
			println("port status")
			println(port_connection_btn_sts)

			if (port_connection_btn_sts.contains(expected_port_sts)) {
				WebUI.comment('COM Port Connection Status :: ' + expected_port_sts)
				break
			}
			WebUI.click(findTestObject('Object Repository/Five_Port_Objects/Options/button_Connect'))
			WebUI.delay(8)
			iteration_count = (iteration_count + 1)
		}
		'execution will fail if connection status is not enable'
		assert port_connection_btn_sts.contains(expected_port_sts)
	}


	@CompileStatic
	@Keyword
	def setProjectName(String project_name) {
		'To configure project name based on port'
		WebUI.clearText(findTestObject('Object Repository/Product_Capability_Objects/Project_name_'))

		//WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/button_Save'))

		WebUI.setText(findTestObject('Product_Capability_Objects/input_Project Name'), project_name)

		WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/button_Save'))

		WebUI.delay(5)
	}

	@CompileStatic
	@Keyword
	def configureSelectPort(String port1_val) {

		'To configure port based on user input'
		WebUI.clearText(findTestObject('Object Repository/Five_Port_Objects/Options/input_Select_Port'))

		WebUI.setText(findTestObject('Object Repository/Five_Port_Objects/Options/input_Select_Port'), port1_val)
	}


	@CompileStatic
	@Keyword
	def enablePort(String port_num_locator,TestObject enable_port_locator) {
		WebDriver driver = DriverFactory.getWebDriver()

		if(port_num_locator=='1'||port_num_locator=='2') {
			'To enable port and toggle button'
			WebElement port_type_element = driver.findElement(By.xpath('(//nav/a[@data-rb-event-key="'+port_num_locator+'"])[2]'))

			JavascriptExecutor enable_executor = ((driver) as JavascriptExecutor)

			enable_executor.executeScript('arguments[0].click();', port_type_element)

			WebUI.delay(10)

			WebUI.click(enable_port_locator)

			WebUI.delay(5)
		}
		else {
			'To enable port and toggle button'
			WebElement port_type_element = driver.findElement(By.xpath('(//nav/a[@data-rb-event-key="'+port_num_locator+'"])[1]'))

			JavascriptExecutor enable_executor = ((driver) as JavascriptExecutor)

			enable_executor.executeScript('arguments[0].click();', port_type_element)

			WebUI.delay(10)

			WebUI.click(enable_port_locator)

			WebUI.delay(5)
		}
	}


	@CompileStatic
	@Keyword
	def disablePort(String port_num_locator,TestObject enable_port_locator) {
		WebDriver driver = DriverFactory.getWebDriver()

		'To navigate options page'
		WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Options'))

		if(port_num_locator=='1'||port_num_locator=='2') {
			'To enable port and toggle button'
			WebElement port_type_element = driver.findElement(By.xpath('(//nav/a[@data-rb-event-key="'+port_num_locator+'"])[2]'))

			JavascriptExecutor enable_executor = ((driver) as JavascriptExecutor)

			enable_executor.executeScript('arguments[0].click();', port_type_element)

			WebUI.delay(5)

			WebUI.click(enable_port_locator)

			WebUI.delay(5)
		}
		else {
			'To enable port and toggle button'
			WebElement port_type_element = driver.findElement(By.xpath('(//nav/a[@data-rb-event-key="'+port_num_locator+'"])[1]'))

			JavascriptExecutor enable_executor = ((driver) as JavascriptExecutor)

			enable_executor.executeScript('arguments[0].click();', port_type_element)

			WebUI.delay(5)

			WebUI.click(enable_port_locator)

			WebUI.delay(5)
		}
	}

	@CompileStatic
	@Keyword
	def getTimestampValue() {

		'To merge timestamp and summary folder name '
		def date = new Date()

		def y_m_d = new SimpleDateFormat('yyyy_MM_dd-HH_mm_ss')

		def report_time_stamp = y_m_d.format(date)

		return report_time_stamp
	}


	@CompileStatic
	@Keyword
	def readTestExecutionStsForPopupDisable() {
		def run_status
		try {
			WebUI.delay(10)
			WebDriver driver = DriverFactory.getWebDriver()
			run_status = true
			int time = 0

			while (run_status)
			{
				String got
				'To check Unlive button status'
				def sts = driver.findElement(By.xpath('//button/img[@src=\'../../images/chartIcons/PNG/Live.png\']'))
				if (sts)
				{
					WebUI.delay(900)
					try
					{
						WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))

					}
					catch(Exception e)
					{
						println("Result tab is not clickable")
						try
						{
							WebUI.click(findTestObject('Object Repository/Press_Ok_Popup/ok_contains'))
							WebUI.delay(4)
							WebUI.
									println("try:clicked popup ok")
							WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))
							println("try:clicked Result tab")
						}
						catch (Exception A)
						{
							WebUI.refresh()
							WebUI.delay(8)
							try {
								WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))
								println("Catch:try: result tab i s clickable")
							}
							catch(Exception z)
							{


								WebUI.delay(8)
								WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))
								println("Catch:Catch: result tab i s clickable")
							}
						}
					}
					time++;
					if(time == 4)
					{
						try
						{
							got= WebUI.getAttribute((findTestObject('Object Repository/Press_Ok_Popup/CurrentTestcase')), "title")
							if(got.contains("TD.4") || got =="TEST.PD.PS.SRC.3 Initial Source PDO Transition"  || got =="TEST.PD.PS.SNK.3 Multiple Request Load Test Post PRSwap")
							{
								WebUI.delay(700)
								println(got)
							}

						}
						catch(Exception z1)
						{
							println("exception in try : finding which testcase is executing")
							WebUI.delay(600)
						}

						//got= WebUI.getAttribute((findTestObject('Object Repository/Press_Ok_Popup/CurrentTestcase')), "title")



						WebUI.refresh()
						WebUI.delay(8)
						WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))
						time= 0
						WebUI.deleteAllCookies()


					}
				}
			}
		}
		catch (Exception e) {
			run_status = false
		}
	}




	//	@CompileStatic
	//	@Keyword
	//	def readTestExecutionStsForPopupDisable() {
	//		def run_status
	//		try {
	//			WebUI.delay(10)
	//			WebDriver driver = DriverFactory.getWebDriver()
	//			run_status = true
	//			int time = 0
	//			int popuptime = 0
	//
	//			while (run_status) {
	//				'To check Unlive button status'
	//				def sts = driver.findElement(By.xpath('//button/img[@src=\'../../images/chartIcons/PNG/Live.png\']'))
	//				if (sts) {
	//					try {
	//						WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))
	//						WebUI.delay(5)
	//					}
	//					catch(Exception e) {
	//						println("Result tab is not clickable")
	//						try {
	//							WebUI.click(findTestObject('Object Repository/Press_Ok_Popup/ok_contains'))
	//							WebUI.delay(4)
	//							println("try : clicked popup ok")
	//							WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))
	//							println("try: click result tab")
	//						}
	//						catch (Exception A) {
	//							WebUI.refresh()
	//							WebUI.delay(8)
	//							try {
	//								WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))
	//								println("Catch:try: result tab i s clickable")
	//							}
	//							catch(Exception z) {
	//								WebUI.refresh()
	//								WebUI.delay(8)
	//								WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))
	//								println("Catch:Catch: result tab i s clickable")
	//							}
	//						}
	//					}
	//					WebUI.delay(12)
	//					run_status = true
	//					if(time%20==0) {
	//						//WebUI.deleteAllCookies()
	//					}
	//					'to refresh page for every 1 hour to avoid memory issue for 20 min= time(260) '
	//					if(time== 150) {
	//						println(getTimestampValue())
	//						WebUI.refresh()
	//						WebUI.delay(8)
	//						try {
	//							time = 0
	//							WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))
	//							println("try:time:150: result tab is clickable")
	//						}
	//						catch(Exception x) {
	//							WebUI.delay(2)
	//							WebUI.click(findTestObject('Object Repository/Press_Ok_Popup/ok_contains'))
	//							println("try :time150: clicked popup ok")
	//							WebUI.delay(4)
	//							WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Results'))
	//							println("try:time150: click result tab")
	//						}
	//					}
	//					time++
	//				}
	//				else {
	//					run_status= false
	//				}
	//			}
	//		}
	//		catch (Exception e) {
	//			run_status = false
	//		}
	//	}
}
