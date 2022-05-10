package com.demo2.demo2.entity.jpa.user;

import java.util.Date;

import javax.persistence.*;

import com.demo2.demo2.entity.common.BaseEntity;


@Entity
@Table(name = "user")
public class User extends BaseEntity {

    
    @Column(name = "user_code", nullable = false)
    private String userCode;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_Enabled", nullable = false)
    private String isEnabled;

    @Column(name = "is_Locked", nullable = false)
    private String isLocked;

    @Column(name = "expired_date", nullable = false)
    private Date expiredDate;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }
    
}
