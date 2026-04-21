package AndroidAppiumTests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTests.AndroidBaseTest;
import io.appium.java_client.AppiumBy;

public class scrollToElementTest extends AndroidBaseTest {
	@Test
	public void scrollToTextTest() throws InterruptedException
	{
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        
		//where to scroll is known prior
	    driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView1\"));"));

	
	}
}