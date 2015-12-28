package com.mde.model;

import java.util.ArrayList;
import java.util.List;

public class CookbookSearcher
{
    private Integer nosheryId;
    
    private Integer categoryId;
    
    public Queryer getQueryer()
    {
        StringBuffer hql = new StringBuffer(256);
        List<Object> parameters = new ArrayList<Object>();
        hql.append("FROM Cookbook c WHERE 1 = 1");
        addNosheryFilter(hql, parameters);
        addCategoryFilter(hql, parameters);
        Queryer queryer = new Queryer();
        queryer.setHql(hql.toString());
        queryer.setParameters(parameters);
        return queryer;
    }
    
    private void addNosheryFilter(StringBuffer hql, List<Object> parameters)
    {
        if (null != nosheryId)
        {
            hql.append(" AND c.nosheryId = ?");
            parameters.add(nosheryId);
        }
    }
    
    private void addCategoryFilter(StringBuffer hql, List<Object> parameters)
    {
        if (null != categoryId)
        {
            hql.append(" AND c.categoryId = ?");
            parameters.add(categoryId);
        }
    }
    
    public Integer getNosheryId()
    {
        return nosheryId;
    }
    
    public void setNosheryId(Integer nosheryId)
    {
        this.nosheryId = nosheryId;
    }
    
    public Integer getCategoryId()
    {
        return categoryId;
    }
    
    public void setCategoryId(Integer categoryId)
    {
        this.categoryId = categoryId;
    }
}
