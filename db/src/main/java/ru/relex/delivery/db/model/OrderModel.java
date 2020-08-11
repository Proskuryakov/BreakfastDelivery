package ru.relex.delivery.db.model;
import lombok.*;
import ru.relex.delivery.commons.model.PositionInOrder;
import ru.relex.delivery.commons.model.StatusesOfOrder;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {
        "id",
        "userId",
        "createdAt",
        "phone",
        "city",
        "street",
        "checkres",
        "house",
        "flat",
        "entrance",
        "floor",
        "statusId"
})

public class OrderModel {
    private long id;
    private Instant createdAt;
    private String city;
    private long userId;

    private String street;
    private String house;
    private String flat;
    private Double checkres;
    private String entrance;
    private String floor;
    private Integer statusId;
    private String phone;
    private List<PositionInOrder> listOfDishes;
}
