package com.dj.bank.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

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
@TableName("bank_user")
public class BankUser {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String password;

    private String phone;

    private String email;

    /**
     * 1男，2女
     */
    private Integer sex;


    /**
     * 1在，2无
     */
    private Integer isDel;

    private String salt;

    private String message;

    private Integer age;

    /**
     * 1,用户，2，管理员
     */
    private Integer type;

    private Date endTime;

    @TableField(exist = false)
    private Integer reputationValue;

    @TableField(exist = false)
    private Integer typeShow;

    @TableField(exist = false)
    private String bankCardNumber;

}
