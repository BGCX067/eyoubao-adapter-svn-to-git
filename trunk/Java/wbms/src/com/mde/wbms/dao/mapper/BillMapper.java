package com.mde.wbms.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mde.wbms.model.BillDetail;

public class BillMapper implements RowMapper<BillDetail>
{
    
    @Override
    public BillDetail mapRow(ResultSet rs, int index)
        throws SQLException
    {
        BillDetail bill = new BillDetail();
        bill.setPurchaseNo(rs.getString("PURCHASE_NO"));
        bill.setInvoiceNo(rs.getString("INVOICE_NO"));
        bill.setWaybillNo(rs.getString("WAYBILL_NO"));
        bill.setWeight(rs.getString("WEIGHT"));
        return bill;
    }
    
}
