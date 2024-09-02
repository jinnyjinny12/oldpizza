package com.ohgiraffers.pizzahouse.user.repository;

import com.ohgiraffers.pizzahouse.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, Integer> {

    List<UserEntity> findByUserName(String userName);


}
