package app.export;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelExport {
	public static void exec(List<String[]> data) {
		Workbook workbook = null;
		workbook = new HSSFWorkbook();
		HSSFSheet sheet = (HSSFSheet) workbook.createSheet("Data");
		int rowCount = 0;
		int columnCount = 0;
		for (String[] dataRow : data) {
			Row row = sheet.createRow(rowCount++);
			columnCount =0;
			for (String dataCell : dataRow) {
				Cell cell = row.createCell(columnCount++);
				cell.setCellValue(dataCell);
			}
		}
		try (FileOutputStream outputStream = new FileOutputStream("test.xlsx")) {
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
