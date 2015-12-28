package com.mde.model;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mde.util.ContextUtil;

public class PaginationModel
{
    public static final String MODEL_KEY = "paginationModel";
    
    public static final String PARAMETER_PAGE_NO = "pageNo";
    
    private Pagination<?> pagination;
    
    private boolean firstEnable;
    
    private boolean prevEnable;
    
    private boolean nextEnable;
    
    private boolean lastEnable;
    
    private String forwardPageUrl;
    
    private String forwardFirstURL;
    
    private String forwardPrevURL;
    
    private String forwardNextURL;
    
    private String forwardLastURL;
    
    public PaginationModel(Pagination<?> pagination, HttpServletRequest request)
    {
        if (null == pagination)
        {
            throw new IllegalArgumentException("pagination can not be null.");
        }
        
        this.pagination = pagination;
        this.firstEnable = !pagination.isFirstPage();
        this.prevEnable = !pagination.isFirstPage();
        this.nextEnable = !pagination.isLastPage();
        this.lastEnable = !pagination.isLastPage();
        String forwardUrlPrefix = getForwardURLPrefix(request);
        this.forwardFirstURL = forwardUrlPrefix + "1";
        this.forwardPrevURL = forwardUrlPrefix + pagination.getPrePage();
        this.forwardNextURL = forwardUrlPrefix + pagination.getNextPage();
        this.forwardLastURL = forwardUrlPrefix + pagination.getTotalPage();
        this.forwardPageUrl = forwardUrlPrefix;
    }
    
    private String getForwardURLPrefix(HttpServletRequest request)
    {
        Map<String, String> parameters = ContextUtil.getParameterMap(request);
        parameters.remove(PARAMETER_PAGE_NO);
        String parameterString = getParameterString(parameters);
        parameterString = "".equals(parameterString) ? "?pageNo=" : parameterString + "&pageNo=";
        return request.getRequestURL() + parameterString;
    }
    
    private String getParameterString(Map<String, String> parameters)
    {
        StringBuffer buf = new StringBuffer(64);
        
        for (Map.Entry<String, String> entry : parameters.entrySet())
        {
            buf.append(String.format("&%1$s=%2$s", entry.getKey(), entry.getValue()));
        }
        
        if (buf.length() > 0)
        {
            buf.replace(0, 1, "?");
        }
        
        return buf.toString();
    }
    
    public Pagination<?> getPagination()
    {
        return pagination;
    }
    
    public String getForwardPageUrl()
    {
        return forwardPageUrl;
    }
    
    public String getForwardFirstURL()
    {
        return forwardFirstURL;
    }
    
    public String getForwardPrevURL()
    {
        return forwardPrevURL;
    }
    
    public String getForwardNextURL()
    {
        return forwardNextURL;
    }
    
    public String getForwardLastURL()
    {
        return forwardLastURL;
    }
    
    public boolean isFirstEnable()
    {
        return firstEnable;
    }
    
    public boolean isPrevEnable()
    {
        return prevEnable;
    }
    
    public boolean isNextEnable()
    {
        return nextEnable;
    }
    
    public boolean isLastEnable()
    {
        return lastEnable;
    }
}
