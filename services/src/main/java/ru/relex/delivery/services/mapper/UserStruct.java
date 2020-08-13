package ru.relex.delivery.services.mapper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.Instant;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Qualifier;
import ru.relex.delivery.db.model.UserModel;
import ru.relex.delivery.services.model.user.*;

@Mapper
public interface UserStruct {

  @Mapping(target = "status", source = "updateData.status")
  @Mapping(target = "personalInfo", source = "updateData.personalInfo")
  @Mapping(target = "userRole", source = "updateData.userRole")
  ExistingUser merge(ExistingUser user, UpdatableUser updateData);

  @Mapping(target = "firstName", source = "user.personalInfo.firstName")
  @Mapping(target = "lastName", source = "user.personalInfo.lastName")
  @Mapping(target = "email", source = "user.personalInfo.email")
  @Mapping(target = "phone", source = "user.personalInfo.phone")
  @Mapping(target = "active", constant = "true")
  @Mapping(target = "locked", constant = "false")
  @Mapping(target = "password", source = "password")
  UserModel fromNewUser(NewUser user, String password, Long createdBy);

  @Mapping(target = "personalInfo.firstName", source = "model.firstName")
  @Mapping(target = "personalInfo.lastName", source = "model.lastName")
  @Mapping(target = "personalInfo.email", source = "model.email")
  @Mapping(target = "personalInfo.phone", source = "model.phone")
  @Mapping(target = "id", source = "id")
  @Mapping(target = "createdAt", source = "createdAt")
  @Mapping(target = "status.isActive", source = "model.active")
  @Mapping(target = "status.isLocked", source = "model.locked")
  ExistingUser toExistingUser(UserModel model, long id, Instant createdAt);

  @Mapping(target = "personalInfo.firstName", source = "firstName")
  @Mapping(target = "personalInfo.lastName", source = "lastName")
  @Mapping(target = "personalInfo.email", source = "email")
  @Mapping(target = "personalInfo.phone", source = "phone")
  @Mapping(target = "status.isActive", source = "active")
  @Mapping(target = "status.isLocked", source = "locked")
  ExistingUser toExistingUser(UserModel byId);

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
