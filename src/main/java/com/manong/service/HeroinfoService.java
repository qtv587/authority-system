package com.manong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manong.entity.HeroInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaodu
 * @since 2023-03-11
 */
public interface HeroInfoService extends IService<HeroInfo> {

    HeroInfo findHeroInfoById(Integer id);
}
