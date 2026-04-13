package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class C4_LoginPage extends C1_BasePage {
	
	public C4_LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPass;
	@FindBy(xpath="//input[@value='Login']") WebElement lnkClick;
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);	
	}
	
	public void setPass(String pass) {
		txtPass.sendKeys(pass);	
	}
	
	public void clickLogin() {
		lnkClick.click();
	}
	
}
