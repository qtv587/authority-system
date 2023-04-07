package com.manong.controller;


import com.manong.entity.HeroInfo;
import com.manong.service.HeroInfoService;
import com.manong.utils.IpUtils;
import com.manong.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaodu
 * @since 2023-03-11
 */
@RestController
@RequestMapping("/api/heroInfo")
public class HeroInfoController {

    @Resource
    private HeroInfoService heroInfoService;
//https://www.somekey.cn/mini/hero/getHeroInfo.php?hero=嬴政&type=wx
    @GetMapping("/listAll")
    public Result list(){
        return Result.ok(heroInfoService.list());
    }

    @GetMapping("/selectInfo/{id}")
    public Result findHeroInfoById(HttpServletRequest request, @PathVariable Integer id) throws Exception {
        HeroInfo heroInfo = heroInfoService.findHeroInfoById(id);
        heroInfo.setStamp(System.currentTimeMillis());
        heroInfo.setClientIP(IpUtils.getIP(request));
        return Result.ok(heroInfo);
    }



}

