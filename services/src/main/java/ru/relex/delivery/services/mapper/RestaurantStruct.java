package ru.relex.delivery.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;
import ru.relex.delivery.db.model.RestaurantModel;
import ru.relex.delivery.services.model.restaurant.CreatedRestaurant;
import ru.relex.delivery.services.model.restaurant.NewRestaurant;
import ru.relex.delivery.services.model.restaurant.UpdatableRestaurant;
import ru.relex.delivery.commons.model.RestaurantType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Mapper
public interface RestaurantStruct {

  @Mapping(target = "restaurantName", source = "restaurant.restaurantName")
  @Mapping(target = "street", source = "restaurant.address.street")
  @Mapping(target = "building", source = "restaurant.address.building")
  @Mapping(target = "startWorkDay", source = "restaurant.workingHours.startWorkDay", dateFormat = "HH:mm")
  @Mapping(target = "endWorkDay", source = "restaurant.workingHours.endWorkDay", dateFormat = "HH:mm")
  RestaurantModel fromNewRestaurant(NewRestaurant restaurant);

  @Mapping(target = "restaurantName", source = "model.restaurantName")
  @Mapping(target = "address.street", source = "model.street")
  @Mapping(target = "address.building", source = "model.building")
  @Mapping(target = "workingHours.startWorkDay", source = "model.startWorkDay")
  @Mapping(target = "workingHours.endWorkDay", source = "model.endWorkDay")
  @Mapping(target = "restaurantType", source = "restaurantType")
  @Mapping(target = "id", source = "id")
  CreatedRestaurant toCreatedRestaurant(RestaurantModel model, RestaurantType restaurantType, long id);

  @Mapping(target = "restaurantName", source = "model.restaurantName")
  @Mapping(target = "address.street", source = "model.street")
  @Mapping(target = "address.building", source = "model.building")
  @Mapping(target = "workingHours.startWorkDay", source = "model.startWorkDay")
  @Mapping(target = "workingHours.endWorkDay", source = "model.endWorkDay")
  @Mapping(target = "restaurantType", source = "restaurantType")
  CreatedRestaurant toCreatedRestaurant(RestaurantModel model);

  @Mapping(target = "restaurantName", source = "updatableRestaurant.restaurantName")
  @Mapping(target = "restaurantType", source = "updatableRestaurant.restaurantType")
  @Mapping(target = "address", source = "updatableRestaurant.address")
  @Mapping(target = "workingHours", source = "updatableRestaurant.workingHours")
  @Mapping(target = "restaurantImage", source = "updatableRestaurant.restaurantImage")
  CreatedRestaurant merge(CreatedRestaurant restaurant, UpdatableRestaurant updatableRestaurant);

}
