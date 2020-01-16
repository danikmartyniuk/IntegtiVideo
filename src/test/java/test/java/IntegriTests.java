package test.java;

import org.testng.annotations.Test;
import test.java.Pages.IntegriChatPage;
import test.java.Pages.IntegriSettingsPage;
import test.java.Pages.IntegriUploadPage;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class IntegriTests extends BaseTest {

    IntegriChatPage chat;

    @Test
    public void validateMessageTesting() {

        String message = "bla";
        String anyURL = "https://www.tut.by/";
        String jsCode = "<html><body><p>test</p></body></html>";
        String editMessage = "privetos";
        String emptyMessage = "";

        chat = new IntegriChatPage(driver);

        chat.openPage();
        chat.typeMessage(message);
        chat.sendMessageUsingButton();
        chat.messageShouldContainText(message, 1);

        chat.typeMessage("privet");
        chat.sendMessageUsingEnter();
        chat.messageShouldContainText("privet", 2);

        chat.sendMessageUsingEnter();

        chat.sendThousandSymbols();
        chat.sendMessageUsingEnter();

        chat.openPage();
        chat.typeMessage(anyURL);
        chat.sendMessageUsingEnter();
        chat.messageShouldContainLink();

        chat.openPage();
        chat.typeMessage(jsCode);
        chat.sendMessageUsingEnter();
        chat.messageContainsJavaScriptCode();

        chat.openPage();
        chat.typeMessage(message);
        chat.sendMessageUsingEnter();
        chat.editMessage(editMessage);
        chat.messageShouldContainText(editMessage, 1);

        chat.openPage();
        chat.typeMessage(message);
        chat.sendMessageUsingEnter();
        chat.editAndDelete(emptyMessage);

        chat.openPage();
        chat.typeMessage(message);
        chat.sendMessageUsingEnter();
        chat.deleteMessage();

        chat.openPage();
        chat.sendElevenMessages(message);
    }

    IntegriSettingsPage settings;

    @Test
    public void settingsTesting() throws IOException, UnsupportedFlavorException {

        String name = "Danik";
        String email = "privet@kak.dela";
        String photoURL = "https://img.gazeta.ru/files3/861/12292861/RIAN_5841797.HR-pic4_zoom-1500x1500-26932.jpg";

        settings = new IntegriSettingsPage(driver);

        settings.openPage();
        settings.openSettings();
        settings.changeName(name);

        settings.openPage();
        settings.openSettings();
        settings.changeEmail(email);

        settings.openPage();
        settings.openSettings();
        settings.changeAvatar(photoURL);

        settings.openPage();
        settings.inviteUser();

        settings.openPage();
        settings.getLinkFromScript();
    }

    IntegriUploadPage upload;

    @Test
    public void uploadFilesTesting () {

        String pathOne = "C:\\Users\\Mybook\\Desktop\\TheFatherOfQA.docx";
        String pathTwo = "C:\\Users\\Mybook\\Desktop\\macr.docx";

        upload = new IntegriUploadPage(driver);

        upload.openPage();
        upload.uploadFile(pathOne);

        upload.openPage();
        upload.uploadSomeFiles(pathOne,pathTwo);
    }

}
