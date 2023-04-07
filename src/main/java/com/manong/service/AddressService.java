package com.manong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manong.entity.Address;
import java.util.List;

/**
 * @Author: XiaoDu
 * @CreateTime: 2023/4/2 13:43
 * @Description:
 */
public interface AddressService extends IService<Address> {
    public List<Address> findAddressTree();
}
