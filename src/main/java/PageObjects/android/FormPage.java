package PageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import EcommBasePage.AndriodBasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndriodBasePage {
	
	AndroidDriver driver;
	
	public FormPage(AndroidDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
	private WebElement femaleRadioBtn;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
	private WebElement maleRadioBtn;
	
	@AndroidFindBy(id="android:id/text1")
	private WebElement countryDropDown;
	
	@AndroidFindBy(xpath="//android.widget.Button[contains(@text,'Shop')]")
	private WebElement shopBtn;
	
	@AndroidFindBy(xpath="//android.widget.Toast")
	private WebElement errorMessage;
	
	public void setName(String name)
	{
		nameField.sendKeys(name);
	}
	
	public void setGender(String gender)
	{
		if(gender.contains("female"))
			femaleRadioBtn.click();
		else
			maleRadioBtn.click();
	}
	
	public void selectCountry(String country)
	{
		countryDropDown.click();
		scrollToText(country).click();
		
	}
	
	public ProductCatalogPage clickOnShop()
	{
		shopBtn.click();
		return new ProductCatalogPage(driver);
	}
	
	public String getErrorMessage()
	{
		return errorMessage.getAttribute("name");
	}
	
	

}
