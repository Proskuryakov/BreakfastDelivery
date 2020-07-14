package ru.relex.uber.services.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import ru.relex.uber.services.validation.ValidationErrors;

public interface BaseUser {

  @NotNull(message = ValidationErrors.ROLE_MUST_BE_SET)
  Role getRole();

  @Valid
  @NotNull(message = ValidationErrors.PERSONAL_INFO_MUST_BE_SET)
  PersonalInfo getPersonalInfo();

}
