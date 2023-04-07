package com.manong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manong.dao.HeroInfoMapper;
import com.manong.entity.HeroInfo;
import com.manong.service.HeroInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaodu
 * @since 2023-03-11
 */
@Service
public class HeroInfoServiceImpl extends ServiceImpl<HeroInfoMapper, HeroInfo> implements HeroInfoService {

    @Override
    public HeroInfo findHeroInfoById(Integer id) {



        return baseMapper.selectById(id);
    }
}
