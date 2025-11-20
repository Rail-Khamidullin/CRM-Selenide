import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.contact.ContactsPage;
import rest.SupportREST;
import web.MainPage;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static rest.SupportREST.*;
import static web.MainPage.CONTACTS;

// создание контакта
public class TestCreateContact extends BaseSelenidePage {

    // инициализация REST запросов
    SupportREST supportREST = new SupportREST();
    // экземпляр класса главной страницы
    MainPage mainPage = new MainPage();
    // токен для авторизации по REST запросу
    private String token;
    // id контакта
    private int id;

    @Test
    @DisplayName("Создание контакта")
    public void createNewContact() {
        // получение токена
        token = supportREST.adminToken();
        // статус отображения кнопки "Редактировать"
        boolean statusCreator = mainPage.openPage(CONTACTS, ContactsPage.class).
                clickButtonCreateNewContact().createNewContact().checkCreateContact();
        // получение id созданного контакта
        id = supportREST.getId(token, CONTACT_LIST_API);

        assertTrue(statusCreator, "Редактировать' отображается на странице контакта");
    }

    @AfterEach
    public void tearDown() {
        // удаление контакта
        supportREST.deleteEntity(token, id, DELETE_CONTACT_API);
        Selenide.closeWebDriver();
    }
}
