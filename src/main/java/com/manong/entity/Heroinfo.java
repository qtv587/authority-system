package com.manong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaodu
 * @since 2023-03-11
 */
@Data
@TableName("hero_hero_info")
public class HeroInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    private String name;

    private String alias;

    private String platform;

    private String photo;

    private String area;

    private Integer areaPower;

    private String city;

    private Integer cityPower;

    private String province;

    private Integer provincePower;

    private Integer guobiao;

    private LocalDateTime updatetime;

    @TableField(exist = false)
    private Long stamp;

    @TableField(exist = false)
    private String clientIP;


}
