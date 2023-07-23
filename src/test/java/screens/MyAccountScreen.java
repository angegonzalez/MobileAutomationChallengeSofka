package screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyAccountScreen extends BaseScreen {
    private final By optionsListBy = By.xpath("//androidx.recyclerview.widget.RecyclerView" +
            "/android.widget.LinearLayout");
    private final By btnCategoriesBy = By.xpath("//android.widget.TextView[@text='Categorías']");
    private final By btnCloseMessage = By.xpath("//android.view.View[@content-desc='Cerrar']");

    public MyAccountScreen(AndroidDriver driver) {
        super(driver);
    }

    public SignInScreen clickEnter() {
        waitForElementPresence(optionsListBy);
        List<WebElement> optionsList = mapToElements(optionsListBy);
        clickElement(optionsList.get(0));
        return new SignInScreen(driver);
    }

    public CategoriesScreen clickCategories() {
        waitForElementPresence(btnCategoriesBy);
        clickElement(btnCategoriesBy);
        return new CategoriesScreen(driver);
    }

    public MyAccountScreen closeMessage() {
        waitAndCloseMessage(btnCloseMessage);
        return new MyAccountScreen(driver);
    }


}
