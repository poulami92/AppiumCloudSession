package EcommBasePage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

public class AppiumBasePage {
	
	AppiumDriver driver;
	
	public AppiumBasePage(AppiumDriver driver)
	{
		this.driver=driver;
	}
	
	public double getFomattedAmount(String displayedPrice)
	{
		return Double.parseDouble(displayedPrice.substring(1));
	}
	
	public WebElement findElement(By by)
	{
		return driver.findElement(by);
	}
	
	public void waitForElementToBeVisible(By by)
	{
		new WebDriverWait(driver,Duration.ofSeconds(5))
		.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

}
