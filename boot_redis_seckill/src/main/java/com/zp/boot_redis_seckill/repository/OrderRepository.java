package com.zp.boot_redis_seckill.repository;
import com.zp.boot_redis_seckill.Entity.Order;
import com.zp.boot_redis_seckill.Entity.vo.OrderVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
/**
 * 订单dao
 * @author zhaopeng
 * @create 2020-03-27 21:06
 */
@Mapper
@Repository
public interface OrderRepository {
    @Insert("insert into a_order (order_id,create_time,order_status,product_id,user_id)" +
            "" +
            "values(#{orderId},#{createTime},#{orderStatus},#{productId},#{userId})")
   int insOrder(Order order);

    //查询OrderVo的数据
   @Select("SELECT a.order_id as orderId,a.order_status as orderStatus,a.create_time as createTime,b.product_name as productName,b.product_address as productAddress,c.user_id as userId,c.user_name as userName,c.user_address as userAddress,c.user_phone as userPhone\n" +
           "FROM a_product b\n" +
           "INNER JOIN a_order a on a.product_id=b.product_id INNER JOIN a_user c on c.user_id=a.user_id \n" +
           "where \n" +
           "a.order_id=#{orderId}")
   OrderVo selOrderByPrimaryKey(String orderId);
}
