package com.manong.controller;

import com.manong.config.redis.RedisService;
import com.manong.utils.JwtUtils;
import com.manong.utils.Result;
import com.manong.vo.TokenVo;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    public Result refreshToken(HttpServletRequest request){
        //获取token信息
        String token = request.getHeader("token");
        if(ObjectUtils.isEmpty(token)){
            token = request.getParameter("token");
        }
        //从spring security上下文中获取用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取用户的身份信息
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //生成新的token
        String newToken = "";
        if (jwtUtils.validateToken(token,userDetails)){
            //重新生成成更新的token
            newToken = jwtUtils.refreshToken(token);
        }

        //获取本次token的到期时间
        Long expireTime =  Jwts.parser().setSigningKey(jwtUtils.getSecret())
                .parseClaimsJws(newToken.replace("jwt_",""))
                .getBody().getExpiration().getTime();
        //清楚原来的token信息
        String oldTokenKey = "token_"+token;
        redisService.del(oldTokenKey);
        //将新的信息保存到缓存中
        String newTokenKey = "token_"+newToken;
        redisService.set(newTokenKey,newToken,jwtUtils.getExpiration()/1000);

        //创建TokenVo对象
        TokenVo tokenVo = new TokenVo(expireTime,newToken);
        return Result.ok(tokenVo).message("token刷新成功");
    }
}
