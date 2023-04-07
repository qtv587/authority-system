package com.manong.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: XiaoDu
 * @CreateTime: 2023/4/2 13:25
 * @Description:
 */
@Data
@TableName("address")
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    private int addressId;
    private String addressName;
    private int addressPid;
    /**
     * 子菜单列表
     */
    @TableField(exist = false)  //因为表中不存在此字段，所以进行排除
    @JsonInclude(JsonInclude.Include.NON_NULL)  //属性值为NULL的不进行序列化操作
    private List<Address> children = new ArrayList<>();
}
