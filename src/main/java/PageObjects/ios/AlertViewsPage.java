package PageObjects.ios;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import EcommBasePage.IOSBasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AlertViewsPage extends IOSBasePage{
	
	IOSDriver driver;
	
	public AlertViewsPage(IOSDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name == 'TextEntry'`]")
	private WebElement textEntry;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeCell")
	private WebElement textBox;
	
	@iOSXCUITFindBy(accessibility="OK")
	private WebElement AcceptTextBoxPopup;
	
	@iOSXCUITFindBy(iOSNsPredicate="type=='XCUIElementTypeStaticText' AND value='Confirm / Cancel'")
	private WebElement confirmCancelBtn;
	
	@iOSXCUITFindBy(iOSNsPredicate="name BEGINSWITH[c] 'A message'")
	private WebElement textMesageOnConfirmCancelPopup;
	
	@iOSXCUITFindBy(iOSNsPredicate="lebel=='Confirm'")
	private WebElement acceptConfirmPopup;
	
	public void clickOnTextEntry()
	{
		textEntry.click();
	}
	
	public void fillTextBoxPopup(String text)
	{
		textBox.sendKeys(text);
		AcceptTextBoxPopup.click();
	}
	
	public void clickOnConfirmCancel()
	{
		confirmCancelBtn.click();
	}
	
	public String getTextOnConfirmCancelPopup()
	{
		return textMesageOnConfirmCancelPopup.getText();
	}
	
	public void clickOnAcceptConfirmPopup()
	{
		acceptConfirmPopup.click();
	}
}
