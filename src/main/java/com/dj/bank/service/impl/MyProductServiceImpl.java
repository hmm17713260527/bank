package com.dj.bank.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.bank.mapper.MyProductMapper;
import com.dj.bank.pojo.BankCard;
import com.dj.bank.pojo.BankProduct;
import com.dj.bank.pojo.MyProduct;
import com.dj.bank.service.BankCardService;
import com.dj.bank.service.BankProductService;
import com.dj.bank.service.MyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 86150
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MyProductServiceImpl extends ServiceImpl<MyProductMapper, MyProduct> implements MyProductService {

    @Autowired
    private BankProductService bankProductService;

    @Autowired
    private BankCardService bankCardService;

    @Override
    public void add(MyProduct myProduct, Integer type) throws Exception {
        this.save(myProduct);

        QueryWrapper<BankCard> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("type", type);
        wrapper1.eq("user_id", myProduct.getUserId());
        BankCard bankCard = bankCardService.getOne(wrapper1);

        BankProduct bankProduct = bankProductService.getById(myProduct.getProId());

        UpdateWrapper<BankProduct> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("count", bankProduct.getCount()-1);
        updateWrapper.eq("id", myProduct.getProId());
        bankProductService.update(updateWrapper);

        UpdateWrapper<BankCard> wrapper = new UpdateWrapper<>();
        wrapper.set("integral", bankCard.getIntegral() - bankProduct.getProIntegral());
        wrapper.eq("user_id", myProduct.getUserId());
        wrapper.eq("type", type);
        bankCardService.update(wrapper);
    }
}
