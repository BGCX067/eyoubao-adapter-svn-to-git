package com.mde.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 设置公共属性
 *
 * @author  xuxin
 * @version 1.1.2, 2013-3-12
 */
public class BaseAction
{
    @ModelAttribute
    protected void fillCommonAttributes(HttpServletRequest request, ModelMap model)
    {
        String base = request.getContextPath();
        model.addAttribute("base", base);
        model.addAttribute("site_name", "深圳地铁文化中心");
        model.addAttribute("thirdparty", base + "/thirdparty");
    }
    
    protected String getCurrentUsername(HttpSession session)
    {
        return (String)session.getAttribute("USERNAME");
    }
}
