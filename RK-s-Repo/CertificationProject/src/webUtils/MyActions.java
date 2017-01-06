package webUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utility.Initialiser;
import utility.LogGenerator;
import utility.Screenshot;

public class MyActions {

	public static void openBrowser(String browser) {
		try {
			if (browser.equalsIgnoreCase("Chrome")) {
				Initialiser.chromeIinitialiser();
			} else if (browser.equalsIgnoreCase("Firefox")) {
				Initialiser.firefoxIinitialiser();
			} else if (browser.equalsIgnoreCase("IE")) {
				Initialiser.iEIinitialiser();
			}

			Initialiser.driver.manage().window().maximize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void goToURL(String URL) {
		

		LogGenerator.info("============== implicit wait in goToURL method ==============");
		Initialiser.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Initialiser.driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		LogGenerator.info("============== Go to URL:  " + URL + " ==============");
		Initialiser.driver.get(URL);
		Initialiser.driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		Initialiser.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static String getCurrentURL() {
		String currentURL = Initialiser.driver.getCurrentUrl();
		LogGenerator.info("============== Current URL is:  " + currentURL + " ==============");
		return currentURL;
	}

	public static void click(By by) {
		Initialiser.driver.findElement(by).click();
	}

	public static String getTitle() {
		String currentTitle = Initialiser.driver.getTitle();
		LogGenerator.info("============== Current Title is:  " + currentTitle + " ==============");
		return currentTitle;
	}

	public static void selectDropDown(By by, String testData) {
		WebElement dropDown = Initialiser.driver.findElement(by);
		dropDown.click();
		Select sel = new Select(dropDown);
		String value = null;
		if (testData.contains("value")) {
			value = testData.split("=")[1].trim();
			sel.selectByValue(value);
		} else if (testData.contains("text")) {
			value = testData.split("=")[1].trim();
			sel.selectByVisibleText(value);
		}
		LogGenerator.info("============== Selected value from Drop Down is: " + value + " ==============");
	}

	public static void selectRadioButton(By by) {
		Initialiser.driver.findElement(by).click();
		LogGenerator.info("============== Radio button selected ==============");
	}

	public static void setValue(By by, String value) {
		Initialiser.driver.findElement(by).sendKeys(value);
		LogGenerator.info("============== Value Entered is: " + value + " ==============");
	}

	public static String getValue(By by) {
		String value = Initialiser.driver.findElement(by).getText();
		LogGenerator.info("============== Text present is: " + value + " ==============");
		return value;
	}

	public static boolean verifyValue(String expectedValue, String actualValue) {
		boolean flag;
		
		LogGenerator.info("============== Expected Value is: " + expectedValue + " ==============");
		LogGenerator.info("============== Actual Value is: " + actualValue + " ==============");
		
		if (actualValue.equals(expectedValue)) {
			flag = true;
			LogGenerator.info("============== Actual and Expected Values are matching ==============");
		} else {
			flag = false;
			LogGenerator.info("============== Actual and Expected Values are NOT matching ==============");
		}
		return flag;
	}

	public static void clearData(By by) {
		Initialiser.driver.findElement(by).clear();
	}

	public static void wait(int waitTime) {
		Initialiser.driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
	}

	public static void takeScreenshot(String fileName) {
		Screenshot.takeScreenShot(fileName);
	}
	
	public static String isPresent(By by)
	{
		boolean flag= Initialiser.driver.findElement(by).isDisplayed();
		if (flag== true){
			LogGenerator.info("============== Element is present ==============");
			return "PASS";
		}
		else {
			LogGenerator.info("============== Element does NOT present ==============");
			return "FAIL";
		}	
	}
	
	public static String isEnabled(By by)
	{
		boolean flag= Initialiser.driver.findElement(by).isEnabled();
		if (flag== true){
			LogGenerator.info("============== Element is enabled ==============");
			return "PASS";
		}
		else {
			LogGenerator.info("============== Element is disabled ==============");
			return "FAIL";
		}
			
	}

	/*
	 * public static void selectDate
	 * 
	 * public static void switchWindow
	 * 
	 * public static void goBack
	 * 
	 * public static void goNext
	 * 
	 * public static void refreshScreen
	 */

	public static String getAndCheckAllLinks(String textToCheck)
	{
		String result="";
		List<WebElement> links=  Initialiser.driver.findElements(By.tagName("a"));
		LogGenerator.info("================== Total links on MercuryTours= " + links.size() +" ==================");
		String linksArray [] = new String[links.size()];
		int i=0;
		for(WebElement e: links)
		{
			linksArray[i]= e.getText();
			LogGenerator.info("================== Link Text ["+i+"]= "+ linksArray[i] +" ==================");
			i++;
		}
		
		for (String j : linksArray) {
			WebElement ele = Initialiser.driver.findElement(By.linkText(j));
			ele.click();

			if (Initialiser.driver.getTitle().contains(textToCheck)) {
				LogGenerator.info("======================== '" + j + "'  : working ========================");
				result= result + "PASS";
			} else {
				LogGenerator.error("======================== '" + j + "'  : Not working ========================");
				result= result + "FAIL";
			}

			Initialiser.driver.navigate().back();
			MyActions.wait(10);

		}
		if (result.contains("FAIL"))
			return "FAIL";
		
		else return "PASS";
	}
	
	
	public static void closeBrowserInstance() {
		Initialiser.driver.close();
	}

	public static void closeBrowser() {
		Initialiser.driver.quit();
	}

}
