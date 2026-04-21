package AndroidAppiumTests;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseTests.EcommBaseTest;
import PageObjects.android.ProductCatalogPage;


public class DataProviderTest extends EcommBaseTest {

	public DataProviderTest() throws IOException {
		super();
	}

	@Test(dataProvider="getData")
	public void appiumTest(String name,String gender,String country) 
	{
		
		formPage.setName(name);
		formPage.setGender(gender);
	    formPage.selectCountry(country);
	    ProductCatalogPage productCatalogPage=formPage.clickOnShop();
		
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object formData[][]= {
				{"Poulami Datta","female","Argentina"},
				{"Rahul Shetty","male","Cananda"}
		}; 
		
		return formData;
	}

}
