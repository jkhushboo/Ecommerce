package suite.page;

import java.io.IOException;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import suite.base.PageBase;

public class MyAccountPage extends PageBase {
	
	private WebDriver driver;

	public MyAccountPage(WebDriver driver) {
	super(driver);
	this.driver = driver;
	PageFactory.initElements(driver, this);
	}

	public MyAccountPage getLoginPage(String pageURL) {
	NavigatetoPage(pageURL);
	return this;
	}


	@FindBy(css = ".janus-icon-listing>li:nth-child(1)>h6>a")
	private WebElement editInfoClk;


	@FindBy(id = "FirstName")
	private WebElement firstName;

	@FindBy(xpath = "//*[@id='LastName']")
	private WebElement lastName;

	@FindBy(id = "Phone")
	private WebElement phoneNum;

	@FindBy(css = ".btn.janus-btn-blue.btn-lg.text-uppercase")
	private WebElement updateProfileBtn;

	@FindBy(css = ".janus-icon-listing>li:nth-child(3)>h6>a")
	private WebElement logout;
	
	@FindBy(linkText="Address Book")
	private WebElement addressLink;
	
	@FindBy(linkText="My Orders")
	private WebElement myOrder;
	
	@FindBy(linkText="Payment Information")
	private WebElement paymentInformation;
	
	@FindBy(linkText="Product Registration")
	private WebElement registeredProduct;

	@FindBy(xpath="//*[@id=\"main\"]/div[3]/div[1]/div[2]/div/div/div/nav/div/div[1]/div/ul/li[3]/a")
	private WebElement clickSupport;
	
	@FindBy(xpath="/html/body/div[1]/div[3]/div[1]/div[2]/div/div/div/nav/div/div[1]/div/ul/li[3]/div/div/div[1]/div[1]/ul/li[3]/a")
	private WebElement clickOnProductRegistration;
	
	@FindBy(css="input[name='LoginEmail']")
	private WebElement uname;
	
	@FindBy(css="input[name='LoginPassword']")
	private WebElement pass;
	
	@FindBy(xpath="//*[@id=\"loginbtn\"]")
	private WebElement submitBtn;
	
	@FindBy(css="a[class='btn btn janus-btn-blue janus-sign-up-btn']")
	private WebElement productRegBtn;
	
	@FindBy(css="input[name='SerialNumber']")
	private WebElement serNum;
	
	@FindBy(css="input[value='Next']")
	private WebElement enterNextButton;
	
	@FindBy(css="#SelectedProductLine > option:nth-child(16)")
	private WebElement selectProdGroup;
	
	@FindBy(css="#SelectedModel > option:nth-child(4)")
	private WebElement selModel;
	
	@FindBy(css="input[id='btnNextStep2']")
	private WebElement nextButton;
	
	@FindBy(css="#SelectedPurchaseLocation > option:nth-child(2)")
	private WebElement clickPurchaseLocation;
	
	@FindBy(css="input[name='PurchaseDate']")
	private WebElement dateOfPurchased;
	
	@FindBy(css="input[id='btnNextStep4']")
	private WebElement ClickCompleteRegis;
	
	@FindBy(css="input[id='btnNextStep4']")
	private WebElement modelText;
	
	@FindBy(css="#registerProductForm > table > thead > tr > th:nth-child(1)")
	private WebElement model;
	
	@FindBy(css="#registerProductForm > table > thead > tr > th:nth-child(1)")
	private WebElement clickbutt;
	
	@FindBy(css="#RegisterProductTab > h3")
	private WebElement thankYouMessage;
	
	@FindBy(xpath="//*[@id=\"main\"]/div[4]/div[2]/div[2]/div/div/div/div/div[2]/div/div/div[1]/a")
	private WebElement loginToRegisterButton;
	/**
	* for clicking on edit personal information link
	*
	* @return object of current class
	*/
	public MyAccountPage clickOnEditInfo() {
	clickingElement(editInfoClk);
	return this;
	}


	/**
	* for editing the first username on Update page
	*
	* @param uname
	* @return object of current class
	*/
	public MyAccountPage UpdateFname(String fName) {
	clearTextArea(firstName);
	setText(firstName, fName);
	return this;
	}


	/**
	* for editing the first lastname on Update page
	*
	* @param uname
	* @return object of current class
	*/
	public MyAccountPage UpdateLname(String lName) {
	clearTextArea(lastName);
	setText(lastName, lName);
	return this;
	}

	/**
	* for editing the first phone no. on Update page
	*
	* @param uname
	* @return object of current class
	*/
	public MyAccountPage UpdatePhone(String phone) {
	clearTextArea(phoneNum);
	setText(phoneNum, phone);
	return this;
	}


	/**
	* for clicking on update button on update page
	*
	* @return object of current class
	*/
	public MyAccountPage clickOnUpdateProfileBtn() {
	clickingElement(updateProfileBtn);
	return this;
	}


	/**
	* for clicking on logout button on logout page
	*
	* @return object of current class
	*/
	public MyAccountPage clickOnLogOut() {
	clickingElement(logout);
	return this;
	}


	/**
	* for clicking on submit button on login page
	*
	* @return object of current class
	*/

	public String getFname() {
	return getAttributeValue(firstName, "value");
	}

	/**
	* for clicking on submit button on login page
	*
	* @return object of current class
	*/

	public String getLname() {
	return getAttributeValue(lastName, "value");
	}


	/**
	* for clicking on submit button on login page
	*
	* @return object of current class
	*/

	public String getPhoneNum() {
	return getAttributeValue(phoneNum, "value");
	}
	
	/**
	* for verifying Address Book is Displayed
	*
	* @return object of current class
	*/

	public Boolean isAddressMessageDisplay() {
		return getWebElement(addressLink).isDisplayed();
		
	}
	
	/**
	* for verifying My order tab is Displayed
	*
	* @return object of current class
	*/
	
	public Boolean isMyOrderDisplay() {
		return getWebElement(myOrder).isDisplayed();
		
	}
	
	/**
	* for verifying Payment Information tab is Displayed
	*
	* @return object of current class
	*/
	
	public Boolean isPaymentInformationDisplay() {
		return getWebElement(paymentInformation).isDisplayed();
		
	}
	
	/**
	* for verifying Registered Product tab is Displayed
	*
	* @return object of current class
	*/
	
	public Boolean isRegisteredProductDisplay() {
		return getWebElement(registeredProduct).isDisplayed();
		
	}

	public MyAccountPage clickOnSupport() {
		
		clickingElement(clickSupport);
		return this;
		// TODO Auto-generated method stub
		
	}

	public MyAccountPage clickOnProductRegistration() {
		clickingElement(clickOnProductRegistration);
		return this;
		
	}

	public MyAccountPage enterEmail(String unameTxt) {
		setText(uname, unameTxt);
		return this;
	}

	public MyAccountPage enterPassword(String passTxt) {
		setText(pass, passTxt);
		return this;
		
	}

	public MyAccountPage enterSubmit() {
		clickingElement(submitBtn);
		return this;
		
	}

	public MyAccountPage enterProductRegBtn() {
		clickingElement(productRegBtn);
		return this;
	}

	public MyAccountPage enterSerialNumber(String serialNum) {
		setText(serNum, serialNum);
		return this;
	}

	public MyAccountPage enterNextBtn() {
		clickingElement(enterNextButton);
		return this;
		
	}

	public MyAccountPage selectProductGroup(String productgroup) {
		
		
		/*
		 * Select dropdown = new Select(selectProdGroup);
		 * dropdown.selectByVisibleText(productgroup); return this;
		 */
		 
			  clickingElement(selectProdGroup); return this;
			 
		
	}

	public MyAccountPage selectModel(String inputText) {
		
		clickingElement(selModel);
		return this;
		
	}

	public MyAccountPage clickNextButton() {
		clickingElement(nextButton);
		return this;
		
	}

	public MyAccountPage clickOnPurchase(String purchaselocation) {
		clickingElement(clickPurchaseLocation);
		return this;
		
		
	}

	public MyAccountPage clickOnDatePurchased(String phone) {
		//clickingElement(dateOfPurchased);
		setText(dateOfPurchased, phone);
		return this;
		
		
	}

	public MyAccountPage clickOnCompleteRegistration() {
		clickingElement(ClickCompleteRegis);
		isElementDisplayed(model);
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
		
	}

	public MyAccountPage enterPass() {
		clickingElement(clickbutt);
		return null;
	}

	public boolean getThankYouMessage() {
		// TODO Auto-generated method stub
		return getWebElement(thankYouMessage).isDisplayed();
	}

	public MyAccountPage clickOnLoginToRegisterButton() {
		clickingElement(loginToRegisterButton);
		return this;
	}
}

/*
 * public String getModel() {
 * 
 * return getText(modelText); }
 */
		
	
	

