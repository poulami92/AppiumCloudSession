package IOSAppiumTests;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import BaseTests.IOSBaseTest;
import PageObjects.ios.AlertViewsPage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class IOSBasicTest_POM extends IOSBaseTest {
	
	
	
	
	@Test
	public void appiumIOSLocatorTest() 
	{
		AlertViewsPage alertViewsPage=homePage.clickOnAlerViews();
		alertViewsPage.clickOnTextEntry();
		alertViewsPage.fillTextBoxPopup("Hello World");
		alertViewsPage.clickOnConfirmCancel();
		String alertText=alertViewsPage.getTextOnConfirmCancelPopup();
	    System.out.println(alertText);
	    alertViewsPage.clickOnAcceptConfirmPopup();
		
	}

}
