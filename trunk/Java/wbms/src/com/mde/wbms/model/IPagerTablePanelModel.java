package com.mde.wbms.model;

public interface IPagerTablePanelModel 
{
    void reset(int pageNo, int pageSize);
    
    Pagination<?> getCurrentPagination();
}
