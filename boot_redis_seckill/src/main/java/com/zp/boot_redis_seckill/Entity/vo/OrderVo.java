package com.zp.boot_redis_seckill.Entity.vo;
import java.util.Date;
/**
 * 承载数据表单的类
 * @author zhaopeng
 * @create 2020-03-29 15:38
 */
public class OrderVo {
    //订单Id
    private String orderId;
    //订单状态
    private int orderStatus;
    //创建时间
    private Date createTime;
    //产品名称
    private String productName;
    //产地
    private String productAddress;
    //用户Id
    private Long userId;
    //用户名
    private String userName;
    //用户地址
    private String userAddress;
    //用户电话
    private String userPhone;

    public OrderVo() {
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "orderId='" + orderId + '\'' +
                ", orderStatus=" + orderStatus +
                ", createTime=" + createTime +
                ", productName='" + productName + '\'' +
                ", productAddress='" + productAddress + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductAddress(String productAddress) {
        this.productAddress = productAddress;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getOrderId() {

        return orderId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductAddress() {
        return productAddress;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }
}
