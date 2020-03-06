package com.dj.bank.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.bank.pojo.BankUser;

/**
 * @ProjectName: pms
 * @Package: com.dj.pms.service
 * @ClassName: UserService
 * @Author: Administrator
 * @Description:
 * @Date: 2020/1/31 13:09
 * @Version: 1.0
 */
public interface UserService extends IService<BankUser> {

    /**
     * 还款金额
     * @param userId
     * @return
     * @throws Exception
     */
    BankUser getPayMoneyAllByUserId(Integer userId) throws Exception;
}
