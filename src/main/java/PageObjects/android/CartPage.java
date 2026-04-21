package PageObjects.android;

import java.time.Duration;
import java.util.List;

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

public class CartPage extends AndriodBasePage {
	
	public CartPage(AndroidDriver driver)
	{
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productName")
	private List<WebElement> productList;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPrices;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement displayTotal;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	private WebElement termsElem;
	
	@AndroidFindBy(id="android:id/button1")
	private WebElement okBtnOnTerms;
	
	@AndroidFindBy(className="android.widget.CheckBox")
	private WebElement checkBox;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement completePurchaseBtn;
	
	
    public List<WebElement> getProductListOnCart()
    {
    	return productList;
    }
    
    public boolean matchCartItem(List<WebElement> pdtNameListOnCart,String productName)
    {
    	 boolean match=pdtNameListOnCart.stream().map(pdt->pdt.getText()).anyMatch(pdt->pdt.equals(productName));
    	 return match;
    }
    
    public List<WebElement> getProductPricesOnCart()
    {
    	return productPrices;
    }
    
    public double getTotalProcuctPriceOnCart(List<WebElement> productPrices)
    {
    	double sum=0;
    	for(WebElement pdtPrice:productPrices)
    	{
    		double price=Double.parseDouble(pdtPrice.getText().substring(1));
    		sum=price+sum;
    	}
    	return sum;
    }	
    
    public double getDisplayedTotalPriceOnCart()
    {
    	String displayedPrice= displayTotal.getText();
    	return getFomattedAmount(displayedPrice);
    }
    
    public void longPressOnTearmButton()
    {
    	longPressAction(termsElem);
    }
    
    public void acceptTermsCondition()
    {
    	okBtnOnTerms.click();
    }
    
    public void clickOnCheckBox()
    {
    	checkBox.click();
    }
    
    public void completePurchase()
    {
    	completePurchaseBtn.click();
    }

}
