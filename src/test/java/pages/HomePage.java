package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Paningasan, Crisaldo
 * @since 6/12/2021
 * Page Object class for Home Page, this will include all the elements that are located on the Home page and the actions
 * that can be done on each elements 
 * */
public class HomePage {
	
	private WebDriver driver;
	
	/*
	 * Declaration of different elements on the Contact page
	 */
	@FindBy(how = How.XPATH, using = "//*[@id=\"nav-contact\"]")
	private WebElement contactTab;
	@FindBy(how = How.XPATH, using = "//*[@id=\"nav-shop\"]")
	private WebElement shopTab;
	@FindBy(how = How.XPATH, using = "//*[@id=\"nav-cart\"]")
	private WebElement cart;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * Methods that can be called on your tests for the Home page
	 */
	
	/**
	 * Method action to be called to click on Contact Tab on the Home page
	 */
	public void clickOnContactTab() {
		Actions actions = new Actions(driver);
		actions.pause(3000);
		actions.moveToElement(contactTab).click().perform();
	}
	
	/**
	 * Method action to be called to click on Shop Tab on the Home page
	 */
	public void clickOnShopTab() {
		Actions actions = new Actions(driver);
		actions.pause(3000);
		actions.moveToElement(shopTab).click().perform();
	}
	
	
	/**
	 * Method action to be called to click on Cart on the Home page
	 */
	public void clickOnCart() {
		Actions actions = new Actions(driver);
		actions.pause(3000);
		actions.moveToElement(cart).click().perform();
	}

}
