package Demo_Action;

import static org.testng.Assert.fail;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class OtherAction {
	// 存儲xml元素信息的容器
	private HashMap<String, String> elemmap = new HashMap<String, String>();
	private WebDriver driver;

	public OtherAction(WebDriver driver) {
		this.driver = driver;
	}
	
	// 讀取XML
	public HashMap<String, String> ReadXml(String XMLfile) throws DocumentException {
		Document doc = null;

		SAXReader saxReader = new SAXReader();
		try {
			doc = saxReader.read(new File(XMLfile));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		// 獲取根節點
		Element root = doc.getRootElement();
		getElementList(root);
		System.out.println(XMLfile + "資料已讀取");
		return elemmap;
	}

	public void getElementList(Element element) {
		List<Element> elements = element.elements();
		if (elements.size() == 0) {
			// 沒有子元素
			String xpath = element.getPath();
			String value = element.getTextTrim();
			elemmap.put(xpath, value);
		} else {
			// 有子元素
			for (Iterator<Element> it = elements.iterator(); it.hasNext();) {
				Element elem = (Element) it.next();
				// 遞歸遍歷
				getElementList(elem);
			}
		}
	}

	// 確認是否有元素
	public boolean isContentAppeared(WebDriver driver, String xpath) {
		boolean status = false;
		try {
			driver.findElement(By.xpath(xpath));
			status = true;
		} catch (NoSuchElementException e) {
			status = false;
		}
		return status;
	}

	public void JSscrollbar(String Xpath) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0] .scrollIntoView()", driver.findElement(By.xpath(Xpath)));
	}

	public String StringURLEncode(String getString) {
		String encodeURL = "";
		System.out.print("編碼前字串：" + getString);
		try {
			encodeURL = URLEncoder.encode(getString, "UTF-8");
			System.out.println("\t編碼後字串：" + encodeURL);
		} catch (Exception e) {
			System.out.println("\t編碼錯誤：" + e);
		}
		return encodeURL;
	}

	public String SpaceProcess(String _GetText) {
		String GetText = _GetText;
		System.out.println("移除字串前後空白");
		GetText = GetText.trim();
		System.out.println("移除字串中間複數空白取代成一個空白");
		GetText = GetText.replaceAll("[\\s]{2,}", " ");
		return GetText;
	}

	public String Date_DateFormatSec(long _GetTimetamp) throws Exception {
		String DateFormatDay = "";
		long GetTimetamp = 0;
		GetTimetamp = _GetTimetamp;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormatDay = df.format(new Date((GetTimetamp)));
		return DateFormatDay;
	}

	public void PrintTestTime(Long _TestStartTime,String _TestClassName) throws Exception {
		long TestStartTime = 0, TestEndTime = 0, TestTotalTime = 0;
		String TestClassName = "", TestStartDate = "", TestEndDate = "";
		TestStartTime = _TestStartTime;
		TestClassName = _TestClassName;
		TestEndTime = System.currentTimeMillis();
		TestStartDate = Date_DateFormatSec(TestStartTime);
		TestEndDate = Date_DateFormatSec(TestEndTime);
		TestTotalTime = (TestEndTime - TestStartTime)/1000;
		System.out.println("Class: " + TestClassName + "\n測試開始時間 : " + TestStartDate + "\n測試結束時間 : "
				+ TestEndDate + " \n共花費" + TestTotalTime + "秒");
	}
}
