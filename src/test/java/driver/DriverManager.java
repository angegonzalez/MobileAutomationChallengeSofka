package driver;

import common.Global;
import common.PlatformType;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class DriverManager {

    public static AndroidDriver driver;

    public static void configureDriver() {
        DesiredCapabilities desiredCapabilities = Global.getCapabilities(PlatformType.LOCAL);
        URL host = Global.getConnectHost(PlatformType.LOCAL);
        driver = new AndroidDriver(host, desiredCapabilities);
    }

    public static AndroidDriver getDriver() {
        return driver;
    }

}
