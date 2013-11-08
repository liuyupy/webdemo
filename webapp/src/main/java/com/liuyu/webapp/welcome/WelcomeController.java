/*
 * Copyright (C), 2013-2013, 上海汽车集团股份有限公司
 * FileName: WelcomeController.java
 * Author:   pengyao
 * Date:     2013年11月7日 上午10:29:18
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.liuyu.webapp.welcome;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author pengyao
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping(value="welcome")
public class WelcomeController {
    
    private static Logger log = LoggerFactory.getLogger(WelcomeController.class);

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ModelAndView welcome(Model model) {
        UserVO user = new UserVO();
        ModelAndView mav = new ModelAndView("index");
        model.addAttribute("user", user);
        return mav;
    }

    @RequestMapping(value = "/selfinfo", method = RequestMethod.POST)
    public ModelAndView info(@Valid UserVO user, BindingResult result, Model model) {
        log.debug(user.toString());
        
        ModelAndView mav = new ModelAndView("index");
        if (result.hasErrors()) {
            model.addAttribute("error", result.getAllErrors());
            
        }
        model.addAttribute("user", user);
        return mav;
    }
}
