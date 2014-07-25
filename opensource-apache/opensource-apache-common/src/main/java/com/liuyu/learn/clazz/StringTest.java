/*
 * Copyright (C), 2013-2013, 上海汽车集团股份有限公司
 * FileName: StringTest.java
 * Author:   pengyao
 * Date:     2014年5月14日 下午5:59:34
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.liuyu.learn.clazz;

/**
 * @author pengyao
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class StringTest {
    public static void main(String[] args) {
        System.out.println(String.format("distribute leads to %s. success: %d ; fail: %d ", null,0,0));
        System.out.println(String.format("distribute leads to %s. success: %d ; fail: %d ", "SGM",1,2));
        System.out.println(String.format("distribute leads to %s. success: %d ; fail: %d ", null,0,0));
        
        String s1=null,s2=null;
        System.out.println(s1+s2);
    }
}
