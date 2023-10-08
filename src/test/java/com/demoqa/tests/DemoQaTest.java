package com.demoqa.tests;

import com.demoqa.factory.PageinstancesFactory;
import com.demoqa.pages.DemoPage;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DemoQaTest extends BaseTest {
    @Test
    public void DemoQaTest() {
        Response response = given()
                .when()
                .get("https://randomuser.me/api/?seed=foobar");

        response.then()
                .assertThat()
                .body("info.seed", equalTo("foobar"));
        JsonPath jsonPathEvaluator = response.jsonPath();

        driver.get("https://demoqa.com/automation-practice-form");
        DemoPage demoPage = PageinstancesFactory.getInstance(DemoPage.class);
        demoPage.enterFirstName(jsonPathEvaluator.getString("results[0].name.first"));
        demoPage.enterLastName(jsonPathEvaluator.getString("results[0].name.last"));
        demoPage.enterEmail(jsonPathEvaluator.getString("results[0].email"));
        demoPage.selectGender(jsonPathEvaluator.getString("results[0].gender"));
        demoPage.enterMobileNumber(jsonPathEvaluator.getString("results[0].cell"));
        demoPage.enterDateOfBirth(jsonPathEvaluator.getString("results[0].dob.date"));
        demoPage.enterSubject("Maths");
        demoPage.selectHobbies("Reading");
        demoPage.selectHobbies("Music");
        demoPage.selectPicture(jsonPathEvaluator.get("results[0].picture.large"));
        demoPage.enterAddress(jsonPathEvaluator.getString("results[0].location.street.number"), jsonPathEvaluator.getString("results[0].location.street.name"));
        demoPage.selectState("Haryana");
        demoPage.selectCity("Karnal");
        demoPage.clickSubmit();
        softAssert.assertEquals(demoPage.getFullName(), jsonPathEvaluator.getString("results[0].name.first") + " " + jsonPathEvaluator.getString("results[0].name.last"));
        softAssert.assertEquals(demoPage.getEmail(), jsonPathEvaluator.getString("results[0].email"));
        demoPage.clickClose();
    }
}
