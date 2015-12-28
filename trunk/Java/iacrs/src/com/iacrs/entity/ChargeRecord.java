package com.iacrs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "IACRS_CHARGE_RECORD")
public class ChargeRecord implements Serializable
{
    private static final long serialVersionUID = -6069912962153274480L;
    
    private Integer id;
    
    private Integer userId;
    
    private Date chargeTime;
    
    private Integer amount;
    
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    @Column(name = "USER_ID")
    public Integer getUserId()
    {
        return userId;
    }
    
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }
    
    @Column(name = "CHARGE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getChargeTime()
    {
        return chargeTime;
    }
    
    public void setChargeTime(Date chargeTime)
    {
        this.chargeTime = chargeTime;
    }
    
    @Column(name = "AMOUNT")
    public Integer getAmount()
    {
        return amount;
    }
    
    public void setAmount(Integer amount)
    {
        this.amount = amount;
    }
}
