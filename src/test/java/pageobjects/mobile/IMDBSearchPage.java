package pageobjects.mobile;

import org.openqa.selenium.WebDriver;

import core.BasePage;

public class IMDBSearchPage extends BasePage{

	public IMDBSearchPage(WebDriver driver) {
		super(driver);
	}

	
	public boolean setUrl(String url) throws Exception{
		boolean bval = false;
		try{
			pageWebDriverClient.setURL(url);
		}catch(Exception e){
			e.printStackTrace();
		}
		return bval;
	}
	

}
