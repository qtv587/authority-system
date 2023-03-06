package com.manong.vo;

import com.manong.entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: XiaoDu
 * @CreateTime: 2023/3/7 0:35
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionVo {
    private List<Permission> permissionList = new ArrayList<>();
    private Object[] checkList;
}
