package com.iacrs.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy(value = false)
public class ContextHolder implements ApplicationContextAware
{
    private static Context context;
    
    private static ApplicationContext applicationContext;
    
    public static Context getSystemContext()
    {
        return context;
    }
    
    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        ContextHolder.context = (Context)applicationContext.getBean("systemContext");
        ContextHolder.applicationContext = applicationContext;
    }
}
