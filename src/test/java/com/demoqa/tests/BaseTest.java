package com.demoqa.tests;

import com.demoqa.context.Constants;
import com.demoqa.context.WebDriverContext;
import com.demoqa.listeners.LogListener;
import com.demoqa.listeners.ReportListener;
import com.demoqa.util.LoggerUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.concurrent.TimeUnit;

@Listeners({ReportListener.class, LogListener.class})
public class BaseTest {
    protected WebDriver driver;
    SoftAssert softAssert = new SoftAssert();

    @BeforeSuite(alwaysRun = true)
    public void globalSetup() {
        LoggerUtil.log("************************** Test Execution Started ************************************");
    }

    @AfterSuite(alwaysRun = true)
    public void wrap(ITestContext context) {
        int total = context.getAllTestMethods().length;
        int passed = context.getPassedTests().size();
        int failed = context.getFailedTests().size();
        int skipped = context.getSkippedTests().size();
        LoggerUtil.log("Total number of testcases : " + total);
        LoggerUtil.log("Number of testcases Passed : " + passed);
        LoggerUtil.log("Number of testcases Failed : " + failed);
        LoggerUtil.log("Number of testcases Skipped  : " + skipped);
        LoggerUtil.log("************************** Test Execution Finished ************************************");
    }

    @BeforeClass
    protected void setup() {
        System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
        //WebDriverManager.chromedriver().setup();
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("disable-infobars");
        ops.addArguments("Zoom 60%");
        ops.addExtensions(new File(Constants.EXTENSION_PATH));
        driver = new ChromeDriver(ops);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverContext.setDriver(driver);

    }

    @AfterClass
    public void wrapUp() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
