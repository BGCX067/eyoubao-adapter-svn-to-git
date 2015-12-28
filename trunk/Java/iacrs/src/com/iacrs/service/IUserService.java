package com.iacrs.service;

import com.iacrs.entity.User;
import com.iacrs.model.Pagination;
import com.iacrs.model.UserModel;

public interface IUserService
{
    User getUser(String username);
    
    UserModel getUserModel(Integer id);
    
    UserModel getUserModel(String username);
    
    UserModel getUserModelByCard(String idcardno);
    
    Pagination<UserModel> find(int pageNo, int pageSize);
    
    void setState(Integer id, boolean disabled);
    
    void setPreauth(Integer id, Integer preauth);
    
    void modifyPassword(String username, String oldPassword, String newPassword);
}
