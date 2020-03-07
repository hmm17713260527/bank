package com.dj.bank.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

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
     * 1待审核，2审核通过 3审核失败
     */
    private Integer status;

}
