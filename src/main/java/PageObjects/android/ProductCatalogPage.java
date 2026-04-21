package PageObjects.android;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import EcommBasePage.AndriodBasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCatalogPage extends AndriodBasePage {
	
	AndroidDriver driver;
	
	public ProductCatalogPage(AndroidDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	private String before_xpath="//android.widget.TextView[@text='";
	private String after_xpath="']//following-sibling::android.widget.LinearLayout//android.widget.TextView[@text='ADD TO CART']";
	
	private By cartText = By.xpath("//android.widget.TextView[@text='Cart']");
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartBtn;
	
	public void addproductToCart(String productName)
	{
		scrollToText(productName);
		findElement(By.xpath(before_xpath+productName+after_xpath)).click();
	}
	
	public CartPage clickOnCart()
	{
		cartBtn.click();
		waitForElementToBeVisible(cartText);
		return new CartPage(driver);
	}


}
