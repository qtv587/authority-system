package com.manong.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: XiaoDu
 * @CreateTime: 2023/3/2 13:46
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenVo {

    //过期时间
    private Long expireTime;
    //token
    private String token;
}
