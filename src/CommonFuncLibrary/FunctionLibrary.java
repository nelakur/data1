package CommonFuncLibrary;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.PropertyFileUtil;

public class FunctionLibrary 
{

	public static WebDriver startBrowser(WebDriver driver) throws Exception
	{
		
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("Firefox"))
		{
			driver= new FirefoxDriver();
			
		}
		else
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", "CommonJarFiles/IEDriverServer.exe");
			driver=new InternetExplorerDriver();
			
		}
		else
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "CommonJarFiles/chromedriver.exe");
			driver= new ChromeDriver();
		}
				
		return driver;		
	}
	
	public static void openApplication(WebDriver driver) throws Exception
	{
		driver.manage().window().maximize();
		driver.get(PropertyFileUtil.getValueForKey("URL"));
	}
	
	public static void clickAction(WebDriver driver,String locatorType,String locatorValue)
	{
		if(locatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorValue)).click();
		}
		else
			if(locatorType.equalsIgnoreCase("name"))
			{
				driver.findElement(By.name(locatorValue)).click();
			}
			else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					driver.findElement(By.xpath(locatorValue)).click();
				}
	}
	
	public static void typeAction(WebDriver driver,String locatorType,String locatorValue,String data)
	{
		if(locatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorValue)).clear();
			driver.findElement(By.id(locatorValue)).sendKeys(data);
		}
		else
			if(locatorType.equalsIgnoreCase("name"))
			{
				driver.findElement(By.name(locatorValue)).clear();
				driver.findElement(By.name(locatorValue)).sendKeys(data);
			}
			else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					driver.findElement(By.xpath(locatorValue)).clear();
					driver.findElement(By.xpath(locatorValue)).sendKeys(data);
				}
	}
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
		
	}
	public static void waitForElement(WebDriver driver,String locatorType,String locatorValue,String waitTime)
	{
		WebDriverWait wait=new WebDriverWait(driver,Integer.parseInt(waitTime));
		if(locatorType.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
		}
		else
			if(locatorType.equalsIgnoreCase("name"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
				
			}
			else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
					
				}
	}
	
	public static void titleValidation(WebDriver driver, String validData)
	{
		String act_title=driver.getTitle();
		String exp_title=validData;
		Assert.assertEquals(act_title, exp_title);
	}
	
	public static String getRandomNumberFromDate()
	{

		Date date= new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("YYYY_MM_dd_ss");

		return sdf.format(date);
		
	}
	
	public static void getData(WebDriver driver, String locatorValue) throws Exception
	{
		String data=driver.findElement(By.xpath(locatorValue)).getAttribute("value");
		FileWriter fw=new FileWriter("./CapturedData/Data.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(data);
		bw.flush();
		bw.close();
	}
	
	public static void tableValidation(WebDriver driver, String reqNum) throws Exception
	{
		FileReader fr = new FileReader("./CapturedData/Data.txt");
		BufferedReader br=new BufferedReader(fr);
		String exp_data=br.readLine();
		int reqNum1=Integer.parseInt(reqNum);
		if(driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).isDisplayed())
		{
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).clear();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).sendKeys(exp_data);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.btn"))).click();
			
		}
		else
		{
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.option"))).click();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).clear();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).sendKeys(exp_data);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.btn"))).click();
			
		}
	}
	
	public static void mouseOver(WebDriver driver, String LocatorValue)
	{
		Actions act= new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath(LocatorValue)));
		act.build().perform();
	}
	
	public static void framesElement(WebDriver driver,String LocatorType, String LocatorValue)
	{
		if(LocatorType.equalsIgnoreCase("id"))
		{
			WebElement frameElement=driver.findElement(By.id(LocatorValue));
			driver.switchTo().frame(frameElement);
		}
		else
			if(LocatorType.equalsIgnoreCase("name"))
			{
				WebElement frameElement = driver.findElement(By.name(LocatorValue));
				driver.switchTo().frame(frameElement);
			}
			else
				if(LocatorType.equalsIgnoreCase("xpath"))
				{
					WebElement frameElement = driver.findElement(By.xpath(LocatorValue));
					driver.switchTo().frame(frameElement);
				}
				else
					if(LocatorType.equalsIgnoreCase("className"))
					{
						WebElement frameElement = driver.findElement(By.className(LocatorValue));
						driver.switchTo().frame(frameElement);
					}
		
		
	}
	public static void switchToAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	
		
}
