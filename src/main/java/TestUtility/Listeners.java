package TestUtility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import TestUtility.TestUtil;
import io.appium.java_client.AppiumDriver;

public class Listeners extends TestUtil implements ITestListener{
	
	static ExtentReports extentReport;
	ExtentTest test;
	AppiumDriver driver;
	
	public void onStart(ITestContext context)
	{
		if(extentReport==null)
		{
			extentReport = getExtentReporter();
		}
		
	}

	public void onTestStart(ITestResult result)
	{	
		test= extentReport.createTest(result.getName());
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test.log(Status.PASS, "Test Passed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		try {
			driver=(AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String filePath="";
		test.fail(result.getThrowable());
		try {
			filePath = getSecreenShot(result.getName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath,result.getName());
	}
	
	public void onTestSkipped(ITestResult result)
	{
		System.out.println("Test Skipped "+result.getName());
	}
	
	public void onFinish(ITestContext context)
	{
		extentReport.flush();
	}




}
