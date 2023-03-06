package com.manong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manong.dao.PermissionMapper;
import com.manong.entity.Permission;
import com.manong.entity.PermissionQueryVo;
import com.manong.service.PermissionService;
import com.manong.utils.MenuTree;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lemon
 * @since 2023-02-27
 */
@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public List<Permission> findPermissionListByUserId(Long userId) {

        return baseMapper.findPermissionListByUserId(userId);
    }

    /**
     * 查询菜单列表
     * @param permissionQueryVo
     * @return
     */
    @Override
    public List<Permission> findPermissionList(PermissionQueryVo permissionQueryVo) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("order_num");
        List<Permission> permissionList = baseMapper.selectList(queryWrapper);
        List<Permission> menuTree = MenuTree.makeMenuTree(permissionList, 0L);
        return menuTree;
    }

    /**
     * 查询上级菜单列表
     * @return*
     */

    @Override
    public List<Permission> findParentPermissionList() {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("order_num");
        queryWrapper.in("type", Arrays.asList(0,1));
        List<Permission> permissionList = baseMapper.selectList(queryWrapper);
        Permission permission = new Permission();
        permission.setId(0L);
        permission.setParentId(-1L);
        permission.setLabel("顶级菜单");
        permissionList.add(permission);
        List<Permission> menuTree = MenuTree.makeMenuTree(permissionList, -1L);


        return menuTree;
    }

    @Override
    public boolean hasChildrenOfPermission(Long id) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        if (baseMapper.selectCount(queryWrapper)>0){
            return true;
        }
        return false;
    }


}
