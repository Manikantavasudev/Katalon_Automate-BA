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
import internal.GlobalVariable as GlobalVariable


'Browser Initialization'
WebUI.openBrowser('')
WebUI.navigateToUrl('http://localhost:5001/')
WebUI.maximizeWindow()

'step0: conect button'
if( WebUI.verifyElementClickable(findTestObject('Object Repository/Page_GRL - USB PD C2/button_Connect')))
{
	WebUI.comment('connect button is clickable')
	WebUI.click(findTestObject('Object Repository/Page_GRL - USB PD C2/button_Connect'))
	WebUI.delay(5)
	'step1: verify eload update button'
	if( WebUI.verifyElementClickable(findTestObject('Object Repository/Product_Capability_Objects/click _Eload_update')))
	{
		update_Eload_text_sts = WebUI.getText(findTestObject('Object Repository/Product_Capability_Objects/click _Eload_update'))

		println(update_Eload_text_sts)
		if(update_Eload_text_sts == "Update E-Load Firmware")
		{
			WebUI.comment('update c2 ELoad button name proper as expected')
		}
		else
		{
			WebUI.comment('update c2 ELoad name is not expected')
			assert false
		}
	


		'step2: click  Eload button'
		WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/click _Eload_update'))

		'Step3: verify ELoad popup messaage button'
		if( WebUI.verifyElementPresent(findTestObject('Object Repository/Product_Capability_Objects/Eload__update/Eload _popup'),3) )
		{
			WebUI.delay(2)
			sucessfull =WebUI.getText(findTestObject('Object Repository/Product_Capability_Objects/Eload__update/Eload _popup'))
			if(sucessfull == "Update Eload Firmware" )
			{
				WebUI.comment('Update Eload Firmware dailogue appeared')
			}
			else
			{
				WebUI.comment('Update Eload Firmware dailogue not appeared as expected')
			}

			'step 4: verify eload update button'
			if( WebUI.verifyElementClickable(findTestObject('Object Repository/Product_Capability_Objects/Eload__update/Elaod_popup_button')) )
			{
				WebUI.delay(2)
				WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Eload__update/Elaod_popup_button'))
				WebUI.delay(4)

				'step:5 verify popup message'
				boolean result
				try
				{
					result = WebUI.verifyElementPresent(findTestObject('Object Repository/Product_Capability_Objects/Eload__update/Port_POPup'),4)
				}

				catch (Exception e) {
					result = false

				}
				if( result )
				{
					'Verify GRL-USB-PD Compliance Test Solution popup'
					if(WebUI.getText(findTestObject('Object Repository/Product_Capability_Objects/Eload__update/Port_POPup')) == "GRL-USB-PD Compliance Test Solution")
					{
						String dailogue = "Please select eload's COM port in drop down menu and then click OK\nClick Cancel to stop e-load FW update."	
				
						String dailogue_2 = "Software could not detect eload COM port.\nPlease verify the connection and try again."


						WebUI.comment("remove all cable except eload cable popup detected")

						'click ok button for remove all cable need to remove except eload wire'
						if(WebUI.verifyElementClickable(findTestObject('Object Repository/Product_Capability_Objects/Eload__update/button_GRL_USB_PD _popup')))
						{
							WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Eload__update/button_GRL_USB_PD _popup'))
							WebUI.delay(2)
							try
							{
								WebUI.verifyElementPresent(findTestObject('Object Repository/Product_Capability_Objects/Eload__update/Select_C2_eload_COM_Port'), 10)
							}
							catch(Exception e)
							{
								result = false
							}
							if(result)
							{
								WebUI.delay(1)

								boolean com= false
								println("string value below")
								
								String got = WebUI.getText(findTestObject('Object Repository/Product_Capability_Objects/Eload__update/com_selection'))
								println(got)
								if(got.contains(dailogue) )
								{


									WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Eload__update/Right_click_port'))
									WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Eload__update/com_six_select'))
									com = true
									WebUI.comment("selected comp_port as default")
									WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Eload__update/Ok_com_port'))
									WebUI.delay(60)
									'step:6 verify final popup message after updating eload'

									try
									{
										result = WebUI.verifyElementPresent(findTestObject('Object Repository/Product_Capability_Objects/Eload__update/verify_Eload_update_Proper_or_not'),5)
									}
									catch(Exception e)
									{
										result =  false
									}
									if(result)
									{
										if("Eload Firmware Updated" == WebUI.getText(findTestObject('Object Repository/Product_Capability_Objects/Eload__update/verify_Eload_update_Proper_or_not')) )
										{
											WebUI.comment('eload update is sucessfull')
										}
										else
										{
											'need to update final popup header text (sucesss or not)'
											WebUI.comment('eload is failed')
										}
									}

								}
								else if(got.contains(dailogue_2) )
								{
									WebUI.comment("If eload cable is connected means eload cable detection is failed")
									WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Eload__update/Select_C2s_eload_COM_Port_ok_button'))
									CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
								}
								else
								{
									WebUI.comment('No popup message is appeared')
									CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
									assert false

								}
							}
							else
							{
								WebUI.comment('unable to click GRL-USB-PD Compliance Test Solution ok button')
								assert false
							}

						}
						else
						{
							WebUI.comment("remove all cable popup is not displayed")
		
						}

					}
					else
					{
						WebUI.comment("GRL-USB-PD Compliance Test Solution popup message is not appeared")
						assert false
						CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
					}

				}
				else
				{
					WebUI.delay(60)
					'step:6 verify final popup message after updating eload'
					try
					{
						result = WebUI.verifyElementPresent(findTestObject('Object Repository/Product_Capability_Objects/Eload__update/verify_Eload_update_Proper_or_not'),8)
					}
					catch(Exception e)
					{
						result  = false
					}
					if( result)
					{
						if("Eload Firmware Updated" == WebUI.getText(findTestObject('Object Repository/Product_Capability_Objects/Eload__update/verify_Eload_update_Proper_or_not')) )
						{
							WebUI.comment('eload update is sucessfull')
						}
						else
						{
							'need to update final popup header text (sucesss or not)'
							WebUI.comment('eload is failed')
						}
					}
					else
					{
						WebUI.comment('GRL-USB-PD Compliance Test Solution popup message popup is not displayed ')
						CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
					}
					if(WebUI.verifyElementClickable(findTestObject('Object Repository/Product_Capability_Objects/Eload__update/Button_sucessfull_eload _update')) )
					{
						WebUI.click(findTestObject('Object Repository/Product_Capability_Objects/Eload__update/Button_sucessfull_eload _update'))
						WebUI.comment('final button is clickable')
						CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
						assert true
					}
					else
					{
						WebUI.comment('final button is unclickable')
					}
					CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
				}
				
			}
			else
			{
				WebUI.comment('eload update button is unclickable')
				assert false
				CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
			}
		}
		else
		{
			WebUI.comment('eload popup message is not present')
			CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
			assert false
		}
	}
	else
	{
		WebUI.comment('eload Firmware button is not present or invisible')
		assert false
		CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
		
	}

}
else
{
	WebUI.comment('unable to connect c2')
	assert false 
	CustomKeywords.'grl_usb_pd_c2_Testcase.Browser_SetUp_Teardown.browserTeardown'()
}
