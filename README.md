## Mobile automation challenge
Solved by : Angel Mateo González Bejarano, 2023

### Context and statement of the challenge

Prerequisites: Download the 'Dafiti' app from the store
1. Do log into the app (with a previous created user) 
2. Add two elements to the shopping cart
3. Do checkout before the payment process
4. Verify the total value of the added products (must correspond to the previous added products)

### Challenge resolution

I solve this challenge using Appium with a POM design pattern approach, in addition 
I added cucumber in order to design and execute the proposed test case using the BDD approach.

**Summary of used tools**:
1. Appium (Java) using the POM design pattern and I executed the test case in the `android` platform
2. Cucumber (for BDD approach) and TestNG as runner
3. AssertJ for assertions
4. Allure for reporting (including screenshots on test failures)
5. BrowserStack for execute the test case in a cloud environment

### Run the project

Before all, you need to ensure that you have some tools listed here:
1. Java JDK 19 + Maven (it's important to have this JDK version and Maven for executing the project)
2. Appium Server 2.0.0 (if you have npm you can run `npm i --location=global appium`)
3. UiAutomator2 for Appium (you can install it with: `appium driver install uiautomator2`)
4. Allure reporting tool (last version) (ex, you can install it with: `brew install allure`)

In addition, you need to add some configuration files in order to run the project:

1. The `.env` file: located in the `src/test/resources/` folder and this file will contain the login credentials as:

```txt
EMAIL=[email]
PASSWORD=[password]
```

2. The `config.properties`file: located in the `src/test/resources/` folder and this file will contain the driver's
configuration and some BrowserStack configuration:

```txt
PLATFORM_NAME=android
DEVICE_NAME=[device to use]
AUTOMATION_NAME=uiautomator2
APP_PACKAGE=br.com.dafiti
APP_ACTIVITY=.activity.SplashSelectCountry_
APPIUM_HOST=[appium localhost]
BROWSERSTACK_USERNAME=[browser stack username]
BROWSERSTACK_ACCESS_KEY=[browser stack access key]
BROWSERSTACK_SERVER=[browser stack server]
```

Once the config files are ready, you can modify the `configureDriver()` function in the `driver/DriverManager.java` file:
```java
DesiredCapabilities desiredCapabilities = Global.getCapabilities(PlatformType);
URL host = Global.getConnectHost(PlatformType);
```
to change the capabilities and the host for the `AndroidDriver`, you can use the `PlatformType` enum values:
```java
PlatformType.BROWSER_STACK
PlatformType.LOCAL
```
When the platform is configured as needed you can proceed with the project execution:

1. In a terminal window (run appium server):
```bash
appium
```
2. In another terminal window, located in the project's root folder:
```bash
mvn clean test
```
3. Once the previous command is finished, you can run the following command (in the project's root folder) to view reports:
```bash
allure serve target/allure-results
```