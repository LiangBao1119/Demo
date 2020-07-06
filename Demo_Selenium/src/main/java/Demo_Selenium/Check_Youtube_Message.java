package Demo_Selenium;

import java.util.Base64;
import java.util.concurrent.TimeUnit;

import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Check_Youtube_Message {

	Base64.Decoder decoder = Base64.getDecoder();
	Base64.Encoder encoder = Base64.getEncoder();
	WebDriver webdriver = null;
	String Chrome_Path = "./EXE/chromedriver.exe";	
	String URL = "https://www.youtube.com/";
	long TESTSTARTTIME = 0;
	String TESTCLASSNAME = "";

	@BeforeTest
	public void beforeTest() throws InterruptedException, DocumentException {
		TESTSTARTTIME = System.currentTimeMillis();
		TESTCLASSNAME = this.getClass().getName();
		System.setProperty("webdriver.chrome.driver", Chrome_Path);
		webdriver = new ChromeDriver();
		webdriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		webdriver.manage().window().maximize();
		webdriver.get(URL);
	}
	
	@Test
	public void Studio_NPT() throws Exception {
		
		Into_Viedo();
		
	}
	
	public void Into_Viedo() {
		
		WebDriverWait wait = new WebDriverWait(webdriver, 30);
		
		String Hot_btn = "//*[@id='endpoint'][@title='發燒影片']";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Hot_btn))).click();
		
	}
	
	@AfterTest
	public void afterTest() throws Exception {
//		webdriver.close();
	}
}
