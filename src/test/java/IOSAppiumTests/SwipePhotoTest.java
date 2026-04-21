package IOSAppiumTests;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseTests.IOSBaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class SwipePhotoTest  {
	
	public IOSDriver driver;
	public AppiumDriverLocalService service;

	
	@BeforeClass
	public void configureAppium() throws MalformedURLException, URISyntaxException
	{
		service = new AppiumServiceBuilder()
		        .withIPAddress("127.0.0.1")
		        .usingPort(4723)
		        .build();
		
		service.start();
		
		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName("iPhone 13 Pro");
	    options.setPlatformVersion("15.5");
	    options.setWdaLaunchTimeout(Duration.ofSeconds(20));
	    
	    driver = new IOSDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}
	
	
	
	@Test
	public void swipeTest()
	{
		Map<String,String> params = new HashMap<>();
		params.put("bundleId", "com.apple.mobileslideshow");
		
		//Launching photos app
		driver.executeScript("mobile:launchApp", params);
		
		//Click on All Photos tab
		driver.findElement(AppiumBy.iOSNsPredicateString("label=='All Photos'")).click();
		
		//Get count of all photos
		List<WebElement> allPhotos = driver.findElements(AppiumBy.iOSClassChain("**/XCUIElementTypeCell"));
		int count = allPhotos.size();
		
		//Click on first photo
		driver.findElement(By.xpath("//XCUIElementTypeCell[1]")).click();
		
		Map<String,String> swipeParams = new HashMap<>();
		swipeParams.put("direction", "left");
		
		//Swipe to all photos till no of photos
		for(int i=0;i<count;i++)
		{
			System.out.println(driver.findElement(By.xpath("//XCUIElementTypeNavigationBar")).getAttribute("name"));
			driver.executeScript("mobile:swipe", swipeParams);
		}
		
		//Back to previous screen and click on Albums tab
		driver.navigate().back();
		driver.findElement(AppiumBy.accessibilityId("Albums")).click();

	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
		service.stop();
	}

}
