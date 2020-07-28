package ru.relex.delivery.services.model.order;

import org.springframework.lang.Nullable;
import ru.relex.delivery.commons.model.PositionInOrder;
import ru.relex.delivery.services.validation.ValidationErrorsOrder;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public interface BaseOrder {
    @Nullable
    @NotEmpty(message = ValidationErrorsOrder.LIST_OF_DISHES_MUST_BE_SET)
    List<PositionInOrder> getListOfDishes();

    @Valid
    @Nullable
    @NotNull(message = ValidationErrorsOrder.PERSONAL_INFO_MUST_BE_SET)
    Address getAddress();

    @Nullable
    @NotNull
    @Pattern(regexp = "\\d{5,15}", message = "PHONE_FORMAT_IS_INVALID")
    String getPhone();
}
