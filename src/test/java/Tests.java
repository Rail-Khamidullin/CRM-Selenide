import org.junit.jupiter.api.Test;
import web.MainPage;

public class Tests extends BaseSelenidePage {

    @Test
    public void createNewContact() {
        MainPage mainPage = new MainPage();
        mainPage.openContactsPage().clickButtonCreateNewContact().createNewContact().check();
    }
}
