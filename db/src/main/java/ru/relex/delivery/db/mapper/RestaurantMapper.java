package ru.relex.delivery.db.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ru.relex.delivery.commons.model.RestaurantType;
import ru.relex.delivery.db.model.RestaurantModel;
import ru.relex.delivery.db.model.RestaurantTypesModel;

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

  //language=PostgreSQL
  @Select("" +
          "SELECT rt.name AS restaurantType, " +
          "       r.restaurant_image AS restaurantImage " +
          "FROM restaurant_types rt " +
          "INNER JOIN (" +
          "SELECT restaurant_type_id, " +
          "            MIN(restaurant_id) AS restaurant_id" +
          "            FROM restaurant_restaurant_types " +
          "            GROUP BY restaurant_type_id" +
          ") rrt ON rt.restaurant_type_id = rrt.restaurant_type_id " +
          "LEFT JOIN restaurants r on rrt.restaurant_id = r.restaurant_id "
  )
  RestaurantTypesModel[] findAllTypes();
}
