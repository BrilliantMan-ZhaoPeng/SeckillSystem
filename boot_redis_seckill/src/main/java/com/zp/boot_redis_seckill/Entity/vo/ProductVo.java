package com.zp.boot_redis_seckill.Entity.vo;
import com.zp.boot_redis_seckill.Entity.Product;
/**
 * @author zhaopeng
 * @create 2020-03-29 22:32
 */
public class ProductVo {
    private Product product;
    private boolean buy;
    private String orderId;

    public ProductVo() {

    }

    @Override
    public String toString() {
        return "ProductVo{" +
                "product=" + product +
                ", buy=" + buy +
                ", orderId='" + orderId + '\'' +
                '}';
    }

    public Product getProduct() {
        return product;
    }

    public boolean isBuy() {
        return buy;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setBuy(boolean buy) {
        this.buy = buy;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
