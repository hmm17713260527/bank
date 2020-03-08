package com.dj.bank.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
    private Integer password;

    /**
     * 银行卡余额
     */
    private Double balance;

    /**
     * 卡上剩余积分
     */
    private Integer integral;

    /**
     * 银行卡信誉值
     */
    private Integer reputationValue;

    /**
     * 银行卡状态：17为正常，0为冻结，16待审核；18，审核未通过
     */
    private Integer status;

    /**
     * 银行卡申请时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @TableField(exist = false)
    private String createTimeShow;

    public String getCreateTimeShow() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return createTime == null ? null : dateFormat.format(createTime);
    }


    /**
     *11:工商银行 12:建设银行 13:农业银行 14中国银行
     */
    private Integer type;

    @TableField(exist = false)
    private String typeShow;

    @TableField(exist = false)
    private Double payMoneyAll;

    /**
     * 剩余可借金额
     */
    private Double borrowBalance;
    /**
     * @Description:用于展示的用户名
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    @TableField(exist = false)
    private String userName;
    /**
     * @Description:用于展示的银行类型名
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    @TableField(exist = false)
    private String baseName;
     /**
     * @Description:用于展示的银行卡状态名
     * @Author: Liuwf
     * @Date:
     * @param null:
     * @return: null
     **/
    @TableField(exist = false)
    private String statusName;

}