/*
 * Copyright (C), 2013-2013, 上海汽车集团股份有限公司
 * FileName: MoneyValidator.java
 * Author:   pengyao
 * Date:     2013年11月7日 上午11:04:09
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.liuyu.webapp.validator;

import java.util.regex.Pattern;  

import javax.validation.ConstraintValidator;  
import javax.validation.ConstraintValidatorContext;  
   
/**
 * @author pengyao
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
   
public class MoneyValidator implements ConstraintValidator<Money, Double> {  
   
    private String moneyReg = "^\\d+(\\.\\d{1,2})?$";//表示金额的正则表达式  
    private Pattern moneyPattern = Pattern.compile(moneyReg);  
     
    public void initialize(Money money) {  

    }  
   
    public boolean isValid(Double value, ConstraintValidatorContext arg1) {
        if (value == null) {
            return true;
        }
        return moneyPattern.matcher(value.toString()).matches();
    }
   
}  
