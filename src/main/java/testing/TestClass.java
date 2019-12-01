package testing;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestClass {

	
	ExtentReports extent = new ExtentReports();
	ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output//extenttestReport.html");
	
	@Test
	public void method1() {
		
		ExtentTest test = extent.createTest("Validate Gmail", "Check that the application is up");		
		try
		{
			System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
	    	WebDriver driver = new ChromeDriver();
	    	driver.get("https://www.gmail.com");
	    	
	    	Thread.sleep(5000);
	    	
	    	TakesScreenshot tsc = (TakesScreenshot) driver;
	    	File src = tsc.getScreenshotAs(OutputType.FILE);
	    	File dest = new File("screenshot//ValidateGmail1.png");
	    	FileUtils.copyFile(src, dest);
	    	
	    	System.out.println("Opened Gmail");
	    	Reporter.log("Opened Gmail");
	    	test.addScreenCaptureFromPath(dest.getAbsolutePath(),"Gmail");
	    	
	    	driver.quit();
	    	test.pass("Gmail is up");
	    	
		}catch(Exception e) {
			test.fail("Gmail is up");
		}
    	
    	extent.attachReporter(htmlReporter);
    	extent.flush();
    	
	}
	
	
	
	@Test
	public void method2() {
		
		ExtentTest test = extent.createTest("Validate facebook", "Check that the application is up");		
		try
		{
			System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
	    	WebDriver driver = new ChromeDriver();
	    	driver.get("https://www.facebook.com");
	    	
	    	
	    	//Mouse movement
	    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.linkText("Help")));
	    	
	    	Thread.sleep(5000);
	    	
	    	WebElement element = driver.findElement(By.id("email"));
	    	Actions actions = new Actions(driver);
	    	actions.moveToElement(element);
	    	actions.perform();
	    	
	    	Thread.sleep(5000);
	    	
	    	ExtentTest testtitle = extent.createTest("Validate facebook", "Check that the application is up");
	    	String txt = driver.getTitle();
	    	
	    	if(txt.contains("facebook")) {
	    		testtitle.pass("Title is valid");
	    	}else {
	    		testtitle.fail("Title is invalid");
	    	}
	    	
	    	System.out.println("Opened facebook");
	    	Reporter.log("Opened facebook");
	    	
	    	driver.quit();
	    	test.pass("facebook is up");
	    	
		}catch(Exception e) {
			test.fail("facebook is up");
		}
    	
    	extent.attachReporter(htmlReporter);
    	extent.flush();
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
	}
	
	
}
