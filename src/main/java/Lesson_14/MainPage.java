package Lesson_14;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    @FindBy(xpath = "//div[@class='bepaid-app']")
    private WebElement popup;

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

    private WebElement popupSum;

    private WebElement popupPhoneNumber;

    private WebElement buttonValue;

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
        return payPartners.isDisplayed();
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

    public boolean isPopupDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(popup));
        return popup.isDisplayed();
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

    public String[] initPopupValues() {
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='bepaid-iframe']")));

        popupPhoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(.,'Номер:')]")));
        popupSum = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(.,'BYN')]")));
        buttonValue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Оплатить')]")));

        String[] asd = new String[3];
        asd[0] = popupPhoneNumber.getText();
        asd[1] = popupSum.getText();
        asd[2] = buttonValue.getText();
        return asd;
    }

    public boolean areCardsDisplayedPopup() {
        WebElement cards = driver.findElement(By.xpath("//div[@class='cards-brands cards-brands__container ng-tns-c61-0 ng-trigger ng-trigger-brandsState ng-star-inserted']"));
        return cards.isDisplayed();
    }

    public String[] getPopupFieldsPlaceholders() {
        WebElement cardNumber = driver.findElement(By.xpath("//div[@class='content ng-tns-c46-1']/label"));
        WebElement cardDate = driver.findElement(By.xpath("//div[@class='content ng-tns-c46-4']/label"));
        WebElement cardCvc = driver.findElement(By.xpath("//div[@class='content ng-tns-c46-5']/label"));
        WebElement cardHolderName = driver.findElement(By.xpath("//div[@class='content ng-tns-c46-3']/label"));

        String[] placeholders = new String[4];
        placeholders[0] = cardNumber.getText();
        placeholders[1] = cardDate.getText();
        placeholders[2] = cardCvc.getText();
        placeholders[3] = cardHolderName.getText();

        return placeholders;
    }

    public String[] selectConnectionOption() {
        openSelect();
        scrollToElement(connectionOption);
        wait.until(ExpectedConditions.visibilityOf(connectionOption));
        clickElementWithJS(connectionOption);

        String[] conFields = new String[3];
        conFields[0] = phoneField.getAttribute("placeholder");
        conFields[1] = sumField.getAttribute("placeholder");
        conFields[2] = emailField.getAttribute("placeholder");
        return conFields;
    }

    public String[] selectInternetOption() {
        openSelect();
        scrollToElement(internetOption);
        wait.until(ExpectedConditions.visibilityOf(internetOption));
        clickElementWithJS(internetOption);

        WebElement intPhoneField = driver.findElement(By.xpath("//input[@id='internet-phone']"));
        WebElement intSumField = driver.findElement(By.xpath("//input[@id='internet-sum']"));
        WebElement intEmailField = driver.findElement(By.xpath("//input[@id='internet-email']"));

        String[] intFields = new String[3];
        intFields[0] = intPhoneField.getAttribute("placeholder");
        intFields[1] = intSumField.getAttribute("placeholder");
        intFields[2] = intEmailField.getAttribute("placeholder");

        return intFields;
    }

    public String[] selectInstalmentOption() {
        openSelect();
        scrollToElement(instalmentOption);
        wait.until(ExpectedConditions.visibilityOf(instalmentOption));
        clickElementWithJS(instalmentOption);

        WebElement instPhoneField = driver.findElement(By.xpath("//input[@id='score-instalment']"));
        WebElement instSumField = driver.findElement(By.xpath("//input[@id='instalment-sum']"));
        WebElement instEmailField = driver.findElement(By.xpath("//input[@id='instalment-email']"));

        String[] instFields = new String[3];
        instFields[0] = instPhoneField.getAttribute("placeholder");
        instFields[1] = instSumField.getAttribute("placeholder");
        instFields[2] = instEmailField.getAttribute("placeholder");

        return instFields;
    }

    public String[] selectArrearsOption() {
        openSelect();
        scrollToElement(arrearsOption);
        wait.until(ExpectedConditions.visibilityOf(arrearsOption));
        clickElementWithJS(arrearsOption);

        WebElement arrPhoneField = driver.findElement(By.xpath("//input[@id='score-arrears']"));
        WebElement arrSumField = driver.findElement(By.xpath("//input[@id='arrears-sum']"));
        WebElement arrEmailField = driver.findElement(By.xpath("//input[@id='arrears-email']"));

        String[] arrFields = new String[3];
        arrFields[0] = arrPhoneField.getAttribute("placeholder");
        arrFields[1] = arrSumField.getAttribute("placeholder");
        arrFields[2] = arrEmailField.getAttribute("placeholder");

        return arrFields;
    }
}