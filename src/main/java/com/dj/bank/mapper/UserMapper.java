package com.dj.bank.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.bank.pojo.BankUser;
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
public interface UserMapper extends BaseMapper<BankUser> {

    List<BankUser> findByIsDelAndType(@Param("isDel") Integer isDel, @Param("type")Integer type) throws DataAccessException;


}
