package com.mde.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mde.dao.BaseDaoSupport;
import com.mde.entity.Category;
import com.mde.entity.Cookbook;
import com.mde.entity.Noshery;
import com.mde.entity.User;
import com.mde.entity.UserArchive;
import com.mde.model.CookbookSearcher;
import com.mde.model.Pagination;
import com.mde.model.Queryer;
import com.mde.service.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService
{
    @Autowired
    private BaseDaoSupport baseDaoSupport;
    
    @Override
    public Pagination<User> findUser(int pageNo, int pageSize)
    {
        Queryer queryer = new Queryer();
        queryer.setHql("FROM User u WHERE u.type = 1");
        return baseDaoSupport.find(queryer, pageNo, pageSize, User.class);
    }
    
    @Override
    @Transactional
    public void deleteUser(Integer id)
    {
        User user = baseDaoSupport.get(User.class, id);
        
        if (null != user.getArchiveID())
        {
            baseDaoSupport.deleteByID(UserArchive.class, user.getArchiveID());
        }
        
        baseDaoSupport.deleteByID(User.class, id);
    }
    
    @Override
    public List<Noshery> findNoshery()
    {
        return baseDaoSupport.find(Noshery.class);
    }
    
    @Override
    public Pagination<Noshery> findNoshery(int pageNo, int pageSize)
    {
        Queryer queryer = new Queryer();
        queryer.setHql("FROM Noshery");
        return baseDaoSupport.find(queryer, pageNo, pageSize, Noshery.class);
    }
    
    @Override
    public Noshery getNoshery(Integer id)
    {
        return baseDaoSupport.get(Noshery.class, id);
    }
    
    @Override
    @Transactional
    public void addNoshery(Noshery data)
    {
        baseDaoSupport.insert(data);
    }
    
    @Override
    @Transactional
    public void modifyNoshery(Noshery data)
    {
        Noshery entity = getNoshery(data.getId());
        entity.setName(data.getName());
        entity.setPhone(data.getPhone());
        entity.setAddress(data.getAddress());
        entity.setImagePath(data.getImagePath());
        entity.setDescription(data.getDescription());
        baseDaoSupport.update(entity);
    }
    
    @Override
    @Transactional
    public void deleteNoshery(Integer id)
    {
        baseDaoSupport.deleteByID(Noshery.class, id);
    }
    
    @Override
    public List<Category> findCategory()
    {
        return baseDaoSupport.find(Category.class);
    }
    
    @Override
    public Pagination<Category> findCategory(int pageNo, int pageSize)
    {
        Queryer queryer = new Queryer();
        queryer.setHql("FROM Category");
        return baseDaoSupport.find(queryer, pageNo, pageSize, Category.class);
    }
    
    @Override
    public Category getCategory(Integer id)
    {
        return baseDaoSupport.get(Category.class, id);
    }
    
    @Override
    @Transactional
    public void addCategory(Category data)
    {
        baseDaoSupport.insert(data);
    }
    
    @Override
    @Transactional
    public void modifyCategory(Category data)
    {
        Category entity = getCategory(data.getId());
        entity.setName(data.getName());
        baseDaoSupport.update(entity);
    }
    
    @Override
    @Transactional
    public void deleteCategory(Integer id)
    {
        baseDaoSupport.deleteByID(Category.class, id);
    }
    
    @Override
    public Pagination<Cookbook> findCookbook(CookbookSearcher searcher, int pageNo, int pageSize)
    {
        return baseDaoSupport.find(searcher.getQueryer(), pageNo, pageSize, Cookbook.class);
    }
    
    @Override
    public Pagination<Cookbook> findCookbook(int pageNo, int pageSize)
    {
        Queryer queryer = new Queryer();
        queryer.setHql("FROM Cookbook");
        return baseDaoSupport.find(queryer, pageNo, pageSize, Cookbook.class);
    }
    
    @Override
    public Cookbook getCookbook(Integer id)
    {
        return baseDaoSupport.get(Cookbook.class, id);
    }
    
    @Override
    @Transactional
    public void addCookbook(Cookbook data)
    {
        baseDaoSupport.insert(data);
    }
    
    @Override
    @Transactional
    public void modifyCookbook(Cookbook data)
    {
        Cookbook entity = getCookbook(data.getId());
        entity.setNosheryId(data.getNosheryId());
        entity.setCategoryId(data.getCategoryId());
        entity.setName(data.getName());
        entity.setPrice(data.getPrice());
        entity.setImagePath(data.getImagePath());
        entity.setDescription(data.getDescription());
        baseDaoSupport.update(entity);
    }
    
    @Override
    @Transactional
    public void deleteCookbook(Integer id)
    {
        baseDaoSupport.deleteByID(Cookbook.class, id);
    }
}
