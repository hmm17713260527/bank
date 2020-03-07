package com.dj.bank.web.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.bank.pojo.BankCard;
import com.dj.bank.pojo.BankLoans;
import com.dj.bank.pojo.BaseData;
import com.dj.bank.pojo.TradingRecord;
import com.dj.bank.service.BaseDataService;
import com.dj.bank.service.TradingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("toUpdateStatusShow")
    private String toUpdateStatusShow() {
        return "bank_loans/update_status_show";
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
