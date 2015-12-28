package com.mde.model;

public class MenuData
{
    private String name;
    
    private String code;
    
    private String parentCode;
    
    private String forwardURI;
    
    private int sortNO;
    
    public MenuData()
    {
        //
    }
    
    public MenuData(String code, String parentCode, String name, String forwardURI, int sortNO)
    {
        this.code = code;
        this.parentCode = parentCode;
        this.name = name;
        this.forwardURI = forwardURI;
        this.sortNO = sortNO;
    }
    
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public String getParentCode()
    {
        return parentCode;
    }
    
    public void setParentCode(String parentCode)
    {
        this.parentCode = parentCode;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getForwardURI()
    {
        return forwardURI;
    }
    
    public void setForwardURI(String forwardURI)
    {
        this.forwardURI = forwardURI;
    }
    
    public int getSortNO()
    {
        return sortNO;
    }
    
    public void setSortNO(int sortNO)
    {
        this.sortNO = sortNO;
    }
}
