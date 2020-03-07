package com.dj.bank.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@TableName("base_data")
public class BaseData {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 父级id
     */
    private Integer parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 1展示 2删除
     */
    private Integer isDel;
}