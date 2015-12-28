package com.iacrs.service;

import java.util.List;

import com.iacrs.entity.CarPosition;
import com.iacrs.model.CarAdapter;
import com.iacrs.model.CarForm;
import com.iacrs.model.CarModelAdapter;
import com.iacrs.model.CarModelForm;
import com.iacrs.model.Pagination;

public interface ICarService
{
    void addModel(CarModelForm form);
    
    void modifyModel(CarModelForm form);
    
    void deleteModel(Integer id);
    
    CarModelAdapter getCarModelAdapter(Integer id);
    
    Pagination<CarModelAdapter> find(int pageNo, int pageSize);
    
    void addCar(CarForm form);
    
    void modifyCar(CarForm form);
    
    void deleteCar(Integer id);
    
    CarAdapter getCarAdapter(Integer id);
    
    CarAdapter getCarAdapterByGPS(String gpsid);
    
    Pagination<CarAdapter> findCars(int pageNo, int pageSize);
    
    void setCarPosition(String gpsid, double longitude, double latitude);
    
    CarPosition getPosition(Integer id);
    
    List<CarPosition> findPositions(Integer id);
}
