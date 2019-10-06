package amandzhi.springjdbc.repository;

import amandzhi.springjdbc.model.Author;
import amandzhi.springjdbc.model.Book;
import amandzhi.springjdbc.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

@Repository(value = "bookRepository")
public class BookRepositoryDaoImpl implements RepositoryDao<Book> {

    private final NamedParameterJdbcOperations template;

    private RepositoryDao<Author> authorRepository;

    private  RepositoryDao<Genre> genreRepository;

    @Autowired
    public BookRepositoryDaoImpl(NamedParameterJdbcOperations template,@Qualifier("authorRepository") RepositoryDao<Author> authorRepository, @Qualifier("genreRepository") RepositoryDao<Genre> genreRepository) {
        this.template = template;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }


    @Override
    public boolean insert(Book book) {
        final String query = "INSERT INTO BOOKS(title, author_id, genre_id) VALUES (:title, :author, :genre)";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("title", book.getTitle())
                .addValue("author", book.getAuthor().getId())
                .addValue("genre", book.getGenre().getId());
        int result = template.update(query, param);
        return result == 1;
    }

    @Override
    public boolean update(Book book) {
        final String query = "UPDATE BOOKS set title=:title, author_id=:author, genre_id=:genre WHERE id=:bookId";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", book.getTitle());
        map.put("author", book.getAuthor().getId());
        map.put("genre", book.getGenre().getId());
        map.put("bookId", book.getId());
        int result = template.update(query, map);
        return result == 1;
    }

    @Override
    public Book findById(Long id) {
        final String query = "SELECT id, title, author_id, genre_id FROM BOOKS WHERE id = :bookId";
        return template.queryForObject(query, new MapSqlParameterSource(
                "bookId", id), new BookMapper());
    }

    @Override
    public boolean deleteById(Long id) {
        final String query = "DELETE from BOOKS WHERE id=:bookId";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("bookId", id);
        int result = template.update(query, map);
        return result == 1;
    }

    @Override
    public Book findByName(String title) {
        final String query = "SELECT id, title, author_id, genre_id FROM BOOKS WHERE title = :bookTitle";
        return template.queryForObject(query, new MapSqlParameterSource(
                "bookTitle", title), new BookMapper());
    }

    @Override
    public Collection<Book> findAll() {
        final String query = "SELECT * FROM BOOKS";
        return template.query(query, new BookMapper());
    }

    private final class BookMapper implements RowMapper<Book> {
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            Long bookId = rs.getLong("id");
            Long authorId = rs.getLong("author_id");
            Long genreId = rs.getLong("genre_id");
            String bookTitle = rs.getString("title");
            Author author = authorRepository.findById(authorId);
            Genre genre = genreRepository.findById(genreId);
            book.setId(bookId);
            book.setTitle(bookTitle);
            book.setAuthor(author);
            book.setGenre(genre);
            return book;
        }
    }
}