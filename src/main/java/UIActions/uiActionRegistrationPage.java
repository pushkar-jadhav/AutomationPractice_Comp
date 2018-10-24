package UIActions;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testBase.TestBase;
import utility.ExcelUtility;

public class uiActionRegistrationPage extends TestBase
{
	public static final Logger log = Logger.getLogger(uiActionHomepage.class.getName());	
	
	uiActionMyAccountLogin myAccount;
	
	//Locators
	@FindBy(xpath="//*[@id=\"reg_email\"]")
	static
	WebElement emailAddress;
	
	@FindBy(xpath="//*[@id=\"reg_password\"]")
	static
	WebElement registrationPassword;
	
	@FindBy(xpath="//*[@id=\"reg_password\"]")
	static
	WebElement registorButton;
	
	//Constructor
	public uiActionRegistrationPage(WebDriver driver) throws IOException {
		super();
		PageFactory.initElements(driver, this);
		myAccount = new uiActionMyAccountLogin(driver); 
	}

	//Methods
	public void registrationCredentialVerification(){
		
		String path = "C:\\\\Users\\\\ajinkya.bhobad\\\\eclipse-workspace\\\\AutomationPractise\\\\src\\\\resources\\\\data\\\\ExcelFile.xls";
		ExcelUtility excel = new ExcelUtility(path);
		int row = excel.getRowCount("cred");
		
		myAccount.myAccountMenuButton.click();
		emailAddress.clear();
		emailAddress.sendKeys("aj13blu@gmail.com");
		registrationPassword.clear();
		registrationPassword.sendKeys("@jinkyA99");
		registorButton.click();
	}
	
	@Test(dataProvider="RegistrationCredentials")
	public void test() throws IOException
	{
				uiActionRegistrationPage registration = new uiActionRegistrationPage(driver);
				registration.registrationCredentialVerification();
	}
	
	@DataProvider(name = "RegistrationCredentials")
	  public static void credentials() {
		 String path = "C:\\Users\\ajinkya.bhobad\\eclipse-workspace\\AutomationPractise\\src\\resources\\data\\RegistrationData.xls";
			ExcelUtility excel = new ExcelUtility(path);
			int row = excel.getRowCount("Regcred");
			int column = excel.getColumnCount("Regcred");
			  
			for (int i=2;i<=row;i++){
				
				try {
					log.info(excel.getCellData("cred",0,i));

					String emailAddressText=excel.getCellData("cred",1,i);
					//waitForElement(driver, userName , 4);
					emailAddress.sendKeys(emailAddressText);
					
					String Pwd=excel.getCellData("cred",2,i);
					//waitForElement(driver, 4, password);
					registrationPassword.sendKeys(Pwd);
					
					registorButton.click();
					//waitForLoad(driver);
					
					String validationText=excel.getCellData("cred",3,i);
					//System.out.println("Excel Text - "+validationText);
					} catch (Exception e) {
						e.printStackTrace();					
					}
	        	}
						
	  }
	
}
