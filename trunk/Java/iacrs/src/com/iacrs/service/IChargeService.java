package com.iacrs.service;

import com.iacrs.entity.ChargeRecord;
import com.iacrs.model.ChargeForm;
import com.iacrs.model.Pagination;

public interface IChargeService
{
    void charge(ChargeForm data);
    
    Pagination<ChargeRecord> find(Integer userId, int pageNo, int pageSize);
}
