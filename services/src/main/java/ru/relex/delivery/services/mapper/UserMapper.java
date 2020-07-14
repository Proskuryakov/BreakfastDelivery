package ru.relex.delivery.services.mapper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;
import ru.relex.uber.services.model.ExistingUser;
import ru.relex.uber.services.model.ImmutableUserStatus;
import ru.relex.uber.services.model.NewUser;
import ru.relex.uber.services.model.UpdatableUser;
import ru.relex.uber.services.model.UserStatus;

@Mapper
public interface UserMapper {

  @Mapping(target = "status", source = "updateData.status")
  @Mapping(target = "personalInfo", source = "updateData.personalInfo")
  @Mapping(target = "role", source = "updateData.role")
  ExistingUser merge(ExistingUser user, UpdatableUser updateData);

  @Qualifier
  @Retention(RetentionPolicy.CLASS)
  @interface DefaultStatusMapper {
  }

  @Mapping(target = "id", source = "newId")
  @Mapping(target = "status", source = "user", qualifiedBy = DefaultStatusMapper.class)
  ExistingUser fromNewUser(NewUser user, long newId);

  @DefaultStatusMapper
  default UserStatus defaultStatusMapper(@SuppressWarnings("unused") NewUser user) {
    return ImmutableUserStatus
      .builder()
      .build();
  }

}
