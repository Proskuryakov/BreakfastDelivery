package ru.relex.delivery.db.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {
        "resId",
        "userId",
        "dishId",
        "count"
})
public class DishesFromBasketModel {
    private long resId;

    private long userId;
    private long dishId;
    private long count;

 }
