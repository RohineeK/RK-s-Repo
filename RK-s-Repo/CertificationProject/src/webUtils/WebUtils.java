package webUtils;

import org.openqa.selenium.By;

import applicationToTest.MercuryTours.BookFlight;
import applicationToTest.MercuryTours.FlightConfirmation;
import applicationToTest.MercuryTours.FlightFinder;
import applicationToTest.MercuryTours.SelectFlight;
import applicationToTest.MercuryTours.WelcomePage;
import executionEngine.ExecutionDriver;
import utility.LogGenerator;
import utility.PropertiesReader;

public class WebUtils {
	public static String actualValue = null;

	public static By elementLocator(String testOn, String objectName) {
		String objectRepoPah = ExecutionDriver.ObjectRepositoryPath;
		String property = testOn + "." + objectName;

		String locatorProperty = PropertiesReader.readProperties(objectRepoPah, property);
		String[] splitLProperty = locatorProperty.split(":=");

		String locateBy = splitLProperty[0];
		String locator = splitLProperty[1];

		switch (locateBy) {
		case "id":
			By byID = By.id(locator);
			return byID;

		case "name":
			By byName = By.name(locator);
			return byName;

		case "linkText":
			By byLinkText = By.linkText(locator);
			return byLinkText;

		case "partialLinkText":
			By byPartialLinkText = By.partialLinkText(locator);
			return byPartialLinkText;

		case "className":
			By byClassName = By.className(locator);
			return byClassName;

		case "cssSelector":
			By byCssSelector = By.cssSelector(locator);
			return byCssSelector;

		case "tagName":
			By byTagName = By.tagName(locator);
			return byTagName;

		case "xpath":
			By byXpath = By.xpath(locator);
			return byXpath;

		default:
			return null;

		}
	}

	public static String performAction(String sheetName, String testCaseID, String objectName, String actionKeyword, String testData, By by) {
		String result = "FAIL";
		
		LogGenerator.info("============ action keyword found [" + actionKeyword + "] ============");
		if (actionKeyword.contains("Pre-requisite")) {
			String[] splitActionKeyword = actionKeyword.split("_");
			if (splitActionKeyword[1].equalsIgnoreCase("login")) {
				if (sheetName.equalsIgnoreCase("MercuryTours")) {
					result = WelcomePage.login(testData);

					String fileName = sheetName + "/" + testCaseID;
					MyActions.takeScreenshot(fileName);
				} else if (sheetName.equalsIgnoreCase("WorldPress")) {

				} else if (sheetName.equalsIgnoreCase("Facebook")) {

				}
			}
		}

		else if (actionKeyword.contains("fill")) {
			String[] splitActionKeyword = actionKeyword.split("_");
			if (sheetName.equalsIgnoreCase("MercuryTours")) {
				if (splitActionKeyword[1].equalsIgnoreCase("FlightFinder")) {
					result = FlightFinder.findFlight();
					String fileName = sheetName + "/" + testCaseID;
					MyActions.takeScreenshot(fileName);
				}

				else if (splitActionKeyword[1].equalsIgnoreCase("SelectFlight")) {
					result = SelectFlight.selectFlight();
					String fileName = sheetName + "/" + testCaseID;
					MyActions.takeScreenshot(fileName);
				}

				else if (splitActionKeyword[1].equalsIgnoreCase("BookFlight")) {
					result = BookFlight.bookFlight();
					String fileName = sheetName + "/" + testCaseID;
					MyActions.takeScreenshot(fileName);
				}

				else if (splitActionKeyword[1].equalsIgnoreCase("FlightConfirmation")) {
					result = FlightConfirmation.flightConfirm();
					String fileName = sheetName + "/" + testCaseID;
					MyActions.takeScreenshot(fileName);
				}
			} else if (sheetName.equalsIgnoreCase("WorldPress")) {

			} else if (sheetName.equalsIgnoreCase("Facebook")) {

			}

		}

		else {
			switch (actionKeyword) {

			case "openBrowser":
				MyActions.openBrowser(testData);
				result = "PASS";
				break;

			case "goToURL":
				MyActions.goToURL(testData);
				result = "PASS";
				break;

			case "getCurrentURL":
				actualValue = MyActions.getCurrentURL();
				result = "PASS";
				break;

			case "click":
				String fileName2 = sheetName + "/" + testCaseID;
				MyActions.takeScreenshot(fileName2);

				MyActions.click(by);
				result = "PASS";
				break;

			case "getTitle":
				actualValue = MyActions.getTitle();
				result = "PASS";
				break;

			case "selectDropDown":
				MyActions.selectDropDown(by, testData);
				result = "PASS";
				break;
			case "selectDDValue":
				result = "PASS";
				break;
			case "selectRadioButton":
				MyActions.selectRadioButton(by);
				result = "PASS";
				break;
			case "selectCheckBox":
				MyActions.click(by);
				result = "PASS";
				break;

			case "setValue":
				MyActions.clearData(by);
				MyActions.setValue(by, testData);
				result = "PASS";
				break;

			case "getValue":
				actualValue = MyActions.getValue(by);
				result = "PASS";
				break;

			case "verifyValue":
				boolean b = MyActions.verifyValue(testData, actualValue);
				String fileName = sheetName + "/" + testCaseID;
				MyActions.takeScreenshot(fileName);
				if (b == true)
					result = "PASS";
				break;

			case "clearData":
				MyActions.clearData(by);
				result = "PASS";
				break;

			case "takeScreenshot":
				String fileName1 = sheetName + "/" + testCaseID;
				MyActions.takeScreenshot(fileName1);
				result = "PASS";
				break;

			case "wait":
				testData = testData.substring(0, 1);
				int waitTime = Integer.parseInt(testData);
				LogGenerator.info("============ wait time is: " + waitTime + " ============");
				MyActions.wait(waitTime);
				result = "PASS";
				break;

			case "isPresent":
				result = MyActions.isPresent(by);
				String fileName3 = sheetName + "/" + testCaseID;
				MyActions.takeScreenshot(fileName3);
				break;

			case "isEnabled":
				result = MyActions.isEnabled(by);
				String fileName4 = sheetName + "/" + testCaseID;
				MyActions.takeScreenshot(fileName4);
				break;

			case "getCheckAllLinks":
				result = MyActions.getAndCheckAllLinks(testData);
				break;
				
			case "switchWindow":
				result = "PASS";
				break;
			case "goBack":
				result = "PASS";
				break;
			case "goNext":
				result = "PASS";
				break;
			case "refreshScreen":
				result = "PASS";
				break;

			case "closeBrowserInstance":
				MyActions.closeBrowserInstance();
				result = "PASS";
				break;

			}
		}
		return result.toUpperCase();
	}

}
