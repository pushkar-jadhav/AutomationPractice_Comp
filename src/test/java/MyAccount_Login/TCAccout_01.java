package MyAccount_Login;

import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import UIActions.uiActionMyAccountLogin;
import testBase.TestBase;

public class TCAccout_01 extends TestBase {
	
	public TCAccout_01() throws IOException {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException 
	{	
	  initMyAccount();
      log.info(" -------------------  EXECUTION STARTED -------------------------------");
      log.info("1. Log-in with valid username and password. : Opened in chrome");
      log.info("2. Enter the URL http://practice.automationtesting.in ");     
    }
	
	@Test(enabled=false)
	public void verifyAaddToCart() 
	{
		try 
		{
			uiActionMyAccountLogin MyAccount = new uiActionMyAccountLogin(driver);
			MyAccount.validCredentials();
			//getScreenShot("TCAccout_01_Success");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			//getScreenShot("TCAccout_01_Failure");
		}
		
		log.info("Test Case: 1. Log-in with valid username and password.");
	}
		
	@Test(enabled=true)
	public void excelCheckCredentials()
	{
		try 
		{
			uiActionMyAccountLogin MyAccount = new uiActionMyAccountLogin(driver);
			MyAccount.credentialCheckExcel();
			//getScreenShot("TCAccout_01_Success");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			//getScreenShot("TCAccout_01_Failure");
		}
	}
	

	@AfterMethod
	public void endTest()
	{
		closeBrowser();
		log.info("---------------------EXECUTION ENDED -------------------------------------");
	}

}
