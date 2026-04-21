package BaseTests;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.google.common.collect.ImmutableMap;

import PageObjects.android.FormPage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class EcommBaseTest {
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	UiAutomator2Options options;
	public FormPage formPage;
	public static Properties prop;
	
	public EcommBaseTest() throws IOException
	{
		prop = new Properties();
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\resources\\testData.properties");
		prop.load(fs);
	}
	
	@BeforeClass
	public void configureAppium() throws MalformedURLException, URISyntaxException
	{
		service = new AppiumServiceBuilder()
		        .withIPAddress(prop.getProperty("ipAddress"))
		        .usingPort(Integer.parseInt(prop.getProperty("port")))
		        .build();
		
		service.start();
		
		options = new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("AndroidDeviceName"));
		options.setApp(System.getProperty("user.dir")+"\\resources\\General-Store.apk");
		options.setChromedriverExecutable(System.getProperty("user.dir")+"\\resources\\chromedriver-win64\\chromedriver.exe");
	}
	
	@BeforeMethod
	public void setUpDriver() throws MalformedURLException, URISyntaxException
	{
		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		formPage = new FormPage(driver);
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
