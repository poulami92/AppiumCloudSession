package AndroidAppiumTests;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import BaseTests.EcommBaseTest;
import PageObjects.android.ProductCatalogPage;


public class DataProviderTest_json extends EcommBaseTest {

	public DataProviderTest_json() throws IOException {
		super();
		
	}

	@Test(dataProvider="getData")
	public void appiumTest(Map<String,String> data) 
	{
		
		formPage.setName(data.get("name"));
		formPage.setGender(data.get("gender"));
	    formPage.selectCountry(data.get("country"));
	    ProductCatalogPage productCatalogPage=formPage.clickOnShop();
		
	}
	
	@DataProvider
	public Iterator<HashMap<String, String>> getData() throws IOException
	{
		Path path = Path.of(System.getProperty("user.dir")+"\\resources\\data.json");
		String jsonData = Files.readString(path);
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String,String>> data = mapper.readValue(jsonData, new TypeReference<List<HashMap<String,String>>>(){
			
		});
		
		
		return data.iterator();
	}

}
