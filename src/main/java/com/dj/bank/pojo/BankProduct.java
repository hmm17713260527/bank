package com.dj.bank.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 86150
 */
@Data
@TableName("bank_product")
public class BankProduct {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名
     */
    private String proName;

    /**
     * 商品图片
     */
    private String proImg;

    /**
     * 商品积分
     */
    private Integer proIntegral;

    /**
     * 1在，2无
     */
    private Integer isDel;

    /**
     * 商品库存
     */
    private Integer count;


}
