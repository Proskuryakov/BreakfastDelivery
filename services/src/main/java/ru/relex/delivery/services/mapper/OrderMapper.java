package ru.relex.delivery.services.mapper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;

import ru.relex.delivery.services.model.order.*;

@Mapper
public interface OrderMapper {
    @Qualifier
    @Retention(RetentionPolicy.CLASS)
    @interface DefaultStatusMapper {
    }

    @Qualifier
    @Retention(RetentionPolicy.CLASS)
    @interface DefaultCreatedAtMapper {
    }
    @Mapping(target = "check", source = "order.check")
    @Mapping(target = "id", source = "order.id")
    @Mapping(target = "address", source = "order.address" )
    @Mapping(target = "createdAt", source = "order.createdAt" )
    @Mapping(target = "listOfDishes", source = "order.listOfDishes" )
    @Mapping(target = "status", source = "updateData.status")
    CreatedOrder merge(CreatedOrder order, UpdatableOrder updateData);

    @Mapping(target = "check", source = "resCheck")
    @Mapping(target = "id", source = "newId")
    @Mapping(target = "createdAt", source = "order", qualifiedBy = DefaultCreatedAtMapper.class)
    @Mapping(target = "status", source = "order", qualifiedBy = DefaultStatusMapper.class)
    CreatedOrder fromNewOrder(NewOrder order, long newId,   double resCheck);

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