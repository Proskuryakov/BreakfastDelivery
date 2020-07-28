package ru.relex.delivery.db.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import ru.relex.delivery.commons.model.StatusesOfOrder;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes({StatusesOfOrder.class})
@MappedJdbcTypes({JdbcType.INTEGER, JdbcType.BIGINT})

public class StatusTypeOfOrderHandler implements TypeHandler<StatusesOfOrder> {
    @Override
    public void setParameter(PreparedStatement ps, int i, StatusesOfOrder parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            ps.setNull(i, jdbcType.TYPE_CODE);
        } else {
            ps.setInt(i, parameter.getId());
        }
    }

    @Override
    public StatusesOfOrder getResult(ResultSet rs, String columnName) throws SQLException {
        return StatusesOfOrder.fromId(rs.getInt(columnName));
    }

    @Override
    public StatusesOfOrder getResult(ResultSet rs, int columnIndex) throws SQLException {
        return StatusesOfOrder.fromId(rs.getInt(columnIndex));
    }

    @Override
    public StatusesOfOrder getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return StatusesOfOrder.fromId(cs.getInt(columnIndex));
    }
}
