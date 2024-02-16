package org.timbuk2.globalresources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter esr = new ExtentSparkReporter(path);
		esr.config().setReportName(" Timbuk2CheckOutFlow Results");
		esr.config().setDocumentTitle("Timbuk2 ");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(esr);
		extent.setSystemInfo("Nagendra","Timbuk2CheckOutFlow");
		return extent;
	}
}
