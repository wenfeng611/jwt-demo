package org.skytech.kefu.customer.config;

import lombok.extern.slf4j.Slf4j;
import org.skytech.kefu.customer.intecepter.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private JWTInterceptor jwtInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/login");
        super.addInterceptors(registry);
    }
}