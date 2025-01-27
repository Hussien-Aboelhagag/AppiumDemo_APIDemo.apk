package AppiumGestures;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestByPackageAndActivity extends TestBasePackage {
    @Test
    public void activityTest() {
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
