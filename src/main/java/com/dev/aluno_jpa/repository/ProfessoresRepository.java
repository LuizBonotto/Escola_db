package com.dev.aluno_jpa.repository;

import com.dev.aluno_jpa.entity.ProfessoresEntity;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository

@EnableAutoConfiguration
public class ProfessoresRepository {

    private static String SELECT_ALL = "select * from professores";
    private static String INSERT = "insert into professores (id, nome, email) values (?, ?, ?)";
    private static String DELETE = "delete from professores where id = ?";
    private static String UPDATE = "update professores set nome = ?, email = ? where id = ?";

    private JdbcTemplate jdbcTemplate;

    public ProfessoresRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ProfessoresEntity insert (ProfessoresEntity professores) {
        Object[] professor = new Object[]{
                professores.getId(),
                professores.getNome(),
                professores.getEmail()
        };
        jdbcTemplate.update(INSERT, professor);
        return professores;
    }

    public List<ProfessoresEntity> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<ProfessoresEntity>(){
            @Override
            public ProfessoresEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProfessoresEntity professores = new ProfessoresEntity();
                professores.setId(rs.getLong("id"));
                professores.setNome(rs.getString("nome"));
                professores.setEmail(rs.getString("email"));
                return professores;
            }
        });
    }

    public int remove (Long id) {
        return jdbcTemplate.update(DELETE, id);
    }

    public ProfessoresEntity update (ProfessoresEntity professores) {
        Object[] professor = new Object[]{
                professores.getNome(),
                professores.getEmail(),
                professores.getId()
        };
        jdbcTemplate.update(UPDATE, professor);
        return professores;
    }

}
