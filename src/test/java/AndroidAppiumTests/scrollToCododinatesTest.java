package AndroidAppiumTests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import BaseTests.AndroidBaseTest;
import io.appium.java_client.AppiumBy;

public class scrollToCododinatesTest extends AndroidBaseTest {
	@Test
	public void appiumTest() throws InterruptedException
	{
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        
        //return true/flase if there is more page to scroll or not
        
        boolean canScrollMore = (boolean)((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "down",
			    "percent", 3.0
			    
			));
        
        
        while(canScrollMore)
        {
        	canScrollMore=(boolean)((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
    			    "left", 100, "top", 100, "width", 200, "height", 200,
    			    "direction", "down",
    			    "percent", 3.0
    	    ));		    
        }


	
	}
}