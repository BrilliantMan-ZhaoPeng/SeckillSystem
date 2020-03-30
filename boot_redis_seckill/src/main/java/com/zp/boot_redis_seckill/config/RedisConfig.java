package com.zp.boot_redis_seckill.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 配置RedisConfig属性，配置序列化的redisTemplate
 * @author zhaopeng
 * @create 2020-03-27 16:39
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate initRedisTemplate(RedisConnectionFactory factory){
      RedisTemplate redisTemplate=new RedisTemplate();
      redisTemplate.setConnectionFactory(factory);
      redisTemplate.setKeySerializer(new StringRedisSerializer());
      redisTemplate.setHashKeySerializer(new StringRedisSerializer());
      redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
      redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
      redisTemplate.setDefaultSerializer(new StringRedisSerializer());
      redisTemplate.afterPropertiesSet();
      return redisTemplate;
    }
}
