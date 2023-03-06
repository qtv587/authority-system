package com.manong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manong.dao.RoleMapper;
import com.manong.entity.Role;
import com.manong.entity.User;
import com.manong.service.RoleService;
import com.manong.utils.SecurityUtils;
import com.manong.vo.RoleVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;


@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


    @Override
    public IPage<Role> findRoleListByUserId(IPage<Role> page, RoleVo roleVo) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!ObjectUtils.isEmpty(roleVo.getRoleName()),"role_name",roleVo.getRoleName());
        queryWrapper.orderByAsc("id");
        User user = SecurityUtils.getCurrentUser();
        if (user!=null&&!ObjectUtils.isEmpty(user.getIsAdmin())&&user.getIsAdmin()!=1){
            queryWrapper.eq("create_user",user.getId());
        }
        return baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public boolean hasRoleCount(Long id) {
        return baseMapper.getRoleCountByRoleId(id)>0;
    }

    @Override
    public boolean deleteRoleById(Long id) {
        baseMapper.deleteRolePermissionByRoleId(id);
        return baseMapper.deleteById(id)>0;
    }
}
