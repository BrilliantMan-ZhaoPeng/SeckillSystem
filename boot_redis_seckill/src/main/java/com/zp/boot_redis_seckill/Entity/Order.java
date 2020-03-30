package com.zp.boot_redis_seckill.Entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
 * 订单类的创建
 * @author zhaopeng
 * @create 2020-03-27 20:51
 */
@Entity
@Table(name="a_order")
public class Order {
    @Id
    private String orderId;

    //保存user的id，，，，相当于是外键
    @Column
    private Long userId;

    //保存product的id    相当于是外键
    @Column
    private Long productId;

    //支付状态    0,未支付     1,支付   默认设置为0
    @Column
    private int orderStatus;

    //订单创建时间
    private Date createTime;

    public Order() {

    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", userId=" + userId +
                ", productId=" + productId +
                ", orderStatus=" + orderStatus +
                ", createTime=" + createTime +
                '}';
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
