/*
 * Copyright (C), 2013-2013, 上海汽车集团股份有限公司
 * FileName: User.java
 * Author:   pengyao
 * Date:     2013年12月2日 下午1:59:36
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.jyl.loan.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author pengyao
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Entity(name="user")
@Table(name="t_user")
public class User implements Serializable{
    
    private static final long serialVersionUID = 21898061660159260L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long uid;
    
    @Column(name="user_name",nullable=false,insertable=true)
    private String name;
    
    @Column(name="user_password",nullable=false,insertable=true)
    private String password;
    
    public User(){}
    
    public User(String name,String password){
        this.name = name;
        this.password = password;
    }

    /**
     * @return the uid
     */
    public Long getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
