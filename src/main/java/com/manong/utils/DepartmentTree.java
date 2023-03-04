package com.manong.utils;

import com.manong.entity.Department;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentTree {
    /**
     * 生成部门树
     *
     * @param deptList
     * @param pid
     * @return
     */
    public static List<Department> makeDepartmentTree(List<Department> deptList,
                                                      Long pid) {
//创建集合保存部门信息
        List<Department> list = new ArrayList<Department>();
//如果deptList部门列表不为空，则使用部门列表，否则创建集合对象
        Optional.ofNullable(deptList).orElse(new ArrayList<Department>())
                .stream().filter(item -> item != null && item.getPid() == pid)
                .forEach(item -> {
//创建部门对象
                    Department dept = new Department();
//复制属性
                    BeanUtils.copyProperties(item, dept);
//获取每一个item的下级部门,递归生成部门树
                    List<Department> children = makeDepartmentTree(deptList,
                            item.getId());
//设置子部门
                    dept.setChildren(children);
//将部门对象添加到集合
                    list.add(dept);
                });
        return list;
    }
}
