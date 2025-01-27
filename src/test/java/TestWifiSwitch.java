import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestWifiSwitch extends TestBase{
    @Test
    public void test() {
        //AndroidDriver driver=new AndroidDriver();
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                "left", 100, "top", 100, "width", 200, "height", 200,
                "direction", "down",
                "percent", 1.0
        ));
        //driver.findElement(AppiumBy.androidUIAutomator(String.format("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"WebView\"))")));

    }
    @Test
    public void testWifi(){
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
                "intent","io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"
        ));
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.RelativeLayout\").instance(1)")).click();
        driver.findElement(AppiumBy.id("android:id/alertTitle")).isDisplayed();
        String msg=driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(msg,"WiFi settings");
        driver.setClipboardText("Hussein");
        driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys(driver.getClipboardText());
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='OK']")).click();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }
}
