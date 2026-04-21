package IOSAppiumTests;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import BaseTests.IOSBaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class IOSLocatorTest extends IOSBaseTest {
	
	
	
	
	@Test
	public void appiumIOSLocatorTest() 
	{
		
	
		driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();	
		
//		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Text Entry']")).click();
	    driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == 'TextEntry'`]")).click();
	    driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeCell")).sendKeys("Hello World");
	    driver.findElement(AppiumBy.accessibilityId("OK")).click();
	    
	    driver.findElement(AppiumBy.iOSNsPredicateString("type=='XCUIElementTypeStaticText' AND value='Confirm / Cancel'")).click();
	    //driver.findElement(AppiumBy.iOSNsPredicateString("type=='XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Confirm'")).click();
	    //driver.findElement(AppiumBy.iOSNsPredicateString("type=='XCUIElementTypeStaticText' AND value ENDSWITH[c] 'Cancel'")).click();
	    
	    String alertText=driver.findElement(AppiumBy.iOSNsPredicateString("name BEGINSWITH[c] 'A message'")).getText();
	    System.out.println(alertText);
	    
	    driver.findElement(AppiumBy.iOSNsPredicateString("lebel=='Confirm'")).click();
		
	}

}
