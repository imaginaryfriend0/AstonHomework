package Lesson_14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UITest {
    private String phone = "297777777";
    private String sum = "100";
    private String email = "asd@mail.ru";

    @Test
    public void Test() {
        System.setProperty("webdriver.firefox.driver", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();

        MainPage page = new MainPage(driver);

        driver.navigate().to("https://www.mts.by/");
        page.acceptCookies();

        // Проверяем, что форма существует и отображается
        Assertions.assertTrue(page.isFormDisplayed(), "Форма не отображается");

        // Проверяем, что логотипы платежных систем существуют и отображаются
        Assertions.assertTrue(page.isPayPartnersDisplayed(), "Логотипы платежных систем не отображаются");

        // Проверяем работу выбора опций
        //Домашний интернет
        String[] internet = page.selectInternetOption();
        Assertions.assertEquals(internet[0], "Номер абонента", "Плейсхолдер номера абонента слетел");
        Assertions.assertEquals(internet[1], "Сумма", "Плейсхолдер суммы слетел");
        Assertions.assertEquals(internet[2], "E-mail для отправки чека", "Плейсхолдер мыла слетел");

        //Рассрочка
        String[] instalment = page.selectInstalmentOption();
        Assertions.assertEquals(instalment[0], "Номер счета на 44", "Плейсхолдер номера счета слетел");
        Assertions.assertEquals(instalment[1], "Сумма", "Плейсхолдер суммы слетел");
        Assertions.assertEquals(instalment[2], "E-mail для отправки чека", "Плейсхолдер мыла слетел");

        //Задолженность
        String[] arrears = page.selectArrearsOption();
        Assertions.assertEquals(arrears[0], "Номер счета на 2073", "Плейсхолдер номера счета слетел");
        Assertions.assertEquals(arrears[1], "Сумма", "Плейсхолдер суммы слетел");
        Assertions.assertEquals(arrears[2], "E-mail для отправки чека", "Плейсхолдер мыла слетел");

        //Услуги связи
        String[] connection = page.selectConnectionOption();
        Assertions.assertEquals(connection[0], "Номер телефона", "Плейсхолдер номера телефона слетел");
        Assertions.assertEquals(connection[1], "Сумма", "Плейсхолдер суммы слетел");
        Assertions.assertEquals(connection[2], "E-mail для отправки чека", "Плейсхолдер мыла слетел");

        // Проверяем, что по нажатию на гиперссылку происходит переход
        String currentUrl = driver.getCurrentUrl();
        page.clickHelpLink();
        String newUrl = driver.getCurrentUrl();
        Assertions.assertNotEquals(currentUrl, newUrl, "Ссылка не изменилась");
        Assertions.assertTrue(newUrl.contains("/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"));

        // Возвращаемся на предыдущую страницу
        driver.navigate().back();
        page.acceptCookies();

        // Проверяем работу кнопки "Продолжить"
        page.fillPaymentForm(phone, sum, email);
        page.clickContinueButton();
        Assertions.assertTrue(page.isPopupDisplayed(), "При нажатии кнопки не высвечивается попап оплаты");

        //Проверка отображения ввода данных на попапе
        String[] webElements = page.initPopupValues();
        String actualSum = webElements[1].split(" ")[0].split("\\.")[0];
        String actualButtonValue = webElements[2].substring(9, 12);
        String actualPhone = webElements[0].substring(30);

        Assertions.assertEquals(actualSum, sum, "Сумма неверна");
        Assertions.assertEquals(actualPhone, phone, "Телефон неверен");
        Assertions.assertEquals(actualButtonValue, sum, "Сумма на кнопке неверна");
        Assertions.assertTrue(page.areCardsDisplayedPopup(), "Иконки карт в попапе не отображаются");

        //Проверка плейсхолдеров полей карты
        String[] placeholders = page.getPopupFieldsPlaceholders();
        Assertions.assertEquals(placeholders[0], "Номер карты", "Плейсхолдер номера карты слетел");
        Assertions.assertEquals(placeholders[1], "Срок действия", "Плейсхолдер срока карты слетел");
        Assertions.assertEquals(placeholders[2], "CVC", "Плейсхолдер CVC карты слетел");
        Assertions.assertEquals(placeholders[3], "Имя держателя (как на карте)", "Плейсхолдер имени держателя карты слетел");

        driver.close();
    }
}
