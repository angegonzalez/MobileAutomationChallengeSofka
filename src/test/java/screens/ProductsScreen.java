package screens;

import common.Global;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductsScreen extends BaseScreen {

    private final By productsBy = By.xpath("//androidx.recyclerview.widget.RecyclerView//" +
            "android.widget.FrameLayout/android.widget.LinearLayout");
    private final By txtProductPriceBy = By.xpath("//android.widget.TextView" +
            "[@resource-id='br.com.dafiti:id/current_price']");
    private final By btnAddProductBy = By.xpath("//android.widget.Button" +
            "[@resource-id='br.com.dafiti:id/add_to_cart_button']");
    private final By bottomViewBy = By.xpath("//android.widget.FrameLayout" +
            "[@resource-id='br.com.dafiti:id/design_bottom_sheet']");
    private final By bottomViewTitleAddedBy = By.xpath("//android.widget.TextView" +
            "[@resource-id='br.com.dafiti:id/title']");
    private final By bottomViewTitleSizeBy = By.xpath("//android.widget.TextView" +
            "[@text='Seleccione el tamaño']");
    private final By btnCloseBy = By.xpath("//android.widget.ImageView" +
            "[@resource-id='br.com.dafiti:id/add_to_cart_confirmation_close']");
    private final By btnShoppingCartBy = By.xpath("//android.widget.ImageView" +
            "[@content-desc='Carrito']");
    private final List<WebElement> randomSelectedProductsList = new ArrayList<>();
    private int totalProducts = 0;

    public ProductsScreen(AndroidDriver driver) {
        super(driver);
    }

    public ProductsScreen selectRandomProducts() {

        waitForElementPresence(productsBy);

        List<WebElement> productsElementList = mapToElements(productsBy);
        productsElementList.remove(productsElementList.size() - 1);
        productsElementList.remove(productsElementList.size() - 1);

        int firstRandomIndex = Global.generateRandomIndex(0, productsElementList.size() - 1);
        int secondRandomIndex = Global.generateRandomIndex(0, productsElementList.size() - 1);

        while (firstRandomIndex == secondRandomIndex) {
            secondRandomIndex = Global.generateRandomIndex(0, productsElementList.size() - 1);
        }

        randomSelectedProductsList.add(productsElementList.get(firstRandomIndex));
        randomSelectedProductsList.add(productsElementList.get(secondRandomIndex));

        for (WebElement product : randomSelectedProductsList) {
            addCurrentPriceTotal(product);
        }

        return this;
    }

    public ProductsScreen addProductsToShoppingCart() {
        for (WebElement product : randomSelectedProductsList) {
            addProductToShoppingCart(product);
        }
        return this;
    }

    public ShoppingCartScreen goToShoppingCart() {
        clickElement(btnShoppingCartBy);
        return new ShoppingCartScreen(driver);
    }

    private void addCurrentPriceTotal(WebElement product) {
        String productCurrentPrice = product.findElement(txtProductPriceBy).getText();
        String productCurrentPriceSanitized = productCurrentPrice.
                replace("$", "").
                replace(".", "").
                trim();
        totalProducts += Integer.parseInt(productCurrentPriceSanitized);
    }

    private void addProductToShoppingCart(WebElement element) {
        waitForElementPresence(productsBy);
        WebElement buttonAdd = element.findElement(btnAddProductBy);
        clickElement(buttonAdd);

        waitForElementPresenceFluent(bottomViewBy);

        try {
            waitForElementPresenceFluent(bottomViewTitleAddedBy);
            tapElement(btnCloseBy);
        } catch (TimeoutException exception) {
            waitForElementPresence(bottomViewTitleSizeBy);
            selectSizeOfProduct(element);
        }
    }

    private void selectSizeOfProduct(WebElement element) {
        By productSizesBy = By.xpath("//androidx.recyclerview.widget.RecyclerView" +
                "[@resource-id='br.com.dafiti:id/size_list']/android.widget.TextView");
        waitForElementPresence(productSizesBy);
        List<WebElement> productSizesList = mapToElements(productSizesBy);
        List<String> optionsTextslist = new ArrayList<>();

        for (WebElement productSize : productSizesList) {
            optionsTextslist.add(productSize.getText());
        }

        for (String option : optionsTextslist) {
            By optionToTapBy = By.xpath("//android.widget.TextView[@text='" + option + "']");
            tapElement(optionToTapBy);
            try {
                waitForElementPresenceFluent(bottomViewTitleAddedBy);
                tapElement(btnCloseBy);
                return;
            } catch (TimeoutException ignore) {
                WebElement buttonAdd = element.findElement(btnAddProductBy);
                clickElement(buttonAdd);
            }
        }
    }

    public int getTotalProducts() {
        return totalProducts;
    }


}
