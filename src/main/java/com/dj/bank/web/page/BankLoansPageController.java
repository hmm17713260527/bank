package com.dj.bank.web.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("toUpdateStatusShow")
    private String toUpdateStatusShow() {
        return "bank_loans/update_status_show";
    }
}
