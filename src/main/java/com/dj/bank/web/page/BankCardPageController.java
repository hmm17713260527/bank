package com.dj.bank.web.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.bank.common.SystemConstant;
import com.dj.bank.pojo.BankCard;
import com.dj.bank.pojo.BaseData;
import com.dj.bank.service.BankCardService;
import com.dj.bank.service.BaseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private BankCardService bankCardService;
    /**
     * @Description:去申请银行卡页面
     * @Author: Liuwf
     * @Date:
     * @param model:
     * @return: java.lang.String
     **/
    @RequestMapping("toAdd")
    private String toAdd(Model model) {
        QueryWrapper<BaseData> baseDataQueryWrapper = new QueryWrapper<>();
        baseDataQueryWrapper.eq("parent_id", SystemConstant.BANK_TYPE_PID);
        List<BaseData> baseDataList = baseDataService.list(baseDataQueryWrapper);
        model.addAttribute("baseDataList",baseDataList);
        return "bank_card/bank_card_add";
    }
    /**
     * @Description:去修改银行卡密码
     * @Author: Liuwf
     * @Date:
     * @param
     * @return: java.lang.String
     **/
 @RequestMapping("toUpdatePassword")
    private String toUpdatePassword() {
        return "bank_card/update_password";
    }

    @RequestMapping("toList")
    private String toList() {
        return "bank_card/bank_card_list";
    }
    /**
     * @Description:密码修改确认页面
     * @Author: Liuwf
     * @Date:

     * @return: null
     **/
    @RequestMapping("surePassword/{id}")
    private String oldPassword(@PathVariable Integer id, Model model) {
        QueryWrapper<BankCard> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        BankCard bankCard = bankCardService.getOne(wrapper);
        model.addAttribute("bankCard",bankCard);
        return "bank_card/old_password";
    }



}
