/*
 * Copyright (C), 2013-2013, 上海汽车集团股份有限公司
 * FileName: User.java
 * Author:   pengyao
 * Date:     2013年11月7日 上午10:52:00
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.liuyu.webapp.welcome;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author pengyao
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class UserVO {

    @NotNull
    @Size(min = 2, max = 6, message = "名称长度必须为2-6个字符")
    private String userName;

    @Min(value = 1, message = "年龄不能小于1岁")
    @Max(value = 120, message = "年龄不能大于120岁!")
    private int age;

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "userName : " + userName + " , age : " + age;
    }
}
