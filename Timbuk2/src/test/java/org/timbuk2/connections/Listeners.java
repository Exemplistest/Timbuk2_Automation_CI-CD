package org.timbuk2.connections;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.timbuk2.globalresources.ExtentReportsNG;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends ConnectionsClass implements ITestListener {

	ExtentReports extent = ExtentReportsNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		// WebDriver driver = driverThread.get();

		// Capture screenshot and add it to Extent Report
		try {
			// driver = (WebDriver)
			// result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			String screenshotPath = captureScreenshot(driver, result.getMethod().getMethodName());
			extentTest.get().addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {

		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {

		extent.flush();
		ITestListener.super.onFinish(context);
	}

}
