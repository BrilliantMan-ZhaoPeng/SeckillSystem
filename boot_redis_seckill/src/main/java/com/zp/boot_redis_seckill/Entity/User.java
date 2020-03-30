package com.zp.boot_redis_seckill.Entity;

import javax.persistence.*;

/**
 * @author zhaopeng
 * @create 2020-03-29 15:07
 */
@Table(name="a_user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增
    private Long userId;
    //买家姓名
    @Column
    private String userName;
    //密码
    @Column
    private String userPwd;
    //买家地址
    @Column
    private String userAddress;
    //买家电话号码
    @Column
    private String userPhone;

    public User() {

    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Long getUserId() {

        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }
}
