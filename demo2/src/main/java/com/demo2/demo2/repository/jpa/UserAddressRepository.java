package com.demo2.demo2.repository.jpa;

import com.demo2.demo2.entity.jpa.user.UserAddress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Integer>{
    

}
