package com.manong.dao;

import com.manong.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manong.entity.PermissionQueryVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lemon
 * @since 2023-02-27
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> findPermissionListByUserId(Long userId);
}
