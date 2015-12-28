package com.mde.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SWT_ORDER_ENTRY")
public class OrderEntry implements Serializable
{
    private static final long serialVersionUID = 7978549892613270534L;
    
    private Integer id;
    
    private Order order;
    
    private String cookbookName;
    
    private String categoryName;
    
    private String nosheryName;
    
    private Double price;
    
    private Integer count;
    
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
    
    @Column(name = "COOKBOOK_NAME")
    public String getCookbookName()
    {
        return cookbookName;
    }
    
    public void setCookbookName(String cookbookName)
    {
        this.cookbookName = cookbookName;
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
    
    @Column(name = "COUNT")
    public Integer getCount()
    {
        return count;
    }
    
    public void setCount(Integer count)
    {
        this.count = count;
    }
    
    @Column(name = "CATEGORY_NAME")
    public String getCategoryName()
    {
        return categoryName;
    }
    
    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }
    
    @Column(name = "NOSHERY_NAME")
    public String getNosheryName()
    {
        return nosheryName;
    }
    
    public void setNosheryName(String nosheryName)
    {
        this.nosheryName = nosheryName;
    }
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDER_ID")
    public Order getOrder()
    {
        return order;
    }
    
    public void setOrder(Order order)
    {
        this.order = order;
    }
}
