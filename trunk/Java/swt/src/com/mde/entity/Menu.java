package com.mde.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SWT_MENU")
public class Menu implements Serializable
{
    private static final long serialVersionUID = -2389779614412455550L;
    
    private String name;
    
    private String code;
    
    private String parentCode;
    
    private String forwardURI;
    
    private Integer sortNO;
    
    @Column(name = "NAME")
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    @Id
    @Column(name = "CODE")
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    @Column(name = "PARENT_CODE")
    public String getParentCode()
    {
        return parentCode;
    }
    
    public void setParentCode(String parentCode)
    {
        this.parentCode = parentCode;
    }
    
    @Column(name = "FORWARD_URI")
    public String getForwardURI()
    {
        return forwardURI;
    }
    
    public void setForwardURI(String forwardURI)
    {
        this.forwardURI = forwardURI;
    }
    
    @Column(name = "SORT_NO")
    public Integer getSortNO()
    {
        return sortNO;
    }
    
    public void setSortNO(Integer sortNO)
    {
        this.sortNO = sortNO;
    }
}
