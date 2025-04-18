package SauceDemo.SauceDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.google.common.collect.Table.Cell;

public class ReadExcelFile {
	
	
	@DataProvider
	public static Object[][] getLoginData(String filePath,String sheetName) throws IOException
	{
		String filepath = "./TestData/TestData.xlsx";
		Object[][] data = null;

        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getLastCellNum();

            data = new Object[rowCount - 1][colCount];

            for (int i = 1; i < rowCount; i++) {
                XSSFRow row = sheet.getRow(i);

                for (int j = 0; j < colCount; j++) {
                    XSSFCell cell = row.getCell(j);
                    data[i - 1][j] = (cell != null) ? cell.toString() : "";
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
