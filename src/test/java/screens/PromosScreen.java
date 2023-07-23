package screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class PromosScreen extends BaseScreen {

    private final By btnMyAccountBy = By.xpath("//android.widget.TextView[@text='Mi Cuenta']");
    private final By imageMessageCloseBy = By.xpath("//android.widget.ImageButton" +
            "[@content-desc='cerrar']");

    public PromosScreen(AndroidDriver driver) {
        super(driver);
    }

    public MyAccountScreen clickMyAccount() {
        waitForElementPresence(btnMyAccountBy);
        clickElement(btnMyAccountBy);
        return new MyAccountScreen(driver);
    }

    public PromosScreen closeMessage() {
        waitAndCloseMessage(imageMessageCloseBy);
        return this;
    }
}
