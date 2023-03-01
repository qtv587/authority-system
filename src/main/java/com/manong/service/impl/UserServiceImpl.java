package com.manong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.manong.entity.User;
import com.manong.dao.UserMapper;
import com.manong.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lemon
 * @since 2023-02-27
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Override
    public User findUserByUserName(String username) {

        //创建条件构造器对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("username",username);
        //执行查询
        return baseMapper.selectOne(queryWrapper);
    }
}
