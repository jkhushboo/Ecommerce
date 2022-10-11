package suite.base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Test;

import suite.init.SessionInit;
import suite.listener.WebDriverListener;
import suite.utils.ExcelUtils;

/**
 * This is Test Base class for automation framework contains all the common test
 * prerequisites for automation
 * 
 * @author Khushboo Jain
 *
 */
public class TestBase extends EnvBase {

	private WebDriver driver;
	private WebDriverWait wait;
	private EventFiringWebDriver eventHandler;
	private WebDriverListener ecapture;

	@Parameters({ "env" })
	@BeforeClass
	public void setUpEnv(@Optional("qa") String env) {
		setEnv(env);
	}

	@Parameters({ "browser" })
	@BeforeMethod
	public void setDriver(@Optional("chrome") String browser) {

		SessionInit.getDriverSession().initiateBrowserSession(browser);
		this.driver = SessionInit.getDriverSession().getBrowserSession();

		this.eventHandler = new EventFiringWebDriver(driver);
		this.ecapture = new WebDriverListener();
		this.eventHandler.register(ecapture);
	}

	@AfterMethod
	public void screenShot(ITestResult result) throws IOException {
		// using ITestResult.FAILURE is equals to result.getStatus then it enter into if
		// condition
		if (result.getThrowable() != null || (ITestResult.FAILURE == result.getStatus())) {
			/*
			 * test.log(status, result.getThrowable());
			 */

			String timeStamp;
			String screenShotName;

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			screenShotName = Paths.get("").toAbsolutePath().toString() + "/Screenshots/" + result.getName() + ".png";
			System.out.println("testbase" + screenShotName);
			File Desination = new File(screenShotName);

			FileUtils.copyFile(scrFile, Desination);

		}

		this.eventHandler.unregister(ecapture);
		SessionInit.getDriverSession().terminateBrowserSession(driver);

	}
	/*
	 * public void tearDown() { this.eventHandler.unregister(ecapture);
	 * SessionInit.getDriverSession().terminateBrowserSession(driver); }
	 */

	public EventFiringWebDriver getDriver() {
		return this.eventHandler;
	}

	@Override
	public String getEnv() {
		return super.getEnv();
	}

	public WebDriverWait getWait() {
		return wait;
	}

	/**
	 * for getting the data from excel sheet
	 *
	 * @return path of the sheet
	 */
	public XSSFWorkbook getTestData() {
		return new ExcelUtils().ExcelDataConfig("src/main/resources/TestDataSheet_uat.xlsx");
	}

}