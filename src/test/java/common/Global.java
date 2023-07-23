package common;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.log4testng.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

public class Global {
    public static final int GLOBAL_TIMEOUT = 20;
    public static final int FLUENT_TIMEOUT_TYPE = 2;
    public static final int FLUENT_TIMEOUT = 5;
    public static final int POLLING_TIMEOUT = 100;
    public static Properties properties;
    public static Logger logger = Logger.getLogger(Global.class);

    public static int generateRandomIndex(int minValue, int maxValue) {
        return (int) (Math.random() * (maxValue - minValue + 1) + minValue);
    }

    public static DesiredCapabilities getCapabilities(PlatformType platform) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform == PlatformType.BROWSER_STACK) {

            HashMap<String, Object> browserstackOptions = new HashMap<>();
            browserstackOptions.put("appiumVersion", "2.0.0");
            browserstackOptions.put("interactiveDebugging", "true");
            browserstackOptions.put("video", "true");

            capabilities.setCapability("bstack:options", browserstackOptions);
            capabilities.setCapability("platformName", "android");
            capabilities.setCapability("platformVersion", "13.0");
            capabilities.setCapability("deviceName", "Samsung Galaxy S23 Ultra");
            capabilities.setCapability("app", "bs://ff95d86798f9fb3afa17ae2ec356a164d8f4cd0f");
            capabilities.setCapability("language", "es");
            capabilities.setCapability("locale", "CO");
            return capabilities;
        } else if (platform == PlatformType.LOCAL) {
            Global.loadProperties();
            String platformName = properties.getProperty("PLATFORM_NAME");
            String deviceName = properties.getProperty("DEVICE_NAME");
            String automationName = properties.getProperty("AUTOMATION_NAME");
            String packageName = properties.getProperty("APP_PACKAGE");
            String appActivity = properties.getProperty("APP_ACTIVITY");

            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, packageName);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
        }

        return capabilities;

    }

    public static URL getConnectHost(PlatformType platform) {
        URL host = null;

        if (platform == PlatformType.BROWSER_STACK) {
            Global.loadProperties();
            String username = properties.getProperty("BROWSERSTACK_USERNAME");
            String accessKey = properties.getProperty("BROWSERSTACK_ACCESS_KEY");
            String server = properties.getProperty("BROWSERSTACK_SERVER");
            try {
                host = new URL("http://" + username + ":" + accessKey + "@" + server + "/wd/hub");
            } catch (MalformedURLException e) {
                logger.error("Error defining host URL :" + e.getMessage());
            }
        } else if (platform == PlatformType.LOCAL) {
            try {
                host = new URL(properties.getProperty("APPIUM_HOST"));
            } catch (MalformedURLException e) {
                logger.error("Error defining host URL :" + e.getMessage());
            }
        }

        return host;
    }

    private static void loadProperties() {
        properties = new Properties();
        try {
            properties.load(new FileReader("src/test/resources/config.properties"));
        } catch (FileNotFoundException e) {
            logger.error("Properties file was not found!");
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("Error while opening properties file!");
            throw new RuntimeException(e);
        }
    }

}
