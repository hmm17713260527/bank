package com.dj.bank.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.bank.pojo.BankUser;
import org.springframework.dao.DataAccessException;

/**
 * @ProjectName: pms
 * @Package: com.dj.pms.mapper
 * @ClassName: UserMapper
 * @Author: Administrator
 * @Description:
 * @Date: 2020/1/31 13:08
 * @Version: 1.0
 */
public interface UserMapper extends BaseMapper<BankUser> {

    /**
     * 还款金额
     * @param userId
     * @return
     * @throws Exception
     */
    BankUser getPayMoneyAllByUserId(Integer userId) throws DataAccessException;

}
