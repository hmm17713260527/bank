package com.dj.bank.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.bank.mapper.UserMapper;
import com.dj.bank.pojo.BankUser;
import com.dj.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName: pms
 * @Package: com.dj.pms.service.impl
 * @ClassName: UserServiceImpl
 * @Author: Administrator
 * @Description:
 * @Date: 2020/1/31 13:10
 * @Version: 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, BankUser> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<BankUser> findByIsDelAndType(Integer isDel, Integer type) throws Exception {
        return userMapper.findByIsDelAndType(isDel, type);
    }
}
