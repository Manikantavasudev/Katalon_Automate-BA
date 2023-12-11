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

WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:5001/')

WebUI.maximizeWindow()

if (WebUI.verifyElementClickable(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Product Capability'))) {
    WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Product Capability'))

    'This step will call Common Data Reader Testcase and it will read the user provided json data'
    Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

    String[] Get_Device_Data = all_data.user_config_data.Get_Device_Data

    int i = 0

    while (i < Get_Device_Data.length) {
        //boolean  result = WebUI.clearText('Object Repository/Page_GRL - USB PD C2/input_Project Name_pcNewProjectNameInputField', FailureHandling.STOP_ON_FAILURE)
        WebUI.sendKeys(findTestObject('Object Repository/Page_GRL - USB PD C2/input_Project Name_pcNewProjectNameInputField'), 
            Keys.chord(Keys.CONTROL, 'a'))

        WebUI.sendKeys(findTestObject('Object Repository/Page_GRL - USB PD C2/input_Project Name_pcNewProjectNameInputField'), 
            Keys.chord(Keys.BACK_SPACE))

        WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/button_Save'))

        println(Get_Device_Data)

        WebUI.setText(findTestObject('Object Repository/Page_GRL - USB PD C2/input_Project Name_pcNewProjectNameInputField'), 
            (Get_Device_Data[i]) + '_GetCaps')

        WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/button_Save'))

        // WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/button_Load XML VIF File'))
        WebUI.delay(1)

        WebUI.sendKeys(findTestObject('Product_Capability_Objects/Eload__update/Update_load_File'), ('C:\\GRL\\C2AutomationBuild_0.7.2Version\\V.3.2.4.0' + 
            '\\') + (Get_Device_Data[i]))

        WebUI.delay(2)
		
		if(WebUI.verifyAlertPresent(5,FailureHandling.STOP_ON_FAILURE))
		{
			WarningPopup = WebUI.getText(findTestObject('Object Repository/Page_GRL - USB PD C2/img_Product Capability_leftnavbar-img'))
			
			if(WarningPopup == "VIF Version Support")
			{
				WebUI.comment("Warning for VIF version")		
			}
			else
			{
				WebUI.comment("some Pop-up message appeared")				
			}
		}
		WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/button_Get Device Data'))
		WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/button_Ok'))
		
		
		
		
    }
    
    WebUI.closeBrowser()
}

WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:5001/v6.5.5.html')

WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/img_Product Capability_leftnavbar-img'))

WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Product Capability'))

WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/button_Get Device Data'))

WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/button_Ok'))

WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/button_Ok'))

WebUI.closeBrowser()

WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:5001/')

WebUI.maximizeWindow()

if (WebUI.verifyElementClickable(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Product Capability'))) {
    WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/span_Product Capability'))

    'This step will call Common Data Reader Testcase and it will read the user provided json data'
    Map all_data = WebUI.callTestCase(findTestCase('Common_Data_Reader/Test_Data_Reader'), [:], FailureHandling.STOP_ON_FAILURE)

    String[] Get_Device_Data = all_data.user_config_data.Get_Device_Data

    int i = 0

    while (i < Get_Device_Data.length) {
        //boolean  result = WebUI.clearText('Object Repository/Page_GRL - USB PD C2/input_Project Name_pcNewProjectNameInputField', FailureHandling.STOP_ON_FAILURE)
        WebUI.sendKeys(findTestObject('Object Repository/Page_GRL - USB PD C2/input_Project Name_pcNewProjectNameInputField'), 
            Keys.chord(Keys.CONTROL, 'a'))

        WebUI.sendKeys(findTestObject('Object Repository/Page_GRL - USB PD C2/input_Project Name_pcNewProjectNameInputField'), 
            Keys.chord(Keys.BACK_SPACE))

        WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/button_Save'))

        println(Get_Device_Data)

        WebUI.setText(findTestObject('Object Repository/Page_GRL - USB PD C2/input_Project Name_pcNewProjectNameInputField'), 
            (Get_Device_Data[i]) + '_GetCaps')

        WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/button_Save'))

        // WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/button_Load XML VIF File'))
        WebUI.delay(1)

        WebUI.sendKeys(findTestObject('Product_Capability_Objects/Eload__update/Update_load_File'), ('C:\\GRL\\C2AutomationBuild_0.7.2Version\\V.3.2.4.0' + 
            '\\') + (Get_Device_Data[i]))

        WebUI.delay(1)

        WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/button_Get Device Data'))

        WebUI.closeBrowser()

        i++
    }
}

WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/button_Ok'))

WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/button_Ok'))

WebUI.closeBrowser()

WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:5001/v6.5.5.html')

WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/label_Test Config'))

WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/label_Product Capability'))

WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/button_Load XML VIF File'))

WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/strong_VIF Version Support'))

WebUI.rightClick(findTestObject('Object Repository/Page_GRL - USB PD C2/button_Ok'))

WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/button_Ok'))

WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/button_Get Device Data'))

WebUI.closeBrowser()

