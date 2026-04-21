package AndroidAppiumTests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTests.EcommBaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class EcommWebViewTests extends EcommBaseTest {
	
	public EcommWebViewTests() throws IOException {
		super();
	}

	@Test
	public void PurchaseProduct() throws InterruptedException
	{
		// Fill Form 
		
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Poulami Datta");
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));")).click();
		driver.findElement(By.xpath("//android.widget.Button[contains(@text,'Shop')]")).click();
		
		// Add Products To Cart
		
		String productList[]= {"Converse All Star"};
		for(String productName:productList)
		{
			addToCart(productName);
		}
	
		
		//Click on Cart Icon and Verify Added Products Displayed on Cart Page
		
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();		
		new WebDriverWait(driver,Duration.ofSeconds(5))
		.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Cart']")));
		List<WebElement> pdtNameListOnCart=driver.findElements(By.id("com.androidsample.generalstore:id/productName"));
		for(String productName:productList)
		{
			boolean match=pdtNameListOnCart.stream().map(pdt->pdt.getText()).anyMatch(pdt->pdt.equals(productName));
			Assert.assertTrue(match);
		}

		// Click Check Box
		
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		
		// Proceed to web page
		
		String appContext= driver.getContext();
		
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		
		Thread.sleep(20000);
		
		Set<String> contexts= driver.getContextHandles();
		
		System.out.println(contexts);
		
		for(String context : contexts)
		{
			if(!context.equals(appContext))
			{
				driver.context(context);
				break;
			}
		}
		driver.findElement(By.name("q")).sendKeys("rahul shetty academy");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		
		Thread.sleep(10000);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		
		driver.context(appContext);


	}

	public void addToCart(String productName)
	{
		//driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));
		driver.findElement(AppiumBy.androidUIAutomator(
			    "new UiScrollable(new UiSelector()).scrollIntoView(text(\""+productName+"\"));"
			));

		String before_xpath="//android.widget.TextView[@text='";
		String after_xpath="']//following-sibling::android.widget.LinearLayout//android.widget.TextView[@text='ADD TO CART']";
		driver.findElement(By.xpath(before_xpath+productName+after_xpath)).click();
	}
	
	
}