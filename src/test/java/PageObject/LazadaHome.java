package PageObject;

import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
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
	
	@FindBy(xpath="//div[@class=\"lzd-nav-search\"]//form//div[contains(@class, \"suggest-list\")]//a[contains(@class, \"suggest-common\")]//b")
	public List<WebElement> suggestLst;

    @FindBy(xpath="//div[@data-qa-locator=\"general-products\" and @data-spm=\"list\"]//div[@class=\"c16H9d\"]//a")
    public List<WebElement> searchtLst;
    
    @FindBy(xpath="//div[@id=\"root\"]//li[contains(@class,\"ant-pagination-next\")]")
    public WebElement nextPage;
    
    @FindBy(xpath="//div[@id=\"root\"]//span[contains(text(),\"items found for\")]")
    public WebElement noResultLbl;
}
