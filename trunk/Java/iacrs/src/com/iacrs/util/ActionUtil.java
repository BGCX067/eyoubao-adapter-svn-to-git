package com.iacrs.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.iacrs.context.ContextHolder;

public class ActionUtil
{
    public static void fillCommonAttributes(HttpServletRequest request, ModelMap model)
    {
        String base = request.getContextPath();
        model.addAttribute("base", base);
        model.addAttribute("thirdparty", base + "/thirdparty");
        model.addAttribute("system_name", getMessage("System.Name"));
    }
    
    public static String getMessage(String key, Object... objects)
    {
        return ContextHolder.getSystemContext().getMessage(key, objects);
    }
}
