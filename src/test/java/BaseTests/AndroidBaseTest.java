package BaseTests;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AndroidBaseTest {
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	UiAutomator2Options options;
	
	@BeforeClass
	public void configureAppium() throws MalformedURLException, URISyntaxException
	{
		service = new AppiumServiceBuilder()
		        .withIPAddress("127.0.0.1")
		        .usingPort(4723)
		        .build();
		
		service.start();
		
		options = new UiAutomator2Options();
		options.setDeviceName("Pixel 8 Emulator");
		
		//To run on read andriod device
		//options.setDeviceName("Andriod Device"); 
		
		options.setApp("C:\\Users\\MSUSERSL123\\eclipse-workspace\\AppiumSession\\resources\\General-Store.apk");
		
	}
	
	@BeforeMethod
	public void setUpDriver() throws MalformedURLException, URISyntaxException
	{
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}
	
	@AfterMethod
	public void quitDriver()
	{
		driver.quit();
	}
	
	@AfterClass
	public void tearDown()
	{
		service.stop();
	}
	



}
