package com.dj.bank.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.bank.mapper.TradingRecordMapper;
import com.dj.bank.pojo.TradingRecord;
import com.dj.bank.service.TradingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName: pms
 * @Package: com.dj.pms.service.impl
 * @ClassName: UserServiceImpl
 * @Author: Administrator
 * @Description:
 * @Date: 2020/1/31 13:10
 * @Version: 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TradingRecordServiceImpl extends ServiceImpl<TradingRecordMapper, TradingRecord> implements TradingRecordService {

    @Autowired
    private TradingRecordMapper tradingRecordMapper;

    @Override
    public TradingRecord findLoans(Integer id) throws Exception {
        return this.baseMapper.findLoans(id);
    }

    @Override
    public List<TradingRecord> findTradingByUserTypeAndUserId(Integer type, Integer id) throws Exception {
        return tradingRecordMapper.findTradingByUserTypeAndUserId(type, id);
    }
}
