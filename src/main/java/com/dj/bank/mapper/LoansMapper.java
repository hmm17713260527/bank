package com.dj.bank.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.bank.pojo.BankLoans;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Date;
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
public interface LoansMapper extends BaseMapper<BankLoans> {

    List<BankLoans> findLoans(@Param("status") Integer status) throws DataAccessException;

    List<BankLoans> findRepaymentList(@Param("isDel") Integer isDel, @Param("id") Integer id) throws DataAccessException;

    Integer findDate(@Param("repaymentTime") Date repaymentTime) throws DataAccessException;
}
