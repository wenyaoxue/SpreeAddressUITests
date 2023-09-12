package com.TestRunner;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(

		//features = {"Feature/ToGet_Details_Using_CountryName_UsingAPI.feature"}
		//features = {"Feature/WebOrder_Login_DataTable_All_TCs.feature"}
		//features = {"Feature/TestComplete_Order.feature"}
		//features = {"Feature/TestComplete_Login.feature"}
		//features = {"Feature/OrangeHRM_Login.feature"}
		//features = {"Feature/OrangeHRM_AddUser.feature"}
//		features = {"Feature/Spree_Login.feature"}
//		features = {"Feature/WebOrder_Order.feature"}
//		features = {"Feature/WebOrder_Login.feature"}
		//features = {"Feature"}
//		features = {"Feature/Spree_AddressForm.feature"}
		features = {"Feature/Spree_AddressForm_JSON.feature"}
//		features = {"Feature/Spree_AddressFlow.feature"}
		,glue= "com.StepDefinition"
		,dryRun=false
		,monochrome=true
		,plugin  = {"pretty","html:CucumberReport/Report.html"}
		//,plugin  = {"pretty","html:CucumberReport/Report.html","rerun:Rerun/failed_scenarios.txt"}
		//Execute all of them Except Sanity
		//,tags="not @smoke"
		// Execute Smoke and Sanity both test cases
		//,tags= "@smoke or @sanity"
		// Execute Smoke and Sanity , but both should be marked together in feature
		//,tags= "@smoke and @sanity"
		//,tags= "@smoke"
		
		)
public class TestRunner_Main {

}
