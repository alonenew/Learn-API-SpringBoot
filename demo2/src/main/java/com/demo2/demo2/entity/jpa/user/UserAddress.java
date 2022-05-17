package com.demo2.demo2.entity.jpa.user;

import javax.persistence.*;

import com.demo2.demo2.entity.common.BaseEntity;


@Entity
@Table(name = "address")
public class UserAddress extends BaseEntity {
    
    @Column(name = "line_1", nullable = false, length = 512)
    private String line1;

    @Column(name = "line_2", nullable = false, length = 512)
    private String line2;

    @Column(name = "postcode", nullable = false, length = 20)
    private String postCode;

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @Column(name = "prefer", nullable = false, length = 1)
    private String prefer;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrefer() {
        return prefer;
    }

    public void setPrefer(String prefer) {
        this.prefer = prefer;
    }

}
