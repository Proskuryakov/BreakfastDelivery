package ru.relex.delivery.db.model;

import lombok.*;
import ru.relex.delivery.commons.model.DishType;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {
  "dishName",
  "dishPrice",
  "dishCalories",
  "dishCookingTimeMinutes",
  "restaurant_id",
     "dishImage"
})
public class DishModel {

  private long id;
  private String dishName;
  private Integer dishPrice;
  private Integer dishCalories;
  private Integer dishCookingTimeMinutes;
  private DishType dishType;
  private String dishImage;
  private Integer restaurantId;


}
