package com.mde.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mde.entity.User;
import com.mde.model.MenuTreeNode;
import com.mde.service.IMenuService;

@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends BaseAction
{
    private static final long serialVersionUID = 5015382340882956203L;
    
    @Autowired
    private IMenuService menuService;
    
    private String innerUri;
    
    public String forwardLogin()
    {
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String forwardLoginSuccess()
    {
        if (null != innerUri)
        {
            getRequest().setAttribute("innerUri", innerUri);
        }
        
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String top()
    {
        getRequest().setAttribute("username", getCurrentUser().getUsername());
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String main()
    {
        if (null != innerUri)
        {
            getRequest().setAttribute("innerUri", innerUri);
        }
        
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String left()
    {
        List<MenuTreeNode> menus = menuService.getRootMenus(getAuthentication());
        getRequest().setAttribute("menus", menus);
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String preferredMenu()
    {
        User user = getCurrentUser();
        String url = User.TYPE_ADMIN == user.getType() ? "/admin/user_list.do" : "/admin/user_list.do";
        getRequest().setAttribute("redirect_url", url);
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String getInnerUri()
    {
        return innerUri;
    }
    
    public void setInnerUri(String innerUri)
    {
        this.innerUri = innerUri;
    }
}
