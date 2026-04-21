package AndroidAppiumTests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import BaseTests.EcommBaseTest;
import PageObjects.android.CartPage;
import PageObjects.android.FormPage;
import PageObjects.android.ProductCatalogPage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class E2EEcommTests_POM extends EcommBaseTest{
	

	public E2EEcommTests_POM() throws IOException {
		super();
	}

	@Test(priority=2)
	public void PurchaseProduct() throws InterruptedException, MalformedURLException, URISyntaxException
	{
		
		// Fill Form 
		
		formPage.setName("Poulami Datta");
		formPage.setGender("female");
	    formPage.selectCountry("Argentina");
	    ProductCatalogPage productCatalogPage=formPage.clickOnShop();
		
		// Add Products To Cart
		
		String productList[]= {"Converse All Star","Jordan 6 Rings"};
		for(String productName:productList)
		{
			productCatalogPage.addproductToCart(productName);
		}
		
		
		//Click on Cart Icon and Verify Added Products Displayed on Cart Page	
		
		CartPage cartPage =productCatalogPage.clickOnCart();
		List<WebElement> pdtNameListOnCart=cartPage.getProductListOnCart();
		
		for(String productName:productList)
		{
			boolean match=cartPage.matchCartItem(pdtNameListOnCart, productName);
			Assert.assertTrue(match);
		}
		
		// Verify Total Price of Cart Items
		
		
		List<WebElement> productPrices = cartPage.getProductPricesOnCart();
		double expectedTotalPriceOnCart=cartPage.getTotalProcuctPriceOnCart(productPrices);
		double actualTotalPriceOnCart=cartPage.getDisplayedTotalPriceOnCart();
		Assert.assertEquals(expectedTotalPriceOnCart,actualTotalPriceOnCart);
		
		//Long Press on Terms button
		
		cartPage.longPressOnTearmButton();
		cartPage.acceptTermsCondition();
	
		// Click Check Box
		
		cartPage.clickOnCheckBox();
		
		// Proceed to web page
		
		cartPage.completePurchase();
		
		Thread.sleep(10000);


	}
	
	@Test(priority=1)
	public void validateToastMessage()
	{
		formPage.setGender("female");
		formPage.selectCountry("Argentina");	
		ProductCatalogPage productCatalogPage=formPage.clickOnShop();
		String actualToastMessage=formPage.getErrorMessage();
		Assert.assertEquals(actualToastMessage, "Please enter your name");
		
	}

	
	
}