package com.example.CommonService.service;


import com.example.CommonService.entity.CommonEntity;
import com.example.CommonService.repo.CommonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private CommonRepo commonRepo ;

    @Override
    public CommonEntity saveEntity(CommonEntity commonEntity) {
        return commonRepo.save(commonEntity);
    }

//    @Override
//    public CommonEntity saveEntity(CommonEntity commonEntity) {
//        return commonRepo.save(commonEntity);
//
//    }
}
