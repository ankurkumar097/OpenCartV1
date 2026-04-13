package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;										// UI of the report
	public ExtentReports extent;													// populate common info on the report
	public ExtentTest test;															// creating test case entries in the report and update status of the test methods
	String repName;
	
	public void onStart(ITestContext testContext) {										// this method will execute only once after execution is started
																						// testContext is the menthod name which is captured by onStart method
		/*SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date();
		String currentDateTimeStamp = df.format(dt);
		*/
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());		// 25-28 in single line. it will generate timeStamp
		repName = "Test-Report-"+timestamp+".html";												// use the timeStamp in file name		
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+ repName);		//specify the location of the report, file name should be Dynamic	
		
		
		sparkReporter.config().setDocumentTitle("OpenCart Automation Report");				// Title of the Report/Tab
		sparkReporter.config().setReportName("OpenCart Functional Testing");					// Name of the report		
		sparkReporter.config().setTheme(Theme.DARK);							// Theme of web page
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Computer Name", "Local Host");						// Actual project these things will be User provided
		extent.setSystemInfo("Application Name", "openCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");				// getting parameter from the xml file from which the test is running
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");	// getting parameter from the xml file from which the test is running
		extent.setSystemInfo("Browser", browser);
		
		List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups();	// getting groups details from the xml file from which the test is running
		if(!includeGroups.isEmpty()) {														// list should not be empty
		extent.setSystemInfo("Groups", includeGroups.toString());
		}
	  }
	
	public void onTestSuccess(ITestResult result) {									// this method will execute when a method is passed
		test = extent.createTest(result.getTestClass().getName());					// create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());						// to display groups in report
		test.log(Status.PASS, result.getName()+" got successfullt executed");		// update status - pass, fail, skip
	  }
	
	public void onTestFailure(ITestResult result) {									// this method will execute when a method is failed
		test = extent.createTest(result.getTestClass().getName());					// create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());						// to display groups in report
		
		test.log(Status.FAIL, "Test cae FAILED is: "+ result.getName());			// update status - pass, fail, skip
		test.log(Status.INFO, result.getThrowable().getMessage());					// update Error message in the report - Exception
		
		
		// Get the ACTUAL test class instance (extends BaseTest)
	    Object testInstance = result.getInstance();
	    BaseClass baseTestInstance = (BaseClass) testInstance;  // Cast to your BaseTest
	    
	    try {
			String imgPath = baseTestInstance.captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
	    } catch (IOException e) {
			e.printStackTrace();
		}
	    
	    
		/*
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());		// calling captureScreen() method from base class and getting the name and storing it in String. 
																					//You can create a BaseClass object. And Make WebDriver as Static in BaseClass
			test.addScreenCaptureFromPath(imgPath);
		}catch(Exception e1) {
			e1.printStackTrace();
		}*/
	  }
	
	
	public void onTestSkipped(ITestResult result) {									// this method will execute when a method is skipped
		test = extent.createTest(result.getName());									// create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());						// to display groups in report
		test.log(Status.SKIP, "Test cae SKIPPED is: "+ result.getName());			// update status - pass, fail, skip
		test.log(Status.INFO, result.getThrowable().getMessage());
	  }
	
	public void onFinish(ITestContext context) {									// this method will execute only once after execution is finished
		extent.flush();
		
		// to open report automatically instead of manually
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		
		/*
		// to automatically send an email
		try {
			@SuppressWarnings("deprecation")
			URL url = new URL("file://"+System.getProperty("user.dir")+"\\reports\\"+repName);
			//Create an EMail message
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("ankurkumar097@gmail.com", "password"));
			email.setSSLOnConnect(true);
			email.setFrom("ankurkumar097@gmail.com");													//Sender
			email.setSubject("Test Results");
			email.setMsg("Please find attached Report...");
			email.addTo("xyz@gmail.com");																// receiver
			email.attach(url, "extent report", "please check report");
			email.send();
			
		}catch(Exception e3) {
			e3.printStackTrace();
		}
		*/
	  }
}
