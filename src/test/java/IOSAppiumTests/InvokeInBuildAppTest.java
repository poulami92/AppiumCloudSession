package IOSAppiumTests;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.HashMap;
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

public class InvokeInBuildAppTest  {
	
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
	public void invokeAppTest()
	{
		Map<String,String> params = new HashMap<>();
		params.put("bundleId", "com.apple.mobileslideshow");
		
		driver.executeScript("mobile:launchApp", params);

	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
		service.stop();
	}

}
