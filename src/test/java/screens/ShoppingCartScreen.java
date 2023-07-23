package screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ShoppingCartScreen extends BaseScreen {
    private final By totalBy = By.xpath("//android.widget.TextView" +
            "[@resource-id='br.com.dafiti:id/cart_bottom_price_normal']");

    public ShoppingCartScreen(AndroidDriver driver) {
        super(driver);
    }

    public int getTotalShoppingCart() {
        waitForElementPresence(totalBy);
        String totalText = mapToElement(totalBy).getText();
        String totalTextSanitized = totalText.replace("$", "").replace(".", "").trim();
        return Integer.parseInt(totalTextSanitized);
    }


}
