package com.mde.wbms.dao;

import com.mde.wbms.model.BillDetail;
import com.mde.wbms.model.Pagination;

public interface IBillDAO
{
    Pagination<BillDetail> find(int pageNo, int pageSize);
}
