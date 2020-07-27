package ru.relex.delivery.db.model;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {
        "dishid",
        "count",

})
public class PositionInOrderModel {
    private long dishid;
    private long count;

}
