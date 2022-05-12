package com.demo2.demo2.service.user;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import com.demo2.demo2.entity.jpa.user.User;
import com.demo2.demo2.model.user.UserProfileRequest;
import com.demo2.demo2.model.user.UserRegisterRequest;
import com.demo2.demo2.model.user.UserRegisterResponse;
import com.demo2.demo2.model.user.UserRequest;
import com.demo2.demo2.model.user.UserResponse;
// import com.demo2.demo2.repository.jpa.UserAddressRepository;
// import com.demo2.demo2.repository.jpa.UserProfileRepository;
import com.demo2.demo2.repository.jpa.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    // @Autowired
    // private UserProfileRepository userProfileRepository;

    // @Autowired
    // private UserAddressRepository userAddressRepository;

    @Transactional
    public UserResponse createUser(UserRequest userRequest){
        
        User user = new User();
        user.setUserCode(userRequest.getUserCode());
        user.setUserName(userRequest.getUserName());
        user.setPassword(userRequest.getPassword());
        user.setIsEnabled(userRequest.getIsEnabled());
        user.setIsLocked(userRequest.getIsLocked());
        user.setExpiredDate(userRequest.getExpiredDate());
        user.setCreatedBy(userRequest.getCreatedBy());
        user.setCreatedAt(userRequest.getCreatedAt());
        user.setDeleteFlag(userRequest.getDeleteFlag());
        user = userRepository.save(user);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUserCode(user.getUserCode());
        userResponse.setUserName(user.getUserName());
        userResponse.setIsEnabled(user.getIsEnabled());
        userResponse.setIsLocked(user.getIsLocked());
        userResponse.setExpiredDate(user.getExpiredDate());
        userResponse.setCreatedBy(user.getCreatedBy());
        userResponse.setCreatedAt(user.getCreatedAt());
        return userResponse;

    }

    public User updateUser(UserRequest userRequest){
        Optional<User> updateUser = userRepository.findByUserName(userRequest.getUserName());
        User user = updateUser.get();
        user.setUserCode(userRequest.getUserCode());
        user.setUserName(userRequest.getUserName());
        user.setPassword(userRequest.getPassword());
        user.setIsEnabled(userRequest.getIsEnabled());
        user.setIsLocked(userRequest.getIsLocked());
        user.setExpiredDate(userRequest.getExpiredDate());
        user.setCreatedBy(userRequest.getCreatedBy());
        user.setCreatedAt(userRequest.getCreatedAt());
        user.setDeleteFlag(userRequest.getDeleteFlag());
        return userRepository.save(user);
    }


    public void deleteUser(UserRequest userRequest){
        Optional<User> deleteUser = userRepository.findByUserName(userRequest.getUserName());
        userRepository.delete(deleteUser.get());
    }

    @Transactional
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest){
        User user = new User();
        user.setUserCode(userRegisterRequest.getUserName());
        user.setUserName(userRegisterRequest.getUserName());
        user.setPassword(userRegisterRequest.getPassword());
        user.setIsEnabled("Y");
        user.setIsLocked("N");
        user.setExpiredDate(new Date());
        user.setCreatedBy(1);
        user.setCreatedAt(new Date());
        user.setDeleteFlag("N");
        user = userRepository.save(user);

        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setUserId(user.getId());
        userRegisterResponse.setUserName(user.getUserName());
        return userRegisterResponse;
    }

    
}
