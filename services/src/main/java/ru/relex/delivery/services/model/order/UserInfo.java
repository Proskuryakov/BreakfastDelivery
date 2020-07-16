package ru.relex.delivery.services.model.order;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.validator.constraints.Length;
import org.immutables.value.Value;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import ru.relex.delivery.services.validation.ValidationErrorsOrder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Value.Immutable
@JsonDeserialize(builder = ImmutableUserInfo.Builder.class)
public interface UserInfo {
    @Nullable
    @NotNull @Length(
            min = 1,
            max = 50,
            message = ValidationErrorsOrder.FIRST_NAME_LENGTH_IS_INVALID
    )
    String getFirstName();

    @Nullable
    @NotNull
    @Length(
            min = 1,
            max = 50,
            message = ValidationErrorsOrder.LAST_NAME_LENGTH_IS_INVALID
    )
    String getLastName();

    @Nullable
    @NotNull
    @Pattern(regexp = "\\d{5,15}", message = "PHONE_FORMAT_IS_INVALID")
    String getPhone();

    @NonNull
    @Value.Derived
    default String getFullName() {

        if ((getFirstName() == null || getFirstName().isBlank()) && (getLastName() == null || getLastName().isBlank())) {
            return "";
        }
        if (getLastName() == null) {
            String newFirstName = changeName(getFirstName());
            return newFirstName;
        }
        if (getFirstName() == null) {
            String newLastName = changeName(getLastName());

            return newLastName;
        }
        String newFirstName = changeName(getFirstName());
        String newLastName = changeName(getLastName());

        return newFirstName + " " + newLastName;
    }

    default String changeName(String str) {
        String newstr = str.strip();
        String first = newstr.substring(0, 1).toUpperCase();
        String last = "";
        if (newstr.length() > 1) {
            last = newstr.substring(1).toLowerCase();
        }
        String name = first + last;
        return name;
    }
}
