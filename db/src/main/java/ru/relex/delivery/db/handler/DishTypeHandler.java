package ru.relex.delivery.db.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import ru.relex.delivery.commons.model.DishType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes({DishType.class})
@MappedJdbcTypes({JdbcType.INTEGER, JdbcType.BIGINT})
public class DishTypeHandler implements TypeHandler<DishType>  {

  @Override
  public void setParameter(PreparedStatement ps, int i, DishType parameter, JdbcType jdbcType) throws SQLException {
    if (parameter == null) {
      ps.setNull(i, jdbcType.TYPE_CODE);
    } else {
      ps.setInt(i, parameter.getId());
    }
  }

  @Override
  public DishType getResult(ResultSet rs, String columnName) throws SQLException {
    return DishType.fromId(rs.getInt(columnName));
  }

  @Override
  public DishType getResult(ResultSet rs, int columnIndex) throws SQLException {
    return DishType.fromId(rs.getInt(columnIndex));
  }

  @Override
  public DishType getResult(CallableStatement cs, int columnIndex) throws SQLException {
    return DishType.fromId(cs.getInt(columnIndex));
  }
}
