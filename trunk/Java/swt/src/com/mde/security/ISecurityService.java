package com.mde.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface ISecurityService
{
    UserDetails getUserDetails(String username);
    
    boolean isAccessDenied(String uri, Authentication auth);
}
