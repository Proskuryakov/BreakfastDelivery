package ru.relex.delivery.db.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {
        "restaurantImage"
})
public class RestaurantTypesModel {
    private String restaurantType;
    private String restaurantImage;
}