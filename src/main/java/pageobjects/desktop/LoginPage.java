package pageobjects.desktop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.BasePage;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id="signin-link")
	WebElement loginLink;
	
	@FindBy(xpath="//input[@class='js-username-field email-input js-initial-focus']")
	WebElement txt_Username;
	
	@FindBy(xpath="//input[@class='js-password-field']")
	WebElement txt_Password;
	
	@FindBy(xpath="//button[text()='Log in']")
	WebElement btn_Login;
	
	public boolean doLogin(String username, String password) throws Exception   {
		boolean bval = false;
		try{
			pageWebDriverClient.waitForVisibilityOfElement(txt_Username);
			pageWebDriverClient.clearTextAndType(txt_Username, username);
			pageWebDriverClient.clearTextAndType(txt_Password, password);
			bval = pageWebDriverClient.click(btn_Login);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return bval;
	}

}
