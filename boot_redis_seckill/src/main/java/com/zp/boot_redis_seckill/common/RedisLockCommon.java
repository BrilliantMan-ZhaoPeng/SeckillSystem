package com.zp.boot_redis_seckill.common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;
import java.time.Duration;

/**
 * redis分布式锁实现的工具类
 * @author zhaopeng
 * @create 2020-03-27 16:45
 */
@Component
public class RedisLockCommon {
     @Autowired
     private StringRedisTemplate stringRedisTemplate;
    /**
     * redis加锁的实现
     * @param key
     * @param value
     * @return
     */
     public boolean tryLock(String key,String value){
         //setIfAbsent(key,value)  表示的是key不存在的时候存入值，存在的时候返回false
       if(stringRedisTemplate.opsForValue().setIfAbsent(key,value)){//
           System.err.println("执行插入key ----  value");
           return true;
       }else{//表示key已经存在了
           String currentValue = stringRedisTemplate.opsForValue().get(key);     //获取上一个锁的时间 如果高并发的情况可能会出现已经被修改的问题  所以多一次判断保证线程的安全
           if(!StringUtils.isEmpty(currentValue)&&Long.valueOf(currentValue)<System.currentTimeMillis()){
               String oldValue=stringRedisTemplate.opsForValue().getAndSet(key,value);
               if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                   return true;
               }
           }
       }
         //获取redis中该key所存的值
       return false;
     }

    /**
     *
     * 当在3000ms内用户不断提交表单且redis中可以获取到jsessionid的值,表示是重复提交
     *
     *
     * redis加锁的实现
     * @param key
     * @param value
     * @return
     */
    public boolean tryLock(String key,String value,int timeout){
        //setIfAbsent(key,value)  表示的是key不存在的时候存入值，存在的时候返回false
        //表示key不存在
        if(stringRedisTemplate.opsForValue().setIfAbsent(key,value,Duration.ofSeconds(timeout))){//
            return true;
        }else{//表示key存在,返回false,表示的是重复提交
            return false;
        }
    }

    /**
     * Redis的解锁操作
     * @param key
     * @param value
     */
     public void unLock(String key,String value){
         String currentValue = stringRedisTemplate.opsForValue().get(key);
         try {
             if(!StringUtils.isEmpty(currentValue)&&currentValue.equals(value)){
                 //解锁成功！！！
                 System.err.println("解锁成功！！！");
                 stringRedisTemplate.opsForValue().getOperations().delete(key);
             }
         }catch (Exception e){

         }
     }


     public void unLock(String key){
         String currentValue = stringRedisTemplate.opsForValue().get(key);
         try {
             if(!StringUtils.isEmpty(currentValue)){
                 //解锁成功！！！
                 System.err.println("解锁成功！！！");
                 stringRedisTemplate.opsForValue().getOperations().delete(key);
             }
         }catch (Exception  e){

         }
     }
}
