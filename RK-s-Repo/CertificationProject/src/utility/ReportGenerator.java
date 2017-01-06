package utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportGenerator {

	public static String testLabReportGenerator() {

		String content = "<html> <head> <style> table { border-collapse: collapse; }" + "table, td, th { border: 1px solid black; } th, td { text-align: left;"
				+ "padding: 8px; } th { background-color: #B5AAD7; color: black; }" + "tr:nth-child(even){background-color: #f2f2f2} </style> </head>" +

				"<title>Test Lab Report</title>" + "<body><h3><center>Test Lab Results</center></h3><center> <table> " + "<tr><th width='10%'>Test On</th>"
				+ "<th width='10%'>Test Case ID</th>" + "<th width='30%'>Test Case Description</th>" + "<th width='10%'>Result</th>" + "<th width='40%'>Remarks</th></tr>";

		String filePath = PropertiesReader.readProperties(Initialiser.configPropertyFile, "ReportPath");
		LogGenerator.info("Readig ReportPath from configPropertyFile..... || Path is: "+filePath);
		DateFormat df = new SimpleDateFormat("yyyy_dd_MM");
		Date d = new Date();

		try {
			File f = new File(filePath);
			f.mkdirs();
			filePath= filePath +"/TestLabReport" + "_" + df.format(d) + ".html";
			File report= new File(filePath);
			report.createNewFile();
			//f.createNewFile();
			FileWriter fw = new FileWriter(report);
			
			fw.write(content);
			fw.flush();
			fw.close();
			// return filePath;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;

	}

	public static void testLabReportWriter(String filePath, String testOn, String testCaseID, String testCaseDesc, String testCaseResult, String Remarks) {

		String result = "<tr><td>" + testOn + "</td><td>" + testCaseID + "</td><td>" + testCaseDesc + "</td><td>" + testCaseResult + "</td><td>" + Remarks + "</td></tr>";

		try {
			FileWriter fw = new FileWriter(filePath, true);
			fw.write(result);
			fw.flush();
			fw.close();
			// return filePath;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String testCaseReportGenerator() {

		String content = "<html> <head> <style> table { border-collapse: collapse; }" + "table, td, th { border: 1px solid black; } th, td { text-align: left;"
				+ "padding: 8px; } th { background-color: #B5AAD7; color: black; }" + "tr:nth-child(even){background-color: #f2f2f2} </style> </head>" +

				"<title>Test Case Report</title>" + "<body><h3><center>Test Results</center></h3><center> <table> " + "<tr><th width='10%'>Test Case ID</th>"
				+ "<th width='10%'>Test Step</th>" + "<th width='30%'>Step Description</th>" + "<th width='10%'>Result</th>" + "<th width='40%'>Remarks</th></tr>";

		String filePath = PropertiesReader.readProperties(Initialiser.configPropertyFile, "ReportPath");
		DateFormat df = new SimpleDateFormat("yyyy_dd_MM");
		Date d = new Date();
		
		try {
			File f = new File(filePath);
			f.mkdirs();
			filePath = filePath + "/TestCaseReport" + "_" + df.format(d) + ".html";
			File report= new File(filePath);
			report.createNewFile();
			
			//f.createNewFile();
			FileWriter fw = new FileWriter(report);
			fw.write(content);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filePath;

	}

	public static void testCaseReportWriter(String filePath, String testCaseID, String testStep, String stepDesc, String stepResult, String Remarks) {
		String result = "\n<tr><td>" + testCaseID + "</td><td>" + testStep + "</td><td>" + stepDesc + "</td><td>" + stepResult + "</td><td>" + Remarks + "</td></tr>";
LogGenerator.info("================= Report row:\n"+result);
		try {
			FileWriter fw = new FileWriter(filePath, true);
			fw.write(result);
			fw.flush();
			fw.close();
			// return filePath;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
