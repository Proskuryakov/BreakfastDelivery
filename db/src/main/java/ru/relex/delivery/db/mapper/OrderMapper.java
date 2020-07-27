package ru.relex.delivery.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
 import ru.relex.delivery.db.model.OrderModel;

@Mapper
public interface OrderMapper {

    OrderModel createOrder(OrderModel order);
    //language=postgreSQL
    @Select("" +
            "SELECT  o.order_id AS id, " +
            "       created_at," +
            "       phone," +
            "       city," +
            "       street," +
            "       house," +
            "       flat," +
            "      entrance," +
            "       floor," +
            "status_id  "+
            "FROM orders o "+
            "WHERE  order_id = #{id}"

    )
    OrderModel findById(@Param("id") long id);
//    @Select("" +
//            "SELECT   order_id AS orderId, " +
//            "       dish_id AS dishId," +
//            "       count," +
//            "FROM dishesOfOrder   "+
//            "WHERE  order_id = #{orderId}"
//
//    )
    void addPositionOfOrder(@Param("order_id") long orderId,@Param("dish_id") long dishId, @Param("count") long count );





}


