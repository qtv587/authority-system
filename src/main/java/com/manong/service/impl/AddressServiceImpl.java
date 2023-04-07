package com.manong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manong.dao.AddressMapper;
import com.manong.entity.Address;
import com.manong.service.AddressService;
import com.manong.utils.AddressTree;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: XiaoDu
 * @CreateTime: 2023/4/2 13:53
 * @Description:
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper,Address> implements AddressService {



    @Override
    public List<Address> findAddressTree() {
        return AddressTree.makeAddressTree( baseMapper.selectList(new QueryWrapper<>()),0);
    }
}
