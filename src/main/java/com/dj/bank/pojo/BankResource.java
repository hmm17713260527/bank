package com.dj.bank.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ProjectName: bank
 * @Package: com.dj.bank.pojo
 * @ClassName: BankResource
 * @Author: Administrator
 * @Description:
 * @Date: 2020/3/6 11:32
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
@TableName("bank_resource")
public class BankResource {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String resourceName;
    private Integer parentId;
    private String url;
    /**
     * 1在，2无
     */
    private Integer isDel;

    /**
     * 1.2用户展示；1.3管理员展示
     */
    private Integer type;

    @TableField(exist = false)
    private Boolean checked = false;
}
