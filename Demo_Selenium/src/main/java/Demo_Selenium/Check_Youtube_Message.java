package Demo_Selenium;

import java.util.ArrayList;
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
//	String URL = "https://www.youtube.com/watch?v=NDSVf2azIGs";
	String URL = "https://youtu.be/8_MpbRWh1jY";
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
	
	public void Get_Msg() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(webdriver, 30);
		List<WebElement> rows;
		int Count = 1,Acc = 1;
		String Get_Msg;
		String Get_Name;
		String Get_Cus_Name;
		String Find_Msg = "(//*[@id='content-text'])";
		String Find_Name = "(//*[@id='name']//*[@id='text'])";
		String Find_Cus_Name = "(//*[@id='top-row']//*[@id='text']/a)";
//		ArrayList<String> Array_Msg = new ArrayList<String>();
		
		String [][] Array = new String [3][];

		while(true) {
			
			Boolean Is_Find = Other.isContentAppeared(webdriver, Find_Msg+"["+Count+"]");
			if (Is_Find) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Find_Msg+"["+Count+"]")));
				Other.JSscrollbar(Find_Msg+"["+Count+"]");
				Acc = 1;
			} else {
				Thread.sleep(500);
				Is_Find = Other.isContentAppeared(webdriver, Find_Msg+"["+Count+"]");
				if (Is_Find) {
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Find_Msg+"["+Count+"]")));
					Other.JSscrollbar(Find_Msg+"["+Count+"]");
					Acc = 1;
				} else {
					if (Acc == 5) {
						break;
					}
					Acc++;
					System.out.println(Acc);
				}
			}
			Count++;
		}
		
		
		rows = webdriver.findElements(By.xpath(Find_Msg));
		Array = new String [3][rows.size()];
		for (int i = 1; i <= rows.size(); i++) {
			Get_Msg = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					Find_Msg+"["+i+"]"))).getText();
			Get_Msg = Get_Msg.replace(",", " ");
			System.out.println(Get_Msg);
			
			Get_Name = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					Find_Name+"["+i+"]"))).getText();
			Get_Name = Get_Name.replace(",", " ");
			System.out.println(Get_Name);

			Get_Cus_Name = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					Find_Cus_Name))).getText();
			Get_Cus_Name = Get_Cus_Name.replace(",", " ");
			System.out.println(Get_Cus_Name);
			
//			Array_Msg.add(Get_Name);
//			Array_Msg.add(Get_Cus_Name);
//			Array_Msg.add(Get_Msg);
			
			int a = i-1;
			Array [0][a] = Get_Name;
			Array [1][a] = Get_Cus_Name;
			Array [2][a] = Get_Msg;
		}
		
		Message_Output_Json.createJsonFile(Array, "C:\\Users\\USER\\Documents\\GitHub\\Demo\\Demo_Selenium\\", "OutPut");
//		Message_Output_Txt.Output_Txt(Array_Msg);
	}
	
	@AfterTest
	public void afterTest() throws Exception {
		Other.PrintTestTime(TESTSTARTTIME, TESTCLASSNAME);
		webdriver.close();
	}
}
