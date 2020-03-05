package com.dj.bank.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.bank.common.ResultModel;
import com.dj.bank.common.SystemConstant;
import com.dj.bank.pojo.BankUser;
import com.dj.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: bank
 * @Package: com.dj.bank.web
 * @ClassName: UserController
 * @Author: Administrator
 * @Description:
 * @Date: 2020/3/5 18:00
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param bankUser
     * @return
     */
    @RequestMapping("add")
    public ResultModel<Object> add (BankUser bankUser){
        try {
            userService.save(bankUser);
            return new ResultModel<Object>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<Object>().error(SystemConstant.ERROR + e.getMessage());
        }
    }

    /**
     * 注册去充
     * @param bankUser
     * @return
     */
    @RequestMapping("distinct")
    public Boolean findByUsername (BankUser bankUser) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("user_name", bankUser.getUserName());
            queryWrapper.eq("phone", bankUser.getPhone());
            queryWrapper.eq("email", bankUser.getEmail());
            queryWrapper.eq("is_del", SystemConstant.IS_DEL);
            BankUser bankUser1 = userService.getOne(queryWrapper);
            if (bankUser1 == null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
