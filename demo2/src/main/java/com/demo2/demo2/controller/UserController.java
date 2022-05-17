package com.demo2.demo2.controller;

import com.demo2.demo2.model.user.UserRegisterResponse;
import com.demo2.demo2.model.user.UserRequest;
import com.demo2.demo2.service.user.UserService;
import com.demo2.demo2.model.user.UserRegisterRequest;
import com.demo2.demo2.entity.jpa.user.User;
import com.demo2.demo2.model.common.SuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")        
    public ResponseEntity<SuccessResponse<UserRegisterResponse>> register(@RequestBody UserRegisterRequest userRegisterRequest){
        UserRegisterResponse userRegisterResponse = userService.register(userRegisterRequest);
        SuccessResponse<UserRegisterResponse> successResponse = new SuccessResponse<UserRegisterResponse>();
        successResponse.setData(userRegisterResponse);
        return ResponseEntity.ok(successResponse);
    }

    // @PostMapping("/register1")        
    // public ResponseEntity<SuccessResponse<UserRegisterResponse>> register1(@RequestBody UserRegisterRequest userRegisterRequest){
    //     return ResponseEntity.ok(new SuccessResponse<UserRegisterResponse>(userService.register(userRegisterRequest)));
    // }

    @PatchMapping("/update")      
    public ResponseEntity<SuccessResponse<User>> update(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(new SuccessResponse<User>(userService.updateUser(userRequest)));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<SuccessResponse<String>> delete(@RequestBody UserRequest userRequest) {
      userService.deleteUser(userRequest);
      return ResponseEntity.ok(new SuccessResponse<String>());
    }
}
