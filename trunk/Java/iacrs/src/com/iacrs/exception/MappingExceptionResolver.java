package com.iacrs.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.iacrs.context.ContextHolder;
import com.iacrs.util.ActionUtil;

public class MappingExceptionResolver extends SimpleMappingExceptionResolver
{
    private static Logger log = LoggerFactory.getLogger(MappingExceptionResolver.class);
    
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    {
        log.error(ex.getMessage(), ex);
        
        ServiceException wrapped;
        
        if (ex instanceof ServiceException)
        {
            wrapped = (ServiceException)ex;
        }
        else
        {
            wrapped = new ServiceException(ServiceExceptionCode.IACRS_0001);
        }
        
        wrapped.setErrorMessage(ContextHolder.getSystemContext().getMessage(wrapped.getCode()));
        ModelAndView modelAndView = super.doResolveException(request, response, handler, wrapped);
        
        if (null != modelAndView)
        {
            ActionUtil.fillCommonAttributes(request, modelAndView.getModelMap());
        }
        
        return modelAndView;
    }
}
