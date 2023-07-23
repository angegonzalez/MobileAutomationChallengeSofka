package common;

import driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.Before;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    @Before
    @Description("Configure driver before tests")
    public void before() {
        DriverManager.configureDriver();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] captureScreenshot(AndroidDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
