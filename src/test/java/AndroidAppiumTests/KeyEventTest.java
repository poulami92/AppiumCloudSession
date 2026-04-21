package AndroidAppiumTests;

import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTests.AndroidBaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class KeyEventTest extends AndroidBaseTest {
	@Test
	public void keyTest() throws InterruptedException
	{
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='WiFi settings']")).click();
		String actualTitle=driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(actualTitle, "WiFi settings");
		
		
		driver.findElement(By.id("android:id/edit")).sendKeys("PD WIFI");
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
	    driver.findElement(By.id("android:id/button1")).click();
	    driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.pressKey(new KeyEvent(AndroidKey.HOME));

	
	}
}