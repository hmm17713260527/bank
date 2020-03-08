package com.dj.bank.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.bank.common.SystemConstant;
import com.dj.bank.mapper.BankCardMapper;
import com.dj.bank.pojo.BankCard;
import com.dj.bank.pojo.BankLoans;
import com.dj.bank.pojo.TradingRecord;
import com.dj.bank.service.BankCardService;
import com.dj.bank.service.LoansService;
import com.dj.bank.service.TradingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
public class BankCardServiceImpl extends ServiceImpl<BankCardMapper, BankCard> implements BankCardService {


    @Autowired
    private LoansService loansService;

    @Autowired
    private TradingRecordService tradingRecordService;

    @Override
    public List<BankCard> findListByUserId(Integer status, Integer id) {
        return this.baseMapper.findListByUserId(status,id);
    }

    @Override
    public void updateBankCardAndSaveLoans(BankCard bankCard, BankLoans bankLoans) throws Exception {
        //修改银行卡剩余可借金额
        UpdateWrapper<BankCard> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("borrow_balance", bankCard.getBorrowBalance() - bankLoans.getPayMoneyAll());
        updateWrapper.eq("id", bankLoans.getBankCardId());
        this.update(updateWrapper);
        //借款进行新增
        bankLoans.setPayMoneyMonth(bankLoans.getPayMoneyAll() / bankLoans.getPayMonthNumber());
        loansService.save(bankLoans);
    }

    @Override
    public void updateBankCardAndUpdateTradingRecord(Double balance, Integer bankCardId, TradingRecord tradingRecord) throws Exception {
        UpdateWrapper<BankCard> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("balance", balance + Double.valueOf(tradingRecord.getDealMoney()));
        updateWrapper.eq("id", bankCardId);
        this.update(updateWrapper);
        tradingRecord.setDealTime(new Date());
        tradingRecord.setDealMoney("+"+tradingRecord.getDealMoney());
        tradingRecord.setBalanceMoney(balance + Double.valueOf(tradingRecord.getDealMoney()));
        tradingRecord.setPayWay(SystemConstant.ADD);
        tradingRecordService.save(tradingRecord);
    }

    @Override
    public List<BankCard> findUserCard(Integer id) throws Exception {
        return this.baseMapper.findUserCard(id);
    }
}
