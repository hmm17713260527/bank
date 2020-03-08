package com.dj.bank.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dj.bank.common.ResultModel;
import com.dj.bank.common.SystemConstant;
import com.dj.bank.pojo.BankProduct;
import com.dj.bank.pojo.BankUser;
import com.dj.bank.service.UserService;
import com.dj.bank.util.MessageVerifyUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


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
        wrapper.or().eq("user_name", userName).or().eq("phone", userName).or().eq("email", userName);
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
     * 注册去重
     * @param bankUser
     * @return
     */
    @GetMapping("distinct")
    public Boolean findByUsername (BankUser bankUser) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            if (!StringUtils.isEmpty(bankUser.getUserName())) {
                queryWrapper.eq("user_name", bankUser.getUserName());
            }
            if (!StringUtils.isEmpty(bankUser.getPhone())) {
                queryWrapper.eq("phone", bankUser.getPhone());
            }
            if (!StringUtils.isEmpty(bankUser.getEmail())) {
                queryWrapper.eq("email", bankUser.getEmail());
            }
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

    @PutMapping("updatePwd")
    public ResultModel<Object> updatePwd(String phone, String password, String message, String salt) {
        try {
            if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(message)) {
                return new ResultModel<Object>().error("手机号或验证码不得为空!");
            }
            QueryWrapper<BankUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("phone", phone);
            queryWrapper.eq("message", message);
            BankUser user1 = userService.getOne(queryWrapper);
            if (user1 == null) {
                return new ResultModel<Object>().error("手机号与验证码不匹配!!");
            }
            if (user1.getIsDel() == 2) {
                return new ResultModel<Object>().error("用户已被删除!");
            }
            if (user1.getEndTime().compareTo(new Date()) != 1) {
                return new ResultModel<>().error("验证码已失效");
            }
            UpdateWrapper<BankUser> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("password", password);
            updateWrapper.set("salt", salt);
            updateWrapper.eq("phone", phone);
            userService.update(updateWrapper);
            return new ResultModel<>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.ERROR + e.getMessage());
        }
    }

    /**
     *
     * @param
     * @return
     */
    @GetMapping("getCode")
    public ResultModel<Object> getCode(String phone) {
        try {
            QueryWrapper<BankUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("phone", phone);
            BankUser user1 = userService.getOne(queryWrapper);
            if (user1.getIsDel() == 1 && user1 != null ) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.MINUTE, 2);
                String newCode = String.valueOf(MessageVerifyUtils.getNewcode());
                UpdateWrapper<BankUser> updateWrapper = new UpdateWrapper<>();
                updateWrapper.set("message", newCode).set("end_time", cal.getTime());
                updateWrapper.eq("phone", phone);
                userService.update(updateWrapper);
                MessageVerifyUtils.sendSms(phone, newCode);
                return new ResultModel<Object>().success();
            }
            return new ResultModel<Object>().error("用户不存在");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.ERROR + e.getMessage());
        }
    }

    @GetMapping("list")
    public ResultModel<Object> list(BankUser bankUser) {
        try {
            List<BankUser> userList = userService.findByIsDelAndType(bankUser.getIsDel(), bankUser.getType());
            return new ResultModel<Object>().success(userList);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.ERROR + e.getMessage());
        }
    }

    /**
     * 用户拉黑
     * @param id
     * @param isDel
     * @return
     */
    @PutMapping("updateIsDelById")
    public ResultModel<Object> updateIsDelById(Integer id, Integer isDel) {
        try {
            UpdateWrapper<BankUser> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("is_del", isDel);
            updateWrapper.eq("id", id);
            userService.update(updateWrapper);
            return new ResultModel<>().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.ERROR + e.getMessage());
        }
    }


}
