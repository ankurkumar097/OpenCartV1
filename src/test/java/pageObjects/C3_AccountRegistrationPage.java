package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class C3_AccountRegistrationPage extends C1_BasePage{

	public C3_AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstName;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txtLastName;
	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txtEmailConfirmPassword;
	@FindBy(xpath="//input[@name='agree']") WebElement chkpolicy;
	@FindBy(xpath="//input[@value = 'Continue']") WebElement btnContinue;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;
	
	public void setFirstName(String fName) {
		txtFirstName.sendKeys(fName);
	}
	
	public void setLastName(String lName) {
		txtLastName.sendKeys(lName);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String tel) {
		txtTelephone.sendKeys(tel);
	}
	
	public void setPassword(String pass) {
		txtEmailConfirmPassword.sendKeys(pass);
	}
	
	public void setConfirmPassword(String pass) {
		txtPassword.sendKeys(pass);
	}
	
	public void setPrivacyPolicy() {
		chkpolicy.click();
	}
	
	public void clickContinue() {
		//btnContinue.click();									//1
		//btnContinue.submit();									//2
		
		Actions act = new Actions(driver);					//3
		act.moveToElement(btnContinue).click().perform();
		
		//JavascriptExecutor js = (JavascriptExecutor) driver;	//4
		//js.executeScript("argument[0].click();", btnContinue);
		
		//btnContinue.sendKeys(Keys.RETURN);					//5
		
		//WebDriverWait exWait = new WebDriverWait(driver, Duration.ofSeconds(10));		//6
		//exWait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();		
	}
	
	public String getConfirmationMsg() {
		try {
		return msgConfirmation.getText();
		} catch(Exception e) {
			return e.getMessage();
		}
	}
	
}
