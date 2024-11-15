package extentReport;

import java.time.Duration;
import java.time.LocalDateTime;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ToAttachScreenshot {
	@Test
	public void assScreenshot() {
		String timeStamp = LocalDateTime.now().toString().replace(":","-");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.get("https://www.flipkart.com/");
		TakesScreenshot ts=(TakesScreenshot) driver;
		String photo = ts.getScreenshotAs(OutputType.BASE64);
		
		ExtentSparkReporter spark=new ExtentSparkReporter("./HTML_report/screenshotReport"+timeStamp+".html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("ExtentReport");
		ExtentReports extReport = new ExtentReports();
		extReport.attachReporter(spark);
		  ExtentTest test = extReport.createTest("add screenshot");
		  test.log(Status.PASS,"adding screenshot into report");
		  test.addScreenCaptureFromBase64String(photo);
		  extReport.flush();
				
	}

}