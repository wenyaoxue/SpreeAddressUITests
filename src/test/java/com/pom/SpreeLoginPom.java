package com.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;

public class SpreeLoginPom {
	private WebDriver driver;
	
	public SpreeLoginPom(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h3[text()='Log in to continue']")
	WebElement header;
	
	@FindBy(name = "spree_user[email]")
	WebElement email;
	@FindBy(name = "spree_user[password]")
	WebElement password;

	@FindBy(name = "commit")
	WebElement login;
	
	public void visit() {
	    driver.get("http://demo.spreecommerce.org/login");
	}
	@SuppressWarnings("deprecation")
	public void verifyHeader() {
		Assert.assertTrue(header.isDisplayed());
	}
	public void enterCredentials(String em, String pw) {
		email.sendKeys(em);
		password.sendKeys(pw);
	}
	public void clickLogin() {
		login.click();
	}
}
