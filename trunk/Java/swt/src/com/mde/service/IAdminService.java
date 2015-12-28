package com.mde.service;

import java.util.List;

import com.mde.entity.Category;
import com.mde.entity.Cookbook;
import com.mde.entity.Noshery;
import com.mde.entity.User;
import com.mde.model.CookbookSearcher;
import com.mde.model.Pagination;

public interface IAdminService
{
    Pagination<User> findUser(int pageNo, int pageSize);
    
    void deleteUser(Integer id);
    
    List<Noshery> findNoshery();
    
    Pagination<Noshery> findNoshery(int pageNo, int pageSize);
    
    Noshery getNoshery(Integer id);
    
    void addNoshery(Noshery data);
    
    void modifyNoshery(Noshery data);
    
    void deleteNoshery(Integer id);
    
    List<Category> findCategory();
    
    Pagination<Category> findCategory(int pageNo, int pageSize);
    
    Category getCategory(Integer id);
    
    void addCategory(Category data);
    
    void modifyCategory(Category data);
    
    void deleteCategory(Integer id);
    
    Pagination<Cookbook> findCookbook(int pageNo, int pageSize);
    
    Pagination<Cookbook> findCookbook(CookbookSearcher searcher, int pageNo, int pageSize);
    
    Cookbook getCookbook(Integer id);
    
    void addCookbook(Cookbook data);
    
    void modifyCookbook(Cookbook data);
    
    void deleteCookbook(Integer id);
}
