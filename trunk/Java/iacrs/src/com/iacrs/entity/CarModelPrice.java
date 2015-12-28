package com.iacrs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IACRS_CAR_MODEL_PRICE")
public class CarModelPrice implements Serializable
{
    private static final long serialVersionUID = 4584083482517031869L;
    
    private Integer carModelId;
    
    private Integer dailyRental;
    
    private Integer dailyPremium;
    
    private Integer preauth;
    
    @Id
    @Column(name = "CAR_MODEL_ID")
    public Integer getCarModelId()
    {
        return carModelId;
    }
    
    public void setCarModelId(Integer carModelId)
    {
        this.carModelId = carModelId;
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
