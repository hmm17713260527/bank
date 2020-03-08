package com.dj.bank.web;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dj.bank.common.ResultModel;
import com.dj.bank.common.SystemConstant;
import com.dj.bank.pojo.BankCard;
import com.dj.bank.pojo.BankLoans;
import com.dj.bank.pojo.BankUser;
import com.dj.bank.pojo.TradingRecord;
import com.dj.bank.service.BankCardService;
import com.dj.bank.service.LoansService;
import com.dj.bank.service.TradingRecordService;
import com.dj.bank.util.MessageVerifyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: bank
 * @Package: com.dj.bank.web
 * @ClassName: BankLoansController
 * @Author: Administrator
 * @Description:
 * @Date: 2020/3/7 17:25
 * @Version: 1.0
 */
@RestController
@RequestMapping("/bankLoans/")
public class BankLoansController {


    @Autowired
    private LoansService loansService;

    @Autowired
    private TradingRecordService tradingRecordService;

    @Autowired
    private BankCardService bankCardService;

    /**
     * 银行卡展示
     * @return
     */
    @GetMapping("bankCardList")
    public ResultModel<Object> bankCardList(Integer status) {
        try {
            List<BankLoans> BankLoansList = loansService.findLoans(status);
            return new ResultModel<>().success(BankLoansList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.EXCEPTION);
        }
    }

    /**
     * 还款展示
     * @param user
     * @param isDel
     * @return
     */
    @GetMapping("repaymentList")
    public ResultModel<Object> repaymentList(@SessionAttribute("USER_SESSION") BankUser user, Integer isDel) {
        try {
            List<BankLoans> BankLoansList = loansService.findRepaymentList(isDel, user.getId());
            return new ResultModel<>().success(BankLoansList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.EXCEPTION);
        }
    }



    @PutMapping("updateStatusById")
    public ResultModel<Object> updateStatusById(TradingRecord tradingRecord) {
        try {
            //审核未通过
            if (tradingRecord.getStatus() == 18) {
                MessageVerifyUtils.sendSms(tradingRecord.getPhone(), "您的借款审核未通过");
            }
            //审核通过
            if (tradingRecord.getStatus() == 17) {
                UpdateWrapper<BankLoans> wrapper = new UpdateWrapper<BankLoans>();
                wrapper.set("status",tradingRecord.getStatus());
                wrapper.eq("id", tradingRecord.getId());
                loansService.update(wrapper);

                String dealMoney = tradingRecord.getDealMoney();
                String s = "+" + dealMoney;
                tradingRecord.setId(null).setDealMoney(s).setDealTime(new Date()).setPayWay("借款");
                tradingRecordService.save(tradingRecord);

                UpdateWrapper<BankCard> updateWrapper = new UpdateWrapper<BankCard>();
                Double money = tradingRecord.getBalanceMoney() +  Double.parseDouble(tradingRecord.getDealMoney());
                updateWrapper.set("balance", money);
                updateWrapper.eq("id", tradingRecord.getCarId());
                bankCardService.update(updateWrapper);

            }
            return new ResultModel<>().success();
        }catch (Exception e){
            e.printStackTrace();
            return  new ResultModel<>().error(SystemConstant.EXCEPTION + e.getMessage());
        }

    }


}
