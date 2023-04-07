package com.manong.utils;

import com.manong.entity.Address;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: XiaoDu
 * @CreateTime: 2023/4/2 13:33
 * @Description:
 */
public class AddressTree {
    public static List<Address> makeAddressTree(List<Address> addressList, int pid) {
        //创建集合保存菜单
        ArrayList<Address> addressListA = new ArrayList<>();
        //判断菜单列表是否为空,如果不为空则使用菜单列表，如果为空则创建集合对象
        Optional.ofNullable(addressList).orElse(new ArrayList<Address>())
                //筛选不为空并且和父id相等的对象
                .stream().filter(item -> item != null && item.getAddressPid() == pid)
                .forEach(item -> {
                    //创建权限菜单对象
                    Address address = new Address();
//                    将原有的属性复制给菜单对象
                    BeanUtils.copyProperties(item, address);
//                    获取每一个item对象的子菜单，递归生成菜单树
                    List<Address> children = makeAddressTree(addressList, item.getAddressId());
                    //设置子菜单
                    address.setChildren(children);
                    addressListA.add(address);
//                    System.out.println(item);
                });
        return addressListA;
    }
}
