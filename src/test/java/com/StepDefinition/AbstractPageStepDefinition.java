package com.StepDefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AbstractPageStepDefinition {
	protected static WebDriver driver;
	protected WebDriver getDriver() {
		System.out.println("get driver");
		if (driver == null) {
			System.out.println("new");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
		}
		return driver;
	}
}
