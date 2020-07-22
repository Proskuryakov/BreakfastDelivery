package ru.relex.delivery.services.mapper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.Instant;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;

import ru.relex.delivery.db.model.OrderModel;
import ru.relex.delivery.services.model.order.*;

@Mapper
public interface OrderStruct {
    CreatedOrder toCreatedOrder(OrderModel byId);

    @Qualifier
    @Retention(RetentionPolicy.CLASS)
    @interface DefaultStatusMapper {
    }

    @Qualifier
    @Retention(RetentionPolicy.CLASS)
    @interface DefaultCreatedAtMapper {
    }

    @Mapping(target = "status", source = "updateData.status")
    CreatedOrder merge(CreatedOrder order, UpdatableOrder updateData);

    @Mapping(target = "phone", source = "order.phone")
    @Mapping(target = "house", source = "order.address.house")
    @Mapping(target = "street", source = "order.address.street")
    @Mapping(target = "city", source = "order.address.city")
    @Mapping(target = "flat", source = "order.address.flat")
    @Mapping(target = "entrance", source = "order.address.entrance")
    @Mapping(target = "floor", source = "order.address.floor")

    OrderModel fromNewOrder(NewOrder order,  double resCheck);

    @Mapping(target = "address.house", source = "model.house")
    @Mapping(target = "address.street", source = "model.street")
    @Mapping(target = "address.city", source = "model.city")
    @Mapping(target = "address.flat", source = "model.flat")
    @Mapping(target = "address.entrance", source = "model.entrance")
    @Mapping(target = "address.floor", source = "model.floor")
    @Mapping(target = "phone", source = "model.phone")
     @Mapping(target = "createdAt", source = "createdAt")
     CreatedOrder toCreatedOrder(OrderModel model, long id, Instant createdAt);

//    @Mapping(target = "address.house", source = "model.house")
//    @Mapping(target = "address.street", source = "model.street")
//    @Mapping(target = "address.city", source = "model.city")
//    @Mapping(target = "address.flat", source = "model.flat")
//    @Mapping(target = "address.entrance", source = "model.entrance")
//    @Mapping(target = "address.floor", source = "model.floor")
//    @Mapping(target = "phone", source = "model.phone")
//    @Mapping(target = "order_id", source = "id")
//    @Mapping(target = "createdAt", source = "createdAt")
//    @Mapping(target = "status", source = "model.status")
//    CreatedOrder toCreatedOrder(OrderModel byId);
    @DefaultStatusMapper
    default OrderStatus defaultStatusMapper(@SuppressWarnings("unused") NewOrder order) {
        return ImmutableOrderStatus
                .builder()
                .build();
    }

    @DefaultCreatedAtMapper
    default CreatedAt defaultCreatedAtMapper(@SuppressWarnings("unused") NewOrder order) {
        return ImmutableCreatedAt
                .builder()
                .build();
    }


}