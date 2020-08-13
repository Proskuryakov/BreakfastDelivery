package ru.relex.delivery.db.model;

import lombok.*;
import ru.relex.delivery.commons.model.DishType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {
        "dishName",
        "dishPrice",
        "dishType",
        "dishCalories",
        "dishImage"

})
public class UpdatableDishModel {
    private String dishName;
    private Integer dishPrice;
    private DishType dishType;
    private long dishId;
    private long restaurantId;

    private Integer dishCalories;
    private Integer dishCookingTimeMinutes;
    private String dishImage;
}
