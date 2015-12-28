package com.iacrs.model;

import com.iacrs.entity.User;
import com.iacrs.entity.UserAccount;
import com.iacrs.entity.UserArchive;

public class UserModel
{
    private User user;
    
    private UserArchive archive;
    
    private UserAccount account;
    
    public User getUser()
    {
        return user;
    }
    
    public void setUser(User user)
    {
        this.user = user;
    }
    
    public UserArchive getArchive()
    {
        return archive;
    }
    
    public void setArchive(UserArchive archive)
    {
        this.archive = archive;
    }
    
    public UserAccount getAccount()
    {
        return account;
    }
    
    public void setAccount(UserAccount account)
    {
        this.account = account;
    }
}
