package com.dj.bank.web.page;

import com.dj.bank.util.PasswordSecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /**
     * 去登录页面
     * @return
     */
    @RequestMapping("toLogin")
    private String toLogin() {
        return "user/login";
    }

    /**
     * 去注册
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("toAdd")
    private String toAdd(Model model) throws Exception {
        String salt = PasswordSecurityUtil.generateSalt();
        model.addAttribute("salt", salt);
        return "user/user_add";
    }

    /**
     * 去借款
     * @return
     */
    @RequestMapping("toBorrowMoney")
    private String toBorrowMoney() {
        return "user/user_borrow_money";
    }

    @RequestMapping("toResetPwd")
    private String toResetPwd(Model model) throws Exception {
        String salt = PasswordSecurityUtil.generateSalt();
        model.addAttribute("salt", salt);
        return "user/rest_pwd";
    }


}
