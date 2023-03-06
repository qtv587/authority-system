package com.manong.utils;

import com.manong.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author: XiaoDu
 * @CreateTime: 2023/3/6 19:07
 * @Description:
 */
public class SecurityUtils {
    private SecurityUtils() {
    }
    public static User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user;
    }
}
