package MyAccount;

import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import UIActions.uiActionMyAccountLogin;
import UIActions.uiActionRegistrationPage;
import UIActions.uiActionShopPage;
import testBase.TestBase;
import utility.ExcelUtility;

public class TC_MyAccount extends TestBase{

	public TC_MyAccount() throws IOException {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException 
	{	
	  init();
      log.info(" -------------------  EXECUTION STARTED -------------------------------");
      log.info("1. Log-in with valid username and password. : Opened in chrome");
      log.info("2. Enter the URL http://practice.automationtesting.in ");
	}
	
	/*@Test
	public void dashboardFunctionality() throws IOException {
		try 
		{
			uiActionMyAccountLogin myAccount = new uiActionMyAccountLogin(driver);
			myAccount.dashBoard();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void ordersFlow1Functionality() throws IOException {
		try 
		{
			uiActionMyAccountLogin myAccount = new uiActionMyAccountLogin(driver);
			myAccount.ordersFlow1();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void ordersFlow2Functionality() throws IOException {
		try 
		{
			uiActionMyAccountLogin myAccount = new uiActionMyAccountLogin(driver);
			myAccount.ordersFlow2();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void ordersFlow3Functionality() throws IOException {
		try 
		{
			uiActionMyAccountLogin myAccount = new uiActionMyAccountLogin(driver);
			myAccount.ordersFlow3();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void addressFlow1Functionality() throws IOException {
		try 
		{
			uiActionMyAccountLogin myAccount = new uiActionMyAccountLogin(driver);
			myAccount.addressFlow1();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void addressFlow2Functionality() throws IOException {
		try 
		{
			uiActionMyAccountLogin myAccount = new uiActionMyAccountLogin(driver);
			myAccount.addressFlow2();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}*/
	
	@Test(enabled=false)
	public void accountDetailsFunctionality() throws IOException {
		try 
		{
			uiActionMyAccountLogin myAccount = new uiActionMyAccountLogin(driver);
			myAccount.accountDetails();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void logOutFunctionality() throws IOException {
		try 
		{
			uiActionMyAccountLogin myAccount = new uiActionMyAccountLogin(driver);
			myAccount.accountDetails();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void endTest()
	{
		closeBrowser();
		log.info("---------------------EXECUTION ENDED -------------------------------------");
	}
}
