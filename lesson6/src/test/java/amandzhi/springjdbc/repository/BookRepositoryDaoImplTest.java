package amandzhi.springjdbc.repository;

import amandzhi.springjdbc.model.Author;
import amandzhi.springjdbc.model.Book;
import amandzhi.springjdbc.model.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

@DisplayName("DAO для книг")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(value = {BookRepositoryDaoImpl.class, AuthorRepositoryDaoImpl.class, GenreRepositoryDaoImpl.class})
class BookRepositoryDaoImplTest {

    @Autowired
    private BookRepositoryDaoImpl bookRepositoryDao;

    @Autowired
    @Qualifier("authorRepository")
    private RepositoryDao<Author> authorRepositoryDao;

    @Autowired
    @Qualifier("genreRepository")
    private  RepositoryDao<Genre> genreRepositoryDao;

    @Test
    void insert() {
        Author author = new Author();
        author.setName("testjamandzhi");
        author.setId(11L);
        Genre genre = new Genre();
        genre.setName("testfantasy");
        genre.setId(11L);
        Book book = new Book();
        book.setTitle("newMyBook");
        book.setAuthor(author);
        book.setGenre(genre);
        boolean insert = bookRepositoryDao.insert(book);
        Assertions.assertTrue(insert);
    }

    @Test
    void update() {
        Author author = new Author();
        author.setName("testjamandzhi");
        author.setId(11L);
        Genre genre = new Genre();
        genre.setName("testfantasy");
        genre.setId(11L);
        Book book = new Book();
        book.setId(24L);
        book.setTitle("newUpdateMyBook");
        book.setAuthor(author);
        book.setGenre(genre);
        boolean insert = bookRepositoryDao.update(book);
        Assertions.assertTrue(insert);
    }


    @Test
    void findById() {
                Book book = bookRepositoryDao.findById(22L);
        Assertions.assertNotNull(book);
    }

    @Test
    void deleteById() {
        boolean delete = bookRepositoryDao.deleteById(23L);
        Assertions.assertTrue(delete);
    }

    @Test
    void findByName() {
                Book book = bookRepositoryDao.findByName("testjamandzhi");
        Assertions.assertNotNull(book);
    }

    @Test
    void findAll() {
        Collection<Book> all = bookRepositoryDao.findAll();
        Assertions.assertEquals(3, all.size());
    }
}