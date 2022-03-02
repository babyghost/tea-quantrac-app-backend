package vn.tea.app.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.core.io.ClassPathResource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApachePoiUtils {

	public static Workbook loadTemplateFile(String filePath) throws IOException {
		Workbook wb = new XSSFWorkbook(new ClassPathResource(filePath).getInputStream());
		return wb;
	}

	public static XWPFDocument loadTemplateFileWord(String filePath) throws IOException {
		XWPFDocument document = new XWPFDocument(new ClassPathResource(filePath).getInputStream());
		return document;
	}

	public static void replaceParagraphText(XWPFParagraph p, String seachText, String replaceText) {
		List<XWPFRun> runs = p.getRuns();
		if (runs != null) {
			for (XWPFRun r : runs) {
				String text = r.getText(0);
				if (text != null && text.contains(seachText)) {
					text = text.replace(seachText, replaceText);
					r.setText(text, 0);
				}
			}
		}
	}

	public static void replaceTableText(XWPFTable tbl, String seachText, String replaceText) {
		for (XWPFTableRow row : tbl.getRows()) {
			for (XWPFTableCell cell : row.getTableCells()) {
				for (XWPFParagraph p : cell.getParagraphs()) {
					for (XWPFRun r : p.getRuns()) {
						String text = r.getText(0);
						log.info("replaceTableText {} {}", r.getTextPosition(), text);
						if (text != null && text.contains(seachText)) {
							text = text.replace(seachText, replaceText);
							r.setText(text, 0);
						}
					}
				}
			}
		}
	}

	public static XSSFRow createBlankRow(XSSFSheet sheet, int rowId, int numberColumns, CellStyle style) {
		XSSFRow row = sheet.createRow(rowId);
		for (int i = 0; i < numberColumns; i++) {
			XSSFCell rowCell_i = row.createCell(i);
			rowCell_i.setCellStyle(style);
		}
		return row;
	}

	public static void deleteRows(XSSFSheet sheet, int fromRowId, int numberRowsDel) {
		int numberRows = sheet.getLastRowNum();
		sheet.shiftRows(fromRowId + numberRowsDel, numberRows, numberRowsDel - 2 * numberRowsDel);
	}

	public static void insertRows(XSSFSheet sheet, int fromRowId, int numberRowsInsert) {
		int numberRows = sheet.getLastRowNum();
		sheet.shiftRows(fromRowId, numberRows, numberRowsInsert);
	}

	public static List<String> getCellsXLSX(Row row, String endOfRow) {
		List<String> fields = new ArrayList<String>();
		Iterator<Cell> cellIterator = row.cellIterator();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			if (endOfRow.equals(cell.getStringCellValue())) {
				fields.add(cell.getStringCellValue());
				break;
			}
			fields.add(cell.getStringCellValue());
		}
		log.info("HEADER: {}", fields);
		return fields;
	}

	/**
	 * Transfer values from List to each Cell of Row
	 * 
	 * @param List<String> values
	 * @param Row
	 * @return Row
	 */
	public static Row createRow(List<String> values, Row row) {
		int index = 0;
		while (index < values.size()) {
			Cell fCell = row.createCell(index);
			fCell.setCellValue(values.get(index));
			index++;
		}
		return row;
	}

	/**
	 * 
	 * @param Workbook
	 * @param numberOfRows  based 0
	 * @param numberOfCells based 0
	 * @return Workbook
	 */
	public static <T extends Workbook> T createHeader(T workbook, int numberOfRows, int numberOfCells) {
		Sheet sheet = workbook.getSheetAt(0);
		for (int i = 0; i < numberOfRows; i++) {
			Row row = sheet.createRow(i);
			for (int j = 0; j < numberOfCells; j++) {
				Cell cell = row.createCell(j);
				log.info("CELL: {}", j);
				if (i == 1 && j == 3) {
					cell.setCellValue("Tên loại kết quả:");
				}

				if (i == 2 && j == 3) {
					cell.setCellValue("Cơ quan cấp:");
				}

				if (i == 3 && j == 3) {
					cell.setCellValue("Ghi chú:");
				}

			}
		}

		return workbook;
	}

	public static Row style(Row row, Workbook workbook) {
		for (int j = 0; j < row.getLastCellNum(); j++) {
			Cell cell = row.getCell(j);

			if (workbook instanceof HSSFWorkbook) {
				HSSFFont defaultFont = (HSSFFont) workbook.createFont();
				defaultFont.setFontName("Times New Roman");
				defaultFont.setFontHeightInPoints((short) 13);
			}

			if (workbook instanceof XSSFWorkbook) {
				XSSFFont defaultFont = (XSSFFont) workbook.createFont();
				defaultFont.setFontName("Times New Roman");
				defaultFont.setFontHeightInPoints((short) 13);
			}

			CellStyle style = workbook.createCellStyle();
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());

			cell.setCellStyle(style);

		}
		return row;
	}

	public static <T extends Workbook> T style(T workbook) {
		Sheet sheet = workbook.getSheetAt(0);
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				if (workbook instanceof HSSFWorkbook) {
					HSSFFont defaultFont = (HSSFFont) workbook.createFont();
					defaultFont.setFontName("Times New Roman");
					defaultFont.setFontHeightInPoints((short) 13);

					CellStyle style = workbook.createCellStyle();
					style.setBorderTop(BorderStyle.THIN);
					style.setBorderRight(BorderStyle.THIN);
					style.setBorderBottom(BorderStyle.THIN);
					style.setBorderLeft(BorderStyle.THIN);
					style.setBottomBorderColor(IndexedColors.BLACK.getIndex());

					cell.setCellStyle(style);
				}

				if (workbook instanceof XSSFWorkbook) {
					XSSFFont defaultFont = (XSSFFont) workbook.createFont();
					defaultFont.setFontName("Times New Roman");
					defaultFont.setFontHeightInPoints((short) 13);

					CellStyle style = workbook.createCellStyle();
					style.setBorderTop(BorderStyle.THIN);
					style.setBorderRight(BorderStyle.THIN);
					style.setBorderBottom(BorderStyle.THIN);
					style.setBorderLeft(BorderStyle.THIN);
					style.setBottomBorderColor(IndexedColors.BLACK.getIndex());

					cell.setCellStyle(style);
				}
			}
		}
		return workbook;
	}
	
	public static String getStringCellValue(Cell cell) {
	    try {
	        switch (cell.getCellType()) {
	            case FORMULA:
	                try {
	                    return NumberToTextConverter.toText(cell.getNumericCellValue());
	                } catch (NumberFormatException e) {
	                    return cell.getStringCellValue();
	                }
	            case NUMERIC:
	                return NumberToTextConverter.toText(cell.getNumericCellValue());
	            case STRING:
	                String cellValue = cell.getStringCellValue().trim();
	                String pattern = "\\^\\$?-?([1-9][0-9]{0,2}(,\\d{3})*(\\.\\d{0,2})?|[1-9]\\d*(\\.\\d{0,2})?|0(\\.\\d{0,2})?|(\\.\\d{1,2}))$|^-?\\$?([1-9]\\d{0,2}(,\\d{3})*(\\.\\d{0,2})?|[1-9]\\d*(\\.\\d{0,2})?|0(\\.\\d{0,2})?|(\\.\\d{1,2}))$|^\\(\\$?([1-9]\\d{0,2}(,\\d{3})*(\\.\\d{0,2})?|[1-9]\\d*(\\.\\d{0,2})?|0(\\.\\d{0,2})?|(\\.\\d{1,2}))\\)$";
	                if (((Pattern.compile(pattern)).matcher(cellValue)).find()) {
	                    return cellValue.replaceAll("[^\\d.]", "");
	                }
	                return cellValue.trim();
	            case BOOLEAN:
	                return String.valueOf(cell.getBooleanCellValue());
	            case ERROR:
	                return null;
	            default:
	                return cell.getStringCellValue();
	        }
	    } catch (Exception e) {
	        if (e.getLocalizedMessage() != null)
	            return "";
	    }
	    return "";
	}
	
	
}
