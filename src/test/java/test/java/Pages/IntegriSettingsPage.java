package test.java.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class IntegriSettingsPage extends BasePage {

    private static final By SETTINGS = By.className("integri-chat-settings integri-pointer");
    private static final By NAME_FIELD = By.name("userName");
    private static final By EMAIL_FIELD = By.name("userEmail");
    private static final By PIC_FIELD = By.name("userPic");
    private static final By USER_PHOTO = By.className("integri-user-pic integri-session-is-online integri-current-session");
    private static final By USER_NAME = By.className("integri-session-user-name");
    private static final By INVITE_BUTTON = By.id("invite-users-to-chat");
    private static final By SCRIPT_LINK = By.className("component-code");
    private static final String URL = "https://dev.integrivideo.com/demo/chat/new";
    private static final By NOTIFICATION_LINK = By.className("col-xs-11 col-sm-4 alert alert-info animated fadeInDown");

    public IntegriSettingsPage(WebDriver driver) {
        super(driver);
    }

    public void openPage () {
        driver.get(URL);
    }

    public void openSettings () {
        WebElement settings = driver.findElement(SETTINGS);
        action.moveToElement(settings).perform();
        settings.click();
    }

    public void changeName (String name) {
        WebElement inputName = driver.findElement(NAME_FIELD);
        inputName.clear();
        inputName.sendKeys(name);
        inputName.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.textToBe(USER_NAME,name));
        String changedName = driver.findElement(USER_NAME).getText();
        assertEquals(changedName,name,"the wrong changed name");
    }

    public void changeEmail (String email) {
        WebElement inputEmail = driver.findElement(EMAIL_FIELD);
        inputEmail.sendKeys(email);
        inputEmail.sendKeys(Keys.ENTER);
        openSettings();
        String changedEmail = driver.findElement(EMAIL_FIELD).getText();
        assertEquals(changedEmail,email,"the email hasn't changed");
    }

    public void changeAvatar (String photoURL) {
        WebElement inputURL = driver.findElement(PIC_FIELD);
        inputURL.sendKeys(photoURL);
        inputURL.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(USER_PHOTO));
    }

    public void inviteUser () throws IOException, UnsupportedFlavorException {
        driver.findElement(INVITE_BUTTON).click();
        String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        String currentURL = driver.getCurrentUrl();
        assertEquals(data,currentURL,"there is wrong link");
    }

    public void getLinkFromScript () throws IOException, UnsupportedFlavorException {
        driver.findElement(SCRIPT_LINK).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(NOTIFICATION_LINK));
    }
}