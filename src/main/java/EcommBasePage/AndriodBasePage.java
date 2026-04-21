package EcommBasePage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndriodBasePage extends AppiumBasePage{
	
	AndroidDriver driver;
	
	public AndriodBasePage(AndroidDriver driver)
	{
		super(driver);
		this.driver=driver;
	}
	
	public WebElement scrollToText(String text)
	{
		WebElement ele=driver.findElement(AppiumBy.androidUIAutomator(
			    "new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"
			));
		return ele;
	}
	
	public void longPressAction(WebElement ele)
	{
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),
						"duration",2000));
	}
	
	public void swipeAction(WebElement ele,String direction)
	{
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)ele).getId(),			 
			    "direction", direction,
			    "percent", 0.5
			));
	}
	
	
	
	

}
