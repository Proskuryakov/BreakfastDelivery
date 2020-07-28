package ru.relex.delivery.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ru.relex.delivery.commons.model.PositionInOrder;
import ru.relex.delivery.db.model.OrderModel;
import ru.relex.delivery.db.model.PositionInOrderModel;

import java.util.List;

@Mapper
public interface OrderMapper {

    OrderModel createOrder(OrderModel order);

    //language=postgreSQL
    @Select("" +
            "SELECT  order_id   , " +
            "       created_at," +
            "       phone," +
            "       city," +
            "       street," +
            "       house," +
            "       flat," +
            "      entrance," +
            "       floor," +
            "       status_id  as statusId ," +
            "       checkres  " +
            "FROM orders   " +
            "WHERE  order_id = #{order_id}"
    )
    OrderModel getOrder(@Param("order_id") long order_id);

    @Select("" +
            "SELECT  dish_id  as dishId , " +
            "       count " +
            "FROM dishesFromOrder   " +
            "WHERE  order_id = #{order_id}"
    )
    List<PositionInOrderModel> getPositionOfOrder(@Param("order_id") long order_id);
    void addPositionOfOrder(@Param("order_id") long orderId, @Param("dish_id") long dishId, @Param("count") long count);

    OrderModel updateOrder(@Param("order_id") long order_id, @Param("status_id") long status_id);
    //OrderModel getOrder(@Param("position_id") long orderId  );


    void deleteOrder(@Param("order_id") long order_id );
    void deletePosition(@Param("order_id") long order_id );


}


