package com.dj.bank.web;

import com.dj.bank.common.ResultModel;
import com.dj.bank.common.SystemConstant;
import com.dj.bank.pojo.BankUser;
import com.dj.bank.pojo.TradingRecord;
import com.dj.bank.service.TradingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ProjectName: bank
 * @Package: com.dj.bank.web
 * @ClassName: TradingRecordController
 * @Author: Administrator
 * @Description:
 * @Date: 2020/3/7 18:14
 * @Version: 1.0
 */
@RestController
@RequestMapping("/trading/")
public class TradingRecordController {

    @Autowired
    private TradingRecordService tradingRecordService;

    /**
     * 交易信息
     * @param
     * @return
     */
    @GetMapping("list")
    public ResultModel<Object> list(HttpSession session) {
        try {
            BankUser user = (BankUser) session.getAttribute(SystemConstant.USER_SESSION);
            List<TradingRecord> tradingRecordList = tradingRecordService.findTradingByUserTypeAndUserId(user.getType(), user.getId());
            return new ResultModel<Object>().success(tradingRecordList);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.ERROR + e.getMessage());
        }
    }
}
