package com.manong.controller;

import com.manong.service.AddressService;
import com.manong.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: XiaoDu
 * @CreateTime: 2023/4/2 13:57
 * @Description:
 */
@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Resource
    private AddressService addressService;

    @GetMapping("/addressTree")
    public Result list(){
        return Result.ok(addressService.findAddressTree());
    }

}
