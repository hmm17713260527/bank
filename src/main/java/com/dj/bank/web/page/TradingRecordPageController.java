package com.dj.bank.web.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: bank
 * @Package: com.dj.bank.web.page
 * @ClassName: TradingRecordPageController
 * @Author: Administrator
 * @Description:
 * @Date: 2020/3/7 18:14
 * @Version: 1.0
 */
@Controller
@RequestMapping("/trading/")
public class TradingRecordPageController {

    @RequestMapping("toShow")
    public String toShow() {
        return "user/user_trading_show";
    }
}
