package page.interests;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import page.BasePage;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static locators.Locators.*;

public class InterestsPage extends BasePage {

    // локатор наименования записи в таблице
    private static final SelenideElement NAME_ON_TABLE_INTERESTS = $x(".//tr[@class = 'clickable-JNIQAI']//td[@data-cell-name='name']/div");

    @Step("Выбор кнопки 'Создать Лид'")
    public CreateInterestsPage openCreateInterests() {
        return openPage(actionButton("Создать Лид"), CreateInterestsPage.class);
    }

    @Step("Получение id созданного Лида из таблицы")
    public String getIdInterest() {
        return ID_ON_TABLE.shouldBe(visible).getText();
    }

    @Step("Получение названия созданного Лида из таблицы")
    public String getNameInterest() {
        return NAME_ON_TABLE_INTERESTS.shouldBe(visible).getText();
    }
}
