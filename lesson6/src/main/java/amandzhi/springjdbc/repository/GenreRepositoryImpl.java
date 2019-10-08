package amandzhi.springjdbc.repository;

import amandzhi.springjdbc.model.Genre;
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
public class GenreRepositoryImpl implements GenreRepository<Genre> {
    @Autowired
    private NamedParameterJdbcOperations template;

    @Override
    public boolean insert(Genre genre) {
        final String query = "INSERT INTO GENRES(name) VALUES (:genreName)";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("genreName", genre.getName());
        int result = template.update(query, param);
        return result == 1;
    }

    @Override
    public boolean update(Genre genre) {
        final String query = "UPDATE GENRES set name=:genreName WHERE id=:genreId";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("genreName", genre.getName());
        map.put("genreId", genre.getId());
        int result = template.update(query, map);
        return result == 1;
    }

    @Override
    public Genre findById(Long id) {
        final String query = "SELECT id, name FROM GENRES WHERE id = :genreId";
        return template.queryForObject(query, new MapSqlParameterSource(
                "genreId", id), new GenreMapper());
    }

    @Override
    public boolean deleteById(Long id) {
        final String query = "DELETE from GENRES WHERE id=:genreId";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("genreId", id);
        int result = template.update(query, map);
        return result == 1;
    }

    @Override
    public Genre findByName(String name) {
        final String query = "SELECT id, name FROM GENRES WHERE name = :genreName";
        return template.queryForObject(query, new MapSqlParameterSource(
                "genreName", name), new GenreMapper());
    }

    @Override
    public Collection<Genre> findAll() {
        final String query = "SELECT * FROM GENRES";
        return template.query(query, new GenreMapper());
    }

    private static final class GenreMapper implements RowMapper<Genre> {
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            Genre genre = new Genre();
            genre.setId(rs.getLong("id"));
            genre.setName(rs.getString("name"));
            return genre;
        }
    }
}