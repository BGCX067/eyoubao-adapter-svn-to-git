package com.mde.wbms.service.impl;

import com.mde.wbms.dao.IBillDAO;
import com.mde.wbms.dao.impl.BillDAOImpl;
import com.mde.wbms.model.BillDetail;
import com.mde.wbms.model.Pagination;
import com.mde.wbms.service.IBillService;

public class BillServiceImpl implements IBillService
{
    private IBillDAO dao = new BillDAOImpl();
    
    @Override
    public Pagination<BillDetail> find(int pageNo, int pageSize)
    {
        return dao.find(pageNo, pageSize);
    }
}
