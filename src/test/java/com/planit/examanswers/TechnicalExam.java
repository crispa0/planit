package com.planit.examanswers;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;
import pages.CartPage;
import pages.ContactPage;
import pages.HomePage;
import pages.ShopPage;

public class TechnicalExam {
	WebDriver driver;
	
	@Before
	public void beforeEveryTests() {
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath+"/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://jupiter.cloud.planittesting.com/#/");
		driver.manage().window().maximize();
	}
	
	@Test
	public void testCase1() {
		String foreName = "testName";
		
		HomePage home = new HomePage(driver);
		home.clickOnContactTab();

		/*
		 * Step 1. From the home page go to contact page 
		 */
		ContactPage contact = new ContactPage(driver);
		contact.isContactPageDisplayed();
		
		/*
		 * Step 2. Click submit button 
		 */
		contact.clickSubmitButton();
		
		/*
		 * Step 3. Validate errors 
		 */
		contact.isEmailErrorDisplayed(true);
		contact.isForeNameErrorDisplayed(true);
		contact.isHeaderMessageErrorDisplayed(true);
		contact.isMessageErrorDisplayed(true);
		
		/*
		 * Step 4. Populate mandatory fields 
		 */
		contact.populateEmail("test@jupitertoys.com");
		contact.populateForeName(foreName);
		contact.populateMessage("test Message");
		contact.clickSubmitButton();
		
		/*
		 * Step 5. Validate errors are gone 
		 */
		contact.isEmailErrorDisplayed(false);
		contact.isForeNameErrorDisplayed(false);
		contact.isHeaderMessageErrorDisplayed(false);
		contact.isMessageErrorDisplayed(false);	
	}
	
	@Test
	public void testCase2() {
		String foreName = "testName";
		int x = 1;
		
		for(x=1;x<=5;x++) {
			HomePage home = new HomePage(driver);
			
			/*
			 * Step 1. From the home page go to contact page 
			 */
			home.clickOnContactTab();
			
			/*
			 * Step 2. Populate mandatory fields 
			 */
			ContactPage contact = new ContactPage(driver);
			contact.populateEmail("test@jupitertoys.com");
			contact.populateForeName(foreName);
			contact.populateMessage("test Message");
			
			/*
			 * Step 3. Click submit button 
			 */
			contact.clickSubmitButton();
			
			/*
			 * Step 4. Validate successful submission message
			 */
			contact.isSuccessMessageDisplayed(foreName);
			contact.clickGoBackButton();
			
			System.out.println("Run "+x+" was successful");
		}
		
		System.out.println("5 runs successfully passed.");
	}
	
	@Test
	public void testCase3() {
		int cowCount = 2;
		int bunnyCount = 1;
		
		HomePage home = new HomePage(driver);
		
		/*
		 * Step 1. From the home page go to shop page 
		 */
		home.clickOnShopTab();
		
		ShopPage shop = new ShopPage(driver);
		
		/*
		 * Step 2. Click buy button 2 times on “Funny Cow” 
		 */
		shop.clickOnBuyCow(cowCount);
		
		/*
		 * Step 3. Click buy button 1 time on “Fluffy Bunny” 
		 */
		shop.clickOnBuyBunny(bunnyCount);
		
		/*
		 * Step 4. Click the cart menu
		 */
		home.clickOnCart();
		
		CartPage cart = new CartPage(driver);
		
		/*
		 * Step 5. Verify the items are in the cart 
		 */
		cart.validateQuantity("Funny Cow", cowCount);
		cart.validateQuantity("Fluffy Bunny", bunnyCount);
	}
	
	@Test
	public void testCase4() throws InterruptedException {
		int frogCount = 2;
		double frogPrice = 10.99;
		int bunnyCount = 5;
		double bunnyPrice = 9.99;
		int bearCount = 3;
		double bearPrice = 14.99;
		BigDecimal expectedTotal = new BigDecimal("116.90");
		
		HomePage home = new HomePage(driver);
		home.clickOnShopTab();
		
		ShopPage shop = new ShopPage(driver);
		
		/*
		 * Step 1. Buy 2 Stuffed Frog, 5 Fluffy Bunny, 3 Valentine Bear 
		 */
		shop.clickOnBuyFrog(frogCount);
		shop.clickOnBuyBunny(bunnyCount);
		shop.clickOnBuyBear(bearCount);
		
		/*
		 * Step 2. Go to the cart page 
		 */
		home.clickOnCart();
				
		CartPage cart = new CartPage(driver);
		
		/*
		 * Step 3. Verify the price for each product 
		 * 4. Verify that each product’s sub total = product price * quantity 
		 */
		BigDecimal subTotalFrog = cart.validateSubTotal("Stuffed Frog", frogCount, frogPrice);
		BigDecimal subTotalBunny = cart.validateSubTotal("Fluffy Bunny", bunnyCount, bunnyPrice);
		BigDecimal subTotalBear = cart.validateSubTotal("Valentine Bear", bearCount, bearPrice);
		
		BigDecimal computedTotal = (subTotalFrog.add(subTotalBunny).add(subTotalBear)).setScale(2, RoundingMode.FLOOR);
		
		/*
		 * Step 5. Verify that total = sum(sub totals)  
		 */
		Assert.assertEquals(computedTotal, expectedTotal);
	}
	
	@After
	public void afterEveryTests() {
		driver.quit();	
	}
}
