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
		String Get_Cus_id;
		String Get_Cus_Name;
		String Get_id;
		String Get_Time;
		String Find_Msg = "(//*[@id='content-text'])";
		String Find_Name = "(//*[@id='author-text']/span)";
//		String FInd_Name_Owner = "(//*[@id='author-text']/span)[4][contains(@class,'channel-owner')]";
		String Find_Cus_Name = "(//*[@id='top-row']//*[@id='text']/a)";
		String Find_id = "(//*[@id='author-text'])";
		String Find_Time = "(//*[@id='main']/div/div[2]/yt-formatted-string/a)";
		
		int a = 0 ;
		String [][] Array = new String [a][];

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

		Array = new String [rows.size()][7];

		for (int i = 1; i <= rows.size(); i++) {
			Get_Msg = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					Find_Msg+"["+i+"]"))).getText();
			Get_Msg = Get_Msg.replace(",", " ");
			System.out.println(Get_Msg);
	
			Get_Cus_Name = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					Find_Cus_Name))).getText();
			Get_Cus_Name = Get_Cus_Name.replace(",", " ");
			System.out.println(Get_Cus_Name);
			 
			Get_Cus_id = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					Find_Cus_Name))).getAttribute("href");
			Get_Cus_id = Get_Cus_id.replace(",", " ");
			Get_Cus_id = Get_Cus_id.substring(32);
			System.out.println(Get_Cus_id);
			
			Get_Name = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					Find_Name+"["+i+"]"))).getText();
			String Find_Owner = "(//*[@id='author-text']/span)["+i+"][contains(@class,'channel-owner')]";
			Boolean Is_Owner = Other.isContentAppeared(webdriver, Find_Owner);
			if (Is_Owner) {
				Get_Name = Get_Cus_Name;
			}
			Get_Name = Get_Name.replace(",", " ");
			System.out.println(Get_Name);

			Get_id = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					Find_id+"["+i+"]"))).getAttribute("href");
			Get_id = Get_id.replace(",", " ");
			Get_id = Get_id.substring(32);
			System.out.println(Get_id);
			
			Get_Time = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					Find_Time+"["+i+"]"))).getText();
			Get_Time = Get_Time.replace(",", " ");
			System.out.println(Get_Time);
			
			a = i-1;
			System.out.println("-------------");
			Array [a][0] = Get_id;
			Array [a][1] = Get_Name;
			Array [a][2] = Get_Cus_id;
			Array [a][3] = Get_Cus_Name;
			Array [a][4] = Get_Msg;
			Array [a][5] = Get_Time;
			Array [a][6] = URL;
		}
		
		boolean Is_Json = Message_Output_Json.createJsonFile(Array, "C:\\Users\\USER\\Documents\\GitHub\\Demo\\Demo_Selenium\\", "OutPut");
		System.out.println("JSON檔是否新增成功:"+Is_Json);
		//		Message_Output_Txt.Output_Txt(Array_Msg);
	}
	
	
	
	@AfterTest
	public void afterTest() throws Exception {
		Other.PrintTestTime(TESTSTARTTIME, TESTCLASSNAME);
		webdriver.close();
	}
}
