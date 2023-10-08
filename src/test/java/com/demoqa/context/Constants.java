package com.demoqa.context;

public class Constants {
    public static final String WORKING_DIRECTORY = System.getProperty("user.dir");
    public final static String REPORT_DIRECTORY = WORKING_DIRECTORY + "/ExtentReports/AutomationResult.html";
    public final static String PROJECT_NAME = "DemoQA";
    public final static String EXTENT_CONFIG_PATH = WORKING_DIRECTORY + "/src/test/resources/config/extent-config.xml";
    public final static String CHROME_DRIVER_PATH = WORKING_DIRECTORY + "/Drivers/chromedriver";
    public final static String EXTENSION_PATH = WORKING_DIRECTORY + "/src/test/resources/uBlock-Origin.crx";
}
