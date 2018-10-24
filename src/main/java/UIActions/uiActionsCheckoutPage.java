package UIActions;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import testBase.TestBase;

public class uiActionsCheckoutPage  extends TestBase{

	public static final Logger log = Logger.getLogger(uiActionHomepage.class.getName());
	
		//locator
		@FindBy(xpath="//*[@id=\"billing_first_name\"]")
		static WebElement firstName;
		
		@FindBy(xpath="//*[@id=\"billing_last_name\"]")
		static WebElement lastName;
		
		@FindBy(xpath="//*[@id=\"billing_email\"]")
		static WebElement emailAddress;
		
		@FindBy(xpath="//*[@id=\"billing_phone\"]")
		static WebElement phone;
		
		@FindBy(xpath="//*[@id=\"billing_address_1\"]")
		static WebElement address;

		@FindBy(xpath="//*[@id=\"billing_city\"]")
		static WebElement townCity;
		
		@FindBy(xpath="//*[@id='select2-chosen-2']")
		static WebElement stateDropDown;
		
		@FindBy(xpath="//div[contains(text(),'Alaska')]")
		static WebElement stateName;
		
		@FindBy(xpath="//*[@id='s2id_billing_country']/a/span[2]/b")
		static WebElement countryDropDown;
		
		@FindBy(xpath="//*[@id='s2id_autogen1_search']")
		static WebElement countryName;
		
		@FindBy(xpath="//*[@id=\"billing_postcode\"]")
		static WebElement zip;
		
		@FindBy(xpath="//*[@id=\"payment\"]/ul/li[3]/label")
		static WebElement COD;
		
		@FindBy(xpath="//input[@id='place_order']")
		static WebElement placeOrderButton;
		
		@FindBy(xpath="//p[contains(text(),'Thank you. Your order has been received.')]")
		static WebElement orderConfirmationMessage;
		
	public uiActionsCheckoutPage() throws IOException {
		super();
		PageFactory.initElements(driver, this);  //this - refers current class objects
	}
	
	public void billingInformation() throws Exception
	{
		log.info("11. Now user can fill his details in billing details form and can opt any payment in the payment gateway like Direct bank transfer,cheque,cash or paypal.");
		safeJavaScriptScrollDown();
		Thread.sleep(4000);
		safeJavaScriptScrollUp();
		firstName.sendKeys("TestFirstName");
		lastName.sendKeys("TestLastName");
		emailAddress.sendKeys("TestAddress@gmail.com");
		phone.sendKeys("123456789");
		address.sendKeys("Test Address");
		townCity.sendKeys("Test City");
		stateDropDown.click();
		stateName.click();
		zip.sendKeys("12345");
		safeJavaScriptScrollDown();
		waitForLoad(driver);
		waitForElement(driver,COD,10);
		COD.click();
		waitForLoad(driver);
		waitForElement(driver, placeOrderButton, 10);
		placeOrderButton.click();
		waitForLoad(driver);
		log.info("12. Now click on Place Order button to complete process");
		Assert.assertEquals(orderConfirmationMessage.getText(),"Thank you. Your order has been received.");
		log.info("13. On clicking place order button user completes his process where the page navigates to Order confirmation page with order details,bank details,customer details and billing details.");
	}
}
