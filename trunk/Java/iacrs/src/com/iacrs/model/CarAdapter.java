package com.iacrs.model;

import com.iacrs.entity.Car;

public class CarAdapter
{
    private Car car;
    
    private CarModelAdapter modelAdapter;
    
    public Car getCar()
    {
        return car;
    }
    
    public void setCar(Car car)
    {
        this.car = car;
    }
    
    public CarModelAdapter getModelAdapter()
    {
        return modelAdapter;
    }
    
    public void setModelAdapter(CarModelAdapter modelAdapter)
    {
        this.modelAdapter = modelAdapter;
    }
}
