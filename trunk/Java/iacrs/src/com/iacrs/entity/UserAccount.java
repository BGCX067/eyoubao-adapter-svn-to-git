package com.iacrs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IACRS_USER_ACCOUNT")
public class UserAccount implements Serializable
{
    private static final long serialVersionUID = 1842990457556349922L;
    
    private Integer userId;
    
    private Integer balance;
    
    private Integer preauth;
    
    @Id
    @Column(name = "USER_ID")
    public Integer getUserId()
    {
        return userId;
    }
    
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }
    
    @Column(name = "BALANCE")
    public Integer getBalance()
    {
        return balance;
    }
    
    public void setBalance(Integer balance)
    {
        this.balance = balance;
    }
    
    @Column(name = "PRE_AUTH")
    public Integer getPreauth()
    {
        return preauth;
    }
    
    public void setPreauth(Integer preauth)
    {
        this.preauth = preauth;
    }
}
