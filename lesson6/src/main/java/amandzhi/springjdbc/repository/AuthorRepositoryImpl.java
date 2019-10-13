package amandzhi.springjdbc.repository;

import amandzhi.springjdbc.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    @Autowired
    private NamedParameterJdbcOperations template;

    @Override
    public boolean insert(Author author) {
        final String query = "INSERT INTO AUTHORS(name) VALUES (:authorName)";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("authorName", author.getName());
        int result = template.update(query, param);
        return result == 1;
    }

    @Override
    public boolean update(Author author) {
        final String query = "UPDATE AUTHORS SET name=:authorName WHERE id=:authorId";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("authorName", author.getName());
        map.put("authorId", author.getId());
        int result = template.update(query, map);
        return result == 1;
    }

    @Override
    public Author findById(Long id) {
        final String query = "SELECT id, name FROM AUTHORS WHERE id = :authorId";
        return template.queryForObject(query, new MapSqlParameterSource(
                "authorId", id), new AuthorMapper());
    }

    @Override
    public boolean deleteById(Long id) {
        final String query = "DELETE from AUTHORS WHERE id=:id";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        int result = template.update(query, map);
        return result == 1;
    }

    @Override
    public Author findByName(String name) {
        final String query = "SELECT id, name FROM AUTHORS WHERE name = :authorName";
        return template.queryForObject(query, new MapSqlParameterSource(
                "authorName", name), new AuthorMapper());
    }

    @Override
    public Collection<Author> findAll() {
        final String query = "SELECT * FROM AUTHORS";
        return template.query(query, new AuthorMapper());
    }

    private static final class AuthorMapper implements RowMapper<Author> {
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            Author author = new Author();
            author.setId(rs.getLong("id"));
            author.setName(rs.getString("name"));
            return author;
        }
    }
}