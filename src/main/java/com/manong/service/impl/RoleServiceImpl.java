package com.manong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manong.dao.RoleMapper;
import com.manong.entity.Role;
import com.manong.entity.User;
import com.manong.service.RoleService;
import com.manong.utils.SecurityUtils;
import com.manong.vo.query.RoleQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;


@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


    @Override
    public IPage<Role> findRoleListByUserId(IPage<Role> page, RoleQueryVo roleQueryVo) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!ObjectUtils.isEmpty(roleQueryVo.getRoleName()), "role_name", roleQueryVo.getRoleName());
        queryWrapper.orderByAsc("id");
        User user = SecurityUtils.getCurrentUser();
        if (user != null && !ObjectUtils.isEmpty(user.getIsAdmin()) && user.getIsAdmin() != 1) {
            queryWrapper.eq("create_user", user.getId());
        }
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public boolean hasRoleCount(Long id) {
        return baseMapper.getRoleCountByRoleId(id) > 0;
    }

    @Override
    public boolean deleteRoleById(Long id) {
        baseMapper.deleteRolePermissionByRoleId(id);
        return baseMapper.deleteById(id) > 0;
    }

    /**
     * 保存角色权限关系
     *
     * @param roleId
     * @param permissionIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class )
    public boolean saveRolePermission(Long roleId, List<Long> permissionIds) {
//删除该角色对应的权限信息
        baseMapper.deleteRolePermission(roleId);
//保存角色权限
        return baseMapper.saveRolePermission(roleId, permissionIds) > 0;
    }
    /**
     * 根据用户ID查询该用户拥有的角色ID
     *
     * @param userId
     * @return
     */
    @Override
    public List<Long> findRoleIdByUserId(Long userId) {
        return baseMapper.findRoleIdByUserId(userId);
    }
}
