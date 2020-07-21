package ru.relex.delivery.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;
import ru.relex.delivery.services.model.restaurant.CreatedRestaurant;
import ru.relex.delivery.services.model.restaurant.NewRestaurant;
import ru.relex.delivery.services.model.restaurant.UpdatableRestaurant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Mapper
public interface RestaurantMapper {

  @Mapping(target = "id", source = "newId")
  CreatedRestaurant fromNewRestaurant(NewRestaurant restaurant, long newId);

  @Mapping(target = "restaurantName", source = "updatableRestaurant.restaurantName")
  @Mapping(target = "restaurantType", source = "updatableRestaurant.restaurantType")
  @Mapping(target = "address", source = "updatableRestaurant.address")
  @Mapping(target = "workingHours", source = "updatableRestaurant.workingHours")
  @Mapping(target = "listOfDishes", source = "updatableRestaurant.listOfDishes")
  CreatedRestaurant merge(CreatedRestaurant restaurant, UpdatableRestaurant updatableRestaurant);

}
