package ru.relex.delivery.db.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import ru.relex.delivery.commons.model.RestaurantType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes({RestaurantType.class})
@MappedJdbcTypes({JdbcType.INTEGER, JdbcType.BIGINT})
public class RestaurantTypeHandler implements TypeHandler<RestaurantType> {
  @Override
  public void setParameter(PreparedStatement ps, int i, RestaurantType parameter, JdbcType jdbcType) throws SQLException {
    if (parameter == null) {
      ps.setNull(i, jdbcType.TYPE_CODE);
    } else {
      ps.setInt(i, parameter.getId());
    }
  }

  @Override
  public RestaurantType getResult(ResultSet rs, String columnName) throws SQLException {
    return RestaurantType.fromId(rs.getInt(columnName));
  }

  @Override
  public RestaurantType getResult(ResultSet rs, int columnIndex) throws SQLException {
    return RestaurantType.fromId(rs.getInt(columnIndex));
  }

  @Override
  public RestaurantType getResult(CallableStatement cs, int columnIndex) throws SQLException {
    return RestaurantType.fromId(cs.getInt(columnIndex));
  }
}
