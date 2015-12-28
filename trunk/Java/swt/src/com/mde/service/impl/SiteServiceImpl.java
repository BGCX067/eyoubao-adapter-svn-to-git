package com.mde.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mde.dao.BaseDaoSupport;
import com.mde.entity.User;
import com.mde.entity.UserArchive;
import com.mde.model.RegisterForm;
import com.mde.service.ISiteService;

@Service
public class SiteServiceImpl implements ISiteService
{
    @Autowired
    private BaseDaoSupport baseDaoSupport;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    @Transactional
    public void register(RegisterForm data)
    {
        UserArchive archive = new UserArchive();
        archive.setName(data.getName());
        archive.setPhone(data.getPhone());
        archive.setEmail(data.getEmail());
        archive.setAddress(data.getAddress());
        baseDaoSupport.insert(archive);
        
        User user = new User();
        user.setUsername(data.getUsername());
        user.setPassword(passwordEncoder.encodePassword(data.getPassword(), ""));
        user.setType(User.TYPE_USER);
        user.setDisabled(false);
        user.setArchiveID(archive.getId());
        baseDaoSupport.insert(user);
    }
}
