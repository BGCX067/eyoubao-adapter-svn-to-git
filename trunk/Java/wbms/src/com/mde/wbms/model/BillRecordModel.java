package com.mde.wbms.model;

import com.mde.wbms.service.IBillService;
import com.mde.wbms.service.impl.BillServiceImpl;

public class BillRecordModel implements IPagerTablePanelModel
{
    private Pagination<BillDetail> pagination;
    
    private IBillService billService = new BillServiceImpl();
    
    public BillRecordModel()
    {
        reset(1, 1);
    }
    
    @Override
    public void reset(int pageNo, int pageSize)
    {
        this.pagination = billService.find(pageNo, pageSize);
    }
    
    @Override
    public Pagination<?> getCurrentPagination()
    {
        return pagination;
    }
}
