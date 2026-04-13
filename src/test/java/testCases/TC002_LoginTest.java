package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.C2_HomePage;
import pageObjects.C4_LoginPage;
import pageObjects.C5_MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups= {"Sanity", "Master"})
	public void verifyLogin() {
		
		logger.info("****  Starting TC002_LoginTest  ****");
		
		try {
		C2_HomePage hp = new C2_HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		C4_LoginPage lp = new C4_LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPass(p.getProperty("password"));
		lp.clickLogin();
		
		logger.info("Logged in...");
		
		C5_MyAccountPage macc = new C5_MyAccountPage(driver);
		boolean targetPage= macc.isMyAccouuntPageExist();
		logger.info("Heading Message found: "+ targetPage);
		
		Assert.assertTrue(targetPage);//Assert.assertEquals(targetPage, true, "Login failed");
		
		macc.clickLogout();
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("****  Finishing TC002_LoginTest  ****");
		
	}
}
