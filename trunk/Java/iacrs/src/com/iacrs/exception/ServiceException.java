package com.iacrs.exception;

public class ServiceException extends RuntimeException
{
    private static final long serialVersionUID = -4140846493380170594L;
    
    private String code;
    
    private String errorMessage;
    
    public ServiceException(String code)
    {
        this.code = code;
    }
    
    public String getCode()
    {
        return code;
    }
    
    public String getErrorMessage()
    {
        return errorMessage;
    }
    
    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }
}
