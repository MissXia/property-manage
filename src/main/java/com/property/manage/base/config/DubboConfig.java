package com.property.manage.base.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;


@Configuration
public class DubboConfig {
    @Resource
    private Environment environment;

    /*
    @Bean
    public ReferenceBean<SecretRequest> secretRequest() {
        return DubboBeanUtils.referenceBean(SecretRequest.class);
    }

    @Bean
    public ReferenceBean<IRACAuthService> iracAuthService() {
        return DubboBeanUtils.referenceBean(IRACAuthService.class, environment.getProperty("dubbo.rac-version", "1.0.0"));
    }*/
}
