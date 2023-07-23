package screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CategoriesScreen extends BaseScreen {
    private final By categoriesBy = By.xpath("//androidx.recyclerview.widget.RecyclerView" +
            "/android.widget.FrameLayout");

    public CategoriesScreen(AndroidDriver driver) {
        super(driver);
    }

    public ProductsScreen selectRandomCategory() {
        waitForElementPresence(categoriesBy);
        WebElement categoryElement = selectRandomElement(mapToElements(categoriesBy));
        clickElement(categoryElement);
        return new ProductsScreen(driver);
    }
}
