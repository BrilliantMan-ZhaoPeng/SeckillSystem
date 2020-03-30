package com.zp.boot_redis_seckill.repository;
import com.zp.boot_redis_seckill.Entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @autho * 产品dao zhaopeng
 * @create 2020-03-27 20:48
 */
@Mapper
@Repository
public interface ProductRepository {
    @Select("select * from a_product where product_id=#{productId}")
    Product selProductByPrimaryKey(Long productId);

    int updateProductByPrimaryKey(Product product);

    @Select("select * from a_product")
    List<Product> selAllProduct();
}
