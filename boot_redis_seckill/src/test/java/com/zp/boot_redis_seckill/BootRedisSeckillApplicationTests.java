package com.zp.boot_redis_seckill;
import com.sun.scenario.effect.impl.prism.PrDrawable;
import com.zp.boot_redis_seckill.Entity.Order;
import com.zp.boot_redis_seckill.Entity.Product;
import com.zp.boot_redis_seckill.Entity.vo.ProductVo;
import com.zp.boot_redis_seckill.common.TestCountDownLatch;
import com.zp.boot_redis_seckill.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@SpringBootTest
@PropertySource("classpath:application.properties")
class BootRedisSeckillApplicationTests {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private TestCountDownLatch testCountDownLatch;

    @Value("${redis-key}")
    private String KEY;
    @Test
    void contextLoads() {
        Product product =
                productRepository.selProductByPrimaryKey(1L);
        System.err.println(product);
    }

    @Test
    void test() {
        Product product=new Product();
        product.setProductId(1L);
        product.setProductStock(0);
        System.err.println(product);
        int i = productRepository.updateProductByPrimaryKey(product);
        System.err.println(i);
    }

    @Test
    void test2(){
      // Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent("name", "asdasdasdas");
       // redisTemplate.opsForValue().set("dec_store_a","6666666666666");
       //System.err.println(aBoolean);
    }

    @Test
    void test3(){
        Set<String> dec_store = redisTemplate.keys("*dec_store_success*");
        Iterator<String> iterator = dec_store.iterator();
        while(iterator.hasNext()){
            System.err.println(iterator.next().toString());
        }
    }



    @Test
    void test4() {
        //获取所有的产品信息
        List<Product> products = productRepository.selAllProduct();
        System.err.println(products);
        String key = "*" + KEY + "success_" + 1 + "_*";
        //获取该用户所有成功的产品id
        Set<String> keys = redisTemplate.keys(key);
        List<ProductVo> lists=new ArrayList<>();
        ProductVo productVo=new ProductVo();
        //循环迭代
        products.stream().forEach(product -> {
            Iterator<String> iterator = keys.iterator();
            productVo.setProduct(product);
            productVo.setBuy(false);
            while(iterator.hasNext()) {
                String tempKey=iterator.next().toString();
                char c = tempKey.charAt(tempKey.length() - 1);
                int productId = Integer.parseInt(c + "");
                if (product.getProductId() == productId) {
                     productVo.setBuy(true);
                     productVo.setOrderId(redisTemplate.opsForValue().get(tempKey).toString());
                     break;
                 }
             }
            iterator=keys.iterator();
            lists.add(productVo);
        });
        System.err.println(lists);
    }
}
