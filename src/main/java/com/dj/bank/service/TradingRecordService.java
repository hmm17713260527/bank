package com.dj.bank.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.bank.pojo.TradingRecord;

import java.util.List;

/**
 * @ProjectName: pms
 * @Package: com.dj.pms.service
 * @ClassName: UserService
 * @Author: Administrator
 * @Description:
 * @Date: 2020/1/31 13:09
 * @Version: 1.0
 */
public interface TradingRecordService extends IService<TradingRecord> {

    TradingRecord findLoans(Integer id) throws Exception;

    List<TradingRecord> findTradingByUserTypeAndUserId(Integer type, Integer id) throws Exception;
}
