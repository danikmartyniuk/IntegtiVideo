package test.java.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class IntegriChatPage extends BasePage{

    private static final By MESSAGE_TEXT_AREA = By.tagName("textarea");
    private static final By BUTTON = By.xpath("//button[@title='Send message']");
    private static final By MESSAGE_TEXT = By.className("integri-chat-message-text");
    private static final String URL = "https://dev.integrivideo.com/demo/chat/new";
    private static final String URL_THOUSAND_SYMBOLS = "https://www.lipsum.com/feed/html";
    private static final By EDIT_BUTTON = By.xpath(".//span[@class='iv-icon iv-icon-pencil integri-chat-edit-message']");
    private static final By CHANGING_FIELD = By.tagName("textarea");
    private static final By DELETE_BUTTON = By.xpath(".//span[@class='iv-icon iv-icon-trash2 integri-chat-remove-message']");
    private static final By A_LOT_OF_MESSAGES = By.id("Content");


    public IntegriChatPage (WebDriver driver) {
        super(driver);
    }

    public void openPage () {
        driver.get(URL);
    }

    public void openPage (String url) {
        driver.get(URL_THOUSAND_SYMBOLS);
    }

    public void typeMessage (String message) {
        driver.findElement(MESSAGE_TEXT_AREA).sendKeys(message);
    }

    public void sendMessageUsingButton () {
        driver.findElement(BUTTON).click();
    }

    public void sendMessageUsingEnter () {
        driver.findElement(MESSAGE_TEXT_AREA).sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    /**
     *
     * @param message
     * @param messageNumber = starts from 1
     */

    public void messageShouldContainText(String message, int messageNumber) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(MESSAGE_TEXT,messageNumber - 1));
        String text = driver.findElements(MESSAGE_TEXT).get(messageNumber-1).getText();
        assertEquals(message,text, "wrong message");
    }

    public void ctrlVtoInput (String text) {
        driver.findElement(MESSAGE_TEXT_AREA).sendKeys(Keys.CONTROL+"v");
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public void sendThousandSymbols () {
        openPage(URL_THOUSAND_SYMBOLS);
        String text = driver.findElement(By.id("Content")).getText();
        openPage();
        ctrlVtoInput(text);
    }

    public void messageShouldContainLink () {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("https://www.tut.by/")));
    }

    public void messageContainsJavaScriptCode () {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("integri-chat-message-text")));
        String message = driver.findElement(MESSAGE_TEXT).getText();
        assertEquals(message,"test","there is no text");
    }

    public void editMessage(String newMessage) {
        WebElement editButton = driver.findElement(EDIT_BUTTON);
        action.moveToElement(editButton);
        editButton.click();
        WebElement changingField = driver.findElement(CHANGING_FIELD);
        changingField.clear();
        changingField.sendKeys(newMessage);
        changingField.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MESSAGE_TEXT));
    }

    public void editAndDelete (String emptyMessage) {
        WebElement editButton = driver.findElement(EDIT_BUTTON);
        action.moveToElement(editButton);
        editButton.click();
        WebElement changingField = driver.findElement(CHANGING_FIELD);
        changingField.clear();
        changingField.sendKeys(emptyMessage);
        changingField.sendKeys(Keys.ENTER);
        assertEquals(driver.findElements(CHANGING_FIELD).size(),1,"the empty message was sent");
    }

    public void deleteMessage () {
        WebElement deleteButton = driver.findElement(DELETE_BUTTON);
        action.moveToElement(deleteButton);
        deleteButton.click();
        driver.switchTo().alert().accept();
        driver.navigate().refresh();
        assertEquals(driver.findElements(MESSAGE_TEXT).size(),0,"the message wasn't deleted");
    }

    public void sendElevenMessages (String message) {
        for (int i = 0; i < 35; i++){
            typeMessage(message);
            sendMessageUsingEnter();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(A_LOT_OF_MESSAGES));
    }
}
