//package com.apex.api_core;
//
//import java.io.File;
////
////import jxl.Cell;
////import jxl.Sheet;
////import jxl.Workbook;
//
//import jxl.Cell;
//import jxl.Sheet;
//import jxl.Workbook;
//
//public class XLUtilities {
//	
//	public static void main(String[] args) {
//		XLUtilities xlUtil = new XLUtilities();
//		xlUtil.TestGetTableArray();
//	}
//	
//	public void TestGetTableArray() {
//		String xlsFile =System.getProperty("user.dir") + "\\src\\test\\java\\com\\apex\\api_Resources\\customerData.xlsx"; 
//		String sheet ="customerDetails";
//		String tableMarker ="GoogleIds";	
//	
//		String[][] tabData = getTableArray(xlsFile, sheet, tableMarker);
//	}
//	
//	public String[][] getTableArray(String xlFilePath, String sheetName, String tableName){
//        String[][] tabArray=null;
//        try{
//            Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
//            Sheet sheet = workbook.getSheet(sheetName);
//            int startRow,startCol, endRow, endCol,ci,cj;
//            Cell tableStart=sheet.findCell(tableName);
//            startRow=tableStart.getRow();
//            startCol=tableStart.getColumn();
//
//            Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100, 64000,  false);                               
//            
//            //sheet.findCell(tableName)
//            
//            endRow=tableEnd.getRow();
//            endCol=tableEnd.getColumn();
//            System.out.println("startRow="+startRow+", endRow="+endRow+", " +
//                    "startCol="+startCol+", endCol="+endCol);
//            tabArray=new String[endRow-startRow-1][endCol-startCol-1];
//            ci=0;
//
//            for (int i=startRow+1;i<endRow;i++,ci++){
//                cj=0;
//                for (int j=startCol+1;j<endCol;j++,cj++){
//                    tabArray[ci][cj]=sheet.getCell(j,i).getContents();
//                }
//            }
//        }
//        catch (Exception e)    {
//        	System.out.println(e.getMessage());
//            System.out.println("error in getTableArray()");
//        }
//
//        return(tabArray);
//    }
//
//}
