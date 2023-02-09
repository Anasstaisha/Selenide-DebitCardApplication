package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class DebitCardApplicationUiTest {
    @BeforeEach
    void shouldOpenBrowser(){
        open("http://localhost:9999");
    }

    @Test
    void shouldSendFormIfAllCondition() {
        SelenideElement form = $("[id=root]");
        $("[data-test-id=name] input").val("Анастасия Мисюрова");
        $("[data-test-id=phone] input").val("+79166556655");
        $("[data-test-id=agreement]").click();
        $("[type=\"button\"]").click();
        $("[data-test-id=order-success]").shouldHave(exactText(("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.")));
    }

    @Test
    void shouldSendFormIfName() {
        SelenideElement form = $("[id=root]");
        $("[data-test-id=name] input").val("Анастасия");
        $("[data-test-id=phone] input").val("+79166556655");
        $("[data-test-id=agreement]").click();
        $("[type=\"button\"]").click();
        $("[data-test-id=order-success]").shouldHave(exactText(("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.")));
    }

    @Test
    void shouldNotSendFormIfNameWithEnglishKeyboardLayout() {
        SelenideElement form = $("[id=root]");
        $("[data-test-id=name] input").val("Anastassiya Misyurova");
        $("[data-test-id=phone] input").val("+79166556655");
        $("[data-test-id=agreement]").click();
        $("[type=\"button\"]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText(("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")));
    }

    @Test
    void shouldNotSendFormIfNameWithЁ() {
        SelenideElement form = $("[id=root]");
        $("[data-test-id=name] input").val("Матрёна Иванова");
        $("[data-test-id=phone] input").val("+79166556655");
        $("[data-test-id=agreement]").click();
        $("[type=\"button\"]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText(("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")));
    }

    @Test
    void shouldNotSendFormIfNameWithNumbers() {
        SelenideElement form = $("[id=root]");
        $("[data-test-id=name] input").val("Анас1асия");
        $("[data-test-id=phone] input").val("+79166556655");
        $("[data-test-id=agreement]").click();
        $("[type=\"button\"]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText(("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")));
    }

    @Test
    void shouldNotSendFormIfPhoneLonger() {
        SelenideElement form = $("[id=root]");
        $("[data-test-id=name] input").val("Анастасия Мисюрова");
        $("[data-test-id=phone] input").val("+791665566555");
        $("[data-test-id=agreement]").click();
        $("[type=\"button\"]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText(("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")));
    }

    @Test
    void shouldNotSendFormIfPhoneFormatWrong() {
        SelenideElement form = $("[id=root]");
        $("[data-test-id=name] input").val("Анастасия Мисюрова");
        $("[data-test-id=phone] input").val("+7(916)655-66-55");
        $("[data-test-id=agreement]").click();
        $("[type=\"button\"]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText(("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")));
    }

    @Test
    void shouldNotSendFormIfFieldsNotFilled() {
        SelenideElement form = $("[id=root]");
        $("[data-test-id=name] input").val("");
        $("[data-test-id=phone] input").val("");
        $("[data-test-id=agreement]").click();
        $("[type=\"button\"]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText(("Поле обязательно для заполнения")));
    }

    @Test
    void shouldNotSendFormIfPhoneNotFilled() {
        SelenideElement form = $("[id=root]");
        $("[data-test-id=name] input").val("Анастасия Мисюрова");
        $("[data-test-id=phone] input").val("");
        $("[data-test-id=agreement]").click();
        $("[type=\"button\"]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText(("Поле обязательно для заполнения")));
    }

    @Test
    void shouldNotSendFormIfNameNotFilled() {
        SelenideElement form = $("[id=root]");
        $("[data-test-id=name] input").val("");
        $("[data-test-id=phone] input").val("+79166556655");
        $("[data-test-id=agreement]").click();
        $("[type=\"button\"]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText(("Поле обязательно для заполнения")));
    }

    @Test
    void shouldNotSendFormIfAgreementNotGiven() {
        SelenideElement form = $("[id=root]");
        $("[data-test-id=name] input").val("Анастасия Мисюрова");
        $("[data-test-id=phone] input").val("+79166556655");
        $("[type=\"button\"]").click();
        $("[data-test-id=agreement]").shouldHave(exactText(("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй")));
    }
}
