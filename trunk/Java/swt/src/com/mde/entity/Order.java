package com.mde.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SWT_ORDER")
public class Order implements Serializable
{
    public static final int PAY_MODE_OFFLINE = 0;
    
    public static final int PAY_MODE_ONLINE = 1;
    
    public static final int PAY_STATE_PAID = 0;
    
    public static final int PAY_STATE_UNPAID = 1;
    
    public static final int STATE_UNCONFIRM = 0;
    
    public static final int STATE_CONFIRM = 1;
    
    public static final int STATE_CANCEL = 2;
    
    private static final long serialVersionUID = -5800099082917371223L;
    
    private Integer id;
    
    private String code;
    
    private Double amount;
    
    private Date orderTime;
    
    private Integer state;
    
    private Integer payMode;
    
    private Integer payState;
    
    private String deliverAddress;
    
    private List<OrderEntry> entries;
    
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
    
    @Column(name = "CODE")
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    @Column(name = "AMOUNT")
    public Double getAmount()
    {
        return amount;
    }
    
    public void setAmount(Double amount)
    {
        this.amount = amount;
    }
    
    @Column(name = "ORDER_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getOrderTime()
    {
        return orderTime;
    }
    
    public void setOrderTime(Date orderTime)
    {
        this.orderTime = orderTime;
    }
    
    @Column(name = "STATE")
    public Integer getState()
    {
        return state;
    }
    
    public void setState(Integer state)
    {
        this.state = state;
    }
    
    @Column(name = "PAY_MODE")
    public Integer getPayMode()
    {
        return payMode;
    }
    
    public void setPayMode(Integer payMode)
    {
        this.payMode = payMode;
    }
    
    @Column(name = "PAY_STATE")
    public Integer getPayState()
    {
        return payState;
    }
    
    public void setPayState(Integer payState)
    {
        this.payState = payState;
    }
    
    @Column(name = "DELIVER_ADDRESS")
    public String getDeliverAddress()
    {
        return deliverAddress;
    }
    
    public void setDeliverAddress(String deliverAddress)
    {
        this.deliverAddress = deliverAddress;
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    public List<OrderEntry> getEntries()
    {
        return entries;
    }
    
    public void setEntries(List<OrderEntry> entries)
    {
        this.entries = entries;
    }
    
}
