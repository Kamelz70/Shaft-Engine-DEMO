package com.qacart.shaft;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.HashMap;
//import org.openqa.selenium.remote.Browser;

public class OpenBrowsers {
    @Test
    public void OpenChromeBrowser()
    {

//        SHAFT.Properties.web.set().targetBrowserName(Browser.CHROME.browserName());
//        Start browser
        SHAFT.GUI.WebDriver driver = new SHAFT.GUI.WebDriver();
        driver.browser().navigateToURL("https://hatem-hatamleh.github.io/Selenium-html/");
//      click acitons button
        By actionsBt= By.linkText("Actions");
        driver.element().click(actionsBt);

//        Identify all input fields
        By FnameInput= By.name("firstName");
        By LnameInput= By.name("lastName");
        By levelSelect= By.id("level");
        By webOption= By.id("web");
        By submitBt= By.id("actions-button");

//        Fill data
        driver.element().type(FnameInput,"Mohamed");
        driver.element().type(LnameInput,"Kamel");
        driver.element().select(levelSelect,"Junior");
        driver.element().click(webOption);
//        Submit
        driver.element().click(submitBt);
//        quit browesr
        driver.quit();



    }
    @Test
    public void openWaitsPage()
    {

//        SHAFT.Properties.web.set().targetBrowserName(Browser.CHROME.browserName());
//        Start browser

        SHAFT.GUI.WebDriver driver = new SHAFT.GUI.WebDriver();
        driver.browser().navigateToURL("https://hatem-hatamleh.github.io/Selenium-html/");
//        click waits button
        By actionsBt= By.linkText("Waits");
        driver.element().click(actionsBt);
//      await and click Me button
        By clickMeBt= By.cssSelector(".button.primary");
        driver.element().click(clickMeBt);
        By secondaryBt= By.cssSelector(".button.secondary");
        driver.element().click(secondaryBt);
//        quit
        driver.quit();

    }
    @Test
    public void loginRequestAPI()
    {
//        instantiate API
        SHAFT.API api = new SHAFT.API("https://todo.qacart.com/api/v1");
//        create input hashmap
        HashMap<String,String> loginBody= new HashMap();
        loginBody.put("email","simple.user73@gmail.com");
        loginBody.put("password","1q2w3e4r5t6y");
//        post the body
        api.post("/users/login").setContentType(ContentType.JSON).setRequestBody(loginBody).perform();
//        assert response has a firstName
        api.assertThatResponse().body().contains("firstName");
//        log firstName
        String name=api.getResponseJSONValue("firstName");
        System.out.println(name);
    }
}
