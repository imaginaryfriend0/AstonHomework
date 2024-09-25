package Lesson_16;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PopupPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//iframe[@class='bepaid-iframe']")
    private WebElement popupIframe;

    @FindBy(xpath = "//span[contains(.,'Номер:')]")
    private WebElement popupPhoneNumber;

    @FindBy(xpath = "//span[contains(.,'BYN')]")
    private WebElement popupSum;

    @FindBy(xpath = "//button[contains(text(),'Оплатить')]")
    private WebElement buttonValue;

    public PopupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        PageFactory.initElements(driver, this);
    }

    public boolean isPopupDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(popupIframe));
        return popupIframe.isDisplayed();
    }

    public List<String> initPopupValues() {
        driver.switchTo().frame(popupIframe);

        popupPhoneNumber = wait.until(ExpectedConditions.visibilityOf(popupPhoneNumber));
        popupSum = wait.until(ExpectedConditions.visibilityOf(popupSum));
        buttonValue = wait.until(ExpectedConditions.visibilityOf(buttonValue));

        List<String> popupValues = new ArrayList<>();
        popupValues.add(popupPhoneNumber.getText());
        popupValues.add(popupSum.getText());
        popupValues.add(buttonValue.getText());

        return popupValues;
    }

    public boolean areCardsDisplayedPopup() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cards-brands cards-brands__container ng-tns-c61-0 ng-trigger ng-trigger-brandsState ng-star-inserted']")));
        WebElement cards = driver.findElement(By.xpath("//div[@class='cards-brands cards-brands__container ng-tns-c61-0 ng-trigger ng-trigger-brandsState ng-star-inserted']"));

        if (!cards.isDisplayed()) {
            return false;
        }

        List<WebElement> images = cards.findElements(By.tagName("img"));
        return images.size() == 5;
    }


    public List<String> getPopupFieldsPlaceholders() {
        WebElement cardNumber = driver.findElement(By.xpath("//div[@class='content ng-tns-c46-1']/label"));
        WebElement cardDate = driver.findElement(By.xpath("//div[@class='content ng-tns-c46-4']/label"));
        WebElement cardCvc = driver.findElement(By.xpath("//div[@class='content ng-tns-c46-5']/label"));
        WebElement cardHolderName = driver.findElement(By.xpath("//div[@class='content ng-tns-c46-3']/label"));

        List<String> placeholders = new ArrayList<>();
        placeholders.add(cardNumber.getText());
        placeholders.add(cardDate.getText());
        placeholders.add(cardCvc.getText());
        placeholders.add(cardHolderName.getText());

        return placeholders;
    }
}