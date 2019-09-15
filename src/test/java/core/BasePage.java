	package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utilities.WebDriverClient;

public class BasePage {
	public WebDriverClient pageWebDriverClient = null;
	DriverManagerFactory driverManagerFactory = null;
	protected AppiumDriver appiumDriver = null;
	protected WebDriver driver = null;
	public String currentURL = "";
	
	public BasePage(WebDriver driver) {
        this.driver = driver;
		PageFactory.initElements(driver, this);
        pageWebDriverClient = new WebDriverClient(driver);
    }
	
	//So that we can utilize appium driver method
	public BasePage(AppiumDriver appiumDriver) {
		//BasePage constructor for appium driver
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
        pageWebDriverClient = new WebDriverClient(appiumDriver);
    }
	
}