package ru.relex.delivery.db.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;
import ru.relex.delivery.commons.model.DishType;
import ru.relex.delivery.db.model.DishModel;
import ru.relex.delivery.db.model.DishTypesModel;
import ru.relex.delivery.db.model.OrderModel;
import ru.relex.delivery.db.model.UpdatableDishModel;

@Mapper
public interface DishMapper {

    DishModel createDish(DishModel dish);

    //language=PostgreSQL
    @Select("" +
            "SELECT d.dish_id AS id, " +
            "       dish_name," +
            "       dish_price," +
            "       dish_calories," +
            "       dish_cooking_time_minutes," +
            "       dish_type_id AS dish_type," +
            "       dish_image," +
            "       restaurant_id " +
            "FROM dishes d " +
            "INNER JOIN dish_dish_types dt ON d.dish_id = dt.dish_id " +
            "WHERE d.dish_id = #{id}"
    )
    DishModel findById(@Param("id") long id);

    //language=PostgreSQL
    @Select("" +
            "SELECT d.dish_id AS id, " +
            "       dish_name," +
            "       dish_price," +
            "       dish_calories," +
            "       dish_cooking_time_minutes," +
            "       dish_type_id AS dish_type," +
            "       dish_image," +
            "       restaurant_id " +
            "FROM dishes d " +
            "INNER JOIN dish_dish_types dt ON d.dish_id = dt.dish_id " +
            "WHERE d.restaurant_id = #{restaurantId}"

    )
    DishModel[] findByRestaurantId(@Param("restaurantId") long id);

    //language=PostgreSQL
    @Select("" +
            "SELECT d.dish_id AS id, " +
            "       dish_name," +
            "       dish_price," +
            "       dish_calories," +
            "       dish_cooking_time_minutes," +
            "       dish_type_id AS dish_type," +
            "       dish_image," +
            "       restaurant_id " +
            "FROM dishes d " +
            "INNER JOIN dish_dish_types dt ON d.dish_id = dt.dish_id"
    )
    DishModel[] findAll();

    void saveDishType(@Param("dishId") long dishId, @Param("dishType") DishType dishType);

    void deleteDishById(@Param("dish_id") long disId );

    DishModel updateDishById(UpdatableDishModel dishModel );
    void updateDishType( @Param("dishId") long dishId, @Param("dishType") Integer dishType );

    //language=PostgreSQL
    @Select("" +
            "SELECT dt.name AS dishType, " +
            "       d.dish_image AS dishImage " +
            "FROM dish_types dt " +
            "INNER JOIN (" +
            "SELECT dish_type_id, " +
            "            MIN(dish_id) AS dish_id" +
            "            FROM dish_dish_types " +
            "            GROUP BY dish_type_id" +
            ") ddt ON dt.dish_type_id = ddt.dish_type_id " +
            "LEFT JOIN dishes d on ddt.dish_id = d.dish_id "
    )
    DishTypesModel[] findAllTypes();

}
