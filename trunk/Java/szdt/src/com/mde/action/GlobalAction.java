/*
 * 文件名称：MasterAction.java  下午2:18:36 2013-3-12
 * 版权说明：js.todaysoft Technologies Co., Ltd. Copyright 2010-2017, All rights reserved.
 */
package com.mde.action;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 处理系统全局请求
 *
 * @author  xuxin
 * @version 1.0, 2013-3-12
 */
@Controller
public class GlobalAction extends BaseAction
{
    @RequestMapping("/index.do")
    public String index(String username, HttpSession session)
    {
        if (null == username && null == getCurrentUsername(session))
        {
            return "forbidden";
        }
        
        if (null != username)
        {
            session.setAttribute("USERNAME", username);
        }
        
        return "index";
    }
    
    @RequestMapping("/library.do")
    public String library()
    {
        return "library";
    }
}
