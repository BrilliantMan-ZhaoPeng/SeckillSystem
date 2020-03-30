package com.zp.boot_redis_seckill.common;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author zhaopeng
 * @create 2020-03-28 17:22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRepeatSubmit {
    /**
     * 设置锁定的时间
     * @return
     */
    int timeOut() default 10;
}
