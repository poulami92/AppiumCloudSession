package IOSAppiumTests;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTests.IOSBaseTest;
import io.appium.java_client.AppiumBy;

public class DropDownTest extends IOSBaseTest {
	
	@Test
	public void dropDownEvent()
	{
		driver.findElement(AppiumBy.accessibilityId("Picker View")).click();
		
		driver.findElement(AppiumBy.accessibilityId("Red color component value")).sendKeys("80");
		
		driver.findElement(AppiumBy.accessibilityId("Green color component value")).sendKeys("220");
		
		driver.findElement(AppiumBy.accessibilityId("Blue color component value")).sendKeys("105");
		
		String actualText=driver.findElement(AppiumBy.accessibilityId("Green color component value")).getText();
		
		Assert.assertEquals(actualText,"105");
		
	}

}
