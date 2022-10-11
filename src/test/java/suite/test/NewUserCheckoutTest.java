package suite.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.Assert;
import org.testng.annotations.Test;

import suite.base.TestBase;
import suite.page.CheckoutPage;
import suite.testdata.CheckoutTestData;
import suite.utils.Xls_Reader;

public class NewUserCheckoutTest extends TestBase{
	
	private XSSFSheet getSheet() { 
		return getTestData().getSheet("New_user"); 
		}
	
	  private CheckoutPage co;
		DataFormatter formatter = new DataFormatter();
		private Cell cell;
		
		Xls_Reader reader = new Xls_Reader("src/main/resources/TestDataSheet_uat.xlsx");
		
		
		private String getPageUrlStandard(int row, int column) {
			return getEnv().concat((getSheet().getRow(row).getCell(column).getStringCellValue()));
		}
		
		private String getPageUrlSecond() {
			return getEnv().concat((getSheet().getRow(5).getCell(1).getStringCellValue()));
		}
		
		private String getPageUrlNext() {
			return getEnv().concat((getSheet().getRow(9).getCell(1).getStringCellValue()));
		}
		
		private String getPageUrlNext1() { return
				  getEnv().concat((getSheet().getRow(9).getCell(1).getStringCellValue())); }
		  
		  private String getPageUrlNext2() { return
		  getEnv().concat((getSheet().getRow(10).getCell(1).getStringCellValue())); }
		
		private String getLoginPageUrl() {
			return getEnv().concat(CheckoutTestData.LOGIN_PAGE);
		}
		
		private String getCardNo() {
			 cell= getSheet().getRow(1).getCell(14);
			 return formatter.formatCellValue(cell);
								
		}
		
		private String getCardNo2() {
			 cell= getSheet().getRow(2).getCell(14);
			 return formatter.formatCellValue(cell);
								
		}
		
		private String getCardNo3() {
			 cell= getSheet().getRow(3).getCell(14);
			 return formatter.formatCellValue(cell);
								
		}
		
		private String getCardNo4() {
			 cell= getSheet().getRow(4).getCell(14);
			 return formatter.formatCellValue(cell);
								
		}
		
		private String getPhoneNo() {
			 cell= getSheet().getRow(2).getCell(7);
			 return formatter.formatCellValue(cell);						
		}
		
		private String getZipCode() {
			 cell= getSheet().getRow(1).getCell(11);
			 return formatter.formatCellValue(cell);						
		}
		
		private String getEXDateMM() {
			 cell= getSheet().getRow(1).getCell(15);
			 return formatter.formatCellValue(cell);						
		}
		
		private String getEXDateYY() {
			 cell= getSheet().getRow(1).getCell(16);
			 return formatter.formatCellValue(cell);						
		}
		
		private String getCVV() {
			 cell= getSheet().getRow(1).getCell(17);
			 return formatter.formatCellValue(cell);						
		}
  

	@Test(priority=0,enabled=true,description = "Placed order using Create an new user with ground shipping using Visa CC")//taking too much time to load
	public void NewUser_GroundShipping_Visa() {
		
	
		reader.removeColumn("New_user", 20);
		reader.addColumn("New_user", "Order_ID");
		
		  String myEmailAddress = "user+" + System.nanoTime() + "@brother.com"; 
		  co = new CheckoutPage(getDriver()); 
		  co.getPdpCheckoutPage(getPageUrlStandard(1,1))
		    .clkAddtoCartBtn() 
		    .clkProceedToCartBtn() 
		    .clkCheckoutBtn()
		    .clickLoginButton()
		    .clkCreateNewUserTab()
		    .enterCheckoutEmail(myEmailAddress)
		    .enterCheckoutPass(getSheet().getRow(1).getCell(3).getStringCellValue())
		    .enterCheckoutConfirmPass(getSheet().getRow(1).getCell(4).getStringCellValue())
		    .enterCheckoutFname(getSheet().getRow(1).getCell(5).getStringCellValue())
		    .enterCheckoutLname(getSheet().getRow(1).getCell(6).getStringCellValue())
			.scrollToCreateAccountBtn()
			.clkCreateAccountBtn()
			.clkCookiesCrossBtn()
			.enterGuestPhoneNo(getPhoneNo())
			.enterGuestAddress(getSheet().getRow(1).getCell(8).getStringCellValue())
			.enterGuestCity(getSheet().getRow(1).getCell(9).getStringCellValue())
			.selectGuestState(getSheet().getRow(1).getCell(10).getStringCellValue())
			.enterGuestZip(getZipCode())
			.enterAddressLine2("Test")
			.clkInfoNextBtn()
			.clkViewShippingNextBtn()
			.switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
			.enterNameOnCardCc(getSheet().getRow(1).getCell(12).getStringCellValue())
			.selectCardTypeVisa()  
		    .enterCardNoCc(getCardNo())
			.enterExpiryMmCc(getEXDateMM()) 
			.enterExpiryYyCc(getEXDateYY())
			.enterCvvCc(getCVV()) 
			.switchToPaymentPage() 
			.clkConfirmBtn()
			.clkPlaceOrderBtn(); 
		 
		     String actual=co.getOrderId(); 
		     System.out.println("Your Order id for checkout flow of New user with ground shipping using Visa cc is " +actual);
		     reader.setCellData("New_user", "Order_ID", 2, actual);
	}

	@Test(enabled=true,description = "Placed order using Create an new user with ground shipping using mastercard CC")//taking too much time to load
	public void NewUser_GroundShipping_Master() {
		
		  String myEmailAddress = "user+" + System.nanoTime() + "@brother.com"; 
		
		  co = new CheckoutPage(getDriver()); 
			
		  co.getPdpCheckoutPage(getPageUrlStandard(2,1))
			.clkAddtoCartBtn()
			.clkProceedToCartBtn() 
			.clkCheckoutBtn() 
			.clickLoginButton()
			.clkCreateNewUserTab()
			.enterCheckoutEmail(myEmailAddress)
			.enterCheckoutPass(getSheet().getRow(1).getCell(3).getStringCellValue())
			.enterCheckoutConfirmPass(getSheet().getRow(1).getCell(4).getStringCellValue()) 
			.enterCheckoutFname(getSheet().getRow(1).getCell(5).getStringCellValue())
			.enterCheckoutLname(getSheet().getRow(1).getCell(6).getStringCellValue())
			.scrollToCreateAccountBtn() 
			.clkCreateAccountBtn() 
			.clkCookiesCrossBtn()
			.enterGuestPhoneNo(getPhoneNo())
			.enterGuestAddress(getSheet().getRow(1).getCell(8).getStringCellValue())
			.enterGuestCity(getSheet().getRow(1).getCell(9).getStringCellValue())
			.selectGuestState(getSheet().getRow(1).getCell(10).getStringCellValue())
			.enterGuestZip(getZipCode()) 
			.enterAddressLine2("Test") 
			.clkInfoNextBtn()
			.clkViewShippingNextBtn() 
			.switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
			.enterNameOnCardCc(getSheet().getRow(2).getCell(12).getStringCellValue())
			.selectCardTypeMaster() 
			.enterCardNoCc(getCardNo2())
			.enterExpiryMmCc(getEXDateMM()) 
			.enterExpiryYyCc(getEXDateYY())
			.enterCvvCc(getCVV()) 
			.switchToPaymentPage() 
			.clkConfirmBtn()
			.clkPlaceOrderBtn();
			  
			 String actual=co.getOrderId(); 
			 System.out.println("Your Order id for checkout flow of New user with ground shipping using Mastercard cc is "+actual); 
			 reader.setCellData("New_user", "Order_ID", 3, actual);
			 
			}
	
	@Test(enabled=true,description = "Placed order using Create an new user with ground shipping using Amex CC")//taking too much time to load
	public void NewUser_GroundShipping_Amex() {
		
	      String myEmailAddress = "user+" + System.nanoTime() + "@brother.com"; 
		  co = new CheckoutPage(getDriver()); 
		  co.getPdpCheckoutPage(getPageUrlStandard(3,1))
		    .clkAddtoCartBtn() 
		    .clkProceedToCartBtn() 
		    .clkCheckoutBtn()
		    .clickLoginButton()
		    .clkCreateNewUserTab()
		    .enterCheckoutEmail(myEmailAddress)
		    .enterCheckoutPass(getSheet().getRow(1).getCell(3).getStringCellValue())
		    .enterCheckoutConfirmPass(getSheet().getRow(1).getCell(4).getStringCellValue())
			.enterCheckoutFname(getSheet().getRow(1).getCell(5).getStringCellValue())
			.enterCheckoutLname(getSheet().getRow(1).getCell(6).getStringCellValue())
		    .scrollToCreateAccountBtn()
		    .clkCreateAccountBtn()
			.clkCookiesCrossBtn()
			.enterGuestPhoneNo(getPhoneNo())
			.enterGuestAddress(getSheet().getRow(1).getCell(8).getStringCellValue())
			.enterGuestCity(getSheet().getRow(1).getCell(9).getStringCellValue())
			.selectGuestState(getSheet().getRow(1).getCell(10).getStringCellValue())
			.enterGuestZip(getZipCode())
			.enterAddressLine2("Test")
			.clkInfoNextBtn()
			.clkViewShippingNextBtn()
			.switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
			.enterNameOnCardCc(getSheet().getRow(3).getCell(12).getStringCellValue())
			.selectCardTypeAmex()
			.enterCardNoCc(getCardNo3())
			.enterExpiryMmCc(getEXDateMM()) 
			.enterExpiryYyCc(getEXDateYY())
			.enterCvvCc(getCVV()) 
			.switchToPaymentPage() 
			.clkConfirmBtn()
			.clkPlaceOrderBtn();
		  
		     System.out.println("Thank You for Shopping with us");
		  
		     String actual=co.getOrderId(); 
		     System.out.println("Your Order id for checkout flow of New user with ground shipping using Amex cc is " +actual);
		     reader.setCellData("New_user", "Order_ID", 4, actual);
			}
	
	@Test(enabled=true,description = "Placed order using Create an new user with ground shipping using Discover CC")//taking too much time to load
	public void NewUser_GroundShipping_Disc() {
		
		String myEmailAddress = "user+" + System.nanoTime() + "@brother.com"; 
		co = new CheckoutPage(getDriver()); 
		co.getPdpCheckoutPage(getPageUrlStandard(4,1))
		  .clkAddtoCartBtn() 
		  .clkProceedToCartBtn() 
		  .clkCheckoutBtn()
		  .clickLoginButton()
		  .clkCreateNewUserTab()
		  .enterCheckoutEmail(myEmailAddress)
		  .enterCheckoutPass(getSheet().getRow(1).getCell(3).getStringCellValue())
		  .enterCheckoutConfirmPass(getSheet().getRow(1).getCell(4).getStringCellValue())
		  .enterCheckoutFname(getSheet().getRow(1).getCell(5).getStringCellValue())
		  .enterCheckoutLname(getSheet().getRow(1).getCell(6).getStringCellValue())
		  .scrollToCreateAccountBtn()
		  .clkCreateAccountBtn()
		  .clkCookiesCrossBtn()
		  .enterGuestPhoneNo(getPhoneNo())
		  .enterGuestAddress(getSheet().getRow(1).getCell(8).getStringCellValue())
		  .enterGuestCity(getSheet().getRow(1).getCell(9).getStringCellValue())
		  .selectGuestState(getSheet().getRow(1).getCell(10).getStringCellValue())
		  .enterGuestZip(getZipCode())
		  .enterAddressLine2("Test")
		  .clkInfoNextBtn()
		  .clkViewShippingNextBtn()
		  .switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
		  .enterNameOnCardCc(getSheet().getRow(4).getCell(12).getStringCellValue())
		  .selectCardTypeDiscover()
		  .enterCardNoCc(getCardNo4())
		  .enterExpiryMmCc(getEXDateMM()) 
		  .enterExpiryYyCc(getEXDateYY())
		  .enterCvvCc(getCVV()) 
		  .switchToPaymentPage() 
		  .clkConfirmBtn()
		  .clkPlaceOrderBtn();
		  
		  String actual=co.getOrderId(); 
		  System.out.println("Your Order id for checkout flow of New user with ground shipping using Discover cc is " +actual);
		  reader.setCellData("New_user", "Order_ID", 5, actual);
	}
	
	@Test(enabled=true,description = "Placed order using Create an new user with second day shipping using Visa CC")//taking too much time to load
	public void NewUser_SecondShipping_Visa() {
		
		String myEmailAddress = "user+" + System.nanoTime() + "@brother.com"; 
		co = new CheckoutPage(getDriver()); 
		co.getPdpCheckoutPage(getPageUrlStandard(5,1))
		  .clkAddtoCartBtn() 
		  .clkProceedToCartBtn() 
		  .clkCheckoutBtn()
		  .clickLoginButton()
		  .clkCreateNewUserTab()
		  .enterCheckoutEmail(myEmailAddress)
		  .enterCheckoutPass(getSheet().getRow(1).getCell(3).getStringCellValue())
		  .enterCheckoutConfirmPass(getSheet().getRow(1).getCell(4).getStringCellValue())
		  .enterCheckoutFname(getSheet().getRow(1).getCell(5).getStringCellValue())
		  .enterCheckoutLname(getSheet().getRow(1).getCell(6).getStringCellValue())
		  .scrollToCreateAccountBtn()
		  .clkCreateAccountBtn()
		  .clkCookiesCrossBtn()
		  .enterGuestPhoneNo(getPhoneNo())
		  .enterGuestAddress(getSheet().getRow(1).getCell(8).getStringCellValue())
		  .enterGuestCity(getSheet().getRow(1).getCell(9).getStringCellValue())
		  .selectGuestState(getSheet().getRow(1).getCell(10).getStringCellValue())
		  .enterGuestZip(getZipCode())
		  .enterAddressLine2("Test")
		  .clkInfoNextBtn()
		  .clkSecondDayAirShip()
		  .clkShippingNextBtn()
		  .switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
		  .enterNameOnCardCc(getSheet().getRow(5).getCell(12).getStringCellValue())
		  .selectCardTypeVisa()  
		  .enterCardNoCc(getCardNo())
		  .enterExpiryMmCc(getEXDateMM()) 
		  .enterExpiryYyCc(getEXDateYY())
		  .enterCvvCc(getCVV()) 
		  .switchToPaymentPage() 
		  .clkConfirmBtn()
		  .clkPlaceOrderBtn();
		  
		  System.out.println("Thank You for Shopping with us");
		  
		  String actual=co.getOrderId(); 
		  System.out.println("Your Order id for checkout flow of New user with second day shipping using visa cc is " +actual);
		  reader.setCellData("New_user", "Order_ID", 6, actual);
	}
	
	@Test(enabled=true,description = "Placed order using Create an new user with second day shipping using master CC")//taking too much time to load
	public void NewUser_SecondShipping_Master() {
		
		String myEmailAddress = "user+" + System.nanoTime() + "@brother.com"; 
		co = new CheckoutPage(getDriver()); 
		co.getPdpCheckoutPage(getPageUrlStandard(6,1))
		  .clkAddtoCartBtn() 
		  .clkProceedToCartBtn() 
		  .clkCheckoutBtn()
		  .clickLoginButton()
		  .clkCreateNewUserTab()
		  .enterCheckoutEmail(myEmailAddress)
		  .enterCheckoutPass(getSheet().getRow(1).getCell(3).getStringCellValue())
		  .enterCheckoutConfirmPass(getSheet().getRow(1).getCell(4).getStringCellValue())
		  .enterCheckoutFname(getSheet().getRow(1).getCell(5).getStringCellValue())
		  .enterCheckoutLname(getSheet().getRow(1).getCell(6).getStringCellValue())
		  .scrollToCreateAccountBtn()
		  .clkCreateAccountBtn()
		  .clkCookiesCrossBtn()
		  .enterGuestPhoneNo(getPhoneNo())
		  .enterGuestAddress(getSheet().getRow(1).getCell(8).getStringCellValue())
		  .enterGuestCity(getSheet().getRow(1).getCell(9).getStringCellValue())
		  .selectGuestState(getSheet().getRow(1).getCell(10).getStringCellValue())
		  .enterGuestZip(getZipCode())
		  .enterAddressLine2("Test")
		  .clkInfoNextBtn()
		  .clkSecondDayAirShip()
		  .clkShippingNextBtn()
		  .switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
		  .enterNameOnCardCc(getSheet().getRow(6).getCell(12).getStringCellValue())
		  .selectCardTypeMaster()
		  .enterCardNoCc(getCardNo2())
		  .enterExpiryMmCc(getEXDateMM()) 
		  .enterExpiryYyCc(getEXDateYY())
		  .enterCvvCc(getCVV()) 
		  .switchToPaymentPage() 
		  .clkConfirmBtn()
		  .clkPlaceOrderBtn();
		  
		  System.out.println("Thank You for Shopping with us");
		  
		  String actual=co.getOrderId(); 
		  System.out.println("Your Order id for checkout flow of New user with second day shipping using master cc is " +actual);
		  reader.setCellData("New_user", "Order_ID", 7, actual);
			}
	
	@Test(enabled=true,description = "Placed order using Create an new user with second day shipping using Amex CC")//taking too much time to load
	public void NewUser_SecondShipping_Amex() {
		
		String myEmailAddress = "user+" + System.nanoTime() + "@brother.com"; 
		co = new CheckoutPage(getDriver()); 
		co.getPdpCheckoutPage(getPageUrlStandard(7,1))
		  .clkAddtoCartBtn() 
		  .clkProceedToCartBtn() 
		  .clkCheckoutBtn()
		  .clickLoginButton()
		  .clkCreateNewUserTab()
		  .enterCheckoutEmail(myEmailAddress)
		  .enterCheckoutPass(getSheet().getRow(1).getCell(3).getStringCellValue())
		  .enterCheckoutConfirmPass(getSheet().getRow(1).getCell(4).getStringCellValue())
		  .enterCheckoutFname(getSheet().getRow(1).getCell(5).getStringCellValue())
		  .enterCheckoutLname(getSheet().getRow(1).getCell(6).getStringCellValue())
		  .scrollToCreateAccountBtn()
		  .clkCreateAccountBtn()
		  .clkCookiesCrossBtn()
		  .enterGuestPhoneNo(getPhoneNo())
		  .enterGuestAddress(getSheet().getRow(1).getCell(8).getStringCellValue())
		  .enterGuestCity(getSheet().getRow(1).getCell(9).getStringCellValue())
		  .selectGuestState(getSheet().getRow(1).getCell(10).getStringCellValue())
		  .enterGuestZip(getZipCode())
		  .enterAddressLine2("Test")
		  .clkInfoNextBtn()
		  .clkSecondDayAirShip()
		  .clkShippingNextBtn()
		  .switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
		  .enterNameOnCardCc(getSheet().getRow(7).getCell(12).getStringCellValue())
		  .selectCardTypeAmex()
		  .enterCardNoCc(getCardNo3())
		  .enterExpiryMmCc(getEXDateMM()) 
		  .enterExpiryYyCc(getEXDateYY())
		  .enterCvvCc(getCVV()) 
		  .switchToPaymentPage() 
		  .clkConfirmBtn()
		  .clkPlaceOrderBtn();
		  
		  System.out.println("Thank You for Shopping with us");
		  
		  String actual=co.getOrderId(); 
		  System.out.println("Your Order id for checkout flow of New user with second day shipping using amex cc is " +actual);
		  reader.setCellData("New_user", "Order_ID", 8, actual);
			}

	@Test(enabled=true,description = "Placed order using Create an new user with second day shipping using Discover CC")//taking too much time to load
	public void NewUser_SecondShipping_Disc() {
		
		String myEmailAddress = "user+" + System.nanoTime() + "@brother.com"; 
		co = new CheckoutPage(getDriver()); 
		co.getPdpCheckoutPage(getPageUrlStandard(8,1))
		  .clkAddtoCartBtn() 
		  .clkProceedToCartBtn() 
		  .clkCheckoutBtn()
		  .clickLoginButton()
		  .clkCreateNewUserTab()
		  .enterCheckoutEmail(myEmailAddress)
		  .enterCheckoutPass(getSheet().getRow(1).getCell(3).getStringCellValue())
		  .enterCheckoutConfirmPass(getSheet().getRow(1).getCell(4).getStringCellValue())
		  .enterCheckoutFname(getSheet().getRow(1).getCell(5).getStringCellValue())
		  .enterCheckoutLname(getSheet().getRow(1).getCell(6).getStringCellValue())
		  .scrollToCreateAccountBtn()
		  .clkCreateAccountBtn()
		  .clkCookiesCrossBtn()
		  .enterGuestPhoneNo(getPhoneNo())
		  .enterGuestAddress(getSheet().getRow(1).getCell(8).getStringCellValue())
		  .enterGuestCity(getSheet().getRow(1).getCell(9).getStringCellValue())
		  .selectGuestState(getSheet().getRow(1).getCell(10).getStringCellValue())
		  .enterGuestZip(getZipCode())
		  .enterAddressLine2("Test")
		  .clkInfoNextBtn()
		  .clkSecondDayAirShip()
		  .clkShippingNextBtn()
		  .switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
		  .enterNameOnCardCc(getSheet().getRow(8).getCell(12).getStringCellValue())
		  .selectCardTypeDiscover()
		  .enterCardNoCc(getCardNo4())
		  .enterExpiryMmCc(getEXDateMM()) 
		  .enterExpiryYyCc(getEXDateYY())
		  .enterCvvCc(getCVV()) 
		  .switchToPaymentPage() 
		  .clkConfirmBtn()
		  .clkPlaceOrderBtn();
		  
		  System.out.println("Thank You for Shopping with us");
		  
		  String actual=co.getOrderId(); 
		  System.out.println("Your Order id for checkout flow of New user with second day shipping using Discover cc is " +actual);
		  reader.setCellData("New_user", "Order_ID", 9, actual);
			}
	
	@Test(enabled=true,description = "Placed order using Create an new user with next day shipping using Visa CC")//taking too much time to load
	public void NewUser_NextShipping_Visa() {
		
		String myEmailAddress = "user+" + System.nanoTime() + "@brother.com"; 
		co = new CheckoutPage(getDriver()); 
		co.getPdpCheckoutPage(getPageUrlStandard(9,1))
		  .clkAddtoCartBtn() 
		  .clkProceedToCartBtn() 
		  .clkCheckoutBtn()
		  .clickLoginButton()
		        .clkCreateNewUserTab()
		        .enterCheckoutEmail(myEmailAddress)
		        .enterCheckoutPass(getSheet().getRow(1).getCell(3).getStringCellValue())
				.enterCheckoutConfirmPass(getSheet().getRow(1).getCell(4).getStringCellValue())
				.enterCheckoutFname(getSheet().getRow(1).getCell(5).getStringCellValue())
				.enterCheckoutLname(getSheet().getRow(1).getCell(6).getStringCellValue())
				.scrollToCreateAccountBtn()
				.clkCreateAccountBtn()
				.clkCookiesCrossBtn()
				.enterGuestPhoneNo(getPhoneNo())
				.enterGuestAddress(getSheet().getRow(1).getCell(8).getStringCellValue())
				.enterGuestCity(getSheet().getRow(1).getCell(9).getStringCellValue())
				.selectGuestState(getSheet().getRow(1).getCell(10).getStringCellValue())
				.enterGuestZip(getZipCode())
				 .enterAddressLine2("Test")
				.clkInfoNextBtn()
				.clkNextDayAirShip()
				.clkShippingNextBtn()
				.switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
				.enterNameOnCardCc(getSheet().getRow(10).getCell(12).getStringCellValue())
				.selectCardTypeVisa()  
				  .enterCardNoCc(getCardNo())
				  .enterExpiryMmCc(getEXDateMM()) 
				  .enterExpiryYyCc(getEXDateYY())
				  .enterCvvCc(getCVV()) 
				  .switchToPaymentPage() 
				  .clkConfirmBtn()
				  .clkPlaceOrderBtn();
		  
		  System.out.println("Thank You for Shopping with us");
		  
		  String actual=co.getOrderId(); 
		  System.out.println("Your Order id for checkout flow of New user with next day shipping using visa cc is " +actual);
		  reader.setCellData("New_user", "Order_ID", 10, actual);
			}
	
	@Test(enabled=true,description = "Placed order using Create an new user with next day shipping using master CC")//taking too much time to load
	public void NewUser_NextShipping_Master() {
		
		String myEmailAddress = "user+" + System.nanoTime() + "@brother.com"; 
		co = new CheckoutPage(getDriver()); 
		co.getPdpCheckoutPage(getPageUrlStandard(10,1))
		  .clkAddtoCartBtn() 
		  .clkProceedToCartBtn() 
		  .clkCheckoutBtn()
		  .clickLoginButton()
		        .clkCreateNewUserTab()
		        .enterCheckoutEmail(myEmailAddress)
		        .enterCheckoutPass(getSheet().getRow(1).getCell(3).getStringCellValue())
				.enterCheckoutConfirmPass(getSheet().getRow(1).getCell(4).getStringCellValue())
				.enterCheckoutFname(getSheet().getRow(1).getCell(5).getStringCellValue())
				.enterCheckoutLname(getSheet().getRow(1).getCell(6).getStringCellValue())
				.scrollToCreateAccountBtn()
				.clkCreateAccountBtn()
				.clkCookiesCrossBtn()
				.enterGuestPhoneNo(getPhoneNo())
				.enterGuestAddress(getSheet().getRow(1).getCell(8).getStringCellValue())
				.enterGuestCity(getSheet().getRow(1).getCell(9).getStringCellValue())
				.selectGuestState(getSheet().getRow(1).getCell(10).getStringCellValue())
				.enterGuestZip(getZipCode())
				 .enterAddressLine2("Test")
				.clkInfoNextBtn()
				.clkNextDayAirShip()
				.clkShippingNextBtn()
				.switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
				.enterNameOnCardCc(getSheet().getRow(11).getCell(12).getStringCellValue())
				.selectCardTypeMaster()
				  .enterCardNoCc(getCardNo2())
				  .enterExpiryMmCc(getEXDateMM()) 
				  .enterExpiryYyCc(getEXDateYY())
				  .enterCvvCc(getCVV()) 
				  .switchToPaymentPage() 
				  .clkConfirmBtn()
				  .clkPlaceOrderBtn();
		  
		  System.out.println("Thank You for Shopping with us");
		  
		  String actual=co.getOrderId(); 
		  System.out.println("Your Order id for checkout flow of New user with next day shipping using master cc is " +actual);
		  reader.setCellData("New_user", "Order_ID", 11, actual);
			}
	
	@Test(enabled=true,description = "Placed order using Create an new user with next day shipping using Amex CC")//taking too much time to load
	public void NewUser_NextDayShipping_Amex() {
		
		String myEmailAddress = "user+" + System.nanoTime() + "@brother.com"; 
		co = new CheckoutPage(getDriver()); 
		co.getPdpCheckoutPage(getPageUrlStandard(11,1))
		  .clkAddtoCartBtn() 
		  .clkProceedToCartBtn() 
		  .clkCheckoutBtn()
		  .clickLoginButton()
		        .clkCreateNewUserTab()
		        .enterCheckoutEmail(myEmailAddress)
		        .enterCheckoutPass(getSheet().getRow(1).getCell(3).getStringCellValue())
				.enterCheckoutConfirmPass(getSheet().getRow(1).getCell(4).getStringCellValue())
				.enterCheckoutFname(getSheet().getRow(1).getCell(5).getStringCellValue())
				.enterCheckoutLname(getSheet().getRow(1).getCell(6).getStringCellValue())
				.scrollToCreateAccountBtn()
				.clkCreateAccountBtn()
				.clkCookiesCrossBtn()
				.enterGuestPhoneNo(getPhoneNo())
				.enterGuestAddress(getSheet().getRow(1).getCell(8).getStringCellValue())
				.enterGuestCity(getSheet().getRow(1).getCell(9).getStringCellValue())
				.selectGuestState(getSheet().getRow(1).getCell(10).getStringCellValue())
				.enterGuestZip(getZipCode())
				 .enterAddressLine2("Test")
				.clkInfoNextBtn()
				.clkNextDayAirShip()
				.clkShippingNextBtn()
				.switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
				.enterNameOnCardCc(getSheet().getRow(12).getCell(12).getStringCellValue())
				.selectCardTypeAmex() 
				  .enterCardNoCc(getCardNo3())
				  .enterExpiryMmCc(getEXDateMM()) 
				  .enterExpiryYyCc(getEXDateYY())
				  .enterCvvCc(getCVV()) 
				  .switchToPaymentPage() 
				  .clkConfirmBtn()
				  .clkPlaceOrderBtn();
		  
		  System.out.println("Thank You for Shopping with us");
		  
		  String actual=co.getOrderId(); 
		  System.out.println("Your Order id for checkout flow of New user with next day shipping using amex cc is " +actual);
		  reader.setCellData("New_user", "Order_ID", 12, actual);
			}

	@Test(enabled=true,description = "Placed order using Create an new user with next day shipping using Discover CC")//taking too much time to load
	public void NewUser_NextShipping_Disc() {
		
		String myEmailAddress = "user+" + System.nanoTime() + "@brother.com"; 
		co = new CheckoutPage(getDriver()); 
		co.getPdpCheckoutPage(getPageUrlStandard(12,1))
		  .clkAddtoCartBtn() 
		  .clkProceedToCartBtn() 
		  .clkCheckoutBtn()
		  .clickLoginButton()
		        .clkCreateNewUserTab()
		        .enterCheckoutEmail(myEmailAddress)
		        .enterCheckoutPass(getSheet().getRow(1).getCell(3).getStringCellValue())
				.enterCheckoutConfirmPass(getSheet().getRow(1).getCell(4).getStringCellValue())
				.enterCheckoutFname(getSheet().getRow(1).getCell(5).getStringCellValue())
				.enterCheckoutLname(getSheet().getRow(1).getCell(6).getStringCellValue())
				.scrollToCreateAccountBtn()
				.clkCreateAccountBtn()
				.clkCookiesCrossBtn()
				.enterGuestPhoneNo(getPhoneNo())
				.enterGuestAddress(getSheet().getRow(1).getCell(8).getStringCellValue())
				.enterGuestCity(getSheet().getRow(1).getCell(9).getStringCellValue())
				.selectGuestState(getSheet().getRow(1).getCell(10).getStringCellValue())
				.enterGuestZip(getZipCode())
				 .enterAddressLine2("Test")
				.clkInfoNextBtn()
				.clkNextDayAirShip()
				.clkShippingNextBtn()
				.switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
				.enterNameOnCardCc(getSheet().getRow(13).getCell(12).getStringCellValue())
				.selectCardTypeDiscover()
				  .enterCardNoCc(getCardNo4())
				  .enterExpiryMmCc(getEXDateMM()) 
				  .enterExpiryYyCc(getEXDateYY())
				  .enterCvvCc(getCVV()) 
				  .switchToPaymentPage() 
				  .clkConfirmBtn()
				  .clkPlaceOrderBtn();
		  
		  System.out.println("Thank You for Shopping with us");
		  
		  String actual=co.getOrderId(); 
		  System.out.println("Your Order id for checkout flow of New user with next day shipping using Discover cc is " +actual);
		  reader.setCellData("New_user", "Order_ID", 13, actual);
			}
	
	@Test(enabled=true,description = "Placed order using Create an new user with ground shipping using  PayPal")//taking too much time to load
	public void NewUser_GroundShipping_PayPal() {
		
		String myEmailAddress = "user+" + System.nanoTime() + "@brother.com"; 
		co = new CheckoutPage(getDriver()); 
		co.getPdpCheckoutPage(getPageUrlStandard(13,1))
		  .clkAddtoCartBtn() 
		  .clkProceedToCartBtn() 
		  .clkCheckoutBtn()
		  .clickLoginButton()
		  .clkCreateNewUserTab()
		  .enterCheckoutEmail(myEmailAddress)
		  .enterCheckoutPass(getSheet().getRow(1).getCell(3).getStringCellValue())
		  .enterCheckoutConfirmPass(getSheet().getRow(1).getCell(4).getStringCellValue())
		  .enterCheckoutFname(getSheet().getRow(1).getCell(5).getStringCellValue())
		  .enterCheckoutLname(getSheet().getRow(1).getCell(6).getStringCellValue())
		  .scrollToCreateAccountBtn()
		  .clkCreateAccountBtn()
		  .clkCookiesCrossBtn()
		  .enterGuestPhoneNo(getPhoneNo())
		  .enterGuestAddress(getSheet().getRow(1).getCell(8).getStringCellValue())
		  .enterGuestCity(getSheet().getRow(1).getCell(9).getStringCellValue())
		  .selectGuestState(getSheet().getRow(1).getCell(10).getStringCellValue())
		  .enterGuestZip(getZipCode())
		  .enterAddressLine2("Test")
		  .clkInfoNextBtn()
		  .clkShippingNextBtn()
		  .selectPaypalReg()
		  .clkConfirmBtn()
		  .clkPlaceOrderBtn()
		  .enterPpUserName(getSheet().getRow(13).getCell(18).getStringCellValue())
		  .clkppUnNextBtn()
		  .enterPpPass(getSheet().getRow(13).getCell(19).getStringCellValue())
		  .clkPpLoginBtn()
		  .clkppContinue();
		
		  String actual=co.getOrderId(); 
		  System.out.println("Your Order id for checkout flow of new user with ground shipping using paypal is " +actual);
		  reader.setCellData("New_user", "Order_ID", 14, actual);
	}

	
	@Test(enabled=true,description = "Placed order using Create an new user with second day shipping using PayPal")//taking too much time to load
	public void NewUser_SecondShipping_PayPal() {
		
		String myEmailAddress = "user+" + System.nanoTime() + "@brother.com"; 
		co = new CheckoutPage(getDriver()); 
		co.getPdpCheckoutPage(getPageUrlStandard(14,1))
		  .clkAddtoCartBtn() 
		  .clkProceedToCartBtn() 
		  .clkCheckoutBtn()
		  .clickLoginButton()
		  .clkCreateNewUserTab()
		  .enterCheckoutEmail(myEmailAddress)
		  .enterCheckoutPass(getSheet().getRow(1).getCell(3).getStringCellValue())
		  .enterCheckoutConfirmPass(getSheet().getRow(1).getCell(4).getStringCellValue())
		  .enterCheckoutFname(getSheet().getRow(1).getCell(5).getStringCellValue())
		  .enterCheckoutLname(getSheet().getRow(1).getCell(6).getStringCellValue())
		  .scrollToCreateAccountBtn()
		  .clkCreateAccountBtn()
		  .clkCookiesCrossBtn()
		  .enterGuestPhoneNo(getPhoneNo())
		  .enterGuestAddress(getSheet().getRow(1).getCell(8).getStringCellValue())
		  .enterGuestCity(getSheet().getRow(1).getCell(9).getStringCellValue())
		  .selectGuestState(getSheet().getRow(1).getCell(10).getStringCellValue())
		  .enterGuestZip(getZipCode())
		  .enterAddressLine2("Test")
		  .clkInfoNextBtn()
		  .clkSecondDayAirShip()
		  .clkShippingNextBtn()
		  .selectPaypalReg()
		  .clkConfirmBtn()
		  .clkPlaceOrderBtn()
		  .enterPpUserName(getSheet().getRow(14).getCell(18).getStringCellValue())
		  .clkppUnNextBtn()
		  .enterPpPass(getSheet().getRow(14).getCell(19).getStringCellValue())
		  .clkPpLoginBtn()
		  .clkppContinue();
		
		  String actual=co.getOrderId(); 
		  System.out.println("Your Order id for checkout flow of new user with second day shipping using paypal is " +actual);
		  reader.setCellData("New_user", "Order_ID", 15, actual);
	}
	
	@Test(enabled=true,description = "Placed order using Create an new user with next day shipping using PayPal")//taking too much time to load
	public void NewUser_NextShipping_PayPal() {
		
		String myEmailAddress = "user+" + System.nanoTime() + "@brother.com"; 
		co = new CheckoutPage(getDriver()); 
		co.getPdpCheckoutPage(getPageUrlStandard(15,1))
		  .clkAddtoCartBtn() 
		  .clkProceedToCartBtn() 
		  .clkCheckoutBtn()
		  .clickLoginButton()
		  .clkCreateNewUserTab()
		  .enterCheckoutEmail(myEmailAddress)
		  .enterCheckoutPass(getSheet().getRow(1).getCell(3).getStringCellValue())
		  .enterCheckoutConfirmPass(getSheet().getRow(1).getCell(4).getStringCellValue())
		  .enterCheckoutFname(getSheet().getRow(1).getCell(5).getStringCellValue())
		  .enterCheckoutLname(getSheet().getRow(1).getCell(6).getStringCellValue())
		  .scrollToCreateAccountBtn()
		  .clkCreateAccountBtn()
		  .clkCookiesCrossBtn()
		  .enterGuestPhoneNo(getPhoneNo())
		  .enterGuestAddress(getSheet().getRow(1).getCell(8).getStringCellValue())
		  .enterGuestCity(getSheet().getRow(1).getCell(9).getStringCellValue())
		  .selectGuestState(getSheet().getRow(1).getCell(10).getStringCellValue())
		  .enterGuestZip(getZipCode())
		  .enterAddressLine2("Test")
		  .clkInfoNextBtn()
		  .clkNextDayAirShip()
		  .clkShippingNextBtn()
		  .selectPaypalReg()
		  .clkConfirmBtn()
		  .clkPlaceOrderBtn()
		  .enterPpUserName(getSheet().getRow(15).getCell(18).getStringCellValue())
		  .clkppUnNextBtn()
		  .enterPpPass(getSheet().getRow(15).getCell(19).getStringCellValue())
		  .clkPpLoginBtn() 
		  .clkppContinue();
			  
	      String actual=co.getOrderId(); 
		  System.out.println("Your Order id for checkout flow of new user with next day shipping using paypal is "+actual); 
		  reader.setCellData("New_user", "Order_ID", 16, actual);
			 
	}
	
	@Test(enabled=true,description = "Placed order using Create an new user with ground shipping using PayPal credit")//taking too much time to load
	public void NewUser_GroundShipping_PayPalCredit() {
		
		String myEmailAddress = "user+" + System.nanoTime() + "@brother.com"; 
		co = new CheckoutPage(getDriver()); 
		co.getPdpCheckoutPage(getPageUrlStandard(16,1))
		  .clkAddtoCartBtn() 
		  .clkProceedToCartBtn() 
		  .clkCheckoutBtn()
		  .clickLoginButton()
		  .clkCreateNewUserTab()
		  .enterCheckoutEmail(myEmailAddress)
		  .enterCheckoutPass(getSheet().getRow(1).getCell(3).getStringCellValue())
		  .enterCheckoutConfirmPass(getSheet().getRow(1).getCell(4).getStringCellValue())
		  .enterCheckoutFname(getSheet().getRow(1).getCell(5).getStringCellValue())
		  .enterCheckoutLname(getSheet().getRow(1).getCell(6).getStringCellValue())
		  .scrollToCreateAccountBtn()
		  .clkCreateAccountBtn()
		  .clkCookiesCrossBtn()
		  .enterGuestPhoneNo(getPhoneNo())
		  .enterGuestAddress(getSheet().getRow(1).getCell(8).getStringCellValue())
		  .enterGuestCity(getSheet().getRow(1).getCell(9).getStringCellValue())
		  .selectGuestState(getSheet().getRow(1).getCell(10).getStringCellValue())
		  .enterGuestZip(getZipCode())
		  .enterAddressLine2("Test")
		  .clkInfoNextBtn()
		  .clkShippingNextBtn()
		  .selectPaypalCrReg()
		  .clkConfirmBtn()
		  .clkPlaceOrderBtn()
		  .enterPpUserName(getSheet().getRow(16).getCell(18).getStringCellValue())
		  .clkppUnNextBtn()
		  .enterPpPass(getSheet().getRow(16).getCell(19).getStringCellValue())
		  .clkPpLoginBtn()
		  .clkppcContinue();
		
		  String actual=co.getOrderId(); 
		  System.out.println("Your Order id for checkout flow of new user with ground shipping using paypal credit is " +actual);
		  reader.setCellData("New_user", "Order_ID", 17, actual);
	}

	@Test(enabled=true,description = "Placed order using Create an new user with second day shipping using PayPal credit")//taking too much time to load
	public void NewUser_SecondDayShipping_PayPalCredit() {
		
		String myEmailAddress = "user+" + System.nanoTime() + "@brother.com"; 
		co = new CheckoutPage(getDriver()); 
		co.getPdpCheckoutPage(getPageUrlStandard(17,1))
		  .clkAddtoCartBtn() 
		  .clkProceedToCartBtn() 
		  .clkCheckoutBtn()
		  .clickLoginButton()
		  .clkCreateNewUserTab()
		  .enterCheckoutEmail(myEmailAddress)
		  .enterCheckoutPass(getSheet().getRow(1).getCell(3).getStringCellValue())
		  .enterCheckoutConfirmPass(getSheet().getRow(1).getCell(4).getStringCellValue())
		  .enterCheckoutFname(getSheet().getRow(1).getCell(5).getStringCellValue())
		  .enterCheckoutLname(getSheet().getRow(1).getCell(6).getStringCellValue())
		  .scrollToCreateAccountBtn()
		  .clkCreateAccountBtn()
		  .clkCookiesCrossBtn()
		  .enterGuestPhoneNo(getPhoneNo())
		  .enterGuestAddress(getSheet().getRow(1).getCell(8).getStringCellValue())
		  .enterGuestCity(getSheet().getRow(1).getCell(9).getStringCellValue())
		  .selectGuestState(getSheet().getRow(1).getCell(10).getStringCellValue())
		  .enterGuestZip(getZipCode())
		  .enterAddressLine2("Test")
		  .clkInfoNextBtn()
		  .clkSecondDayAirShip()
		  .clkShippingNextBtn()
		  .selectPaypalCrReg()
		  .clkConfirmBtn()
		  .clkPlaceOrderBtn()
		  .enterPpUserName(getSheet().getRow(17).getCell(18).getStringCellValue())
		  .clkppUnNextBtn()
		  .enterPpPass(getSheet().getRow(17).getCell(19).getStringCellValue())
		  .clkPpLoginBtn()
		  .clkppcContinue();
		
		  String actual=co.getOrderId(); 
		  System.out.println("Your Order id for checkout flow of new user with second day shipping using paypal creditis " +actual);
		  reader.setCellData("New_user", "Order_ID", 15, actual);
	}
	
	@Test(enabled=true,description = "Placed order using Create an new user with next day shipping using PayPal credit")//taking too much time to load
	public void NewUser_NextDayShipping_PayPalCredit() {
		
		String myEmailAddress = "user+" + System.nanoTime() + "@brother.com"; 
		co = new CheckoutPage(getDriver()); 
		co.getPdpCheckoutPage(getPageUrlStandard(18,1))
		  .clkAddtoCartBtn() 
		  .clkProceedToCartBtn() 
		  .clkCheckoutBtn()
		  .clickLoginButton()
		  .clkCreateNewUserTab()
		  .enterCheckoutEmail(myEmailAddress)
		  .enterCheckoutPass(getSheet().getRow(1).getCell(3).getStringCellValue())
	      .enterCheckoutConfirmPass(getSheet().getRow(1).getCell(4).getStringCellValue())
		  .enterCheckoutFname(getSheet().getRow(1).getCell(5).getStringCellValue())
		  .enterCheckoutLname(getSheet().getRow(1).getCell(6).getStringCellValue())
		  .scrollToCreateAccountBtn()
		  .clkCreateAccountBtn()
		  .clkCookiesCrossBtn()
		  .enterGuestPhoneNo(getPhoneNo())
		  .enterGuestAddress(getSheet().getRow(1).getCell(8).getStringCellValue())
		  .enterGuestCity(getSheet().getRow(1).getCell(9).getStringCellValue())
		  .selectGuestState(getSheet().getRow(1).getCell(10).getStringCellValue())
		  .enterGuestZip(getZipCode())
		  .enterAddressLine2("Test")
		  .clkInfoNextBtn()
		  .clkNextDayAirShip()
		  .clkShippingNextBtn()
		  .selectPaypalCrReg()
		  .clkConfirmBtn()
		  .clkPlaceOrderBtn()
		  .enterPpUserName(getSheet().getRow(18).getCell(18).getStringCellValue())
		  .clkppUnNextBtn()
		  .enterPpPass(getSheet().getRow(18).getCell(19).getStringCellValue())
		  .clkPpLoginBtn()
		  .clkppcContinue();
		
		  String actual=co.getOrderId(); 
		  System.out.println("Your Order id for checkout flow of new user with next day shipping using paypal creditis " +actual);
		  reader.setCellData("New_user", "Order_ID", 15, actual);
	}
	
}



