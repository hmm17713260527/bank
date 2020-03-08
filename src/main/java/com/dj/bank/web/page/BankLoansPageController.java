package com.dj.bank.web.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.bank.pojo.*;
import com.dj.bank.service.BankCardService;
import com.dj.bank.service.BaseDataService;
import com.dj.bank.service.TradingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @ProjectName: bank
 * @Package: com.dj.bank.web.page
 * @ClassName: BankLoansPageController
 * @Author: Administrator
 * @Description:
 * @Date: 2020/3/7 17:25
 * @Version: 1.0
 */
@Controller
@RequestMapping("/bankLoans/")
public class BankLoansPageController {

    @Autowired
    private TradingRecordService tradingRecordService;

    @Autowired
    private BaseDataService baseDataService;

    @Autowired
    private BankCardService bankCardService;

    @RequestMapping("toUpdata/{id}")
    public ModelAndView toUpdata(@PathVariable("id") Integer id, @SessionAttribute("USER_SESSION") BankUser user) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("id",id);

        List<BankCard> cardList = bankCardService.findUserCard(user.getId());
        modelAndView.addObject("cardList",cardList);
        modelAndView.setViewName("bank_loans/update");

        return modelAndView;
    }

    @RequestMapping("toUpdateStatusShow")
    public String toUpdateStatusShow() {
        return "bank_loans/update_status_show";
    }

    @RequestMapping("/toRepaymentShow")
    public String toRepaymentShow() {
        return "bank_loans/repayment_show";
    }

    /**
     * 去审核
     */
    @RequestMapping("toUpdateStatus/{id}")
    public ModelAndView toUpdateStatus(@PathVariable("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        TradingRecord bankLoans = tradingRecordService.findLoans(id);
        modelAndView.addObject("bankLoans",bankLoans);
        modelAndView.setViewName("bank_loans/update_status");

        QueryWrapper<BaseData> baseDataQueryWrapper = new QueryWrapper<>();
        baseDataQueryWrapper.eq("parent_id", 15);
        List<BaseData> baseDataList = baseDataService.list(baseDataQueryWrapper);
        modelAndView.addObject("baseDataList",baseDataList);

        return modelAndView;
    }

}
