package com.mde.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mde.dao.BaseDaoSupport;
import com.mde.entity.Cookbook;
import com.mde.entity.Order;
import com.mde.entity.OrderEntry;
import com.mde.model.Shopcart;
import com.mde.model.ShopcartEntry;
import com.mde.service.IAdminService;
import com.mde.service.IUserService;
import com.mde.util.DateUtil;

@Service
public class UserServiceImpl implements IUserService
{
    @Autowired
    private BaseDaoSupport baseDaoSupport;
    
    @Autowired
    private IAdminService adminService;
    
    @Override
    public void booking(Integer cookbookId)
    {
        Cookbook cookbook = adminService.getCookbook(cookbookId);
        Shopcart shopcart = (Shopcart)ServletActionContext.getRequest().getSession().getAttribute("shopcart");
        
        if (null == shopcart)
        {
            shopcart = new Shopcart();
            ServletActionContext.getRequest().getSession().setAttribute("shopcart", shopcart);
        }
        
        shopcart.add(cookbook);
    }
    
    @Override
    public Order prepareOrder(List<ShopcartEntry> data)
    {
        Cookbook cookbook;
        OrderEntry entry;
        Double amount = 0D;
        List<OrderEntry> entries = new ArrayList<>();
        
        for (ShopcartEntry element : data)
        {
            cookbook = adminService.getCookbook(element.getCookbook().getId());
            entry = new OrderEntry();
            entry.setCookbookName(cookbook.getName());
            entry.setCount(element.getCount());
            entry.setPrice(cookbook.getPrice());
            entry.setCategoryName(cookbook.getCategory().getName());
            entry.setNosheryName(cookbook.getNoshery().getName());
            amount += entry.getPrice() * entry.getCount();
            entries.add(entry);
        }
        
        Order order = new Order();
        order.setAmount(amount);
        order.setEntries(entries);
        return order;
    }
    
    @Override
    @Transactional
    public void order(Order order)
    {
        Date now = new Date();
        String code = DateUtil.format(now, "yyyyMMddHHmmss");
        order.setCode(code);
        order.setOrderTime(now);
        order.setState(Order.STATE_UNCONFIRM);
        
        if (Order.PAY_MODE_ONLINE == order.getPayMode())
        {
            order.setPayState(Order.PAY_STATE_UNPAID);
        }
        
        for (OrderEntry entry : order.getEntries())
        {
            entry.setOrder(order);
        }
        
        baseDaoSupport.insert(order);
    }
}
