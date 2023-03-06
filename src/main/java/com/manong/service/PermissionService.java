package com.manong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manong.entity.Permission;
import com.manong.entity.PermissionQueryVo;
import com.manong.vo.RolePermissionVo;

import java.util.List;


public interface PermissionService extends IService<Permission> {
    List<Permission> findPermissionListByUserId(Long userId);

    /**
     * 查询菜单列表
     * @param permissionQueryVo
     * @return
     */

    List<Permission> findPermissionList(PermissionQueryVo permissionQueryVo);

    /**
     * 查询上级菜单列表
     *
     * @return
     */
    List<Permission> findParentPermissionList();

    /**
     * 检查菜单是否有子菜单
     *
     * @param id
     * @return
     */
    boolean hasChildrenOfPermission(Long id);

    /**
     * 查询分配权限树列表
     * @param userId
     * @param roleId
     * @return
     */
    RolePermissionVo findPermissionTree(Long roleId);


}
