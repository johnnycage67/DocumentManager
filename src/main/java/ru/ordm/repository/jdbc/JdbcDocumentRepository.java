package ru.ordm.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ordm.dto.DocType;
import ru.ordm.utils.CustomerRowMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcDocumentRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional
    public List<DocType> getAllDocType(String user){

        List<DocType> entries = new ArrayList<>();
        jdbcTemplate.query("SELECT DISTINCT id,name FROM skl.doc_types, skl.doc_user_actions " +
                           "WHERE id=doc and act in ('VIEW', 'OWN') and usr=? " +
                           "ORDER BY name",
                new Object[]{user},
                (rs,row) -> new DocType(rs.getString("id"), rs.getString("name")))
                .forEach(entry -> entries.add(entry));
        return entries;
    }





}
