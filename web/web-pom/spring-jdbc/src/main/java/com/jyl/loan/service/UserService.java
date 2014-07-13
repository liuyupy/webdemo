/*
 * Copyright (C), 2013-2013, 上海汽车集团股份有限公司
 * FileName: IUserService.java
 * Author:   pengyao
 * Date:     2013年12月2日 下午4:39:19
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.jyl.loan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyl.loan.entity.User;
import com.jyl.loan.repository.IUserDao;

/**
 * @author pengyao
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service("userService")
public class UserService {
    
    @Autowired
    private IUserDao userDao;
    
    public User saveUser(User user){
        return userDao.save(user);
    }
}
