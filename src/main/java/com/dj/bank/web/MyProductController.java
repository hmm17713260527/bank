package com.dj.bank.web;

import com.dj.bank.common.ResultModel;
import com.dj.bank.common.SystemConstant;
import com.dj.bank.pojo.MyProduct;
import com.dj.bank.service.MyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 86150
 */
@RestController
@RequestMapping("/myProduct/")
public class MyProductController {

    @Autowired
    private MyProductService myProductService;

    @RequestMapping("add")
    public ResultModel<Object> add(MyProduct myProduct, Integer type) {
        try {
            if (StringUtils.isEmpty(myProduct.getMyPhone()) || StringUtils.isEmpty(myProduct.getMySite()) || StringUtils.isEmpty(type)){
                return new ResultModel<>().error("不得为空");
            }
            myProductService.add(myProduct, type);
            return new ResultModel<>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.EXCEPTION);
        }
    }

}
