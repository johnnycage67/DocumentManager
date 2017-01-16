package ru.ordm.utils;


import org.springframework.jdbc.core.RowMapper;
import ru.ordm.dto.DocType;


import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        DocType docType = new DocType();
        docType.setId(rs.getString("ID"));
        docType.setName(rs.getString("NAME"));

        return docType;
    }
}
