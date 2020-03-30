package com.zp.boot_redis_seckill.common;
import com.zp.boot_redis_seckill.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * @author zhaopeng
 * @create 2020-03-28 15:49
 */
@Component
public class TestCountDownLatch {
    @Autowired
    private ProductService productService;
    @Autowired
    private RedisTemplate redisTemplate;
    CountDownLatch countDownLatch = new CountDownLatch(1);
    public void runThread(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<10 ;i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        //boolean b = productService.decrementProductStock(1L);
                        //System.err.println(b);
                     //   String name =(String)redisTemplate.opsForValue().get("name");
                       // System.err.println(name);
                        countDownLatch.await();
                        System.out.println("Thread:"+Thread.currentThread().getName()+",time: "+System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        countDownLatch.countDown();
    }
}
