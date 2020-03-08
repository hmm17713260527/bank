package com.dj.bank.web.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.bank.common.SystemConstant;
import com.dj.bank.pojo.BankCard;
import com.dj.bank.pojo.BaseData;
import com.dj.bank.service.BankCardService;
import com.dj.bank.service.BaseDataService;
import com.dj.bank.service.LoansService;
import com.dj.bank.util.PasswordSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ProjectName: bank
 * @Package: com.dj.bank.web.page
 * @ClassName: UserPageController
 * @Author: Administrator
 * @Description:
 * @Date: 2020/3/5 18:00
 * @Version: 1.0
 */
@Controller
@RequestMapping("/user/")
public class UserPageController {

    @Autowired
    BankCardService bankCardService;

    @Autowired
    BaseDataService baseDataService;

    @Autowired
    LoansService loansService;

    /**
     * 去登录页面
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin() {
        return "user/login";
    }

    /**
     * 去注册
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("toAdd")
    public String toAdd(Model model) throws Exception {
        String salt = PasswordSecurityUtil.generateSalt();
        model.addAttribute("salt", salt);
        return "user/user_add";
    }

    /**
     * 去借款页面
     * @return
     */
    @RequestMapping("toBorrowMoney")
    public String toBorrowMoney() {
        return "user/user_borrow_money";
    }

    /**
     * 去借款
     * @return
     */
    @RequestMapping("toBorrow/{id}")
    public String toBorrow(@PathVariable Integer id, Model model) throws Exception {
        QueryWrapper<BaseData> queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id", SystemConstant.REFUND_DATE);
        List<BaseData> baseDataList = baseDataService.list(queryWrapper);
        BankCard bankCard = bankCardService.getById(id);
        model.addAttribute("baseDataList", baseDataList);
        model.addAttribute("bankCard", bankCard);
        return "user/user_borrow";
    }

    @RequestMapping("toResetPwd")
    public String toResetPwd(Model model) throws Exception {
        String salt = PasswordSecurityUtil.generateSalt();
        model.addAttribute("salt", salt);
        return "user/rest_pwd";
    }

    /**
     * 去充值
     * @return
     * @throws Exception
     */
    @RequestMapping("toUpdateBalance")
    public String toUpdateBalance() {
        return "user/user_update_card_balance";
    }

    /**
     * 充值
     * @return
     * @throws Exception
     */
    @RequestMapping("toUserAddBalance/{id}")
    public String toUserAddBalance(@PathVariable Integer id, Model model) {
        BankCard bankCard = bankCardService.getById(id);
        model.addAttribute("id", id);
        model.addAttribute("bankCard", bankCard);
        return "user/user_add_balance";
    }

    /**
     * 用户信息展示
     * @return
     */
    @RequestMapping("toShow")
    public String toShow() {
        return "user/user_show";
    }
}
