package UIActions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import testBase.TestBase;

public class uiActionShopPage extends TestBase{

	public static final Logger log = Logger.getLogger(uiActionHomepage.class.getName());
	uiActionsCheckoutPage checkoutPage;
	
	//locator
	@FindBy(xpath="//*[@id=\"menu-item-40\"]/a")
	WebElement shopButton;
	
	@FindBy(xpath="//*[@id=\"min_price\"]")
	WebElement filterByPriceMin;
	
	@FindBy(xpath="//*[@id=\"max_price\"]")
	WebElement filterByPriceMax;
	
	@FindBy(xpath="//*[@id=\"woocommerce_price_filter-2\"]/form/div/div[2]/button")
	WebElement filterButton;
	
	@FindBy(xpath="//*[@id='content']/ul/li")
	List<WebElement> contentCount;
	
	@FindBy(xpath="//*[@id=\"woocommerce_price_filter-2\"]/form/div/div[2]/button")
	WebElement filterButtonx;
	
	@FindBy(xpath="//*[@id='content']/ul/li[1]/a[1]")
	WebElement firstProduct;
	
	@FindBy(xpath="//*[@id=\"product-169\"]/div[2]/h1")
	WebElement firstProductView;
	
	@FindBy(xpath="//*[@id='content']/form/select")
	WebElement defaultSortingDropDown;
	
	@FindBy(xpath="//*[@id='content']/form/select/option[@selected='selected']")
	WebElement verifyDropDownOptions;
	
	@FindBy(xpath="//*[@id=\"content\"]/ul/li[8]/a[2]")
	WebElement readMore;
	
	@FindBy(xpath="//*[@id=\"product-163\"]/div[2]/p")
	WebElement outOfStock;
	
	@FindBy(xpath="//span[contains(text(),'Sale!')]")
	List<WebElement> saleProduct;
	
	@FindBy(xpath="//*[@id=\"product-169\"]/div[2]/div[1]/p/del/span")
	WebElement saleDeletedPrice;
	
	@FindBy(xpath="//*[@id=\"product-163\"]/div[2]/h1")
	WebElement productTitle;
	
	@FindBy(xpath="//ul[contains(@class,'products')]/li[1]//a[contains(text(),'Add to basket')]")
	WebElement addToBasket;
	
	@FindBy(xpath="//ul[contains(@class,'products')]/li[1]//a[contains(text(),'View Basket')]")
	WebElement viewBasket;
	
	@FindBy(xpath="//tr[contains(@class,'cart_item')]//td[3]/a")
	WebElement productShopText;
	
	@FindBy(xpath="//table[@class='shop_table shop_table_responsive']//tr[3]/th")
	WebElement total;
	
	@FindBy(xpath="//table[@class='shop_table shop_table_responsive']//tr[1]/th")
	WebElement subTotal;
	
	@FindBy(xpath="//table[@class='shop_table shop_table_responsive']//tr[3]/td/strong/span")
	WebElement totalPrice;
	
	@FindBy(xpath="//table[@class='shop_table shop_table_responsive']//tr[1]/td/span")
	WebElement subTotalPrice;
	
	@FindBy(xpath="//a[contains(text(),'Proceed to Checkout')]")
	WebElement proceedToCheckoutButton;
	
	@FindBy(xpath="//*[@id='wpmenucartli']/a")
	WebElement itemLink;
	
	
	public uiActionShopPage() throws IOException {
		super();
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);  //this - refers current class objects
		checkoutPage = new uiActionsCheckoutPage();
	}

	public void filterByPriceFunctionality() throws Exception
	{
		shopButton.click();
		waitForElement(driver, 4, shopButton);
		log.info("3. Click on Shop Menu");
		filterByPriceMin.clear();
		filterByPriceMin.sendKeys("150");
		filterByPriceMax.sendKeys("450");
		log.info("4. Adjust the filter by price between 150 to 450 rps");
		filterButton.click();
		log.info("5. Now click on Filter button");
		waitForLoad(driver);
		
		for(int i=0;i<=contentCount.size();i++)
		{
			List<WebElement> productPrice = driver.findElements(By.xpath("//*[@id=\"content\"]/ul/li["+i+"]/a[1]/span[2]/ins/span | //*[@id=\"content\"]/ul/li["+i+"]/a[1]/span/span"));
			for(WebElement prdprice:productPrice) {
				String priceText = prdprice.getAttribute("innerText");
				
				StringBuffer newPrice=new StringBuffer(priceText);
				newPrice.delete(newPrice.length()-3, newPrice.length()).deleteCharAt(0);
				int price = Integer.parseInt(newPrice.toString());
				
				if(price>=150 && price<=450)
					Assert.assertTrue(true);
				else
					Assert.assertTrue(false);
		log.info("6. User can view books only between 150 to 450 rps price");
			}
		}			
	}
	
	public void productCategoriesFunctionality() throws Exception {
		shopButton.click();
		waitForElement(driver, 4, shopButton);
		log.info("3. Click on Shop Menu");
		firstProduct.click();
		log.info("4. Click any of the product links available in the product category");
		Assert.assertEquals(firstProductView.getText(), "Android Quick Start Guide");
		log.info("5. Now user can view only that particular product");
	}
	
	public void defaultSortingFunctionality() throws InterruptedException {
		waitForElement(driver, shopButton, 5);
		shopButton.click();
		log.info("3. Click on Shop Menu");
		
		Select defaultSorting = new Select(defaultSortingDropDown);
		List <WebElement> elementCount = defaultSorting.getOptions();
		
		for(int i=1;i<elementCount.size();i++) {
			String verifyDropDownText = verifyDropDownOptions.getText();
			WebElement dropDownOptions = driver.findElement(By.xpath("//*[@id='content']/form/select/option["+i+"]"));
			defaultSorting.selectByIndex(i);
			waitForLoad(driver);
			
			try {
				System.out.println(dropDownOptions.getText());
			} catch (Exception e) {
				dropDownOptions = driver.findElement(By.xpath("//*[@id='content']/form/select/option["+i+"]"));
			}

			String dropDownTexts = dropDownOptions.getText();		
			System.out.println(verifyDropDownText);
			
			log.info("4. Click on ----> "+dropDownTexts+" item in Default sorting dropdown");	
			Assert.assertEquals(dropDownTexts, verifyDropDownText);
			log.info("5. Now user can view the ----> "+verifyDropDownText+" only");			
		}
	}
	
	public void readMoreFunctionality() throws Exception {
		safeJavaScriptClick(shopButton);
		//shopButton.click();
		waitForLoad(driver);
		log.info("3. Click on Shop Menu");
		
		readMore.click();
		waitForLoad(driver);
		log.info("4. Click on read more button in home page ");
		
		String outOfStocktext = outOfStock.getText();
		Assert.assertEquals(outOfStocktext, "Out of stock");
		log.info("5. Read More option indicates the Out Of Stock.");
		log.info("6. User cannot add the product which has read more option as it was out of stock.");
	}
	
	public void saleFunctionality() throws Exception {

		safeJavaScriptClick(shopButton);
		//shopButton.click();
		waitForLoad(driver);
		log.info("3. Click on Shop Menu");
		
		int productCount = saleProduct.size();
		System.out.println(productCount);
		for(int i=0;i<productCount;i++)
		{
			for(WebElement product:saleProduct)
			{
				safeJavaScriptClick(product);
				waitForLoad(driver);
				log.info("4. Click on Sale written product in home page");
			}
			boolean deletedPriceDisplayed = saleDeletedPrice.isDisplayed(); 
			Assert.assertTrue(deletedPriceDisplayed);
		}	
	}
	
	public void addAndViewBasket() throws Exception
	{
		safeJavaScriptClick(shopButton);
		waitForLoad(driver);
		log.info("3. Click on Shop Menu");
		
		safeJavaScriptClick(addToBasket);
		log.info("4. Click on the Add To Basket button which adds that book to your basket");
		
		
		try {
			boolean viewBasketDisplayed = viewBasket.isDisplayed();
			if(viewBasketDisplayed)
				safeJavaScriptClick(viewBasket);
			else
				driver.navigate().refresh();
				waitForLoad(driver);
				safeJavaScriptClick(viewBasket);
		} catch (NoSuchElementException e) {
			driver.navigate().refresh();
			waitForLoad(driver);
			safeJavaScriptClick(viewBasket);
		}	
		
		log.info("5. User can view that Book in the Menu item with price .");
			
		Assert.assertEquals(productShopText.getText(), "Android Quick Start Guide");
		log.info("6. User can view that Book in the Menu item with price .");	
		
		Assert.assertEquals(total.getText(), "Total");
		Assert.assertEquals(subTotal.getText(), "Subtotal");
		log.info("7. Now user can find total and subtotal values just above the Proceed to Checkout button.");	
		
		StringBuffer totalPriceText=new StringBuffer(totalPrice.getAttribute("innerText"));
		totalPriceText.delete(totalPriceText.length()-3, totalPriceText.length()).deleteCharAt(0);
		int newTotalPriceText = Integer.parseInt(totalPriceText.toString());
		
		StringBuffer subTotalPriceText=new StringBuffer(subTotalPrice.getAttribute("innerText"));
		subTotalPriceText.delete(subTotalPriceText.length()-3, subTotalPriceText.length()).deleteCharAt(0);
		int newSubTotalPriceText = Integer.parseInt(subTotalPriceText.toString());
		
		if(newTotalPriceText>newSubTotalPriceText)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
		log.info("8. The total always < subtotal because taxes are added in the subtotal");	
		
		safeJavaScriptClick(proceedToCheckoutButton);
		log.info("9. Now click on Proceed to Check out button which navigates to payment gateway page.");	
		
		waitForLoad(driver);
		Assert.assertEquals(driver.getTitle(), "Checkout – Automation Practice Site");
		log.info("10. User can view Billing Details,Order Details,Additional details and Payment gateway details.");	
		
		checkoutPage.billingInformation();
	}
	
	public void addAndViewBasketViaItemLink() throws Exception
	{
		safeJavaScriptClick(shopButton);
		waitForLoad(driver);
		log.info("3. Click on Shop Menu");
		
		safeJavaScriptClick(addToBasket);
		log.info("4. Click on the Add To Basket button which adds that book to your basket");
		
		
		try {
			boolean viewBasketDisplayed = viewBasket.isDisplayed();
			if(viewBasketDisplayed)
				safeJavaScriptClick(viewBasket);
			else
				driver.navigate().refresh();
				waitForLoad(driver);
				safeJavaScriptClick(viewBasket);
		} catch (NoSuchElementException e) {
			driver.navigate().refresh();
			waitForLoad(driver);
			safeJavaScriptClick(viewBasket);
		}	
		
		log.info("5. User can view that Book in the Menu item with price .");
		
		itemLink.click();
		waitForLoad(driver);
		log.info("6. Now click on Item link which navigates to proceed to check out page.");
		
		Assert.assertEquals(total.getText(), "Total");
		Assert.assertEquals(subTotal.getText(), "Subtotal");
		log.info("7. Now user can find total and subtotal values just above the Proceed to Checkout button.");	
		
		StringBuffer totalPriceText=new StringBuffer(totalPrice.getAttribute("innerText"));
		totalPriceText.delete(totalPriceText.length()-3, totalPriceText.length()).deleteCharAt(0);
		int newTotalPriceText = Integer.parseInt(totalPriceText.toString());
		
		StringBuffer subTotalPriceText=new StringBuffer(subTotalPrice.getAttribute("innerText"));
		subTotalPriceText.delete(subTotalPriceText.length()-3, subTotalPriceText.length()).deleteCharAt(0);
		int newSubTotalPriceText = Integer.parseInt(subTotalPriceText.toString());
		
		if(newTotalPriceText>newSubTotalPriceText)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
		log.info("8. The total always < subtotal because taxes are added in the subtotal");	
		
		safeJavaScriptClick(proceedToCheckoutButton);
		log.info("9. Now click on Proceed to Check out button which navigates to payment gateway page.");	
		
		waitForLoad(driver);
		Assert.assertEquals(driver.getTitle(), "Checkout – Automation Practice Site");
		log.info("10. User can view Billing Details,Order Details,Additional details and Payment gateway details.");	
		
		checkoutPage.billingInformation();
	}
	
	public void addAndViewBasketVerifyTax() throws Exception
	{
		safeJavaScriptClick(shopButton);
		waitForLoad(driver);
		log.info("3. Click on Shop Menu");
		
		safeJavaScriptClick(addToBasket);
		log.info("4. Click on the Add To Basket button which adds that book to your basket");
		
		
		try {
			boolean viewBasketDisplayed = viewBasket.isDisplayed();
			if(viewBasketDisplayed)
				safeJavaScriptClick(viewBasket);
			else
				driver.navigate().refresh();
				waitForLoad(driver);
				safeJavaScriptClick(viewBasket);
		} catch (NoSuchElementException e) {
			driver.navigate().refresh();
			waitForLoad(driver);
			safeJavaScriptClick(viewBasket);
		}	
		
		log.info("5. User can view that Book in the Menu item with price .");
		
		itemLink.click();
		waitForLoad(driver);
		log.info("6. Now click on Item link which navigates to proceed to check out page.");
		
		Assert.assertEquals(total.getText(), "Total");
		Assert.assertEquals(subTotal.getText(), "Subtotal");
		log.info("7. Now user can find total and subtotal values just above the Proceed to Checkout button.");	
		
		StringBuffer totalPriceText=new StringBuffer(totalPrice.getAttribute("innerText"));
		totalPriceText.delete(totalPriceText.length()-3, totalPriceText.length()).deleteCharAt(0);
		int newTotalPriceText = Integer.parseInt(totalPriceText.toString());
		
		StringBuffer subTotalPriceText=new StringBuffer(subTotalPrice.getAttribute("innerText"));
		subTotalPriceText.delete(subTotalPriceText.length()-3, subTotalPriceText.length()).deleteCharAt(0);
		int newSubTotalPriceText = Integer.parseInt(subTotalPriceText.toString());
		
		if(newTotalPriceText>newSubTotalPriceText)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
		log.info("8. The total always < subtotal because taxes are added in the subtotal");	
		
		safeJavaScriptClick(proceedToCheckoutButton);		
		waitForLoad(driver);
		Assert.assertEquals(driver.getTitle(), "Checkout – Automation Practice Site");
		
		safeJavaScriptScrollDown();
		Thread.sleep(4000);
		safeJavaScriptScrollUp();
		uiActionsCheckoutPage.firstName.sendKeys("TestFirstName");
		uiActionsCheckoutPage.lastName.sendKeys("TestLastName");
		uiActionsCheckoutPage.emailAddress.sendKeys("TestAddress@gmail.com");
		uiActionsCheckoutPage.phone.sendKeys("123456789");
		uiActionsCheckoutPage.address.sendKeys("Test Address");
		uiActionsCheckoutPage.townCity.sendKeys("Test City");
		uiActionsCheckoutPage.stateDropDown.click();
		uiActionsCheckoutPage.stateName.click();
		uiActionsCheckoutPage.zip.sendKeys("12345");
		uiActionsCheckoutPage.countryDropDown.click();
		uiActionsCheckoutPage.countryName.sendKeys("");
	
		safeJavaScriptScrollDown();
	}
}
