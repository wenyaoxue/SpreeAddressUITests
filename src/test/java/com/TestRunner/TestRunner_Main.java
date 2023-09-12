package com.TestRunner;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
//		features = {"Feature/Spree_AddressForm.feature"}
		features = {"Feature/Spree_AddressForm_JSON.feature"}
//		features = {"Feature/Spree_AddressFlow.feature"}
		,glue= "com.StepDefinition"
		,dryRun=false
		,monochrome=true
		,plugin  = {"pretty","html:CucumberReport/Report.html"}
		)
public class TestRunner_Main {

}
