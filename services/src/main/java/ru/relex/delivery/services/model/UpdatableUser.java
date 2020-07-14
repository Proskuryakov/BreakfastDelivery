package ru.relex.delivery.services.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(builder = ImmutableUpdatableUser.Builder.class)
public interface UpdatableUser extends BaseUser {

  String getPassword();

  UserStatus getStatus();
}
