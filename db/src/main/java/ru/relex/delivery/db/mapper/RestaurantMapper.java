package ru.relex.delivery.db.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ru.relex.delivery.commons.model.DishType;
import ru.relex.delivery.commons.model.RestaurantType;
import ru.relex.delivery.db.model.RestaurantModel;

public interface RestaurantMapper {

  RestaurantModel createRestaurant(RestaurantModel restaurant);

  void updateRestaurant(RestaurantModel model);

  void saveRestaurantType(
    @Param("restaurantId") long restaurantId,
    @Param("restaurantType") RestaurantType restaurantType
  );

  void updateRestaurantType(
    @Param("restaurantId") long restaurantId,
    @Param("restaurantType") RestaurantType restaurantType
  );

  //language=PostgreSQL
  @Select("" +
    "SELECT r.restaurant_id AS id, " +
    "       restaurant_name," +
    "       street," +
    "       building," +
    "       start_work_day," +
    "       end_work_day," +
    "       restaurant_type_id AS restaurant_type," +
    "       restaurant_image " +
    "FROM restaurants r " +
    "INNER JOIN restaurant_restaurant_types rt ON r.restaurant_id = rt.restaurant_id " +
    "WHERE r.restaurant_id = #{id}"
  )
  RestaurantModel getById(@Param("id") long id);

  //language=PostgreSQL
  @Select("" +
    "SELECT r.restaurant_id AS id, " +
    "       restaurant_name," +
    "       street," +
    "       building," +
    "       start_work_day," +
    "       end_work_day," +
    "       restaurant_type_id AS restaurant_type," +
    "       restaurant_image " +
    "FROM restaurants r " +
    "INNER JOIN restaurant_restaurant_types rt ON r.restaurant_id = rt.restaurant_id "
  )
  RestaurantModel[] getAll();

  void deleteRestaurant(@Param("id") long id);
}
