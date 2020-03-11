package com.dj.bank.web;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dj.bank.common.ResultModel;
import com.dj.bank.common.SystemConstant;
import com.dj.bank.pojo.BankProduct;
import com.dj.bank.service.BankProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author 86150
 */
@RestController
@RequestMapping("/product/")
public class BankProductController {

    @Autowired
    private BankProductService bankProductService;

    /**
     * 商品展示
     * @return
     */
    @GetMapping("show")
    public ResultModel<Object> show(Integer isDel) {
        try {
            QueryWrapper<BankProduct> wrapper = new QueryWrapper<BankProduct>();
            wrapper.eq("is_del", isDel);
            List<BankProduct> productList = bankProductService.list(wrapper);
            return new ResultModel<>().success(productList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.EXCEPTION);
        }
    }

    /**
     * wei删除
     * @param id
     * @return
     */
    @PutMapping("updateIsDelById")
    public ResultModel<Object> updateIsDelById(Integer id) {
        try {
            UpdateWrapper<BankProduct> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("is_del", SystemConstant.DELETE_IS_DEL);
            updateWrapper.eq("id", id);
            bankProductService.update(updateWrapper);
            return new ResultModel<>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.EXCEPTION);
        }
    }

}
