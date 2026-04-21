package IOSAppiumTests;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import BaseTests.IOSBaseTest;
import io.appium.java_client.AppiumBy;

public class ScrollTest extends IOSBaseTest {
	
	@Test
	public void scrollTestEvent()
	{
		driver.findElement(AppiumBy.accessibilityId("Steppers")).click();
		WebElement ele = driver.findElement(AppiumBy.accessibilityId("Web View"));
		
		Map<String,Object> params = new HashMap<>();
		params.put("element", ((RemoteWebElement)ele).getId());
		params.put("direction","down");
		
		driver.executeScript("mobile: scroll",params);
		
		driver.findElement(AppiumBy.accessibilityId("Web View")).click();
		
		//Go back to previous page
		
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=''UIKitCatalog]")).click();
	}

}
