package com.dj.bank.web.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.bank.common.SystemConstant;
import com.dj.bank.pojo.BaseData;
import com.dj.bank.service.BaseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ProjectName: bank
 * @Package: com.dj.bank.web.page
 * @ClassName: BankCardPageController
 * @Author: Liuwf
 * @Description: 银行卡
 * @Date: 2020/3/6 20:17
 * @Version: 1.0
 */
@Controller
@RequestMapping("/bankCard/")
public class BankCardPageController {
    @Autowired
    private BaseDataService baseDataService;

    @RequestMapping("toAdd")
    private String toAdd(Model model) {
        QueryWrapper<BaseData> baseDataQueryWrapper = new QueryWrapper<>();
        baseDataQueryWrapper.eq("parent_id", SystemConstant.BANK_TYPE_PID);
        List<BaseData> baseDataList = baseDataService.list(baseDataQueryWrapper);
        model.addAttribute("baseDataList",baseDataList);
        return "bank_card/bank_card_add";
    }


}
