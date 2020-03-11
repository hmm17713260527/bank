package com.dj.bank.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.bank.common.ResultModel;
import com.dj.bank.common.SystemConstant;
import com.dj.bank.pojo.BankProduct;
import com.dj.bank.pojo.BankUser;
import com.dj.bank.pojo.MyProduct;
import com.dj.bank.service.BankProductService;
import com.dj.bank.service.MyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author 86150
 */
@RestController
@RequestMapping("/myProduct/")
public class MyProductController {

    @Autowired
    private MyProductService myProductService;

    @Autowired
    private BankProductService bankProductService;

    @PostMapping("add")
    public ResultModel<Object> add(MyProduct myProduct, Integer type) {
        try {
            if (StringUtils.isEmpty(myProduct.getMyPhone())
                    || StringUtils.isEmpty(myProduct.getMySite())
                    || StringUtils.isEmpty(type)){
                return new ResultModel<>().error(SystemConstant.NOT_NULL);
            }
            myProductService.add(myProduct, type);
            return new ResultModel<>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.EXCEPTION);
        }
    }

    @GetMapping("show")
    public ResultModel<Object> show(@SessionAttribute("USER_SESSION")BankUser user) {
        try {
            QueryWrapper<MyProduct> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id" , user.getId());
            List<MyProduct> myProductList = myProductService.list(queryWrapper);
            for (MyProduct list : myProductList
                 ) {
                BankProduct bankProduct = bankProductService.getById(list.getProId());
                list.setProName(bankProduct.getProName());
                list.setProIntegral(bankProduct.getProIntegral());
            }
            return new ResultModel<>().success(myProductList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.EXCEPTION);
        }
    }

}
