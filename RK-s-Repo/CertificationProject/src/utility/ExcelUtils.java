package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public static XSSFWorkbook wb;
	public static String ExcelPath = PropertiesReader.readProperties(Initialiser.configPropertyFile, "ExcelPath");

	public static void getExcel() {
		try {

			LogGenerator.info("ExcelPath is: " + ExcelPath);

			FileInputStream fis = new FileInputStream(ExcelPath);

			LogGenerator.info("============ Initialize workbook ============");
			wb = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			LogGenerator.error("Excel file not found.\n" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			LogGenerator.error("============== IO Exception.\n" + e.getMessage()+" ==============");
		}
	}

	public static int getRowCount(String sheetName) {
		// LogGenerator.info("Inside getRowCount.....to get row count of sheet: "
		// + sheetName);
		XSSFSheet sheet = wb.getSheet(sheetName);
		// row count
		int rowCount = sheet.getLastRowNum();
		
		return rowCount;
	}

	public static int getRowNumber(String sheetName, String cellData, String columnName) {
		int foundAtRow = 0;
		String flag = "NotFound";
		int rowCount = ExcelUtils.getRowCount(sheetName);
		for (int RowItt = 0; RowItt < rowCount; RowItt++) {

			String ActualCellData = ExcelUtils.getCellData(sheetName, RowItt, columnName);
			if (cellData.equalsIgnoreCase(ActualCellData)) {
				foundAtRow = RowItt;
				flag = "Found";
				break;
			}

			if (flag.equalsIgnoreCase("found")) {
				break;
			}
		}

		return (int) foundAtRow;
	}

	public static String getCellData(String sheetName, int rowNumber, int columnNumber) {
		String cellData = null;
		XSSFSheet sheet = wb.getSheet(sheetName);
		Row row = sheet.getRow(rowNumber);
		cellData = row.getCell(columnNumber).toString();
		return cellData;

	}

	public static String getCellData(String sheetName, int rowNumber, String columnName) {
		XSSFSheet sheet = wb.getSheet(sheetName);
		int Col = getColumnIndex(sheetName, columnName);
		if(sheet.getRow(rowNumber) == null)
			return null;
		String cellData = sheet.getRow(rowNumber).getCell(Col).toString();
		return cellData;
	}

	public static int getColumnCount(String sheetName, int rowNumber) {
		XSSFSheet sheet = wb.getSheet(sheetName);
		int colCount = sheet.getRow(rowNumber).getLastCellNum();
		return colCount;
	}

	public static int getColumnIndex(String sheetName, String ExpColumnName) {
		XSSFSheet sheet = wb.getSheet(sheetName);
		String ActColumnName, found = "false";
		int Colitt, ColumnFoundAt = 0;
		for (Colitt = 0; Colitt <= 255; Colitt++) {
			ActColumnName = sheet.getRow(0).getCell(Colitt).getStringCellValue();
			if (ActColumnName != "") {
				if (ExpColumnName.equalsIgnoreCase(ActColumnName)) {
					ColumnFoundAt = Colitt;
					Colitt = 256;
					found = "true";
					break;
				}
			}
		}
		if (found.equalsIgnoreCase("true")) {
			return ColumnFoundAt;
		} else {
			return -1;
		}
	}

	public static int getTestStepsCount(String sheetName, String TestCaseID, String columnName) {
		int stepCount = 0;
		int TestCaseStartRowNumber = ExcelUtils.getRowNumber(sheetName, TestCaseID, columnName);
		String coulumnContent;
		int totalRowCount= getRowCount(sheetName);
		for (int i = TestCaseStartRowNumber; i < totalRowCount; i++) {
			coulumnContent = ExcelUtils.getCellData(sheetName, i, PropertiesReader.readProperties(Initialiser.configPropertyFile, "TestCaseID"));
			if (!coulumnContent.contains("End")) {
				stepCount ++;
			} else
				break;
		}

		return stepCount + 1;
	}

/*	public static void setCellData(String Result, int RowNum, int ColNum, String sheetName) {
		try {

			XSSFSheet sheet = wb.getSheet(sheetName);
			Row row = sheet.getRow(RowNum);
			Cell cell = row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			if (cell == null) {
				cell = row.createCell(ColNum);
				cell.setCellValue(Result);
			} else {
				cell.setCellValue(Result);
			}
			FileOutputStream fileOut = new FileOutputStream(PropertiesReader.readProperties(Initialiser.configPropertyFile, ExcelPath));
			wb.write(fileOut);
			// fileOut.flush();
			fileOut.close();
			// wb = new XSSFWorkbook(new
			// FileInputStream(Constants.Path_TestData));
		} catch (Exception e) {
			LogGenerator.error("============== Class ExcelUtils | Method setCellData | ============== \nException desc : " + e.getMessage());
			e.printStackTrace();
		}
	}*/
	
}
