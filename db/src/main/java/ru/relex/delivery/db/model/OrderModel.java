package ru.relex.delivery.db.model;
import lombok.*;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {
        "created_at",
        "phone",
        "city",
        "street",
        "house",
        "flat",
        "entrance",
        "floor",
        "status_id"
})

public class OrderModel {
    public long order_id;
    public Instant created_at;
    public String city;
    public String street;
    public String house;
    public String flat;
    public String entrance;
    public String floor;
    public long status_id;
     public String phone;


}
