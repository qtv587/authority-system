package com.manong.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manong.entity.Role;
import com.manong.service.PermissionService;
import com.manong.service.RoleService;
import com.manong.utils.Result;
import com.manong.vo.RolePermissionVo;
import com.manong.vo.RoleVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lemon
 * @since 2023-02-27
 */
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;

    @GetMapping("/list")
    public Result list(RoleVo roleVo) {
        IPage<Role> page = new Page<Role>(roleVo.getPageNo(), roleVo.getPageSize());
        roleService.findRoleListByUserId(page, roleVo);
        return Result.ok(page);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Role role) {
        if (roleService.save(role)) {
            return Result.ok().message("添加角色成功");
        }
        return Result.error().message("添加角色失败");
    }

    @PutMapping("/update")
    public Result update(@RequestBody Role role) {
        if (roleService.updateById(role)) {
            return Result.ok().message("角色修改成功");
        }
        return Result.error().message("角色修改失败");
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        if (roleService.deleteRoleById(id)) {
            return Result.ok().message("角色删除成功");
        }
        return Result.error().message("角色删除失败");
    }

    @GetMapping("/check/{id}")
    public Result check(@PathVariable Long id) {
        if (roleService.hasRoleCount(id)) {
            return Result.exist().message("该角色已分配给其他用户使用,无法删除");
        }
        return Result.ok();
    }

    @GetMapping("/getAssignPermissionTree/{roleId}")
    public Result getAssignPermissionTree(@PathVariable Long roleId) {
//调用查询权限树数据的方法
        RolePermissionVo permissionTree =
                permissionService.findPermissionTree(roleId);
//返回数据
        return Result.ok(permissionTree);
    }


}

