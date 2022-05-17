package com.demo2.demo2.model.user;

import javax.validation.constraints.NotEmpty;

public class UserAuthenResponse {

    private Integer userId;
    
    @NotEmpty(message = "The Username is Require")
    private String userName;

    private String token;

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
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
    
}
