package stepdefs;


import driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import screens.*;

public class AddItemsShoppingCart {
    private final AndroidDriver driver = DriverManager.getDriver();
    private int totalShoppingCart;

    MainScreen mainScreen = new MainScreen(driver);
    PromosScreen promosScreen;
    SignInScreen signInScreen;
    MyAccountScreen myAccountScreen;
    CategoriesScreen categoriesScreen;
    ProductsScreen productsScreen;
    ShoppingCartScreen shoppingCartScreen;

    @Given("the user is logged into the app")
    @Step("Given the user is logged into the app")
    public void userIsLogged() {
        promosScreen = mainScreen.closeMessage().selectRandomImage();
        signInScreen = promosScreen.clickMyAccount().clickEnter();
        myAccountScreen = signInScreen.clickSignInWithEmail().signInWithCredentials().closeMessage();
    }

    @And("the user adds two items to the shopping cart")
    @Step("And the user adds two items to the shopping cart")
    public void userAddsItems() {
        categoriesScreen = myAccountScreen.clickCategories();
        productsScreen = categoriesScreen.selectRandomCategory();
        shoppingCartScreen = productsScreen.selectRandomProducts().addProductsToShoppingCart().goToShoppingCart();
    }

    @When("the user goes to the checkout before the payment process")
    @Step("When the user goes to the checkout before the payment process")
    public void userGoesCheckout() {
        totalShoppingCart = shoppingCartScreen.getTotalShoppingCart();
    }

    @Then("the displayed total value is the sum of the two products added by the user")
    @Step("Then the displayed total value is the sum of the two products added by the user")
    public void verifyDisplayedTotal() {
        int productsTotal = productsScreen.getTotalProducts();
        Assertions.assertThat(totalShoppingCart).isEqualTo(productsTotal);
    }
}
