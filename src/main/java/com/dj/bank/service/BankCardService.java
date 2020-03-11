package com.dj.bank.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.bank.common.ResultModel;
import com.dj.bank.pojo.BankCard;
import com.dj.bank.pojo.BankLoans;
import com.dj.bank.pojo.BankUser;
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
public interface BankCardService extends IService<BankCard> {
    List<BankCard> findListByUserId(Integer status, Integer id) throws Exception;

    /**
     * 借款
     * @param bankCard
     * @param bankLoans
     */
    void updateBankCardAndSaveLoans(BankCard bankCard, BankLoans bankLoans) throws Exception;

    /**
     * 充值
     * @param balance
     * @param bankCardId
     * @param tradingRecord
     */
    void updateBankCardAndUpdateTradingRecord(Double balance, Integer bankCardId, TradingRecord tradingRecord) throws Exception;

    List<BankCard> findUserCard(Integer id) throws Exception;

    /**
     * 分期还款时间判断，改变状态
     * @param user
     * @throws Exception
     */
    ResultModel<Object> getBankCardAndGetLoansAndUpdateLoansByIdAndUpdateBankCardById(BankUser user) throws Exception;

    /**
     * 转账
     * @param bankCardOut
     * @param bankCardIn
     */
    ResultModel<Object> updateBankCardByIdAndSaveTradingRecord(BankCard bankCardOut, BankCard bankCardIn,
                                                               BankCard bankCard) throws Exception;
}
