package AndroidAppiumTests;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class appiumBasics {
	
	
	
	
	@Test
	public void appiumTest() throws MalformedURLException, URISyntaxException
	{
		
//		AppiumDriverLocalService service = new AppiumServiceBuilder()
//		        .withIPAddress("127.0.0.1")
//		        .usingPort(4723)
//		        .build();
//		
//		service.start();
		
		DesiredCapabilities caps = new DesiredCapabilities();
        AppiumDriver driver = new AppiumDriver(
                (new URI("http://hub.browserstack.com/wd/hub").toURL()),
                caps
        );
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 8 Emulator");
		options.setApp("C:\\Users\\MSUSERSL123\\eclipse-workspace\\AppiumSession\\resources\\ApiDemos-debug.apk");
	
//		AndroidDriver driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		
		driver.quit();
//		service.stop();
		
	}

}
