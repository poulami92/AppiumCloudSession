package AndroidAppiumTests;

import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTests.AndroidBaseTest;
import io.appium.java_client.AppiumBy;

public class RotateScreenTest extends AndroidBaseTest {
	@Test
	public void appiumTest() throws InterruptedException
	{
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		
		//DeviceRotation rotationParam = new DeviceRotation(0, 0, 90);
		//driver.rotate(rotationParam);
		driver.rotate(ScreenOrientation.LANDSCAPE);
		
		driver.findElement(By.xpath("//android.widget.TextView[@text='WiFi settings']")).click();
		String actualTitle=driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(actualTitle, "WiFi settings");
		driver.findElement(By.id("android:id/edit")).sendKeys("PD WIFI");
		driver.findElement(By.id("android:id/button1")).click();
	
	}
}