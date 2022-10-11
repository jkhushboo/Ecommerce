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

//@Listeners(ExecutionListener.class)
	public class CheckoutTest extends TestBase {

		
		  private XSSFSheet getSheet() { return getTestData().getSheet("Guest_user"); }
		
		  private CheckoutPage co;
			DataFormatter formatter = new DataFormatter();
			private Cell cell;
			
			Xls_Reader reader = new Xls_Reader("src/main/resources/TestDataSheet_uat.xlsx");

			private String getPageUrlStandard(int row,int column) {
				return getEnv().concat((getSheet().getRow(row).getCell(column).getStringCellValue()));
			}
			
			private String getState(int row,int column) {
				return getEnv().concat((getSheet().getRow(row).getCell(column).getStringCellValue()));
			}
			
			private String getPageUrlSecond(int row,int column) {
				return getEnv().concat((getSheet().getRow(row).getCell(column).getStringCellValue()));
			}
			
			private String getPageUrlNext(int row,int column) {
				return getEnv().concat((getSheet().getRow(row).getCell(column).getStringCellValue()));
			}
			
			private String getPageUrlNext1() { return
					  getEnv().concat((getSheet().getRow(9).getCell(1).getStringCellValue())); }
			  
			  private String getPageUrlNext2() { return
			  getEnv().concat((getSheet().getRow(10).getCell(1).getStringCellValue())); }
			
			private String getLoginPageUrl() {
				return getEnv().concat(CheckoutTestData.LOGIN_PAGE);
			}
			
			private String getCardNo() {
				 cell= getSheet().getRow(1).getCell(13);
				 return formatter.formatCellValue(cell);
									
			}
			
			private String getCardNo2() {
				 cell= getSheet().getRow(2).getCell(13);
				 return formatter.formatCellValue(cell);
									
			}
			
			private String getCardNo3() {
				 cell= getSheet().getRow(3).getCell(13);
				 return formatter.formatCellValue(cell);
									
			}
			
			private String getCardNo4() {
				 cell= getSheet().getRow(4).getCell(13);
				 return formatter.formatCellValue(cell);
									
			}
			
			private String getPhoneNo() {
				 cell= getSheet().getRow(2).getCell(5);
				 return formatter.formatCellValue(cell);						
			}
			
			private String getZipCode(int row , int column) {
				 cell= getSheet().getRow(row).getCell(column);
				 return formatter.formatCellValue(cell);						
			}
			
			private String getEXDateMM() {
				 cell= getSheet().getRow(1).getCell(14);
				 return formatter.formatCellValue(cell);						
			}
			
			private String getEXDateYY() {
				 cell= getSheet().getRow(1).getCell(15);
				 return formatter.formatCellValue(cell);						
			}
			
			private String getCVV() {
				 cell= getSheet().getRow(1).getCell(16);
				 return formatter.formatCellValue(cell);						
			}
	    
		
		@Test(priority=0,enabled=true,description = "Checkout-Placed order using guest user with ground shipping using visa credit card")//need to be more than 2 items cent value problem
		public void GuestUser_GroundShipping_Visa() {
			
			reader.removeColumn("Guest_user", 19);
			reader.addColumn("Guest_user", "Order_ID");
			
			co = new CheckoutPage(getDriver());
			co.getPdpCheckoutPage(getPageUrlStandard(1,1)) 
			  .clkAddtoCartBtn()
			  .clkProceedToCartBtn() 
			  .clkCheckoutBtn() 
			  .clickLoginButton()
			  .clkCheckoutGuestBtn()
			  .enterGuestFname(getSheet().getRow(1).getCell(2).getStringCellValue())
			  .enterGuestLname(getSheet().getRow(1).getCell(3).getStringCellValue())
			  .enterGuestEmail(getSheet().getRow(1).getCell(4).getStringCellValue())
			  .enterGuestPhoneNo(getPhoneNo())
			  .enterGuestAddress(getSheet().getRow(1).getCell(6).getStringCellValue())
			  .enterGuestCity(getSheet().getRow(1).getCell(7).getStringCellValue())
			  .selectGuestState(getState(1,10)) 
			  .enterGuestZip(getZipCode(1,11))
			  .enterAddressLine2("Test") 
			  .clkInfoNextBtn() 
			  .clkViewShippingNextBtn()
			  .switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
			  .enterNameOnCardCc(getSheet().getRow(1).getCell(11).getStringCellValue())
			  .selectCardTypeVisa()  
			  .enterCardNoCc(getCardNo())
			  .enterExpiryMmCc(getEXDateMM()) 
			  .enterExpiryYyCc(getEXDateYY())
			  .enterCvvCc(getCVV()) 
			  .switchToPaymentPage() 
			  .clkConfirmBtn()
			  .clkPlaceOrderBtn();
			  
			  String actual=co.getOrderId(); 
			  System.out.println("Your Order id for checkout flow of guest user with ground shipping using Visa cc is "+actual); reader.setCellData("Guest_user", "Order_ID", 2, actual);
			  
			 
		}
		
		@Test(enabled=false,description = "Checkout-Placed order using guest user with ground shipping using master credit card")//need to be more than 2 items cent value problem
		public void GuestUser_GroundShipping_Master() {
			
			co = new CheckoutPage(getDriver()); 
			co.getPdpCheckoutPage(getPageUrlStandard(2,1))
			  .clkAddtoCartBtn() 
			  .clkProceedToCartBtn() 
			  .clkCheckoutBtn()
			  .clickLoginButton()
			  .clkCheckoutGuestBtn()
			  .enterGuestFname(getSheet().getRow(2).getCell(2).getStringCellValue())
			  .enterGuestLname(getSheet().getRow(2).getCell(3).getStringCellValue())
			  .enterGuestEmail(getSheet().getRow(2).getCell(4).getStringCellValue())
			  .enterGuestPhoneNo(getPhoneNo())
			  .enterGuestAddress(getSheet().getRow(2).getCell(6).getStringCellValue())
			  .enterGuestCity(getSheet().getRow(2).getCell(7).getStringCellValue())
			  .selectGuestState(CheckoutTestData.STATE)
			  .enterGuestZip(getZipCode(2,11))
			  .enterAddressLine2("Test")
			  .clkInfoNextBtn()
			  .clkViewShippingNextBtn()
			  .switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
			  .enterNameOnCardCc(getSheet().getRow(2).getCell(11).getStringCellValue())
			  .selectCardTypeMaster()
			  .enterCardNoCc(getCardNo2()) 
			  .enterExpiryMmCc(getEXDateMM())
			  .enterExpiryYyCc(getEXDateYY()) 
			  .enterCvvCc(getCVV()) 
			  .switchToPaymentPage()
			  .clkConfirmBtn() 
			  .clkPlaceOrderBtn();
			
			   String actual=co.getOrderId(); 
			   System.out.println("Your Order id for checkout flow of guest user with ground shipping using Master cc is "+actual); 
			   reader.setCellData("Guest_user", "Order_ID", 3, actual) ;
		}
		
		@Test(enabled=false,description = "Checkout-Placed order using guest user with ground shipping using Amex credit card")//need to be more than 2 items cent value problem
		public void GuestUser_GroundShipping_Amex() {
			
			co = new CheckoutPage(getDriver()); 
			co.getPdpCheckoutPage(getPageUrlStandard(3,1))
			  .clkAddtoCartBtn() 
			  .clkProceedToCartBtn() 
			  .clkCheckoutBtn()
			  .clickLoginButton()
			  .clkCheckoutGuestBtn()
			  .enterGuestFname(getSheet().getRow(3).getCell(2).getStringCellValue())
			  .enterGuestLname(getSheet().getRow(3).getCell(3).getStringCellValue())
			  .enterGuestEmail(getSheet().getRow(3).getCell(4).getStringCellValue())
			  .enterGuestPhoneNo(getPhoneNo())
			  .enterGuestAddress(getSheet().getRow(3).getCell(6).getStringCellValue())
			  .enterGuestCity(getSheet().getRow(3).getCell(7).getStringCellValue())
			  .selectGuestState(CheckoutTestData.STATE)
			  .enterGuestZip(getZipCode(3,11))
			  .enterAddressLine2("Test")
			  .clkInfoNextBtn() 
			  .clkViewShippingNextBtn()
			  .switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
			  .enterNameOnCardCc(getSheet().getRow(3).getCell(10).getStringCellValue())
			  .selectCardTypeAmex()
			  .enterCardNoCc(getCardNo3())
			  .enterExpiryMmCc(getEXDateMM())
			  .enterExpiryYyCc(getEXDateYY())
			  .enterCvvCc(getCVV())
			  .switchToPaymentPage()
			  .clkConfirmBtn()
			  .clkPlaceOrderBtn();
			  
			  String actual=co.getOrderId(); 
			  System.out.println("Your Order id for checkout flow of guest user with groung shipping using Amex cc is " +actual);
			  reader.setCellData("Guest_user", "Order_ID", 4, actual);
				
		}
		
		@Test(enabled=false,description = "Checkout-Placed order using guest user with ground shipping using Discover credit card")//need to be more than 2 items cent value problem
		public void GuestUser_GroundShipping_Disc() {
			
			co = new CheckoutPage(getDriver()); 
			co.getPdpCheckoutPage(getPageUrlStandard(4,1))
			  .clkAddtoCartBtn() 
			  .clkProceedToCartBtn() 
			  .clkCheckoutBtn()
			  .clickLoginButton()
			  .clkCheckoutGuestBtn()
			  .enterGuestFname(getSheet().getRow(4).getCell(2).getStringCellValue())
			  .enterGuestLname(getSheet().getRow(4).getCell(3).getStringCellValue())
			  .enterGuestEmail(getSheet().getRow(4).getCell(4).getStringCellValue())
			  .enterGuestPhoneNo(getPhoneNo())
			  .enterGuestAddress(getSheet().getRow(4).getCell(6).getStringCellValue())
			  .enterGuestCity(getSheet().getRow(4).getCell(7).getStringCellValue())
			  .selectGuestState(CheckoutTestData.STATE)
			  .enterGuestZip(getZipCode(4,11))
			  .enterAddressLine2("Test")
			  .clkInfoNextBtn() 
			  .clkViewShippingNextBtn()
			  .switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
			  .enterNameOnCardCc(getSheet().getRow(4).getCell(10).getStringCellValue())
			  .selectCardTypeDiscover()
			  .enterCardNoCc(getCardNo4())
			  .enterExpiryMmCc(getEXDateMM())
			  .enterExpiryYyCc(getEXDateYY())
			  .enterCvvCc(getCVV())
			  .switchToPaymentPage()
			  .clkConfirmBtn()
			  .clkPlaceOrderBtn();
			  
			  String actual=co.getOrderId(); 
			  System.out.println("Your Order id for checkout flow of guest user with ground shipping using Discover cc is " +actual); 
			  reader.setCellData("Guest_user", "Order_ID", 5, actual);
		}
		
		@Test(enabled=false,description = "Checkout-Placed order using guest user using Visa credit card with second day shipping")//need to be more than 2 items cent value problem
		public void GuestUser_SecondShipping_Visa() {
			
			co = new CheckoutPage(getDriver()); 
			co.getPdpCheckoutPage(getPageUrlStandard(5,1))
			  .clkAddtoCartBtn() 
			  .clkProceedToCartBtn() 
			  .clkCheckoutBtn()
			  .clickLoginButton()
			  .clkCheckoutGuestBtn()
			  .enterGuestFname(getSheet().getRow(1).getCell(2).getStringCellValue())
			  .enterGuestLname(getSheet().getRow(1).getCell(3).getStringCellValue())
			  .enterGuestEmail(getSheet().getRow(1).getCell(4).getStringCellValue())
			  .enterGuestPhoneNo(getPhoneNo())
			  .enterGuestAddress(getSheet().getRow(1).getCell(6).getStringCellValue())
			  .enterGuestCity(getSheet().getRow(1).getCell(7).getStringCellValue())
			  .selectGuestState(CheckoutTestData.STATE)
			  .enterGuestZip(getZipCode(5,11))
			  .enterAddressLine2("Test")
			  .clkInfoNextBtn() 
			  .clkSecondDayAirShip()
			  .clkShippingNextBtn()
			  .switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
			  .enterNameOnCardCc(getSheet().getRow(1).getCell(11).getStringCellValue())
			  .selectCardTypeVisa()  
			  .enterCardNoCc(getCardNo())
			  .enterExpiryMmCc(getEXDateMM()) 
			  .enterExpiryYyCc(getEXDateYY())
			  .enterCvvCc(getCVV()) 
			  .switchToPaymentPage() .clkConfirmBtn()
			  .clkPlaceOrderBtn(); 
			  
			  String actual=co.getOrderId(); 
			  System.out.println("Your Order id for checkout flow of guest user with second day shipping using Visa cc is " +actual);
			  reader.setCellData("Guest_user", "Order_ID", 6, actual);
			  
	}
		
		@Test(enabled=false,description = "Checkout-Placed order using guest user using Master credit card with second day shipping")//need to be more than 2 items cent value problem
		public void GuestUser_SecondShipping_Master() {
			
			co = new CheckoutPage(getDriver()); 
			co.getPdpCheckoutPage(getPageUrlStandard(6,1))
			  .clkAddtoCartBtn() 
			  .clkProceedToCartBtn() 
			  .clkCheckoutBtn()
			  .clickLoginButton()
			  .clkCheckoutGuestBtn()
			  .enterGuestFname(getSheet().getRow(2).getCell(2).getStringCellValue())
			  .enterGuestLname(getSheet().getRow(2).getCell(3).getStringCellValue())
			  .enterGuestEmail(getSheet().getRow(2).getCell(4).getStringCellValue())
			  .enterGuestPhoneNo(getPhoneNo())
			  .enterGuestAddress(getSheet().getRow(2).getCell(6).getStringCellValue())
			  .enterGuestCity(getSheet().getRow(2).getCell(7).getStringCellValue())
			  .selectGuestState(CheckoutTestData.STATE)
			  .enterGuestZip(getZipCode(6,11))
			  .enterAddressLine2("Test")
			  .clkInfoNextBtn() 
			  .clkSecondDayAirShip()
			  .clkShippingNextBtn()
			  .switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
			  .enterNameOnCardCc(getSheet().getRow(2).getCell(10).getStringCellValue())
			  .selectCardTypeMaster()
			  .enterCardNoCc(getCardNo2())
			  .enterExpiryMmCc(getEXDateMM())
			  .enterExpiryYyCc(getEXDateYY())
			  .enterCvvCc(getCVV())
			  .switchToPaymentPage()
			  .clkConfirmBtn()
			  .clkPlaceOrderBtn();
			 
			  String actual=co.getOrderId(); 
			  System.out.println("Your Order id for checkout flow of guest user with second day shipping using Mastercard cc is " +actual);
			  reader.setCellData("Guest_user", "Order_ID", 7, actual);
		}
			
		@Test(enabled=false,description = "Checkout-Placed order using guest user using Amex credit card with second day shipping")//need to be more than 2 items cent value problem
		public void GuestUser_SecondShipping_Amex() {
			
			co = new CheckoutPage(getDriver()); 
			co.getPdpCheckoutPage(getPageUrlStandard(7,1))
			  .clkAddtoCartBtn() 
			  .clkProceedToCartBtn() 
			  .clkCheckoutBtn()
			  .clickLoginButton()
			  .clkCheckoutGuestBtn()
			  .enterGuestFname(getSheet().getRow(3).getCell(2).getStringCellValue())
			  .enterGuestLname(getSheet().getRow(3).getCell(3).getStringCellValue())
			  .enterGuestEmail(getSheet().getRow(3).getCell(4).getStringCellValue())
			  .enterGuestPhoneNo(getPhoneNo())
			  .enterGuestAddress(getSheet().getRow(3).getCell(6).getStringCellValue())
			  .enterGuestCity(getSheet().getRow(3).getCell(7).getStringCellValue())
			  .selectGuestState(CheckoutTestData.STATE)
			  .enterGuestZip(getZipCode(7,11))
			  .enterAddressLine2("Test")
			  .clkInfoNextBtn() 
			  .clkSecondDayAirShip()
			  .clkShippingNextBtn()
			  .switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
			  .enterNameOnCardCc(getSheet().getRow(3).getCell(10).getStringCellValue())
			  .selectCardTypeAmex()
			  .enterCardNoCc(getCardNo3())
			  .enterExpiryMmCc(getEXDateMM())
			  .enterExpiryYyCc(getEXDateYY())
			  .enterCvvCc(getCVV())
			  .switchToPaymentPage()
			  .clkConfirmBtn()
			  .clkPlaceOrderBtn();
			 
			  String actual=co.getOrderId(); 
			  System.out.println("Your Order id for checkout flow of guest user with second day shipping using Amex cc is " +actual);
			  reader.setCellData("Guest_user", "Order_ID", 8, actual);
		}
		
		@Test(enabled=false,description = "Checkout-Placed order using guest user using Discover credit card with second day shipping")//need to be more than 2 items cent value problem
		public void GuestUser_SecondShipping_Disc() {
			
			co = new CheckoutPage(getDriver()); 
			co.getPdpCheckoutPage(getPageUrlStandard(8,1))
			  .clkAddtoCartBtn() 
			  .clkProceedToCartBtn() 
			  .clkCheckoutBtn()
			  .clickLoginButton()
			  .clkCheckoutGuestBtn()
			  .enterGuestFname(getSheet().getRow(4).getCell(2).getStringCellValue())
			  .enterGuestLname(getSheet().getRow(4).getCell(3).getStringCellValue())
			  .enterGuestEmail(getSheet().getRow(4).getCell(4).getStringCellValue())
			  .enterGuestPhoneNo(getPhoneNo())
			  .enterGuestAddress(getSheet().getRow(4).getCell(6).getStringCellValue())
			  .enterGuestCity(getSheet().getRow(4).getCell(7).getStringCellValue())
			  .selectGuestState(CheckoutTestData.STATE)
			  .enterGuestZip(getZipCode(8,11))
			  .enterAddressLine2("Test")
			  .clkInfoNextBtn() 
			  .clkSecondDayAirShip()
			  .clkShippingNextBtn()
			  .switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
			  .enterNameOnCardCc(getSheet().getRow(4).getCell(10).getStringCellValue())
			  .selectCardTypeDiscover()
			  .enterCardNoCc(getCardNo4())
			  .enterExpiryMmCc(getEXDateMM())
			  .enterExpiryYyCc(getEXDateYY())
			  .enterCvvCc(getCVV())
			  .switchToPaymentPage()
			  .clkConfirmBtn()
			  .clkPlaceOrderBtn();
			
			  String actual=co.getOrderId(); 
			  System.out.println("Your Order id for checkout flow of guest user with second day shipping using Discover cc is " +actual);
			  reader.setCellData("Guest_user", "Order_ID", 9, actual);
	}
		
		@Test(enabled=false,description = "Checkout-Placed order using guest user using Visa credit card with next day shipping")//need to be more than 2 items cent value problem
		public void GuestUser_NextShipping_Visa() {
			
			co = new CheckoutPage(getDriver()); 
			co.getPdpCheckoutPage(getPageUrlStandard(9,1))
			  .clkAddtoCartBtn() 
			  .clkProceedToCartBtn() 
			  .clkCheckoutBtn()
			  .clickLoginButton()
			  .clkCheckoutGuestBtn()
			  .enterGuestFname(getSheet().getRow(1).getCell(2).getStringCellValue())
			  .enterGuestLname(getSheet().getRow(1).getCell(3).getStringCellValue())
			  .enterGuestEmail(getSheet().getRow(1).getCell(4).getStringCellValue())
			  .enterGuestPhoneNo(getPhoneNo())
			  .enterGuestAddress(getSheet().getRow(1).getCell(6).getStringCellValue())
			  .enterGuestCity(getSheet().getRow(1).getCell(7).getStringCellValue())
			  .selectGuestState(CheckoutTestData.STATE)
			  .enterGuestZip(getZipCode(9,11))
			  .enterAddressLine2("Test")
			  .clkInfoNextBtn() 
			  .clkNextDayAirShip()
			  .clkShippingNextBtn()
			  .switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
			  .enterNameOnCardCc(getSheet().getRow(1).getCell(11).getStringCellValue())
			  .selectCardTypeVisa()   
			  .enterCardNoCc(getCardNo())
			  .enterExpiryMmCc(getEXDateMM()) 
			  .enterExpiryYyCc(getEXDateYY())
			  .enterCvvCc(getCVV()) 
			  .switchToPaymentPage() 
			  .clkConfirmBtn()
			  .clkPlaceOrderBtn(); 
			 
			  String actual=co.getOrderId(); 
			  System.out.println("Your Order id for checkout flow of guest user with next day shipping using Visa cc is " +actual);
			  reader.setCellData("Guest_user", "Order_ID", 10, actual);
			  
	}
		
		@Test(enabled=false,description = "Checkout-Placed order using guest user using Master credit card with next day shipping")//need to be more than 2 items cent value problem
		public void GuestUser_NextShipping_Master() {
			
			co = new CheckoutPage(getDriver()); 
			co.getPdpCheckoutPage(getPageUrlStandard(10,1))
			  .clkAddtoCartBtn() 
			  .clkProceedToCartBtn() 
			  .clkCheckoutBtn()
			  .clickLoginButton()
			  .clkCheckoutGuestBtn()
			  .enterGuestFname(getSheet().getRow(2).getCell(2).getStringCellValue())
			  .enterGuestLname(getSheet().getRow(2).getCell(3).getStringCellValue())
			  .enterGuestEmail(getSheet().getRow(2).getCell(4).getStringCellValue())
			  .enterGuestPhoneNo(getPhoneNo())
			  .enterGuestAddress(getSheet().getRow(2).getCell(6).getStringCellValue())
			  .enterGuestCity(getSheet().getRow(2).getCell(7).getStringCellValue())
			  .selectGuestState(CheckoutTestData.STATE)
			  .enterGuestZip(getZipCode(10,11))
			  .enterAddressLine2("Test")
			  .clkInfoNextBtn() 
			  .clkNextDayAirShip()
			  .clkShippingNextBtn()
			  .switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
			  .enterNameOnCardCc(getSheet().getRow(2).getCell(11).getStringCellValue())
			  .selectCardTypeMaster()
			  .enterCardNoCc(getCardNo2())
			  .enterExpiryMmCc(getEXDateMM())
			  .enterExpiryYyCc(getEXDateYY())
			  .enterCvvCc(getCVV())
			  .switchToPaymentPage()
			  .clkConfirmBtn()
			  .clkPlaceOrderBtn();
			 
			  String actual=co.getOrderId(); 
			  System.out.println("Your Order id for checkout flow of guest user with next day shipping using Mastercard cc is " +actual);
			  reader.setCellData("Guest_user", "Order_ID", 11, actual);
			  
	}
		
		@Test(enabled=false,description = "Checkout-Placed order using guest user using Amex credit card with next day shipping")//need to be more than 2 items cent value problem
		public void GuestUser_NextShipping_Amex() {
			
			co = new CheckoutPage(getDriver()); 
			co.getPdpCheckoutPage(getPageUrlStandard(11,1))
			  .clkAddtoCartBtn() 
			  .clkProceedToCartBtn() 
			  .clkCheckoutBtn()
			  .clickLoginButton()
			  .clkCheckoutGuestBtn()
			  .enterGuestFname(getSheet().getRow(1).getCell(2).getStringCellValue())
			  .enterGuestLname(getSheet().getRow(1).getCell(3).getStringCellValue())
			  .enterGuestEmail(getSheet().getRow(1).getCell(4).getStringCellValue())
			  .enterGuestPhoneNo(getPhoneNo())
			  .enterGuestAddress(getSheet().getRow(1).getCell(6).getStringCellValue())
			  .enterGuestCity(getSheet().getRow(1).getCell(7).getStringCellValue())
			  .selectGuestState(CheckoutTestData.STATE)
			  .enterGuestZip(getZipCode(11,11))
			  .enterAddressLine2("Test")
			  .clkInfoNextBtn()
			  .clkNextDayAirShip()
			  .clkShippingNextBtn()
			  .switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
			  .enterNameOnCardCc(getSheet().getRow(3).getCell(11).getStringCellValue())
			  .selectCardTypeAmex()
			  .enterCardNoCc(getCardNo3())
			  .enterExpiryMmCc(getEXDateMM()) 
			  .enterExpiryYyCc(getEXDateYY())
			  .enterCvvCc(getCVV()) 
			  .switchToPaymentPage() 
			  .clkConfirmBtn()
			  .clkPlaceOrderBtn(); 
			 
			  String actual=co.getOrderId(); 
			  System.out.println("Your Order id for checkout flow of guest user with next day shipping using Amex cc is " +actual);
			  reader.setCellData("Guest_user", "Order_ID", 12, actual);
	}
		
		
		@Test(enabled=false,description = "Checkout-Placed order using guest user using Discover credit card with next day shipping")//need to be more than 2 items cent value problem
		public void GuestUser_NextShipping_Disc() {
			
			co = new CheckoutPage(getDriver()); 
			co.getPdpCheckoutPage(getPageUrlStandard(12,1))
			  .clkAddtoCartBtn() 
			  .clkProceedToCartBtn() 
			  .clkCheckoutBtn()
			  .clickLoginButton()
			  .clkCheckoutGuestBtn()
			  .enterGuestFname(getSheet().getRow(1).getCell(2).getStringCellValue())
			  .enterGuestLname(getSheet().getRow(1).getCell(3).getStringCellValue())
			  .enterGuestEmail(getSheet().getRow(1).getCell(4).getStringCellValue())
			  .enterGuestPhoneNo(getPhoneNo())
			  .enterGuestAddress(getSheet().getRow(1).getCell(6).getStringCellValue())
			  .enterGuestCity(getSheet().getRow(1).getCell(7).getStringCellValue())
			  .selectGuestState(CheckoutTestData.STATE)
			  .enterGuestZip(getZipCode(12,11))
			  .enterAddressLine2("Test")
			  .clkInfoNextBtn()
			  .clkNextDayAirShip()
			  .clkShippingNextBtn()
			  .switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
			  .enterNameOnCardCc(getSheet().getRow(4).getCell(11).getStringCellValue())
			  .selectCardTypeDiscover()
			  .enterCardNoCc(getCardNo4())
			  .enterExpiryMmCc(getEXDateMM()) 
			  .enterExpiryYyCc(getEXDateYY())
			  .enterCvvCc(getCVV()) 
			  .switchToPaymentPage() 
			  .clkConfirmBtn()
			  .clkPlaceOrderBtn();
			  
			  String actual=co.getOrderId(); 
			  System.out.println("Your Order id for checkout flow of guest user with next day shipping using discover cc is " +actual);
			  reader.setCellData("Guest_user", "Order_ID", 13, actual);
	}
		
		@Test(enabled=false,description = "Checkout-Placed order using guest user with ground shipping using paypal", groups = { "closed","checkout" })
		public void Guestuser_GroundShipping_Paypal() {

			co = new CheckoutPage(getDriver()); 
			co.getPdpCheckoutPage(getPageUrlStandard(13,1))
			  .clkAddtoCartBtn() 
			  .clkProceedToCartBtn() 
			  .clkCheckoutBtn()
			  .clickLoginButton()
			  .clkCheckoutGuestBtn()
			  .enterGuestFname(getSheet().getRow(4).getCell(2).getStringCellValue())
			  .enterGuestLname(getSheet().getRow(4).getCell(3).getStringCellValue())
			  .enterGuestEmail(getSheet().getRow(4).getCell(4).getStringCellValue())
			  .enterGuestPhoneNo(getPhoneNo())
			  .enterGuestAddress(getSheet().getRow(4).getCell(6).getStringCellValue())
			  .enterGuestCity(getSheet().getRow(4).getCell(7).getStringCellValue())
			  .selectGuestState(CheckoutTestData.STATE)
			  .enterGuestZip(getZipCode(13,11))
			  .enterAddressLine2("Test")
			  .clkInfoNextBtn() 
			  .clkShippingNextBtn()
			  .selectPaypal()
			  .clkConfirmBtn()
			  .clkPlaceOrderBtn()
			  .enterPpUserName(getSheet().getRow(13).getCell(17).getStringCellValue())
			  .clkppUnNextBtn()
			  .enterPpPass(getSheet().getRow(13).getCell(18).getStringCellValue())
			  .clkPpLoginBtn()
			  .clkppContinue();
			
			  String actual=co.getOrderId(); 
			  System.out.println("Your Order id for checkout flow of guest user with ground shipping using paypal is " +actual);
			  reader.setCellData("Guest_user", "Order_ID", 14, actual);
		}
		
		@Test(enabled=false,description = "Checkout-Placed order using guest user with second day shipping using paypal")
		public void Guestuser_SecondShipping_Paypal() {

			co = new CheckoutPage(getDriver()); 
			co.getPdpCheckoutPage(getPageUrlStandard(14,1))
			  .clkAddtoCartBtn() 
			  .clkProceedToCartBtn() 
			  .clkCheckoutBtn()
			  .clickLoginButton()
			  .clkCheckoutGuestBtn()
			  .enterGuestFname(getSheet().getRow(4).getCell(2).getStringCellValue())
			  .enterGuestLname(getSheet().getRow(4).getCell(3).getStringCellValue())
			  .enterGuestEmail(getSheet().getRow(4).getCell(4).getStringCellValue())
			  .enterGuestPhoneNo(getPhoneNo())
			  .enterGuestAddress(getSheet().getRow(4).getCell(6).getStringCellValue())
			  .enterGuestCity(getSheet().getRow(4).getCell(7).getStringCellValue())
			  .selectGuestState(CheckoutTestData.STATE)
			  .enterGuestZip(getZipCode(14,11))
			  .enterAddressLine2("Test")
			  .clkInfoNextBtn() 
			  .clkSecondDayAirShip()
			  .clkShippingNextBtn()
			  .selectPaypal()
			  .clkConfirmBtn()
			  .clkPlaceOrderBtn()
			  .enterPpUserName(getSheet().getRow(14).getCell(17).getStringCellValue())
			  .clkppUnNextBtn()
			  .enterPpPass(getSheet().getRow(14).getCell(18).getStringCellValue())
			  .clkPpLoginBtn()
			  .clkppContinue();
			
			  String actual=co.getOrderId(); 
			  System.out.println("Your Order id for checkout flow of guest user with second day shipping using paypal is " +actual);
			  reader.setCellData("Guest_user", "Order_ID", 15, actual);
		}
		
		@Test(enabled=false,description = "Checkout-Placed order using guest user with next day shipping using paypal")
		public void Guestuser_NextShipping_Paypal() {

			co = new CheckoutPage(getDriver()); 
			co.getPdpCheckoutPage(getPageUrlStandard(15,1))
			  .clkAddtoCartBtn() 
			  .clkProceedToCartBtn() 
			  .clkCheckoutBtn()
			  .clickLoginButton()
			  .clkCheckoutGuestBtn()
			  .enterGuestFname(getSheet().getRow(4).getCell(2).getStringCellValue())
			  .enterGuestLname(getSheet().getRow(4).getCell(3).getStringCellValue())
			  .enterGuestEmail(getSheet().getRow(4).getCell(4).getStringCellValue())
			  .enterGuestPhoneNo(getPhoneNo())
			  .enterGuestAddress(getSheet().getRow(4).getCell(6).getStringCellValue())
			  .enterGuestCity(getSheet().getRow(4).getCell(7).getStringCellValue())
			  .selectGuestState(CheckoutTestData.STATE)
			  .enterGuestZip(getZipCode(15,11))
			  .enterAddressLine2("Test")
			  .clkInfoNextBtn() 
			  .clkNextDayAirShip()
			  .clkShippingNextBtn()
			  .selectPaypal()
			  .clkConfirmBtn()
			  .clkPlaceOrderBtn()
			  .enterPpUserName(getSheet().getRow(15).getCell(17).getStringCellValue())
			  .clkppUnNextBtn()
			  .enterPpPass(getSheet().getRow(15).getCell(18).getStringCellValue())
			  .clkPpLoginBtn()
			  .clkppContinue();
			
			  String actual=co.getOrderId(); 
			  System.out. println("Your Order id for checkout flow of guest user with next day shipping using paypal is " +actual);
			  reader.setCellData("Guest_user", "Order_ID", 16, actual);
					 
		}
		
		@Test(enabled=false,description = "Checkout-Placed order using guest user with ground shipping using PayPal credit")//need to be more than 2 items cent value problem
		public void GuestUser_GroundShipping_PayPalCredit() {
			
			co = new CheckoutPage(getDriver()); 
			co.getPdpCheckoutPage(getPageUrlStandard(16,1))
			  .clkAddtoCartBtn() 
			  .clkProceedToCartBtn() 
			  .clkCheckoutBtn()
			  .clickLoginButton()
			  .clkCheckoutGuestBtn()
			  .enterGuestFname(getSheet().getRow(2).getCell(2).getStringCellValue())
			  .enterGuestLname(getSheet().getRow(2).getCell(3).getStringCellValue())
			  .enterGuestEmail(getSheet().getRow(2).getCell(4).getStringCellValue())
			  .enterGuestPhoneNo(getPhoneNo())
			  .enterGuestAddress(getSheet().getRow(2).getCell(6).getStringCellValue())
			  .enterGuestCity(getSheet().getRow(2).getCell(7).getStringCellValue())
			  .selectGuestState(CheckoutTestData.STATE)
			  .enterGuestZip(getZipCode(16,11))
			  .enterAddressLine2("Test")
			  .clkInfoNextBtn() 
			  .clkShippingNextBtn()
			  .selectPaypalCr()
			  .clkConfirmBtn()
			  .clkPlaceOrderBtn()
			  .enterPpUserName(getSheet().getRow(15).getCell(17).getStringCellValue())
			  .clkppUnNextBtn()
			  .enterPpPass(getSheet().getRow(15).getCell(18).getStringCellValue())
			  .clkPpLoginBtn()
			  .clkppcContinue();
				
				String actual=co.getOrderId(); 
				System.out. println("Your Order id for checkout flow of guest user with ground shipping using paypal credit is " +actual);
				reader.setCellData("Guest_user", "Order_ID", 17, actual);
			  
	}
		
		@Test(enabled=false,description = "Checkout-Placed order using guest user with second day shipping using PayPal credit")//need to be more than 2 items cent value problem
		public void GuestUser_SecondDayShipping_PayPalCredit() {
			
			co = new CheckoutPage(getDriver()); 
			co.getPdpCheckoutPage(getPageUrlStandard(17,1))
			  .clkAddtoCartBtn() 
			  .clkProceedToCartBtn() 
			  .clkCheckoutBtn()
			  .clickLoginButton()
			  .clkCheckoutGuestBtn()
			  .enterGuestFname(getSheet().getRow(2).getCell(2).getStringCellValue())
			  .enterGuestLname(getSheet().getRow(2).getCell(3).getStringCellValue())
			  .enterGuestEmail(getSheet().getRow(2).getCell(4).getStringCellValue())
			  .enterGuestPhoneNo(getPhoneNo())
			  .enterGuestAddress(getSheet().getRow(2).getCell(6).getStringCellValue())
			  .enterGuestCity(getSheet().getRow(2).getCell(7).getStringCellValue())
			  .selectGuestState(CheckoutTestData.STATE)
			  .enterGuestZip(getZipCode(17,11))
			  .enterAddressLine2("Test")
			  .clkInfoNextBtn() 
			  .clkSecondDayAirShip()
			  .clkShippingNextBtn()
			  .selectPaypalCr()
			  .clkConfirmBtn()
			  .clkPlaceOrderBtn()
			  .enterPpUserName(getSheet().getRow(15).getCell(17).getStringCellValue())
			  .clkppUnNextBtn()
			  .enterPpPass(getSheet().getRow(15).getCell(18).getStringCellValue())
			  .clkPpLoginBtn()
			  .clkppcContinue();
				
			   String actual=co.getOrderId(); 
			   System.out. println("Your Order id for checkout flow of guest user with second day shipping using paypal credit is " +actual);
			   reader.setCellData("Guest_user", "Order_ID", 18, actual);
			  
	}
		
		@Test(enabled=false,description = "Checkout-Placed order using guest user with next day shipping using PayPal credit")//need to be more than 2 items cent value problem
		public void GuestUser_NextDayShipping_PayPalCredit() {
			
			co = new CheckoutPage(getDriver()); 
		    co.getPdpCheckoutPage(getPageUrlStandard(18,1))
			  .clkAddtoCartBtn() 
			  .clkProceedToCartBtn() 
			  .clkCheckoutBtn()
			  .clickLoginButton()
			  .clkCheckoutGuestBtn()
			  .enterGuestFname(getSheet().getRow(2).getCell(2).getStringCellValue())
			  .enterGuestLname(getSheet().getRow(2).getCell(3).getStringCellValue())
			  .enterGuestEmail(getSheet().getRow(2).getCell(4).getStringCellValue())
			  .enterGuestPhoneNo(getPhoneNo())
			  .enterGuestAddress(getSheet().getRow(2).getCell(6).getStringCellValue())
			  .enterGuestCity(getSheet().getRow(2).getCell(7).getStringCellValue())
			  .selectGuestState(CheckoutTestData.STATE)
			  .enterGuestZip(getZipCode(18,11))
			  .enterAddressLine2("Test")
			  .clkInfoNextBtn() 
			  .clkNextDayAirShip()
			  .clkShippingNextBtn()
			  .selectPaypalCr()
			  .clkConfirmBtn()
			  .clkPlaceOrderBtn()
			  .enterPpUserName(getSheet().getRow(15).getCell(17).getStringCellValue())
			  .clkppUnNextBtn()
			  .enterPpPass(getSheet().getRow(15).getCell(18).getStringCellValue())
			  .clkPpLoginBtn()
			  .clkppcContinue();
			  
			  String actual=co.getOrderId(); 
			  System.out. println("Your Order id for checkout flow of guest user with next day shipping using paypal credit is "+actual);
			  reader.setCellData("Guest_user", "Order_ID", 19, actual);
			  
	}
	}