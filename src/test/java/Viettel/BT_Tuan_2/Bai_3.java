package Viettel.BT_Tuan_2;

import static org.junit.Assert.assertTrue;

import java.security.Key;
import java.util.concurrent.TimeUnit;

import javax.swing.UIDefaults.LazyValue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObject.*;

public class Bai_3 {
	private WebDriver driver;
	private LazadaHome lazHome;

	@Before
	public void initTest() {
		System.setProperty("webdriver.chrome.driver", "F:\\SOURCE_TEST\\chromedriver.exe");
		driver = new ChromeDriver();
		lazHome = new LazadaHome(driver);
		driver.get("https://www.lazada.vn/");
		driver.manage().window().maximize();
	}

	// Bai 3.1
	@Ignore
	@Test
	public void CheckSuggestList() {
		lazHome.txtSearch.sendKeys("Pin");
		WebDriverWait wait = new WebDriverWait(driver, 10); // doi toi da 10s?
		wait.until(ExpectedConditions.visibilityOfAllElements(lazHome.suggestLst));
		System.out.println(lazHome.suggestLst);
		System.out.println(lazHome.suggestLst.size());
		assertTrue(lazHome.suggestLst.size() == 10);
		driver.quit();
	}

	// Bai 3.2
	@Ignore
	@Test
	public void CheckContainKeyword() {
		String keyWord = "pin";
		boolean isContainKeyword = true;
		lazHome.txtSearch.sendKeys(keyWord);
		WebDriverWait wait = new WebDriverWait(driver, 10); // doi toi da 10s?
		wait.until(ExpectedConditions.visibilityOfAllElements(lazHome.suggestLst));
		for (WebElement el : lazHome.suggestLst) {
			if (!el.getText().toLowerCase().contains(keyWord)) {
				isContainKeyword = false;
				break;
			}
		}

		assertTrue(isContainKeyword); // sao trong
		driver.quit();
	}

	// Bai 3.3
	@Ignore
	@Test
	public void CheckSearchResult() {
		String keyWord = "máy ép trái cây panasonic";
		boolean isContainKeyword = true;
		lazHome.txtSearch.sendKeys(keyWord);
		lazHome.btnSearch.click();
		WebDriverWait wait = new WebDriverWait(driver, 20); // doi toi da 10s?
		wait.until(ExpectedConditions.visibilityOfAllElements(lazHome.searchtLst));
		for (WebElement el : lazHome.searchtLst) {
			if (!el.getText().toLowerCase().contains(keyWord)) {
				isContainKeyword = false;
				break;
			}
		}

		assertTrue(isContainKeyword); // sao trong
		driver.quit();
	}

	// Bai 3.3
	@Test
	public void CheckSearchResult1() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions actions = new Actions(driver);
		int i = 1;
		String notFound = "0 items found for";
		boolean hasNextPage = true;
		String keyWord = "aaaa"; // 111111111 --> ko co ket qua nao  ; 5 hộp Pin 2A lớn Maxell nguyên hộp 40 viên. Made in Indonesia --> 1 ket qua
		boolean isContainKeyword = true;
		lazHome.txtSearch.sendKeys(keyWord);
		lazHome.btnSearch.click();
		if (lazHome.noResultLbl.getText().toLowerCase().contains(notFound)) {
			System.out.println("Không tìm thấy kết quả nào!");
			assertTrue(true);
		} else {

			WebDriverWait wait = new WebDriverWait(driver, 20); // doi toi da 10s?
			try {
				wait.until(ExpectedConditions.visibilityOfAllElements(lazHome.searchtLst));
			} catch (Exception ex) {

			}
			while (hasNextPage) {
				System.out.println("Page " + i);
				for (WebElement el : lazHome.searchtLst) {
					System.out.println(el.getAttribute("title").toLowerCase());
					if (!el.getAttribute("title").toLowerCase().contains(keyWord.toLowerCase())) {
						isContainKeyword = false;
						break;
					}
				}

				js.executeScript("arguments[0].scrollIntoView(false);", lazHome.nextPage);
				actions.sendKeys(Keys.DOWN);
				actions.perform();
				if (lazHome.nextPage.getAttribute("aria-disabled").equals("false")) {
					lazHome.nextPage.click();
					i++;
				} else
					hasNextPage = false;
			}

			assertTrue(isContainKeyword); // sao trong
		}
		driver.quit();
	}
}
