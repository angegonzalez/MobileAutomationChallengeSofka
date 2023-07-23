package screens;

import io.appium.java_client.android.AndroidDriver;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.By;

public class SignInScreen extends BaseScreen {

    private final By txtEmailBy = By.xpath("//android.widget.EditText[@text='Email']");
    private final By txtPasswordBy = By.xpath("//android.widget.EditText[@text='Contraseña']");
    private final By btnSignInBy = By.xpath("//android.widget.Button" +
            "[@resource-id='br.com.dafiti:id/login_or_create_button']");
    private final By btnSignWithEmailBy = By.xpath("//android.widget.Button" +
            "[@resource-id='br.com.dafiti:id/login_button']");
    private final Dotenv dotenv = Dotenv.load();
    private final String email = dotenv.get("EMAIL");
    private final String password = dotenv.get("PASSWORD");

    public SignInScreen(AndroidDriver driver) {
        super(driver);
    }

    public SignInScreen clickSignInWithEmail() {
        waitForElementPresence(btnSignWithEmailBy);
        clickElement(btnSignWithEmailBy);
        return this;
    }

    public MyAccountScreen signInWithCredentials() {
        waitForElementPresence(txtEmailBy);
        waitForElementPresence(txtPasswordBy);
        waitForElementPresence(btnSignInBy);

        typeInElement(txtEmailBy, email);
        typeInElement(txtPasswordBy, password);
        tapElement(btnSignInBy);

        return new MyAccountScreen(driver);
    }

}
