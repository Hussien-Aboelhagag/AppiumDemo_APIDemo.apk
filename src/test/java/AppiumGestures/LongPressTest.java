package AppiumGestures;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LongPressTest extends TestBaseGestures {
     @Test
     public void testLongPress(){
         driver.findElement(AppiumBy.accessibilityId("Views")).click();
         driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
         driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
         WebElement peopleNames=driver.findElement(AppiumBy.xpath
                 ("//android.widget.TextView[@text='People Names']"));

         longPress(peopleNames,2000);
         Assert.assertTrue(driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Sample menu']")).isDisplayed());
     }
     @Test
     public void scrollToEnd(){
         driver.findElement(AppiumBy.accessibilityId("Views")).click();
         continuousScroll();
     }
}
