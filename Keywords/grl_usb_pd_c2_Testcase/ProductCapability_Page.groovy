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
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.KeyEvent
import internal.GlobalVariable
import java.awt.Robot
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


public class ProductCapability_Page {

	@CompileStatic
	@Keyword
	//'To click Load xml Vif file button and load xml file'
	def uploadFile (TestObject to, String filePath) {
		try {
			WebDriver driver = DriverFactory.getWebDriver()
			Path path = Paths.get(filePath);
			Boolean file_path_sts=Files.exists(path)
			println(file_path_sts)
			//if(file_path_sts) {
			WebUI.delay(10)
			WebUI.click(to)
			//driver.findElement(By.xpath("//button[@id ='pcLoadXmlVifFileBtn']")).click()
			WebUI.delay(10)
			StringSelection ss = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			'Used Robot framework for handling the windows filepath object'
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			/*}
			 else {
			 'FilePath is not valid'
			 WebUI.comment('User provided Filepath is not valid')
			 assert false
			 }*/
		}
		catch (Exception e) {
			WebUI.comment('windows File Open Dialog not Appears'+'\n')
			WebUI.takeScreenshot()
			assert false
		}
	}

	@CompileStatic
	@Keyword
	//To validate user provided filename and uploaded filename
	def fileNameValidation (String filePath) {
		'To split user provided file path'
		int index = filePath.lastIndexOf("\\")
		String user_provide__filename = filePath.substring(index + 1)
		WebUI.comment('User Provided File Name is ::'+user_provide__filename+'\n')
		WebUI.delay(10)
		'This Step will verify uploaded file name is present under Load Xml file button or not'
		String uploaded_filename = WebUI.getText(findTestObject('Object Repository/Product_Capability_Objects/button_Load XML VIF File'))
		WebUI.comment('Filename on Load vif xml file Button::'+uploaded_filename+'\n')
		WebUI.delay(5)
		if(uploaded_filename == user_provide__filename) {

			WebUI.comment('User Provided File Name is  Matching ::'+uploaded_filename+'\n')
		}
		else {
			WebUI.comment('Expected File Name is Not Present on Load vif xml Button'+'\n')

		}
	}
	@CompileStatic
	@Keyword
	def vifFilePopupContentValidation (String vif_file_expected_Popup_Content) {
		'To check popup Content After uploading the xml File'
		try {
			if(WebUI.verifyElementVisible(findTestObject('Object Repository/Product_Capability_Objects/popup_content'))) {

				'This step will Read the Popup Content'
				String vif_file_popup_content = WebUI.getText(findTestObject('Object Repository/Product_Capability_Objects/popup_content'))
				WebUI.comment('Vif File Popup Content::'+vif_file_popup_content+'\n')
				Boolean vif_file_content_status=vif_file_popup_content ==  vif_file_expected_Popup_Content
				if(vif_file_content_status) {
					WebUI.comment('Popup Content Has been Verified'+'\n')
					WebUI.takeScreenshot()
				}
			}
			else {
				'popup content is disappered within time limits'
				WebUI.comment('Popup disappered within the Time Limit'+'\n')
				WebUI.takeScreenshot()

			}

			WebUI.delay(10)
			def vif_file_popup_status = WebUI.verifyElementNotPresent(findTestObject('Object Repository/Product_Capability_Objects/popup_content'), 5)
			WebUI.comment('popup Content is disappered::'+vif_file_popup_status+'\n')
		}
		catch (Exception e) {
			WebUI.takeScreenshot()
		}
	}

	@CompileStatic
	@Keyword
	//'To check Port_1 Cabel Selection dropdown list in Compliance mode'
	def complianceCabelSelectionDropdown () {
		List cabel_selection_drpdwn_list=[]
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Compliance/button_cabel_selection_dropdown_icon'))
		WebUI.delay(5)
		List<WebElement> dropdwn_port1_cabel_elements = driver.findElements(By.xpath("//div[@aria-labelledby='pcPort1CableSelectionComboBox']/a"))
		WebUI.comment('The size of elements is ::' +  dropdwn_port1_cabel_elements.size()+'\n')
		try {
			if( dropdwn_port1_cabel_elements.size()>0) {
				for (WebElement dropdown_cabel_element : dropdwn_port1_cabel_elements) {
					cabel_selection_drpdwn_list.add(dropdown_cabel_element.getAttribute('value'))
				}
				WebUI.comment('Cabel dropdown::'+cabel_selection_drpdwn_list+'\n')
			}
			else {
				WebUI.comment('Cabel Selection Dropdown is not in visible state'+'\n')
				assert false
			}
		}
		catch (Exception e) {
			WebUI.takeScreenshot()
		}

	}
	@CompileStatic
	@Keyword
	//'To check Port_1 DUT type  list in Info mode'
	def port1InformationaldutDropdownList () {
		List p1_dut_type_drpdwn_list=[]
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_1_dut_dropdown_icon'))
		WebUI.delay(5)
		List<WebElement> dropdwn_port1_dut_type_elements = driver.findElements(By.xpath("//div[@aria-labelledby='pcPort1DutTypeComboBox']/a"))
		WebUI.comment('The size of elements is ::' +  dropdwn_port1_dut_type_elements.size()+'\n')
		try {
			if( dropdwn_port1_dut_type_elements.size()>0 ) {
				for (WebElement dropdown_dut_element : dropdwn_port1_dut_type_elements) {
					p1_dut_type_drpdwn_list.add(dropdown_dut_element.getText())
				}
				WebUI.comment('Dut Type dropdown::'+p1_dut_type_drpdwn_list+'\n')
				WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_1_dut_dropdown_icon'))
				return p1_dut_type_drpdwn_list
			}
			else {
				WebUI.comment('Dut Type Dropdown is not in visible state'+'\n')
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
	//'To check Port_1  Cabel Selection dropdown list in Info mode'
	def port1InformationalCabelDropdownList () {
		List p1_cabel_drpdwn_list=[]
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_1_cabel_dropdown_icon'))
		WebUI.delay(5)
		List<WebElement> dropdwn_port1_cabel_elements = driver.findElements(By.xpath("//div[@aria-labelledby='pcPort1CableSelectionComboBox']/a"))
		WebUI.comment('The size of elements is ::' +  dropdwn_port1_cabel_elements.size()+'\n')
		try {
			if( dropdwn_port1_cabel_elements.size()>0 ) {
				for (WebElement dropdown_cabel_element : dropdwn_port1_cabel_elements) {
					p1_cabel_drpdwn_list.add(dropdown_cabel_element.getAttribute('value'))
				}
				WebUI.comment('Cabel dropdown:: '+p1_cabel_drpdwn_list+'\n')
				WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_1_cabel_dropdown_icon'))
				return p1_cabel_drpdwn_list
			}

			else {
				WebUI.comment('Cabel Selection Dropdown is not in visible state'+'\n')
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
	//'To check Port_2 DUT type  list in Info mode'
	def port2InformationaldutDropdownList () {
		List p2_dut_type_drpdwn_list=[]
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_2_dut_dropdown_icon'))
		WebUI.delay(5)
		List<WebElement> dropdwn_port2_dut_type_elements = driver.findElements(By.xpath("//div[@aria-labelledby='pcPort2DutTypeComboBox']/a"))
		WebUI.comment('The size of elements is ::' +  dropdwn_port2_dut_type_elements.size()+'\n')
		try {
			if( dropdwn_port2_dut_type_elements.size()>0 ) {
				for (WebElement dropdown_dut_element : dropdwn_port2_dut_type_elements) {
					p2_dut_type_drpdwn_list.add(dropdown_dut_element.getText())
				}
				WebUI.comment('Dut Type dropdown::'+p2_dut_type_drpdwn_list+'\n')
				WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_2_dut_dropdown_icon'))
				return p2_dut_type_drpdwn_list
			}
			else {
				WebUI.comment('Dut Type Dropdown is not in visible state'+'\n')
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
	//'To check Port_2  Cabel Selection dropdown list in Info mode'
	def port2InformationalCabelDropdownList () {
		List p2_cabel_drpdwn_list=[]
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_2_cabel_dropdown_icon'))
		WebUI.delay(5)
		List<WebElement> dropdwn_port2_cabel_elements = driver.findElements(By.xpath("//div[@aria-labelledby='pcPort2CableSelectionComboBox']/a"))
		WebUI.comment('The size of elements is ::' +  dropdwn_port2_cabel_elements.size()+'\n')
		try {
			if( dropdwn_port2_cabel_elements.size()>0 ) {
				for (WebElement dropdown_cabel_element : dropdwn_port2_cabel_elements) {
					p2_cabel_drpdwn_list.add(dropdown_cabel_element.getAttribute('value'))
				}
				WebUI.comment('Cabel dropdown:: '+p2_cabel_drpdwn_list+'\n')
				WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Informational(No VIF)/button_port_2_cabel_dropdown_icon'))
				return p2_cabel_drpdwn_list
			}
			else {
				WebUI.comment('Cabel Selection Dropdown is not in visible state'+'\n')
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
	//'To check dut dropdown list in Create new vif button'
	def createNewVifDutDropdownList () {
		List create_newvif_dut_drpdwn_list=[]
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Create_New_VIF/button_Create_New_Vif_Dropdown_Icon'))
		WebUI.delay(5)
		List<WebElement> dropdwn_dut_elements = driver.findElements(By.xpath("//div[@aria-labelledby='pcVifConfigDutTypeComboBox']/a"))
		WebUI.comment('The size of elements is ::' +  dropdwn_dut_elements.size()+'\n')
		try {
			if( dropdwn_dut_elements.size()>0 ) {
				for (WebElement dropdown_dut_element : dropdwn_dut_elements) {
					create_newvif_dut_drpdwn_list.add(dropdown_dut_element.getText())
				}
				WebUI.comment('Create New vif dut dropdown:: '+create_newvif_dut_drpdwn_list+'\n')
				WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Create_New_VIF/button_Create_New_Vif_Dropdown_Icon'))
			}
			else {
				WebUI.comment('Cabel Selection Dropdown is not in visible state'+'\n')
				assert false
			}
		}
		catch (Exception e) {
			WebUI.takeScreenshot()
		}

	}


	@CompileStatic
	@Keyword
	//'To edit vif file data'
	def editVifFile (Map vif_file_data_collection,List vif_file_row_count,List vif_file_dropdown) {
		WebDriver driver = DriverFactory.getWebDriver()
		'To edit user provided input in textbox'
		for (def file_data : vif_file_row_count) {
			if (!(vif_file_dropdown.contains(file_data))) {
				driver.findElement(By.xpath(('//tr[' + file_data) + ']/td[2]/div/input')).clear()

				WebUI.comment('Row Data element:: ' + driver.findElement(By.xpath(('//tr[' + file_data) + ']/td[2]/div/input')))
				String textbox_input = vif_file_data_collection.get(file_data)

				driver.findElement(By.xpath(('//tr[' + file_data) + ']/td[2]/div/input')).sendKeys(textbox_input)

				WebUI.comment('Textbox VIF Data::' + textbox_input)
			} else {
				'To choose user provided dropdown option'
				driver.findElement(By.cssSelector(('tr:nth-child(' + file_data) + ') .dropdown-toggle')).click()

				WebUI.comment('Row Data element:: ' + driver.findElement(By.cssSelector(('tr:nth-child(' +file_data) + ') .dropdown-toggle')))

				String dropdown_option_text =vif_file_data_collection.get(file_data)

				driver.findElement(By.linkText(dropdown_option_text)).click()

				WebUI.comment('dropdown VIF Data:: ' + dropdown_option_text)
			}
		}}

}



