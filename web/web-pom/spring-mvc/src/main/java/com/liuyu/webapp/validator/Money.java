/*
 * Copyright (C), 2013-2013, 上海汽车集团股份有限公司
 * FileName: Money.java
 * Author:   pengyao
 * Date:     2013年11月7日 上午11:02:26
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.liuyu.webapp.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author pengyao
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Target({ElementType.FIELD, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Constraint(validatedBy=MoneyValidator.class)  
public @interface Money {  
     
    String message() default"不是金额形式";  
     
    Class<?>[] groups() default {};  
     
    Class<? extends Payload>[] payload() default {};  
   
}  
