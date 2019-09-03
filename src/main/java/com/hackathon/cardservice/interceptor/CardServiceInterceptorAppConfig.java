package com.hackathon.cardservice.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class CardServiceInterceptorAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    CardServiceInterceptor cardServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cardServiceInterceptor);
    }
}
