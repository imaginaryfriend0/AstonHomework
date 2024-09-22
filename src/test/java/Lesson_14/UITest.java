package Lesson_14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;

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
        page.selectInternetOption();
        page.selectInstalmentOption();
        page.selectArrearsOption();
        page.selectConnectionOption();

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
        System.out.println(Arrays.toString(webElements));
        String actualSum = webElements[1].split(" ")[0].split("\\.")[0];
        String actualButtonValue = webElements[2].substring(9, 12);
        String actualPhone = webElements[0].substring(30);
        System.out.println(actualButtonValue);

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
