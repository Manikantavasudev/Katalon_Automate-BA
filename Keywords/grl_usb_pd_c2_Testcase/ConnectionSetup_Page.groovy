package grl_usb_pd_c2_Testcase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import internal.GlobalVariable
import org.openqa.selenium.By
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import groovy.transform.CompileStatic
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import org.openqa.selenium.support.ui.Select
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.KeyEvent
import java.awt.Robot
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.support.Color;



class ConnectionSetup_Page {

	@CompileStatic
	@Keyword
	//This method will list out Available dynamic dropdown ip address

	def static scanNetworkDropdown() {
		WebDriver driver = DriverFactory.getWebDriver()
		List all_dropdown_ip_list=[]
		'To find dropdown ip list & ip count'
		List<WebElement> drpdown_ip_elements = driver.findElements(By.xpath("//li/option"))
		WebUI.comment('The size of ip elements is ::'+drpdown_ip_elements.size())
		if(drpdown_ip_elements.size()>0) {
			for (WebElement dropdwn_ip_element : drpdown_ip_elements) {
				String dropdown_ip_address=dropdwn_ip_element.getAttribute('value')
				all_dropdown_ip_list.add(dropdown_ip_address)
			}
			WebUI.comment('Dropdown ip list ::'+all_dropdown_ip_list)
		}

		else {
			WebUI.comment('Dropdown list is not in Visible state,could not able to find Locators')
			WebUI.takeScreenshot()
			assert false

		}
	}


	@CompileStatic
	@Keyword
	//This method will list out dropdown ip  with pass icon

	def static getDropdownIpWithPassIcon() {
		WebDriver driver = DriverFactory.getWebDriver()

		List pass_icon_dropdown_ip_list=[]
		List pass_fail_icon_img_src=[]
		int pass_icon_count=0
		WebUI.delay(40)
		'To find dropdown ip list & ip count'

		List<WebElement> drpdown_ip_list_count = driver.findElements(By.xpath("//ul[@role='listbox']/li"))

		int drpdown_ip_size=drpdown_ip_list_count.size()

		WebUI.comment('The size of ip elements is ::'+drpdown_ip_size)
		if(drpdown_ip_size>0) {

			String expected_icon="pass"

			for(int row=1;row<=drpdown_ip_size;row++) {

				WebElement pass_fail_icon_list=driver.findElement(By.xpath("//ul[@role='listbox']/li["+row+"]/img"))
				'To find image source value'
				String pass_fail_icon_src=pass_fail_icon_list.getAttribute('src')

				pass_fail_icon_img_src.add(pass_fail_icon_src)

				'To find pass icon ip list'
				if(pass_fail_icon_src.toLowerCase().contains(expected_icon.toLowerCase()))
				{
					WebElement pass_icon_ip_element=driver.findElement(By.xpath("//ul[@role='listbox']/li["+row+"]/option"))
					String ip_address_with_pass_sts = pass_icon_ip_element.getAttribute('value')
					'To append list using pass icon ip address'
					pass_icon_dropdown_ip_list.add(ip_address_with_pass_sts)
					'To check count of pass icon ip'
					pass_icon_count++
				}
			}
			WebUI.comment('Ip with Pass icon list ::'+pass_icon_dropdown_ip_list+' pass icon  ip count:: '+pass_icon_count)
			WebUI.comment('Ip with Image source value ::'+pass_fail_icon_img_src)
			WebUI.takeScreenshot()
			return pass_icon_dropdown_ip_list

		}
		else {
			WebUI.comment('Dropdown list is not in Visible state,could not able to find Locators')
			WebUI.takeScreenshot()
			assert false
			return null

		}
	}

	@CompileStatic
	@Keyword
	//This method will Verify static ip address

	def static verifyStaticIp(List pass_ip_address,String cs_c2_static_ip_address) {

		'To compare User provided static ip with pass icon ip list'
		if(pass_ip_address.contains(cs_c2_static_ip_address))
		{
			WebDriver driver = DriverFactory.getWebDriver()
			'To click user provided static ip '
			WebElement static_ip = driver.findElement(By.xpath('//*[contains(text(), "'+ cs_c2_static_ip_address +'")]'))

			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", static_ip);
			WebUI.delay(20)
			WebUI.comment('Dropdown pass icon ip address is matching with User Provided Static Ip')

		}
		else {
			WebUI.comment('Dropdown pass icon ip address is Not matching with User Provided Static Ip')
			WebUI.takeScreenshot()
			assert false

		}
	}

	@CompileStatic
	@Keyword
	//This method will VerifyDynamic ip address

	def static verifyDynamicIp(List pass_ip_address,String cs_c2_dynamic_ip_address) {

		'To compare User provided dynamic ip with pass icon ip list'
		if(pass_ip_address.contains(cs_c2_dynamic_ip_address)) {
			WebDriver driver = DriverFactory.getWebDriver()
			'To click user provided dynmaic ip '
			WebElement dynamic_ip = driver.findElement(By.xpath('//*[contains(text(), "'+ cs_c2_dynamic_ip_address +'")]'))

			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", dynamic_ip);
			WebUI.delay(20)
			WebUI.comment('Dropdown pass icon ip address is matching with User Provided Dynamic Ip')

		}
		else {
			WebUI.comment('Dropdown pass icon ip address is Not matching with User Provided Dynamic Ip')
			WebUI.takeScreenshot()
			assert false

		}
	}
	@CompileStatic
	@Keyword
	//This method will select pass icon ip without click connect button

	def static getValidCaseListSelection(List pass_ip_address,String cs_c2_valid_ip_address) {

		'To compare User provided valid ip with pass icon ip list'
		if(pass_ip_address.contains(cs_c2_valid_ip_address)) {
			WebDriver driver = DriverFactory.getWebDriver()
			'To click pass icon ip'
			WebElement pass_ip_element = driver.findElement(By.xpath('//*[contains(text(), "'+ cs_c2_valid_ip_address +'")]'))
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", pass_ip_element);
			WebUI.delay(20)
			WebUI.comment('Selected Dropdown ip without click connect button')

		}
		else {
			WebUI.comment('Unable to select pass icon ip Address')
			WebUI.takeScreenshot()
			assert false

		}
	}

	@CompileStatic
	@Keyword
	//To Find Tester status by reading the Tester status textbox value
	def static getConnectionStatus() {

		int iteration_count = 0
		int iteration_limit = 20
		def expected_text_clr="green"
		def expected_text_clr_hex="#0aa205"

		'If Tester Status is connected Method return true and it will verify Tester Status Text color'
		try {
			while (iteration_count < iteration_limit) {
				def Tester_Status = WebUI.getText(findTestObject('Object Repository/Connection_Setup_Objects/b_Connection_Status'))
				if (Tester_Status == 'Connected') {
					WebUI.comment('Tester Status is ::'+Tester_Status)
					def tester_sts_color = WebUI.getCSSValue(findTestObject('Object Repository/Connection_Setup_Objects/b_Connection_Status'), 'color')
					'To convert rgb format to hex value'
					String hexacolor = Color.fromString(tester_sts_color).asHex();
					if(hexacolor==expected_text_clr_hex) {
						WebUI.comment('Tester Status Text color is ::'+expected_text_clr)
					}
					else {
						WebUI.comment('Tester Status Text colr is not Matching::'+hexacolor)
					}
					break
				}
				WebUI.delay(2)
				iteration_count = (iteration_count + 1)
			}
			return true
		}

		catch (Exception e) {
			WebUI.comment('Tester Ip Address is not Connected with c2 application')
			WebUI.takeScreenshot()
			return false
		}
	}

	@CompileStatic
	@Keyword
	//To Find Tester Details and validate the Information by using json data
	def static getTesterDetails(String exp_serial_num,String exp_firmware_ver,String exp_tester_ip_address_info,String exp_calibration_status)
	{


		try {
			'To read Serial Number value and compare that value with user provided json data'

			def serial_number = WebUI.getText(findTestObject('Object Repository/Tester_Details/Page_GRL - USB PD C2/b_Serial_Number'))
			Boolean serial_number_sts = serial_number==exp_serial_num
			WebUI.comment('Serial_Number_Status::'+ serial_number_sts )

			'To read Firmware Version Number value and compare that value with user provided json data'

			def firmware_version = WebUI.getText(findTestObject('Object Repository/Tester_Details/Page_GRL - USB PD C2/b_Firmware_Version'))
			Boolean firmware_version_sts = firmware_version==exp_firmware_ver
			WebUI.comment('Firmware_Version_Status::'+ firmware_version_sts )

			'To read Tester ip address value and compare that value with user provided json data'

			def tester_ip_address_info = WebUI.getText(findTestObject('Object Repository/Tester_Details/Page_GRL - USB PD C2/b_Tester_Ip_Address_Info'))
			Boolean tester_ip_address_info_sts = tester_ip_address_info==exp_tester_ip_address_info
			WebUI.comment('tester_Ip_Address_info_Status::'+ tester_ip_address_info_sts )

			'To calibration status and compare that value with user provided json data'

			def cable_calibration_status = WebUI.getText(findTestObject('Object Repository/Tester_Details/Page_GRL - USB PD C2/b_Test_Cable_Calibration_Status'))
			Boolean calibration_sts = cable_calibration_status==exp_calibration_status
			WebUI.comment('Calibration_Status::'+calibration_sts )

			if(serial_number_sts && firmware_version_sts && tester_ip_address_info_sts && calibration_sts)
			{
				WebUI.comment('Tester Details Values are Matching' )
			}
			else {
				WebUI.comment('Tester Details Values are Not Matching')
				//assert false
			}
		}

		catch (Exception e) {
			WebUI.comment('Tester Details data is not getting')
			WebUI.takeScreenshot()
		}
	}

	@CompileStatic
	@Keyword
	def static validateInvalidIp(List invalid_ip_list) {

		'To input the invalid ip through list '
		for(String invalid_ip:invalid_ip_list) {
			'driver object initialization'
			WebDriver driver = DriverFactory.getWebDriver()

			'clear the existing ip'
			WebUI.click(findTestObject('Object Repository/Connection_Setup_Objects/i_Clear_Ip'))

			'input invalid ip'
			WebUI.sendKeys(findTestObject('Object Repository/Connection_Setup_Objects/input_C2_Ip_Address_Textbox'), invalid_ip)

			'click connect button'
			WebUI.click(findTestObject('Object Repository/Connection_Setup_Objects/button_Connect'))

			try {
				WebUI.delay(8)
				'To get the Tester Status'
				def Tester_Status = WebUI.getText(findTestObject('Object Repository/Connection_Setup_Objects/b_Connection_Status'))

				if (Tester_Status.contains('Invalid IP address format :')) {
					WebUI.comment('Tester Status::'+Tester_Status)
				}
				else {
					WebUI.comment('Tester Status text is not as expected::'+Tester_Status)
				}
			}
			catch (Exception e) {
				WebUI.takeScreenshot()
			}
		}
	}

	@CompileStatic
	@Keyword
	def static getInvalidIpWithCorrectIpStatus() {

		'driver object initialization'
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.delay(10)
		def Tester_Status = WebUI.getText(findTestObject('Object Repository/Connection_Setup_Objects/b_Connection_Status'))
		WebUI.comment('Ip address Tester status is ::'+Tester_Status)
		if(Tester_Status.contains("unreachable")) {
			'To click dropdown icon'
			WebUI.click(findTestObject('Object Repository/Connection_Setup_Objects/i_dropdown_icon'))
			'Verify dropdown ip address with fail icon'
			WebElement fail_icon_ip = driver.findElement(By.xpath("//ul[@role='listbox']/li/img"))
			'To find image source value'
			String fail_icon_src=fail_icon_ip.getAttribute('src')
			if(fail_icon_src.contains("fail")) {
				WebUI.comment('dropdown ip address is listed with fail icon without tester::'+fail_icon_src)
			}
			else {
				WebUI.comment('either ip address image source value is not getting or ip is denoted with pass icon')
				assert false
			}
		}
		else {
			WebUI.comment('Tester Status is wrong::'+Tester_Status)
		}

	}
	@CompileStatic
	@Keyword
	def static getUpdateFirmwareDisableStatus() {

		try {
			'To get Firmware Update disable Status'
			def firmware_btn_disable_sts = WebUI.getAttribute(findTestObject('Object Repository/Connection_Setup_Objects/button_Update Firmware'),'disabled')

			if (firmware_btn_disable_sts) {
				WebUI.comment('Update firmware button disabled ')
			} else {
				WebUI.comment('Update firmware is enabled')
				assert false
			}
		}
		catch (Exception e) {
			WebUI.takeScreenshot()
			assert false
		}
	}

	@CompileStatic
	@Keyword
	def static getSoftwareVersion() {
		WebUI.executeJavaScript('document.body.style.zoom=\'90%\'', null)
		'To get c2 Software version '
		def mainbar_app_name = WebUI.getText(findTestObject('Object Repository/Browser_Main_View_Objects/p_Application_Version_Mainbar'))
		//println(mainbar_app_name)
		String app_ver = mainbar_app_name.substring(mainbar_app_name.indexOf('(') +1 , mainbar_app_name.indexOf(')'))
		println(app_ver)
		WebUI.executeJavaScript('document.body.style.zoom=\'100%\'', null)
		return app_ver
	}





















}