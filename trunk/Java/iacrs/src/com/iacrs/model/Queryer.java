package com.iacrs.model;

import java.util.ArrayList;
import java.util.List;

public class Queryer
{
    private String hql;
    
    private List<Object> parameters = new ArrayList<Object>();
    
    public String getCountHql()
    {
        String formattedHql = hql.toLowerCase().replace("fetch", "");
        int fromIndex = formattedHql.indexOf("from");
        int orderbyIndex = formattedHql.indexOf("order by");
        String header = formattedHql.substring(0, fromIndex);
        String body = -1 != orderbyIndex ? hql.substring(fromIndex, orderbyIndex) : hql.substring(fromIndex);
        header = header.contains("select") ? header.replace("select", "select count(") + ") " : "select count(*) ";
        return header + body;
    }
    
    public String getHql()
    {
        return hql;
    }
    
    public void setHql(String hql)
    {
        this.hql = hql;
    }
    
    public List<Object> getParameters()
    {
        return parameters;
    }
    
    public void setParameters(List<Object> parameters)
    {
        this.parameters = parameters;
    }
}
