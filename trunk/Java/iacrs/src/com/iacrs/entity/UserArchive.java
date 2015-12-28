package com.iacrs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IACRS_USER_INFO")
public class UserArchive implements Serializable
{
    private static final long serialVersionUID = -8858334673916183514L;
    
    private Integer userId;
    
    private String name;
    
    private String idcardno;
    
    private String phone;
    
    private String email;
    
    private String address;
    
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
    
    @Column(name = "NAME")
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    @Column(name = "ID_CARD_NO")
    public String getIdcardno()
    {
        return idcardno;
    }
    
    public void setIdcardno(String idcardno)
    {
        this.idcardno = idcardno;
    }
    
    @Column(name = "PHONE")
    public String getPhone()
    {
        return phone;
    }
    
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    
    @Column(name = "EMAIL")
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    @Column(name = "ADDRESS")
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
}
