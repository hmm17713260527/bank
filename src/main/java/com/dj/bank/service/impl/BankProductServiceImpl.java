package com.dj.bank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.bank.mapper.BankProductMapper;
import com.dj.bank.pojo.BankProduct;
import com.dj.bank.service.BankProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 86150
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BankProductServiceImpl extends ServiceImpl<BankProductMapper, BankProduct> implements BankProductService {
}
