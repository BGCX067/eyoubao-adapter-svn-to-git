package com.iacrs.model;

import com.iacrs.entity.RentBill;

public class RentBillAdapter
{
    private RentBill bill;
    
    private CarAdapter car;
    
    private UserModel user;
    
    public RentBill getBill()
    {
        return bill;
    }
    
    public void setBill(RentBill bill)
    {
        this.bill = bill;
    }
    
    public CarAdapter getCar()
    {
        return car;
    }
    
    public void setCar(CarAdapter car)
    {
        this.car = car;
    }
    
    public UserModel getUser()
    {
        return user;
    }
    
    public void setUser(UserModel user)
    {
        this.user = user;
    }
}
