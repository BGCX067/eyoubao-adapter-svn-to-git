package com.mde.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.mde.context.ContextHolder;

public class ContextUtil
{
    public static Map<String, String> getParameterMap(HttpServletRequest request)
    {
        Map<String, String> result = new HashMap<String, String>();
        Enumeration<?> names = request.getParameterNames();
        
        String name;
        Set<String> commons = new HashSet<String>();
        commons.add("base");
        commons.add("thirdparty");
        commons.add("system_name");
        
        while (names.hasMoreElements())
        {
            name = (String)names.nextElement();
            if (commons.contains(name))
            {
                continue;
            }
            
            result.put(name, request.getParameter(name));
        }
        
        return result;
    }
    
    public static String getMessage(String key, Object... objects)
    {
        return ContextHolder.getSystemContext().getMessage(key, objects);
    }
}
