package com.zp.boot_redis_seckill.controller;
import com.zp.boot_redis_seckill.Entity.Order;
import com.zp.boot_redis_seckill.Entity.vo.OrderVo;
import com.zp.boot_redis_seckill.Entity.vo.ResultVo;
import com.zp.boot_redis_seckill.common.NoRepeatSubmit;
import com.zp.boot_redis_seckill.service.OrderService;
import com.zp.boot_redis_seckill.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.UUID;

/**
 * @author zhaopeng
 * @create 2020-03-27 22:05
 */
@Controller
public class MainController {
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    //执行抢购的提交
    @GetMapping("/submit")
    @ResponseBody
    @NoRepeatSubmit(timeOut = 20)//在20s内不能一直重复提交
    public ResultVo submit(Order order){
        System.err.println(order);
        ResultVo resultVo=null;
        if(!productService.decrementProductStock(order.getProductId())){
            System.err.println("库存不足");
            resultVo=new ResultVo(false,"库存不足！！！");
            return resultVo;
        }
        order.setOrderStatus(0);
        order.setCreateTime(new Date());
        String uuid = UUID.randomUUID().toString();
        order.setUserId(1L);///默认设置为id为1L的用户
        order.setOrderId(uuid);
        orderService.insOrder(order);
        System.err.println("抢购成功！！！");
        //将信息导入到redis中，避免多次抢购
        return new ResultVo(true,"抢购成功！！！",uuid);
    }

    @RequestMapping("/")
    public String toIndex(Model model,Long userId){
        //在跳转到抢购的主页的时候需要判断该用户是否已经抢购成功(当redis数据库存在)
        return orderService.toIndex(1L,model);
    }

    @GetMapping("/success")
    public String toSuccess(String orderId, Model model){
        OrderVo ordervo = orderService.getOrderById(orderId);
        model.addAttribute("order",ordervo);
        return "success";
    }
}
