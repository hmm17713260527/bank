package com.dj.bank.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: BankCard
 * @Date: 2020/3/6 12:37
 * @author: zhangbo
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
@TableName("bank_card")
public class BankCard {

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
    private String bankCardNumber;

    /**
     * 银行卡密码
     */
    private String password;

    /**
     * 银行卡余额
     */
    private BigDecimal balance;

    /**
     * 卡上剩余积分
     */
    private String integral;

    /**
     * 银行卡信誉值
     */
    private Integer reputationValue;

    /**
     * 银行卡总待还金额
     */
    private BigDecimal payMoneyAll;

    /**
     * 银行卡本月待还金额
     */
    private BigDecimal payMoneyMonth;

    /**
     * 银行卡待还款月数
     */
    private Integer payMonthNumber;

    /**
     * 银行卡状态:1为正常，2为冻结
     */
    private Integer status;

    /**
     * 银行卡申请时间
     */
    private Date createTime;

}