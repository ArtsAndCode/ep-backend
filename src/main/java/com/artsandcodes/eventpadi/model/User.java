package com.artsandcodes.eventpadi.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;

/**
 * Created by prestige on 11/17/2017.
 */
@Entity
@Table(name = "auth_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;
    private String mobile;
    private String password;
    private String userType;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "country_id")
//    private Country country;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "role_id")
//    @JsonManagedReference
//    private Role role;
    private short active;
    private short blocked;
    private short deleted;

    private Timestamp lastLoginDate;
    private Timestamp createdDate;
    private Timestamp updatedDate;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getActive() {
        return active;
    }

    public void setActive(short active) {
        this.active = active;
    }

    public short getBlocked() {
        return blocked;
    }

    public void setBlocked(short blocked) {
        this.blocked = blocked;
    }

    public Timestamp getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Timestamp lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public short getDeleted() {
        return deleted;
    }

    public void setDeleted(short deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                ", deleted=" + deleted +
                ", lastLoginDate=" + lastLoginDate +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
