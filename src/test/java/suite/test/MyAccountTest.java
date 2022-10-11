package suite.test;

	import org.testng.Assert;
import org.testng.annotations.Test;

	import suite.base.TestBase;
	import suite.page.LoginPage;
	import suite.page.MyAccountPage;
	import suite.testdata.LoginTestData;
	import suite.testdata.MyAccountTestData;

	public class MyAccountTest extends TestBase {

		private MyAccountPage up;
		
		int min =11;
		int max = 99;
		int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
		/**
		 * create page url
		 * 
		 * @return login pageurl
		 */
		private String getPageUrl() {
			return getEnv().concat(LoginTestData.LOGIN_PAGE);
		}
		
		private String getHomePageUrl() {
			return getEnv().concat(LoginTestData.HOME_LOGIN_PAGE);
		}

		private void login() {
			new LoginPage(getDriver())
			.getLoginPage(getPageUrl())
			.enterUname(LoginTestData.VALID_UNAME)
			.enterPass(LoginTestData.VALID_PASS)
			.clkSubmitBtn();
		}

		@Test(enabled = true,description = "verify clicking on edit profile info")
		public void ma_1() {
			up = new MyAccountPage(getDriver());
			login();
			up.clickOnEditInfo()
			.UpdateFname(MyAccountTestData.FIRST_NAME)
			.UpdateLname(MyAccountTestData.LAST_NAME)
			.UpdatePhone(MyAccountTestData.PHONE_NUM)
			.clickOnUpdateProfileBtn()
			.clickOnLogOut();
			
			login();
			up.clickOnEditInfo();
			
			String firstName = up.getFname();
			String lastName = up.getLname();
			String phoneNum = up.getPhoneNum();
			
			System.out.println(firstName);
			System.out.println(lastName);
			System.out.println(phoneNum);
			
			
			if(MyAccountTestData.FIRST_NAME.equals(firstName)
					&& MyAccountTestData.LAST_NAME.equals(lastName)
				    && MyAccountTestData.PHONE_NUM.equals(phoneNum))
			{
				System.out.println("profile has been edited successfully");
			}
			else
			{
				System.out.println("Updated profile is not matched");
			}
		
		}
		
		@Test(enabled = true,description = "Verify that Address Book is Display on MyAccount Page", groups = {
		"login" })
		public void ma_2() {
			
			up = new MyAccountPage(getDriver());
			login();
			
			Boolean actualAddress= up.isAddressMessageDisplay();
			Assert.assertTrue(actualAddress);

			
		}
		
		@Test(enabled = true,description = "Verify that My Orders is Display on MyAccount Page", groups = {
		"login" })
		public void ma_3() {
			
			up = new MyAccountPage(getDriver());
			login();
			
			Boolean actualAddress= up.isMyOrderDisplay();
			Assert.assertTrue(actualAddress);
	}
		
		@Test(enabled = true,description = "Verify that Payment Information is Display on MyAccount Page", groups = {
		"login" })
		public void ma_4() {
			
			up = new MyAccountPage(getDriver());
			login();
			
			Boolean actualAddress= up.isPaymentInformationDisplay();
			Assert.assertTrue(actualAddress);
	
}
	
		@Test(enabled = true,description = "Verify that Registered Product is Display on MyAccount Page", groups = {
		"login" })
		public void ma_5() {
			
			up = new MyAccountPage(getDriver());
			login();
			
			Boolean actualAddress= up.isRegisteredProductDisplay();
			Assert.assertTrue(actualAddress);
	}
		
		@Test(enabled = true,description = "Verify that Registered Product is Display on MyAccount Page")
		public void ma_6() {
			
			String serialNumber = "E6F1209"+ random_int;
			System.out.println(serialNumber);
			
			  up = new MyAccountPage(getDriver());
			  up.getLoginPage(getHomePageUrl()).clickOnSupport()
			  .clickOnProductRegistration() 
			  .clickOnLoginToRegisterButton()
			  .enterEmail(MyAccountTestData.USERNAME)
			  .enterPassword(MyAccountTestData.PASSWORD) 
			  .enterSubmit()
			  .enterProductRegBtn() 
			  .enterSerialNumber(serialNumber) 
			  .enterNextBtn()
			  .selectProductGroup(MyAccountTestData.PRODUCTGROUP)
			  .selectModel(MyAccountTestData.MODEL)
			  .clickNextButton()
			  .clickOnPurchase(MyAccountTestData.PURCHASELOCATION)
			  .clickOnDatePurchased(MyAccountTestData.DATEPURCHASED)
			  .clickOnCompleteRegistration();
			  
				  boolean actual= up.getThankYouMessage(); 
				  Assert.assertTrue(actual);
				 
			  
			 
}
		
		
}