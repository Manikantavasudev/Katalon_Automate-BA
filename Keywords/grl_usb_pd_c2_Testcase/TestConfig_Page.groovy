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

public class TestConfig_Page {

	@CompileStatic
	@Keyword
	def certificationDropdownSearchList (String search_key) {


		List certification_drpdwn_search_list=[]

		'To initialize driver object'
		WebDriver driver = DriverFactory.getWebDriver()

		'To clear existing search box text'
		WebUI.clearText(findTestObject('Object Repository/Test_Config_Panel/input_Search_Textbox'))

		'To enter user defined search key'
		WebUI.sendKeys(findTestObject('Object Repository/Test_Config_Panel/input_Search_Textbox'), search_key)
		WebUI.delay(5)

		'To get selected Test count value'
		String selected_test_count = driver.findElement(By.xpath("//div[@class='selected-test-count-div']/span/strong")).getText()
		WebUI.comment('Selected Test Count ::' + selected_test_count+'\n')

		'To get the list of testcases as per search box text'
		List<WebElement> dropdwn_certification_list = driver.findElements(By.xpath("//ul[@class='rc-tree-child-tree rc-tree-child-tree-open']/li"))

		try {
			if( dropdwn_certification_list.size()>0 ) {
				for (WebElement dropdwn_certification_text : dropdwn_certification_list) {
					'To get testcase list'
					String certification_testcase = dropdwn_certification_text.getText()
					'To ignore MOI testcase'
					if(!(certification_testcase.startsWith("Power Delivery 3.0 Tests")||certification_testcase.startsWith("PD2 Communication Engine Tests")||certification_testcase.startsWith("PD Merged Tests (Beta)")||certification_testcase.startsWith("PD2 Deterministic Tests")||certification_testcase.startsWith("USB-C Functional Tests")||certification_testcase.startsWith("BC1.2 DCP Sink Tests")||certification_testcase.startsWith("PDMER_PROT_TEST_SNK_CAP_PD2_PD3_Mode")||certification_testcase.startsWith("PDMER_PROT_TEST_SNK_CAP_PD3_Mode")||certification_testcase.startsWith("PDMER_PROT_VDM_SNK_CAP_PD2_PD3")||certification_testcase.startsWith("PDMER_PROT_PS_SNK_CAP_UUT_PD2_PD3")||certification_testcase.startsWith("DisplayPort Alternate Mode Tests"))) {
						certification_drpdwn_search_list.add(certification_testcase)
					}
				}
				WebUI.comment('Certification dropdown search list::'+certification_drpdwn_search_list+'\n')
				WebUI.comment('Certification dropdown search list size::'+certification_drpdwn_search_list.size()+'\n')

				'To verify testlist contains expected search text'
				for(String certification_drpdwn_test:certification_drpdwn_search_list) {
					Boolean search_list_sts=certification_drpdwn_test.contains(search_key)||certification_drpdwn_test.contains(search_key.toLowerCase())||certification_drpdwn_test.contains(search_key.toUpperCase())||certification_drpdwn_test.contains('Src')
					if(search_list_sts) {
						println("search_list_sts::"+search_list_sts)
					}
					else {
						WebUI.comment("certification_drpdwn_test_status::"+certification_drpdwn_test+'\n')
						assert false
					}
				}
				return selected_test_count
			}

			else {
				WebUI.comment('Certification dropdown search list is Empty'+'\n')
				assert false
			}
		}
		catch (Exception e) {
			WebUI.takeScreenshot()
			return null
		}
	}
	@CompileStatic
	@Keyword
	//'To check Certification Dropdown list '
	def certificationDropdownList () {
		List certification_dropdwn_list=[]
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Certification_drop_down'))
		WebUI.delay(5)
		List<WebElement> certification_drpdwn_list_elements = driver.findElements(By.xpath("//div[@aria-labelledby='tcCertificationComboBox']/a"))
		WebUI.comment('The size of elements is ::' +  certification_drpdwn_list_elements.size()+'\n')
		try {
			if( certification_drpdwn_list_elements.size()>0) {
				for (WebElement certification_drpdwn_element : certification_drpdwn_list_elements) {
					certification_dropdwn_list.add(certification_drpdwn_element.getText())
				}
				WebUI.comment('Certification dropdown list::'+certification_dropdwn_list+'\n')
				WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Certification_drop_down'))
				return certification_dropdwn_list
			}
			else {
				WebUI.comment('Certification Dropdown is not in visible state'+'\n')
				assert false
			}
		}
		catch (Exception e) {
			WebUI.takeScreenshot()
			return null
		}

	}

	@CompileStatic
	@Keyword
	//'To get Tooltip Info values'
	def getToolTipInfoValues (TestObject to, String tool_info_locator) {

		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.doubleClick(to)
		WebUI.delay(3)
		WebElement tooltip_menu=driver.findElement(By.xpath("//div[text()='"+tool_info_locator+"']"))
		WebUI.comment('ToolTip Info Text::'+tooltip_menu.getText()+'\n')
		return tooltip_menu.getText()
	}
	@CompileStatic
	@Keyword
	//'To Check Expand Test List Checkbox  & C2 Test Cases checkbox status'
	def getCheckBoxUnSelectedStatus() {

		WebDriver driver = DriverFactory.getWebDriver()
		'To check expand list checkbox status'
		def expand_testlist_checkbox_sts = WebUI.verifyElementNotChecked(findTestObject('Object Repository/Test_Config_Panel/Expand_Test_List'), 5)
		'To check c2 Testcases checkbox status'
		def c2_testcases_checkbox_sts = WebUI.verifyElementNotChecked(findTestObject('Object Repository/Test_Config_Panel/Testconfig_icons/span_C2_Testcases_Checkbox'),5)

		try {
			if(expand_testlist_checkbox_sts && c2_testcases_checkbox_sts) {

				WebUI.comment('expand_testlist_checkbox_sts::'+expand_testlist_checkbox_sts+'***********'+'c2_testcases_checkbox_sts::'+c2_testcases_checkbox_sts+'\n')
				return true
			}
			else {
				WebUI.comment('Checkbox is selected'+'\n')
				assert false
			}
		}
		catch (Exception e) {
			WebUI.takeScreenshot()
			return null
		}
	}
	@CompileStatic
	@Keyword
	//'To click Certificarion Dropdown'
	def selectCertificationDropdown (TestObject dropdown_icon,TestObject certification_locator) {

		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.click(dropdown_icon)
		WebUI.click(certification_locator)

	}
	@CompileStatic
	@Keyword
	//'To select indidual Testcase '
	def selectIndidualTestCase (String moi_name,String testcase_name) {

		WebDriver driver = DriverFactory.getWebDriver()
		'To click Expand Test List Checkbox'
		WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Expand_Test_List'))
		'To check Expected MOI is Available or not'
		def certification_name_sts=driver.findElement(By.xpath("//span[text()='"+moi_name+"']")).isDisplayed()
		if(certification_name_sts)
		{
			'To click Indidual Testcase'
			driver.findElement(By.xpath("//span[text()='"+testcase_name+"']")).click()
			WebUI.delay(5)
			return true
		}
		else {
			WebUI.comment('')
			assert false
		}

	}

	@CompileStatic
	@Keyword
	//'To get MOI Configuration dropdown list '
	def getMoiConfigDropdownList (TestObject dropdown_icon,String dropdown_list) {

		WebDriver driver = DriverFactory.getWebDriver()
		List moi_dropdown_list_values=[]
		'To click dropdown icon'
		WebUI.click(dropdown_icon)
		'To get dropdown list'
		List<WebElement> moi_drpdwn_list_elements = driver.findElements(By.xpath(dropdown_list))
		WebUI.comment('The size of elements is ::' +  moi_drpdwn_list_elements.size()+'\n')
		try {
			if( moi_drpdwn_list_elements.size()>0) {
				for (WebElement  moi_drpdwn_list_element : moi_drpdwn_list_elements) {
					moi_dropdown_list_values.add(moi_drpdwn_list_element.getText())
				}
				WebUI.comment('MOI Configuration dropdown list::'+moi_dropdown_list_values+'\n')
				WebUI.click(dropdown_icon)
				return moi_dropdown_list_values
			}
			else {
				WebUI.comment('MOI Dropdown is not in visible state'+'\n')
				assert false
			}
		}
		catch (Exception e) {
			WebUI.takeScreenshot()
			return null
		}
	}

	@CompileStatic
	@Keyword
	//'To change Textbox Value'
	def changeTextBoxValues (TestObject textbox_locator,String enter_val) {

		WebDriver driver = DriverFactory.getWebDriver()
		'To clear the existing textbox value'
		WebUI.clearText(textbox_locator)
		'To enter the key values for textbox'
		WebUI.delay(3)
		WebUI.sendKeys(textbox_locator, enter_val)
		'To get the textboxbox value'
		def textbox_val = WebUI.getAttribute(textbox_locator, 'value')
		return textbox_val

	}
	@CompileStatic
	@Keyword
	//'To Check MOI checkbox and c2 Checkbox unselected status'
	def getMoiUnSelectedStatus(TestObject exp_moi) {

		WebDriver driver = DriverFactory.getWebDriver()

		'To check MOI Testcases checkbox status'
		def moi_checkbox_sts = WebUI.verifyElementNotChecked(exp_moi,5)

		'To check c2 Testcases checkbox status'
		def c2_testcases_checkbox_sts = WebUI.verifyElementNotChecked(findTestObject('Object Repository/Test_Config_Panel/Testconfig_icons/span_C2_Testcases_Checkbox'),5)

		try {
			if(moi_checkbox_sts && c2_testcases_checkbox_sts) {

				WebUI.comment('moi_checkbox_sts::'+moi_checkbox_sts+'***********'+'c2_testcases_checkbox_sts::'+c2_testcases_checkbox_sts+'\n')
				return true
			}
			else {
				WebUI.comment('Checkbox is selected'+'\n')
				assert false
			}
		}
		catch (Exception e) {
			WebUI.takeScreenshot()
			return null
		}
	}

	@CompileStatic
	@Keyword
	//'To select lit of Testcase '
	def selectListOfTestCase (def testcase_name) {

		WebDriver driver = DriverFactory.getWebDriver()
		'To click Expand Test List Checkbox'
		WebUI.delay(5)
		WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Expand_Test_List'))
		'To check Expected Testcase is Available or not'
		for(String separate_testcase :testcase_name) {
			def certification_name_sts=driver.findElement(By.xpath("//span[text()='"+separate_testcase+"']")).isDisplayed()
			if(certification_name_sts)
			{
				'To click Indidual Testcase'
				driver.findElement(By.xpath("//span[text()='"+separate_testcase+"']")).click()
				WebUI.delay(5)
			}
			else {
				WebUI.comment('')
				assert false
			}
		}
	}

	@CompileStatic
	@Keyword
	//'To read testcase Execution Result and Testcases for Filter Section'
	def readTestCaseExecutionResult (List expected_moi_list,List remove_subtest_for_count) {
		List testcase_and_execution_sts = []
		String testcase_and_exe_sts =[]
		try {
			WebDriver driver = DriverFactory.getWebDriver()
			List execution_sts = driver.findElements(By.xpath('//span[@class="rct-node-clickable"]/span/p'))
			int testcase_size = execution_sts.size()

			for (int i = 1; i <= testcase_size; i++) {
				'To read test result property'
				WebElement testcase_name = driver.findElement(By.xpath('(//span[@class="rct-node-clickable"]/span/p)['+i+']'))
				String testresult_sts = testcase_name.getAttribute('class')
				'To read selected Testcases Name'
				WebElement pass_testcase_name = driver.findElement(By.xpath('(//span[@class="rct-node-clickable"]/span[2])['+i+']'))
				String testcase = pass_testcase_name.getAttribute('innerText')

				if(!(expected_moi_list.contains(testcase)||remove_subtest_for_count.contains(testcase))) {

					if (testresult_sts.contains('result-pass') || testresult_sts.contains('result-na')) {

						testcase_and_exe_sts = ('pass'+':'+testcase)
						testcase_and_execution_sts.add(testcase_and_exe_sts)
					}
					else if (testresult_sts.contains('result-fail')) {

						testcase_and_exe_sts = ('fail'+':'+testcase)
						testcase_and_execution_sts.add(testcase_and_exe_sts)
					}
					else if (testresult_sts.contains('result-incomplete')) {

						testcase_and_exe_sts = ('incomplete'+':'+testcase)
						testcase_and_execution_sts.add(testcase_and_exe_sts)
					} else {

						testcase_and_exe_sts = ('warning'+':'+testcase)
						testcase_and_execution_sts.add(testcase_and_exe_sts)
					}
				}
			}
			return testcase_and_execution_sts
		}
		catch (Exception e) {
			WebUI.takeScreenshot()
			assert false
		}

	}


	@CompileStatic
	@Keyword
	//'To verify filter section selected checkbox testcase and converting to list'
	def filterSectionCheckboxSts (String checkbox_locator) {
		List sel_checkbox_testcaselist=[]
		try {
			WebDriver driver = DriverFactory.getWebDriver()
			List selected_checkbox_sts = driver.findElements(By.xpath(checkbox_locator))
			int selected_checkbox_sts_size = selected_checkbox_sts.size()
			for (int i = 1; i <= selected_checkbox_sts_size; i++) {
				'To read selected checkbox testcase and convert to list'
				WebElement selected_testcase_name = driver.findElement(By.xpath('('+checkbox_locator+')['+i+']'))
				String selected_testcase_name_text = selected_testcase_name.getText()
				sel_checkbox_testcaselist.add(selected_testcase_name_text.trim())
			}
			return sel_checkbox_testcaselist
		}
		catch (Exception e) {
			WebUI.takeScreenshot()
			assert false
		}

	}


	@CompileStatic
	@Keyword
	//'To verify filter section unselected background clr'
	def unselectedFilterButtonBgColor (TestObject filterbuttoncolor) {
		try {
			WebDriver driver = DriverFactory.getWebDriver()
			def unselected_filter_button_color = WebUI.getCSSValue(filterbuttoncolor, 'background-color')
			'To convert rgb format to hex value'
			String unselected_filter_btn_hexacolor = Color.fromString(unselected_filter_button_color).asHex();
			if(unselected_filter_btn_hexacolor) {
				WebUI.comment('UnSelected Filter button color is ::'+unselected_filter_btn_hexacolor+'\n')
			}


			return unselected_filter_btn_hexacolor
		}
		catch (Exception e) {
			WebUI.takeScreenshot()
			assert false
		}


	}
	@CompileStatic
	@Keyword
	//'To verify filter section selected button  background clr'
	def selectedFilterButtonBgColor(TestObject selectedfilterbuttoncolor) {

		try {
			WebDriver driver = DriverFactory.getWebDriver()
			WebUI.delay(3)
			def filter_button_color = WebUI.getAttribute(selectedfilterbuttoncolor, 'class')
			if(filter_button_color.contains('pass')||filter_button_color.contains('fail')||filter_button_color.contains('warning')||filter_button_color.contains('incomplete')) {
				WebUI.comment('Filter button background color is as Expected'+'\n')
			}


		}
		catch (Exception e) {
			WebUI.takeScreenshot()
			assert false
		}

	}

	@CompileStatic
	@Keyword
	def readTestExecutionSts () {
		WebDriver driver = DriverFactory.getWebDriver()
		def run_status

		try {
			run_status = true

			while (run_status) {
				//'To check Unlive button status'
				def sts = driver.findElement(By.xpath('//button/img[@src=\'../../images/chartIcons/PNG/Live.png\']'))

				if (sts) {
					WebUI.delay(5)

					run_status = true
				}
			}
		}
		catch (Exception e) {
			run_status = false
		}

		WebUI.delay(20)

	}

	@CompileStatic
	@Keyword
	def popupTimer(String popup_timer_val) {

		//'To check timeout popup checkbox is Enabled or not'
		try {
			def timeout_popup_sts = WebUI.verifyElementChecked(findTestObject('Object Repository/Test_Config_Panel/Test_Execution/input_Timeout_Popup_Checkbox'),
					30)

			if (timeout_popup_sts) {
				WebUI.clearText(findTestObject('Object Repository/Test_Config_Panel/Test_Execution/input_Popup_Timer'))

				WebUI.sendKeys(findTestObject('Object Repository/Test_Config_Panel/Test_Execution/input_Popup_Timer'), popup_timer_val)
			}
		}
		catch (Exception e) {
			WebUI.click(findTestObject('Object Repository/Test_Config_Panel/Test_Execution/input_Timeout_Popup_Checkbox'))

			WebUI.clearText(findTestObject('Object Repository/Test_Config_Panel/Test_Execution/input_Popup_Timer'))

			WebUI.sendKeys(findTestObject('Object Repository/Test_Config_Panel/Test_Execution/input_Popup_Timer'), popup_timer_val)
		}

	}

	@CompileStatic
	@Keyword
	def selectUnselectedCheckbox(TestObject checkbox_locator) {
		//'To click checkbox if it is not selected'

		try {
			def timeout_popup_sts = WebUI.verifyElementChecked(checkbox_locator,30)
		}
		catch (Exception e) {
			WebUI.delay(3)

			WebUI.click(checkbox_locator)
		}

	}

	@CompileStatic
	@Keyword
	def getConnectionSts () {

		'To navigate Connection Setup page'
		WebUI.click(findTestObject('Object Repository/AllTabs_Entry_Page/Page_GRL - USB PD C2/span_Connection_Setup'))

		WebUI.delay(40)

		'To Read Connection btn status '
		def tester_status = WebUI.getText(findTestObject('Object Repository/Connection_Setup_Objects/b_Connection_Status'))

		if (tester_status.contains('Connected')) {
			WebUI.comment('Tester Connection Status :: ' + tester_status)
		} else {
			WebUI.comment('Tester Connection Status Failed :: ' + tester_status)

			assert false
		}
	}


}







