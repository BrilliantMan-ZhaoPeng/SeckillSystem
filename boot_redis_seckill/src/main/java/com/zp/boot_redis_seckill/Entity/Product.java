package com.zp.boot_redis_seckill.Entity;
import javax.persistence.*;
/**
 * 产品信息类
 * @author zhaopeng
 * @create 2020-03-27 20:07
 */
@Entity
@Table(name = "a_product")
public class Product{
    //产品的id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    //产品名称
    @Column
    private String productName;

    //库存
    @Column
    private int productStock;

    //发货地
    @Column
    private String productAddress;

    public Product() {

    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productStock=" + productStock +
                ", productAddress='" + productAddress + '\'' +
                '}';
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public void setProductAddress(String productAddress) {
        this.productAddress = productAddress;
    }

    public Long getProductId() {

        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductStock() {
        return productStock;
    }

    public String getProductAddress() {
        return productAddress;
    }
}
