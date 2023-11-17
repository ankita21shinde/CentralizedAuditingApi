package com.example.CommonService.auding;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HandlerConfig implements WebMvcConfigurer {


    @Bean
    public Interceptor interceptor(){
        return new Interceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(interceptor());
    }




    //    @Bean
//    public AuditInterceptor intercepter() {
//        return new AuditInterceptor();
//    }
//
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        registry.addInterceptor(intercepter());
//
//    }
}
