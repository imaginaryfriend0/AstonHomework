package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UITest {
    @Test
    public void Test(){
        System.setProperty("webdriver.firefox.driver","C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.navigate().to("https://www.mts.by/");
        killTheCookie(wait);

        //Проверяем, что форма существует и отображается
        WebElement form = driver.findElement(By.xpath("//div[@class='pay__wrapper']"));
        Assertions.assertNotNull(form,"Форма не была найдена");
        Assertions.assertTrue(form.isDisplayed(),"Форма не отображается");

        //Проверяем, что логотипы платежных систем существуют и отображаются
        WebElement payPartners = driver.findElement(By.xpath("//div[@class='pay__partners']"));
        Assertions.assertNotNull(payPartners,"Логотипы не были найдены");
        Assertions.assertTrue(payPartners.isDisplayed(),"Логотипы платежных систем не отображаются");

        //Проверяем, что по нажатию на гиперссылку происходит переход
        String currentUrl = driver.getCurrentUrl();
        WebElement helpLink = driver.findElement(By.xpath("//a[@href='/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/']"));
        helpLink.click();

        String newUrl = driver.getCurrentUrl();
        Assertions.assertNotEquals(currentUrl,newUrl,"Ссылка не изменилась");
        Assertions.assertTrue(newUrl.contains("/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"));

        //Возвращаемся на предыдущую страницу
        driver.navigate().back();
        killTheCookie(wait);

        //Проверяем работу кнопки "Продолжить"
        WebElement phoneField = driver.findElement(By.xpath("//input[@id='connection-phone']"));
        WebElement sumField = driver.findElement(By.xpath("//input[@id='connection-sum']"));
        WebElement emailField = driver.findElement(By.xpath("//input[@id='connection-email']"));
        WebElement button = driver.findElement(By.xpath("//form[@id='pay-connection']//button"));

        phoneField.sendKeys("297777777");
        sumField.sendKeys("100");
        emailField.sendKeys("asd@mail.ru");
        button.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='bepaid-app']")));
        WebElement popup = driver.findElement(By.xpath("//div[@class='bepaid-app']"));
        Assertions.assertTrue(popup.isDisplayed(),"При нажатии кнопки не высвечивается попап оплаты");

        driver.quit();
    }

    public void killTheCookie(WebDriverWait waitDriver){
        //Закрываем попап с куки
        try {
            WebElement cookie = waitDriver.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='cookie-agree']")));
            cookie.click();
            waitDriver.until(ExpectedConditions.invisibilityOfElementLocated(By.className("cookie")));
        } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            System.out.println("Сегодня куки решили не отображаться =)");
        }
    }
}
