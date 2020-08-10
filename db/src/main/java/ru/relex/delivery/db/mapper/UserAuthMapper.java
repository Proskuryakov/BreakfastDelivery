package ru.relex.delivery.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ru.relex.delivery.db.model.UserModel;
import ru.relex.delivery.db.model.UserProfile;

import java.util.Optional;

@Mapper
public interface UserAuthMapper {

    //language=PostgreSQL
    @Select("" +
            "SELECT u.user_id AS id, " +
            "       username," +
            "       password," +
            "       is_locked AS locked," +
            "       is_active AS active," +
            "       u.role_id AS userRole " +
            "FROM users u " +
            "LEFT JOIN roles r on u.role_id = r.role_id " +
            "WHERE LOWER(username) = LOWER(#{searchString})" +
            "   OR LOWER(EMAIL) = LOWER(#{searchString})" +
            "   OR phone = #{searchString}"
    )
    Optional<UserModel> getUserByUsernameOrEmailOrPhone(String searchString);

    //language=PostgreSQL
    @Select(
            "SELECT u.user_id AS id, " +
                    "       username," +
                    "       first_name," +
                    "       last_name," +
                    "       email," +
                    "       phone," +
                    "       u.role_id AS role " +
                    "FROM users u " +
                    "LEFT JOIN roles r on u.role_id = r.role_id " +
                    "WHERE username = #{username}"
    )
    UserProfile getProfileInfo(String username);

}
