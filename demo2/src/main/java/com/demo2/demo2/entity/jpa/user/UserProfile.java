package com.demo2.demo2.entity.jpa.user;

import java.util.Date;

import javax.persistence.*;

import com.demo2.demo2.entity.common.BaseEntity;


@Entity
@Table(name = "user_profile")
public class UserProfile extends BaseEntity{

    
    @Column(name = "first_name_th", nullable = false)
    private String firstNameTh;

    @Column(name = "last_name_th", nullable = false)
    private String lastNameTh;

    @Column(name = "first_name_en", nullable = false)
    private String firstNameEn;

    @Column(name = "last_name_en", nullable = false)
    private String lastNameEn;

    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;

    @Column(name = "birth_date", nullable = true)
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
