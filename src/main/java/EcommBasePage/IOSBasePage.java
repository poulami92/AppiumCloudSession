package EcommBasePage;
import io.appium.java_client.ios.IOSDriver;

public class IOSBasePage extends AppiumBasePage {

    IOSDriver driver;
	
	public IOSBasePage(IOSDriver driver)
	{
		super(driver);
		this.driver=driver;
	}
}
