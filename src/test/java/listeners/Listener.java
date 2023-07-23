package listeners;

import common.Global;
import common.Hooks;
import driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        AndroidDriver driver = DriverManager.getDriver();
        Hooks.captureScreenshot(driver);
    }
    @Override
    public void onFinish(ITestContext context) {
        AndroidDriver driver = DriverManager.getDriver();
        driver.quit();
    }

}
