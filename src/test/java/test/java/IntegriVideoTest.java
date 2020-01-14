package test.java;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;

public class IntegriVideoTest {

    @Test
    public void sendMessageEnter () {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        driver.get("https://dev.integrivideo.com/demo/chat/new");
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        WebElement input = driver.findElement(By.xpath("//*[@id=\"integri-component-chat\"]/div[4]/div[3]/textarea"));
        input.sendKeys("privet");
        input.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("integri-chat-message-text")));
        driver.quit();
    }

    @Test
    public void sendMessageButton () {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        driver.get("https://dev.integrivideo.com/demo/chat/new");
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        WebElement input = driver.findElement(By.xpath("//*[@id=\"integri-component-chat\"]/div[4]/div[3]/textarea"));
        input.sendKeys("privet");
        WebElement btn = driver.findElement(By.xpath("//*[@id=\"integri-component-chat\"]/div[4]/div[3]/div/button[1]"));
        Actions action = new Actions(driver);
        action.moveToElement(btn).perform();
        btn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("integri-chat-message-text")));
        driver.quit();
    }

    @Test
    public void sendThousandSymbols () {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.lipsum.com/feed/html");
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        String text = driver.findElement(By.id("Content")).getText();
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        driver.get("https://dev.integrivideo.com/demo/chat/new");
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        WebElement input = driver.findElement(By.xpath("//*[@id=\"integri-component-chat\"]/div[4]/div[3]/textarea"));
        input.sendKeys(Keys.CONTROL+"v");
        input.sendKeys(Keys.ENTER);
        driver.quit();
    }

    @Test
    public void sendMessageWithLink () {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://dev.integrivideo.com/demo/chat/new");
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement input = driver.findElement(By.xpath("//*[@id=\"integri-component-chat\"]/div[4]/div[3]/textarea"));
        input.sendKeys("https://www.tut.by/");
        input.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"integri-component-chat\"]/div[4]/div[2]/div/div/div[2]/div[2]/a[2]")));
        driver.quit();
    }

    @Test
    public void sendJSCode () {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://dev.integrivideo.com/demo/chat/new");
        WebDriverWait wait = new WebDriverWait(driver, 15);
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        WebElement input = driver.findElement(By.xpath("//*[@id=\"integri-component-chat\"]/div[4]/div[3]/textarea"));
        input.sendKeys("<html><body><p>test</p></body></html>");
        input.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("integri-chat-message-text")));
        String message = driver.findElement(By.className("integri-chat-message-text")).getText();
        assertEquals(message,"test","there is no text");
        driver.quit();
    }

    @Test
    public void editMessage () {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://dev.integrivideo.com/demo/chat/new");
        WebDriverWait wait = new WebDriverWait(driver, 15);
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        WebElement input = driver.findElement(By.xpath("//*[@id=\"integri-component-chat\"]/div[4]/div[3]/textarea"));
        input.sendKeys("zdarova");
        input.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("integri-chat-message-text")));
        WebElement edit = driver.findElement(By.xpath("//*[@id=\"integri-component-chat\"]/div[4]/div[2]/div/div/div[3]/div/span[1]"));
        Actions action = new Actions(driver);
        action.moveToElement(edit);
        edit.click();
        WebElement newInput = driver.findElement(By.xpath("//*[@id=\"integri-component-chat\"]/div[4]/div[2]/div/div/div[2]/textarea"));
        newInput.clear();
        newInput.sendKeys("poka");
        newInput.sendKeys(Keys.ENTER);
        driver.quit();
    }

    @Test
    public void editWithDeleting () {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://dev.integrivideo.com/demo/chat/new");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement input = driver.findElement(By.xpath("//*[@id=\"integri-component-chat\"]/div[4]/div[3]/textarea"));
        input.sendKeys("message to be edited and deleted");
        input.sendKeys(Keys.ENTER);
        WebElement edit = driver.findElement(By.xpath("//*[@id=\"integri-component-chat\"]/div[4]/div[2]/div/div/div[3]/div/span[1]"));
        Actions action = new Actions(driver);
        action.moveToElement(edit);
        edit.click();
        WebElement newInput = driver.findElement(By.xpath("//*[@id=\"integri-component-chat\"]/div[4]/div[2]/div/div/div[2]/textarea"));
        newInput.clear();
        newInput.sendKeys("");
        newInput.sendKeys(Keys.ENTER);
        assertEquals(driver.findElements(By.xpath("//*[@id=\"integri-component-chat\"]/div[4]/div[2]/div/div/div[2]/textarea")).size(), 1,"the field has assumed an empty value");
        driver.quit();
    }

    @Test
    public void deleteMessage () {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://dev.integrivideo.com/demo/chat/new");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        Actions action = new Actions(driver);
        WebElement input = driver.findElement(By.xpath("//*[@id=\"integri-component-chat\"]/div[4]/div[3]/textarea"));
        input.sendKeys("privet");
        input.sendKeys(Keys.ENTER);
        WebElement delete = driver.findElement(By.xpath("//*[@id=\"integri-component-chat\"]/div[4]/div[2]/div/div/div[3]/div/span[2]"));
        action.moveToElement(delete).perform();
        delete.click();
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("integri-chat-message-text")));
        driver.quit();
    }

    @Test
    public void sendElevenMessages () {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://dev.integrivideo.com/demo/chat/new");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement input = driver.findElement(By.xpath("//*[@id=\"integri-component-chat\"]/div[4]/div[3]/textarea"));
        for (int i = 0; i < 25; i++){
            input.sendKeys("p");
            input.sendKeys(Keys.ENTER);
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"integri-component-chat\"]/div[6]/div")));
        driver.quit();
    }

    @Test
    public void changeName () {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://dev.integrivideo.com/demo/chat/new");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        String name = "Danik";
        WebElement settings = driver.findElement(By.xpath("//*[@id=\"integri-component-chat\"]/div[4]/div[4]/span[6]"));
        Actions action = new Actions(driver);
        action.moveToElement(settings).perform();
        settings.click();
        WebElement nameInput = driver.findElement(By.name("userName"));
        nameInput.clear();
        nameInput.sendKeys(name);
        nameInput.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.textToBe(By.className("integri-session-user-name"),name));
        String changedName = driver.findElement(By.className("integri-session-user-name")).getText();
        assertEquals(changedName,name,"the wrong changed name");
        driver.quit();
    }

    @Test
    public void changeEmail () {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://dev.integrivideo.com/demo/chat/new");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        String email = "privet@kak.dela";
        WebElement settings = driver.findElement(By.xpath("//*[@id=\"integri-component-chat\"]/div[4]/div[4]/span[6]"));
        Actions action = new Actions(driver);
        action.moveToElement(settings).perform();
        settings.click();
        WebElement nameEmail = driver.findElement(By.name("userEmail"));
        nameEmail.sendKeys(email);
        nameEmail.sendKeys(Keys.ENTER);
        action.moveToElement(settings).perform();
        settings.click();
        String changedEmail = driver.findElement(By.name("userEmail")).getText();
        assertEquals(changedEmail,email,"the email hasn't changed");
        driver.quit();
    }

    @Test
    public void changePhotoUrl () {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://dev.integrivideo.com/demo/chat/new");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        String photoURL = "https://img.gazeta.ru/files3/861/12292861/RIAN_5841797.HR-pic4_zoom-1500x1500-26932.jpg";
        WebElement settings = driver.findElement(By.xpath("//*[@id=\"integri-component-chat\"]/div[4]/div[4]/span[6]"));
        Actions action = new Actions(driver);
        action.moveToElement(settings).perform();
        settings.click();
        WebElement userPic = driver.findElement(By.name("userPic"));
        userPic.clear();
        userPic.sendKeys(photoURL);
        userPic.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"integri-component-chat\"]/div[4]/div[1]/div/div[1]")));
        driver.quit();
    }

    @Test
    public void checkInvite () throws IOException, UnsupportedFlavorException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://dev.integrivideo.com/demo/chat/new");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        driver.findElement(By.id("invite-users-to-chat")).click();
        String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        System.out.println(data);
        driver.quit();
    }

    @Test
    public void checkBtnWithCode () throws IOException, UnsupportedFlavorException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://dev.integrivideo.com/demo/chat/new");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.className("component-code")).click();
        String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        System.out.println(data);
        driver.quit();
    }
}
