package org.automation.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtils {

    public static Workbook workbook;
    public static Sheet sheet;

    public static String sheet_Name =System.getProperty("user.dir")+"/src/main/java/org/automation/resources/TD.xlsx";

    public static Object[][] getTDFromExcel(String sheetName) throws IOException {

        FileInputStream file =null;

        try {
            file = new FileInputStream(sheet_Name);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        workbook = WorkbookFactory.create(file);
        sheet = workbook.getSheet(sheetName);

        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for (int i = 0; i<sheet.getLastRowNum(); i++){
            for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                data[i][j] = sheet.getRow(i+1).getCell(j).toString();
            }
        }

        return data;
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        return getTDFromExcel("Sheet1");
    }
}
