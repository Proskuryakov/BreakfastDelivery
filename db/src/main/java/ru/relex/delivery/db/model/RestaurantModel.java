package ru.relex.delivery.db.model;


import lombok.*;
import ru.relex.delivery.commons.model.RestaurantType;

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

  private String startWorkDay;
  private String endWorkDay;

  private RestaurantType restaurantType;

}
