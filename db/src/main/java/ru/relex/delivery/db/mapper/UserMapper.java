package ru.relex.delivery.db.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ru.relex.delivery.db.model.UserModel;

@Mapper
public interface UserMapper {

    UserModel createUser(UserModel user);

    //language=PostgreSQL
    @Select("" +
            "SELECT u.user_id AS id, " +
        "           username," +
            "       first_name," +
            "       last_name," +
            "       created_by," +
            "       created_at," +
            "       is_locked AS locked," +
            "       is_active AS active," +
            "       phone," +
            "       email," +
            "       u.role_id AS userRole " +
            "FROM users u " +
            "LEFT JOIN roles r on u.role_id = r.role_id " +
            "WHERE user_id = #{id}"
    )
    UserModel findById(@Param("id") long id);
}
