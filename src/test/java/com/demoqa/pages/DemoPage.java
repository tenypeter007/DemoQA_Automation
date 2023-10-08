package com.demoqa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.io.*;
import java.net.URL;

public class DemoPage extends BasePage {
    @FindBy(id = "firstName")
    private WebElement firstNameInputField;

    @FindBy(id = "lastName")
    private WebElement lastNameInputField;

    @FindBy(id = "userEmail")
    private WebElement emailInputField;

    @FindBy(id = "gender-radio-1")
    private WebElement genderMaleCheckBox;

    @FindBy(id = "gender-radio-2")
    private WebElement genderFemaleCheckBox;

    @FindBy(id = "gender-radio-3")
    private WebElement genderOtherCheckBox;

    @FindBy(id = "hobbies-checkbox-1")
    private WebElement hobbiesSportsCheckBox;

    @FindBy(id = "hobbies-checkbox-2")
    private WebElement hobbiesReadingCheckBox;

    @FindBy(id = "hobbies-checkbox-3")
    private WebElement hobbiesMusicCheckBox;

    @FindBy(id = "userNumber")
    private WebElement mobileNumberInputField;

    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirthInputField;

    @FindBy(id = "uploadPicture")
    private WebElement uploadPictureButton;

    @FindBy(id = "currentAddress")
    private WebElement currentAddressInputField;

    @FindBy(id = "subjectsInput")
    private WebElement subjectsInputField;

    @FindBy(id = "state")
    private WebElement stateDropDown;

    @FindBy(id = "city")
    private WebElement cityDropDown;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(xpath = "//tr[1]/td[2]")
    private WebElement fullName;

    @FindBy(xpath = "//tr[2]/td[2]")
    private WebElement emailValue;

    @FindBy(xpath = "//button[contains(text(),'Close')]")
    private WebElement closeButton;

    public DemoPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        firstNameInputField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameInputField.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        emailInputField.sendKeys(email);
    }

    public void selectGender(String gender) {
        switch (gender) {
            case "male":
                driver.findElement(By.xpath("//label[@for='gender-radio-1']")).click();
                break;
            case "female":
                driver.findElement(By.xpath("//label[@for='gender-radio-2']")).click();
                break;
            case "other":
                driver.findElement(By.xpath("//label[@for='gender-radio-3']")).click();
                break;
            default:
                throw new IllegalArgumentException("Invalid gender");
        }
    }

    public void enterMobileNumber(String mobileNumber) {
        mobileNumberInputField.sendKeys(mobileNumber.replace("-", ""));
    }

    public void enterDateOfBirth(String dob) {
        String[] dobFormatted = dob.split("T");
        if (os.contains("Mac"))
            dateOfBirthInputField.sendKeys(Keys.chord(Keys.COMMAND, "a"), dobFormatted[0]);
        else
            dateOfBirthInputField.sendKeys(Keys.chord(Keys.CONTROL, "a"), dobFormatted[0]);
        mobileNumberInputField.click();
    }

    public void enterSubject(String subject) {

        subjectsInputField.sendKeys(subject);
        try {
            Thread.sleep(1000);
            subjectsInputField.sendKeys(Keys.ENTER);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectHobbies(String hobby) {
        switch (hobby) {
            case "Sports":
                driver.findElement(By.xpath("//label[@for='hobbies-checkbox-1']")).click();
                break;
            case "Reading":
                driver.findElement(By.xpath("//label[@for='hobbies-checkbox-2']")).click();
                break;
            case "Music":
                driver.findElement(By.xpath("//label[@for='hobbies-checkbox-3']")).click();
                break;
            default:
                throw new IllegalArgumentException("Invalid hobby");
        }
    }

    public void selectPicture(String picturePath) {
        try {
            String filePath = System.getProperty("user.dir") + "/src/test/resources/sample.jpg";
            URL url = new URL(picturePath);
            InputStream in = new BufferedInputStream(url.openStream());
            OutputStream out = new BufferedOutputStream(new FileOutputStream(filePath));

            for (int i; (i = in.read()) != -1; ) {
                out.write(i);
            }
            in.close();
            out.close();
            uploadPictureButton.sendKeys(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void enterAddress(String streetNumber, String streetName) {
        currentAddressInputField.sendKeys(streetNumber + " " + streetName);
    }

    public void selectState(String state) {
        stateDropDown.click();
        driver.findElement(By.xpath("//div[text()='" + state + "']")).click();
    }

    public void selectCity(String city) {
        cityDropDown.click();
        driver.findElement(By.xpath("//div[text()='" + city + "']")).click();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.body.style.zoom = '95%'");
    }

    public void clickSubmit() {
        submitButton.sendKeys(Keys.ENTER);
    }

    public String getFullName() {
        return fullName.getText();
    }

    public String getEmail() {
        return emailValue.getText();
    }

    public void clickClose() {
        try {
            closeButton.click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", closeButton);
        }
    }
}
