package com.dj.bank.web.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 86150
 */
@Controller
@RequestMapping("/product/")
public class BankProductPageController {

    @RequestMapping("toShow")
    public String toShow() {
        return "product/show";
    }

}
