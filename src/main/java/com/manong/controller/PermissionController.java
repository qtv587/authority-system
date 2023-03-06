package com.manong.controller;


import com.manong.entity.Permission;
import com.manong.entity.PermissionQueryVo;
import com.manong.service.PermissionService;
import com.manong.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lemon
 * @since 2023-02-27
 */
@RestController
@RequestMapping("/api/permission")
public class PermissionController {


    @Resource
    PermissionService permissionService;

    @GetMapping("/list")
    public Result list(PermissionQueryVo permissionQueryVo){
        List<Permission> permissionList = permissionService.findPermissionList(permissionQueryVo);
        return Result.ok(permissionList);
    }

    @GetMapping("/parent/list")
    public Result getParentList(){
        List<Permission> permissionList = permissionService.findParentPermissionList();
        return Result.ok(permissionList);
    }
    @PostMapping("/add")
    public Result add(@RequestBody Permission permission){
        if (permissionService.save(permission)) {
            return Result.ok().message("菜单添加成功");
        }
        return Result.error().message("菜单添加失败");
    }

    @PutMapping("/update")
    public Result update(@RequestBody Permission permission){
        if (permissionService.updateById(permission)) {
            return Result.ok().message("菜单修改成功");
        }
        return Result.error().message("菜单修改失败");
    }


    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){
        if (permissionService.removeById(id)) {
            return Result.ok().message("菜单删除成功");
        }
        return Result.error().message("菜单删除失败");
    }

    @GetMapping("/check/{id}")
    public Result check(@PathVariable Long id){
        if (permissionService.hasChildrenOfPermission(id)) {
            return Result.exist().message("改菜单下有子菜单,无法删除");
        }
        return Result.ok();
    }
}

