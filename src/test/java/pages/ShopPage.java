package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Paningasan, Crisaldo
 * @since 6/12/2021
 * Page Object class for Shop Page, this will include all the elements that are located on the Shop page and the actions
 * that can be done on each elements
 *
 */
public class ShopPage {
	
	WebDriver driver;
	
	/*
	 * Declaration of elements that are visible on the Shop Page
	 */
	@FindBy(how = How.XPATH, using = "//*[@id=\"product-6\"]/div/p/a")
	private WebElement buyCow;
	@FindBy(how = How.XPATH, using = "//*[@id=\"product-4\"]/div/p/a")
	private WebElement buyBunny;
	@FindBy(how = How.XPATH, using = "//*[@id=\"product-2\"]/div/p/a")
	private WebElement buyFrog;
	@FindBy(how = How.XPATH, using = "//*[@id=\"product-7\"]/div/p/a")
	private WebElement buyBear;
	
	public ShopPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * Methods that can be used for the Shop Page
	 */
	
	/**
	 * @param noOfItems - 
	 */
	public void clickOnBuyCow(int noOfItems) {
		for(int i=0;i<noOfItems;i++) {
		    new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOf(buyCow));
			buyCow.click();
		}
		
	}
	
	public void clickOnBuyBunny(int noOfItems) {
		for(int i=0;i<noOfItems;i++) {
		    new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOf(buyBunny));
			buyBunny.click();
		}
	}
	
	public void clickOnBuyFrog(int noOfItems) {
		for(int i=0;i<noOfItems;i++) {
		    new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOf(buyCow));
			buyFrog.click();
		}
		
	}
	
	public void clickOnBuyBear(int noOfItems) {
		for(int i=0;i<noOfItems;i++) {
		    new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOf(buyBunny));
			buyBear.click();
		}
	}

}
