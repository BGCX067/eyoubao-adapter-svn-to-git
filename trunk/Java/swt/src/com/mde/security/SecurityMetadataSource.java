package com.mde.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

@Component(value = "securityMetadataSource")
public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource
{
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes()
    {
        return Collections.emptyList();
    }
    
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException
    {
        return Collections.emptyList();
    }
    
    @Override
    public boolean supports(Class<?> clazz)
    {
        return true;
    }
}
