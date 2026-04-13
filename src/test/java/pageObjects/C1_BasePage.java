package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class C1_BasePage {
	
	WebDriver driver;
	
	public C1_BasePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
