package com.demoqa.util;

import com.relevantcodes.extentreports.LogStatus;
import com.demoqa.context.WebDriverContext;
import com.demoqa.report.ExtentReportManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ReportUtil {
    /**
     * Adds the screen shot.
     *
     * @param status  the status
     * @param message the message
     */
    public static void addScreenShot(LogStatus status, String message) {
        String base64Image = "data:image/png;base64,"
                + ((TakesScreenshot) WebDriverContext.getDriver()).getScreenshotAs(OutputType.BASE64);
        ExtentReportManager.getCurrentTest().log(status, message,
                ExtentReportManager.getCurrentTest().addBase64ScreenShot(base64Image));
    }
}
