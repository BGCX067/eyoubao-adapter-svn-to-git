package com.iacrs.security;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "passwordEncoder")
public class UserPasswordEncoder implements PasswordEncoder
{
    @Override
    public String encodePassword(String rawPass, Object salt) throws DataAccessException
    {
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        String password = md5.encodePassword(rawPass, salt);
        ShaPasswordEncoder sha = new ShaPasswordEncoder();
        return sha.encodePassword(password, salt);
    }
    
    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) throws DataAccessException
    {
        return PasswordEncoderUtils.equals(encPass, encodePassword(rawPass, salt));
    }
}
