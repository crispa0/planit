package pages;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

/**
 * @author Paningasan, Crisaldo
 * @since 6/12/2021
 * Page Object class for Cart Page, this will include all the elements that are located on the Cart page and the actions
 * that can be done on each elements
 *
 */
public class CartPage {

	static WebDriver driver;
	
	public CartPage(WebDriver driver) {
		CartPage.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * Methods you can use for the Cart Page
	 */
	/**
	 * @param item - Name of the Item that you ordered as displayed on the Shop page
	 * @param count - Quantity of the Item that you ordered
	 */
	public void validateQuantity(String item, int itemCount) {
		BigDecimal quantityRetrieved = retrieveValue("Quantity", item);
		Assert.assertEquals(quantityRetrieved, new BigDecimal(itemCount));
	}
	
	
	/**
	 * @param item - Name of the Item that you ordered as displayed on the Shop page
	 * @param count - Quantity of the Item that you ordered
	 * @param itemPrice - Price of the Item that you ordered
	 */
	public BigDecimal validateSubTotal(String item, int itemCount, double itemPrice) {
		BigDecimal quantityRetrieved = retrieveValue("Quantity", item);
		Assert.assertEquals(quantityRetrieved, new BigDecimal(itemCount));

		BigDecimal priceRetrieved = retrieveValue("Price", item);
		Assert.assertEquals(priceRetrieved, new BigDecimal(itemPrice).setScale(2, RoundingMode.FLOOR));

		BigDecimal subTotalRetrieved = retrieveValue("Subtotal", item);
		
		BigDecimal computedSubTotal = new BigDecimal(itemCount).multiply(new BigDecimal(itemPrice));
		computedSubTotal = computedSubTotal.setScale(2, RoundingMode.FLOOR);
		Assert.assertEquals(computedSubTotal, subTotalRetrieved);

		return subTotalRetrieved;
	}
	
	/*
	 * Retrieve the specific value of a column and row specified
	 */
	private static BigDecimal retrieveValue(String colName, String item) {
		int neededCol = 0;
		int neededRow = 0;
		BigDecimal noQuantity;
		String itemQuantity;
		
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/form/table")));
		WebElement tbl = driver.findElement(By.xpath("/html/body/div[2]/div/form/table"));
		List<WebElement> rows = tbl.findElements(By.tagName("tr"));
	
		for(int i=0; i<rows.size(); i++) {
		    List<WebElement> specificCol = rows.get(i).findElements(By.tagName("th"));
			
		    for(int j=0; j<specificCol.size(); j++) {
		        if(specificCol.get(j).getText().equalsIgnoreCase(colName)){
		        	neededCol = j+1;
		        	j=specificCol.size();
		        	break;
		        };
		    }
		}
		
		for(int i=0; i<rows.size(); i++) {
		    List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
	
		    for(int j=0; j<cols.size(); j++) {
		        if(cols.get(j).getText().equalsIgnoreCase(item)){
		        	neededRow = i;
		        	j=cols.size();
		        	break;
		        };		        
		    }
		}
		
		if(colName.equalsIgnoreCase("Quantity")) {
			itemQuantity = driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr["+ neededRow +"]/td[" + neededCol + "]/input")).getAttribute("value");
		}else {
			itemQuantity = driver.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr["+ neededRow +"]/td[" + neededCol + "]")).getText();
			itemQuantity = itemQuantity.replaceAll("[$,]", "");
		}
		noQuantity = new BigDecimal(itemQuantity);
		
		return noQuantity;
	}
}
