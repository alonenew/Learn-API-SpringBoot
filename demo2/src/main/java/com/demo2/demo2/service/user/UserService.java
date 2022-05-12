package com.demo2.demo2.service.user;

import com.demo2.demo2.entity.jpa.user.User;
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
        userResponse.setDeleteFlag(user.getDeleteFlag());
        return userResponse;
    }
}
