package com.manong.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manong.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lemon
 * @since 2023-02-27
 */
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select count(1) from sys_user_role where role_id = #{roleId}")
    int getRoleCountByRoleId(Long id);

    @Delete("delete from sys_role_permission where role_id = #{id}")
    void deleteRolePermissionByRoleId(Long id);

    /**
     * 删除角色权限关系
     *
     * @param roleId
     */
    @Delete("delete from sys_role_permission where role_id = #{roleId}")
    void deleteRolePermission(Long roleId);

    /**
     * 保存角色权限关系
     *
     * @param roleId
     * @param permissionIds
     * @return
     */
    int saveRolePermission(Long roleId, List<Long> permissionIds);


    /**
     * 根据用户ID查询该用户拥有的角色ID
     * @param userId
     * @return
     */
    @Select("select role_id from `sys_user_role` where user_id = #{userId}")
    List<Long> findRoleIdByUserId(Long userId);
}
