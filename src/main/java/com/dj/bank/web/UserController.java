package com.dj.bank.web;

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
}
