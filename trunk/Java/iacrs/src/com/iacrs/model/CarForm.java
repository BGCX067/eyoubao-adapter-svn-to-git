package com.iacrs.model;

public class CarForm
{
    private Integer id;
    
    private Integer modelId;
    
    private Integer dailyRental;
    
    private Integer dailyPremium;
    
    private Integer preauth;
    
    private String licencePlate;
    
    private String gpsid;
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public Integer getModelId()
    {
        return modelId;
    }
    
    public void setModelId(Integer modelId)
    {
        this.modelId = modelId;
    }
    
    public Integer getDailyRental()
    {
        return dailyRental;
    }
    
    public void setDailyRental(Integer dailyRental)
    {
        this.dailyRental = dailyRental;
    }
    
    public Integer getDailyPremium()
    {
        return dailyPremium;
    }
    
    public void setDailyPremium(Integer dailyPremium)
    {
        this.dailyPremium = dailyPremium;
    }
    
    public Integer getPreauth()
    {
        return preauth;
    }
    
    public void setPreauth(Integer preauth)
    {
        this.preauth = preauth;
    }
    
    public String getLicencePlate()
    {
        return licencePlate;
    }
    
    public void setLicencePlate(String licencePlate)
    {
        this.licencePlate = licencePlate;
    }
    
    public String getGpsid()
    {
        return gpsid;
    }
    
    public void setGpsid(String gpsid)
    {
        this.gpsid = gpsid;
    }
}
