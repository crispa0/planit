package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

/**
 * @author Paningasan, Crisaldo
 * @since 6/12/2021
 * Page Object class for Contact Page, this will include all the elements that are located on the Contact page and the actions
 * that can be done on each elements
 *
 */
public class ContactPage {

	private WebDriver driver;
	
	/*
	 * Declaration of different elements on the Contact page
	 */
	@FindBy(how = How.XPATH, using = "//*[@id=\"header-message\"]/div")
	private WebElement headerMessage;
	@FindBy(how = How.XPATH, using = "//*[@class=\"btn-contact btn btn-primary\"]")
	private WebElement submitButton;
	@FindBy(how = How.XPATH, using = "//*[@id=\"forename-err\"]")
	private WebElement foreNameErrMssg;
	@FindBy(how = How.XPATH, using = "//*[@id=\"email-err\"]")
	private WebElement emailErrMssg;
	@FindBy(how = How.XPATH, using = "//*[@id=\"message-err\"]")
	private WebElement mssgErr;
	@FindBy(how = How.XPATH, using = "//*[@id=\"forename\"]")
	private WebElement foreName;
	@FindBy(how = How.XPATH, using = "//*[@id=\"email\"]")
	private WebElement email;
	@FindBy(how = How.XPATH, using = "//*[@id=\"message\"]")
	private WebElement mssg;
	@FindBy(how = How.XPATH, using = "//*[@class=\"alert alert-success\"]")
	private WebElement successAlert;
	@FindBy(how = How.XPATH, using = "//*[@ng-click=\"goBack()\"]")
	private WebElement goBack;
	
	public ContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * Methods that can be called on your tests for the Contact page
	 */
	
	/**
	 * Method to validate that Contact page is successfully displayed after clicking the Contact tab
	 */
	public void isContactPageDisplayed() {
		waitForElement(headerMessage);
		headerMessage.getText();
		Assert.assertEquals(headerMessage.getText(), "We welcome your feedback - tell it how it is.");
	}
	
	/**
	 * Method to click Submit button on the Contact page
	 */
	public void clickSubmitButton() {
		Actions actions = new Actions(driver);
		actions.pause(3000);
		actions.moveToElement(submitButton).click().perform();
	}
	
	/**
	 * Method to validate if error message is displayed for the Forename field if it is blank upon submission
	 */
	public void isForeNameErrorDisplayed(boolean flag) {
		Assert.assertEquals(isDisplayed(foreNameErrMssg), flag);
		if(flag==true) {
			Assert.assertEquals(foreNameErrMssg.getText(), "Forename is required");
		}
	}
	
	/**
	 * Method to validate if error message is displayed for the Email field if it is blank upon submission
	 */
	public void isEmailErrorDisplayed(boolean flag) {
		Assert.assertEquals(isDisplayed(emailErrMssg), flag);
		if(flag==true) {
			Assert.assertEquals(emailErrMssg.getText(), "Email is required");
		}
	}
	
	/**
	 * Method to validate if error message is displayed for the Message field if it is blank upon submission
	 */
	public void isMessageErrorDisplayed(boolean flag) {
		Assert.assertEquals(isDisplayed(mssgErr), flag);
		if(flag==true) {
			Assert.assertEquals(mssgErr.getText(), "Message is required");
		}
	}
	
	/**
	 * Method to validate if error message is displayed on the header if there are errors upon submission
	 */
	public void isHeaderMessageErrorDisplayed(boolean flag) {
		waitForElement(headerMessage);
		if(flag==true) {
			Assert.assertEquals(headerMessage.getText(), "We welcome your feedback - but we won't get it unless you complete the form correctly.");
		}else {
			Assert.assertEquals(headerMessage.getText(), "We welcome your feedback - tell it how it is.");			
		}
	}
	
	/**
	 * @param text - value that will be entered on the Forename field
	 * Method that is used to enter value on the Forename field
	 */
	public void populateForeName(String text) {
		waitForElement(foreName);
		foreName.sendKeys(text);
	}
	
	/**
	 * @param text - value that will be entered on the Email field
	 * Method that is used to enter value on the Email field
	 */
	public void populateEmail(String text) {
		waitForElement(email);
		email.sendKeys(text);
	}
	
	/**
	 * @param text - value that will be entered on the Message field
	 * Method that is used to enter value on the Message field
	 */
	public void populateMessage(String text) {
		waitForElement(mssg);
		mssg.sendKeys(text);
	}
	
	/**
	 * @param foreName - validate that the Forename entered is displayed on the success message
	 * Method to validate if success alert was displayed upon successful submission
	 */
	public void isSuccessMessageDisplayed(String foreName) {
		waitForElement(successAlert);
		Assert.assertEquals(successAlert.getText(), "Thanks " +foreName+ ", we appreciate your feedback.");
	}
	
	/**
	 * Method to click the go back button on the success alert 
	 */
	public void clickGoBackButton() {
		
		Actions actions = new Actions(driver);
		actions.pause(3000);
		actions.moveToElement(goBack).click().perform();
	}
	
	/**
	 * @param element - Element that is expected to be displayed on the screen before doing any action
	 * Method that is used to do a wait before doing any action on the element passed
	 */
	private void waitForElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	private boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
	
}
