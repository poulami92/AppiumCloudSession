package AndroidAppiumTests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import BaseTests.EcommBaseTest;
import io.appium.java_client.AppiumBy;

public class EcommTests extends EcommBaseTest {
	
	public EcommTests() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void PurchaseProduct() throws InterruptedException
	{
		// Fill Form 
		
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Poulami Datta");
		//driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));")).click();
		driver.findElement(By.xpath("//android.widget.Button[contains(@text,'Shop')]")).click();
		
		// Add Products To Cart
		
		String productList[]= {"Converse All Star","Jordan 6 Rings"};
		for(String productName:productList)
		{
			addToCart(productName);
		}
		
		
//		List<WebElement> pdtElems=driver.findElements(By.id("com.androidsample.generalstore:id/productName"));
//		int pdtCount = pdtElems.size();
//		
//		for(int i=0;i<pdtCount;i++)
//		{
//			String pdtName=pdtElems.get(i).getText();
//			if(pdtName.equals("Jordan 6 Rings"))
//			{
//				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
//				break;
//			}
//			
//		}
		
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
		
		// Verify Total Price of Cart Items
		
		List<WebElement> productPrices =driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		double sum=0;
		for(WebElement pdtPrice:productPrices)
		{
			double price=Double.parseDouble(pdtPrice.getText().substring(1));
			sum=price+sum;
		}				
		String displaySumText =driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		double displaySum=Double.parseDouble(displaySumText.substring(1));		
		Assert.assertEquals(sum,displaySum);
		
		//Long Press on Terms button
		
		WebElement termsElem = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId",((RemoteWebElement)termsElem).getId(),
						"duration",2000));
		driver.findElement(By.id("android:id/button1")).click();

		// Click Check Box
		
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		
		// Proceed to web page
		
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		
		Thread.sleep(7000);


	}

	@Test(enabled=true)
	public void validateToastMessage()
	{
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));")).click();
		driver.findElement(By.xpath("//android.widget.Button[contains(@text,'Shop')]")).click();
		String actualToasMessage=driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("name");
		Assert.assertEquals(actualToasMessage, "Please enter your name");
		
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