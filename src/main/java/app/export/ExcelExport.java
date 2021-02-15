package app.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import app.menu.saisie.Ihm;

public class ExcelExport {
	public static void exec(List<String[]> data, String nom) {
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
				cell.setCellValue(dataCell.trim());
			}
		}
		String fileName = "C:/Temp/"+nom+" "+LocalDate.now()+".xlsx";
		File file = new File(fileName);
		try {
			file.getParentFile().mkdir();
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try (FileOutputStream outputStream = new FileOutputStream(file)) {
            workbook.write(outputStream);
            Ihm.afficherClient("Fichier créé à l'endroit suivant : " + file.getAbsolutePath().toString());
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
