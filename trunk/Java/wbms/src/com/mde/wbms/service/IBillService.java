package com.mde.wbms.service;

import com.mde.wbms.model.BillDetail;
import com.mde.wbms.model.Pagination;

public interface IBillService
{
    Pagination<BillDetail> find(int pageNo, int pageSize);
}
