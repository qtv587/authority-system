package com.manong.vo;

import com.manong.entity.Role;
import lombok.Data;

/**
 * @Author: XiaoDu
 * @CreateTime: 2023/3/6 19:11
 * @Description:
 */
@Data
public class RoleVo extends Role {
    private Long pageNo = 1L;
    private Long pageSize = 10L;
    private Long userId;
}
