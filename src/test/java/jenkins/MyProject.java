package jenkins;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MyProject 
{
	WebDriver driver;
	 ExtentHtmlReporter htmlReporter;
	   ExtentReports extent;
	   ExtentTest test;
  @BeforeSuite
  public void openbrowser()
  {
	   System.setProperty("webdriver.chrome.driver","C:\\Users\\hp\\Desktop\\selenium\\chromedriver6\\chromedriver.exe");
	   driver=new ChromeDriver();
	   
  }
  @BeforeTest
  public void openurl()
  {
	   htmlReporter =new ExtentHtmlReporter("extent.html");
	   htmlReporter.config().setDocumentTitle("Automation report");
	   htmlReporter.config().setReportName("login functionality report");
	   htmlReporter.config().setTheme(Theme.DARK);
	   extent =new ExtentReports();
	   extent.attachReporter(htmlReporter);
	   test =extent.createTest("testng test ");
	  driver.get("https://www.mercurytravels.co.in/");
	  
  }
  @BeforeClass
  public void windowmaximize() throws InterruptedException
  {
	   driver.manage().window().maximize();
	   Thread.sleep(2000);
	   
  }
  @BeforeMethod
  public void deletecookies()
  {
	   driver.manage().deleteAllCookies();
  }
  @Test
  public void logintest() throws InterruptedException
  {
	   Actions act=new Actions(driver);
	   act.moveToElement(driver.findElement(By.xpath("(//a[@class='dropdown-toggle'])[2]"))).build().perform();
	   test.info("dropdown checked");
	   driver.findElement(By.xpath("(//a[@data-toggle='modal'])[3]")).click();
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("//input[@id='sign_user_email']")).sendKeys("tandalemahesh0144@gmail.com");
	   driver.findElement(By.xpath("//input[@id='sign_user_password']")).sendKeys("Amu@4321");
	   driver.findElement(By.xpath("//button[@class='btn btn-lg btn-primary modal-btn ajax-submit'][normalize-space()='Log in']")).click();
	   test.info("login successful");
	   Thread.sleep(2000);
			   
  }
  @Test(dependsOnMethods= {"logintest"})
  public void logouttest() throws InterruptedException
  {
	   Actions act=new Actions(driver);
	   act.moveToElement(driver.findElement(By.xpath("(//a[@class='dropdown-toggle'])[4]"))).build().perform();
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("(//a[contains(text(),'Log Out')])[2]")).click();
	   test.info("logout successful");
	   Thread.sleep(2000);
			   
  }
  @AfterMethod
  public void screenshot() throws IOException
  {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFileToDirectory(src, new File("C:\\Users\\hp\\Desktop\\selenium\\jenkinintegration\\screenshot"));
	   
  }
  @AfterClass
	public void deleteallcookies()
	{
		System.out.println("dlt cookies");
	}
	
	
	@AfterTest
	public void closedbconnection()
	{
		
		extent.flush();
		System.out.println("close db connection");
	}
	
	
	
	@AfterSuite
	public void closebrowser()
	{
		System.out.println("close browser");
		driver.close();
	}
  
}
