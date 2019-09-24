package core;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;

public class DriverManagerFactory {

	private static ThreadLocal<AppiumDriver> threadAppiumDriver = new ThreadLocal<AppiumDriver>();
	private static ThreadLocal<RemoteWebDriver> threadDesktopWebDriver = new ThreadLocal<RemoteWebDriver>();
//	String hub = System.getProperty("hub");
	String hub="http://localhost:4444/wd/hub";
	

	public AppiumDriver getAppiumDriver() {
		AppiumDriver wdriver = threadAppiumDriver.get();
		return wdriver;
	}
	
	public WebDriver getDesktopWebDriver() {
		WebDriver wdriver = threadDesktopWebDriver.get();
		return wdriver;
	}
	
	public static void setAppiumDriver(AppiumDriver driver) {
		threadAppiumDriver.set(driver);
	}
	
	public static void setDesktopWebDriver(RemoteWebDriver driver) {
		threadDesktopWebDriver.set(driver);
	}
	
	public void initializeDriver(String platform) throws MalformedURLException {
		if(platform.equals("desktop")) {
			initializeChromeWebDriver();
		} else if(platform.equals("mobile")) {
			initializeMobileDriver();
		}
	}
	
	public Object getDriver(String platform) throws MalformedURLException {
		if(platform.equals("desktop")) {
			return getDesktopWebDriver();
		} else if(platform.equals("mobile")) {
			return getAppiumDriver();
		}
		return null;
	}
	
	public void initializeChromeWebDriver() throws MalformedURLException {
		 DesiredCapabilities capabilities = new DesiredCapabilities();
		 ChromeOptions options = new ChromeOptions();
	     capabilities.setCapability("platformName", "WINDOWS");
		options.addArguments("start-maximized");
		options.addArguments("--disable-infobars");
		options.addArguments("--dns-prefetch-disable");
		options.merge(capabilities);
		setDesktopWebDriver(new RemoteWebDriver(new URL(hub), options));
	}
	
	public void initializeMobileDriver() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
	       capabilities.setCapability("deviceName", "JKC device");
	       // capabilities.setCapability("deviceName", "192.168.0.27:5000");
//	        capabilities.setCapability("platformVersion", "9");
	        capabilities.setCapability("platformName", "Android");
	        capabilities.setCapability("appPackage", "com.google.android.youtube");
	        capabilities.setCapability("appActivity", "com.google.android.apps.youtube.app.WatchWhileActivity");
			setAppiumDriver(new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities));	
	}
	
	public void closeAllDriver() {
		 if(getAppiumDriver()!=null) {
			getAppiumDriver().quit();
		}
		if (getDesktopWebDriver()!=null)  {
			getDesktopWebDriver().quit();
		}
		
	}
	}
