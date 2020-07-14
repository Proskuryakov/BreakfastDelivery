package ru.relex.uber.services.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableNewUser.Builder.class)
@Value.Style(redactedMask = "*****")
public interface NewUser extends BaseUser {

  String getUsername();

  @Value.Redacted
  String getPassword();
}
