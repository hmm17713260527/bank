package com.dj.bank.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @description: TradingRecord
 * @Date: 2020/3/6 12:48
 * @author: zhangbo
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
@TableName("trading_record")
public class TradingRecord {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 银行卡号
     */
    private String userCard;

    /**
     * 交易金额
     */
    private String dealMoney;

    /**
     * 交易时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date dealTime;

    /**
     * 卡上剩余余额
     */
    private Double balanceMoney;

    /**
     * 支付方式
     */
    private String payWay;

    @TableField(exist = false)
    private String userName;


    @TableField(exist = false)
    private Integer status;

    @TableField(exist = false)
    private String phone;

    @TableField(exist = false)
    private Integer carId;

}