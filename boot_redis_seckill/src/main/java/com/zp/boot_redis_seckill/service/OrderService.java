package com.zp.boot_redis_seckill.service;
import com.zp.boot_redis_seckill.Entity.Order;
import com.zp.boot_redis_seckill.Entity.Product;
import com.zp.boot_redis_seckill.Entity.vo.OrderVo;
import com.zp.boot_redis_seckill.Entity.vo.ProductVo;
import com.zp.boot_redis_seckill.repository.OrderRepository;
import com.zp.boot_redis_seckill.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.thymeleaf.util.StringUtils;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author zhaopeng
 * @create 2020-03-27 22:09
 */
@Service
@PropertySource("classpath:application.properties")
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${redis-key}")
    private String KEY;
    public int insOrder(Order order){
        //dec_store_success_userId_productId////存入成功id的格式
        String key=KEY+"success_"+order.getUserId()+"_"+order.getProductId();
        //存到redis中去
        System.err.println("抢购成功的key------"+key);
        redisTemplate.opsForValue().set(key,order.getOrderId());
        int i = orderRepository.insOrder(order);
        return i;
    }

    //根据orderId获取订单
    public OrderVo getOrderById(String orderId){
        return orderRepository.selOrderByPrimaryKey(orderId);
    }



    //判断用户是否购买成功或拍下某些产品
    public String toIndex(Long userId,Model model){
        //获取所有的产品信息
        List<Product> products = productRepository.selAllProduct();
        String key = "*" + KEY + "success_" + 1 + "_*";
        //获取该用户所有成功的产品id
        Set<String> keys = redisTemplate.keys(key);
        List<ProductVo> lists=new ArrayList<>();
        //循环迭代
        products.stream().forEach(product -> {
            ProductVo productVo=new ProductVo();
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
      // System.err.println(lists);
        model.addAttribute("lists",lists);
        return "index";
    }
}
