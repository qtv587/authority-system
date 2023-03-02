package com.manong.controller;

import com.manong.config.redis.RedisService;
import com.manong.entity.Permission;
import com.manong.entity.User;
import com.manong.entity.UserInfo;
import com.manong.utils.JwtUtils;
import com.manong.utils.MenuTree;
import com.manong.utils.Result;
import com.manong.vo.RouterVo;
import com.manong.vo.TokenVo;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: XiaoDu
 * @CreateTime: 2023/3/2 13:57
 * @Description:
 */
@RestController
@RequestMapping("/api/sysUser")
public class SysUserController {
    @Resource
    private RedisService redisService;
    @Resource
    private JwtUtils jwtUtils;


    @PostMapping("/refreshToken")
    public Result refreshToken(HttpServletRequest request) {
        //获取token信息
        String token = request.getHeader("token");
        if (ObjectUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }
        //从spring security上下文中获取用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取用户的身份信息
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //生成新的token
        String newToken = "";
        if (jwtUtils.validateToken(token, userDetails)) {
            //重新生成成更新的token
            newToken = jwtUtils.refreshToken(token);
        }

        //获取本次token的到期时间
        Long expireTime = Jwts.parser().setSigningKey(jwtUtils.getSecret())
                .parseClaimsJws(newToken.replace("jwt_", ""))
                .getBody().getExpiration().getTime();
        //清楚原来的token信息
        String oldTokenKey = "token_" + token;
        redisService.del(oldTokenKey);
        //将新的信息保存到缓存中
        String newTokenKey = "token_" + newToken;
        redisService.set(newTokenKey, newToken, jwtUtils.getExpiration() / 1000);

        //创建TokenVo对象
        TokenVo tokenVo = new TokenVo(expireTime, newToken);
        return Result.ok(tokenVo).message("token刷新成功");
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/getInfo")
    public Result getInfo() {
//从Spring Security上下文获取用户信息
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
//判断authentication对象是否为空
        if (authentication == null) {
            return Result.error().message("用户信息查询失败");
        }
//获取用户信息
        User user = (User) authentication.getPrincipal();
//用户权限集合
        List<Permission> permissionList = user.getPermissionList();
//获取角色权限编码字段
        Object[] roles =
                permissionList.stream()
                        .filter(Objects::nonNull)
                        .map(Permission::getCode).toArray();
//创建用户信息对象
        UserInfo userInfo = new UserInfo(user.getId(), user.getNickName(),
                user.getAvatar(), null, roles);
//返回数据
        return Result.ok(userInfo).message("用户信息查询成功");
    }

    /**
     * 获取登录用户菜单数据
     *
     * @return
     */
    @GetMapping("/getMenuList")
    public Result getMenuList() {
        //从Spring Security上下文获取用户信息
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        //获取用户信息
        User user = (User) authentication.getPrincipal();
        //获取相应的权限
        List<Permission> permissionList = user.getPermissionList();
        //筛选目录和菜单
        List<Permission> collect = permissionList.stream()
                .filter(item -> item != null && item.getType() != 2)
                .collect(Collectors.toList());
        //生成路由数据
        List<RouterVo> routerVoList = MenuTree.makeRouter(collect, 0L);
        //返回数据
        return Result.ok(routerVoList).message("菜单数据获取成功");
    }


}
