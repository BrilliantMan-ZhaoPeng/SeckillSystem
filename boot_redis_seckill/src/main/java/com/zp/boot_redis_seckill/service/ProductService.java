package com.zp.boot_redis_seckill.service;
import com.zp.boot_redis_seckill.Entity.Product;
import com.zp.boot_redis_seckill.common.RedisLockCommon;
import com.zp.boot_redis_seckill.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
/**
 * @author zhaopeng
 * @create 2020-03-27 17:13
 */
@Service
@PropertySource("classpath:application.properties")
public class ProductService {
    @Autowired
    private RedisLockCommon redisLock;
    @Autowired
    private ProductRepository productRepository;
    @Value("${redis-key}")
    private String KEY;
    //减库存的操作
    public boolean decrementProductStock(Long productId){
        String key=KEY+"lock_"+productId;
        System.err.println("key:"+key);
        Long time=System.currentTimeMillis();
        try {
            //如果加锁失败
            if(!redisLock.tryLock(key,String.valueOf(time))){
               return false;
            }
            Product product = productRepository.selProductByPrimaryKey(productId);
            //表示库存不够
            if(product.getProductStock()==0){
               return false;
            }
            //进行减库的操作
            product.setProductStock(product.getProductStock()-1);
            productRepository.updateProductByPrimaryKey(product);
        }catch (Exception e){
          e.printStackTrace();
          return false;
        }finally {
           //解锁
            redisLock.unLock(key,String.valueOf(time));
        }
        return true;
       /* Product product = productRepository.selProductByPrimaryKey(productId);
        //表示库存不够
        if(product.getProductStock()==0){
            return false;
        }
        //进行减库的操作
        product.setProductStock(product.getProductStock()-1);
        productRepository.updateProductByPrimaryKey(product);
        return true;*/
    }
}
