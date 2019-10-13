package amandzhi.springjdbc.repository;

import amandzhi.springjdbc.model.Author;
import amandzhi.springjdbc.model.Book;
import amandzhi.springjdbc.model.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

@DisplayName("DAO для книг")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(value = {BookRepositoryImpl.class, AuthorRepositoryImpl.class, GenreRepositoryImpl.class})
class BookRepositoryImplTest {

    @Autowired
    private BookRepositoryImpl bookRepositoryDao;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void insert() {
        Author author = new Author();
        author.setName("testamandzhi");
        author.setId(11L);
        Genre genre = new Genre(11L, "testfantasy");
        Book book = new Book(null, "newMyBook", author, genre);
        boolean insert = bookRepositoryDao.insert(book);
        Book findBook = bookRepositoryDao.findByName(book.getTitle());
        Assertions.assertTrue(insert);
        Assertions.assertNotNull(findBook);
        Assertions.assertEquals(book.getTitle(), findBook.getTitle());
        Assertions.assertEquals(book.getAuthor(), findBook.getAuthor());
        Assertions.assertEquals(book.getGenre(), findBook.getGenre());
    }

    @Test
    void update() {
        Author author = new Author(11L, "testjamandzhi");
        Genre genre = new Genre(11L, "testfantasy");;
        Book book = new Book(24L, "newUpdateMyBook", author, genre);
        boolean update = bookRepositoryDao.update(book);
        Book findBook = bookRepositoryDao.findByName(book.getTitle());
        Assertions.assertTrue(update);
        Assertions.assertNotNull(findBook);
    }


    @Test
    void findById() {
        Book book = bookRepositoryDao.findById(22L);
        Assertions.assertNotNull(book);
        Assertions.assertNotNull(book.getTitle());
        Assertions.assertNotNull(book.getAuthor());
        Assertions.assertNotNull(book.getGenre());
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
        Assertions.assertNotNull(book.getTitle());
        Assertions.assertNotNull(book.getAuthor());
        Assertions.assertNotNull(book.getGenre());
    }

    @Test
    void findAll() {
        Collection<Book> all = bookRepositoryDao.findAll();
        Assertions.assertEquals(3, all.size());
        for (Book b: all) {
            Assertions.assertNotNull(b);
            Assertions.assertNotNull(b.getTitle());
            Assertions.assertNotNull(b.getAuthor());
            Assertions.assertNotNull(b.getGenre());
        }
    }
}