package com.iacrs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IACRS_CAR_MODEL")
public class CarModel implements Serializable
{
    private static final long serialVersionUID = -4366834566981442694L;
    
    private Integer id;
    
    private String name;
    
    private String category;
    
    private String brand;
    
    private String style;
    
    private Integer coachType;
    
    private Integer gearType;
    
    private String sweptVolume;
    
    private String imagePath;
    
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
    
    @Column(name = "NAME")
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    @Column(name = "CATEGORY")
    public String getCategory()
    {
        return category;
    }
    
    public void setCategory(String category)
    {
        this.category = category;
    }
    
    @Column(name = "BRAND")
    public String getBrand()
    {
        return brand;
    }
    
    public void setBrand(String brand)
    {
        this.brand = brand;
    }
    
    @Column(name = "STYLE")
    public String getStyle()
    {
        return style;
    }
    
    public void setStyle(String style)
    {
        this.style = style;
    }
    
    @Column(name = "COACH_TYPE")
    public Integer getCoachType()
    {
        return coachType;
    }
    
    public void setCoachType(Integer coachType)
    {
        this.coachType = coachType;
    }
    
    @Column(name = "GEAR_TYPE")
    public Integer getGearType()
    {
        return gearType;
    }
    
    public void setGearType(Integer gearType)
    {
        this.gearType = gearType;
    }
    
    @Column(name = "SWEPT_VOLUME")
    public String getSweptVolume()
    {
        return sweptVolume;
    }
    
    public void setSweptVolume(String sweptVolume)
    {
        this.sweptVolume = sweptVolume;
    }
    
    @Column(name = "IMAGE_PATH")
    public String getImagePath()
    {
        return imagePath;
    }
    
    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
    }
}
