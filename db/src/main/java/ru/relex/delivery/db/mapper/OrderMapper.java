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
    //void addPositionOfOrder(@Param("orderId") long orderId,@Param("dishId") long dishId, @Param("count") long count );



    OrderModel findById(@Param("id") long id);

}


