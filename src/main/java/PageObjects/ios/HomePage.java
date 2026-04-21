package PageObjects.ios;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import EcommBasePage.IOSBasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomePage extends IOSBasePage{
	
	IOSDriver driver;
	
	public HomePage(IOSDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	@iOSXCUITFindBy(accessibility="Alert Views")
	private WebElement alertViews;
	
	public AlertViewsPage clickOnAlerViews()
	{
		alertViews.click();
		return new AlertViewsPage(driver);
	}

}
