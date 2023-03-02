package com.manong.utils;

import com.manong.entity.Permission;
import com.manong.vo.RouterVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: XiaoDu
 * @CreateTime: 2023/3/2 15:27
 * @Description:
 */
public class MenuTree {

    /**
     * 生成路由
     * @param menuList  菜单列表
     * @param pid   父级菜单id
     * @return
     */
    public static List<RouterVo> makeRouter(List<Permission> menuList,Long pid){
        //创建集合保存路由信息
        List<RouterVo> routerVoList = new ArrayList<RouterVo>();
        //判断菜单列表是否为空,如果不为空则使用菜单列表，如果为空则创建集合对象
        Optional.ofNullable(menuList).orElse(new ArrayList<Permission>())
                //筛选不为空并且和父id相等的对象
                .stream().filter(item -> item != null && item.getParentId().equals(pid))
                .forEach(item ->{
                    //创建路由信息对象
                    RouterVo routerVo = new RouterVo();
                    routerVo.setName(item.getName());
                    routerVo.setPath(item.getPath());
                    //判断是否是一级菜单
                    if (item.getParentId()==0L){
                        routerVo.setComponent("Layout");//一级菜单组件
                        routerVo.setAlwaysShow(true);//显示路由
                    }else {
                        routerVo.setComponent(item.getUrl());//具体某一个组件
                        routerVo.setAlwaysShow(false);//折叠路由
                    }

                    //设置mate信息
                    routerVo.setMeta(routerVo.new Meta(item.getLabel(), item.getIcon(), item.getCode().split(",")));
                    //递归生成路由
                    List<RouterVo> children = makeRouter(menuList, item.getId());
                    routerVo.setChildren(children);
                    routerVoList.add(routerVo);
                });
        //返回路由信息
        return routerVoList;
    }

    public static List<Permission> makeMenuTree(List<Permission> menuList,Long pid){
        //创建集合保存菜单
        ArrayList<Permission> permissionList = new ArrayList<>();
        //判断菜单列表是否为空,如果不为空则使用菜单列表，如果为空则创建集合对象
        Optional.ofNullable(permissionList).orElse(new ArrayList<Permission>())
        //筛选不为空并且和父id相等的对象
                .stream().filter(item -> item!=null && item.getParentId().equals(pid))
                .forEach(item ->{
                    //创建权限菜单对象
                    Permission permission = new Permission();
//                    将原有的属性复制给菜单对象
                    BeanUtils.copyProperties(item,permission);
//                    获取每一个item对象的子菜单，递归生成菜单树
                    List<Permission> children = makeMenuTree(menuList, item.getId());
                    //设置子菜单
                    permission.setChildren(children);
                    permissionList.add(permission);
                });
        return permissionList;
    }

}
