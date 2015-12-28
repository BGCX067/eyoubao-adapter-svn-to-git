package com.mde.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "SWT_USER")
public class User implements Serializable
{
    public static int TYPE_ADMIN = 0;
    
    public static int TYPE_USER = 1;
    
    private static final long serialVersionUID = 6441325039970830126L;
    
    private Integer id;
    
    private String username;
    
    private String password;
    
    private Integer type;
    
    private boolean disabled;
    
    private Integer archiveID;
    
    private UserArchive userArchive;
    
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    @Column(name = "USERNAME")
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    @Column(name = "PASSWORD")
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    @Column(name = "TYPE")
    public Integer getType()
    {
        return type;
    }
    
    public void setType(Integer type)
    {
        this.type = type;
    }
    
    @Column(name = "DISABLED")
    public boolean getDisabled()
    {
        return disabled;
    }
    
    public void setDisabled(boolean disabled)
    {
        this.disabled = disabled;
    }
    
    @Column(name = "ARCHIVE_ID")
    public Integer getArchiveID()
    {
        return archiveID;
    }
    
    public void setArchiveID(Integer archiveID)
    {
        this.archiveID = archiveID;
    }
    
    @OneToOne(targetEntity = UserArchive.class, fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "ARCHIVE_ID", insertable = false, updatable = false)
    public UserArchive getUserArchive()
    {
        return userArchive;
    }
    
    public void setUserArchive(UserArchive userArchive)
    {
        this.userArchive = userArchive;
    }
}
