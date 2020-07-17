package ru.relex.delivery.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.relex.delivery.services.model.dish.CreatedDish;
import ru.relex.delivery.services.model.dish.NewDish;
import ru.relex.delivery.services.model.dish.UpdatableDish;

@Mapper
public interface DishMapper {

  @Mapping(target = "mainDishInfo", source = "updateData.mainDishInfo")
  @Mapping(target = "dishType", source = "updateData.dishType")
  @Mapping(target = "dishCalories", source = "updateData.dishCalories")
  @Mapping(target = "dishCookingTimeMinutes", source = "updateData.dishCookingTimeMinutes")
  CreatedDish merge(CreatedDish dish, UpdatableDish updateData);

  @Mapping(target = "dishId", source = "newDishId")
  CreatedDish fromNewDish(NewDish dish, long newDishId);

}
