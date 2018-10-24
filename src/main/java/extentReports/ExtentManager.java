package extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	
	public static ExtentReports createInstance() {
		 htmlReporter = new ExtentHtmlReporter("C://Users//ajinkya.bhobad//eclipse-workspace//AutomationPractise//extent.html");
		 extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		return extent;
	}
	
	public static ExtentReports getInstance() {
		if(extent==null) {
			createInstance();
		}
		return extent;	
	}
}
