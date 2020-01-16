package test.java.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IntegriUploadPage extends BasePage {

    private static final By DROP_FILE = By.className("integri-chat-manual-upload integri-pointer");
    private static final By BROWSE_FILE = By.className("integri-file-upload-manual-init");
    private static final String URL = "https://dev.integrivideo.com/demo/chat/new";
    private static final By ATTACHMENT_FILE = By.className("integri-chat-message-attachment integri-chat-message-attachment-file");
    private static final By START = By.className("integri-file-upload-start integri-float-right integri-button-blue");

    public IntegriUploadPage(WebDriver driver) {
        super(driver);
    }

    public void openPage () {
        driver.get(URL);
    }

    public void uploadFile (String file) {

        WebElement drop = driver.findElement(DROP_FILE);
        action.moveToElement(drop).perform();
        drop.click();
        WebElement browse = driver.findElement(BROWSE_FILE);
        action.moveToElement(browse).perform();
        browse.click();
        browse.sendKeys(file);
        driver.findElement(START).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ATTACHMENT_FILE));
    }

    public void uploadSomeFiles (String pathOne, String pathTwo) {
        WebElement drop = driver.findElement(DROP_FILE);
        action.moveToElement(drop).perform();
        drop.click();
        WebElement browse = driver.findElement(BROWSE_FILE);
        action.moveToElement(browse).perform();
        browse.click();
        browse.sendKeys(pathOne + "\n " + pathTwo);
        driver.findElement(START).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(ATTACHMENT_FILE,2));
    }
}
