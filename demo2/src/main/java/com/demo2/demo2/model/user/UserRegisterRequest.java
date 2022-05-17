package com.demo2.demo2.model.user;

import javax.validation.constraints.NotEmpty;

public class UserRegisterRequest {

    private Integer userId;
    
    @NotEmpty(message = "The username is required.")
    private String userName;

    @NotEmpty(message = "The password is required.")
    private String password;

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
