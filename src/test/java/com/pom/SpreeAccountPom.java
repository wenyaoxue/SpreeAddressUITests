package com.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import junit.framework.Assert;
public class SpreeAccountPom {
	private WebDriver driver;
	
	public SpreeAccountPom(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h3[text()='My Account']")
	WebElement header;
	
	@FindBy(id = "account-button")
	WebElement accounticon;
	//can't find logout?

	@FindBy(xpath = "//address/h4")
	List<WebElement> addrlabels;
	@FindBy(xpath = "//address/span")
	List<WebElement> addrnames;
	@FindBy(xpath = "//address/div[1]")
	List<WebElement> addraddrs;
	@FindBy(xpath = "//address/div[2]")
	List<WebElement> addrareas;
	@FindBy(xpath = "//address/div[3]")
	List<WebElement> addrcountries;
	
	
	@FindBy(xpath = "//a[contains(@class, 'js-delete-address-link')]")
	List<WebElement> deleteaddrbtns; 
	@FindBy(id = "delete-address-popup-confirm")
	WebElement deleteconfirmbtn;
	
	@FindBy(css = "a[data-hook='edit_address']")
	List<WebElement> editaddrbtns; 
	
	@FindBy(linkText = "Add new address")
	WebElement addaddrlink;
	
	@SuppressWarnings("deprecation")
	public void verifyHeader() {
		Assert.assertTrue(header.isDisplayed());
	}
	public void logout() {
	    accounticon.click();
//	    driver.findElement(By.linkText("LOGOUT")).click();
//	    driver.findElement(By.xpath("//a[text()='LOGOUT']")).click();
//	    driver.findElement(By.xpath("//*[@id='link-to-account']/a[2]")).click();
	    driver.get("http://demo.spreecommerce.org/logout");
	}
	
	public boolean hasAtLeastOneDeleteBtn() {
		return deleteaddrbtns.size() > 0;
	}
	public void deleteFirstAddress() {
		deleteaddrbtns.get(0).click();
		deleteconfirmbtn.click();
	}
	public void clickAddAddrLink() {
		addaddrlink.click();
	}
	public void editFirstAddress() {
		editaddrbtns.get(0).click();
	}
	private String getTrimmedText(WebElement ele) { return ele.getText().trim(); }
	
	public void verifyAddressExists(
			String label, String name, String address, String ctstzp, String country 
		) throws InterruptedException {
		
		Thread.sleep(1000);
		for (int i = 0; i < addrnames.size(); i++)
			if  (
				(!label.equals("") && getTrimmedText(addrlabels.get(i)).equals(label))
				&& getTrimmedText(addrnames.get(i)).equals(name)
				&& getTrimmedText(addraddrs.get(i)).equals(address)
				&& getTrimmedText(addrareas.get(i)).equals(ctstzp)
				&& getTrimmedText(addrcountries.get(i)).equals(country)
				)
				return;
		Assert.assertEquals("given address", "addresses on page");
	}
}
