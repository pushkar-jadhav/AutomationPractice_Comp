package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.apache.commons.io.FileUtils;

public class TestBase 
{
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	
	public static WebDriver driver;
	String browser = "chrome";
	String url= "http://practice.automationtesting.in/";
	public Properties properties;
	
	public TestBase() throws IOException
	{
		try {
			properties = new Properties();
			File file = new File("C:\\Users\\ajinkya.bhobad\\eclipse-workspace\\AutomationPractise\\src\\resources\\configFiles\\property.properties");
			FileInputStream fis = new FileInputStream(file); 
			properties.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {		
			e.printStackTrace();
		}
	}
	
	public void Selectbrowser(String browser) throws IOException
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\ajinkya.bhobad\\Desktop\\jars\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.firefox.driver", "C:\\Users\\ajinkya.bhobad\\Desktop\\jars\\geckodriver.exe");
			driver = new FirefoxDriver();
	    }
	}
	
	public void getURL()
	{
		driver.get(properties.getProperty("url"));
		log.info("2. Enter the URL http://practice.automationtesting.in ");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);	
	}
	
	public void getURLMyAccount()
	{
		driver.get(properties.getProperty("urlMyAccount"));
		log.info("2. http://practice.automationtesting.in/my-account/ ");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);	
	}
	
    public void init() throws IOException
    {    	
    	Selectbrowser(browser);
    	log.info("1. Open the browser : " +browser);
    	getURL();
    	log.info("2. Enter the URL " +url);
    	String log4jconfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jconfPath);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    
    public void initMyAccount() throws IOException
    {    	
    	Selectbrowser(browser);
    	log.info("1. Open the browser : " +browser);
    	getURLMyAccount();
    	log.info("2. Enter the URL " +url);
    	String log4jconfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jconfPath);	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    
    public void getScreenShot(String Name)   // ScreenShot
	{
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "\\src\\main\\java\\ScreenShot\\";
			File destFile = new File((String) reportDirectory +"_"+  Name + "_" + formater.format(calender.getTime()) + ".png");
			FileUtils.copyFile(src, destFile);
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "'height='100' width='100'/> </a>");
		} catch (IOException e) {
				e.printStackTrace();
		}     
	}
    
    public boolean waitForElement(WebDriver driver, int timeOutInSeconds, WebElement element) throws Exception {     // Wait for visibility
		//WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		//wait.until(ExpectedConditions.visibilityOf(element));
    	if (element != null) {
    		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
    		try {                
                wait.until(ExpectedConditions.visibilityOf(element));
                return true;
            } catch (Exception e) {
            	driver.navigate().refresh();
            	wait.until(ExpectedConditions.visibilityOf(element));
                return false;
            }
        } else
        	System.out.println("Failed to find Element : " +element);
        return false;
	}
    
    public void waitForStalenessOfElement(WebDriver driver, int timeOutInSeconds,WebElement element) {     // Wait for visibility
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.stalenessOf(element));  
	}
	
	public WebElement waitForElement(WebDriver driver, WebElement element, long timeOutInSeconds) {    // Wait for element clickability
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}
	
	public WebElement fluientWaitforElement(WebElement element, int timoutSec, int pollingSec) {

	    FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver).withTimeout(timoutSec, TimeUnit.SECONDS)
	    .pollingEvery(pollingSec, TimeUnit.SECONDS)
	    .ignoring(NoSuchElementException.class, TimeoutException.class);

	    for (int i = 0; i < 2; i++) {
	        try {
	            fWait.until(ExpectedConditions.visibilityOf(element));
	            fWait.until(ExpectedConditions.elementToBeClickable(element));
	        } 
	        catch (Exception e) {
	            System.out.println("Element Not found trying again - " + element.toString().substring(70));
	            e.printStackTrace();
	        }
	    }
	    return element;
	}
	
	public void waitForLoad(WebDriver driver) 
	{
	        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver driver)
	                    {
	                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	                    }
	                };
	        WebDriverWait wait = new WebDriverWait(driver, 30);
	        wait.until(pageLoadCondition);
	}			
	
	public static void mouseClickByLocator(String cssLocator,WebElement element ) {

	     WebElement elementoToClick = driver.findElement((By) element);
	     Actions builder = new Actions(driver);
	     builder.moveToElement(elementoToClick).click(elementoToClick);
	     builder.perform();
	}
	
	public static void safeJavaScriptClick(WebElement element) throws Exception {
		try {
			if (element.isEnabled() || element.isDisplayed()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			} else {
				driver.navigate().refresh();
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);	
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM "+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to click on element "+ e.getStackTrace());
		}
	}
	
	public static void safeJavaScriptScrollDown() throws Exception {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		} catch (Exception e) {
			e.printStackTrace();
		}		 
	}
	
	public static void safeJavaScriptScrollUp() throws Exception {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void safeJavaScriptScrollToElement(WebElement element) throws Exception {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public void closeBrowser() 
    {   
		driver.quit();	
	}
}
