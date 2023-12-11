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


class MOI_Selection {
	@CompileStatic
	@Keyword
	def forDRPMOISelection () {
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/pdm/Phy_all_'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/pdm/PD_PS_SRC'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/pdm/PD_SNK_TC'))
		try {

			if( WebUI.callTestCase(findTestCase('Test Cases/VIF_FILE_READER/Read_Vif_SRC'),[:], FailureHandling.STOP_ON_FAILURE) == "true") {
				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/pdm/EPR_SRC'))
			}
			if( WebUI.callTestCase(findTestCase('Test Cases/VIF_FILE_READER/Read_Vif_SNK'),[:], FailureHandling.STOP_ON_FAILURE) == "true") {
				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/pdm/EPR_SNK'))
			}
		}
		catch(Exception e) {
			println("epr not supported")
		}

		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/2-1-1-1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/2-1-1-2'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/2-1-2-2'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/2-1-2-1'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/2-3-1-1'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/2-3-1-2'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/2-3-2-1'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/2-3-2-2'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/2-3-3-1'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/2-3-3-2'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNK-E1'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNK-E2'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNK-E3'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNK-E4'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNK-E5'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNK-E6'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNK-E7'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNK-E8'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNK-E9'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNk-E10'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNk-E12'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/PHY-E-6'))

		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-1-1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-3-1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-3-2'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-5-1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-5-4'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-6-1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-6-3'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-7-1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-7-2'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-13-5'))
		try {
			if( WebUI.callTestCase(findTestCase('Test Cases/VIF_FILE_READER/FRS_Supported_SRC'),[:], FailureHandling.STOP_ON_FAILURE) != "0" ) {
				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SRC-E1'))
				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SRC-E3'))
				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SRC-E4'))
				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SRC-E5'))
				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SRC-E6'))
			}
			if( WebUI.callTestCase(findTestCase('Test Cases/VIF_FILE_READER/FRS_Supported_snk'),[:], FailureHandling.STOP_ON_FAILURE) != "0") {
				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SNK-E1'))
				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SNK-E2'))
				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SNK-E3'))
				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SNK-E4'))
				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SNK-E5'))
				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SNK-E6'))
			}
		}
		catch (Exception e) {
			println("no FRS supported UUT")
		}
	}


	@CompileStatic
	@Keyword
	def forConsumerOnly () {
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/pdm/Phy_all_'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/pdm/PD_SNK_TC'))
		try {
			if( WebUI.callTestCase(findTestCase('Test Cases/VIF_FILE_READER/Read_Vif_SNK'),[:], FailureHandling.STOP_ON_FAILURE) == "true") {
				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/pdm/EPR_SNK'))
			}
		}
		catch (Exception e) {
			println("no epr snk supported UUT")
		}
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/2-1-1-1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/2-1-1-2'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/2-1-2-2'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/2-1-2-1'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/2-3-3-1'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNK-E1'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNK-E2'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNK-E3'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNK-E4'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNK-E5'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNK-E6'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNK-E7'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNK-E8'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNK-E9'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNk-E10'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/SNk-E12'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/PHY-E-6'))

		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-1-1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-3-1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-3-2'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-5-1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-5-4'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-6-1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-6-3'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-7-1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-7-2'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-13-5'))
		//		try {
		//
		//			if( WebUI.callTestCase(findTestCase('Test Cases/VIF_FILE_READER/FRS_Supported_SRC'),[:], FailureHandling.STOP_ON_FAILURE) != "0") {
		//
		//				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SNK-E1'))
		//				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SNK-E2'))
		//				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SNK-E3'))
		//				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SNK-E4'))
		//				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SNK-E5'))
		//				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SNK-E6'))
		//			}
		//		}
		//		catch (Exception e) {
		//			println("no FRS snk supported UUT")
		//		}
	}
	@CompileStatic
	@Keyword
	def forCable () {
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/pdm/Phy_all_'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/1-1-1-1-1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/1-1-1-2-1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/1-1-2-1-1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/1-1-2-2-1'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/PHY-E-6'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-13-5'))
	}
	@CompileStatic
	@Keyword
	def ProviderOnly () {
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/pdm/Phy_all_'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/pdm/PD_PS_SRC'))
		try {
			if( WebUI.callTestCase(findTestCase('Test Cases/VIF_FILE_READER/Read_Vif_SRC'),[:], FailureHandling.STOP_ON_FAILURE) == "true") {
				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/pdm/EPR_SRC'))
			}
		}
		catch(Exception e) {
			println("epr src not supported")
		}
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/2-1-1-1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/2-1-1-2'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/2-1-2-2'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/2-1-2-1'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/2-3-1-1'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD2/2-3-2-1'))
//		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/DeterMinistic/PHY-E-6'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-1-1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-3-1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-3-2'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-5-1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-5-4'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-6-1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-6-3'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-7-1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-7-2'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Function/4-13-5'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/SPT/SPT_1'))
		WebUI.click(findTestObject('Object Repository/MOI_selection_lists/SPT/SPT_5'))

		//		try {
		//			if( WebUI.callTestCase(findTestCase('Test Cases/VIF_FILE_READER/FRS_Supported_snk'),[:], FailureHandling.STOP_ON_FAILURE) == "0") {
		//				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SRC-E1'))
		//				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SRC-E3'))
		//				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SRC-E4'))
		//				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SRC-E5'))
		//				WebUI.click(findTestObject('Object Repository/MOI_selection_lists/PD3/FRS-SRC-E6'))
		//			}
		//		}
		//		catch (Exception e) {
		//			println("no FRS supported UUT")
		//		}
		try
		{
			WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Qc_3/QC_Legacy_shorting'))
			WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Qc_3/QC_legacy_HVDCP'))
			WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Qc_3/QC_LEGACY_USBPD_Trans'))
			WebUI.callTestCase(findTestCase('Common_Data_Reader/Comparison_Tool_Moi_Data_Reader/Other_Dut/Config_Quick_Charger_3_Tests'),
					[:], FailureHandling.STOP_ON_FAILURE)
			WebUI.click(findTestObject('Object Repository/MOI_selection_lists/QC_4/QC4_TID_3'))
			WebUI.click(findTestObject('Object Repository/MOI_selection_lists/QC_4/QC4_TID_10'))
			WebUI.click(findTestObject('Object Repository/MOI_selection_lists/QC_4/QC4_TID_13'))
			WebUI.click(findTestObject('Object Repository/MOI_selection_lists/QC_4/QC4_TID_17'))
			WebUI.click(findTestObject('Object Repository/MOI_selection_lists/QC_4/QC_4_IOP_09'))
			WebUI.callTestCase(findTestCase('Common_Data_Reader/Comparison_Tool_Moi_Data_Reader/Other_Dut/Config_Quick_Charge_4_Tests'),
					[:], FailureHandling.STOP_ON_FAILURE)
		}
		catch(Exception f)
		{
			println("Qc not supported UUT")
		}

		try
		{

			WebUI.click(findTestObject('Object Repository/MOI_selection_lists/Custom OEM/Custom.OEM.TD.4.9.2 OCP and OVP Test'))
			WebUI.callTestCase(findTestCase('Common_Data_Reader/Comparison_Tool_Moi_Data_Reader/Other_Dut/Config_Quick_Charge_4_Tests'),
					[:], FailureHandling.STOP_ON_FAILURE)

		}
		catch(Exception g)
		{
			println("Custom OEM not supported UUT")
		}


	}
}

