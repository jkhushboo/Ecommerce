package suite.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.Assert;
import org.testng.annotations.Test;

import suite.base.TestBase;
import suite.page.CheckoutPage;
import suite.page.LoginPage;
import suite.testdata.CheckoutTestData;
import suite.utils.Xls_Reader;

//@Listeners(ExecutionListener.class)
public class RegisteredCheckoutTest extends TestBase {

	private CheckoutPage co;
	private LoginPage login;
	DataFormatter formatter = new DataFormatter();
	private Cell cell;

	Xls_Reader reader = new Xls_Reader("src/main/resources/TestDataSheet_uat.xlsx");

	private XSSFSheet getSheetS1() {
		return getTestData().getSheet("Registered_user");
	}

	private String getPageUrlStandard(int row, int column) {
		return getEnv().concat((getSheetS1().getRow(row).getCell(column).getStringCellValue()));
	}

	private String getPageUrlSecond() {
		return getEnv().concat((getSheetS1().getRow(6).getCell(3).getStringCellValue()));
	}

	private String getPageUrlNext1() {
		return getEnv().concat((getSheetS1().getRow(9).getCell(3).getStringCellValue()));
	}

	private String getPageUrlNext2() {
		return getEnv().concat((getSheetS1().getRow(10).getCell(3).getStringCellValue()));
	}

	private String getLoginPageUrl() {
		return getEnv().concat(CheckoutTestData.LOGIN_PAGE);
	}

	private String getCardNo() {
		cell = getSheetS1().getRow(1).getCell(11);
		return formatter.formatCellValue(cell);

	}

	private String getCardNo2() {
		cell = getSheetS1().getRow(2).getCell(11);
		return formatter.formatCellValue(cell);

	}

	private String getCardNo3() {
		cell = getSheetS1().getRow(4).getCell(11);
		return formatter.formatCellValue(cell);

	}

	private String getCardNo4() {
		cell = getSheetS1().getRow(5).getCell(11);
		return formatter.formatCellValue(cell);

	}

	private String getPhoneNo() {
		cell = getSheetS1().getRow(2).getCell(4);
		return formatter.formatCellValue(cell);
	}

	private String getZipCode() {
		cell = getSheetS1().getRow(2).getCell(8);
		return formatter.formatCellValue(cell);
	}

	private String getEXDateMM() {
		cell = getSheetS1().getRow(2).getCell(12);
		return formatter.formatCellValue(cell);
	}

	private String getEXDateYY() {
		cell = getSheetS1().getRow(2).getCell(13);
		return formatter.formatCellValue(cell);
	}

	private String getCVV() {
		cell = getSheetS1().getRow(2).getCell(14);
		return formatter.formatCellValue(cell);
	}

	/**
	 * create excel file
	 *
	 * @return excel file path
	 */
	private XSSFSheet getSheetS11() {
		return getTestData().getSheetAt(0);
	}

	@Test(enabled = true, description = "Placed order using registered user with ground shipping using Visa CC")
	public void RegisteredUser_GroundShipping_Visa_1() {

		reader.removeColumn("Registered_user", 18);
		reader.addColumn("Registered_user", "Order_ID");

		co = new CheckoutPage(getDriver());
		co.getPdpCheckoutPage(getPageUrlStandard(1,1))
		.clkAddtoCartBtn()
		.clkProceedToCartBtn()
		.clkCheckoutBtn()
		.clickLoginButton()		
		.enterUname(getSheetS1().getRow(1).getCell(1).getStringCellValue())
		.enterPass(getSheetS1().getRow(1).getCell(2).getStringCellValue())
		.clkSubmitBtn()
		.clkCookiesCrossBtn()
		.scrolltoInfoNextBtn()
		.clkInfoNextBtn()
		.clkSecondDayAirShip()
		.clkShippingNextBtn()
		.clkPrimaryPayment()
		.clkConfirmBtn()
		.clkPlaceOrderBtn();

		String actual = co.getOrderId();
		if (actual == null) {
			actual = "null";
		}
		System.out.println("Your Order id for checkout flow of Registered user with saved details is " + actual);

		reader.setCellData("Registered_user", "Order_ID", 2, actual);

	}

	@Test(priority=0,enabled = true, description = "Placed order with registered user with Ground shipping using Visa CC")
	public void RegisteredUser_GroundShipping_Visa_2() {

		reader.removeColumn("Registered_user", 18);
		reader.addColumn("Registered_user", "Order_ID");

		co = new CheckoutPage(getDriver());
		co.getPdpCheckoutPage(getPageUrlStandard(2,1))
		.clkAddtoCartBtn()
		.clkProceedToCartBtn()
		.clkCheckoutBtn()
		.clickLoginButton()		
		.enterUname(getSheetS1().getRow(2).getCell(1).getStringCellValue())
		.enterPass(getSheetS1().getRow(2).getCell(2).getStringCellValue())
		.clkSubmitBtn()
		.clkCookiesCrossBtn()
		.enterGuestPhoneNo(getPhoneNo())
		.enterGuestAddress(getSheetS1().getRow(2).getCell(5).getStringCellValue())
		.enterGuestCity(getSheetS1().getRow(2).getCell(6).getStringCellValue())
		.selectGuestState(CheckoutTestData.STATE)
		.enterGuestZip(CheckoutTestData.ZIP)
		.enterAddressLine2("Test")
		.clkInfoNextBtn().clkViewShippingNextBtn()
		.switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
		.enterNameOnCardCc(CheckoutTestData.NAME_ON_CARD)
		.selectCardTypeVisa()
		.enterCardNoCc(CheckoutTestData.GROUND_VISA)
		.enterExpiryMmCc(CheckoutTestData.EXPIRY_MONTH)
		.enterExpiryYyCc(CheckoutTestData.EXPIRY_YEAR)
		.enterCvvCc(CheckoutTestData.EXPIRY_CVV)
		.switchToPaymentPage()
		.clkConfirmBtn()
		.clkPlaceOrderBtn();

		String actual = co.getOrderId();
		System.out.println(
				"Your Order id for checkout flow of Registered user with ground shipping using Visa cc is " + actual);
		reader.setCellData("Registered_user", "Order_ID", 3, actual);

		/*
		 * String actual=co.paymentConfirmMessage(); Assert.assertEquals(actual,
		 * CheckoutTestData.PAYMENT_CONFIRM_MESSAGE);
		 */

	}

	@Test(enabled = true, description = "Registered user with ground shipping using Master CC")
	public void RegisteredUser_GroundShipping_Master() {

		co = new CheckoutPage(getDriver());
		co.getPdpCheckoutPage(getPageUrlStandard(3,1))
		.clkAddtoCartBtn()
		.clkProceedToCartBtn()
		.clkCheckoutBtn()
		.clickLoginButton()		
		.enterUname(getSheetS1().getRow(3).getCell(1).getStringCellValue())
		.enterPass(getSheetS1().getRow(3).getCell(2).getStringCellValue())
		.clkSubmitBtn()
		.clkCookiesCrossBtn()
		.enterGuestPhoneNo(getPhoneNo())
		.enterGuestAddress(getSheetS1().getRow(2).getCell(5).getStringCellValue())
		.enterGuestCity(getSheetS1().getRow(2).getCell(6).getStringCellValue())
		.selectGuestState(CheckoutTestData.STATE)
		.enterGuestZip(CheckoutTestData.ZIP)
		.enterAddressLine2("Test")
		.clkInfoNextBtn().clkViewShippingNextBtn()
		.switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
		.enterNameOnCardCc(CheckoutTestData.NAME_ON_CARD)
		.selectCardTypeMaster()
		.enterCardNoCc(CheckoutTestData.CARD_NUMBER_MASTER)
		.enterExpiryMmCc(CheckoutTestData.EXPIRY_MONTH)
		.enterExpiryYyCc(CheckoutTestData.EXPIRY_YEAR)
		.enterCvvCc(CheckoutTestData.EXPIRY_CVV)
		.switchToPaymentPage()
		.clkConfirmBtn()
		.clkPlaceOrderBtn();

		String actual = co.getOrderId();
		System.out.println(
				"Your Order id for checkout flow of Registered user with ground shipping using Master cc is " + actual);
		reader.setCellData("Registered_user", "Order_ID", 4, actual);
		/*
		 * String actual=co.paymentConfirmMessage(); Assert.assertEquals(actual,
		 * CheckoutTestData.PAYMENT_CONFIRM_MESSAGE);
		 */
	}

	@Test(enabled = true, description = "Registered user with ground shipping using Amex CC")
	public void RegisteredUser_GroundShipping_Amex() {

		co = new CheckoutPage(getDriver());
		co.getPdpCheckoutPage(getPageUrlStandard(4,1))
		.clkAddtoCartBtn()
		.clkProceedToCartBtn()
		.clkCheckoutBtn()
		.clickLoginButton()		
		.enterUname(getSheetS1().getRow(4).getCell(1).getStringCellValue())
		.enterPass(getSheetS1().getRow(4).getCell(2).getStringCellValue())
		.clkSubmitBtn()
		.clkCookiesCrossBtn()
		.enterGuestPhoneNo(getPhoneNo())
		.enterGuestAddress(getSheetS1().getRow(3).getCell(5).getStringCellValue())
		.enterGuestCity(getSheetS1().getRow(3).getCell(6).getStringCellValue())
		.selectGuestState(CheckoutTestData.STATE)
		.enterGuestZip(CheckoutTestData.ZIP)
		.enterAddressLine2("Test")
		.clkInfoNextBtn()
		.clkViewShippingNextBtn()
		.switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
		.enterNameOnCardCc(CheckoutTestData.NAME_ON_CARD)
		.selectCardTypeAmex()
		.enterCardNoCc(CheckoutTestData.CARD_NUMBER_AMEX)
		.enterExpiryMmCc(CheckoutTestData.EXPIRY_MONTH)
		.enterExpiryYyCc(CheckoutTestData.EXPIRY_YEAR)
		.enterCvvCc(CheckoutTestData.EXPIRY_CVV)
		.switchToPaymentPage()
		.clkConfirmBtn()
		.clkPlaceOrderBtn();

		String actual = co.getOrderId();
		System.out.println(
				"Your Order id for checkout flow of Registered user with ground shipping using Amex cc is " + actual);
		reader.setCellData("Registered_user", "Order_ID", 5, actual);

	}

	@Test(enabled = true, description = "Registered user with ground shipping using Disc CC")
	public void RegisteredUser_GroundShipping_Disc() {

		co = new CheckoutPage(getDriver());
		co.getPdpCheckoutPage(getPageUrlStandard(5,1))
		.clkAddtoCartBtn()
		.clkProceedToCartBtn()
		.clkCheckoutBtn()
		.clickLoginButton()		
		.enterUname(getSheetS1().getRow(5).getCell(1).getStringCellValue())
		.enterPass(getSheetS1().getRow(5).getCell(2).getStringCellValue())
		.clkSubmitBtn().clkCookiesCrossBtn()
		.enterGuestPhoneNo(getPhoneNo())
		.enterGuestAddress(getSheetS1().getRow(5).getCell(5).getStringCellValue())
		.enterGuestCity(getSheetS1().getRow(5).getCell(6).getStringCellValue())
		.selectGuestState(CheckoutTestData.STATE)
		.enterGuestZip(CheckoutTestData.ZIP)
		.enterAddressLine2("Test")
		.clkInfoNextBtn()
		.clkViewShippingNextBtn()
		.switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
		.enterNameOnCardCc(CheckoutTestData.NAME_ON_CARD)
		.selectCardTypeDiscover()
		.enterCardNoCc(CheckoutTestData.GOUND_DISC)
		.enterExpiryMmCc(CheckoutTestData.EXPIRY_MONTH)
		.enterExpiryYyCc(CheckoutTestData.EXPIRY_YEAR)
		.enterCvvCc(CheckoutTestData.EXPIRY_CVV)
		.switchToPaymentPage()
		.clkConfirmBtn()
		.clkPlaceOrderBtn();

		String actual = co.getOrderId();
		System.out.println("Your Order id for checkout flow of Registered user with ground shipping using Discover cc is "
						+ actual);
		reader.setCellData("Registered_user", "Order_ID", 6, actual);
		
	}

	@Test(enabled = true, description = "Registered user with second day shipping using Visa CC")
	public void RegisteredUser_SecondShipping_Visa() {

		co = new CheckoutPage(getDriver());
		co.getPdpCheckoutPage(getPageUrlSecond())
		.clkAddtoCartBtn()
		.clkProceedToCartBtn()
		.clkCheckoutBtn()
		.clickLoginButton()		
		.enterUname(getSheetS1().getRow(6).getCell(1).getStringCellValue())
		.enterPass(getSheetS1().getRow(6).getCell(2).getStringCellValue())
		.clkSubmitBtn()
		.clkCookiesCrossBtn()
		.enterGuestPhoneNo(getPhoneNo())
		.enterGuestAddress(getSheetS1().getRow(5).getCell(5).getStringCellValue())
		.enterGuestCity(getSheetS1().getRow(5).getCell(6).getStringCellValue())
		.selectGuestState(CheckoutTestData.STATE)
		.enterGuestZip(CheckoutTestData.ZIP)
		.enterAddressLine2("Test")
		.clkInfoNextBtn()
		.clkSecondDayAirShip()
		.clkShippingNextBtn()
		.switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
		.enterNameOnCardCc(getSheetS1().getRow(2).getCell(10).getStringCellValue())
		.selectCardTypeVisa()
		.enterCardNoCc(CheckoutTestData.GROUND_VISA)
		.enterExpiryMmCc(CheckoutTestData.EXPIRY_MONTH)
		.enterExpiryYyCc(CheckoutTestData.EXPIRY_YEAR)
		.enterCvvCc(CheckoutTestData.EXPIRY_CVV)
		.switchToPaymentPage()
		.clkConfirmBtn()
		.clkPlaceOrderBtn();

		String actual = co.getOrderId();
		System.out.println("Your Order id for checkout flow of Registered user with second day shipping using Visa cc is "
						+ actual);
		reader.setCellData("Registered_user", "Order_ID", 7, actual);

	}

	@Test(enabled = true, description = "Registered user with second day shipping using Master CC")
	public void RegisteredUser_SecondShipping_Master() {

		co = new CheckoutPage(getDriver());
		co.getPdpCheckoutPage(getPageUrlStandard(6,1))
		.clkAddtoCartBtn()
		.clkProceedToCartBtn()
		.clkCheckoutBtn()
		.clickLoginButton()		
		.enterUname(getSheetS1().getRow(7).getCell(1).getStringCellValue())
		.enterPass(getSheetS1().getRow(7).getCell(2).getStringCellValue())
		.clkSubmitBtn()
		.clkCookiesCrossBtn()
		.enterGuestPhoneNo(getPhoneNo())
		.enterGuestAddress(getSheetS1().getRow(5).getCell(5).getStringCellValue())
		.enterGuestCity(getSheetS1().getRow(5).getCell(6).getStringCellValue())
		.selectGuestState(CheckoutTestData.STATE)
		.enterGuestZip(CheckoutTestData.ZIP)
		.enterAddressLine2("Test")
		.clkInfoNextBtn()
		.clkSecondDayAirShip()
		.clkShippingNextBtn()
		.switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
		.enterNameOnCardCc(getSheetS1().getRow(2).getCell(10).getStringCellValue())
		.selectCardTypeMaster()
		.enterCardNoCc(CheckoutTestData.CARD_NUMBER_MASTER)
		.enterExpiryMmCc(CheckoutTestData.EXPIRY_MONTH)
		.enterExpiryYyCc(CheckoutTestData.EXPIRY_YEAR)
		.enterCvvCc(CheckoutTestData.EXPIRY_CVV)
		.switchToPaymentPage().clkConfirmBtn().clkPlaceOrderBtn();

		String actual = co.getOrderId();
		System.out.println("Your Order id for checkout flow of Registered user with second day shipping using Master cc is "
						+ actual);
		reader.setCellData("Registered_user", "Order_ID", 8, actual);

	}

	@Test(enabled = true, description = "Registered user with second day shipping using Amex CC")
	public void RegisteredUser_SecondShipping_Amex() {

		co = new CheckoutPage(getDriver());
		co.getPdpCheckoutPage(getPageUrlStandard(7,1))
		.clkAddtoCartBtn()
		.clkProceedToCartBtn()
		.clkCheckoutBtn()
		.clickLoginButton()		
		.enterUname(getSheetS1().getRow(8).getCell(1).getStringCellValue())
		.enterPass(getSheetS1().getRow(8).getCell(2).getStringCellValue())
		.clkSubmitBtn()
		.clkCookiesCrossBtn()
		.enterGuestPhoneNo(getPhoneNo())
		.enterGuestAddress(getSheetS1().getRow(5).getCell(5).getStringCellValue())
		.enterGuestCity(getSheetS1().getRow(5).getCell(6).getStringCellValue())
		.selectGuestState(CheckoutTestData.STATE)
		.enterGuestZip(CheckoutTestData.ZIP)
		.enterAddressLine2("Test")
		.clkInfoNextBtn()
		.clkSecondDayAirShip()
		.clkShippingNextBtn()
		.switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
		.enterNameOnCardCc(getSheetS1().getRow(3).getCell(10).getStringCellValue())
		.selectCardTypeAmex()
		.enterCardNoCc(CheckoutTestData.CARD_NUMBER_AMEX)
		.enterExpiryMmCc(CheckoutTestData.EXPIRY_MONTH)
		.enterExpiryYyCc(CheckoutTestData.EXPIRY_YEAR)
		.enterCvvCc(CheckoutTestData.EXPIRY_CVV)
		.switchToPaymentPage()
		.clkConfirmBtn()
		.clkPlaceOrderBtn();

		String actual = co.getOrderId();
		System.out.println("Your Order id for checkout flow of Registered user with second day shipping using Amex cc is "
						+ actual);
		reader.setCellData("Registered_user", "Order_ID", 9, actual);

	}

	@Test(enabled = true, description = "Registered user with second day shipping using Disc CC")
	public void RegisteredUser_SecondShipping_Disc() {

		co = new CheckoutPage(getDriver());
		co.getPdpCheckoutPage(getPageUrlStandard(8,1))
		.clkAddtoCartBtn().clkProceedToCartBtn().clkCheckoutBtn()
		.clickLoginButton()		
		.enterUname(getSheetS1().getRow(9).getCell(1).getStringCellValue())
		.enterPass(getSheetS1().getRow(9).getCell(2).getStringCellValue()).clkSubmitBtn().clkCookiesCrossBtn()
		.enterGuestPhoneNo(getPhoneNo())
		.enterGuestAddress(getSheetS1().getRow(5).getCell(5).getStringCellValue())
		.enterGuestCity(getSheetS1().getRow(5).getCell(6).getStringCellValue())
		.selectGuestState(CheckoutTestData.STATE).enterGuestZip(CheckoutTestData.ZIP).enterAddressLine2("Test")
		.clkInfoNextBtn().clkSecondDayAirShip().clkShippingNextBtn()
		.switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
		.enterNameOnCardCc(getSheetS1().getRow(4).getCell(10).getStringCellValue()).selectCardTypeDiscover()
		.enterCardNoCc(CheckoutTestData.GOUND_DISC).enterExpiryMmCc(CheckoutTestData.EXPIRY_MONTH)
		.enterExpiryYyCc(CheckoutTestData.EXPIRY_YEAR).enterCvvCc(CheckoutTestData.EXPIRY_CVV)
		.switchToPaymentPage().clkConfirmBtn().clkPlaceOrderBtn();

		String actual = co.getOrderId();
		System.out.println("Your Order id for checkout flow of Registered user with second day shipping using Discover cc is "
						+ actual);
		reader.setCellData("Registered_user", "Order_ID", 10, actual);
	}

	@Test(enabled = true, description = "Registered user with next day shipping using Visa CC")
	public void RegisteredUser_NextShipping_Visa() {

		co = new CheckoutPage(getDriver());
		co.getPdpCheckoutPage(getPageUrlStandard(9,1))
		.clkAddtoCartBtn()
		.clkProceedToCartBtn()
		.clkCheckoutBtn()
		.clickLoginButton()		
		.enterUname(getSheetS1().getRow(10).getCell(1).getStringCellValue())
		.enterPass(getSheetS1().getRow(10).getCell(2).getStringCellValue())
		.clkSubmitBtn()
		.clkCookiesCrossBtn()
		.enterGuestPhoneNo(getPhoneNo())
		.enterGuestAddress(getSheetS1().getRow(5).getCell(5).getStringCellValue())
		.enterGuestCity(getSheetS1().getRow(5).getCell(6).getStringCellValue())
		.selectGuestState(CheckoutTestData.STATE)
		.enterGuestZip(CheckoutTestData.ZIP)
		.enterAddressLine2("Test")
		.clkInfoNextBtn()
		.clkNextDayAirShip()
		.clkShippingNextBtn()
		.switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
		.enterNameOnCardCc(getSheetS1().getRow(4).getCell(10).getStringCellValue())
		.selectCardTypeVisa()
		.enterCardNoCc(CheckoutTestData.GROUND_VISA)
		.enterExpiryMmCc(CheckoutTestData.EXPIRY_MONTH)
		.enterExpiryYyCc(CheckoutTestData.EXPIRY_YEAR)
		.enterCvvCc(CheckoutTestData.EXPIRY_CVV)
		.switchToPaymentPage()
		.clkConfirmBtn()
		.clkPlaceOrderBtn();

		String actual = co.getOrderId();
		System.out.println("Your Order id for checkout flow of Registered user with next day shipping using Visa cc is " + actual);
		reader.setCellData("Registered_user", "Order_ID", 11, actual);

	}

	@Test(enabled = true, description = "Registered user with next day shipping using Master CC")
	public void RegisteredUser_NextShipping_Master() {

		co = new CheckoutPage(getDriver());
		co.getPdpCheckoutPage(getPageUrlStandard(10,1))
		.clkAddtoCartBtn()
		.clkProceedToCartBtn()
		.clkCheckoutBtn()
		.enterUname(getSheetS1().getRow(11).getCell(1).getStringCellValue())
		.enterPass(getSheetS1().getRow(11).getCell(2).getStringCellValue())
		.clkSubmitBtn()
		.clkCookiesCrossBtn()
		.enterGuestPhoneNo(getPhoneNo())
		.enterGuestAddress(getSheetS1().getRow(5).getCell(5).getStringCellValue())
		.enterGuestCity(getSheetS1().getRow(5).getCell(6).getStringCellValue())
		.selectGuestState(CheckoutTestData.STATE)
		.enterGuestZip(CheckoutTestData.ZIP)
		.enterAddressLine2("Test")
		.clkInfoNextBtn()
		.clkNextDayAirShip()
		.clkShippingNextBtn()
		.switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
		.enterNameOnCardCc(getSheetS1().getRow(4).getCell(10).getStringCellValue())
		.selectCardTypeMaster()
		.enterCardNoCc(CheckoutTestData.CARD_NUMBER_MASTER)
		.enterExpiryMmCc(CheckoutTestData.EXPIRY_MONTH)
		.enterExpiryYyCc(CheckoutTestData.EXPIRY_YEAR)
		.enterCvvCc(CheckoutTestData.EXPIRY_CVV)
		.switchToPaymentPage()
		.clkConfirmBtn()
		.clkPlaceOrderBtn();

		String actual = co.getOrderId();
		System.out.println("Your Order id for checkout flow of Registered user with next day shipping using Master cc is "+ actual);
        reader.setCellData("Registered_user", "Order_ID", 12, actual);

	}

	@Test(enabled = true, description = "Registered user with next day shipping using Amex CC")
	public void RegisteredUser_NextShipping_Amex() {

		co = new CheckoutPage(getDriver());
		co.getPdpCheckoutPage(getPageUrlStandard(11,1))
		.clkAddtoCartBtn()
		.clkProceedToCartBtn()
		.clkCheckoutBtn()
		.clickLoginButton()		
		.enterUname(getSheetS1().getRow(12).getCell(1).getStringCellValue())
		.enterPass(getSheetS1().getRow(12).getCell(2).getStringCellValue())
		.clkSubmitBtn()
		.clkCookiesCrossBtn()
		.enterGuestPhoneNo(getPhoneNo())
		.enterGuestAddress(getSheetS1().getRow(5).getCell(5).getStringCellValue())
		.enterGuestCity(getSheetS1().getRow(5).getCell(6).getStringCellValue())
		.selectGuestState(CheckoutTestData.STATE)
		.enterGuestZip(CheckoutTestData.ZIP)
		.enterAddressLine2("Test")
		.clkInfoNextBtn()
		.clkNextDayAirShip()
		.clkShippingNextBtn()
		.switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
		.enterNameOnCardCc(getSheetS1().getRow(4).getCell(10).getStringCellValue())
		.selectCardTypeAmex()
		.enterCardNoCc(CheckoutTestData.CARD_NUMBER_AMEX)
		.enterExpiryMmCc(CheckoutTestData.EXPIRY_MONTH)
		.enterExpiryYyCc(CheckoutTestData.EXPIRY_YEAR)
		.enterCvvCc(CheckoutTestData.EXPIRY_CVV)
		.switchToPaymentPage()
		.clkConfirmBtn()
		.clkPlaceOrderBtn();

		String actual = co.getOrderId();
		System.out.println("Your Order id for checkout flow of Registered user with next day shipping using Amex cc is " + actual);
        reader.setCellData("Registered_user", "Order_ID", 13, actual);

	}

	@Test(enabled = true, description = "Registered user with next day shipping using Discover CC")
	public void RegisteredUser_NextShipping_Disc() {

		co = new CheckoutPage(getDriver());
		co.getPdpCheckoutPage(getPageUrlStandard(12,1))
		.clkAddtoCartBtn()
		.clkProceedToCartBtn()
		.clkCheckoutBtn()
		.clickLoginButton()		
		.enterUname(getSheetS1().getRow(13).getCell(1).getStringCellValue())
		.enterPass(getSheetS1().getRow(13).getCell(2).getStringCellValue())
		.clkSubmitBtn()
		.clkCookiesCrossBtn()
		.enterGuestPhoneNo(getPhoneNo())
		.enterGuestAddress(getSheetS1().getRow(5).getCell(5).getStringCellValue())
		.enterGuestCity(getSheetS1().getRow(5).getCell(6).getStringCellValue())
		.selectGuestState(CheckoutTestData.STATE)
		.enterGuestZip(CheckoutTestData.ZIP)
		.enterAddressLine2("Test")
		.clkInfoNextBtn()
		.clkNextDayAirShip()
		.clkShippingNextBtn()
		.switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
		.enterNameOnCardCc(getSheetS1().getRow(4).getCell(10).getStringCellValue())
		.selectCardTypeDiscover()
		.enterCardNoCc(CheckoutTestData.GOUND_DISC)
		.enterExpiryMmCc(CheckoutTestData.EXPIRY_MONTH)
		.enterExpiryYyCc(CheckoutTestData.EXPIRY_YEAR)
		.enterCvvCc(CheckoutTestData.EXPIRY_CVV)
		.switchToPaymentPage()
		.clkConfirmBtn()
		.clkPlaceOrderBtn();

		String actual = co.getOrderId();
		System.out.println("Your Order id for checkout flow of Registered user with next day shipping using Discover cc is "+ actual);
        reader.setCellData("Registered_user", "Order_ID", 14, actual);
	}

	@Test(enabled = true, description = "Placed order with registered user with Ground shipping using paypal")
	public void RegisteredUser_GroundShipping_Paypal() {

		co = new CheckoutPage(getDriver());
		co.getPdpCheckoutPage(getPageUrlStandard(13,1))
		.clkAddtoCartBtn()
		.clkProceedToCartBtn()
		.clkCheckoutBtn()
		.clickLoginButton()		
		.enterUname(getSheetS1().getRow(14).getCell(1).getStringCellValue())
		.enterPass(getSheetS1().getRow(14).getCell(2).getStringCellValue())
		.clkSubmitBtn()
		.clkCookiesCrossBtn()
		.enterGuestPhoneNo(getPhoneNo())
		.enterGuestAddress(getSheetS1().getRow(2).getCell(5).getStringCellValue())
		.enterGuestCity(getSheetS1().getRow(2).getCell(6).getStringCellValue())
		.selectGuestState(CheckoutTestData.STATE)
		.enterGuestZip(CheckoutTestData.ZIP)
		.enterAddressLine2("Test")
		.clkInfoNextBtn()
		.clkShippingNextBtn()
		.selectPaypalReg()
		.clkConfirmBtn()
		.clkPlaceOrderBtn()
		.enterPpUserName(getSheetS1().getRow(14).getCell(16).getStringCellValue()).clkppUnNextBtn()
		.enterPpPass(CheckoutTestData.PAYPAL_PASS)
		.clkPpLoginBtn()
		.clkppContinue();

		String actual = co.getOrderId();
		System.out.println("Your Order id for checkout flow of Registered user with ground shipping using paypal is " + actual);
		reader.setCellData("Registered_user", "Order_ID", 15, actual);
	}

	@Test(enabled = true, description = "Placed order with registered user with second shipping using paypal")
	public void RegisteredUser_SecondShipping_Paypal() {

		co = new CheckoutPage(getDriver());
		co.getPdpCheckoutPage(getPageUrlStandard(14,1)).clkAddtoCartBtn().clkProceedToCartBtn().clkCheckoutBtn()
		.clickLoginButton()		
		.enterUname(getSheetS1().getRow(15).getCell(1).getStringCellValue())
		.enterPass(getSheetS1().getRow(15).getCell(2).getStringCellValue())
		.clkSubmitBtn()
		.clkCookiesCrossBtn()
		.enterGuestPhoneNo(getPhoneNo())
		.enterGuestAddress(getSheetS1().getRow(2).getCell(5).getStringCellValue())
		.enterGuestCity(getSheetS1().getRow(2).getCell(6).getStringCellValue())
		.selectGuestState(CheckoutTestData.STATE)
		.enterGuestZip(CheckoutTestData.ZIP)
		.enterAddressLine2("Test")
		.clkInfoNextBtn()
		.clkSecondDayAirShip()
		.clkShippingNextBtn()
		.selectPaypalReg()
		.clkConfirmBtn()
		.clkPlaceOrderBtn()
		.enterPpUserName(getSheetS1().getRow(15).getCell(16).getStringCellValue())
		.clkppUnNextBtn()
		.enterPpPass(CheckoutTestData.PAYPAL_PASS)
		.clkPpLoginBtn()
		.clkppContinue();

		String actual = co.getOrderId();
		System.out.println("Your Order id for checkout flow of Registered user with second day shipping using paypal is "+ actual);

		reader.setCellData("Registered_user", "Order_ID", 16, actual);
	}

	@Test(enabled = true, description = "Placed order with registered user with next shipping using paypal")
	public void RegisteredUser_NextDayShipping_Paypal() {

		co = new CheckoutPage(getDriver());
		co.getPdpCheckoutPage(getPageUrlStandard(15,1))
		.clkAddtoCartBtn()
		.clkProceedToCartBtn()
		.clkCheckoutBtn()
		.clickLoginButton()		
		.enterUname(getSheetS1().getRow(16).getCell(1).getStringCellValue())
		.enterPass(getSheetS1().getRow(16).getCell(2).getStringCellValue())
		.clkSubmitBtn()
		.clkCookiesCrossBtn()
		.enterGuestPhoneNo(getPhoneNo())
		.enterGuestAddress(getSheetS1().getRow(2).getCell(5).getStringCellValue())
		.enterGuestCity(getSheetS1().getRow(2).getCell(6).getStringCellValue())
		.selectGuestState(CheckoutTestData.STATE)
		.enterGuestZip(CheckoutTestData.ZIP)
		.enterAddressLine2("Test")
		.clkInfoNextBtn()
		.clkNextDayAirShip()
		.clkShippingNextBtn()
		.selectPaypalReg()
		.clkConfirmBtn()
		.clkPlaceOrderBtn()
		.enterPpUserName(getSheetS1().getRow(16).getCell(16).getStringCellValue())
		.clkppUnNextBtn().enterPpPass(CheckoutTestData.PAYPAL_PASS)
		.clkPpLoginBtn()
		.clkppContinue();

		String actual = co.getOrderId();
		System.out.println("Your Order id for checkout flow of Registered user with second day shipping using paypal is "+ actual);
		reader.setCellData("Registered_user", "Order_ID", 17, actual);
	}

	@Test(enabled = true, description = "Placed order with registered user with ground shipping using paypal credit")
	public void RegisteredUser_GroundShipping_PaypalCredit() {

		co = new CheckoutPage(getDriver());
		co.getPdpCheckoutPage(getPageUrlStandard(16,1))
		.clkAddtoCartBtn()
		.clkProceedToCartBtn()
		.clkCheckoutBtn()
		.clickLoginButton()		
		.enterUname(getSheetS1().getRow(17).getCell(1).getStringCellValue())
		.enterPass(getSheetS1().getRow(17).getCell(2).getStringCellValue())
		.clkSubmitBtn()
		.clkCookiesCrossBtn()
		.enterGuestPhoneNo(getPhoneNo())
		.enterGuestAddress(getSheetS1().getRow(2).getCell(5).getStringCellValue())
		.enterGuestCity(getSheetS1().getRow(2).getCell(6).getStringCellValue())
		.selectGuestState(CheckoutTestData.STATE)
		.enterGuestZip(CheckoutTestData.ZIP)
		.enterAddressLine2("Test")
		.clkInfoNextBtn()
		.clkShippingNextBtn().selectPaypalCrReg().clkConfirmBtn().clkPlaceOrderBtn()
		.enterPpUserName(getSheetS1().getRow(17).getCell(16).getStringCellValue()).clkppUnNextBtn()
		.enterPpPass(CheckoutTestData.PAYPAL_PASS)
		.clkPpLoginBtn()
		.clkppcContinue();
		String actual = co.getOrderId();
		System.out.println("Your Order id for checkout flow of Registered user with ground shipping using paypal credit is "+ actual);
        reader.setCellData("Registered_user", "Order_ID", 18, actual);
	}

	@Test(enabled = true, description = "Placed order with registered user with second day shipping using paypal credit")
	public void RegisteredUser_SecondDayShipping_PaypalCredit() {

		co = new CheckoutPage(getDriver());
		co.getPdpCheckoutPage(getPageUrlStandard(17,1))
		.clkAddtoCartBtn()
		.clkProceedToCartBtn()
		.clkCheckoutBtn()
		.clickLoginButton()		
		.enterUname(getSheetS1().getRow(18).getCell(1).getStringCellValue())
		.enterPass(getSheetS1().getRow(18).getCell(2).getStringCellValue()).clkSubmitBtn().clkCookiesCrossBtn()
		.enterGuestPhoneNo(getPhoneNo())
		.enterGuestAddress(getSheetS1().getRow(2).getCell(5).getStringCellValue())
		.enterGuestCity(getSheetS1().getRow(2).getCell(6).getStringCellValue())
		.selectGuestState(CheckoutTestData.STATE).enterGuestZip(CheckoutTestData.ZIP).enterAddressLine2("Test")
		.clkInfoNextBtn().clkSecondDayAirShip().clkShippingNextBtn().selectPaypalCrReg().clkConfirmBtn()
		.clkPlaceOrderBtn().enterPpUserName(getSheetS1().getRow(18).getCell(16).getStringCellValue())
		.clkppUnNextBtn().enterPpPass(CheckoutTestData.PAYPAL_PASS)
		.clkPpLoginBtn()
		.clkppcContinue();

		String actual = co.getOrderId();
		System.out.println("Your Order id for checkout flow of Registered user with second day shipping using paypal credit is "
						+ actual);

		reader.setCellData("Registered_user", "Order_ID", 19, actual);
	}

	@Test(enabled = true, description = "Placed order with registered user with next day shipping using paypal credit")
	public void RegisteredUser_NextDayShipping_PaypalCredit() {

		co = new CheckoutPage(getDriver());
		co.getPdpCheckoutPage(getPageUrlStandard(18,1))
		.clkAddtoCartBtn()
		.clkProceedToCartBtn()
		.clkCheckoutBtn()
		.clickLoginButton()		
		.enterUname(getSheetS1().getRow(19).getCell(1).getStringCellValue())
		.enterPass(getSheetS1().getRow(19).getCell(2).getStringCellValue()).clkSubmitBtn().clkCookiesCrossBtn()
		.enterGuestPhoneNo(getPhoneNo())
		.enterGuestAddress(getSheetS1().getRow(2).getCell(5).getStringCellValue())
		.enterGuestCity(getSheetS1().getRow(2).getCell(6).getStringCellValue())
		.selectGuestState(CheckoutTestData.STATE).enterGuestZip(CheckoutTestData.ZIP).enterAddressLine2("Test")
		.clkInfoNextBtn().clkNextDayAirShip().clkShippingNextBtn().selectPaypalCrReg().clkConfirmBtn()
		.clkPlaceOrderBtn().enterPpUserName(getSheetS1().getRow(19).getCell(16).getStringCellValue())
		.clkppUnNextBtn().enterPpPass(CheckoutTestData.PAYPAL_PASS)		
		.clkPpLoginBtn()
		.clkppcContinue();

		String actual = co.getOrderId();
		System.out.println("Your Order id for checkout flow of Registered user with next day shipping using paypal credit is "
						+ actual);
        reader.setCellData("Registered_user", "Order_ID", 20, actual);
	}

	@Test(enabled = true, description = "Registered user with next day shipping using Discover CC")
	public void RegisteredUser_NextShipping_Disc1() {

		login = new LoginPage(getDriver());
		login.getLoginPage(getPageUrlStandard(19,1))
		.enterUname(getSheetS1().getRow(13).getCell(1).getStringCellValue())
		.enterPass(getSheetS1().getRow(13).getCell(2).getStringCellValue())
		.clkSubmitBtn();
		co = new CheckoutPage(getDriver());
		co.getPdpCheckoutPage(getPageUrlStandard(19,1))
		.clkAddtoCartBtn()
		.clkProceedToCartBtn()
		.clkCheckoutBtn()
		.clickLoginButton()		
		.enterGuestPhoneNo(getPhoneNo())
		.enterGuestAddress(getSheetS1().getRow(5).getCell(5).getStringCellValue())
		.enterGuestCity(getSheetS1().getRow(5).getCell(6).getStringCellValue())
		.selectGuestState(CheckoutTestData.STATE)
		.enterGuestZip(CheckoutTestData.ZIP)
		.enterAddressLine2("Test")
		.clkInfoNextBtn()
		.clkNextDayAirShip()
		.clkShippingNextBtn()
		.switchToPaymentIframe(CheckoutTestData.IFRAME_ID)
		.enterNameOnCardCc(getSheetS1().getRow(4).getCell(10).getStringCellValue())
		.selectCardType(CheckoutTestData.CARD_TYPE_DISC)
		.enterCardNoCc(CheckoutTestData.GOUND_DISC)
		.enterExpiryMmCc(CheckoutTestData.EXPIRY_MONTH)
		.enterExpiryYyCc(CheckoutTestData.EXPIRY_YEAR)
		.enterCvvCc(CheckoutTestData.EXPIRY_CVV)
		.switchToPaymentPage()
		.clkConfirmBtn()
		.clkPlaceOrderBtn();

		String actual = co.getOrderId();
		System.out.println("Your Order id for checkout flow of Registered user with next day shipping using Discover cc is "+ actual);
        reader.setCellData("Registered_user", "Order_ID", 14, actual);
	}
}