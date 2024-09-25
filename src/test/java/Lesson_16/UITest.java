package Lesson_16;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

@Epic("Тест интерфейса")
@Feature("Взаимодействие с интерфейсом")
public class UITest {
    private WebDriver driver;
    private MainPage page;
    private PopupPage popupPage;

    private final String phone = "297777777";
    private final String sum = "100";
    private final String email = "asd@mail.ru";

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.firefox.driver", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.mts.by/");
        page = new MainPage(driver);
        popupPage = new PopupPage(driver);
        page.acceptCookies();
    }

    @Test
    @Story("Валидация платежной формы")
    @Description("Проверяем корректное отображение формы и логотипов платежных систем")
    @Severity(SeverityLevel.CRITICAL)
    public void testFormAndPayPartnersDisplay() {
        Assertions.assertTrue(page.isFormDisplayed(), "Форма не отображается");
        Assertions.assertTrue(page.isPayPartnersDisplayed(), "Логотипы платежных систем не отображаются");
    }

    @Test
    @Story("Валидация выбора видов платежа")
    @Description("Проверка плейсхолдеров")
    @Severity(SeverityLevel.NORMAL)
    public void testOptionPlaceholders() {
        validateInternetOption();
        validateInstalmentOption();
        validateArrearsOption();
        validateConnectionOption();
    }

    @Step("Валидация формы 'Домашний интернет'")
    private void validateInternetOption() {
        List<String> internet = page.selectInternetOption();
        Assertions.assertEquals(internet.get(0), "Номер абонента", "Плейсхолдер номера абонента слетел");
        Assertions.assertEquals(internet.get(1), "Сумма", "Плейсхолдер суммы слетел");
        Assertions.assertEquals(internet.get(2), "E-mail для отправки чека", "Плейсхолдер мыла слетел");
    }

    @Step("Валидация формы 'Рассрочка'")
    private void validateInstalmentOption() {
        List<String> instalment = page.selectInstalmentOption();
        Assertions.assertEquals(instalment.get(0), "Номер счета на 44", "Плейсхолдер номера счета слетел");
        Assertions.assertEquals(instalment.get(1), "Сумма", "Плейсхолдер суммы слетел");
        Assertions.assertEquals(instalment.get(2), "E-mail для отправки чека", "Плейсхолдер мыла слетел");
    }

    @Step("Валидация формы 'Задолженность'")
    private void validateArrearsOption() {
        List<String> arrears = page.selectArrearsOption();
        Assertions.assertEquals(arrears.get(0), "Номер счета на 2073", "Плейсхолдер номера счета слетел");
        Assertions.assertEquals(arrears.get(1), "Сумма", "Плейсхолдер суммы слетел");
        Assertions.assertEquals(arrears.get(2), "E-mail для отправки чека", "Плейсхолдер мыла слетел");
    }

    @Step("Валидация формы 'Услуги связи'")
    private void validateConnectionOption() {
        List<String> connection = page.selectConnectionOption();
        Assertions.assertEquals(connection.get(0), "Номер телефона", "Плейсхолдер номера телефона слетел");
        Assertions.assertEquals(connection.get(1), "Сумма", "Плейсхолдер суммы слетел");
        Assertions.assertEquals(connection.get(2), "E-mail для отправки чека", "Плейсхолдер мыла слетел");
    }

    @Test
    @Story("Проверка гиперссылки")
    @Description("Проверка перехода по гиперссылке")
    @Severity(SeverityLevel.NORMAL)
    public void testHelpLinkNavigation() {
        String currentUrl = driver.getCurrentUrl();
        page.clickHelpLink();
        String newUrl = driver.getCurrentUrl();
        Assertions.assertNotEquals(currentUrl, newUrl, "Ссылка не изменилась");
        assert newUrl != null;
        Assertions.assertTrue(newUrl.contains("/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"));
        driver.navigate().back();
        page.acceptCookies();
    }

    @Test
    @Story("Тест попапа")
    @Description("Проверка соотвествия ранее введенных данных с отображением на попапе")
    @Severity(SeverityLevel.CRITICAL)
    public void testPopupInteraction() {
        page.fillPaymentForm(phone, sum, email);
        page.clickContinueButton();
        Assertions.assertTrue(popupPage.isPopupDisplayed(), "При нажатии кнопки не высвечивается попап оплаты");

        List<String> popupValues = popupPage.initPopupValues();
        String actualSum = popupValues.get(1).split(" ")[0].split("\\.")[0];
        String actualButtonValue = popupValues.get(2).substring(9, 12);
        String actualPhone = popupValues.get(0).substring(30);

        Assertions.assertEquals(actualSum, sum, "Сумма неверна");
        Assertions.assertEquals(actualPhone, phone, "Телефон неверен");
        Assertions.assertEquals(actualButtonValue, sum, "Сумма на кнопке неверна");

        Assertions.assertTrue(popupPage.areCardsDisplayedPopup(), "Иконки карт в попапе не отображаются");
        validatePopupFieldPlaceholders();
    }

    @Step("Валидация плейсхолдеров попапа")
    private void validatePopupFieldPlaceholders() {
        List<String> placeholders = popupPage.getPopupFieldsPlaceholders();
        Assertions.assertEquals(placeholders.get(0), "Номер карты", "Плейсхолдер номера карты слетел");
        Assertions.assertEquals(placeholders.get(1), "Срок действия", "Плейсхолдер срока карты слетел");
        Assertions.assertEquals(placeholders.get(2), "CVC", "Плейсхолдер CVC карты слетел");
        Assertions.assertEquals(placeholders.get(3), "Имя держателя (как на карте)", "Плейсхолдер имени держателя карты слетел");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
