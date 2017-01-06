package executionEngine;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import utility.Initialiser;
import utility.LogGenerator;
import utility.PropertiesReader;
import utility.ExcelUtils;
import utility.ReportGenerator;
import webUtils.WebUtils;

public class ExecutionDriver {

	public static String TestLabSheet = PropertiesReader.readProperties(Initialiser.configPropertyFile, "TestLabSheet");
	public static String WordPressTestSheet = PropertiesReader.readProperties(Initialiser.configPropertyFile, "WorldPressTestSheet");
	public static String MercuryToursTestSheet = PropertiesReader.readProperties(Initialiser.configPropertyFile, "MercuryToursTestSheet");
	public static String FacebookTestSheet = PropertiesReader.readProperties(Initialiser.configPropertyFile, "FacebookTestSheet");

	public static String ObjectRepositoryPath = PropertiesReader.readProperties(Initialiser.configPropertyFile, "ObjectRepositoryPath");

	public static String KEYWORD_FAIL = PropertiesReader.readProperties(Initialiser.configPropertyFile, "KEYWORD_FAIL");
	public static String KEYWORD_PASS = PropertiesReader.readProperties(Initialiser.configPropertyFile, "KEYWORD_PASS");

	// Test Lab Sheet Column Names
	public static String TestLab_TestOn = PropertiesReader.readProperties(Initialiser.configPropertyFile, "TestLab_TestOn");
	public static String TestLab_TestCaseID = PropertiesReader.readProperties(Initialiser.configPropertyFile, "TestLab_TestCaseID");
	public static String TestLab_Description = PropertiesReader.readProperties(Initialiser.configPropertyFile, "TestLab_Description");
	public static String TestLab_RunMode = PropertiesReader.readProperties(Initialiser.configPropertyFile, "TestLab_RunMode");
	public static String TestLab_Result = PropertiesReader.readProperties(Initialiser.configPropertyFile, "TestLab_Result");

	// Test Case Sheet Column Names
	public static String TestCaseID = PropertiesReader.readProperties(Initialiser.configPropertyFile, "TestCaseID");
	public static String TestStepID = PropertiesReader.readProperties(Initialiser.configPropertyFile, "TestStepID");
	public static String TestStepDescription = PropertiesReader.readProperties(Initialiser.configPropertyFile, "TestStepDescription");
	public static String ObjectName = PropertiesReader.readProperties(Initialiser.configPropertyFile, "ObjectName");
	public static String Keyword = PropertiesReader.readProperties(Initialiser.configPropertyFile, "Keyword");
	public static String TestData = PropertiesReader.readProperties(Initialiser.configPropertyFile, "TestData");
	public static String TestStepResult = PropertiesReader.readProperties(Initialiser.configPropertyFile, "TestStepResult");
	public static String TestStepComments = PropertiesReader.readProperties(Initialiser.configPropertyFile, "TestStepComments");

	@Test
	public void testLab() {
		try {
			ExcelUtils.getExcel();
			int rowCount = ExcelUtils.getRowCount(TestLabSheet);

			LogGenerator.info("============ Row count in TestLab is: " + rowCount + " ============");
			int counter = 0;
			String TLFilePath = ReportGenerator.testLabReportGenerator();
			String TCFilePath = ReportGenerator.testCaseReportGenerator();

			for (int i = 1; i <= rowCount; i++) {
				counter++;

				String testOn = ExcelUtils.getCellData(TestLabSheet, i, TestLab_TestOn);
				String testCaseID = ExcelUtils.getCellData(TestLabSheet, i, TestLab_TestCaseID);
				String runMode = ExcelUtils.getCellData(TestLabSheet, i, TestLab_RunMode);
				String testLab_description = ExcelUtils.getCellData(TestLabSheet, i, TestLab_Description);

				LogGenerator.info("Inside ...|| ExecutionDriver class|| ....in testLab method....\ntestOn: " + testOn + " ....... " + "test Case ID: " + testCaseID + " ....... "
						+ "runmode: " + runMode);

				if (counter == rowCount) {
					LogGenerator.info("======================== Test Case Execution Completed ========================");
				}

				if (runMode.equalsIgnoreCase("Yes")) {
					LogGenerator.startTestCase(testCaseID);

					String testCaseResult = "FAIL";
					switch (testOn) {
					case "MercuryTours":
						LogGenerator.info("-------- Inside Mercury Tours TestSheet --------");
						testCaseResult = ExecutionDriver.executeTestCase(MercuryToursTestSheet, testCaseID, TCFilePath);
						LogGenerator.endTestCase(testCaseID);
						break;

					case "WorldPress":
						LogGenerator.info("-------- Inside WorldPress TestSheet --------");
						testCaseResult = ExecutionDriver.executeTestCase(WordPressTestSheet, testCaseID, TCFilePath);
						LogGenerator.endTestCase(testCaseID);
						break;

					case "Facebook":
						LogGenerator.info("-------- Inside Facebook TestSheet --------");
						testCaseResult = ExecutionDriver.executeTestCase(FacebookTestSheet, testCaseID, TCFilePath);
						LogGenerator.endTestCase(testCaseID);
						break;
					}

					if (testCaseResult.equalsIgnoreCase("PASS")) {
						ReportGenerator.testLabReportWriter(TLFilePath, testOn, testCaseID, testLab_description, testCaseResult, "Test Case is PASSED");
					} else {
						ReportGenerator.testLabReportWriter(TLFilePath, testOn, testCaseID, testLab_description, testCaseResult, "Test Case is FAILED");
					}

				} else {
					String testCaseResult = "SKIPPED";
					ReportGenerator.testLabReportWriter(TLFilePath, testOn, testCaseID, testLab_description, testCaseResult, "Test Case is SKIPPED");
				}
			}

		} catch (Exception e) {
			LogGenerator.error("Inside ...|| ExecutionDriver class|| ....in testLab method....\nError message: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static String executeTestCase(String sheetName, String testCaseID, String reportFilePath) {

		String collectiveResult = "PASS";
		try {
			int stepCount = 0;
			int rowNumber = ExcelUtils.getRowNumber(sheetName, testCaseID, TestCaseID);

			if (rowNumber == 0) {
				LogGenerator.error("============== Test case ID: " + testCaseID + " not found in sheet: " + sheetName + " ==============");
			}

			else {
				stepCount = ExcelUtils.getTestStepsCount(sheetName, testCaseID, TestCaseID);
				int stepNumber = 0;

				LogGenerator.info("Inside ...|| ExecutionDriver class|| ....in executeTestCase method....\n step count is: " + stepCount + "...... row number is: " + rowNumber
						+ " in sheet: " + sheetName + " of test case ID : " + testCaseID);

				for (int i = 0; i < stepCount; i++) {
					stepNumber++;

					String actionKeyword = ExcelUtils.getCellData(sheetName, rowNumber, Keyword);
					if (actionKeyword.equals(null) || actionKeyword.equals(""))
						break;

					String objectName = ExcelUtils.getCellData(sheetName, rowNumber, ObjectName);
					String testData = ExcelUtils.getCellData(sheetName, rowNumber, TestData);

					LogGenerator.info("Inside ...|| ExecutionDriver class|| ....in executeTestCase method....\n Step number is= " + stepNumber + "....... action Keyword is: '"
							+ actionKeyword + "' ...... object name is: '" + objectName + "' ...... test data is: '" + testData + "'");

					By by;
					if (objectName.equals("")) {
						by = null;
					} else {
						by = WebUtils.elementLocator(sheetName, objectName);
					}

					String result = WebUtils.performAction(sheetName, testCaseID, objectName, actionKeyword, testData, by);

					String testStepID = ExcelUtils.getCellData(sheetName, rowNumber, TestStepID);
					String testStepDescription = ExcelUtils.getCellData(sheetName, rowNumber, TestStepDescription);

					if (result.equalsIgnoreCase("PASS")) {

						ReportGenerator.testCaseReportWriter(reportFilePath, testCaseID, testStepID, testStepDescription, result, "Test Step Passed");
					} else {
						ReportGenerator.testCaseReportWriter(reportFilePath, testCaseID, testStepID, testStepDescription, result, "Test Step Failed");
						collectiveResult = "FAIL";
					}
					rowNumber++;
				}

				String empty = "   -";
				ReportGenerator.testCaseReportWriter(reportFilePath, empty, empty, empty, empty, empty);
			}
			return collectiveResult;
		} catch (Exception e) {
			e.printStackTrace();
			LogGenerator.error("Inside ...|| ExecutionDriver class|| ....in executeTestCase method....\nError message: " + e.getMessage());
			return "FAIL";
		}

	}

}
