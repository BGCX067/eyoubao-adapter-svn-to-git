package com.iacrs.security;

import org.springframework.security.core.Authentication;

public interface ISecurityService
{
    boolean isAccessDenied(String uri, Authentication auth);
    
    SecurityUserModel getSecurityUserModel(String username);
}
