package com.dj.bank.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.bank.common.ResultModel;
import com.dj.bank.common.SystemConstant;
import com.dj.bank.mapper.BankCardMapper;
import com.dj.bank.pojo.BankCard;
import com.dj.bank.pojo.BankLoans;
import com.dj.bank.pojo.BankUser;
import com.dj.bank.pojo.TradingRecord;
import com.dj.bank.service.BankCardService;
import com.dj.bank.service.LoansService;
import com.dj.bank.service.TradingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    @Lazy
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
        bankLoans.setPayMoneyMonth(bankLoans.getPayMoneyAll() / bankLoans.getPayMonthNumber())
        .setType(SystemConstant.TYPE);
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

    @Override
    public ResultModel<Object> getBankCardAndGetLoansAndUpdateLoansByIdAndUpdateBankCardById(BankUser user) throws Exception {
        QueryWrapper<BankCard> bankCardQueryWrapper = new QueryWrapper<>();
        bankCardQueryWrapper.eq("user_id", user.getId());
        List<BankCard> bankCardList = this.list(bankCardQueryWrapper);
        for (BankCard bankCard : bankCardList) {
            if (bankCard.getStatus().equals(SystemConstant.BANK_CARD_LOCK)) {
                return new ResultModel<>().error(SystemConstant.ACCOUNT_IS_FROZEN);
            }
        }

        //判断上个月已经还款的
        List<BankLoans> repaymentList = loansService.findRepaymentList(SystemConstant.DELETE_IS_DEL, user.getId());
        for (BankLoans bankLoans : repaymentList) {
            Integer i = loansService.findDate(bankLoans.getRepaymentTime());
            int time = SystemConstant.NOT_DELETE_IS_DEL + i;
            BankLoans bankLoan = loansService.findLoansStatus(bankLoans.getId());
            if (time > SystemConstant.TYPE && bankLoan != null) {
                UpdateWrapper<BankLoans> updateWrapper = new UpdateWrapper<>();
                updateWrapper.set("is_del", SystemConstant.NOT_DELETE_IS_DEL);
                updateWrapper.set("repayment_time", new Date());
                updateWrapper.eq("id", bankLoans.getId());
                loansService.update(updateWrapper);
            }
        }

        //判断上个月没还款的
        List<BankLoans> repaymentList2 = loansService.findRepaymentList(SystemConstant.NOT_DELETE_IS_DEL, user.getId());

        Integer count = SystemConstant.TYPE;

        for (BankLoans bankLoans : repaymentList2) {
            Integer i = loansService.findDate(bankLoans.getRepaymentTime()) + SystemConstant.NOT_DELETE_IS_DEL;
            int time = SystemConstant.NOT_DELETE_IS_DEL + i;
            BankCard card = this.getById(bankLoans.getBankCardId());
            BankLoans bankLoan = loansService.findLoansStatus(bankLoans.getId());
            if (time > SystemConstant.TYPE && bankLoan != null) {
                card.setStatus(SystemConstant.BANK_CARD_LOCK);
                count++;
                if (bankLoans.getType().equals(SystemConstant.TYPE)) {
                    int i1 = card.getReputationValue() - SystemConstant.BANK_TYPE_PID;
                    card.setReputationValue(i1);
                    bankLoans.setType(SystemConstant.CARD_integral_TYPE);
                }
            }
            loansService.updateById(bankLoans);
            this.updateById(card);
        }
        if (count > SystemConstant.TYPE) {
            return new ResultModel<>().error(SystemConstant.ACCOUNT_IS_FROZEN);
        }
        return new ResultModel<>().error(SystemConstant.WELCOME);
    }

    @Override
    public ResultModel<Object> updateBankCardByIdAndSaveTradingRecord(BankCard bankCardOut, BankCard bankCardIn,
                                                                      BankCard bankCard) {
        bankCardOut.setBalance(bankCardOut.getBalance() - bankCard.getBalance());
        this.updateById(bankCardOut);
        bankCardIn.setBalance(bankCardIn.getBalance() + bankCard.getBalance());
        this.updateById(bankCardIn);
        TradingRecord tradingRecordOut = new TradingRecord();
        tradingRecordOut.setUserId(bankCardOut.getUserId())
                .setUserCard(bankCardOut.getBankCardNumber())
                .setDealMoney("-" + bankCard.getBalance().toString())
                .setDealTime(SystemConstant.NOW_TIME)
                .setBalanceMoney(bankCardOut.getBalance())
                .setPayWay(SystemConstant.TRANSFER);
        TradingRecord tradingRecordIn = new TradingRecord();
        tradingRecordIn.setUserId(bankCardIn.getUserId())
                .setUserCard(bankCardIn.getBankCardNumber())
                .setDealMoney("+" + bankCard.getBalance().toString())
                .setDealTime(SystemConstant.NOW_TIME)
                .setBalanceMoney(bankCardIn.getBalance())
                .setPayWay(SystemConstant.TRANSFER);
        tradingRecordService.save(tradingRecordOut);
        tradingRecordService.save(tradingRecordIn);
        return new ResultModel<>().success();
    }
}
