package com.dj.bank.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.bank.pojo.MyProduct;

/**
 * @author 86150
 */
public interface MyProductService extends IService<MyProduct> {
    /**
     * 添加商品
     * @param myProduct
     * @param type
     * @throws Exception
     */
    void add(MyProduct myProduct, Integer type) throws Exception;
}
