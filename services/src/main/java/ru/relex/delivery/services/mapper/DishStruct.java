package ru.relex.delivery.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.relex.delivery.commons.model.DishType;
import ru.relex.delivery.db.model.DishModel;
import ru.relex.delivery.db.model.UpdatableDishModel;
import ru.relex.delivery.services.model.dish.CreatedDish;
import ru.relex.delivery.services.model.dish.NewDish;
import ru.relex.delivery.services.model.dish.UpdatableDish;

import java.time.Instant;

@Mapper
public interface DishStruct {


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

  @Mapping(target = "mainDishInfo.dishName", source = "model.dishName")
  @Mapping(target = "mainDishInfo.dishPrice", source = "model.dishPrice")
  @Mapping(target = "dishCalories", source = "model.dishCalories")
  @Mapping(target = "dishCookingTimeMinutes", source = "model.dishCookingTimeMinutes")
  @Mapping(target = "id", source = "id")
  @Mapping(target = "dishImage", source = "model.dishImage")
  @Mapping(target = "restaurantId", source = "restaurantId")
  @Mapping(target = "dishType", source = "dt")
  CreatedDish toCreatedDish(DishModel model, long id , long restaurantId, DishType dt);


  CreatedDish[] toCreatedDishes(DishModel[] all);
  
  CreatedDish[] toCreatedDishesByRestaurantId(DishModel[] byRestaurantId);




}
