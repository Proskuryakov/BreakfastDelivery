package ru.relex.delivery.db.model;

import lombok.*;

import java.time.Instant;

public class PositionInOrder {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode(exclude = {
            "order_id",
    })

    public class OrderModel {
        public long order_id;
        public long position_id;
        public long count;
    }
}
