package com.mde.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.mde.dao.BaseDaoSupport;
import com.mde.entity.User;
import com.mde.model.SecurityUserModel;
import com.mde.security.ISecurityService;

@Service
public class SecurityServiceImpl implements ISecurityService
{
    @Resource(name = "baseDaoSupport")
    private BaseDaoSupport baseDaoSupport;
    
    private static final String ANONYMOUS_USER = "anonymousUser";
    
    @Override
    public UserDetails getUserDetails(String username)
    {
        String hql = "FROM User u WHERE u.username = ?";
        List<User> records = baseDaoSupport.find(User.class, hql, username);
        
        if (records.isEmpty())
        {
            return null;
        }
        
        return new SecurityUserModel(records.get(0));
    }
    
    @Override
    public boolean isAccessDenied(String uri, Authentication auth)
    {
        PathMatcher matcher = new AntPathMatcher();
        
        List<String> ignores = new ArrayList<>();
        ignores.add("/index.do");
        ignores.add("/forward_login.do");
        ignores.add("/forward_register.do");
        ignores.add("/register.do");
        ignores.add("/noshery_list.do");
        ignores.add("/category_list.do");
        ignores.add("/category_list.do");
        ignores.add("/cookbook_list.do");
        
        for (String ignore : ignores)
        {
            if (matcher.match(ignore, uri))
            {
                return false;
            }
        }
        
        if (ANONYMOUS_USER.equals(auth.getPrincipal()))
        {
            return true;
        }
        
        List<String> commons = new ArrayList<String>();
        commons.add("/login_success.do");
        commons.add("/top.do");
        commons.add("/main.do");
        commons.add("/left.do");
        commons.add("/preferred_menu.do");
        
        SecurityUserModel userModel = (SecurityUserModel)auth.getPrincipal();
        
        for (String common : commons)
        {
            if (matcher.match(common, uri))
            {
                return false;
            }
        }
        
        if (matcher.match("/admin/**", uri))
        {
            return userModel.getUser().getType() != User.TYPE_ADMIN;
        }
        
        if (matcher.match("/user/**", uri))
        {
            return userModel.getUser().getType() != User.TYPE_USER;
        }
        
        return true;
    }
}
