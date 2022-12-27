package Base;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

    public class commonMethods {
	public static WebDriver driver;
	//public String[][] strings;
	public static String[][] data;
	public static ExtentHtmlReporter htmlreporter;
    public static ExtentReports extent;
    public static  ExtentTest test;
    
   
    
	 public static  void create_extentreport() {
		   htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/outputFolder/cucumberresult2.html"); 
	        htmlreporter.config().setDocumentTitle("Cini Mitra");
	        htmlreporter.config().setReportName("Open the browser");
	        htmlreporter.config().setTheme(Theme.STANDARD);
	    
	        
	        extent = new ExtentReports();
	        extent.attachReporter(htmlreporter);
	        extent.setSystemInfo("Hostname", "localhost");
	        extent.setSystemInfo("OS", "Windows10");
	        extent.setSystemInfo("Tester Name ", "Dinesh V");
	        extent.setSystemInfo("Position", "Software Test Engineer");
	        extent.setSystemInfo("Browser", "Chrome");
	 }
	    public static void report_creation(String topic ,String description) {
	        test = extent.createTest(topic,description);
	    }
	 public static void closeExtent() {
	    	extent.flush();
	    }
	 
	public static void Open_browsers(String browsername) {

		switch (browsername) {

		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;

		case "IE":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			break;

		}
	}

	public static void geturl(String URL) {
		driver.get(URL);
	}

	public static String[][] exporttoExcel(int sheetnumber) throws Exception {
				File src = new File("C:\\Users\\dinesh.v\\Documents\\My Scans\\CMdatas.xlsx");

				FileInputStream fis = new FileInputStream(src);
				XSSFWorkbook xsf = new XSSFWorkbook(fis);
				XSSFSheet sheet = xsf.getSheetAt(sheetnumber);
				int rowcount = sheet.getLastRowNum();
				short columncount = sheet.getRow(0).getLastCellNum();
				data = new String[rowcount][columncount];
			
				for (int i = 1; i < rowcount + 1; i++) {
					// System.out.println(sheet.getPhysicalNumberOfRows());
					XSSFRow row = sheet.getRow(i);
					for (int j = 0; j < columncount; j++) {

						String cellvalue = " ";
						cellvalue = row.getCell(j).getStringCellValue();
						data[i - 1][j] = cellvalue;
						// System.out.println(data[0][0]);
					}
					System.out.println();

				}

				return data;
				
			}
	
	
	public static void TakeScreenshot(String imagename) throws Exception {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File src = ts.getScreenshotAs(OutputType.FILE);

		File des = new File("/cini_Mitra/ciniMitraScreenShots" + imagename + ".png");

		FileUtils.copyFile(src, des);

	}

	public static void implicitwait(int value) {
		driver.manage().timeouts().implicitlyWait(value, TimeUnit.SECONDS);
	}

	/*
	 * public static void explicitywait(int value) { WebDriverWait wait = new
	 * WebDriverWait(driver, value); }
	 */

	public static void title(String actualtitle) {
		String expectedtitle = driver.getTitle();
		if (expectedtitle.contains(actualtitle)) {
			System.out.println("This title is expected one");
		} else {
			System.out.println("This title is wrong ");
		}
	}

	// public static void javascriptexecutor() {
	// JavascriptExecutor js = (JavascriptExecutor) driver;
	// }

	public static void windowhandling(String windownumber) {
		driver.switchTo().window(windownumber);
	}

	public static void getcurrenturl(String actualurl) {
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.contains(actualurl)) {
			System.out.println("Current URL is expected one");
		} else {
			System.out.println("This is wrong URL");
		}
	}

	public static void title_get(String expectedtitle, String expectedname) {

		String actualtitle = driver.getTitle();
		if (actualtitle.contains(expectedtitle)) {
			System.out.println("This title is correct - " + expectedname);
		} else {
			System.out.println("This title is wrong - " + expectedname);
		}
	}

	public static void driverquit() {
		driver.quit();
	}

	public static void handlingalert() {
		driver.switchTo().alert();
	}

	public static void framesusingindex(int Index) {
		driver.switchTo().frame(Index);
	}

	public static void defcontent() {
		driver.switchTo().defaultContent();
	}

	public static void actionclasses_sendkeys(WebElement element, CharSequence sequence) {
		Actions a = new Actions(driver);
		a.sendKeys(element, sequence);
	}

	public static void robotclass() throws AWTException {
		Robot robot = new Robot();

	}

	public static void refershpage() {
		driver.navigate().refresh();

	}

	public static void type(WebElement ele, String value) {
		ele.sendKeys(value);
	}

	/*
	 * public static void scroolbyindex() { JavascriptExecutor js =
	 * (JavascriptExecutor) driver; js.executeScript("window.scrollBy(0,1500)", "");
	 * }
	 */

	public static void scroolbyindex(int value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + value + ")", " ");
	}

	public static void scrollbyvisibletext(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);

	}

	public static void simpleAlert() {
		driver.switchTo().alert().accept();
	}
	/*
	 * public static void sendkey(WebElement ele, Object object) {
	 * ele.sendKeys(object); }
	 */

	// js.executeScript("arguments[0].scrollIntoView();", element);
	public static void getPageSource(String value) {
		String pageSource = driver.getPageSource();
		if (pageSource.contains(value)) {
			System.out.println("The page navigation is correct");
		} else {
			System.out.println("The page navigation is wrong");
		}

	}

	public static void editbuttoninPP(int value) {
		driver.findElement(By.xpath("(//td[contains(text(),'" + value + "')]/following-sibling::td)[8]")).click();
	}

	public static void threadsleep(int milliseconds) throws InterruptedException {
		Thread.sleep(milliseconds);

	}

}
