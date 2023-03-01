package com.manong.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.manong.utils.Result;
import com.manong.utils.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Author: XiaoDu
 * @CreateTime: 2023/2/28 22:37
 * @Description:匿名用户访问无权限资源时的处理器
 */

@Component
public class AnonymousAuthenticationHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //设置响应的编码格式
        response.setContentType("application/jsom;charset=utf-8") ;
        //获取输出流
        ServletOutputStream outputStream = response.getOutputStream();

        //将结果转换为JSON格式
        String result = JSON.toJSONString(Result.error().code(ResultCode.NO_LOGIN).message("匿名无权限访问！"), SerializerFeature.DisableCircularReferenceDetect);

        //将结果保存到输出中
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
