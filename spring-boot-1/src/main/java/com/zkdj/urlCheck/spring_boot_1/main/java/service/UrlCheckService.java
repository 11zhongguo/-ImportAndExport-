package com.zkdj.urlCheck.spring_boot_1.main.java.service;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class UrlCheckService {
	/**
     * 处理上传的文件
     *
     * @param in
     * @param fileName
     * @return
     * @throws Exception
     */
    public List getBankListByExcel(InputStream in, String fileName) throws Exception {
        List list = new ArrayList<>();
        //创建Excel工作薄
        Workbook work = this.getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                List<String> li = new ArrayList<>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    String stringValueFromCell = getStringValueFromCell(cell);
                    li.add(stringValueFromCell);
                }
                list.add(li);
            }
        }
        work.close();
        return list;
    }

    /**
     * 判断文件格式
     *
     * @param inStr
     * @param fileName
     * @return
     * @throws Exception
     */
    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inStr);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("请上传excel文件！");
        }
        return workbook;
    }
    
    public static String getStringValueFromCell(Cell cell) { 
    	SimpleDateFormat sFormat = new SimpleDateFormat("MM/dd/yyyy"); 
    	DecimalFormat decimalFormat = new DecimalFormat("#.#"); 
    	String cellValue = ""; 
    	if(cell == null) { 
    		return cellValue; 
    		} else if(cell.getCellType() == Cell.CELL_TYPE_STRING) { 
    			cellValue = cell.getStringCellValue(); 
    			} else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) { 
    				if(HSSFDateUtil.isCellDateFormatted(cell)) { 
    					double d = cell.getNumericCellValue(); 
    					Date date = HSSFDateUtil.getJavaDate(d); 
    					cellValue = sFormat.format(date); 
    					} else { 
    						cellValue = decimalFormat.format((cell.getNumericCellValue())); 
    						} 
    				} else if(cell.getCellType() == Cell.CELL_TYPE_BLANK) { 
    					cellValue = ""; 
    					} else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) { 
    						cellValue = String.valueOf(cell.getBooleanCellValue()); 
    						} else if(cell.getCellType() == Cell.CELL_TYPE_ERROR) { 
    							cellValue = ""; 
    							} else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA) { 
    								cellValue = cell.getCellFormula().toString(); 
    								} 
    	return cellValue; 
    	}
}
