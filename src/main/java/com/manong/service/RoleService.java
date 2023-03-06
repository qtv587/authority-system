package com.manong.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manong.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.manong.vo.RoleVo;


public interface RoleService extends IService<Role> {
    IPage<Role> findRoleListByUserId(IPage<Role> page, RoleVo roleVo);


    boolean hasRoleCount(Long id);

    boolean deleteRoleById(Long id);
}
