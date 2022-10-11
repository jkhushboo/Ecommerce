package suite.testdata;

public class LoginTestData {

	public static final String LOGIN_PAGE = "/login";
	public static final String VALID_UNAME = "amrita@brother.com";
	public static final String VALID_PASS = "Welcome1";
	public static final String EXPECTED_WELCOME_MESSAGE = "Welcome ";
	public static final String INVALID_UNAME = "Aditya@aol";
	public static final String EXPECTED_USERNAME_VALIDATION_MESSAGE = "Please enter a valid email.";
	public static final String INVALID_PASS = "abc123";
	public static final String INVALID_UserNAME = "abc@test.com";
	public static final String EXPECTED_PASSWORD_VALIDATION_MESSAGE = "That email address & password combination isn’t in our records. Forgot your password? Reset it now.";
	public static final String VALID_EMAILID = "abctest50@brother.com";
	public static final String EXPECTED_ERROR_MESSAGE = "If email is registered, you will get a reset link.";
	public static final String EXPECTED_THANKYOU_MESSAGE = "Thank You";
	public static final String EXPECTED_INITAL_PASSWORD_VALIDATION_MESSAGE = "Encountered issue with backend services, please try again after some time";
	public static final String INVALID_EMAILID = "ananya1@mailinator.com";
	public static final String HOME_LOGIN_PAGE = "/home";

}
