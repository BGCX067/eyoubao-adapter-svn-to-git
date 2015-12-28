package com.iacrs.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.iacrs.dao.BaseDaoSupport;
import com.iacrs.entity.User;
import com.iacrs.security.ISecurityService;
import com.iacrs.security.SecurityUserModel;

@Service
public class SecurityServiceImpl implements ISecurityService
{
    private static String ROLE_ADMIN = "ROLE_ADMIN";
    
    private static String ROLE_USER = "ROLE_USER";
    
    private static final String ANONYMOUS_USER = "anonymousUser";
    
    @Autowired
    private BaseDaoSupport baseDaoSupport;
    
    @Override
    public boolean isAccessDenied(String uri, Authentication auth)
    {
        // 默认没有登陆的都拒绝访问
        if (ANONYMOUS_USER.equals(auth.getPrincipal()))
        {
            return true;
        }
        
        SecurityUserModel user = (SecurityUserModel)auth.getPrincipal();
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        PathMatcher matcher = new AntPathMatcher();
        
        // 匹配管理员能访问的链接必须要有管理员角色
        if (matcher.match("/admin/**", uri))
        {
            for (GrantedAuthority authority : authorities)
            {
                if (ROLE_ADMIN.equals(authority.getAuthority()))
                {
                    return false;
                }
            }
            
            return true;
        }
        
        // 匹配用户能访问的链接必须要有用户角色
        if (matcher.match("/subscriber/**", uri))
        {
            for (GrantedAuthority authority : authorities)
            {
                if (ROLE_USER.equals(authority.getAuthority()))
                {
                    return false;
                }
            }
            
            return true;
        }
        
        return false;
    }
    
    @Override
    public SecurityUserModel getSecurityUserModel(String username)
    {
        String hql = "FROM User u WHERE u.username = ?";
        List<User> records = baseDaoSupport.find(User.class, hql, username);
        
        if (records.isEmpty())
        {
            return null;
        }
        
        SecurityUserModel model = new SecurityUserModel(records.get(0));
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        String role = User.TYPE_ADMIN == model.getUser().getType() ? ROLE_ADMIN : ROLE_USER;
        authorities.add(new GrantedAuthorityImpl(role));
        model.setAuthorities(authorities);
        return model;
    }
}
