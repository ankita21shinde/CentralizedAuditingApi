package com.example.CommonService.repo;

import com.example.CommonService.entity.CommonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonRepo extends JpaRepository<CommonEntity,Long> {

}
