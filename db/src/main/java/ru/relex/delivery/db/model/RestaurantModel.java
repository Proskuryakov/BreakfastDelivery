package ru.relex.delivery.db.model;


import lombok.*;
import ru.relex.delivery.commons.model.RestaurantType;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {
  "restaurantName",
  "street",
  "building",
  "dishCookingTimeMinutes",
  "startWorkDay",
  "endWorkDay",
  "restaurantType",
})
public class RestaurantModel {

  private long id;

  private String restaurantName;

  private String street;
  private String building;

  private LocalTime startWorkDay;
  private LocalTime endWorkDay;

  private RestaurantType restaurantType;

}
