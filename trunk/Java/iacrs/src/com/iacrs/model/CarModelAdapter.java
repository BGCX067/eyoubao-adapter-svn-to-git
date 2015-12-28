package com.iacrs.model;

import com.iacrs.entity.CarModel;
import com.iacrs.entity.CarModelPrice;

public class CarModelAdapter
{
    private CarModel model;
    
    private CarModelPrice price;
    
    public CarModel getModel()
    {
        return model;
    }
    
    public void setModel(CarModel model)
    {
        this.model = model;
    }
    
    public CarModelPrice getPrice()
    {
        return price;
    }
    
    public void setPrice(CarModelPrice price)
    {
        this.price = price;
    }
}
