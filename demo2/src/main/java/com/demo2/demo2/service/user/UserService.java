package com.demo2.demo2.service.user;

import java.util.*;

import javax.transaction.Transactional;

import com.demo2.demo2.constant.StatusCode;
import com.demo2.demo2.entity.jpa.user.*;
import com.demo2.demo2.exception.BaseException;
import com.demo2.demo2.exception.BusinessException;
import com.demo2.demo2.model.user.*;
import com.demo2.demo2.repository.jpa.*;
import com.demo2.demo2.security.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Transactional
    public UserResponse createUser(UserRequest userRequest){
        
        User user = new User();
        user.setUserCode(userRequest.getUserCode());
        user.setUserName(userRequest.getUserName());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setIsEnabled("Y");
        user.setIsLocked("N");
        user.setExpiredDate(new Date());
        user.setCreatedBy(1);
        user.setCreatedAt(new Date());
        user.setDeleteFlag("N");
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

    @Transactional
    public UserProfileResponse createUserProfile(UserProfileRequest userProfileRequest,Integer id){
        User user = userRepository.getById(id);
        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);
        userProfile.setFirstNameTh(userProfileRequest.getFirstNameTh());
        userProfile.setLastNameTh(userProfileRequest.getLastNameTh());
        userProfile.setFirstNameEn(userProfileRequest.getFirstNameEn());
        userProfile.setLastNameEn(userProfileRequest.getLastNameEn());
        userProfile.setMobileNumber(userProfileRequest.getMobileNumber());
        userProfile.setCreatedBy(1);
        userProfile.setCreatedAt(new Date());
        userProfile.setDeleteFlag("N");
        userProfile = userProfileRepository.save(userProfile);

        UserProfileResponse userProfileResponse = new UserProfileResponse();
        userProfileResponse.setId(userProfile.getId());
        userProfileResponse.setUser(userProfile.getUser());
        userProfileResponse.setFirstNameTh(userProfile.getFirstNameTh());
        userProfileResponse.setLastNameTh(userProfile.getLastNameTh());
        userProfileResponse.setFirstNameEn(userProfile.getFirstNameEn());
        userProfileResponse.setLastNameEn(userProfile.getLastNameEn());
        userProfileResponse.setMobileNumber(userProfile.getMobileNumber());
        userProfileResponse.setBirthDate(userProfile.getBirthDate());
        userProfileResponse.setCreatedBy(userProfile.getCreatedBy());
        userProfileResponse.setCreatedAt(userProfile.getCreatedAt());
        userProfileResponse.setDeleteFlag(userProfile.getDeleteFlag());
        return userProfileResponse; 
    }

    @Transactional
    public UserAddressResponse createUserAddress(UserAddressRequest userAddressRequest,Integer id){
        User user = userRepository.getById(id);
        UserAddress userAddress = new UserAddress();
        userAddress.setUser(user);
        userAddress.setLine1(userAddressRequest.getLine1());
        userAddress.setLine2(userAddressRequest.getLine2());
        userAddress.setType(userAddressRequest.getType());
        userAddress.setPostCode(userAddressRequest.getPostCode());
        userAddress.setPrefer(userAddressRequest.getPrefer());
        userAddress.setCreatedBy(1);
        userAddress.setCreatedAt(new Date());
        userAddress.setDeleteFlag("N");
        userAddress = userAddressRepository.save(userAddress);

        UserAddressResponse userAddressResponse = new UserAddressResponse();
        userAddressResponse.setId(userAddress.getId());
        userAddressResponse.setUser(userAddress.getUser());
        userAddressResponse.setLine1(userAddress.getLine1());
        userAddressResponse.setLine2(userAddress.getLine2());
        userAddressResponse.setPostCode(userAddress.getPostCode());
        userAddressResponse.setType(userAddress.getType());
        userAddressResponse.setPrefer(userAddress.getPrefer());
        userAddressResponse.setCreatedBy(userAddress.getCreatedBy());
        userAddressResponse.setCreatedAt(userAddress.getCreatedAt());
        userAddressResponse.setDeleteFlag(userAddress.getDeleteFlag());
        return userAddressResponse;
        
    }

    @Transactional
    public User updateUser(UserRequest userRequest){
        User user = userRepository.findByUserName(userRequest.getUserName());
        user.setUserCode(userRequest.getUserCode());
        user.setUserName(userRequest.getUserName());
        user.setPassword(user.getPassword());
        user.setIsEnabled("Y");
        user.setIsLocked("N");
        user.setExpiredDate(new Date());
        user.setCreatedBy(1);
        user.setCreatedAt(new Date());
        user.setDeleteFlag("N");
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(UserRequest userRequest){
        User deleteUser = userRepository.findByUserName(userRequest.getUserName());
        if(deleteUser != null) {
			throw new BusinessException(StatusCode.ERR_CODE_401, StatusCode.ERR_DESC_401);
		}
        userRepository.delete(deleteUser);
    }

    @Transactional
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest){
        User user = new User();
        user.setUserCode(userRegisterRequest.getUserName());
        user.setUserName(userRegisterRequest.getUserName());
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
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

    @Transactional
    public UserResponse createUserAndProfile(UserRequest userRequest){
        UserResponse userResponse = new UserResponse();
        User user = new User();
        user.setUserCode(userRequest.getUserCode());
        user.setUserName(userRequest.getUserName());
        user.setPassword(userRequest.getPassword());
        user.setIsEnabled("Y");
        user.setIsLocked("N");
        user.setExpiredDate(new Date());
        user.setCreatedBy(1);
        user.setCreatedAt(new Date());
        user.setDeleteFlag("N");
        user = userRepository.save(user);

        UserProfileRequest userProfileRequest = userRequest.getProfile();
        UserProfileResponse userProfileResponse = new UserProfileResponse();
        if(userProfileRequest != null){
            UserProfile userProfile = new UserProfile();
            userProfile.setUser(user);
            userProfile.setFirstNameTh(userProfileRequest.getFirstNameTh());
            userProfile.setLastNameTh(userProfileRequest.getLastNameTh());
            userProfile.setFirstNameEn(userProfileRequest.getFirstNameEn());
            userProfile.setLastNameEn(userProfileRequest.getLastNameEn());
            userProfile.setMobileNumber(userProfileRequest.getMobileNumber());
            userProfile.setBirthDate(userProfileRequest.getBirthDate());
            userProfile.setCreatedBy(user.getId());
            userProfile.setCreatedAt(new Date());
            userProfile.setDeleteFlag("N");
            userProfile = userProfileRepository.save(userProfile);

            userProfileResponse.setFirstNameTh(userProfile.getFirstNameTh());
            userProfileResponse.setLastNameTh(userProfile.getLastNameTh());
            userProfileResponse.setFirstNameEn(userProfile.getFirstNameEn());
            userProfileResponse.setLastNameEn(userProfile.getLastNameEn());
            userProfileResponse.setMobileNumber(userProfile.getMobileNumber());
            userProfileResponse.setBirthDate(userProfile.getBirthDate());
            userProfileResponse.setCreatedBy(userProfile.getCreatedBy());
            userProfileResponse.setCreatedAt(userProfile.getCreatedAt());
            userProfileResponse.setDeleteFlag(userProfile.getDeleteFlag());
        }
        

        List<UserAddressRequest> address = userRequest.getAddresses();
        List<UserAddressResponse> addressResponses = new ArrayList<UserAddressResponse>();
        if(address != null && !address.isEmpty()){
            UserAddressResponse userAddressResponse = null;
            for(UserAddressRequest userAddressRequest : address){
                UserAddress userAddress = new UserAddress();
                userAddress.setUser(user);
                userAddress.setLine1(userAddressRequest.getLine1());
                userAddress.setLine2(userAddressRequest.getLine2());
                userAddress.setPostCode(userAddressRequest.getPostCode());
                userAddress.setType(userAddressRequest.getType());
                userAddress.setPrefer(userAddressRequest.getPrefer());
                userAddress.setCreatedBy(user.getId());
                userAddress.setCreatedAt(new Date());
                userAddress.setDeleteFlag("N");
                userAddressRepository.save(userAddress);

                userAddressResponse = new UserAddressResponse();
                userAddressResponse.setUser(userAddress.getUser());
                userAddressResponse.setLine1(userAddress.getLine1());
                userAddressResponse.setLine2(userAddress.getLine2());
                userAddressResponse.setPostCode(userAddress.getPostCode());
                userAddressResponse.setType(userAddress.getType());
                userAddressResponse.setPrefer(userAddress.getPrefer());
                userAddressResponse.setCreatedBy(userAddress.getCreatedBy());
                userAddressResponse.setCreatedAt(userAddress.getCreatedAt());
                userAddressResponse.setDeleteFlag(userAddress.getDeleteFlag());
                addressResponses.add(userAddressResponse);
            }
        }

        userResponse.setId(user.getId());
        userResponse.setUserCode(user.getUserCode());
        userResponse.setUserName(user.getUserName());
        userResponse.setPassword(user.getPassword());
        userResponse.setIsEnabled(user.getIsEnabled());
        userResponse.setIsLocked(user.getIsLocked());
        userResponse.setExpiredDate(user.getExpiredDate());
        userResponse.setCreatedBy(user.getCreatedBy());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setDeleteFlag(user.getDeleteFlag());
        userResponse.setProfile(userProfileResponse);
        userResponse.setAddress(addressResponses);
        return userResponse;
    }   

    public UserAuthenResponse authen(UserAuthenRequest userAuthenRequest){
        User user = userRepository.findByUserName(userAuthenRequest.getUserName());
       
        if (user == null) {
            throw new BaseException(HttpStatus.UNAUTHORIZED, StatusCode.ERR_CODE_401, StatusCode.ERR_DESC_401);
        };
        if(!(passwordEncoder.matches(userAuthenRequest.getPassword(), user.getPassword()))){
            throw new BaseException(HttpStatus.UNAUTHORIZED, StatusCode.ERR_CODE_401, StatusCode.ERR_DESC_401);
        }

        String token = jwtUtil.generateToken(user.getUserName());
        UserAuthenResponse userAuthenResponse = new UserAuthenResponse();
        userAuthenResponse.setUserId(user.getId());
        userAuthenResponse.setUserName(user.getUserName());
        userAuthenResponse.setToken(token);

        return userAuthenResponse;
    }

    public UserAuthenResponse refreshToken(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUserName(username);
        String token = jwtUtil.generateToken(username);
        UserAuthenResponse userAuthenResponse = new UserAuthenResponse();
        userAuthenResponse.setUserId(user.getId());
        userAuthenResponse.setUserName(user.getUserName());
        userAuthenResponse.setToken(token);
        return userAuthenResponse;
    }
}
