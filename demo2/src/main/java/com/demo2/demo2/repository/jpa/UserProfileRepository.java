package com.demo2.demo2.repository.jpa;

import com.demo2.demo2.entity.jpa.user.UserProfile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    
}
