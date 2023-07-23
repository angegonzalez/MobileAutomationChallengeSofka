package screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainScreen extends BaseScreen {

    private final By imagesBy = By.xpath("//android.widget.FrameLayout" +
            "[@resource-id='br.com.dafiti:id/state_view']");
    private final By notificationCloseBy = By.xpath("//android.view.View[@index=2]");
    private final By colombiaImageBy = By.xpath("//android.widget.ImageView" +
            "[@resource-id='br.com.dafiti:id/image_flag_co']");
    public MainScreen(AndroidDriver driver) {
        super(driver);
    }

    public MainScreen selectCountry(){
        tapElement(colombiaImageBy);
        return this;
    }

    public MainScreen closeMessage() {
        waitAndCloseMessage(notificationCloseBy);
        return this;
    }

    public PromosScreen selectRandomImage() {
        waitForElementPresenceFluent(imagesBy);
        WebElement imageElement = selectRandomElement(mapToElements(imagesBy));
        clickElement(imageElement);
        return new PromosScreen(driver);
    }
}
