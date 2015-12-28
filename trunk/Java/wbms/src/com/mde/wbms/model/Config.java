package com.mde.wbms.model;

public class Config
{
    private String waybillPath;
    
    private String workPath;
    
    private String mergePath;
    
    public String getWaybillPath()
    {
        return waybillPath;
    }
    
    public void setWaybillPath(String waybillPath)
    {
        this.waybillPath = waybillPath;
    }
    
    public String getMergePath()
    {
        return mergePath;
    }
    
    public void setMergePath(String mergePath)
    {
        this.mergePath = mergePath;
    }
    
    public String getWorkPath()
    {
        return workPath;
    }
    
    public void setWorkPath(String workPath)
    {
        this.workPath = workPath;
    }
}
