package testcases;

import java.net.MalformedURLException;

import org.testng.annotations.Test;

import core.BaseTest;
import core.DriverManagerFactory;

public class Sample extends BaseTest{
	
	
	@Test
	public void method1() throws MalformedURLException {
		DriverManagerFactory factory = new DriverManagerFactory();
		factory.initializeChromeWebDriver();
		
	}
	
	
}
