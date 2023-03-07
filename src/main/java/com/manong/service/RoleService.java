package com.manong.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.manong.entity.Role;
import com.manong.vo.query.RoleQueryVo;

import java.util.List;


public interface RoleService extends IService<Role> {
    IPage<Role> findRoleListByUserId(IPage<Role> page, RoleQueryVo roleQueryVo);


    boolean hasRoleCount(Long id);

    boolean deleteRoleById(Long id);

    /**
     * 保存角色权限关系
     * @param roleId
     * @param permissionIds
     * @return
     */
    boolean saveRolePermission(Long roleId, List<Long> permissionIds);

    /**
     * 根据用户ID查询该用户拥有的角色ID
     * @param userId
     * @return
     */
    List<Long> findRoleIdByUserId(Long userId);

}
