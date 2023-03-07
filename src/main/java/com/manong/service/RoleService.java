package com.manong.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.manong.entity.Role;
import com.manong.vo.RoleVo;

import java.util.List;


public interface RoleService extends IService<Role> {
    IPage<Role> findRoleListByUserId(IPage<Role> page, RoleVo roleVo);


    boolean hasRoleCount(Long id);

    boolean deleteRoleById(Long id);

    /**
     * 保存角色权限关系
     * @param roleId
     * @param permissionIds
     * @return
     */
    boolean saveRolePermission(Long roleId, List<Long> permissionIds);
}
