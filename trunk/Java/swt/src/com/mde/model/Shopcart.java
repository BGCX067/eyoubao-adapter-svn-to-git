package com.mde.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.mde.entity.Cookbook;

public class Shopcart
{
    private Map<Integer, ShopcartEntry> entries = new LinkedHashMap<Integer, ShopcartEntry>();
    
    public void add(Cookbook cookbook)
    {
        ShopcartEntry entry = entries.get(cookbook.getId());
        
        if (null == entry)
        {
            entry = new ShopcartEntry();
            entry.setCookbook(cookbook);
            entries.put(cookbook.getId(), entry);
        }
        
        entry.setCount(entry.getCount() + 1);
    }
    
    public List<ShopcartEntry> getEntries()
    {
        return new ArrayList<ShopcartEntry>(entries.values());
    }
}
