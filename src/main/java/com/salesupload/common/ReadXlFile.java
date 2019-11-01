package com.salesupload.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ReadXlFile {

	// convert multipart file into File
	public static File convertIntoFile(MultipartFile file) throws IOException {
		System.out.println(" -> " + file);
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	@SuppressWarnings({ "rawtypes", "static-access", "unchecked" })
	public static ArrayList readExcelData(File file, int startrowindex) throws Exception {

		ArrayList<ArrayList> vRow = null;

		try (FileInputStream fileInputStream = new FileInputStream(file)) {
			CellType type = null;
			Workbook wb = new XSSFWorkbook(fileInputStream);
			vRow = new ArrayList<ArrayList>();
			FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

			for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets(); sheetNum++) {

				Sheet sheet = wb.getSheetAt(sheetNum);

				for (Row r : sheet) {
					for (Cell c : r) {
						type = c.getCellType();
						if (c.getCellType() == type.FORMULA) {
							evaluator.evaluate(c);
						}

					}

				}

			}

			Sheet sheet = wb.getSheetAt(0);

			Row row = null;

			Cell cell = null;

			ArrayList vCell = null;
			// int totalColumns=getTotalHeaderColumn(fileInputStream);
			boolean checkend = true;
			for (int i = startrowindex - 1; i < sheet.getPhysicalNumberOfRows(); i++) {
//				int empty = 0;
				row = sheet.getRow(i);

				vCell = new ArrayList();
				if (checkend == false) {
					break;
				}

				vRow.add(vCell);

				if (null != row) {
					for (short j = 0; j < row.getPhysicalNumberOfCells(); j++) {
						cell = row.getCell(j);
									
						if (cell == null || cell.toString()==null) {
							vCell.add("");
						} else {
							type = cell.getCellType();
							if (cell.toString().equals("end ***")) {
								checkend = false;
								
								break;
							}
							switch (evaluator.evaluateInCell(cell).getCellType()) {

							case BOOLEAN:

								vCell.add(cell.getBooleanCellValue());

								break;

							case NUMERIC:

								/*if (DateUtil.isCellDateFormatted(cell))
									vCell.add(cell);*/
								if (DateUtil.isCellDateFormatted(cell))
									vCell.add(EfiveUtils.parseDate(cell.toString()));
								else
									//getNumericCellValue()
									vCell.add(cell.getNumericCellValue());

								break;

							case STRING:
								if (EfiveUtils.parseDate(cell.toString()) != null)
									vCell.add(EfiveUtils.parseDate(cell.toString()));
								else
									vCell.add(cell.getStringCellValue());
								break;

							case BLANK:
								vCell.add("");
								break;
							case ERROR:
								vCell.add(cell.getErrorCellValue());
								break;

							case FORMULA:
								break;
							
							 default :
								 
								
								 vCell.add(cell.getStringCellValue());	
							}
						}
					}
				}
			}
			if(null != wb)
			{
				wb.close();
			}	
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}/* finally {

			if (null != file)
				FileUtils.forceDelete(file);
		}*/
		return vRow;
	}

	/*
	 * @SuppressWarnings("static-access") public static int
	 * getTotalHeaderColumn(File file) throws Exception {
	 * 
	 * int columns = 0;
	 * 
	 * try (FileInputStream fileInputStream = new FileInputStream(file)){
	 * 
	 * CellType type = null;
	 * 
	 * Workbook wb = new XSSFWorkbook(fileInputStream);
	 * 
	 * FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
	 * 
	 * int count = 0; for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets();
	 * sheetNum++) {
	 * 
	 * Sheet sheet = wb.getSheetAt(sheetNum);
	 * 
	 * for (Row r : sheet) {
	 * 
	 * if (count == 0) { for (Cell c : r) {
	 * 
	 * type = c.getCellType(); if (c.getCellType() == type.FORMULA) {
	 * evaluator.evaluate(c); } columns++; } } else { break; } count++; } } if(null
	 * != wb) { wb.close(); } } catch (Exception e) {
	 * 
	 * e.printStackTrace();
	 * 
	 * throw new Exception(e);
	 * 
	 * } finally {
	 * 
	 * }
	 * 
	 * return columns;
	 * 
	 * }
	 */

	/*
	 * @SuppressWarnings({ "rawtypes", "static-access", "unchecked" }) public static
	 * ArrayList<?> getColumn(File file, int startrowindex, int cellnumber) throws
	 * Exception { ArrayList vcols = new ArrayList();
	 * 
	 * try(FileInputStream fileInputStream = new FileInputStream(file)) { CellType
	 * type = null; Workbook wb = new XSSFWorkbook(fileInputStream);
	 * 
	 * FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
	 * 
	 * // int count = 0; for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets();
	 * sheetNum++) {
	 * 
	 * Sheet sheet = wb.getSheetAt(sheetNum);
	 * 
	 * for (Row r : sheet) {
	 * 
	 * for (Cell c : r) {
	 * 
	 * type = c.getCellType(); if (c.getCellType() == type.FORMULA) {
	 * 
	 * evaluator.evaluate(c);
	 * 
	 * } } }
	 * 
	 * } Sheet sheet = wb.getSheetAt(0); Row row = null; Cell cell = null;
	 * 
	 * for (int i = startrowindex - 1; i < sheet.getLastRowNum(); i++) {
	 * 
	 * row = sheet.getRow(i);
	 * 
	 * if (row != null) { cell = row.getCell(cellnumber); if (cell != null) {
	 * vcols.add(cell); } } } if(null != wb) { wb.close(); } } catch (Exception e) {
	 * e.printStackTrace(); throw new Exception(e); } return vcols; }
	 */

}
