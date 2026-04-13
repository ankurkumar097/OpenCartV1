package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class C5_MyAccountPage extends C1_BasePage{

	public C5_MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement msgHeading;
	@FindBy(linkText="Logout") WebElement lnkLogout;
	
	public boolean isMyAccouuntPageExist() {
		try {
		return msgHeading.isDisplayed();
		} 
		catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		lnkLogout.click();
	}
}
