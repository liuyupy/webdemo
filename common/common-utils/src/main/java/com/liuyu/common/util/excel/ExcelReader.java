/*
 * Copyright (C), 2013-2013, 上海汽车集团股份有限公司
 * FileName: ExcelReader.java
 * Author:   pengyao
 * Date:     2014年4月2日 上午11:26:58
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.liuyu.common.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * @author pengyao
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ExcelReader {

    public String[][] readPlainExcel(File file) throws IOException{
        if(!file.exists()) {
            return null;
        }
        
        FileInputStream fis = new FileInputStream(file);  
        POIFSFileSystem POIStream = new POIFSFileSystem(fis);  
        HSSFWorkbook workBook = new HSSFWorkbook(POIStream); 
        
        HSSFSheet sheet1 = workBook.getSheetAt(0);
        fis.close();
        
        int rows = sheet1.getLastRowNum();  
        int columns = 0 ;
        if(rows > 0){
            HSSFRow row = sheet1.getRow(0); 
            columns = row.getLastCellNum();
        }
        
        if(columns > 0){
            String[][] result = new String[rows+1][columns];
            for(short i = 0; i <= rows; i++){
                HSSFRow row = sheet1.getRow(i);  
                if(row != null)  
                {  
                    int cells = row.getLastCellNum();  
                    for(short j = 0 ; j < cells; j++)  
                    {  
                        HSSFCell cell = row.getCell(j);  
                        String value = "";
                        switch(cell.getCellType())  
                        {  
                            case HSSFCell.CELL_TYPE_BLANK:  
                                break;  
                            case HSSFCell.CELL_TYPE_BOOLEAN:  
                                value += cell.getBooleanCellValue();  
                                break;  
                            case HSSFCell.CELL_TYPE_ERROR:  
                                value += cell.getErrorCellValue();  
                                break;  
                            case HSSFCell.CELL_TYPE_FORMULA:  
                                value += cell.getCellFormula();  
                                break;  
                            case HSSFCell.CELL_TYPE_NUMERIC:  
                                value += cell.getNumericCellValue();  
                                break;  
                            case HSSFCell.CELL_TYPE_STRING:  
                                value +=  cell.getRichStringCellValue();  
                                break;  
                            default:  
                                break; 
                        }
                        result[i][j] = value;
                    }
                }
            }
            
            return result;
        }
        
        return null;
    }
}
