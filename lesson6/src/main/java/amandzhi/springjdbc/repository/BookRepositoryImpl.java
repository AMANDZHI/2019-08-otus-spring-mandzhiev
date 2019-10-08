package amandzhi.springjdbc.repository;

import amandzhi.springjdbc.model.Author;
import amandzhi.springjdbc.model.Book;
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
public class BookRepositoryImpl implements BookRepository<Book> {

    private final NamedParameterJdbcOperations template;

    private AuthorRepository<Author> authorRepository;

    private GenreRepository<Genre> genreRepository;

    @Autowired
    public BookRepositoryImpl(NamedParameterJdbcOperations template, AuthorRepository<Author> authorRepository, GenreRepository<Genre> genreRepository) {
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
        final String query = "SELECT BOOKS.id , BOOKS.title, AUTHORS.id , AUTHORS.name , GENRES.id , GENRES.name  FROM BOOKS JOIN AUTHORS ON AUTHORS.id = BOOKS.author_id JOIN GENRES ON GENRES.id = BOOKS.genre_id  WHERE BOOKS.id = :bookId";
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
        final String query = "SELECT BOOKS.id , BOOKS.title, AUTHORS.id , AUTHORS.name , GENRES.id , GENRES.name  FROM BOOKS JOIN AUTHORS ON AUTHORS.id = BOOKS.author_id JOIN GENRES ON GENRES.id = BOOKS.genre_id  WHERE BOOKS.title = :bookTitle";
        return template.queryForObject(query, new MapSqlParameterSource(
                "bookTitle", title), new BookMapper());
    }

    @Override
    public Collection<Book> findAll() {
        final String query = "SELECT BOOKS.id , BOOKS.title, AUTHORS.id , AUTHORS.name , GENRES.id , GENRES.name  FROM BOOKS JOIN AUTHORS ON AUTHORS.id = BOOKS.author_id JOIN GENRES ON GENRES.id = BOOKS.genre_id";
        return template.query(query, new BookMapper());
    }

    private final class BookMapper implements RowMapper<Book> {
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long bookId = rs.getLong("BOOKS.id");
            Long authorId = rs.getLong("AUTHORS.id");
            Long genreId = rs.getLong("GENRES.id");
            String authorName = rs.getString("AUTHORS.name");
            String genreName = rs.getString("GENRES.name");
            String bookTitle = rs.getString("BOOKS.title");
            Author author = new Author(authorId, authorName);
            Genre genre = new Genre(genreId, genreName);
            return new Book(bookId, bookTitle, author, genre);
        }
    }
}