package io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import strategy.Choice;
import strategy.StrategyCombo;

public class ExcelReader {

	// "blackJackStrat.xlsx"
	public Map<StrategyCombo, Choice> strategyExcelReader(String file) {

		Map<StrategyCombo, Choice> map = new HashMap<StrategyCombo, Choice>();

		try {
			XSSFWorkbook wb = new XSSFWorkbook(file);
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			XSSFCell cell;

			int rows; // No of rows
			rows = sheet.getPhysicalNumberOfRows();

			int cols = 0; // No of columns
			int tmp = 0;

			// This trick ensures that we get the data properly even if it
			// doesn't start from first few rows
			for (int i = 0; i < 10 || i < rows; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					tmp = sheet.getRow(i).getPhysicalNumberOfCells();
					if (tmp > cols)
						cols = tmp;
				}
			}
			row = sheet.getRow(0);
			List<String> dealerHands = new ArrayList<>();
			for (int c = 1; c < cols; c++) {
				cell = row.getCell((short) c);
				if (cell != null) {
					String val = cell.toString();
					dealerHands.add(val);
				}
			}

			for (int r = 1; r < rows; r++) {
				row = sheet.getRow(r);
				if (row != null) {
					String playerHand = row.getCell(0).toString();
					for (int c = 1; c < cols; c++) {
						cell = row.getCell((short) c);
						if (cell != null) {
							String val = cell.getStringCellValue();
							StrategyCombo combo = new StrategyCombo(dealerHands.get(c - 1), playerHand);
							map.put(combo, Enum.valueOf(Choice.class, val));
						}
					}
				}
			}
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		return map;

	}
}
