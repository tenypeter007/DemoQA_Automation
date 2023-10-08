package com.demoqa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class BasePage {
    String os = System.getProperty("os.name");
    protected WebDriver driver;
    protected FluentWait<WebDriver> waiter;

    /**
     * Instantiates a new base page.
     *
     * @param driver the driver
     */
    public BasePage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waiter = new FluentWait<WebDriver>(driver).ignoring(NoSuchElementException.class, WebDriverException.class)
                .withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(2));
    }
}
