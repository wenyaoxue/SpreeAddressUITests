package com.StepDefinition;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.pom.SpreeAccountPom;
import com.pom.SpreeAddressPom;
import com.pom.SpreeLoginPom;
import com.utils.JSONReader_FileArrOneEle;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import junit.framework.Assert;

public class SpreecomPageStepDefinition extends AbstractPageStepDefinition {
	WebDriver driver = getDriver();
	SpreeLoginPom spreelogin = new SpreeLoginPom(driver);
	SpreeAccountPom spreeaccount = new SpreeAccountPom(driver);
	SpreeAddressPom spreeaddress = new SpreeAddressPom(driver);
	
	@Given("User has launched Chrome browser")
	public void user_has_launched_chrome_browser() {}

	@When("User navigates to Spree login page")
	public void visitSpreeLogin() {
		spreelogin.visit();
	}

	@When("User enters valid username and password in Spree login page")
	public void user_enters_valid_username_and_password_in_spree_login_page() {
		spreelogin.enterCredentials("c@s.com", "123456");
	}

	@When("User clicks login on Spree login page")
	public void user_clicks_login_on_spree_login_page() {
		spreelogin.clickLogin();
	}

	@Then("My Account page should display")
	public void my_account_page_should_display() {
	    spreeaccount.verifyHeader();
	}

	@When("User clicks logout on Spree")
	public void user_clicks_logout_on_spree() {
		spreeaccount.logout();
	}

	@Then("Login page should display")
	public void login_page_should_display() {
		spreelogin.verifyHeader();
	}

	@When("User deletes all existing addresses on Spree account page")
	public void deletealladddrs() throws InterruptedException {
		while (    spreeaccount.hasAtLeastOneDeleteBtn()    ) {
			Thread.sleep(1000);
			deletefirstaddr();
		}
	}
	@When("User deletes first address on Spree account page")
	public void deletefirstaddr() {
		spreeaccount.deleteFirstAddress();
	}
	
	@Given("User clicks on Add new address link on Spree account page")
	public void user_clicks_on_add_new_address_link_on_spree_account_page() {
	    spreeaccount.clickAddAddrLink();
	}
	@When("User enters address {string}{string}{string}{string}{string}{string}{string}{string}{string}{string} on Spree new address page")
	public void user_enters_address_on_spree_new_address_page(
			String label, String firstname, String lastname, String address1, String address2, String city, String state, String zipcode, 
			String country, String phone
		) {
		spreeaddress.fillForm(
			label, firstname, lastname, address1, address2, city, state, zipcode, 
			country, phone
		);
	}
	@When("User enters JSON{string} index {string} address on Spree new address page")
	public void user_enters_address_on_spree_new_address_page(
			String jsonfile, String addrindex
		) throws NumberFormatException, ParseException, FileNotFoundException, IOException {
		JSONReader_FileArrOneEle address = new JSONReader_FileArrOneEle(jsonfile, addrindex);
		spreeaddress.fillForm(
				address.getVal("label"), address.getVal("firstname"), address.getVal("lastname"),
				address.getVal("address1"), address.getVal("address2"), 
				address.getVal("city"), address.getVal("state"), address.getVal("zipcode"), 
				address.getVal("country"), address.getVal("phone")
		);
	}
	@When("User clears form")
	public void clearnewaddrform() throws InterruptedException {
		spreeaddress.clearForm();
	    
	}
	@When("User clicks button on Spree new address page")
	public void user_clicks_save_on_spree_new_address_page() {
	    spreeaddress.clickButton();
	}
	@Then("If the new address is a {string}, show address on Spree account page as {string}{string}{string}{string}{string}, otherwise clear form")
	public void addressformsubmitresult(String success, String label, String name, String address, String ctstzp, String country ) throws InterruptedException {
	    if (success.equals("Y")) {
		    spreeaccount.verifyHeader();
		    checkaddr(label, name, address, ctstzp, country);
	    }
	    else {
	    	clearnewaddrform();
	    }
	}
	@Then("If the new JSON{string} index {string} address is good, show address on Spree account page, otherwise clear form")
	public void addressformsubmitresult(String jsonfile, String addrindex ) throws InterruptedException, FileNotFoundException, IOException, ParseException {
		JSONReader_FileArrOneEle address = new JSONReader_FileArrOneEle(jsonfile, addrindex);
	    if (address.getVal("success").equals("Y")) {
		    spreeaccount.verifyHeader();
		    checkaddr(
		    		address.getVal("label"),
		    		address.getVal("firstname") + " " + address.getVal("lastname"),
		    		address.getVal("address1") + " " + address.getVal("address2") + ",",
		    		address.getVal("city") + ", " + address.getVal("st") + " " + address.getVal("zipcode") + ",",
		    		address.getVal("country"));
	    }
	    else {
	    	System.out.println("Gotta clear");
	    	clearnewaddrform();
	    }
	}
	
	@Then("First address displayed on account page is {string}{string}{string}{string}{string}")
	public void checkaddr(
			String label, String name, String address, String ctstzp, String country 
		) throws InterruptedException {
		spreeaccount.verifyAddressExists(label, name, address, ctstzp, country);
	}

	@Given("User clicks the first update address icon on Spree account page")
	public void user_clicks_the_first_update_address_icon_on_spree_account_page() {
		spreeaccount.editFirstAddress();
	}

	@Then("No addresses should show on Spree account page")
	public void no_addresses_should_show_on_spree_account_page() {
		my_account_page_should_display();
		Assert.assertFalse(spreeaccount.hasAtLeastOneDeleteBtn());
		
	}

}
