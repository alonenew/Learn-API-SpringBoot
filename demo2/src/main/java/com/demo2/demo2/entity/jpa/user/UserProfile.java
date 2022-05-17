package com.demo2.demo2.entity.jpa.user;

import java.util.Date;

import javax.persistence.*;

import com.demo2.demo2.entity.common.BaseEntity;


@Entity
@Table(name = "user_profile")
public class UserProfile extends BaseEntity{

    
    @Column(name = "first_name_th", nullable = false, length = 256)
    private String firstNameTh;

    @Column(name = "last_name_th", nullable = false, length = 256)
    private String lastNameTh;

    @Column(name = "first_name_en", nullable = false, length = 256)
    private String firstNameEn;

    @Column(name = "last_name_en", nullable = false, length = 256)
    private String lastNameEn;

    @Column(name = "mobile_number", nullable = false, length = 20)
    private String mobileNumber;

    @Column(name = "birth_date", nullable = true)
    private Date birthDate;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstNameTh() {
        return firstNameTh;
    }

    public void setFirstNameTh(String firstNameTh) {
        this.firstNameTh = firstNameTh;
    }

    public String getLastNameTh() {
        return lastNameTh;
    }

    public void setLastNameTh(String lastNameTh) {
        this.lastNameTh = lastNameTh;
    }

    public String getFirstNameEn() {
        return firstNameEn;
    }

    public void setFirstNameEn(String firstNameEn) {
        this.firstNameEn = firstNameEn;
    }

    public String getLastNameEn() {
        return lastNameEn;
    }

    public void setLastNameEn(String lastNameEn) {
        this.lastNameEn = lastNameEn;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
}
