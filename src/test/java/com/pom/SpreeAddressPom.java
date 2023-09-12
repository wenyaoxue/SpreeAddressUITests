package com.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SpreeAddressPom {
	private WebDriver driver;
	
	public SpreeAddressPom(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "address[label]")
	WebElement label;
	@FindBy(name = "address[firstname]")
	WebElement firstname;
	@FindBy(name = "address[lastname]")
	WebElement lastname;
	@FindBy(name = "address[address1]")
	WebElement address1;
	@FindBy(name = "address[address2]")
	WebElement address2;
	@FindBy(name = "address[city]")
	WebElement city;
	@FindBy(name = "address[state_id]")
	WebElement state;
	@FindBy(name = "address[zipcode]")
	WebElement zipcode;
	@FindBy(name = "address[country_id]")
	WebElement country;
	@FindBy(name = "address[phone]")
	WebElement phone;
	
	@FindBy(name = "commit")
	WebElement button;
	
	public void fillForm(
			String label, String firstname, String lastname, String address1, String address2, String city, String state, String zipcode, 
			String country, String phone
		) {
		this.label.sendKeys(label);
		this.firstname.sendKeys(firstname);
		this.lastname.sendKeys(lastname);
		this.address1.sendKeys(address1);
		this.address2.sendKeys(address2);
		this.city.sendKeys(city);
		(new Select( this.state)).selectByVisibleText(state);
		this.zipcode.sendKeys(zipcode);
		(new Select( this.country)).selectByVisibleText(country);
		this.phone.sendKeys(phone);
	}
	public void clearForm() throws InterruptedException {
		Thread.sleep(2000);
		label.clear();
		firstname.clear();
		lastname.clear();
		address1.clear();
		address2.clear();
		city.clear();
		zipcode.clear();
		phone.clear();
	}
	
	public void clickButton() {
		button.click();
	}
}
