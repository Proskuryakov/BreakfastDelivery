package ru.relex.delivery.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.relex.delivery.db.model.DishModel;
import ru.relex.delivery.services.model.dish.CreatedDish;
import ru.relex.delivery.services.model.dish.NewDish;
import ru.relex.delivery.services.model.dish.UpdatableDish;

import java.time.Instant;

@Mapper
public interface DishStruct {

  @Mapping(target = "mainDishInfo", source = "updateData.mainDishInfo")
  @Mapping(target = "dishType", source = "updateData.dishType")
  @Mapping(target = "dishCalories", source = "updateData.dishCalories")
  @Mapping(target = "dishCookingTimeMinutes", source = "updateData.dishCookingTimeMinutes")
  @Mapping(target = "dishImage", source = "updateData.dishImage")
  CreatedDish merge(CreatedDish dish, UpdatableDish updateData);

  @Mapping(target = "dishName", source = "dish.mainDishInfo.dishName")
  @Mapping(target = "dishPrice", source = "dish.mainDishInfo.dishPrice")
  @Mapping(target = "dishCalories", source = "dish.dishCalories")
  @Mapping(target = "dishCookingTimeMinutes", source = "dish.dishCookingTimeMinutes")
  @Mapping(target = "dishImage", source = "dish.dishImage")
  @Mapping(target = "restaurantId", source = "restaurantId")
  DishModel fromNewDish(NewDish dish, long restaurantId);

  @Mapping(target = "mainDishInfo.dishName", source = "model.dishName")
  @Mapping(target = "mainDishInfo.dishPrice", source = "model.dishPrice")
  @Mapping(target = "dishCalories", source = "model.dishCalories")
  @Mapping(target = "dishCookingTimeMinutes", source = "model.dishCookingTimeMinutes")
  @Mapping(target = "id", source = "id")
  CreatedDish toCreatedDish(DishModel model, long id);

  @Mapping(target = "mainDishInfo.dishName", source = "dishName")
  @Mapping(target = "mainDishInfo.dishPrice", source = "dishPrice")
  @Mapping(target = "dishCalories", source = "dishCalories")
  @Mapping(target = "dishCookingTimeMinutes", source = "dishCookingTimeMinutes")
  CreatedDish toCreatedDish(DishModel byId);

  CreatedDish[] toCreatedDishes(DishModel[] all);
  
  CreatedDish[] toCreatedDishesByRestaurantId(DishModel[] byRestaurantId);

}
