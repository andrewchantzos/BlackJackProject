package io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import game.Stats;

public class WriteExcel {

	private String file = "";
	
	public WriteExcel(String path) {
		this.file = path;
	}
	
	public void writeExcelData(List<Stats> list) {
		 XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet sheet = workbook.createSheet("BlackJackResults");


	        Row row = sheet.createRow(0);
	        Cell cell = row.createCell(0);
	        cell.setCellValue("Dealer");
	        
	        for (int i = 0; i <list.get(0).getP().length; i++) {
	        	cell = row.createCell(i + 1);
	        	cell.setCellValue("P" + (i+1));
	        }
	        
	        int rowNum = 1;
	        System.out.println("Creating excel");

	        for (Stats stats: list) {
	        	row = sheet.createRow(rowNum++);
	        	int colNum = 0;
	        	cell = row.createCell(colNum++);
	        	cell.setCellValue(stats.getM());
	        	String[] arr = stats.getP();
	        	for (String st: arr) {
		        	Cell cell1 = row.createCell(colNum++);
		        	cell1.setCellValue(st);
	        	}
	        }

	        try {
	            FileOutputStream outputStream = new FileOutputStream(file);
	            workbook.write(outputStream);
	            workbook.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
}
