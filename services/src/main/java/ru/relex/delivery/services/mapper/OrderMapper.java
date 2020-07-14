package ru.relex.delivery.services.mapper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;

 import ru.relex.uber.services.model.order.*;

@Mapper
public interface OrderMapper {
    @Qualifier
    @Retention(RetentionPolicy.CLASS)
    @interface DefaultStatusMapper {
    }
    @Mapping(target = "check", source = "resCheck")
    @Mapping(target = "id", source = "newId")
    @Mapping(target = "createdAt", source = "time")
    @Mapping(target = "status", source = "order", qualifiedBy = DefaultStatusMapper.class)
    CreatedOrder fromNewOrder(NewOrder order, long newId, String time, double resCheck);

    @DefaultStatusMapper
    default OrderStatus defaultStatusMapper(@SuppressWarnings("unused") NewOrder order) {
        return ImmutableOrderStatus
                .builder()
                .build();
    }


}
