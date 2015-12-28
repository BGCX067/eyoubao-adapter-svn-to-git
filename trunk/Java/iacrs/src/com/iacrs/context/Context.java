package com.iacrs.context;

import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.iacrs.dao.BaseDaoSupport;
import com.iacrs.entity.Menu;
import com.iacrs.model.MenuData;
import com.iacrs.model.Tree;

@Component(value = "systemContext")
@Lazy(value = false)
public class Context
{
    private static Logger log = LoggerFactory.getLogger(Context.class);
    
    @Autowired
    private BaseDaoSupport baseDaoSupport;
    
    @Autowired
    private MessageSource messageSource;
    
    private Tree<MenuData> menuTree;
    
    @PostConstruct
    protected void init()
    {
        this.initMenuTree();
    }
    
    private void initMenuTree()
    {
        List<Menu> menus = baseDaoSupport.find(Menu.class);
        menuTree = new MenuTreeBuilder(menus).build();
    }
    
    public Tree<MenuData> getMenuTree()
    {
        return menuTree;
    }
    
    public String getMessage(String key, Object... objects)
    {
        try
        {
            return messageSource.getMessage(key, objects, Locale.getDefault());
        }
        catch (NoSuchMessageException e)
        {
            log.warn("Cannot found message for key: {}", key);
            return key;
        }
    }
}
