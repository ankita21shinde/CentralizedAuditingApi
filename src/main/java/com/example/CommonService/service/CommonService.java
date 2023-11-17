package com.example.CommonService.service;


import com.example.CommonService.entity.CommonEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public interface CommonService {

    @Bean
    public CommonEntity saveEntity(CommonEntity commonEntity);
}
