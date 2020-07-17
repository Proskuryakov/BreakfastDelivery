package ru.relex.delivery.services.model.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;
import ru.relex.delivery.services.validation.ValidationErrorsUser;

@Value.Immutable
@JsonDeserialize(builder = ImmutablePersonalInfo.Builder.class)
public interface PersonalInfo {

  @Nullable
  @Length(
    min = 1,
    max = 50,
    message = ValidationErrorsUser.FIRST_NAME_LENGTH_IS_INVALID
  )
  String getFirstName();

  @Nullable
  @Length(
    min = 1,
    max = 50,
    message = ValidationErrorsUser.LAST_NAME_LENGTH_IS_INVALID
  )
  String getLastName();

  @Pattern(regexp = "[a-zA-Z\\d-_.]+@[a-zA-Z\\d-_.]{3,}", message = ValidationErrorsUser.EMAIL_HAS_INVALID_FORMAT)
  String getEmail();

  @Pattern(regexp = "\\d{5,15}", message = "PHONE_FORMAT_IS_INVALID")
  String getPhone();
}
