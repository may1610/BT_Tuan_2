package Viettel.BT_Tuan_2;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import PageObject.*;

public class Bai_3 {
	private WebDriver driver;
	private LazadaHome lazHome;

	@Before
	public void initTest() {
		System.setProperty("webdriver.chrome.driver", "F:\\SOURCE_TEST\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		lazHome = new LazadaHome(driver);
		driver.get("https://www.lazada.vn/");
		driver.manage().window().maximize();
	}

	@Test
	public void Search() {
		lazHome.txtSearch.sendKeys("ppp");
		lazHome.btnSearch.click();
		assertTrue(true);
	}

}
