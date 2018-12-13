package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LazadaHome {
	private WebDriver driver;

	public LazadaHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id=\"topActionHeader\"]//div[@class=\"lzd-nav-search\"]//input[@type=\"search\"]")
	public WebElement txtSearch;

	@FindBy(xpath = "//div[@id=\"topActionHeader\"]//div[@class=\"lzd-nav-search\"]//button")
	public WebElement btnSearch;

}
