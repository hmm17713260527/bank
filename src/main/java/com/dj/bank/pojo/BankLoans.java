package com.dj.bank.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ProjectName: bank
 * @Package: com.dj.bank.pojo
 * @ClassName: BankUser
 * @Author: Administrator
 * @Description:
 * @Date: 2020/3/5 17:53
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
@TableName("bank_loans")
public class BankLoans {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 银行卡id
     */
    private Integer bankCardId;

    /**
     * 总待还金额(捐款金额)
     */
    private Double payMoneyAll;

    /**
     * 本月代还金额
     */
    private Double payMoneyMonth;

    /**
     * 剩余还款月
     */
    private Integer payMonthNumber;

    /**
     * 1在，2无
     */
    private Integer isDel;

    /**
     * 16待审核，17审核通过 18审核失败
     */
    private Integer status;

    /**
     * 用于判断减少信誉积分：1：已减少，0：没
     */
    private Integer type;

    /**
     * 还款时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date repaymentTime;


    @TableField(exist = false)
    private String bankCardNumber;


    @TableField(exist = false)
    private String userName;

}
