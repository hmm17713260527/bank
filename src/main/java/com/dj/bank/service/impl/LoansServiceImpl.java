package com.dj.bank.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.bank.mapper.LoansMapper;
import com.dj.bank.pojo.BankLoans;
import com.dj.bank.service.LoansService;
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
public class LoansServiceImpl extends ServiceImpl<LoansMapper, BankLoans> implements LoansService {

    @Override
    public List<BankLoans> findLoans(Integer status) throws Exception {
        return this.baseMapper.findLoans(status);
    }

    @Override
    public List<BankLoans> findRepaymentList(Integer isDel, Integer id) throws Exception {
        return this.baseMapper.findRepaymentList(isDel, id);
    }
}
