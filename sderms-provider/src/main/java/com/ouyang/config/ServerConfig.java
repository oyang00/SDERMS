package com.ouyang.config;


import com.ouyang.config.filter.CrossDomainFilter;
import com.ouyang.config.filter.EncodingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;


/**
 * @author 16计算机 ouyang
 * @version 1.0
 * @copyright ：ouyang 版权所有 © 2019
 * @date 2019年1月30日上午11:14:43
 * @Description TODO Server 配置
 */
@Configuration
public class ServerConfig {

    // 注册 CharacterEncodingFilter
    @Bean
    public FilterRegistrationBean<EncodingFilter> encodingFilter() {
        FilterRegistrationBean<EncodingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new EncodingFilter());
        registrationBean.setUrlPatterns(Collections.singletonList("/*"));
        return registrationBean;
    }

    // 注册 CharacterEncodingFilter
    @Bean
    public FilterRegistrationBean<CrossDomainFilter> crossDomainFilter() {
        FilterRegistrationBean<CrossDomainFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CrossDomainFilter());
        registrationBean.setUrlPatterns(Collections.singletonList("/*"));
        return registrationBean;
    }

}
