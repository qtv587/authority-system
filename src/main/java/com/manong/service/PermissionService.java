package com.manong.service;

import com.manong.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface PermissionService extends IService<Permission> {
    List<Permission> findPermissionListByUserId(Long userId);


}
