package UIActions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import testBase.TestBase;
import utility.ExcelUtility;

public class uiActionMyAccountLogin extends TestBase 
{
	public static final Logger log = Logger.getLogger(uiActionHomepage.class.getName());
	
	@FindBy(xpath="//*[@id=\"username\"]")
	WebElement userName;
	
	@FindBy(xpath="//input[@name='password'][@id='password']")
	WebElement password;
	
	@FindBy(xpath="//input[@name='login']")
	WebElement login;
	
	@FindBy(xpath="//p/strong")
	WebElement emailIdName;
	
	@FindBy(xpath="//a[text()='My Account']")
	WebElement myAccountMenuButton;
	
	@FindBy(xpath="//ul[@class='woocommerce-error']/li")
	WebElement validationForInvalidCredentials;
	
	@FindBy(xpath="//div[@class='woocommerce-MyAccount-content']/p")
	WebElement validationForvalidCredentials;
	
	@FindBy(xpath="//a[text()='Logout']")
	WebElement logOut;
	
	@FindBy(xpath="//a[text()='Logout']")
	List<WebElement> verifylogOut;
	
	@FindBy(xpath="//*[@id=\"page-36\"]/div/div[1]/nav/ul/li[1]/a")
	WebElement dashBoard;
	
	@FindBy(xpath="//*[@id=\"page-36\"]/div/div[1]/nav/ul/li[2]/a")
	WebElement orders;
	
	@FindBy(xpath="//*[@id=\"page-36\"]/div/div[1]/div/table/tbody/tr/td[5]/a")
	WebElement ordersViewButton;
	
	@FindBy(xpath="//*[contains(text(),'Order Details')]|//*[contains(text(),'Customer Details')]|//*[contains(text(),'Billing Address')]")
	List<WebElement> ordersDetails;
	
	@FindBy(xpath="//*[@id=\"page-36\"]/div/div[1]/div/p/mark")
	List<WebElement> ordersNumberDateStatus;
	
	@FindBy(xpath="//*[@id=\"page-36\"]/div/div[1]/nav/ul/li[4]/a")
	WebElement address;
	
	@FindBy(xpath="//*[@id='page-36']/div/div[1]/div/div/div//header/h3")
	List<WebElement> addressType;
	
	@FindBy(xpath="//*[@id=\"page-36\"]/div/div[1]/div/div/div[2]/header/a")
	WebElement editShippingAddress;
	
	@FindBy(xpath="//*[@id=\"page-36\"]/div/div[1]/div/form/h3")
	WebElement editShippingAddressText;
	
	@FindBy(xpath="//*[@id=\"page-36\"]/div/div[1]/nav/ul/li[5]/a")
	WebElement accountDetailsLink;
	
	@FindBy(xpath="//*[@id=\"page-36\"]/div/div[1]/div/form/fieldset/legend")
	WebElement passwordChangeText;
	
	@FindBy(xpath="//*[@id=\"page-36\"]/div/div[1]/nav/ul/li[6]/a")
	WebElement logOutButton;
	
	@FindBy(xpath="//*[@id=\"customer_login\"]/div[1]/h2")
	WebElement loginText;
	
	public uiActionMyAccountLogin(WebDriver driver) throws IOException // constructor
	{
		super();
		PageFactory.initElements(driver, this);  //this - refers current class objects
	}
	
	public void validCredentials() throws Exception
	{
		waitForElement(driver, 10, myAccountMenuButton);
		myAccountMenuButton.click();    
		log.info("3. Click on My Account Menu");
		userName.sendKeys(properties.getProperty("username"));
		log.info("4. Enter registered username in username textbox");
		password.sendKeys(properties.getProperty("password"));	
		log.info("5. Enter password in password textbox");
		login.click();
		log.info("6. Click on login button");
		Assert.assertEquals(validationForvalidCredentials.getText(),"Hello aj13blu (not aj13blu? Sign out)");
		log.info("7. User must successfully login to the web page");
	}
	
	public void invalidCredentialsFlow() throws Exception
	{		
		waitForElement(driver, 10, myAccountMenuButton);
		myAccountMenuButton.click();    
		log.info("3. Click on My Account Menu");
		userName.sendKeys(properties.getProperty("incorrectusername"));
		log.info("4. Enter incorrect username in username textbox");
		password.sendKeys(properties.getProperty("incorrectpassword"));	
		log.info("5. Enter incorrect password in password textbox.");
		login.click();
		log.info("6. Click on login button");
		validationForInvalidCredentials.getText();
		System.out.println(validationForInvalidCredentials.getText());
		Assert.assertEquals(validationForInvalidCredentials.getText(),"Error: A user could not be found with this email address.");
		log.info("7. Proper error must be displayed(ie Invalid username) and prompt to enter login again");		
	}
	
	public void credentialCheckExcel() throws FileNotFoundException {   		
		String path= "C:\\Users\\ajinkya.bhobad\\eclipse-workspace\\AutomationPractise\\src\\resources\\data\\ExcelFile.xls";
		ExcelUtility excel = new ExcelUtility(path);
		int row = excel.getRowCount("cred");
		
		for (int i=2;i<=row;i++){
			
					try {
						log.info(excel.getCellData("cred",0,i));

						String UserName=excel.getCellData("cred",1,i);
						waitForElement(driver, 4 , userName);
						userName.sendKeys(UserName);
						
						String Pwd=excel.getCellData("cred",2,i);
						waitForElement(driver, 4, password);
						password.sendKeys(Pwd);
						
						login.click();
						waitForLoad(driver);
						
						String validationText=excel.getCellData("cred",3,i);
						//System.out.println("Excel Text - "+validationText);
						                    
						if(verifylogOut.size()>0) {
							Assert.assertEquals(validationForvalidCredentials.getText(),validationText);
							logOut.click();
							waitForLoad(driver);
						}
						else if(verifylogOut.size()==0){
							//System.out.println("From Site Text -"+validationForInvalidCredentials.getText());
							Assert.assertEquals(validationForInvalidCredentials.getText(),validationText);
							waitForLoad(driver);
							waitForElement(driver, userName , 4);
							userName.clear();
							waitForElement(driver, 4, password);
							password.clear();
						}
					  getScreenShot("TCAccout_01_Success");
					} catch (Exception e) {
						e.printStackTrace();
						getScreenShot("TCAccout_01_Failure");
					}
		}
	}
	public void MyAccountDashBoard() throws Exception {
		validCredentials();
		//waitForElement(driver, 10, myAccountMenuButton);
		//myAccountMenuButton.click();
		safeJavaScriptClick(myAccountMenuButton);
		log.info("8. Click on Myaccount link which leads to Dashboard");
	}
	
	public void dashBoard() throws Exception {	
		MyAccountDashBoard();
		Assert.assertEquals(dashBoard.getText(),"Dashboard");
		log.info("9. User must view Dashboard of the site");
	}
	
	public void ordersFlow1() throws Exception {
		MyAccountDashBoard();	
		Assert.assertEquals(dashBoard.getText(),"Dashboard");
		log.info("9. Click on Orders link");
		safeJavaScriptClick(orders);
		waitForLoad(driver);
		Assert.assertEquals(driver.getCurrentUrl(), "http://practice.automationtesting.in/my-account/orders/");
		log.info("10. User must view their orders on clicking orders link");
	}
	
	public void ordersFlow2() throws Exception {
		MyAccountDashBoard();	
		Assert.assertEquals(dashBoard.getText(),"Dashboard");
		log.info("9. Click on Orders link");
		safeJavaScriptClick(orders);
		waitForLoad(driver);
		ordersViewButton.click();
		log.info("10. Click view button");
		System.out.println(ordersDetails.size());
		if(ordersDetails.size()==3) {
			log.info("11. User must view his Order details,customer details and billing details on clicking view button");
		}
		else throw new Exception("User is not able to viwe Order details,customer details and billing details on clicking view button");
	}
	
	public void ordersFlow3() throws Exception {
		MyAccountDashBoard();	
		Assert.assertEquals(dashBoard.getText(),"Dashboard");
		log.info("9. Click on Orders link");
		safeJavaScriptClick(orders);
		waitForLoad(driver);
		ordersViewButton.click();
		log.info("10. Click view button");
		
		System.out.println("Following are the details for Order Number Ordered date and Status : ");
		for(WebElement orderInformation:ordersNumberDateStatus)
		{
			String orderInfo = orderInformation.getText();
			System.out.println(orderInfo);		
		}
		
		log.info("11. User must view Order Number Ordered date and Status of the order on clicking view button");
	}
	
	public void addressFlow1() throws Exception {
		MyAccountDashBoard();
		address.click();
		waitForLoad(driver);
		log.info("9. Click on Address link");
		System.out.println("Following are the details for billing address and ship address");
		for(WebElement addresstext:addressType)
		{
			if(addresstext==null) {
				throw new Exception("Element was NULL");
			}
			String addressView = addresstext.getText();
			System.out.println(addressView);
		}
		log.info("10. User must view billing address and ship address");
	}
		
	public void addressFlow2() throws Exception {
		MyAccountDashBoard();
		address.click();
		waitForLoad(driver);
		log.info("9. Click on Address link");
		editShippingAddress.click();
		waitForLoad(driver);
		log.info("10. Click Edit on Shipping Address");
		Assert.assertEquals(editShippingAddressText.getText(),"Shipping Address");
		log.info("11. User can Edit Shipping address");
	}
	
	public void accountDetails() throws Exception {
		MyAccountDashBoard();
		accountDetailsLink.click();
		waitForLoad(driver);
		log.info("9. Click on Account details");
		Assert.assertEquals(passwordChangeText.getText(),"Password Change");
		log.info("10. User can view account details where he can change his pasword also.");
	}
	
	public void logOut() throws Exception {
		MyAccountDashBoard();
		logOutButton.click();
		waitForLoad(driver);
		log.info("9. Click on Logout button");
		Assert.assertEquals(loginText.getText(),"Login");
		log.info("10. On clicking logout,User successfully comes out from the site");
		
		
	}
	
}
