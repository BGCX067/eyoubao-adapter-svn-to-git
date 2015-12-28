package com.mde.model;

import com.mde.entity.Cookbook;

public class ShopcartEntry
{
    private Cookbook cookbook;
    
    private int count;
    
    public Cookbook getCookbook()
    {
        return cookbook;
    }
    
    public void setCookbook(Cookbook cookbook)
    {
        this.cookbook = cookbook;
    }
    
    public int getCount()
    {
        return count;
    }
    
    public void setCount(int count)
    {
        this.count = count;
    }
}
