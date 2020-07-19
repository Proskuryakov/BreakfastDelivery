package ru.relex.delivery.services.model.order;


    public enum Statuses {

        ORDER_IN_PROCESSING,//в обработке
        ORDER_PREPARING,//заказ готовится
        ORDER_DELIVERY,//доставка заказа
        ORDER_DELIVERED,//заказ доставлен
        ORDER_CANCELLED//заказ отменен
    }

