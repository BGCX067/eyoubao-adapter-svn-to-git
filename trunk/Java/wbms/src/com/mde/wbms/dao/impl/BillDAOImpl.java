package com.mde.wbms.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.mde.wbms.config.SystemConfig;
import com.mde.wbms.dao.IBillDAO;
import com.mde.wbms.dao.mapper.BillMapper;
import com.mde.wbms.model.BillDetail;
import com.mde.wbms.model.Pagination;

public class BillDAOImpl implements IBillDAO
{
    private static Logger log = LoggerFactory.getLogger(BillDAOImpl.class);
    
    private JdbcTemplate jdbc = SystemConfig.getInstance().getJdbcTemplate();
    
    @Override
    public Pagination<BillDetail> find(int pageNo, int pageSize)
    {
        int totalCount = jdbc.queryForInt("SELECT COUNT(*) FROM WBMS_BILL_DETAIL");
        Pagination<BillDetail> pagination = new Pagination<BillDetail>(pageNo, pageSize, totalCount);
        int minPageNo = 1;
        int maxPageNo = pagination.getTotalPage();
        pageNo = pageNo < minPageNo ? minPageNo : pageNo;
        pageNo = pageNo > maxPageNo ? maxPageNo : pageNo;
        pagination.setPageNo(pageNo);
        
        if (totalCount == 0)
        {
            List<BillDetail> records = Collections.emptyList();
            pagination.setRecords(records);
            return pagination;
        }
        
        final int offset = (pageNo - 1) * pageSize;
        final int limit = pageSize;
        
        log.info("PageNo:{}, PageSize:{}", pageNo, pageSize);
        log.info("offset:{}, limit:{}", offset, limit);
        
        List<BillDetail> records =
            jdbc.query("SELECT * FROM WBMS_BILL_DETAIL ORDER BY PURCHASE_NO limit ?,?", new PreparedStatementSetter()
            {
                @Override
                public void setValues(PreparedStatement ps)
                    throws SQLException
                {
                    int index = 1;
                    ps.setInt(index++, offset);
                    ps.setInt(index++, limit);
                }
            }, new BillMapper());
        pagination.setRecords(records);
        return pagination;
    }
}
