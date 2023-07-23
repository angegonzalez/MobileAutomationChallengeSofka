package screens;

import com.google.common.collect.ImmutableMap;
import common.Global;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import java.time.Duration;
import java.util.List;

public class BaseScreen {
    protected AndroidDriver driver;
    protected WebDriverWait wait;
    protected Wait<AndroidDriver> fluentWaitType;
    protected Wait<AndroidDriver> fluentWait;

    private final Logger logger = Logger.getLogger(BaseScreen.class);

    public BaseScreen(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Global.GLOBAL_TIMEOUT));
        this.fluentWaitType = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Global.FLUENT_TIMEOUT_TYPE))
                .pollingEvery(Duration.ofMillis(Global.POLLING_TIMEOUT))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);
        this.fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Global.FLUENT_TIMEOUT))
                .pollingEvery(Duration.ofMillis(Global.POLLING_TIMEOUT))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);
    }

    public WebElement mapToElement(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> mapToElements(By locator) {
        return driver.findElements(locator);
    }

    public void waitForElementPresence(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitForElementNotPresent(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForElementPresenceFluent(By locator) throws NoSuchElementException {
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForTyping(By locator, String text) {
        try {
            fluentWaitType.until(ExpectedConditions.textToBe(locator, text));
        } catch (org.openqa.selenium.TimeoutException e) {
            logger.error(e.getMessage());
        }
    }

    public WebElement selectRandomElement(List<WebElement> elementsList) {
        int randomIndex = Global.generateRandomIndex(0, elementsList.size() - 1);
        return elementsList.get(randomIndex);
    }

    public void tapElement(By locator) {
        waitForElementPresence(locator);
        WebElement element = mapToElement(locator);
        String elementId = ((RemoteWebElement) element).getId();
        driver.executeScript("mobile: clickGesture", ImmutableMap.of(
                "elementId", elementId
        ));
    }

    public void clickElement(By locator) {
        mapToElement(locator).click();
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void typeInElement(By locator, String text) {
        tapElement(locator);
        WebElement element = mapToElement(locator);
        String elementId = ((RemoteWebElement) element).getId();
        driver.executeScript("mobile: type", ImmutableMap.of(
                "elementId", elementId, "text", text
        ));
        waitForTyping(locator, text);
    }

    public void waitAndCloseMessage(By locator) {
        waitForElementPresence(locator);
        clickElement(locator);
    }

}
