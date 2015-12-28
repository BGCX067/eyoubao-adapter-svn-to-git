package com.mde.context;

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

import com.mde.dao.BaseDaoSupport;
import com.mde.entity.Menu;
import com.mde.model.MenuData;
import com.mde.model.Tree;
import com.mde.service.impl.MenuTreeBuilder;

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
