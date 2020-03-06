package com.dj.bank.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.bank.common.ResultModel;
import com.dj.bank.pojo.BankCard;
import com.dj.bank.pojo.BankResource;
import com.dj.bank.pojo.BankUser;
import com.dj.bank.service.BankCardService;
import com.dj.bank.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @ProjectName: pms
 * @Package: com.dj.pms.web
 * @ClassName: ResourceController
 * @Author: Administrator
 * @Date: 2020/2/3 17:18
 * @Version: 1.0
 */
@RestController
@RequestMapping("/bankcard/")
public class BankCardController {

    @Autowired
    private BankCardService bankCardService;


    /**
     * 银行卡展示
     * @return
     */
    @GetMapping("bankCardList")
    public ResultModel<Object> bankCardList(HttpSession session) {
        BankUser bankUser = (BankUser) session.getAttribute("user");
        QueryWrapper<BankCard> wrapper = new QueryWrapper<BankCard>();
        wrapper.eq("user_id", bankUser.getId());
        List<BankCard> bankCardList = bankCardService.list(wrapper);
        return new ResultModel<>().success(bankCardList);
    }






}
