package com.dj.bank.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.bank.common.ResultModel;
import com.dj.bank.pojo.BankLoans;
import com.dj.bank.pojo.BankUser;
import com.dj.bank.pojo.TradingRecord;

import java.util.Date;
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
public interface LoansService extends IService<BankLoans> {
    List<BankLoans> findLoans(Integer status) throws Exception;

    List<BankLoans> findRepaymentList(Integer isDel, Integer id) throws Exception;

    Integer findDate(Date repaymentTime) throws Exception;

    BankLoans findLoansStatus(Integer id) throws Exception;

    Double findPayMoneyAllSum(Integer carId) throws Exception;

    List<BankLoans> findLoansList(Integer isDel, Integer id) throws Exception;

    /**
     * 还款
     * @param loansId
     * @param carId
     * @param user
     */
    ResultModel<Object> getLoansAndGetBankCardAndUpdateLoansAndUpdateBankCardAndSaveTradingRecord(Integer loansId,
                                                                                                  Integer carId,
                                                                                                  BankUser user
    ) throws Exception;

    /**
     * 审核
     * @param tradingRecord
     */
    ResultModel<Object> updateLoansAndUpdateBankCardAndSaveTradingRecord(TradingRecord tradingRecord);
}
