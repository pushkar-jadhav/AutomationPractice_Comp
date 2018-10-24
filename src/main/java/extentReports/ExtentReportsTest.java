package extentReports;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportsTest {

	@Test
	public void test123()
	{
		ExtentHtmlReporter report = new ExtentHtmlReporter("extent.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(report);
		ExtentTest test = extent.createTest(extent.getClass().getName());
	}
}
 