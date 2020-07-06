package Demo_Selenium;

import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Demo_Action.OtherAction;

public class Check_Youtube_Message {

	Base64.Decoder decoder = Base64.getDecoder();
	Base64.Encoder encoder = Base64.getEncoder();
	WebDriver webdriver = null;
	String Chrome_Path = "./EXE/chromedriver.exe";	
	String URL = "https://www.youtube.com/watch?v=NDSVf2azIGs";
	OtherAction Other = null;
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
		Other = new OtherAction(webdriver);
	}
	
	@Test
	public void Studio_NPT() throws Exception {
		// 找到留言處
		Find_Msg();
		// 抓取留言
		Get_Msg();
	}
	
	public void Find_Msg() throws InterruptedException {
		
		String Find_Msg = "(//*[@id='content-text'])";

		while (true) {
			Boolean Is_ = Other.isContentAppeared(webdriver, Find_Msg);
			if (Is_ == false) {
				//找到下方元素
				Other.JSscrollbar("//*[@class='style-scope ytd-video-secondary-info-renderer'][not(@id)][@style]");
			} else {
				break;
			}
			Thread.sleep(500);
		}
		System.out.println("Find the first message.");
		
	}
	
	public void Get_Msg() {
		
		WebDriverWait wait = new WebDriverWait(webdriver, 30);
		List<WebElement> rows;
		String Get_Msg;
		String Find_Msg = "(//*[@id='content-text'])";

		rows = webdriver.findElements(By.xpath(Find_Msg));
		for (int i = 1; i <= rows.size(); i++) {
			Get_Msg = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					Find_Msg+"["+i+"]"))).getText();
			System.out.println(Get_Msg);
		}
		
	}
	
	@AfterTest
	public void afterTest() throws Exception {
		Other.PrintTestTime(TESTSTARTTIME, TESTCLASSNAME);
		webdriver.close();
	}
}
