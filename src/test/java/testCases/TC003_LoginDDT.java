package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.C2_HomePage;
import pageObjects.C4_LoginPage;
import pageObjects.C5_MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass = DataProviders.class, groups="Datadrivern")		// data provider class is in another package(utilities.DataProviders.java)
	public void verify_loginDDT(String email, String pass, String expRes) {
		
		logger.info("****  Starting TC003_LoginDDT  ****");
		try {
		//Home Page
		C2_HomePage hp = new C2_HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		logger.info("Login Page reached");
		//Login
		C4_LoginPage lp = new C4_LoginPage(driver);
		lp.setEmail(email);									// get the data from Data Provider
		lp.setPass(pass);									// get the data from Data Provider
		lp.clickLogin();
		logger.info("Entered User email and Pass...");
		
		//My Account
		C5_MyAccountPage macc = new C5_MyAccountPage(driver);
		boolean targetPage= macc.isMyAccouuntPageExist();
		
		
		
		/* VALIDATIONS
		 *  Data is Valid - Login Successful - Test Pass - logout
		 * Data is invalid - Login failed - Test fail
		 * 
		 * Data is invalid - Login Successful - Test fail - logout
		 * Data is invalid - Login failed - Test Pass
		 */
		
		if(expRes.equalsIgnoreCase("Valid")) {
			if(targetPage==true) {
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
		}
		if(expRes.equalsIgnoreCase("Invalid")) {
			if(targetPage==true) {
				macc.clickLogout();
				Assert.assertTrue(false);
			}else {
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e) {
			Assert.assertTrue(false);
		}
		logger.info("****  Finish TC003_LoginDDT  ****");
	}
}
