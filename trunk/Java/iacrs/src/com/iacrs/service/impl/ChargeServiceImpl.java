package com.iacrs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iacrs.dao.BaseDaoSupport;
import com.iacrs.entity.ChargeRecord;
import com.iacrs.entity.UserAccount;
import com.iacrs.model.ChargeForm;
import com.iacrs.model.Pagination;
import com.iacrs.model.Queryer;
import com.iacrs.service.IChargeService;

@Service
public class ChargeServiceImpl implements IChargeService
{
    @Autowired
    private BaseDaoSupport baseDaoSupport;
    
    @Override
    @Transactional
    public void charge(ChargeForm data)
    {
        if (!data.isSuccess())
        {
            return;
        }
        
        ChargeRecord record = new ChargeRecord();
        record.setUserId(data.getUserId());
        record.setAmount(data.getAmount());
        record.setChargeTime(new Date());
        baseDaoSupport.insert(record);
        UserAccount account = baseDaoSupport.get(UserAccount.class, data.getUserId());
        Integer balance = account.getBalance() == null ? 0 : account.getBalance();
        account.setBalance(balance + data.getAmount());
        baseDaoSupport.update(account);
        
    }
    
    @Override
    public Pagination<ChargeRecord> find(Integer userId, int pageNo, int pageSize)
    {
        Queryer queryer = new Queryer();
        queryer.setHql("FROM ChargeRecord r WHERE r.userId = ? ORDER BY r.chargeTime DESC");
        List<Object> parameters = new ArrayList<Object>();
        parameters.add(userId);
        queryer.setParameters(parameters);
        return baseDaoSupport.find(queryer, pageNo, pageSize, ChargeRecord.class);
    }
}
