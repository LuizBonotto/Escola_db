package com.dev.aluno_jpa.repository;

import com.dev.aluno_jpa.entity.AlunosEntity;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@EnableAutoConfiguration
public class AlunosRepository {

    private static String SELECT_ALL = "select * from alunos";
    private static String INSERT = "insert into alunos (id, nome, email) values (?, ?, ?)";
    private static String DELETE = "delete from alunos where id = ?";
    private static String UPDATE = "update alunos set nome = ?, email = ? where id = ?";

    private JdbcTemplate jdbcTemplate;

    public AlunosRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public AlunosEntity insert (AlunosEntity alunos) {
        Object[] aluno = new Object[]{
                alunos.getId(),
                alunos.getNome(),
                alunos.getEmail()
        };
        jdbcTemplate.update(INSERT, aluno);
        return alunos;
    }

    public List<AlunosEntity> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<AlunosEntity>(){
            @Override
            public AlunosEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
                AlunosEntity alunos = new AlunosEntity();
                alunos.setId(rs.getLong("id"));
                alunos.setNome(rs.getString("nome"));
                alunos.setEmail(rs.getString("email"));
                return alunos;
            }
        });
    }

    public int remove (Long id) {
        return jdbcTemplate.update(DELETE, id);
    }

    public AlunosEntity update (AlunosEntity alunos) {
        Object[] aluno = new Object[]{
                alunos.getNome(),
                alunos.getEmail(),
                alunos.getId()
        };
        jdbcTemplate.update(UPDATE, aluno);
        return alunos;
    }

}
