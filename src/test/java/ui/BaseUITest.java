package ui;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@Guice
public abstract class BaseUITest {

    protected static final String BASE_URL = "https://s.gsb.co.zm/";
    private static final Logger log = LoggerFactory.getLogger(BaseUITest.class);

    private static void applyChromeCapabilities() {
        MutableCapabilities capabilities = new MutableCapabilities();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-notifications");
        Map<String, Object> prefs = new HashMap<>();
        Map<String, Object> profile = new HashMap<>();
        Map<String, Object> contentSettings = new HashMap<>();
        contentSettings.put("media_stream", 2);
        profile.put("managed_default_content_settings", contentSettings);
        prefs.put("profile", profile);
        options.setExperimentalOption("prefs", prefs);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        applyChromeCapabilities();
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 30000;
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
    }

    @BeforeMethod(alwaysRun = true, dependsOnMethods = "setUp")
    public void openWebPage() {
        open(BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult testResult) {
        String testStatus = "passed";
        if (!testResult.isSuccess())
            testStatus = "failed";
        log.info(String.format("Test %s is %s", testResult.getMethod(), testStatus.toUpperCase()));
        closeWebDriver();
    }
}
