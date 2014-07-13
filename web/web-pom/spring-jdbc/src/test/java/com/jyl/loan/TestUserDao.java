/*
 * Copyright (C), 2013-2013, 上海汽车集团股份有限公司
 * FileName: TestUserDao.java
 * Author:   pengyao
 * Date:     2013年12月2日 下午2:27:15
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.jyl.loan;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jyl.loan.entity.User;
import com.jyl.loan.repository.IUserDao;
import com.jyl.loan.service.UserService;

/**
 * @author pengyao
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:context-jpa.xml"})
public class TestUserDao  extends AbstractTestNGSpringContextTests{
    
    private Logger log = LoggerFactory.getLogger(TestUserDao.class);
    
    @Autowired
    UserService userService;
    
    @Autowired
    IUserDao userDao;
    
    @Test(groups={"user"},priority=0)
    public void testAddUser(){
        Assert.assertNotNull(userService);
        User user = userDao.save(new User("张三","222"));
        Assert.assertNotNull(user.getUid());
        System.out.println(user.getUid());
    }
    
    @Test(groups={"user"},priority=0)
    public void testGetUser(){
        Assert.assertNotNull(userDao);
        User user = userDao.findByUid(1l);
        Assert.assertNotNull(user);
        log.info(user.getName());
        
        String name="李四";
        String pass=String.valueOf(System.currentTimeMillis());
        userDao.save(new User(name,pass));
        user = userDao.findByNameAndPassword(name, pass);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getUid());
        
        user = userDao.findByNameOrPassword(null, pass);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getUid());
        
      //  user = userDao.findByNameOrPassword(name, pass);
      //  Assert.assertNotNull(user);
      //  Assert.assertNotNull(user.getUid());
    }
}
