package AndroidAppiumTests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import BaseTests.AndroidBaseTest;
import io.appium.java_client.AppiumBy;

public class ActivityTest extends AndroidBaseTest {
	@Test
	public void appiumTest() throws InterruptedException
	{
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
			    "intent","io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"		    
			));
		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='WiFi settings']")).click();
		String actualTitle=driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(actualTitle, "WiFi settings");
		driver.findElement(By.id("android:id/edit")).sendKeys("PD WIFI");
		driver.findElement(By.id("android:id/button1")).click();
	
	}
}