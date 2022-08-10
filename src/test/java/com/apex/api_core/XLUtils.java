
package com.apex.api_core;

import java.io.FileInputStream;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static Cell startCellData;
	public static Cell endCellData;
	
	public static int getRowCount(String xlFile, String xlSheet) throws IOException {
		fi=new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		int rowcount = ws.getLastRowNum();
		
		wb.close();
		fi.close();
		return rowcount;
	}
	
	public static int getCellCount(String xlFile, String xlSheet, int rownum) throws IOException {
		fi=new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(rownum);
		//row.getla
		int cellcount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}
	
	public static String getCellData(String xlFile, String xlSheet, int rownum, int colnum) throws IOException {
		fi=new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(rownum);
		cell= row.getCell(colnum);
		
		String data;
		try {
			DataFormatter  formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		}
		catch(Exception e){
			//e.printStackTrace();
			 data="";
		}
		wb.close();
		fi.close();
		return data;
	}
	
	public void setCellData(String xlFile, String xlSheet, int rownum, int colnum, String data) throws IOException {
		fi=new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(rownum);
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(xlFile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	
	
	
	public static void mainTemp(String[] args) throws IOException {
	    
		String xlsFile =System.getProperty("user.dir") + "\\src\\test\\java\\com\\apex\\api_Resources\\customerData.xlsx"; 
		String sheet ="customerDetails";
		String tableMarker ="GoogleIds";
		
		startEndTableMarkers(xlsFile, sheet,tableMarker);
		customerData(xlsFile, sheet);
		
	}

	/**
	 * @throws IOException
	 */
	public static void startEndTableMarkers(String xlsFile, String sheet, String tableMarker) throws IOException {
		
		int startRow=0;
		int startCol= 0;//getCellCount(xlsFile,sh, startRow);
		
		int endRow = getRowCount(xlsFile,sheet);
		int endCol =getCellCount(xlsFile,sheet, endRow); ;
		
		System.out.println("startRow =" + startRow + " startCol =" + startCol  +" endRow = " + endRow + " endCol = " + endCol);
		//System.out.println(xlsFile +"  "+sh +"  "+tableMarker +"  "+r+"  "+ c);
		startCellData = findCellData(xlsFile,sheet,tableMarker ,startRow, startCol, endRow);
		System.out.println("Start Celldata = " + startCellData  + " At row=" + startCellData.getRowIndex() + " At col = " + startCellData.getColumnIndex());
		
		if ((startRow+1)<endRow) {
			startRow=startRow+1;
		}
		
		endCellData = findCellData(xlsFile,sheet,tableMarker ,startRow, endCol-1, endRow+1);
		System.out.println("End Celldata = " + endCellData  );//
		//+ " At row=" + endCellData.getRowIndex() + " At col = " + endCellData.getColumnIndex());
	}
	
	
	
	
	
	public static Cell findCellData(String xlFile, String xlSheet,String tableMarker, int startrownum, int colnum , int endrownum) throws IOException {
		fi=new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(startrownum);
		//cell= row.getCell(colnum);
		String data;
		
		for(int r=startrownum;r<endrownum;r++) {
			row = ws.getRow(r);
			try {
			cell= row.getCell(colnum);
			}
			catch(Exception e){
				//e.printStackTrace();
				 data="";
				 cell =null;
			}
			
			try {
				DataFormatter  formatter = new DataFormatter();
				String cellData = formatter.formatCellValue(cell);
				if (cellData.equals(tableMarker)) {
					System.out.println("Table marker found. row ="  + cell.getRowIndex()  + " col = " + cell.getColumnIndex());
					return cell;
				}
				else {
					//System.out.println("Executing else  ="  + cellData);
				}
			}
			catch(Exception e){
				//e.printStackTrace();
				 data="";
				 cell =null;
			}
		}
		wb.close();
		fi.close();
		//return data;
		return null;
	}

	public static Object[][] customerData(String xlFile, String xlSheet) throws IOException {
		int rowStartAt= startCellData.getRowIndex() ;
		int colStartAt=startCellData.getColumnIndex();
		System.out.println("rowStartAt = " + rowStartAt + " colStartAt = " +colStartAt);
		
		int rowEndAt= endCellData.getRowIndex() ;
		int colEndAt=endCellData.getColumnIndex();
		System.out.println("rowEndAt = " + rowEndAt + " colEndAt = " + colEndAt);
		String custData[][]=new String [rowEndAt-rowStartAt-1][colEndAt-colStartAt-1];
		int i=0;
		int j=0;
		for (int r=rowStartAt+1 ; r<rowEndAt ;r++ ) {
			for(int c=colStartAt+1 ; c<colEndAt; c++) {				
				custData[i][j]=getCellData( xlFile, xlSheet, r, c);
				System.out.println("custData["+i+"]["+j +"] = " + custData[i][j]);
				j=j+1;
			}
			i=i+1;
			j=0;
		}
		
		
		
		i=custData.length;
		j=custData[0].length;
		System.out.println("Displaying thata data. Length ="  );
		for (int idx=0;idx<i;idx++) {
			for(int idx2=0;idx2<j;idx2++) {
				System.out.print("   " + custData[idx][idx2]);
			}
			System.out.println();
		}	
		return custData;
	}
	
}
