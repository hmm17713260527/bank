package com.dj.bank.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.bank.common.ResultModel;
import com.dj.bank.common.SystemConstant;
import com.dj.bank.pojo.BankLoans;
import com.dj.bank.service.LoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
