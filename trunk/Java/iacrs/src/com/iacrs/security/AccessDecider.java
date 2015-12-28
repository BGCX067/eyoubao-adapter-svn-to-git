package com.iacrs.security;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

@Component(value = "accessDecisionManager")
public class AccessDecider implements AccessDecisionManager
{
    private static Logger log = LoggerFactory.getLogger(AccessDecider.class);
    
    @Autowired
    private ISecurityService securityService;
    
    @Override
    public void decide(Authentication auth, Object object, Collection<ConfigAttribute> attributes) throws AccessDeniedException, InsufficientAuthenticationException
    {
        String uri = ((FilterInvocation)object).getRequestUrl();
        
        if (securityService.isAccessDenied(uri, auth))
        {
            log.info("User {}, Access {} be denied.", auth.getName(), uri);
            throw new AccessDeniedException("");
        }
    }
    
    @Override
    public boolean supports(ConfigAttribute attribute)
    {
        return true;
    }
    
    @Override
    public boolean supports(Class<?> clazz)
    {
        return true;
    }
}
