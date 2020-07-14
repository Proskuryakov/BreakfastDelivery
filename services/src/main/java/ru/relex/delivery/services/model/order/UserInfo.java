package ru.relex.delivery.services.model.order;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.validator.constraints.Length;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;
import ru.relex.delivery.services.validation.ValidationErrorsOrder;

import javax.validation.constraints.Pattern;

@Value.Immutable
@JsonDeserialize(builder = ImmutableUserInfo.Builder.class)
public interface UserInfo {
    @Nullable
    @Length(
            min = 1,
            max = 50,
            message = ValidationErrorsOrder.FIRST_NAME_LENGTH_IS_INVALID
    )
    String getFirstName();

    @Nullable
    @Length(
            min = 1,
            max = 50,
            message = ValidationErrorsOrder.LAST_NAME_LENGTH_IS_INVALID
    )
    String getLastName();

    @Nullable
    @Pattern(regexp = "\\d{5,15}", message = "PHONE_FORMAT_IS_INVALID")
    String getPhone();
}
