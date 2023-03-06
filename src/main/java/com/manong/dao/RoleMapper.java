package com.manong.dao;

import com.manong.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
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
}
