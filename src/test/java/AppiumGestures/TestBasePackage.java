package AppiumGestures;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;


public class TestBasePackage {
    AppiumDriverLocalService service;
    AndroidDriver driver;
    @BeforeClass
    public void setupService() {
        service =new AppiumServiceBuilder()
                .usingPort(4723)
                .withIPAddress("127.0.0.1")
                .withArgument(() -> "--use-drivers", "uiautomator2")
                .build();
        service.start();
    }
    @BeforeMethod
    public void setupDriver(){
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(Platform.ANDROID.name());
        //options.noReset();
        options.setDeviceName("Medium Phone API 35");
        options.setApp("./src/main/resources/ApiDemos-debug.apk");
        options.setAppPackage("io.appium.android.apis");
        options.setAppActivity("io.appium.android.apis.preference.PreferenceDependencies");
        try {
            driver = new AndroidDriver( new URI("http://127.0.0.1:4723").toURL(), options
            );
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
                    // The default URL in Appium 1 is http://
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }



    @AfterMethod
    public void tearDownDriver() {
        driver.quit();
    }
    @AfterClass
    public void tearDownService() {
        service.stop();
    }

    public void scrollByName(String value){
        driver.findElement(AppiumBy.androidUIAutomator(String.format
                ("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\""+value+"\"))")));
    }
    public void continuousScroll(){
        Boolean canScrollMore;
        do{
            canScrollMore =(Boolean)  ((JavascriptExecutor) driver)
                .executeScript("mobile: scrollGesture", ImmutableMap.of(
                        "left", 100, "top", 100, "width", 200, "height", 200,
                        "direction", "down",
                        "percent", 1.0));
        }while(Boolean.TRUE.equals(canScrollMore));
    }
    public void longPress(WebElement element, int duration){
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),"duration",duration
        ));
    }
    public void swipeAction(WebElement element, String direction, double percent){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", direction,
                "percent", percent
        ));
    }
    
}
