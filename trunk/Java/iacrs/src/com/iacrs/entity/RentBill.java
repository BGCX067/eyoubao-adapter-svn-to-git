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
@Table(name = "IACRS_BILL")
public class RentBill implements Serializable
{
    private static final long serialVersionUID = -1997394270309920380L;
    
    private Integer id;
    
    private Integer userId;
    
    private Integer carId;
    
    private Date startTime;
    
    private Date endTime;
    
    private Integer duration;
    
    private Integer dailyRental;
    
    private Integer dailyPremium;
    
    private Integer amount;
    
    private Date balanceTime;
    
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
    
    @Column(name = "CAR_ID")
    public Integer getCarId()
    {
        return carId;
    }
    
    public void setCarId(Integer carId)
    {
        this.carId = carId;
    }
    
    @Column(name = "START_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getStartTime()
    {
        return startTime;
    }
    
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }
    
    @Column(name = "END_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getEndTime()
    {
        return endTime;
    }
    
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }
    
    @Column(name = "DURATION")
    public Integer getDuration()
    {
        return duration;
    }
    
    public void setDuration(Integer duration)
    {
        this.duration = duration;
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
    
    @Column(name = "BALANCE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getBalanceTime()
    {
        return balanceTime;
    }
    
    public void setBalanceTime(Date balanceTime)
    {
        this.balanceTime = balanceTime;
    }
    
    @Column(name = "DAILY_RENTAL")
    public Integer getDailyRental()
    {
        return dailyRental;
    }
    
    public void setDailyRental(Integer dailyRental)
    {
        this.dailyRental = dailyRental;
    }
    
    @Column(name = "DAILY_PREMIUM")
    public Integer getDailyPremium()
    {
        return dailyPremium;
    }
    
    public void setDailyPremium(Integer dailyPremium)
    {
        this.dailyPremium = dailyPremium;
    }
}
