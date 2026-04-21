package IOSAppiumTests;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import BaseTests.IOSBaseTest;
import io.appium.java_client.AppiumBy;

public class LongPressTest extends IOSBaseTest {
	
	@Test
	public void longPressEvent()
	{
		driver.findElement(AppiumBy.accessibilityId("Steppers")).click();
		WebElement ele = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='Increament'`][3]"));
		
		Map<String,Object> params = new HashMap<>();
		params.put("element", ((RemoteWebElement)ele).getId());
		params.put("duration",5);
		
		driver.executeScript("mobile: touchAndHold",params);
	}

}
