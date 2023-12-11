package checking_instance_of_app

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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import internal.GlobalVariable

public class instance_checking_c2_app {
	@Keyword
	def checking_instance() {
		try {
			String process;
			def flag = "False"
			// getRuntime: Returns the runtime object associated with the current Java application.
			// exec: Executes the specified string command in a separate process.
			Process p =  Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((process = input.readLine()) != null) {
				if(process.find("C2BrowserApp.exe")) {
					flag = "True"
					return flag
				}
			}
			input.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
	@Keyword
	def Run_C2_app() {
		def task = [
			"C:\\GRL\\C2_Automation\\Python\\python.exe" ,
			"..\\Python_files\\Instance_app_helper\\instance_call.py"
		].execute()

	}
}
