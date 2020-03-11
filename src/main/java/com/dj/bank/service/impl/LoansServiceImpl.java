package com.dj.bank.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.bank.common.ResultModel;
import com.dj.bank.common.SystemConstant;
import com.dj.bank.mapper.LoansMapper;
import com.dj.bank.pojo.BankCard;
import com.dj.bank.pojo.BankLoans;
import com.dj.bank.pojo.BankUser;
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
public class LoansServiceImpl extends ServiceImpl<LoansMapper, BankLoans> implements LoansService {

    @Autowired
    private TradingRecordService tradingRecordService;

    @Autowired
    private BankCardService bankCardService;

    @Override
    public List<BankLoans> findLoans(Integer status) throws Exception {
        return this.baseMapper.findLoans(status);
    }

    @Override
    public List<BankLoans> findRepaymentList(Integer isDel, Integer id) throws Exception {
        return this.baseMapper.findRepaymentList(isDel, id);
    }

    @Override
    public Integer findDate(Date repaymentTime) throws Exception {
        return this.baseMapper.findDate(repaymentTime);
    }

    @Override
    public BankLoans findLoansStatus(Integer id) throws Exception {
        return this.baseMapper.findLoansStatus(id);
    }

    @Override
    public Double findPayMoneyAllSum(Integer carId) throws Exception {
        return this.baseMapper.findPayMoneyAllSum(carId);
    }

    @Override
    public List<BankLoans> findLoansList(Integer isDel, Integer id) throws Exception {
        return this.baseMapper.findLoansList(isDel, id);
    }

    @Override
    public ResultModel<Object> getLoansAndGetBankCardAndUpdateLoansAndUpdateBankCardAndSaveTradingRecord(Integer loansId, Integer carId, BankUser user) throws Exception {
        BankLoans bankLoans = this.getById(loansId);
        BankCard bankCard = bankCardService.getById(carId);
        if (bankCard.getBalance() < bankLoans.getPayMoneyMonth()) {
            return new ResultModel<>().error(SystemConstant.NOT_SUFFICIENT_FUNDS);
        }

        UpdateWrapper<BankCard> updateWrapper = new UpdateWrapper<>();
        double v = bankCard.getBalance() - bankLoans.getPayMoneyMonth();
        int i = bankCard.getReputationValue() + SystemConstant.CREDIT_INTEGRAL;
        int i1 = bankCard.getIntegral() + SystemConstant.INTEGRAL;
        updateWrapper.set("balance", v).set("reputation_value", i).set("integral", i1);
        updateWrapper.eq("id",bankCard.getId());

        UpdateWrapper<BankLoans> updateWrapper1 = new UpdateWrapper<>();
        int i2 = bankLoans.getPayMonthNumber() - SystemConstant.CREDIT_INTEGRAL;
        updateWrapper1.set("is_del", SystemConstant.DELETE_IS_DEL).set("pay_month_number", i2).set("repayment_time", new Date()).set("type",SystemConstant.NOT_DELETE_IS_DEL);
        updateWrapper1.eq("id", bankLoans.getId());



        if (i <= 60) {
            if (i2 == 0) {
                updateWrapper.set("borrow_balance", bankCard.getBorrowBalance() + bankLoans.getPayMoneyAll());
                updateWrapper1.set("is_del", 3);
            }
            this.update(updateWrapper1);
            bankCardService.update(updateWrapper);
        }

        //信誉值改变，对应贷款改变
        if (60 < i && i < 81) {
            if (i2 == 0) {
                updateWrapper.set("borrow_balance", bankCard.getBorrowBalance() + bankLoans.getPayMoneyAll());
                updateWrapper1.set("is_del", 3);
            }
            //可贷款50000
            Double payMoney = this.findPayMoneyAllSum(bankCard.getId());
            double v1 = 50000 - payMoney;
            updateWrapper.set("borrow_balance", v1);
            this.update(updateWrapper1);
            bankCardService.update(updateWrapper);
        }

        if (80 < i && i < 101) {
            if (i2 == 0) {
                updateWrapper.set("borrow_balance", bankCard.getBorrowBalance() + bankLoans.getPayMoneyAll());
                updateWrapper1.set("is_del", 3);
            }
            //可贷款100000
            Double payMoney = this.findPayMoneyAllSum(bankCard.getId());
            double v1 = 100000 - payMoney;
            updateWrapper.set("borrow_balance", v1);
            bankCardService.update(updateWrapper);
            this.update(updateWrapper1);

        }

        if (100 < i) {
            if (i2 == 0) {
                updateWrapper.set("borrow_balance", bankCard.getBorrowBalance() + bankLoans.getPayMoneyAll());
                updateWrapper1.set("is_del", 3);
            }
            //可贷款200000
            Double payMoney = this.findPayMoneyAllSum(bankCard.getId());
            double v1 = 200000 - payMoney;
            updateWrapper.set("borrow_balance", v1);
            this.update(updateWrapper1);
            bankCardService.update(updateWrapper);

        }

        double c = bankLoans.getPayMoneyMonth();
        String str = "-" + c;
        tradingRecordService.save(new TradingRecord().setUserId(user.getId()).setUserCard(bankCard.getBankCardNumber())
                .setDealMoney(str).setDealTime(new Date())
                .setBalanceMoney(v).setPayWay("还款"));
        return new ResultModel<>().success();
    }

    @Override
    public ResultModel<Object> updateLoansAndUpdateBankCardAndSaveTradingRecord(TradingRecord tradingRecord) {
        //审核通过
        if (tradingRecord.getStatus().equals(SystemConstant.LOANS_STATUS)) {
            UpdateWrapper<BankLoans> wrapper = new UpdateWrapper<BankLoans>();
            wrapper.set("status",tradingRecord.getStatus());
            wrapper.eq("id", tradingRecord.getId());
            this.update(wrapper);



            UpdateWrapper<BankCard> updateWrapper = new UpdateWrapper<BankCard>();
            Double money = tradingRecord.getBalanceMoney() +  Double.parseDouble(tradingRecord.getDealMoney());
            updateWrapper.set("balance", money);
            updateWrapper.eq("id", tradingRecord.getCarId());
            bankCardService.update(updateWrapper);

            String dealMoney = tradingRecord.getDealMoney();
            String s = "+" + dealMoney;
            tradingRecord.setId(null).setDealMoney(s).setDealTime(new Date()).setPayWay("借款").setBalanceMoney(money);
            tradingRecordService.save(tradingRecord);

        }

        return new ResultModel<>().success();
    }


}
