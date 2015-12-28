/*
 * 文件名称：UserAction.java  下午4:42:15 2013-4-15
 * 版权说明：js.todaysoft Technologies Co., Ltd. Copyright 2010-2017, All rights reserved.
 */
package com.mde.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mde.entity.Order;
import com.mde.model.Shopcart;
import com.mde.model.ShopcartEntry;
import com.mde.service.IUserService;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction
{
    private static final long serialVersionUID = 1700638452992281401L;
    
    @Autowired
    private IUserService userService;
    
    private Integer cookbookId;
    
    private List<ShopcartEntry> entriesForm;
    
    private Order orderForm;
    
    public String shopcart()
    {
        Shopcart shopcart = (Shopcart)getSession().getAttribute("shopcart");
        
        if (null == shopcart)
        {
            shopcart = new Shopcart();
            getSession().setAttribute("shopcart", shopcart);
        }
        
        getRequest().setAttribute("entries", shopcart.getEntries());
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String booking()
    {
        userService.booking(cookbookId);
        Shopcart shopcart = (Shopcart)getSession().getAttribute("shopcart");
        getRequest().setAttribute("entries", shopcart.getEntries());
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String forwardOrder()
    {
        Order order = userService.prepareOrder(entriesForm);
        getRequest().setAttribute("order", order);
        setCommonAttributes();
        return SUCCESS;
    }
    
    public String order()
    {
        userService.order(orderForm);
        return SUCCESS;
    }
    
    public Integer getCookbookId()
    {
        return cookbookId;
    }
    
    public void setCookbookId(Integer cookbookId)
    {
        this.cookbookId = cookbookId;
    }
    
    public List<ShopcartEntry> getEntriesForm()
    {
        return entriesForm;
    }
    
    public void setEntriesForm(List<ShopcartEntry> entriesForm)
    {
        this.entriesForm = entriesForm;
    }
    
    public Order getOrderForm()
    {
        return orderForm;
    }
    
    public void setOrderForm(Order orderForm)
    {
        this.orderForm = orderForm;
    }
}
