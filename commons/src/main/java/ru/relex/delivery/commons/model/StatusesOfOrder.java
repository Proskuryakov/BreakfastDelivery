package ru.relex.delivery.commons.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;


public enum StatusesOfOrder {

    ORDER_IN_PROCESSING(1),//в обработке
    ORDER_PREPARING(2),//заказ готовится
    ORDER_DELIVERY(3),//доставка заказа
    ORDER_DELIVERED(4),//заказ доставлен
    ORDER_CANCELLED(5);//заказ отменен
    private final int id;

    StatusesOfOrder(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static StatusesOfOrder fromId(Integer id) {
        if (id == null) {
            return null;
        }

        for (var value : values()) {
            if (id.equals(value.id)) {
                return value;
            }
        }

        return null;
    }
}

