package com.dj.bank.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 86150
 */
@Data
@TableName("my_product")
public class MyProduct {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 商品id
     */
    private Integer proId;

    @TableField(exist = false)
    private String proName;

    @TableField(exist = false)
    private Integer proIntegral;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 我的地址
     */
    private String mySite;

    /**
     * 我的手机号
     */
    private String myPhone;

    /**
     * 订单状态 1为派送中 2为已完成 3为待发货
     */
    private Integer status;


}
