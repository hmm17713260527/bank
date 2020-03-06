package com.dj.bank.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.bank.common.ResultModel;
import com.dj.bank.common.SystemConstant;
import com.dj.bank.pojo.BankCard;
import com.dj.bank.pojo.BankResource;
import com.dj.bank.pojo.BankUser;
import com.dj.bank.pojo.BaseData;
import com.dj.bank.service.BankCardService;
import com.dj.bank.service.ResourceService;
import com.dj.bank.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * @ProjectName: pms
 * @Package: com.dj.pms.web
 * @ClassName: ResourceController
 * @Author: Administrator
 * @Date: 2020/2/3 17:18
 * @Version: 1.0
 */
@RestController
@RequestMapping("/bankCard/")
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
    /**
     * @Description:获取银行卡号
     * @Author: Liuwf
     * @Date:
     * @param
     * @return: null
     **/
    @RequestMapping("getNumber")
    public ResultModel<Object> getNumber(Integer type){
        try {
            String number = "";
            SimpleDateFormat format = new SimpleDateFormat("YYYYMMddHHmm");
            if(null != type){
                number = format.format(new Date()) + Random.getRandNum();
            }
            ResultModel resultModel = new ResultModel().success(true);
            resultModel.setData(number);
            return resultModel;
        }catch (Exception e){
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.EXCEPTION);
        }

    }
    @RequestMapping("insertCard")
    public ResultModel<Object> insertCard(BankCard bankCard, HttpSession session){
        try {
            BankUser user = (BankUser) session.getAttribute(SystemConstant.USER_RESOURCE);
            bankCard.setUserId(user.getId()).setReputationValue(60).setIntegral(1000)
                    .setCreateTime(new Date()).setBalance(SystemConstant.NULL)
                    .setBorrowBalance(SystemConstant.NULL);
            bankCardService.save(bankCard);
            return new ResultModel<>().success();
        }catch (Exception e){
            e.printStackTrace();
            return  new ResultModel<>().error(SystemConstant.EXCEPTION + e.getMessage());
        }

    }






}
