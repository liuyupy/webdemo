/*
 * Copyright (C), 2013-2013, 上海汽车集团股份有限公司
 * FileName: TestExcelREader.java
 * Author:   pengyao
 * Date:     2014年4月2日 上午11:27:11
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.liuyu.common.util.excel;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

import junit.framework.TestCase;

/**
 * @author pengyao
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TestExcelREader extends TestCase{
    
    public void testReaderPlanExcel() throws IOException{
        File file = new File(TestExcelREader.class.getClassLoader().getResource("test3.xls").getFile());
        assertNotNull(file);
        assertTrue(file.exists());
        
        ExcelReader reader = new ExcelReader();
        String[][] plains = reader.readPlainExcel(file);
        assertNotNull(plains);
        StringBuilder sb1 = new StringBuilder(10000);
        StringBuilder sb2 = new StringBuilder(10000);
        StringBuilder sb3 = new StringBuilder(1000);
        TreeSet<String> set = new TreeSet<String>();
        for(int i= 0 ; i < plains.length ; i++){
            int length = plains[i].length;
            sb1.append("insert into t_leads_feedback")
              .append("(oem_code , dupl_falg , follow_step , create_time , is_feedback , update_time , drive_flag , ")
              .append(" leads_id , cust_name , cust_gender , cust_mobile_num , cert_no , vel_model, ")
              .append(" vel_color , vel_vin , deal_time , loan_flag , remark ")
              .append(") values(");
            sb1.append("'SGMW','N','12' , '2014-04-02 18:30:00','N', null , 'N' , ");
            for(int j = 0 ; j <length ;j++){
                String value = StringUtils.replaceChars(plains[i][j], "\n", "").trim();
                sb1.append("\'");
                if(j == 0){
                    sb2.append("update t_main_leads t set t.FOLWUP_STATUS = '12' where t.MAIN_LEADS_ID='")
                        .append(value).append("'; ");
                    sb3.append(value).append("',");
                    set.add(value);
                }
                sb1.append(value);
                sb1.append("\'");
                if((j+1) < length){
                    sb1.append(",");
                }
            }
            sb1.append(");\r\n");
            sb2.append("\r\n");
        }
        System.out.println(sb1.toString());
        System.out.println();
        System.out.println(sb2.toString());
        System.out.println();
        System.out.println(sb3.toString());
        System.out.println();
        
        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            
            System.out.print("'" + it.next() + "',");
        }
    }
}
