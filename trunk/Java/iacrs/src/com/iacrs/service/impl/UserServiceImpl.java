package com.iacrs.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iacrs.dao.BaseDaoSupport;
import com.iacrs.entity.User;
import com.iacrs.entity.UserAccount;
import com.iacrs.entity.UserArchive;
import com.iacrs.exception.ServiceException;
import com.iacrs.exception.ServiceExceptionCode;
import com.iacrs.model.Pagination;
import com.iacrs.model.Queryer;
import com.iacrs.model.UserModel;
import com.iacrs.service.IUserService;

@Service
public class UserServiceImpl implements IUserService
{
    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Autowired
    private BaseDaoSupport baseDaoSupport;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public User getUser(String username)
    {
        String hql = "FROM User u WHERE u.username = ?";
        List<User> users = baseDaoSupport.find(User.class, hql, username);
        return users.isEmpty() ? null : users.get(0);
    }
    
    @Override
    public UserModel getUserModel(Integer id)
    {
        User user = baseDaoSupport.get(User.class, id);
        
        if (null == user)
        {
            return null;
        }
        
        UserModel model = new UserModel();
        model.setUser(user);
        model.setArchive(baseDaoSupport.get(UserArchive.class, user.getId()));
        model.setAccount(baseDaoSupport.get(UserAccount.class, user.getId()));
        return model;
    }
    
    @Override
    public UserModel getUserModel(String username)
    {
        User user = getUser(username);
        
        if (null == user)
        {
            return null;
        }
        
        UserModel model = new UserModel();
        model.setUser(user);
        model.setArchive(baseDaoSupport.get(UserArchive.class, user.getId()));
        model.setAccount(baseDaoSupport.get(UserAccount.class, user.getId()));
        return model;
    }
    
    @Override
    public UserModel getUserModelByCard(String idcardno)
    {
        String hql =
            "FROM User u, UserArchive uar, UserAccount uac WHERE uar.userId = u.id AND uac.userId = u.id AND u.type = ? AND uar.idcardno = ?";
        List<?> records = baseDaoSupport.find(hql, User.TYPE_USER, idcardno);
        
        if (records.isEmpty())
        {
            return null;
        }
        
        return wrap((Object[])records.get(0));
    }
    
    @Override
    public Pagination<UserModel> find(int pageNo, int pageSize)
    {
        String hql =
            "FROM User u, UserArchive uar, UserAccount uac WHERE uar.userId = u.id AND uac.userId = u.id AND u.type = ?";
        List<Object> parameters = new ArrayList<Object>();
        parameters.add(User.TYPE_USER);
        Queryer queryer = new Queryer();
        queryer.setHql(hql);
        queryer.setParameters(parameters);
        Pagination<Object[]> pagination = baseDaoSupport.find(queryer, pageNo, pageSize, Object[].class);
        List<Object[]> records = pagination.getRecords();
        List<UserModel> wrapperRecords = new ArrayList<UserModel>();
        
        for (Object[] record : records)
        {
            wrapperRecords.add(wrap(record));
        }
        
        Pagination<UserModel> result =
            new Pagination<UserModel>(pagination.getPageNo(), pagination.getPageSize(), pagination.getTotalCount());
        result.setRecords(wrapperRecords);
        return result;
    }
    
    private UserModel wrap(Object[] record)
    {
        UserModel model = new UserModel();
        model.setUser((User)record[0]);
        model.setArchive((UserArchive)record[1]);
        model.setAccount((UserAccount)record[2]);
        return model;
    }
    
    @Override
    @Transactional
    public void setState(Integer id, boolean disabled)
    {
        User user = baseDaoSupport.get(User.class, id);
        user.setDisabled(disabled);
        baseDaoSupport.update(user);
    }
    
    @Override
    @Transactional
    public void setPreauth(Integer id, Integer preauth)
    {
        UserAccount account = baseDaoSupport.get(UserAccount.class, id);
        account.setPreauth(preauth);
        baseDaoSupport.update(account);
    }
    
    @Override
    @Transactional
    public void modifyPassword(String username, String oldPassword, String newPassword)
    {
        User user = getUser(username);
        
        if (!passwordEncoder.isPasswordValid(user.getPassword(), oldPassword, ""))
        {
            log.error("Modify password error: the old password is not matched.");
            throw new ServiceException(ServiceExceptionCode.IACRS_0002);
        }
        
        user.setPassword(passwordEncoder.encodePassword(newPassword, ""));
        baseDaoSupport.update(user);
    }
}
