package shopPage;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import UIActions.uiActionMyAccountLogin;
import UIActions.uiActionShopPage;
import testBase.TestBase;

public class TC_ShopPage extends TestBase{

	public TC_ShopPage() throws IOException {
		super();
	}
	
	@BeforeClass
	public void setUp() throws IOException 
	{	
	  init();
      log.info(" -------------------  EXECUTION STARTED -------------------------------");
      log.info("1. Log-in with valid username and password. : Opened in chrome");
      log.info("2. Enter the URL http://practice.automationtesting.in ");     
    }
	
	@Test(enabled=false)
	public void filterByPrice()
	{
		try 
		{
			uiActionShopPage shop = new uiActionShopPage();
			shop.filterByPriceFunctionality();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test(enabled=false)
	public void productCategories()
	{
		try 
		{
			uiActionShopPage shop = new uiActionShopPage();
			shop.productCategoriesFunctionality();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test(enabled=false)
	public void defaultSorting()
	{
		try 
		{
			uiActionShopPage shop = new uiActionShopPage();
			shop.defaultSortingFunctionality();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test(enabled=false)
	public void readMore()
	{
		try 
		{
			uiActionShopPage shop = new uiActionShopPage();
			shop.readMoreFunctionality();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test(enabled=false)
	public void sale()
	{
		try 
		{
			uiActionShopPage shop = new uiActionShopPage();
			shop.saleFunctionality();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test(enabled=false)
	public void addAndViewBasketFunctionality()
	{
		try 
		{
			uiActionShopPage shop = new uiActionShopPage();
			shop.addAndViewBasket();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test(enabled=false)
	public void addAndViewBasketVerifyTaxFunctionality()
	{
		try 
		{
			uiActionShopPage shop = new uiActionShopPage();
			shop.addAndViewBasketViaItemLink();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void addAndViewBasketViaTextLinkFunctionality()
	{
		try 
		{
			uiActionShopPage shop = new uiActionShopPage();
			shop.addAndViewBasketVerifyTax();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void endTest()
	{
		//closeBrowser();
		log.info("---------------------EXECUTION ENDED -------------------------------------");
	}

}
