package com.iacrs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IACRS_CAR_INFO")
public class Car implements Serializable
{
    private static final long serialVersionUID = -6396163742875618681L;
    
    private Integer id;
    
    private Integer modelId;
    
    private Integer dailyRental;
    
    private Integer dailyPremium;
    
    private Integer preauth;
    
    private String licencePlate;
    
    private String gpsid;
    
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
    
    @Column(name = "MODEL_ID")
    public Integer getModelId()
    {
        return modelId;
    }
    
    public void setModelId(Integer modelId)
    {
        this.modelId = modelId;
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
    
    @Column(name = "LICENCE_PLATE")
    public String getLicencePlate()
    {
        return licencePlate;
    }
    
    public void setLicencePlate(String licencePlate)
    {
        this.licencePlate = licencePlate;
    }
    
    @Column(name = "GPS_ID")
    public String getGpsid()
    {
        return gpsid;
    }
    
    public void setGpsid(String gpsid)
    {
        this.gpsid = gpsid;
    }
}
