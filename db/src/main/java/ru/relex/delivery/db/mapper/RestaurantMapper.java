package ru.relex.delivery.db.mapper;

import org.apache.ibatis.annotations.Param;
import ru.relex.delivery.commons.model.DishType;
import ru.relex.delivery.commons.model.RestaurantType;
import ru.relex.delivery.db.model.RestaurantModel;

public interface RestaurantMapper {

  RestaurantModel createRestaurant(RestaurantModel restaurant);

  void saveRestaurantType(
    @Param("restaurantId") long restaurantId,
    @Param("restaurantType") RestaurantType restaurantType
  );

}
