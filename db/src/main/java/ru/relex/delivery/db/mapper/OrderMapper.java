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
            "SELECT order_id, " +
            "       created_at," +
            "       phone," +
            "       city," +
            "       street," +
            "       house," +
            "       flat," +
            "      entrance," +
            "       floor," +
            "       status_id," +

            "FROM orders "
    )

    OrderModel findById(@Param("order_id") long order_id);

}


