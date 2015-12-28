package com.mde.service;

import java.util.List;

import com.mde.entity.Order;
import com.mde.model.ShopcartEntry;

public interface IUserService
{
    void booking(Integer cookbookId);
    
    Order prepareOrder(List<ShopcartEntry> data);
    
    void order(Order order);
}
