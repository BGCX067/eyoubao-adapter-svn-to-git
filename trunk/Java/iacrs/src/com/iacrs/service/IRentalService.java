package com.iacrs.service;

import com.iacrs.model.Pagination;
import com.iacrs.model.RentBillAdapter;

public interface IRentalService
{
    int READ_CARD_START_RENTAL = 0;
    
    int READ_CARD_END_RENTAL = 1;
    
    /**
     * 处理刷卡操作
     */
    Integer readCard(String idcardno, String gpsid);
    
    Pagination<RentBillAdapter> find(Integer userId, int pageNo, int pageSize);
    
    Pagination<RentBillAdapter> find(int pageNo, int pageSize);
}
