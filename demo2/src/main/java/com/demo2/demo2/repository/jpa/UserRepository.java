package com.demo2.demo2.repository.jpa;


import com.demo2.demo2.entity.jpa.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User , Integer> {

   User findByUserName(String userName);
}
