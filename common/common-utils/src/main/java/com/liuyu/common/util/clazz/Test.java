/*
 * Copyright (C), 2013-2013, 上海汽车集团股份有限公司
 * FileName: Test.java
 * Author:   pengyao
 * Date:     2014年5月7日 上午10:30:23
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.liuyu.common.util.clazz;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author pengyao
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Test<T>
{
 
    public Class<T> getTClass()
    {
        Class<T> tClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }
    
    public static void main(String[] args)
    {
        Test<String> foo = new Test<String>(){
            
        };
        // 在类的外部这样获取
        Type type = ((ParameterizedType)foo.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        System.out.println(type);
        // 在类的内部这样获取
        System.out.println(foo.getTClass());
        
    }
}
 
