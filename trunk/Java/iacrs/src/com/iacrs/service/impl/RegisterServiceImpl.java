package com.iacrs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iacrs.dao.BaseDaoSupport;
import com.iacrs.entity.User;
import com.iacrs.entity.UserAccount;
import com.iacrs.entity.UserArchive;
import com.iacrs.model.RegisterForm;
import com.iacrs.service.IRegisterService;

@Service
public class RegisterServiceImpl implements IRegisterService
{
    
    @Autowired
    private BaseDaoSupport baseDaoSupport;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    @Transactional
    public void register(RegisterForm data)
    {
        User user = new User();
        user.setUsername(data.getUsername());
        user.setPassword(passwordEncoder.encodePassword(data.getPassword(), ""));
        user.setType(User.TYPE_USER);
        user.setDisabled(false);
        baseDaoSupport.insert(user);
        UserArchive archive = new UserArchive();
        archive.setUserId(user.getId());
        archive.setName(data.getName());
        archive.setIdcardno(data.getIdcardno());
        archive.setPhone(data.getPhone());
        archive.setEmail(data.getEmail());
        archive.setAddress(data.getAddress());
        baseDaoSupport.insert(archive);
        UserAccount account = new UserAccount();
        account.setUserId(user.getId());
        account.setBalance(0);
        account.setPreauth(0);
        baseDaoSupport.insert(account);
    }
    
}
