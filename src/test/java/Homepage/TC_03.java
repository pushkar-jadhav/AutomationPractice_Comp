package Homepage;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import testBase.TestBase;
import UIActions.uiActionHomepage;

public class TC_03 extends TestBase
{
	@BeforeClass
	public void setUp() throws IOException 
	{	
      init();
      log.info(" -------------------  EXECUTION STARTED -------------------------------");
      log.info("1. Open the browser : Opened in crome");
      log.info("2. Enter the URL http://practice.automationtesting.in ");
    }
	
	@Test
	public void verifyAaddToCart() 
	{
		try {
			uiActionHomepage Homepage = new uiActionHomepage(driver);
			Homepage.clickOnShopAndopenHome();
			Homepage.getArrivals();
			Homepage.ArrivalImageCheck();			
			getScreenShot("TC03_Success");
		} catch (Exception e) {
			e.printStackTrace();
			getScreenShot("TC03_Failure");
		}
	}
	
	@AfterClass
	public void endTest()
	{
		closeBrowser();
		log.info("---------------------EXECUTION ENDED -------------------------------------");
	}

}