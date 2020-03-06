package com.dj.bank.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.bank.common.ResultModel;
import com.dj.bank.common.SystemConstant;
import com.dj.bank.pojo.BankUser;
import com.dj.bank.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("findSalt")
    public ResultModel<Object> findSalt(String userName) {
        QueryWrapper<BankUser> wrapper = new QueryWrapper<BankUser>();
        wrapper.eq("user_name", userName);
        BankUser user = userService.getOne(wrapper);
        return new ResultModel<>().success(user.getSalt());
    }

    @GetMapping("login")
    public ResultModel<Object> login1(String userName, String password) {
        try {
            if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
                return new ResultModel<>().error(SystemConstant.LOGIN_NULL);
            }


            //shiro登陆
            Subject subject = SecurityUtils.getSubject(); //获取subject
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            subject.login(token);

            return new ResultModel<>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.ERROR + e.getMessage());
        }

    }

    /**
     * 用户注册
     * @param bankUser
     * @return
     */
    @PostMapping("add")
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
    @GetMapping("distinct")
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
