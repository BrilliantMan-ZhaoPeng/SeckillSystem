package com.zp.boot_redis_seckill.common;

import com.zp.boot_redis_seckill.Entity.vo.ResultVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaopeng
 * @create 2020-03-28 17:26
 */
@Aspect
@Component
public class RepeatSubmitAspect {
    @Autowired
    private RedisLockCommon redisLock;
    @Autowired
    private HttpServletRequest request;
    @Value("redis-key")
    private String KEY;
    @Pointcut(value = "@annotation(noRepeatSubmit)",argNames = "noRepeatSubmit")
    public void pointCut(NoRepeatSubmit noRepeatSubmit){

    }

    @Around(value = "pointCut(noRepeatSubmit)",argNames = "pjp,noRepeatSubmit")
    public Object around(ProceedingJoinPoint pjp,NoRepeatSubmit noRepeatSubmit){
        //获取得到过期的时间
        int lockSeconds = noRepeatSubmit.timeOut();
        //得到用户的id;   解决在不同浏览器抢购的问题
        String userId = request.getParameter("userId");
        String key = KEY+"lock-"+userId;
        //得到sessionId
        String sessionId = request.getSession().getId();
        //获取浏览器的信息
        //String browerInfo = request.getHeader("User-Agent");
        boolean b = redisLock.tryLock(userId, sessionId, lockSeconds);
        if(b){
            // 获取锁成功
            Object result=null;
            try {
                // 执行进程
                Object proceed = pjp.proceed();
                result=proceed;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return result;
        }else{
            return new ResultVo(false,"抢购失败,请重试！！！");
        }
    }
}
