/*
 * Copyright (C), 2013-2013, 上海汽车集团股份有限公司
 * FileName: UserController.java
 * Author:   pengyao
 * Date:     2013年12月2日 下午10:16:18
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.jyl.loan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jyl.loan.entity.User;
import com.jyl.loan.repository.IUserDao;

/**
 * @author pengyao
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    IUserDao userDao;
    
    @RequestMapping(value = "/list" , method = RequestMethod.GET)
    public ModelAndView listUser(Model model){
        ModelAndView mav = new ModelAndView("user/listuser");
        List<User> users = userDao.findAll();
        model.addAttribute("users", users);
        return mav;
    }

}
