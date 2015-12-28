package com.iacrs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iacrs.dao.BaseDaoSupport;
import com.iacrs.entity.Car;
import com.iacrs.entity.CarModel;
import com.iacrs.entity.CarModelPrice;
import com.iacrs.entity.CarPosition;
import com.iacrs.model.CarAdapter;
import com.iacrs.model.CarForm;
import com.iacrs.model.CarModelAdapter;
import com.iacrs.model.CarModelForm;
import com.iacrs.model.Pagination;
import com.iacrs.model.Queryer;
import com.iacrs.service.ICarService;

@Service
public class CarServiceImpl implements ICarService
{
    
    @Autowired
    private BaseDaoSupport baseDaoSupport;
    
    @Override
    @Transactional
    public void addModel(CarModelForm form)
    {
        CarModel model = new CarModel();
        model.setName(form.getName());
        model.setCategory(form.getCategory());
        model.setBrand(form.getBrand());
        model.setStyle(form.getStyle());
        model.setCoachType(form.getCoachType());
        model.setGearType(form.getGearType());
        model.setSweptVolume(form.getSweptVolume());
        model.setImagePath(form.getImagePath());
        baseDaoSupport.insert(model);
        CarModelPrice price = new CarModelPrice();
        price.setCarModelId(model.getId());
        price.setDailyRental(form.getDailyRental());
        price.setDailyPremium(form.getDailyPremium());
        price.setPreauth(form.getPreauth());
        baseDaoSupport.insert(price);
    }
    
    @Override
    @Transactional
    public void modifyModel(CarModelForm form)
    {
        CarModel model = baseDaoSupport.get(CarModel.class, form.getId());
        model.setName(form.getName());
        model.setCategory(form.getCategory());
        model.setBrand(form.getBrand());
        model.setStyle(form.getStyle());
        model.setCoachType(form.getCoachType());
        model.setGearType(form.getGearType());
        model.setSweptVolume(form.getSweptVolume());
        model.setImagePath(form.getImagePath());
        baseDaoSupport.update(model);
        CarModelPrice price = baseDaoSupport.get(CarModelPrice.class, form.getId());
        price.setDailyRental(form.getDailyRental());
        price.setDailyPremium(form.getDailyPremium());
        price.setPreauth(form.getPreauth());
        baseDaoSupport.update(price);
    }
    
    @Override
    @Transactional
    public void deleteModel(Integer id)
    {
        baseDaoSupport.deleteByID(CarModelPrice.class, id);
        baseDaoSupport.deleteByID(CarModel.class, id);
    }
    
    @Override
    public CarModelAdapter getCarModelAdapter(Integer id)
    {
        String hql = "FROM CarModel cm, CarModelPrice cmr WHERE cmr.carModelId = cm.id AND cm.id = ?";
        List<?> records = baseDaoSupport.find(hql, id);
        
        if (records.isEmpty())
        {
            return null;
        }
        
        return wrap((Object[])records.get(0));
    }
    
    @Override
    public Pagination<CarModelAdapter> find(int pageNo, int pageSize)
    {
        String hql = "FROM CarModel cm, CarModelPrice cmr WHERE cmr.carModelId = cm.id";
        Queryer queryer = new Queryer();
        queryer.setHql(hql);
        Pagination<Object[]> pagination = baseDaoSupport.find(queryer, pageNo, pageSize, Object[].class);
        List<Object[]> records = pagination.getRecords();
        List<CarModelAdapter> wrapperRecords = new ArrayList<CarModelAdapter>();
        
        for (Object[] record : records)
        {
            wrapperRecords.add(wrap(record));
        }
        
        Pagination<CarModelAdapter> result =
            new Pagination<CarModelAdapter>(pagination.getPageNo(), pagination.getPageSize(),
                pagination.getTotalCount());
        result.setRecords(wrapperRecords);
        return result;
    }
    
    private CarModelAdapter wrap(Object[] record)
    {
        CarModelAdapter model = new CarModelAdapter();
        model.setModel((CarModel)record[0]);
        model.setPrice((CarModelPrice)record[1]);
        return model;
    }
    
    @Override
    @Transactional
    public void addCar(CarForm form)
    {
        Car car = new Car();
        car.setModelId(form.getModelId());
        car.setDailyRental(form.getDailyRental());
        car.setDailyPremium(form.getDailyPremium());
        car.setPreauth(form.getPreauth());
        car.setLicencePlate(form.getLicencePlate());
        car.setGpsid(form.getGpsid());
        baseDaoSupport.insert(car);
        CarPosition position = new CarPosition();
        position.setCarId(car.getId());
        position.setLongitude(116.3277D);
        position.setLatitude(39.8997D);
        position.setTime(new Date());
        baseDaoSupport.insert(position);
    }
    
    @Override
    @Transactional
    public void modifyCar(CarForm form)
    {
        Car car = baseDaoSupport.get(Car.class, form.getId());
        car.setModelId(form.getModelId());
        car.setDailyRental(form.getDailyRental());
        car.setDailyPremium(form.getDailyPremium());
        car.setPreauth(form.getPreauth());
        car.setLicencePlate(form.getLicencePlate());
        car.setGpsid(form.getGpsid());
        baseDaoSupport.update(car);
    }
    
    @Override
    @Transactional
    public void deleteCar(Integer id)
    {
        baseDaoSupport.deleteByID(Car.class, id);
    }
    
    @Override
    public CarAdapter getCarAdapter(Integer id)
    {
        String hql =
            "FROM Car c, CarModel cm, CarModelPrice cmr WHERE cmr.carModelId = cm.id AND c.modelId = cm.id AND c.id = ?";
        List<?> records = baseDaoSupport.find(hql, id);
        
        if (records.isEmpty())
        {
            return null;
        }
        
        return wrapCar((Object[])records.get(0));
    }
    
    @Override
    public CarAdapter getCarAdapterByGPS(String gpsid)
    {
        String hql =
            "FROM Car c, CarModel cm, CarModelPrice cmr WHERE cmr.carModelId = cm.id AND c.modelId = cm.id AND c.gpsid = ?";
        List<?> records = baseDaoSupport.find(hql, gpsid);
        
        if (records.isEmpty())
        {
            return null;
        }
        
        return wrapCar((Object[])records.get(0));
    }
    
    @Override
    public Pagination<CarAdapter> findCars(int pageNo, int pageSize)
    {
        String hql = "FROM Car c, CarModel cm, CarModelPrice cmr WHERE cmr.carModelId = cm.id AND c.modelId = cm.id";
        Queryer queryer = new Queryer();
        queryer.setHql(hql);
        Pagination<Object[]> pagination = baseDaoSupport.find(queryer, pageNo, pageSize, Object[].class);
        List<Object[]> records = pagination.getRecords();
        List<CarAdapter> wrapperRecords = new ArrayList<CarAdapter>();
        
        for (Object[] record : records)
        {
            wrapperRecords.add(wrapCar(record));
        }
        
        Pagination<CarAdapter> result =
            new Pagination<CarAdapter>(pagination.getPageNo(), pagination.getPageSize(), pagination.getTotalCount());
        result.setRecords(wrapperRecords);
        return result;
    }
    
    private CarAdapter wrapCar(Object[] record)
    {
        CarAdapter model = new CarAdapter();
        model.setCar((Car)record[0]);
        CarModelAdapter modelAdapter = new CarModelAdapter();
        modelAdapter.setModel((CarModel)record[1]);
        modelAdapter.setPrice((CarModelPrice)record[2]);
        model.setModelAdapter(modelAdapter);
        return model;
    }
    
    @Override
    @Transactional
    public void setCarPosition(String gpsid, double longitude, double latitude)
    {
        CarAdapter car = getCarAdapterByGPS(gpsid);
        
        if (null == car)
        {
            return;
        }
        
        CarPosition position = new CarPosition();
        position.setCarId(car.getCar().getId());
        position.setLongitude(longitude);
        position.setLatitude(latitude);
        position.setTime(new Date());
        baseDaoSupport.insert(position);
    }
    
    @Override
    public CarPosition getPosition(Integer id)
    {
        String hql = "FROM CarPosition p WHERE p.carId = ? ORDER BY p.time DESC";
        Queryer queryer = new Queryer();
        queryer.setHql(hql);
        List<Object> parameters = new ArrayList<Object>();
        parameters.add(id);
        queryer.setParameters(parameters);
        Pagination<CarPosition> pagination = baseDaoSupport.find(queryer, 1, 1, CarPosition.class);
        return pagination.getRecords().isEmpty() ? null : pagination.getRecords().get(0);
    }
    
    @Override
    public List<CarPosition> findPositions(Integer id)
    {
        String hql = "FROM CarPosition p WHERE p.carId = ? ORDER BY p.time DESC";
        Queryer queryer = new Queryer();
        queryer.setHql(hql);
        List<Object> parameters = new ArrayList<Object>();
        parameters.add(id);
        queryer.setParameters(parameters);
        Pagination<CarPosition> pagination = baseDaoSupport.find(queryer, 1, 20, CarPosition.class);
        return pagination.getRecords();
    }
}
