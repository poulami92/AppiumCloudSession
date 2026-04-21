package BaseTests;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import PageObjects.ios.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class IOSBaseTest {
	
	public IOSDriver driver;
	public AppiumDriverLocalService service;
	public HomePage homePage;
	
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
		
		//To Run Test on Real IOS Device
//		options.setDeviceName("iPhone");
		options.setApp("C:\\Users\\MSUSERSL123\\eclipse-workspace\\AppiumSession\\resources\\UIKitCatalog.app");
	    options.setPlatformVersion("15.5");
	    options.setWdaLaunchTimeout(Duration.ofSeconds(20));
	    
	    //To Run Test on Real IOS Device
	    
//	    options.setCapability("xcodeOrgId","YOUR_TEAM_ID");
//	    options.setCapability("xcodeSigningId","iPhone Developer");
//	    options.setCapability("udid","iPhone udid number");
//	    options.setCapability("updateWDABundleId","provisional file");
	    
	    driver = new IOSDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		homePage=new HomePage(driver);
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
		service.stop();
	}


}
