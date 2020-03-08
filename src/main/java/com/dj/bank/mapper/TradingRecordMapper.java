package com.dj.bank.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.bank.pojo.TradingRecord;
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
public interface TradingRecordMapper extends BaseMapper<TradingRecord> {

    TradingRecord findLoans(@Param("id") Integer id) throws DataAccessException;

    List<TradingRecord> findTradingByUserTypeAndUserId(@Param("userType") Integer type, @Param("userId")Integer id) throws DataAccessException;
}
