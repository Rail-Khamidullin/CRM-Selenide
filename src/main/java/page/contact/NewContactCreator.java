package page.contact;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class NewContactCreator {
    SelenideElement editButtonContact = $x("//div[text()='Основное']/following-sibling::div/button");

    @Step("Проверка наличия кнопки 'Редактировать', как атрибута созданного контакта")
    public boolean checkCreateContact() {
        try {
            editButtonContact.shouldBe(Condition.enabled);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
