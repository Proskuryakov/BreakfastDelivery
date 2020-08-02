package ru.relex.delivery.services.model.dishesFromBasket;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import ru.relex.delivery.services.model.order.ImmutableNewOrder;
import ru.relex.delivery.services.validation.ValidationErrorsDishesFromBasket;
import ru.relex.delivery.services.validation.ValidationErrorsOrder;

import javax.validation.constraints.NotEmpty;

@Value.Immutable
@JsonDeserialize(builder = ImmutableBaseDishesFromBasket.Builder.class)

public interface BaseDishesFromBasket {

    long getUserId();


    long getDishId();


    long getCount();

}
