package Lesson_16;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jsExecutor;

    @FindBy(xpath = "//div[@class='pay__wrapper']")
    private WebElement form;

    @FindBy(xpath = "//div[@class='pay__partners']")
    private WebElement payPartners;

    @FindBy(xpath = "//a[@href='/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/']")
    private WebElement helpLink;

    @FindBy(xpath = "//button[@id='cookie-agree']")
    private WebElement cookieButton;

    @FindBy(xpath = "//input[@id='connection-phone']")
    private WebElement phoneField;

    @FindBy(xpath = "//input[@id='connection-sum']")
    private WebElement sumField;

    @FindBy(xpath = "//input[@id='connection-email']")
    private WebElement emailField;

    @FindBy(xpath = "//form[@id='pay-connection']//button")
    private WebElement continueButton;

    @FindBy(css = ".select__header")
    private WebElement selectHeader;

    @FindBy(xpath = "//li[@class='select__item']/p[text()='Услуги связи']")
    private WebElement connectionOption;

    @FindBy(xpath = "//li[@class='select__item']/p[text()='Домашний интернет']")
    private WebElement internetOption;

    @FindBy(xpath = "//li[@class='select__item']/p[text()='Рассрочка']")
    private WebElement instalmentOption;

    @FindBy(xpath = "//li[@class='select__item']/p[text()='Задолженность']")
    private WebElement arrearsOption;

    @FindBy(xpath = "//div[@id='pay-section']")
    private WebElement paySection;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        this.jsExecutor = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void acceptCookies() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
            wait.until(ExpectedConditions.invisibilityOf(cookieButton));
        } catch (Exception e) {
            System.out.println("Сегодня куки решили не отображаться =)");
        }
    }

    public boolean isFormDisplayed() {
        return form.isDisplayed();
    }

    public boolean isPayPartnersDisplayed() {
        List<WebElement> images = payPartners.findElements(By.tagName("img"));

        if (images.size() != 5) {
            return false;
        }

        for (WebElement image : images) {
            if (!image.isDisplayed()) {
                return false;
            }
        }

        return true;
    }

    public void clickHelpLink() {
        helpLink.click();
    }

    public void fillPaymentForm(String phone, String sum, String email) {
        phoneField.sendKeys(phone);
        sumField.sendKeys(sum);
        emailField.sendKeys(email);
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    private void openSelect() {
        scrollToElement(paySection);
        wait.until(ExpectedConditions.elementToBeClickable(selectHeader)).click();
    }

    private void scrollToElement(WebElement element) {
        jsExecutor.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    private void clickElementWithJS(WebElement element) {
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public List<String> selectConnectionOption() {
        openSelect();
        scrollToElement(connectionOption);
        wait.until(ExpectedConditions.visibilityOf(connectionOption));
        clickElementWithJS(connectionOption);

        List<String> conFields = new ArrayList<>();
        conFields.add(phoneField.getAttribute("placeholder"));
        conFields.add(sumField.getAttribute("placeholder"));
        conFields.add(emailField.getAttribute("placeholder"));
        return conFields;
    }

    public List<String> selectInternetOption() {
        openSelect();
        scrollToElement(internetOption);
        wait.until(ExpectedConditions.visibilityOf(internetOption));
        clickElementWithJS(internetOption);

        WebElement intPhoneField = driver.findElement(By.xpath("//input[@id='internet-phone']"));
        WebElement intSumField = driver.findElement(By.xpath("//input[@id='internet-sum']"));
        WebElement intEmailField = driver.findElement(By.xpath("//input[@id='internet-email']"));

        List<String> intFields = new ArrayList<>();
        intFields.add(intPhoneField.getAttribute("placeholder"));
        intFields.add(intSumField.getAttribute("placeholder"));
        intFields.add(intEmailField.getAttribute("placeholder"));

        return intFields;
    }

    public List<String> selectInstalmentOption() {
        openSelect();
        scrollToElement(instalmentOption);
        wait.until(ExpectedConditions.visibilityOf(instalmentOption));
        clickElementWithJS(instalmentOption);

        WebElement instPhoneField = driver.findElement(By.xpath("//input[@id='score-instalment']"));
        WebElement instSumField = driver.findElement(By.xpath("//input[@id='instalment-sum']"));
        WebElement instEmailField = driver.findElement(By.xpath("//input[@id='instalment-email']"));

        List<String> instFields = new ArrayList<>();
        instFields.add(instPhoneField.getAttribute("placeholder"));
        instFields.add(instSumField.getAttribute("placeholder"));
        instFields.add(instEmailField.getAttribute("placeholder"));

        return instFields;
    }

    public List<String> selectArrearsOption() {
        openSelect();
        scrollToElement(arrearsOption);
        wait.until(ExpectedConditions.visibilityOf(arrearsOption));
        clickElementWithJS(arrearsOption);

        WebElement arrPhoneField = driver.findElement(By.xpath("//input[@id='score-arrears']"));
        WebElement arrSumField = driver.findElement(By.xpath("//input[@id='arrears-sum']"));
        WebElement arrEmailField = driver.findElement(By.xpath("//input[@id='arrears-email']"));

        List<String> arrFields = new ArrayList<>();
        arrFields.add(arrPhoneField.getAttribute("placeholder"));
        arrFields.add(arrSumField.getAttribute("placeholder"));
        arrFields.add(arrEmailField.getAttribute("placeholder"));

        return arrFields;
    }
}
