package com.ojt.kbl.repository;


import com.ojt.kbl.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

      void deleteByAuthId(String authId);
}
