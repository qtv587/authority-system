package com.manong.controller;


import com.manong.service.UserService;
import com.manong.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lemon
 * @since 2023-02-27
 */
@RestController
@RequestMapping("/api/user")
@Api(tags = "用户服务相关接口")
public class UserController {

    @Resource
    private UserService userService;


    @RequestMapping("/listAll")
    @ApiOperation(value = "查询所有用户的接口",
            notes = "<span style='color:red;'>描述:</span>&nbsp;用来查询所有用户信息的接口")
    public Result listAll(){
        return Result.ok(userService.list());
    }

}

