package com.mde.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.mde.entity.User;
import com.mde.model.SecurityUserModel;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport
{
    private static final long serialVersionUID = -1595334942122224610L;
    
    protected Authentication getAuthentication()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    
    protected User getCurrentUser()
    {
        Authentication authentication = getAuthentication();
        
        if (null != authentication)
        {
            Object principal = authentication.getPrincipal();
            
            if (principal instanceof SecurityUserModel)
            {
                return ((SecurityUserModel)principal).getUser();
            }
        }
        
        return null;
    }
    
    protected void setCommonAttributes()
    {
        HttpServletRequest request = getRequest();
        String base = request.getContextPath();
        request.setAttribute("base", base);
        request.setAttribute("thirdparty", base + "/thirdparty");
        request.setAttribute("system_name", "食为天便民网");
        
        User user = getCurrentUser();
        if (null != user)
        {
            request.setAttribute("username", user.getUsername());
        }
    }
    
    protected HttpServletRequest getRequest()
    {
        return ServletActionContext.getRequest();
    }
    
    protected HttpSession getSession()
    {
        return getRequest().getSession();
    }
}
