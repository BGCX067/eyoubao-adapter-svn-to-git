package com.mde.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "SWT_COOKBOOK")
public class Cookbook implements Serializable
{
    private static final long serialVersionUID = -2026018622185919160L;
    
    private Integer id;
    
    private Integer nosheryId;
    
    private Integer categoryId;
    
    private String name;
    
    private Double price;
    
    private String imagePath;
    
    private String description;
    
    private Noshery noshery;
    
    private Category category;
    
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
    
    @Column(name = "NOSHERY_ID")
    public Integer getNosheryId()
    {
        return nosheryId;
    }
    
    public void setNosheryId(Integer nosheryId)
    {
        this.nosheryId = nosheryId;
    }
    
    @Column(name = "CATEGORY_ID")
    public Integer getCategoryId()
    {
        return categoryId;
    }
    
    public void setCategoryId(Integer categoryId)
    {
        this.categoryId = categoryId;
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
    
    @Column(name = "PRICE")
    public Double getPrice()
    {
        return price;
    }
    
    public void setPrice(Double price)
    {
        this.price = price;
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
    
    @Column(name = "DESCRIPTION")
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    @OneToOne(targetEntity = Noshery.class, fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "NOSHERY_ID", insertable = false, updatable = false)
    public Noshery getNoshery()
    {
        return noshery;
    }
    
    public void setNoshery(Noshery noshery)
    {
        this.noshery = noshery;
    }
    
    @OneToOne(targetEntity = Category.class, fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "CATEGORY_ID", insertable = false, updatable = false)
    public Category getCategory()
    {
        return category;
    }
    
    public void setCategory(Category category)
    {
        this.category = category;
    }
}
