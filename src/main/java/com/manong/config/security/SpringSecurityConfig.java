package com.manong.config.security;

import com.manong.config.security.handler.AnonymousAuthenticationHandler;
import com.manong.config.security.handler.CustomerAccessDeniedHandler;
import com.manong.config.security.handler.LoginFailureHandler;
import com.manong.config.security.handler.LoginSuccessHandler;
import com.manong.config.security.service.CustomerUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * @Author: XiaoDu
 * @CreateTime: 2023/2/28 22:50
 * @Description:
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String URL_WHITELIST[] = {
            "/api/user/login",
            "/swagger-ui.html",
            "/webjars/springfox-swagger-ui/**",
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui.html"
    };
    @Resource
    private LoginFailureHandler loginFailureHandler;
    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    @Resource
    private AnonymousAuthenticationHandler anonymousAuthenticationHandler;
    @Resource
    private CustomerAccessDeniedHandler customerAccessDeniedHandler;
    @Resource
    private CustomerUserDetailsService customerUserDetailsService;

    /**
     * 注入加密类
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /**
     * 处理登录认证
     * @param http
     * @throws Exception
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登录过程处理
        http.formLogin()//表单登录
                .loginProcessingUrl("/api/user/login")//登录请求的url
                .successHandler(loginSuccessHandler)    //认证成功处理器
                .failureHandler(loginFailureHandler)    //认证失败处理器
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers(URL_WHITELIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(anonymousAuthenticationHandler)//匿名角色无权限访问
                .accessDeniedHandler(customerAccessDeniedHandler)
                .and()
                .cors();

    }

    /**
     * 配置认证处理器
     * @param auth
     * @throws Exception
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserDetailsService).passwordEncoder(passwordEncoder());
    }


}
