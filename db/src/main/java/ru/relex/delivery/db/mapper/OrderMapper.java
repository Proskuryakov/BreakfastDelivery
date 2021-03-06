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
            "SELECT  order_id  as id   , " +
            "       created_at," +
            "user_id as userId," +
            "       phone," +
            "       city," +
            "       street," +
            "       house," +
            "       flat," +
            "      entrance," +
            "       floor," +
            "       status_id  as statusId ," +
            "       checkres as checkres  " +
            "FROM orders   " +
            "WHERE  order_id = #{order_id}"
    )
    OrderModel getOrderByOrderId(@Param("order_id") long order_id);
    @Select("" +
            "SELECT  order_id as id  , " +
            "user_id as userId," +
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
            "FROM orders   "
    )
    List<OrderModel> getOrders( );
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
    @Select("" +
            "SELECT  order_id  as id   , " +
            "       created_at," +
            "user_id as userId," +
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
            "WHERE  user_id = #{user_id}"
    )
    OrderModel getOrderByUserId(@Param("user_id") long user_id);

    void deleteOrder(@Param("order_id") long order_id );
    void deletePosition(@Param("order_id") long order_id );
    @Select("" +
            "SELECT  order_id  as id     " +
            "FROM orders   " +
            "WHERE  status_id = #{status_id}"
    )
List<OrderModel>  getCountOrdersByOrderStatus(@Param("status_id") long status_id );

}


