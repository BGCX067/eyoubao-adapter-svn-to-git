package com.mde.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mde.entity.User;

public class SecurityUserModel implements UserDetails
{
    private static final long serialVersionUID = -7150571514002329891L;
    
    private User user;
    
    private Collection<GrantedAuthority> authorities;
    
    public SecurityUserModel(User user)
    {
        this.user = user;
    }
    
    @Override
    public Collection<GrantedAuthority> getAuthorities()
    {
        return this.authorities;
    }
    
    @Override
    public String getPassword()
    {
        return this.user.getPassword();
    }
    
    @Override
    public String getUsername()
    {
        return this.user.getUsername();
    }
    
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }
    
    @Override
    public boolean isEnabled()
    {
        return !this.user.getDisabled();
    }
    
    public void setAuthorities(Collection<GrantedAuthority> authorities)
    {
        this.authorities = authorities;
    }
    
    public User getUser()
    {
        return user;
    }
}
