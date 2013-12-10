/*
 * Copyright (C), 2013-2013, 上海汽车集团股份有限公司
 * FileName: IUserDao.java
 * Author:   pengyao
 * Date:     2013年12月2日 下午2:20:40
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.jyl.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jyl.loan.entity.User;

/**
 * @author pengyao
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface IUserDao extends JpaRepository<User, Long> {
    
    User findByUid(Long uid);
    
    User findByNameAndPassword(String name ,String password);
    
    User findByNameOrPassword(String name ,String password);
}
