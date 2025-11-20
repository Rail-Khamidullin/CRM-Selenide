
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import page.interests.InterestsPage;
import rest.SupportREST;
import web.MainPage;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static rest.SupportREST.DELETE_INTEREST_API;
import static rest.SupportREST.INTEREST_LIST_API;
import static web.MainPage.INTERESTS;

// создание Лида
@ExtendWith(AllureJunit5.class)
public class TestCreateInterest extends BaseSelenidePage {

    // инициализация REST запросов
    SupportREST supportREST = new SupportREST();
    // токен для авторизации по REST запросу
    private String token;
    // id лида
    private Integer id;

    @Test
    @DisplayName("Создание Лида")
    public void testInterestCreate() {

        // получение токена
        token = supportREST.adminToken();
        // создание экземпляра класса с главной страницей
        MainPage mainPage = new MainPage();
        // открытие таблицы с Лидами
        InterestsPage interestsPage = mainPage.openPage(INTERESTS, InterestsPage.class);
        // переход к созданию Лида (заполнение полей и выбор кнопки "Создать Лид")
        interestsPage.openCreateInterests().createNewInterest();
        // получение id созданного Лида
        id = supportREST.getId(token, INTEREST_LIST_API);

        assertAll("Созданный Лид отображается в таблице",
                () -> assertEquals(interestsPage.getIdInterest(), String.valueOf(id)),
                () -> assertEquals(interestsPage.getNameInterest(), String.valueOf(supportREST.getName(token, INTEREST_LIST_API)))
        );
    }

    @AfterEach
    public void tearDown() {
        // удаление Лида
        supportREST.deleteEntity(token, id, DELETE_INTEREST_API);
        Selenide.closeWebDriver();
    }
}
