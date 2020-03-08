package com.dj.bank.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.bank.pojo.BankCard;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @ProjectName: pms
 * @Package: com.dj.pms.mapper
 * @ClassName: UserMapper
 * @Author: Administrator
 * @Description:
 * @Date: 2020/1/31 13:08
 * @Version: 1.0
 */
public interface BankCardMapper extends BaseMapper<BankCard> {
//    根据登录人查询银行卡
    List<BankCard> findListByUserId(@Param("status") Integer status, @Param("id") Integer id) throws DataAccessException;


    List<BankCard> findUserCard(@Param("id") Integer id) throws DataAccessException;
}
