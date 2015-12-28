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
@Table(name = "IACRS_CAR_POSITION")
public class CarPosition implements Serializable
{
    private static final long serialVersionUID = 5979789631186866522L;
    
    private Integer id;
    
    private Integer carId;
    
    private Double longitude;
    
    private Double latitude;
    
    private Date time;
    
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
    
    @Column(name = "CAR_ID")
    public Integer getCarId()
    {
        return carId;
    }
    
    public void setCarId(Integer carId)
    {
        this.carId = carId;
    }
    
    @Column(name = "FEEDBACK_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTime()
    {
        return time;
    }
    
    public void setTime(Date time)
    {
        this.time = time;
    }
    
    @Column(name = "LONGITUDE")
    public Double getLongitude()
    {
        return longitude;
    }
    
    public void setLongitude(Double longitude)
    {
        this.longitude = longitude;
    }
    
    @Column(name = "LATITUDE")
    public Double getLatitude()
    {
        return latitude;
    }
    
    public void setLatitude(Double latitude)
    {
        this.latitude = latitude;
    }
}
