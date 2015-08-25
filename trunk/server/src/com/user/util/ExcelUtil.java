package com.user.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import jxl.Sheet;
import jxl.Workbook;
public class ExcelUtil {
	
	public static void main(String[] args) {
		File file = new File("D://test.xls");
		try {
			parseXsl(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 解析excel
	 * @param file
	 */
	public static void parseXsl(File file) throws Exception{
		Workbook xlsWorkbook = null;
		InputStream is = null;
		try 
		{
			if (file != null)
			{
				is = new FileInputStream(file);
				xlsWorkbook = Workbook.getWorkbook(is);
				printXsl(xlsWorkbook);
			}
		} catch (Exception e) {
			throw new RuntimeException("文件导入失败" + e.getMessage());
		}
		finally 
		{
			try 
			{
				if(xlsWorkbook!=null)
				{
					xlsWorkbook.close();
				} 
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if(is!=null) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 打印excel,实际业务按需利用
	 * @param xlsWorkbook
	 */
	private static void printXsl(Workbook xlsWorkbook) {
		Sheet[] sheets = xlsWorkbook.getSheets();
		//N个sheet
		for(Sheet sheet : sheets) {
			//行数
			int rows = sheet.getRows();
			//每行
			for(int i = 0; i < rows; i++) {
				//每列
				for (int j = 0; j < sheet.getColumns(); j++) {
					System.out.println("第" + (i+1) + "行，第" + (j+1) + "列的数据是" + sheet.getCell(j, i).getContents());
				}
			}
		}
	}
}
