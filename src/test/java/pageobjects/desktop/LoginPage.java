package pageobjects.desktop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.BasePage;
import utilities.TestFrameworkException;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id="signin-link")
	WebElement loginLink;
	
	@FindBy(xpath="//input[contains(@name, 'username_or_email')]")
	WebElement txt_Username;
	
	@FindBy(xpath="//input[contains(@name, 'password')]")
	WebElement txt_Password;
	
	@FindBy(xpath="//input[@value='Log in']")
	WebElement btn_Login;
	
	public boolean doLogin(String username, String password) throws TestFrameworkException{
		try{
			pageWebDriverClient.click(loginLink);
			pageWebDriverClient.clearTextAndType(txt_Username, username);
			pageWebDriverClient.clearTextAndType(txt_Password, password);
			return pageWebDriverClient.click(btn_Login);
		}catch(Exception e){
			throw new TestFrameworkException("Unable to Login Successfully", e);
		}
	}

}
