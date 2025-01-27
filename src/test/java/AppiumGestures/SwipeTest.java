package AppiumGestures;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SwipeTest extends TestBaseGestures {
    @Test
    public void swipeTest() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
        WebElement photo = driver.findElement(AppiumBy.xpath("//android.widget.ImageView[1]"));
        Assert.assertEquals(photo.getDomAttribute("focusable"), "true");

        swipeAction(photo,"left",0.30);
        Assert.assertEquals(photo.getDomAttribute("focusable"), "false");
    }
}
