package com.manong.dto;

import lombok.Data;

import java.util.List;

/**
 * 用于给用户分配角色时保存选中的角色数据
 */
@Data
public class UserRoleDTO {
    private Long userId;
    private List<Long> roleIds;
}
