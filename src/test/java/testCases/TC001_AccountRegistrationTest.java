package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.C2_HomePage;
import pageObjects.C3_AccountRegistrationPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups= {"Regression", "Master"})
	public void verify_account_registration() {
		logger.info("****  Starting TC001_AccountRegistrationTest  ****");
		
		try {
		C2_HomePage hp = new C2_HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on my Account link...");
		hp.clickRegister();
		logger.info("Clicked on my Register link...");
		
		C3_AccountRegistrationPage rp = new C3_AccountRegistrationPage(driver);
		
		logger.info("Providing customer details...");
		rp.setFirstName(randomString().toUpperCase());
		rp.setLastName(randomString().toUpperCase());
		rp.setEmail(randomAlphanumeric() +"@gmail.com");
		rp.setTelephone(randomNumber());
		
		String pass = randomAlphanumeric();
		rp.setPassword(pass);
		rp.setConfirmPassword(pass);
		
		rp.setPrivacyPolicy();
		rp.clickContinue();
		
		logger.info("Validating Expected message...");
		Assert.assertEquals(rp.getConfirmationMsg(), "Your Account Has Been Created!");
		}
		catch(Exception e) {
			logger.error("Test Failed....");
			logger.debug("Debug logs....");
			Assert.fail();
		}
		logger.info("****  Finished TC001_AccountRegistrationTest  ****");
	}
}
