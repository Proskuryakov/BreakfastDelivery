package ru.relex.delivery.services.model.user;

import org.immutables.value.Value;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.util.List;

@Value.Immutable
public interface ExistingUser extends BaseUser {

  long getId();

  String getUsername();

  UserStatus getStatus();

  Instant getCreatedAt();

  @Nullable
  Long getCreatedBy();

}
